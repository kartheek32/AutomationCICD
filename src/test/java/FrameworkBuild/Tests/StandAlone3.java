package FrameworkBuild.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAlone3 {

    public static void main(String[] args) {

//New Comments
        String productName = "IPHONE 13 PRO";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
//        options.addArguments("--disable-blink-features=AutomationControlled");
//        options.setExperimentalOption("excludeSwitches", List.of("enable-automation", "enable-logging"));
//        options.setExperimentalOption("useAutomationExtension", false);

//         🚫 Disable Chrome password manager popup
//        options.setExperimentalOption("prefs", Map.of(
//            "credentials_enable_service", false,
//            "profile.password_manager_enabled", false
//        ));

        // ✅ Use guest mode to avoid saved credentials interference
        options.addArguments("--guest");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client/");

        driver.findElement(By.id("userEmail")).sendKeys("Dhoni@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Dhoni@123");
        driver.findElement(By.id("login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        WebElement prod = products.stream()
            .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
            .findFirst()
            .orElse(null);

        if (prod == null) {
            System.out.println("Product not found!");
            driver.quit();
            return;
        }

        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
      

        WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink*='cart']")));
        cartBtn.click();

        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

        //cartProducts.forEach(p -> System.out.println("Cart contains: " + p.getText()));

        boolean match = cartProducts.stream()
            .anyMatch(cartProduct -> cartProduct.getText().trim().equalsIgnoreCase(productName));

        Assert.assertTrue(match, "Product not found in cart!");
        driver.findElement(By.cssSelector(".totalRow button")).click();
        
        WebElement countryInput = driver.findElement(By.cssSelector("[placeholder='Select Country']"));
        countryInput.click();
        countryInput.sendKeys("Ind");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        List<WebElement> countries = driver.findElements(By.cssSelector("[class*='ta-item list-group-item']"));
        WebElement india = countries.stream()
        	    .filter(c -> c.getText().equals("India"))
        	    .findFirst()
        	    .orElseThrow(() -> new RuntimeException("Country not found"));
india.click();
driver.findElement(By.cssSelector(".action__submit ")).click();
String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
driver.close();
    }
}
