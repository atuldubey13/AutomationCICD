package adpractice.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import adpractice.pageobjects.CartPage;
import adpractice.pageobjects.CheckoutPage;
import adpractice.pageobjects.ProductCatalogue;
import adpractice.pageobjects.ThankyouPage;
import adpractice.testcomponents.BaseTest;
import adpractice.testcomponents.Retry;

public class ErrorValidationsTest extends BaseTest{
	
	@Test(groups= {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void LoginErrorValidation() {

		landingPage.loginApplication("atul@mail.com", "User1234");
		String errorMessage = landingPage.getErrorMessage();
		AssertJUnit.assertEquals("Incorrect email or password.", errorMessage);
		
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException{
		
		String productName = "ZARA COAT 3";
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("atul@mail.com", "User@1234");
		
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCart();
		
		Boolean match = cartPage.checkItemInCart("ZARA COAT 33");
		Assert.assertFalse(match);
	
	}

}
