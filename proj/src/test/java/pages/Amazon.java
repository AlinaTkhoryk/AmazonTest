package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Amazon {

    @FindBy(id = "twotabsearchtextbox")
    WebElement searchBox;

    @FindBy(xpath = "//a[normalize-space()='Sneakers']")
    WebElement sneakersFilterButton;

    @FindBy(id = "low-price")
    WebElement minPriceFilter;

    @FindBy(id = "a-autoid-1")
    WebElement goButton;

    //@FindBy(xpath = "//*[contains(@data-component-type,'s-search-result')]")
    @FindBy(xpath = "//*[contains(@data-component-type,'s-search-result')]//div[@data-component-type='s-search-result']")
    List<WebElement> allSneakers;

    public Map<String, String> getAllDistinctProducts(String defaultPriceValue) {
        Map<String, String> result = new HashMap<>();
        for (WebElement el : allSneakers) {
            String elementData = el.getText();
            String[] viewData = elementData.split("\n");

            int index = viewData.length == 3 ? 1 : 0;

            String name = elementData;

            if (index < viewData.length) {
                name = viewData[index];
            } else {
                System.out.println("Could not get name from data" + elementData);
            }
            index++;
            String price = defaultPriceValue;

            if (index < viewData.length) {
                price ="$"+ viewData[index];
            } else {
                System.out.println("Could not get price from data" + elementData);
            }
            result.put(name, price);
        }
        return result;
    }

    public void searchProduct(String text) {
        searchBox.clear();
        searchBox.sendKeys(text);
        searchBox.sendKeys(Keys.ENTER);
    }

    public void setMinPriceFilter(String text) {
        minPriceFilter.clear();
        minPriceFilter.sendKeys(text);
    }

    public void setSneakersType() {
        sneakersFilterButton.click();
    }

    public void goSearch() {
        goButton.click();
    }
}
