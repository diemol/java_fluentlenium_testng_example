package pages;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePage extends FluentPage {

    private By departureRowSelector = By.cssSelector(".departure.row-input");
    private By arrivalRowSelector = By.cssSelector(".arrival.row-input");
    private By citySelector = By.id("$city");
    private By searchButtonSelector = By.cssSelector(".sb-field.search-button");
    private By partnerSelector = By.className("sb-partner");
    private By partnerIconSelector = By.className("sb-icon");

    @Page
    private SearchResultsPage searchResultsPage;

    @Override
    public String getUrl() {
        return "https://www.goeuro.com/";
    }

    @Override
    public void isAt() {
        assertThat(window().title()).containsIgnoringCase("GoEuro");
    }

    public SearchResultsPage searchRoute(String departureCity, String arrivalCity) {
        find(departureRowSelector).
                find(citySelector).
                write(departureCity);

        find(arrivalRowSelector).
                find(citySelector).
                write(arrivalCity);

        find(searchButtonSelector).
                click();

        return searchResultsPage;
    }

    public void removeSearchAccommodationCheck() {
        find(partnerSelector).
                find(partnerIconSelector).
                click();
    }
}
