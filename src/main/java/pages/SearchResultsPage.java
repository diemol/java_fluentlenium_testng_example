package pages;

import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchResultsPage extends BasePage {

    private By sortingBarLocator = By.cssSelector("div[class^='SortingBar']");
    private By tabsLocator = By.tagName("div");
    private By transportationMeansGroupLocator = By.cssSelector("div[class^='ResultTabs__resultTabsHeader']");
    private By transportationMeansLocator = By.cssSelector("a[class^='ResultTabs__']");
    private By resultPriceLocator = By.cssSelector("div[class^='Result__resultPrice']");
    private By priceMainPartLocator = By.cssSelector("span[class^='Result__priceMain']");
    private By pricePointPartLocator = By.cssSelector("span[class^='Result__pricePoint']");
    private By priceFractionPartLocator = By.cssSelector("span[class^='Result__priceFraction']");

    @Step("Wait for elements to load")
    @Override
    public void isAt() {
        await().atMost(10, TimeUnit.SECONDS).until(() -> find(sortingBarLocator).present());
        attachScreenShot("elements_loaded");
    }

    @Step("Click on Cheapest tab")
    public void clickOnCheapestTab() {
        // (no fixed IDs were found on the page, then I take the approach of using the index of the tab)
        find(sortingBarLocator).
                find(tabsLocator).
                get(1).click();
        attachScreenShot("click_on_cheapest_tab");
    }

    @Step("Click on Train tab")
    public void clickOnTrainTab() {
        // (no fixed IDs were found on the page, then I take the approach of clicking on the first tab)
        find(transportationMeansGroupLocator).
                find(transportationMeansLocator).
                first().
                click();
        attachScreenShot("click_on_cheapest_tab");
    }

    @Step("Getting list of prices")
    public List<Float> getListOfPrices() {
        List<Float> pricesList = new ArrayList<>();
        for (FluentWebElement priceElement : find(resultPriceLocator)) {
            String price = priceElement.find(priceMainPartLocator).text() +
                    priceElement.find(pricePointPartLocator).text() +
                    priceElement.find(priceFractionPartLocator).text();
            pricesList.add(Float.parseFloat(price));
        }
        attachScreenShot("get_list_of_prices");
        return pricesList;
    }

}
