import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Task1 {

    public WebDriver task1() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-extensions");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();

        //Close the Cookies consent window.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.findElement(By.xpath("//div/button[@aria-label='Consent']")).click();

        //Select the "Widgets" tab.
        WebElement WidgetsTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/div/h5[text() = 'Widgets']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", WidgetsTab);
        WidgetsTab.click();

        //Choose the menu item "Progress Bar".
        WebElement ProgressBar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/ul/li/span[text() = 'Progress Bar']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ProgressBar);
        ProgressBar.click();

        //Click the "Start" button.
        WebElement StartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/button[text()='Start']")));
        StartButton.click();

        //Wait until it reaches 100% and click "Reset".
        WebElement ProgressBar100 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/div[@aria-valuenow='100' and text()='100%']")));

        //Ensure that the progress bar is empty (0%).
        WebElement resetButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Reset']")));
        resetButton.click();

        return driver;

    }

    public void quitDriver(WebDriver driver) {
        driver.quit();
    }
}
//Task 1:
//
//Open https://demoqa.com/
//Close the Cookies consent window.
//Select the "Widgets" tab.
//Choose the menu item "Progress Bar".
//Click the "Start" button.
//Wait until it reaches 100% and click "Reset".
//Ensure that the progress bar is empty (0%).
