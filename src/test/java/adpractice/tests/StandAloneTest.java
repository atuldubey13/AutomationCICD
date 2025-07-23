package adpractice.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) {
		
		String productName = "ZARA COAT 3";
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//login
		driver.findElement(By.id("userEmail")).sendKeys("atul@mail.com");
		driver.findElement(By.id("userPassword")).sendKeys("User@1234");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait until products load
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		//getting list of products
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		//filtering the required product
		WebElement prod = products.stream().filter(product-> product.findElement(By.cssSelector("h5 b")).
				getText().equals(productName)).findFirst().orElse(null);
		//click on Add to Cart
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		//wait until loader disappears
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		//wait until added message appears
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		//click on Cart button
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		//getting list of available items in cart
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		//checking added item in cart
		Boolean match = cartProducts.stream().anyMatch(product-> product.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		//click on Checkout button
		driver.findElement(By.cssSelector(".subtotal button")).click();
		//enter country name
		driver.findElement(By.cssSelector(".user__address input")).sendKeys("India");
		//wait until drop down loading
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		//getting list of countries from drop down
		List<WebElement> countries = driver.findElements(By.cssSelector(".ta-results span"));
		//filtering the country name and click
		countries.stream().filter(country-> country.getText().equals("India")).collect(Collectors.toList()).get(0).click();
		//click on Proceed button
		driver.findElement(By.cssSelector(".action__submit")).click();
		//getting success message and check with expected 
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
		driver.close();
	
	}

}
