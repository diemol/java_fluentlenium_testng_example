import org.fluentlenium.adapter.testng.FluentTestNg;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTest extends FluentTestNg {

    private static final Logger log = Logger.getLogger(SearchTest.class.getName());

    @Override
    public String getRemoteUrl() {
        return "http://localhost:4444/wd/hub";
    }

    @Override
    public String getWebDriver() {
        return "remote";
    }

    @Override
    public Capabilities getCapabilities() {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability(CapabilityType.PLATFORM, Platform.LINUX);
        return desiredCapabilities;
    }

    /*
        Go to GoEuro home page, search between 'Berlin' and 'Prague' and verify that the sorting price for the train
        results is correct for the 'Cheapest' tab. Correct means that they are sorted in ascendant order.
     */
    @Test
    public void searchTripBetweenBerlinAndPragueOrdersResultsByPriceWhenSelectingCheapestTab() throws Exception {

        // Go to GoEuro.com

        log.info("Loading GoEuro...");
        window().maximize();
        goTo("http://www.goeuro.com/");

        // Input departure and destination cities
        log.info("Typing departure and destination cities...");
        find(By.cssSelector(".departure.row-input")).
                find(By.id("$city")).
                write("Berlin");
        find(By.cssSelector(".arrival.row-input")).
                find(By.id("$city")).
                write("Prague");

        // Avoid getting a 2nd tab from the partner search
        log.info("Removing 'Search accommodation with...' check...");
        find(By.className("sb-partner")).
                find(By.className("sb-icon")).
                click();

        // Search with default date
        log.info("Click on search...");
        find(By.cssSelector(".sb-field.search-button")).
                click();

        // Click on Cheapest tab (no fixed IDs were found on the page, then I take the approach
        // of using the index of the tab).
        log.info("Waiting for elements to load...");
        await().atMost(10, TimeUnit.SECONDS).until(() -> find(By.cssSelector("div[class^='SortingBar']")).present());
        log.info("Clicking on Cheapest tab...");
        find(By.cssSelector("div[class^='SortingBar']")).
                find(By.tagName("div")).
                get(1).click();

        // Click on the Train tab (no fixed IDs were found on the page, then I take the approach
        // of using the index of the tab).
        log.info("Clicking on the Train tab...");
        // find(By.cssSelector(""))
        find(By.cssSelector("div[class^='ResultTabs__resultTabsHeader']")).
                find(By.cssSelector("a[class^='ResultTabs__']")).
                first().click();

        // Get the list of prices
        log.info("Getting list of prices and asserting the order...");
        List<Float> pricesList = new ArrayList<>();
        for (FluentWebElement fluentWebElement : find(By.cssSelector("div[class^='Result__resultPrice']"))) {
            String price = fluentWebElement.find(By.cssSelector("span[class^='Result__priceMain']")).text() +
                    fluentWebElement.find(By.cssSelector("span[class^='Result__pricePoint']")).text() +
                    fluentWebElement.find(By.cssSelector("span[class^='Result__priceFraction']")).text();
            pricesList.add(Float.parseFloat(price));
        }
        log.info(pricesList.toString());

        // Check that they are sorted in an ascendant order
        assertThat(pricesList).isSorted();

    }

}
