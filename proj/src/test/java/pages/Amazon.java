package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Optional;

import java.util.HashMap;
import java.util.LinkedHashMap;
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

    public Map<String, String> getAllDistinctProducts() {
        Map<String, String> result = new HashMap<>();
        for (WebElement el : allSneakers) {
            String elementData = el.getText();
            String[] viewData = elementData.split("\n");
            int nameIndex = viewData.length - 2;

            String name = elementData;

            if (nameIndex > 0) {
                name = viewData[nameIndex];
            } else {
                //todo make a log;
            }
            String price = "$0.00";
            int priceIndex = viewData.length - 1;

            if (priceIndex > 0) {
                price = viewData[priceIndex];
            } else {
                //todo make a log;
            }
            result.put(name, price);
            System.out.println(String.format("found price {0} {1}", name, price));
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
