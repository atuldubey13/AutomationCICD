package adpractice.pageobjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import adpractice.abstractcomponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".ta-results span")
	List<WebElement> countries;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By countriesBy = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		country.sendKeys(countryName);
		waitForElementToAppear(countriesBy);
		countries.stream().filter(country-> country.getText().equals("India")).collect(Collectors.toList()).get(0).click();
	}
	
	public ThankyouPage submitOrder() {
		submit.click();
		ThankyouPage thankyouPage = new ThankyouPage(driver);
		return thankyouPage;
	}
			

}
