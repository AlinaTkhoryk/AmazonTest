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
        Map<String, String> mapOfDistinctSneakers = storePage.getAllDistinctProducts();
        LOG.debug(String.format("Found {0} items", mapOfDistinctSneakers.size()));
        mapOfDistinctSneakers.forEach((key, value) -> System.out.println(key + " --- " + value));

        LOG.info("Search results validation start");

        //Assert
        SoftAssert softAssert = new SoftAssert();
        //softAssert.


    }


}