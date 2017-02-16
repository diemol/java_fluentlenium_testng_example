package pages;

import org.apache.commons.io.FileUtils;
import org.fluentlenium.core.FluentPage;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;

public class BasePage extends FluentPage {
    @Attachment
    public byte[] attachScreenShot(String fileName) {
        takeScreenShot("target/screenshots/" + fileName + ".png");
        File screenShot = new File("target/screenshots/" + fileName + ".png");
        try {
            return FileUtils.readFileToByteArray(screenShot);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
