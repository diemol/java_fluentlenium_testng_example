package pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchResultsPage extends FluentPage {

    private By sortingBarLocator = By.cssSelector("div[class^='SortingBar']");
    private By tabsLocator = By.tagName("div");
    private By transportationMeansGroupLocator = By.cssSelector("div[class^='ResultTabs__resultTabsHeader']");
    private By transportationMeansLocator = By.cssSelector("a[class^='ResultTabs__']");

    @Override
    public void isAt() {
        await().atMost(10, TimeUnit.SECONDS).until(() -> find(sortingBarLocator).present());
    }

    public void clickOnCheapestTab() {
        find(sortingBarLocator).
                find(tabsLocator).
                get(1).click();
    }

    public void clickOnTrainTab() {
        find(transportationMeansGroupLocator).
                find(transportationMeansLocator).
                first().
                click();
    }

}
