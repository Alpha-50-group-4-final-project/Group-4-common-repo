package WEare;


import com.telerikacademy.testframework.CustomWebDriverManager;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

public  class MyTestWatcher implements TestWatcher {

//    @Override
//    public void testAborted(ExtensionContext context, Throwable cause) {
//        TestWatcher.super.testAborted(context, cause);
//        Allure.addAttachment("Attachment",
//                new ByteArrayInputStream(((TakesScreenshot)CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver()).getScreenshotAs(OutputType.BYTES)));
//    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Allure.addAttachment("Attachment",
                new ByteArrayInputStream(((TakesScreenshot)CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver()).getScreenshotAs(OutputType.BYTES)));
        TestWatcher.super.testFailed(context, cause);

    }
}

