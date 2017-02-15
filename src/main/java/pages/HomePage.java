package pages;

import org.fluentlenium.core.FluentPage;
import static org.assertj.core.api.Assertions.assertThat;

public class HomePage extends FluentPage {

    @Override
    public String getUrl() {
        return "https://www.goeuro.com/";
    }

    @Override
    public void isAt() {
        assertThat(window().title()).containsIgnoringCase("GoEuro");
    }
}
