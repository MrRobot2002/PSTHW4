import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args){
//        Task1 task1 = new Task1();
//        WebDriver driver = task1.task1();
//        task1.quitDriver(driver);
//
//        Task2 task2 = new Task2();
//        driver = task2.task2();
//        task2.quitDriver(driver);

//        UserCreationTest userCreationTest = new UserCreationTest();
//        userCreationTest.setUp();
//        User user = userCreationTest.createUser();

        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-extensions");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://2captcha.com/demo/recaptcha-v3");

        WebElement check = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/button[@data-action='demo_action']")));
        check.click();



    }
}























//public class Main {
//    public static void main(String[] args) {
//        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
//
//        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("useAutomationExtension", false);
//        options.addArguments("--disable-extensions");
//
//        WebDriver driver = new ChromeDriver(options);
//        driver.get("http://www.google.com");
//        driver.get("https://demowebshop.tricentis.com/");
//
//        driver.findElement(By.linkText("Gift Cards")).click();
//
//        String giftCardLink = driver.getCurrentUrl();
//
//        //3. Select products with a price higher than 99. The selection of the product should not be hardcoded, as the product price may change.
//        List<WebElement> priceElements = driver.findElements(By.xpath("//div[@class = 'product-grid']//div[@class='prices']//span[text() > 49]"));
//
//        for(int i = 0; i < priceElements.size(); i++) {
//
//            List<WebElement> priceElementsRefreshed = driver.findElements(By.xpath("//div[@class = 'product-grid']//div[@class='prices']//span[text() > 49]"));
//            WebElement specificAddToCartButton = priceElementsRefreshed.get(i).findElement(By.xpath("./../..//input[@value='Add to cart']"));
//            specificAddToCartButton.click();
//
//
//            //4. Fill in the fields 'Recipient's Name:', 'Your Name:' at your discretion.
//
//            try {
//                sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            //WebElement recipientName = driver.findElement(By.xpath("//div//input[@class='recipient-name']"));\
//            WebElement recipientName = driver.findElement(By.cssSelector("input.recipient-name"));
//            recipientName.click();
//            recipientName.sendKeys("Kunigas");
//
//            WebElement yourName = driver.findElement(By.xpath("//input[@class='sender-name']"));
//            yourName.click(); // Ensure focus
//            yourName.sendKeys("Aldona");
//
//            //5. Enter '5000' into the 'Qty' text field at your discretion.
//            WebElement qty = driver.findElement(By.xpath("//input[@class='qty-input']"));
//            qty.click();
//            qty.clear();
//            qty.sendKeys("5000");
//
//
//            //6. Click the 'Add to cart' button.
//            WebElement addToCart = driver.findElement(By.xpath("//input[@value='Add to cart']"));
//            addToCart.click();
//
//            try {
//                sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            //7. Click the 'Add to wish list' button.
//            WebElement addToWishList = driver.findElement(By.xpath("//input[@value='Add to wishlist']"));
//            addToWishList.click();
//
//            try {
//                sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            //cia grizta atgal i gift card puslapi, jei butu daugiau elementu, kuriu kaina virs 99
//            driver.get(giftCardLink);
//
//        }
//
//        //8. Click 'Jewelry' in the left menu.
//        driver.findElement(By.linkText("Jewelry")).click();
//
//        //9. Click the 'Create Your Own Jewelry' link.
//        driver.findElement(By.linkText("Create Your Own Jewelry")).click();
//
//        //10. Choose the values: 'Material' - 'Silver 1mm', 'Length in cm' - '80', 'Pendant' - 'Star'
//        WebElement material = driver.findElement(By.xpath("//select[starts-with(@name, 'product_attribute')]"));
//        material.click();
//        material.findElement(By.xpath("//option[normalize-space(text())='Silver (1 mm)']")).click();
//
//        WebElement length = driver.findElement(By.xpath("//input[starts-with(@name, 'product_attribute') and @type='text']"));
//        length.click();
//        length.clear();
//        length.sendKeys("80");
//
//        WebElement pendant = driver.findElement(By.xpath("//ul[@class='option-list']/li/label[normalize-space(text()) = 'Star']"));
//        pendant.click();
//
//        //11. Enter '26' into the 'Qty' text field.
//        WebElement qty = driver.findElement(By.xpath("//input[@class='qty-input']"));
//        qty.click();
//        qty.clear();
//        qty.sendKeys("26");
//
//        //12. Click the 'Add to cart' button.
//        WebElement addToCart = driver.findElement(By.xpath("//input[@value='Add to cart']"));
//        addToCart.click();
//
//        try {
//            sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        //13. Click the 'Add to wish list' button.
//        WebElement addToWishList = driver.findElement(By.xpath("//input[@value='Add to wishlist']"));
//        addToWishList.click();
//
//        try {
//            sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        //14. Click the 'Wishlist' link at the top of the page.
//        driver.findElement(By.linkText("Wishlist")).click();
//
//        //15. For both products, tick the 'Add to cart' checkbox.
//        List<WebElement> checkBoxes = driver.findElements(By.xpath("//td[@class='add-to-cart']/input"));
//        for(WebElement checkBox : checkBoxes) {
//            checkBox.click();
//        }
//
//        //16. Click the 'Add to cart' button.
//        driver.findElement(By.name("addtocartbutton")).click();
//
//        //17. On the Shopping cart page, confirm that the 'Sub-Total' value is '1002600.00'.
//        WebElement subTotal = driver.findElement(By.xpath("//span[@class='product-price order-total']"));
//        String subTotalValue = subTotal.getText();
//        //check if sub-total value is correct and then highlight it
//        if(subTotalValue.equals("1502600.00")) {
//            System.out.println("Sub-Total value is correct: " + subTotalValue);
//        } else {
//            System.out.println("Sub-Total value is incorrect: " + subTotalValue);
//        }
//
//
//
//        //driver.quit();
//
//    }
//}
////1. Open the website https://demowebshop.tricentis.com/
////2. Click 'Gift Cards' in the left menu.
////3. Select products with a price higher than 99. The selection of the product should not be hardcoded, as the product price may change.
////4. Fill in the fields 'Recipient's Name:', 'Your Name:' at your discretion.
////5. Enter '5000' into the 'Qty' text field at your discretion.
////6. Click the 'Add to cart' button.
////7. Click the 'Add to wish list' button.
////8. Click 'Jewelry' in the left menu.
////9. Click the 'Create Your Own Jewelry' link.
////10. Choose the values: 'Material' - 'Silver 1mm', 'Length in cm' - '80', 'Pendant' - 'Star'
////11. Enter '26' into the 'Qty' text field.
////12. Click the 'Add to cart' button.
////13. Click the 'Add to wish list' button.
////14. Click the 'Wishlist' link at the top of the page.
////15. For both products, tick the 'Add to cart' checkbox.
////16. Click the 'Add to cart' button.
////17. On the Shopping cart page, confirm that the 'Sub-Total' value is '1002600.00'.