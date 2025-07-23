package adpractice.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import adpractice.abstractcomponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ngx-spinner-overlay.ng-tns-c31-31.ng-trigger.ng-trigger-fadeIn.ng-star-inserted.ng-animating")
	WebElement spinner;
	//driver.findElement(By.cssSelector(".ngx-spinner-overlay.ng-tns-c31-31.ng-trigger.ng-trigger-fadeIn.ng-star-inserted.ng-animating"))
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.id("toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;		
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(product-> product.findElement(By.cssSelector("h5 b")).
				getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		
	}	

}
