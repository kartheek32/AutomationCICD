package FrameWorkBuild.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameWorkBuild.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{
WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	 @FindBy(css="tr td:nth-child(3)")
	 List<WebElement> productNames;
	 
	 public boolean VerifyOrderDisplay(String productName) {
		 
		 boolean match = productNames.stream()
		            .anyMatch(cartProduct -> cartProduct.getText().trim().equalsIgnoreCase(productName));
		 return match;
	 }
	 

	
}
