package adpractice.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ThankyouPage {
	
	WebDriver driver;
	
	public ThankyouPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement successMessage;
	
	public String getSuccessMessage() {
		String message = successMessage.getText();
		return message;
	}
		
}
