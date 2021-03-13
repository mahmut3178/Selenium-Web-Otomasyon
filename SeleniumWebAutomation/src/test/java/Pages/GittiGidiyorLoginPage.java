package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GittiGidiyorLoginPage {

	WebDriver _webDriver;
	WebElement dynamicElement;

	public GittiGidiyorLoginPage(WebDriver webDriver) {
		_webDriver = webDriver;
	}
	
	By tbxUsername = By.cssSelector("[name='kullanici']");
	By tbxPassword = By.cssSelector("[name='sifre']");
	By btnLogin = By.id("gg-login-enter");
	

	public void keyInUsername(String username)
	{
		dynamicElement = _webDriver.findElement(tbxUsername);
		dynamicElement.sendKeys(username);
	}
	
	public void keyInPassword(String password)
	{
		dynamicElement = _webDriver.findElement(tbxPassword);
		dynamicElement.sendKeys(password);
	}
	
	public void clickOnLogin()
	{
		dynamicElement = _webDriver.findElement(btnLogin);
		dynamicElement.click();
	}	
}
