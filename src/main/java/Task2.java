import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class Task2 {

    public WebDriver task2(){

        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-extensions");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//div/button[@aria-label='Consent']")).click();

        WebElement WidgetsTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/div/h5[text() = 'Elements']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", WidgetsTab);
        WidgetsTab.click();

        WebElement ProgressBar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/ul/li/span[text() = 'Web Tables']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ProgressBar);
        ProgressBar.click();


        while (true) {
            addElement(driver, wait);
            int i = 0;
            if (driver.findElement(By.xpath("//div/button[text()='Next']")).isEnabled() && i < 20) {
                break; // break if next button is enabled
            }
            ++i;
        }

        WebElement NextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/button[text()='Next']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", NextButton);
        NextButton.click();

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement DeleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/span[@title='Delete']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", DeleteButton);
        DeleteButton.click();

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean previous = !driver.findElement(By.xpath("//div/button[text()='Previous']")).isEnabled();
        WebElement totalPages = driver.findElement(By.xpath("//span[@class='-totalPages' and text()=1]"));
        String totalPagesText = totalPages.getText();

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(previous && totalPagesText.equals("1")) {
            System.out.println("Pagination automatically moves to the first page");
        } else {
            System.out.println("Pagination does not automatically move to the first page");
        }

        //driver.findElement(By.xpath("//span[@class='-totalPages' and text()=1]"));

//        WebElement InputAreaLabel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/span/div/input[@aria-label='jump to page']")));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", InputAreaLabel);
//        InputAreaLabel.sendKeys("1");

        return driver;
    }

    public void addElement(WebDriver driver, WebDriverWait wait) {
        WebElement AddButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/button[text()='Add']")));
        AddButton.click();

        WebElement FirstName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/input[@id='firstName']")));
        FirstName.sendKeys("Klebonas");

        WebElement LastName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/input[@id='lastName']")));
        LastName.sendKeys("Valaitis");

        WebElement Email = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/input[@id='userEmail']")));
        Email.sendKeys("LaiskuDezute@gmail.com");

        WebElement Age = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/input[@id='age']")));
        Age.sendKeys("37");

        WebElement Salary = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/input[@id='salary']")));
        Salary.sendKeys("1000000");

        WebElement Department = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/input[@id='department']")));
        Department.sendKeys("IT");

        WebElement SubmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/button[text()='Submit']")));
        SubmitButton.click();

    }

    public void quitDriver(WebDriver driver) {
        driver.quit();
    }
}


//Task 2:
//
//Open https://demoga.com/
//Close the Cookies consent window.
//Select the "Elements" tab.
//Choose the menu item "Web Tables".
//Add enough elements for a second page to appear in the pagination.
//Select the second page by clicking "Next".
//Delete an element on the second page.
//Ensure that the pagination automatically moves to the first page and that the number of pages has decreased to one.
