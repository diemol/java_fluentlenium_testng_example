import org.fluentlenium.adapter.testng.FluentTestNg;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;

import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTest extends FluentTestNg {

    private static final Logger log = Logger.getLogger(SearchTest.class.getName());

    @SuppressWarnings("unused")
    @Page
    private HomePage homePage;

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
        goTo(homePage);
        homePage.isAt();

        // Avoid getting a 2nd tab from the partner search
        log.info("Removing 'Search accommodation with...' check...");
        homePage.removeSearchAccommodationCheck();

        // Search with default date, for Berlin - Prague routes
        log.info("Typing departure and destination cities...");
        SearchResultsPage searchResultsPage = homePage.searchRoute("Berlin", "Prague");

        log.info("Waiting for elements to load...");
        searchResultsPage.isAt();

        // Click on Cheapest tab
        log.info("Clicking on Cheapest tab...");
        searchResultsPage.clickOnCheapestTab();

        // Click on the Train tab
        log.info("Clicking on the Train tab...");
        searchResultsPage.clickOnTrainTab();

        // Get the list of prices
        // I assume that it is enough to validate the order only on the first page
        log.info("Getting list of prices and asserting the order...");
        List<Float> pricesList = searchResultsPage.getListOfPrices();
        log.info(pricesList.toString());

        // Check that they are sorted in an ascendant order
        assertThat(pricesList).isSorted();

    }

}
