package adpractice.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import adpractice.pageobjects.CartPage;
import adpractice.pageobjects.CheckoutPage;
import adpractice.pageobjects.OrdersPage;
import adpractice.pageobjects.ProductCatalogue;
import adpractice.pageobjects.ThankyouPage;
import adpractice.testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest{
	
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException{
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCart();
		
		Boolean match = cartPage.checkItemInCart(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		
		checkoutPage.selectCountry("india");
		ThankyouPage thankyouPage = checkoutPage.submitOrder();
	
		String message = thankyouPage.getSuccessMessage();
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
	
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest() throws IOException{
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("atul@mail.com", "User@1234");
		OrdersPage ordersPage = productCatalogue.goToOrders();
		Boolean match = ordersPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//adpractice//data//PurchaseOrder.json");
		return new Object[][]  {{data.get(0)},{data.get(1)}};
	
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		
//		return new Object[][]  {{"atul@mail.com","User@1234","ZARA COAT 3"},{"atul@mail.com","User@1234","ADIDA ORIGINAL"}};
//	
//	}
	
//	HashMap<String, String> map = new HashMap<String,String>();
//	map.put("email", "atul@mail.com");
//	map.put("password", "User@1234");
//	map.put("product", "ZARA COAT 3");
//	
//	HashMap<String, String> map1 = new HashMap<String,String>();
//	map1.put("email", "atul@mail.com");
//	map1.put("password", "User@1234");
//	map1.put("product", "ADIDAS ORIGINAL");
}
