package FrameWorkBuild.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameWorkBuild.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	 @FindBy(css=".cartSection h3")
	 List<WebElement> cartList;
	 
	 @FindBy(css=".totalRow button")
	 WebElement checkoutEle;
	 
	 public boolean VerifyProductDisplay(String productName) {
		 
		 boolean match = cartList.stream()
		            .anyMatch(cartProduct -> cartProduct.getText().trim().equalsIgnoreCase(productName));
		 return match;
	 }
	 
	 public CheckOut goToCheckOut() {
		 checkoutEle.click(); 
		 CheckOut CheckOut = new CheckOut(driver);
		 return CheckOut;
	 }
	
}
