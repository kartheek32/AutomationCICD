package FrameWorkBuild.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import FrameWorkBuild.AbstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents {

	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By productsBy = By.cssSelector(".mb-3");
		By addToCart = By.cssSelector(".card-body button:last-of-type");
		By toaster = By.cssSelector("#toast-container");
		

	public List<WebElement> getProductList() {
		WaitForElementToBeAppear(productsBy);
		return products;

	}
	public WebElement getProductName(String productName) {
		
		WebElement prod = getProductList().stream()
	            .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
	            .findFirst()
	            .orElse(null);

	        if (prod == null) {
	            System.out.println("Product not found!");
	          driver.quit();
	            return prod;
	        }
	        return prod;
	}
	public void addToCart(String productName) throws InterruptedException {
		WebElement prod = getProductName(productName);
        prod.findElement(addToCart).click();
        WaitForElementToBeAppear(toaster);
        WaitForElementToBeDisappear(spinner);
	}

	
	
	
	
}
