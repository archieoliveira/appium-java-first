package com.first.tests;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;


public class YoutubeTeste {
    public static void main(String[] args) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "com.google.android.youtube");
        caps.setCapability("appActivity", ".HomeActivity");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement youtubeIcon = driver.findElement(By.xpath("//android.widget.TextView[contains(@text, 'YouTube')]"));
        youtubeIcon.click();

        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence tap = new Sequence(input, 1);
        tap.addAction(input.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 553, 1257));
        tap.addAction(input.createPointerDown(0)); // Correção aqui
        tap.addAction(input.createPointerUp(0));
        driver.perform(List.of(tap));

        WebElement searchIcon = driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='Search']"));
        searchIcon.click();


        WebElement searchBox = driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.google.android.youtube:id/search_edit_text']"));
        searchBox.sendKeys("Taylor Swift Delicate");


        WebElement firstResult = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.TextView[contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'taylor swift delicate')]")
        ));
        firstResult.click();

        WebElement firstVideo = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.view.ViewGroup[contains(@content-desc, 'Taylor Swift - Delicate')]")
        ));
        firstVideo.click();
    }
}


