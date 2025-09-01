package FrameworkBuild.stepDefinitions;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import FrameWorkBuild.pageObjects.CartPage;
import FrameWorkBuild.pageObjects.CheckOut;
import FrameWorkBuild.pageObjects.ConfirmationPage;
import FrameWorkBuild.pageObjects.LandlingPage;
import FrameWorkBuild.pageObjects.ProductCatalog;
import FrameworkBuild.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefinitionImpl extends BaseTest {
	
	public LandlingPage LandingPage;
	public ProductCatalog ProductCatalog;
	public ConfirmationPage ConfirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void ProductCatalog() throws IOException {
		LandingPage =  LaunchApplication();
		
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_with_username_and_password(String username, String password) {
		ProductCatalog= LandingPage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = ProductCatalog.getProductList();
		ProductCatalog.addToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) {
		CartPage CartPage = ProductCatalog.goToCartPage();

		Boolean match = CartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match, "Product not found in cart!");
		CheckOut CheckOut = CartPage.goToCheckOut();
		CheckOut.SelectCountry("Ind");
		ConfirmationPage = CheckOut.submitOrder();
		
	}
	
	@Then("{string} message displayed on ConfirmationPage")
	public void message_displayed_on_confitmationPage(String string) {
		String confirmMessage = ConfirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
		}
	
	@Then("{string} message is displayed")
	public void message_displayed(String string1) {
		  Assert.assertEquals(string1, LandingPage.getErrorMessage());
		driver.close();
		}
	

}
