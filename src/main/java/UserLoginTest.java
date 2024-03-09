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

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
    public void loginWithCreatedUser(){

        User user = new User();
        user = user.readUserData();


        driver.get("https://demowebshop.tricentis.com/");
        driver.findElement(By.xpath("//li/a[@class='ico-login']")).click();

        driver.findElement(By.id("Email")).sendKeys(user.getEmail());
        driver.findElement(By.id("Password")).sendKeys(user.getPassword());
        driver.findElement(By.cssSelector("input[value='Log in']")).click();

        driver.findElement(By.xpath("//div[@class='listbox']/ul[@class='list']/li/a[@href='/digital-downloads']")).click();




    }

}
