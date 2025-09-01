package FrameWorkBuild.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameWorkBuild.AbstractComponents.AbstractComponents;

public class CheckOut extends AbstractComponents {
WebDriver driver;
	public CheckOut(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
   // WebElement countryInput = driver.findElement(By.cssSelector("[placeholder='Select Country']"));
    
	@FindBy(css="[placeholder='Select Country']")
	WebElement Country;
	
	@FindBy(css="[class*='ta-item list-group-item']")
	List<WebElement> CountryList;
	
	@FindBy(css=".action__submit ")
	WebElement placeorder;
	
	By countrys = By.cssSelector(".ta-results");
	
	
	
	public void SelectCountry(String countryText) {
		Country.click();
		Country.sendKeys(countryText);
		WaitForElementToBeAppear(countrys);
		  WebElement india = CountryList.stream()
	        	    .filter(c -> c.getText().equals("India"))
	        	    .findFirst()
	        	    .orElseThrow(() -> new RuntimeException("Country not found"));
	india.click();

	}
	public ConfirmationPage submitOrder() {
		placeorder.click();
		return new ConfirmationPage(driver);
	}

}
