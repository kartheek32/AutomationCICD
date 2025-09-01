package FrameWorkBuild.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import FrameWorkBuild.pageObjects.CartPage;
import FrameWorkBuild.pageObjects.OrderPage;

public class AbstractComponents {

	WebDriver driver;
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = "[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;

	public void WaitForElementToBeAppear(By Findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Findby));
	}
	public void WaitForWebElementToBeAppear(WebElement element) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(element)); // ✅ Correct usage
	}

	
	public void WaitForElementToBeDisappear(WebElement ele) throws InterruptedException {
		Thread.sleep(2000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	public void WaitForElementToBeClickable(WebElement element) throws InterruptedException {
//		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public CartPage goToCartPage(){
		 cart.click();
		 CartPage CartPage = new CartPage(driver);
		 return CartPage;
	}
	public OrderPage goToOrderPage(){
		orderHeader.click();
		 OrderPage OrderPage = new OrderPage(driver);
		 return OrderPage;
	}
	
}
