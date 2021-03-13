package Pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GittiGidiyorHomePage {

	WebDriver _webDriver;
	WebElement dynamicElement;

	public GittiGidiyorHomePage(WebDriver webDriver) {
		_webDriver = webDriver;
	}

	By userMenu = By.cssSelector("[data-cy='header-user-menu']");
	By buttonSignIn = By.cssSelector("[data-cy='header-login-button']");
	By myAccount = By.cssSelector("[title='Hesabım']");
	By tbxSearch = By.name("k");
	By btnSearch = By.cssSelector("[data-cy='search-find-button']");

	public void verifyPageUrl() {
		Assertions.assertEquals("https://www.gittigidiyor.com/", _webDriver.getCurrentUrl());
	}

	public void hoverOnUserMenu() {
		Actions action = new Actions(_webDriver);
		dynamicElement = _webDriver.findElement(userMenu);
		action.moveToElement(dynamicElement).perform();
	}

	public void clickOnSignIn() {
		dynamicElement = _webDriver.findElement(buttonSignIn);
		dynamicElement.click();
	}

	public void checkIfUserLoggedIn() {
		dynamicElement = _webDriver.findElement(myAccount);
		Assertions.assertTrue(dynamicElement.isDisplayed());
	}

	public void enterProductName(String productName) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) _webDriver;
		WebElement we = _webDriver.findElement(tbxSearch);
		jse.executeScript("arguments[0].value= arguments[1];", we,productName);

	}

	public void clickOnSearchButton() {
		JavascriptExecutor jse = (JavascriptExecutor) _webDriver;
		WebElement we = _webDriver.findElement(btnSearch);
		jse.executeScript("arguments[0].click();", we);
	}

}
