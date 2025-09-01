package FrameworkBuild.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FrameWorkBuild.pageObjects.CartPage;
import FrameWorkBuild.pageObjects.CheckOut;
import FrameWorkBuild.pageObjects.ConfirmationPage;
import FrameWorkBuild.pageObjects.OrderPage;
import FrameWorkBuild.pageObjects.ProductCatalog;
import FrameworkBuild.TestComponents.BaseTest;

public class StandAlone extends BaseTest {
	String productName = "IPHONE 13 PRO";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalog ProductCatalog = LandingPage.loginApplication(input.get("Email"), input.get("Password"));

		List<WebElement> products = ProductCatalog.getProductList();
		ProductCatalog.addToCart(input.get("productName"));
		CartPage CartPage = ProductCatalog.goToCartPage();

		Boolean match = CartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match, "Product not found in cart!");
		CheckOut CheckOut = CartPage.goToCheckOut();
		CheckOut.SelectCountry("Ind");
		ConfirmationPage ConfirmationPage = CheckOut.submitOrder();
		String confirmMessage = ConfirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = { "SubmitOrder" })
	public void OrderHistoryTest() {

		ProductCatalog ProductCatalog = LandingPage.loginApplication("Dhoni@gmail.com", "Dhoni@123");
		OrderPage OrderPage = ProductCatalog.goToOrderPage();
		Assert.assertTrue(OrderPage.VerifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

//			HashMap<String,String> map = new HashMap<String,String>();
//			map.put("Email", "Dhoni@gmail.com");
//			map.put("Password", "Dhoni@123");
//			map.put("productName", "IPHONE 13 PRO");
//			
//			HashMap<String,String> map1 = new HashMap<String,String>();
//			map1.put("Email", "rahulshetty@gmail.com");
//			map1.put("Password", "Iamking@000");
//			map1.put("productName", "ZARA COAT 3");
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "//src//test//java//FrameworkBuild//data//PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
}
