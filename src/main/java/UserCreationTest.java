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

import java.io.FileReader;
import java.io.FileWriter;
import java.time.Duration;


//Task:
//Create two automated tests, task conditions:
//          - A new user must be created before the tests can be run automatically.
//          - Both tests must log in with the same created user.
//          - The tests and the user creation must be executed in separate webdriver sessions.
//          - Use the annotations of the Unit Tests to invoke and close the webdriver sessions.
//          - Run the tests via a Jenkins job with a cron scheduler (subject of the next lecture).
//
//
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

public class UserCreationTest{

    private static WebDriver driver;
    private static WebDriverWait wait;

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

    @Test
    public User createUser(){

        User user = new User();

        driver.get("https://demowebshop.tricentis.com/");
        WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='ico-register']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerButton);
        registerButton.click();

        // Fill in the registration form
        wait.until(ExpectedConditions.elementToBeClickable(By.id("gender-male"))).click();
        driver.findElement(By.id("FirstName")).sendKeys("Lempa");
        driver.findElement(By.id("LastName")).sendKeys("IsIkea");

        int randomInt = (int) (Math.random() * 1000 + 1);
        String emailString = "vailitismanotetis" + randomInt + "@gmail.com";
        driver.findElement(By.id("Email")).sendKeys(emailString);
        driver.findElement(By.id("Password")).sendKeys("slaptas");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("slaptas");

        user.setEmail(emailString);
        user.setPassword("slaptas");

        driver.findElement(By.id("register-button")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='button' and @value='Continue']"))).click();

        return user;
    }

    public void saveUserData(User user){
        try(FileWriter fileWriter = new FileWriter("userData.txt", false)){
            fileWriter.write(user.getEmail() + "\n" + user.getPassword() );
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public User readUserData(){
        User user = new User();

    }

    @AfterEach
    public void tearDown(){
        if(driver != null)
                driver.quit();
    }

}







//public WebDriver userCreation(){
//
//    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
//
//    ChromeOptions options = new ChromeOptions();
//    options.setExperimentalOption("useAutomationExtension", false);
//    options.addArguments("--disable-extensions");
//
//    WebDriver driver = new ChromeDriver(options);
//    driver.get("https://demowebshop.tricentis.com/");
//    driver.manage().window().maximize();
//
//    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    driver.findElement(By.xpath("//a[@class='ico-login']"));
//
//    WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='ico-register']")));
//    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerButton);
//    registerButton.click();
//
//    WebElement gender = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='gender-male']")));
//    gender.click();
//
//    WebElement firstName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='FirstName']")));
//    firstName.sendKeys("Lempa");
//
//    WebElement lastName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='LastName']")));
//    lastName.sendKeys("IsIkea");
//
//    //generate a random integer for the email
//    int randomInt = (int)(Math.random() * 1000 + 1);
//    String emailString = "vailitismanotetis" + randomInt + "@gmail.com";
//    WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='Email']")));
//    email.sendKeys(emailString);
//
//    WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='Password']")));
//    password.sendKeys("slaptas");
//
//    WebElement confirmPassword = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='ConfirmPassword']")));
//    confirmPassword.sendKeys("slaptas");
//
//    WebElement register = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='register-button']")));
//    register.click();
//
//    WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='button' and @value='Continue']")));
//    continueButton.click();
//
//    return driver;
//}