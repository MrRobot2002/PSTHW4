//User development workflow:
//           1. Open the website https://demowebshop.tricentis.com/
//           2. Click 'Log in'
//           3. Click 'Register' under 'New Customer'
//           4. Fill in the fields on the registration form
//           5. Click 'Register'
//           6. Click 'Continue'

//Test 1:   createa a new user with @beforeeach annotation and log in with the created user
//          I need to run these tests with cron scheduler Jenkins job
//
//Test scenarios:
//          1. Open the website https://demowebshop.tricentis.com/
//          2. Click 'Log in'
//          3. Fill in 'Email:', 'Password:' and click 'Log in'
//          4. Select 'Digital downloads' from the side menu
//          5. Add items to your basket by reading the text file (for the first test read from data1.txt, for the second test read from data2.txt)
//          6. Open 'Shopping cart'
//          7. Click on the 'I agree' checkbox and the 'Checkout' button
//          8. in 'Billing Address' select an existing address or fill in the fields for a new address, click 'Continue'
//          9. for 'Payment Method' click 'Continue'
//          10. 'Payment Information' click 'Continue'
//          11. 'Confirm Order' click 'Confirm'
//          12. 'Confirm''.
//
//          data1.txt: data2.txt:
//          3rd Album 3rd Album
//          3rd Album Music 2
//          3rd Album

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Scanner;

public class UserLoginTest {

    public static WebDriver driver;
    public static WebDriverWait wait;

    //Test 2:   log in with the created user
    //          I need to run these tests with cron scheduler Jenkins job

    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-extensions");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown(){
        if(driver != null)
            driver.quit();
    }


    public void loginWithCreatedUser(String fileName){

        User user = new User();
        user = user.readUserData();


        driver.get("https://demowebshop.tricentis.com/");
        driver.findElement(By.xpath("//li/a[@class='ico-login']")).click();

        driver.findElement(By.id("Email")).sendKeys(user.getEmail());
        driver.findElement(By.id("Password")).sendKeys(user.getPassword());
        driver.findElement(By.cssSelector("input[value='Log in']")).click();

        driver.findElement(By.xpath("//div[@class='listbox']/ul[@class='list']/li/a[@href='/digital-downloads']")).click();

        String content = readData(fileName);
        System.out.println("Content: \n" + content);

        String[] items = content.split("\n");
        System.out.println("Items:");

        String previousPage = driver.getCurrentUrl();

        for(String item : items){

            System.out.println("'" + item + "'");

            if(item.equals("3rd Album")) {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2/a[text()='" + item + "']"))).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/input[@id='add-to-cart-button-53']"))).click();
            }
            else{
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2/a[text()='" + item + "']"))).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/input[@id='add-to-cart-button-51']"))).click();
            }

            try {
                Thread.sleep(1000); // 1000 milliseconds = 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            driver.get(previousPage);
        }

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/ul/li/a/span[text()='Shopping cart']"))).click();
        driver.findElement(By.id("termsofservice")).click();
        driver.findElement(By.id("checkout")).click();


        try{
            driver.findElement(By.id("billing-address-select"));
        }catch(Exception e){
            driver.findElement(By.id("BillingNewAddress_CountryId")).click();
            driver.findElement(By.xpath("//option[text()='Lithuania']")).click();
            driver.findElement(By.id("BillingNewAddress_City")).sendKeys("Vilnius");
            driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("Gedimino pr. 1");
            driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("LT-01103");
            driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("+37060000000");
            driver.findElement(By.cssSelector("input[value='Continue']")).click();
        }

        WebElement continueButtonn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='button-1 new-address-next-step-button']")));
        continueButtonn.click();

        By continueButtonSelector = By.xpath("//input[@value='Continue' and @class='button-1 payment-method-next-step-button']");
        WebElement continueButton = driver.findElement(continueButtonSelector);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueButton);
        continueButton = wait.until(ExpectedConditions.elementToBeClickable(continueButtonSelector));
        continueButton.click();

        WebElement continueButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Continue' and @class='button-1 payment-info-next-step-button']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueButton2);
        continueButton2.click();

        WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Confirm' and @class='button-1 confirm-order-next-step-button']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", confirmButton);
        confirmButton.click();

    }

    //read file data1.txt and return the content
    public String readData(String resourcePath) {
        StringBuilder contentBuilder = new StringBuilder();
        // Use the current thread's class loader to get the resource as a stream
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to read file: " + resourcePath;
        }
        return contentBuilder.toString();
    }

    @Test
    public void Test1(){
        loginWithCreatedUser("data1.txt");
    }
    @Test
    public void Test2(){
        loginWithCreatedUser("data2.txt");
    }


}
