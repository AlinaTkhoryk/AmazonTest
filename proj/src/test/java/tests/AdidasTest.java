package tests;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Amazon;

import java.util.Map;

public class AdidasTest extends BaseTest {

    @Test
    public void testCase1() {

        LOG.info("Open amazon page");
        driver.get("https://amazon.com/");
        Amazon storePage = PageFactory.initElements(driver, Amazon.class);
        LOG.info("Amazon page opened");

        LOG.info("Setup search start");
        storePage.searchProduct("adidas shoes men");
        storePage.setSneakersType();
        storePage.setMinPriceFilter("40");
        LOG.info("Setup search finished");

        storePage.goSearch();
        LOG.info("Search run");

        LOG.info("pull search results");
        String defaultPriceValue = "$0";
        Map<String, String> mapOfDistinctSneakers = storePage.getAllDistinctProducts(defaultPriceValue);
        LOG.debug(String.format("Found {0} items", mapOfDistinctSneakers.size()));

        SoftAssert softAssert = new SoftAssert();

        LOG.info("Search results validation start");

        mapOfDistinctSneakers.forEach((key, value) ->
        {
            System.out.println(key + " --- " + value);
            if(value == defaultPriceValue){
                LOG.error(String.format("Product"+key+" doesn't has price"));
                softAssert.assertNotEquals(value, defaultPriceValue);
            }
        });

        LOG.info("Test finished"); 
    }


}