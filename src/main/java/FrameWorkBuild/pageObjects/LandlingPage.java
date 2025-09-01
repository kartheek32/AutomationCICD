package FrameWorkBuild.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameWorkBuild.AbstractComponents.AbstractComponents;

public class LandlingPage extends AbstractComponents {

	WebDriver driver;

	public LandlingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement pass;

	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	public ProductCatalog loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		pass.sendKeys(password);
		
		try {
			WaitForElementToBeClickable(submit); // wait first
			submit.click();                      // then click
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ProductCatalog productCatalog = new ProductCatalog(driver); 
		return productCatalog;
	}
	public String getErrorMessage() {
		WaitForWebElementToBeAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo() {
        driver.get("https://rahulshettyacademy.com/client/");

	}
}
