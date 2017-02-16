package pages;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchResultsPage extends FluentPage {

    private By sortingBarLocator = By.cssSelector("div[class^='SortingBar']");
    private By tabsLocator = By.tagName("div");
    private By transportationMeansGroupLocator = By.cssSelector("div[class^='ResultTabs__resultTabsHeader']");
    private By transportationMeansLocator = By.cssSelector("a[class^='ResultTabs__']");
    private By resultPriceLocator = By.cssSelector("div[class^='Result__resultPrice']");
    private By priceMainPartLocator = By.cssSelector("span[class^='Result__priceMain']");
    private By pricePointPartLocator = By.cssSelector("span[class^='Result__pricePoint']");
    private By priceFractionPartLocator = By.cssSelector("span[class^='Result__priceFraction']");

    @Override
    public void isAt() {
        await().atMost(10, TimeUnit.SECONDS).until(() -> find(sortingBarLocator).present());
    }

    public void clickOnCheapestTab() {
        // (no fixed IDs were found on the page, then I take the approach of using the index of the tab)
        find(sortingBarLocator).
                find(tabsLocator).
                get(1).click();
    }

    public void clickOnTrainTab() {
        // (no fixed IDs were found on the page, then I take the approach of clicking on the first tab)
        find(transportationMeansGroupLocator).
                find(transportationMeansLocator).
                first().
                click();
    }

    public List<Float> getListOfPrices() {
        List<Float> pricesList = new ArrayList<>();
        for (FluentWebElement priceElement : find(resultPriceLocator)) {
            String price = priceElement.find(priceMainPartLocator).text() +
                    priceElement.find(pricePointPartLocator).text() +
                    priceElement.find(priceFractionPartLocator).text();
            pricesList.add(Float.parseFloat(price));
        }
        return pricesList;
    }

}
