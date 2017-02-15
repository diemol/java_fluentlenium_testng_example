package pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchResultsPage extends FluentPage {

    private By sortingBarLocator = By.cssSelector("div[class^='SortingBar']");

    @Override
    public void isAt() {
        await().atMost(10, TimeUnit.SECONDS).until(() -> find(sortingBarLocator).present());
    }

}
