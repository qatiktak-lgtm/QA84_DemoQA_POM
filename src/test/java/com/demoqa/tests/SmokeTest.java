package com.demoqa.tests;


import com.demoqa.core.TestBase;
import com.demoqa.utils.LoggerWriter;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class SmokeTest extends TestBase {

    @Test
    public void testBrowserOpened() {
        LoggerWriter.logStep("Verifying browser is opened");

        String title = driver.getTitle();
        LoggerWriter.logInfo("Page title: " + title);

        assertThat(title).isNotEmpty();
        LoggerWriter.logStep("Test passed successfully");
    }
}
