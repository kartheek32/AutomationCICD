package FrameworkBuild.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import FrameworkBuild.TestComponents.BaseTest;
import FrameworkBuild.TestComponents.Retry; // ✅ Correct import

import FrameWorkBuild.pageObjects.CartPage;
import FrameWorkBuild.pageObjects.ProductCatalog;

public class ErrorValidations extends BaseTest {

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidations() throws IOException {
        LandingPage.loginApplication("Dhoni@gmail.com", "Dhoni@1244443");
        Assert.assertEquals("Incorrect email or password.", LandingPage.getErrorMessage());
    }

    @Test
    public void ProductErrorValidations() throws IOException, InterruptedException {
        String productName = "IPHONE 13 PRO";

        ProductCatalog ProductCatalog = LandingPage.loginApplication("rahulshetty@gmail.com", "Iamking@000");

        List<WebElement> products = ProductCatalog.getProductList();
        ProductCatalog.addToCart(productName);
        CartPage CartPage = ProductCatalog.goToCartPage();

        Boolean match = CartPage.VerifyProductDisplay("IPHONE 133 PRO");
        Assert.assertFalse(match);
    }
}
