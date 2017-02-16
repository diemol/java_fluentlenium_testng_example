package pages;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePage extends BasePage {

    private By departureRowSelector = By.cssSelector(".departure.row-input");
    private By arrivalRowSelector = By.cssSelector(".arrival.row-input");
    private By citySelector = By.id("$city");
    private By searchButtonSelector = By.cssSelector(".sb-field.search-button");
    private By partnerSelector = By.className("sb-partner");
    private By partnerIconSelector = By.className("sb-icon");

    @SuppressWarnings("unused")
    @Page
    private SearchResultsPage searchResultsPage;

    @Override
    public String getUrl() {
        return "https://www.goeuro.com/";
    }

    @Step("Open GoEuro.com")
    @Override
    public void isAt() {
        attachScreenShot("open_GoEuro");
        assertThat(window().title()).containsIgnoringCase("GoEuro");
    }

    @Step("Search route between {0} and {1}")
    public SearchResultsPage searchRoute(String departureCity, String arrivalCity) {
        find(departureRowSelector).
                find(citySelector).
                write(departureCity);

        find(arrivalRowSelector).
                find(citySelector).
                write(arrivalCity);

        find(searchButtonSelector).
                click();

        attachScreenShot("search_route");

        return searchResultsPage;
    }

    @Step("Remove search accommodation check")
    public void removeSearchAccommodationCheck() {
        find(partnerSelector).
                find(partnerIconSelector).
                click();
        attachScreenShot("remove_search_accommodation_check");
    }

}
