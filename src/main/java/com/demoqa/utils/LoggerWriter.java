package com.demoqa.utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TestWatcher для логирования результатов тестов в JUnit 6.1.3
 */
public class LoggerWriter implements TestWatcher {
    private static final Logger logger = LoggerFactory.getLogger(LoggerWriter.class);
    private static final String SCREENSHOTS_PATH = "target/screenshots/";
    private static final DateTimeFormatter timeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    /**
     * Вызывается когда тест прошёл успешно
     */
    @Override
    public void testSuccessful(ExtensionContext context) {
        logger.info("==================================================");
        logger.info("✅ PASSED: TEST [{}] Successful", context.getDisplayName());
        logger.info("Class: {}", context.getTestClass().map(Class::getSimpleName).orElse("Unknown"));
        logger.info("Method: {}", context.getTestMethod().map(m -> m.getName()).orElse("Unknown"));
        logger.info("==================================================");
    }

    /**
     * Вызывается когда тест упал
     */
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        logger.error("==================================================");
        logger.error("❌ FAILED: TEST [{}] Failed!", context.getDisplayName());
        logger.error("Class: {}", context.getTestClass().map(Class::getSimpleName).orElse("Unknown"));
        logger.error("Method: {}", context.getTestMethod().map(m -> m.getName()).orElse("Unknown"));
        logger.error("Error Message: {}", cause.getMessage());
        logger.error("Error Type: {}", cause.getClass().getSimpleName());
        logger.error("==================================================");

        captureScreenshot(context);
        logger.debug("Stack Trace: ", cause);
    }

    // ============ Helper методы ============

    /**
     * Сохраняет скриншот при падении теста
     */
    private void captureScreenshot(ExtensionContext context) {
        try {
            WebDriver driver = getWebDriver(context);

            if (driver != null && driver instanceof TakesScreenshot) {
                createScreenshotDirectory();

                String fileName = generateScreenshotName(context);
                String filePath = SCREENSHOTS_PATH + fileName;

                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Files.copy(screenshot.toPath(), Paths.get(filePath));

                logger.error("📸 Screenshot saved: {}", filePath);
            }
        } catch (IOException e) {
            logger.error("Failed to capture screenshot: {}", e.getMessage());
        }
    }

    /**
     * Получает WebDriver из контекста
     */
    private WebDriver getWebDriver(ExtensionContext context) {
        try {
            return context.getStore(ExtensionContext.Namespace.create("webdriver"))
                    .get("driver", WebDriver.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Генерирует имя файла скриншота
     */
    private String generateScreenshotName(ExtensionContext context) {
        String testName = context.getDisplayName();
        String timestamp = LocalDateTime.now().format(timeFormatter);
        return String.format("%s_%s.png", testName.replace(" ", "_"), timestamp);
    }

    /**
     * Создаёт директорию для скриншотов
     */
    private void createScreenshotDirectory() throws IOException {
        Files.createDirectories(Paths.get(SCREENSHOTS_PATH));
    }

    // ============ Дополнительные методы логирования ============

    public static void logStep(String step) {
        logger.info("→ STEP: {}", step);
    }

    public static void logInfo(String message) {
        logger.info("ℹ️ INFO: {}", message);
    }

    public static void logWarning(String message) {
        logger.warn("⚠️ WARNING: {}", message);
    }

    public static void logError(String message) {
        logger.error("❌ ERROR: {}", message);
    }
}