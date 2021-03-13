package Pages;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GittiGidiyorProductsPage {
	
	WebDriver _webDriver;
	WebElement dynamicElement;

	public GittiGidiyorProductsPage(WebDriver webDriver) {
		_webDriver = webDriver;
	}

	By productsListElement = By.xpath("//*[@id=\'best-match-right\']/div[4]/div[2]/ul");
	By listItemElement = By.cssSelector("li[product-index]");
	By productPriceElement = By.cssSelector("p.fiyat.robotobold.price-txt");
	By btnAddToBasketElement = By.id("add-to-basket");

	By btnMyCartElement = By.cssSelector(".dIB");
	By btnGoToCartElement = By.xpath("//*[@id=\'header_wrapper\']/div[4]/div[3]/div/div/div/div[2]/div[4]/div[1]/a");

	By productsInCartElement = By.cssSelector(".product-item-box");
	By productPriceInCartElement = By.cssSelector(".data-salePrice");

	double priceInProductList;
	double priceInCart;

	public void verifyUrl() {
		Assertions.assertTrue(_webDriver.getCurrentUrl().contains("arama/?"));
	}

	public void selectProductArbitrarily() {
		WebElement productsList = _webDriver.findElement(productsListElement);
		List<WebElement> productListItems = productsList.findElements(listItemElement);

		Random randomElement = new Random();
		int index = randomElement.nextInt(productListItems.size() - 1);
		dynamicElement = productListItems.get(index);

		String price = dynamicElement.findElement(productPriceElement).getText();
		priceInProductList = convertStringToDouble(price);
		dynamicElement.click();

	}

	public void addProductToCart() {
		dynamicElement = _webDriver.findElement(btnAddToBasketElement);
		JavascriptExecutor jse = (JavascriptExecutor) _webDriver;

		jse.executeScript("arguments[0].scrollIntoView()", dynamicElement);
		dynamicElement.click();
	}

	public void goToCart() {

		_webDriver.findElement(btnMyCartElement).click();
	}

	public void compareProductPrice() {
		List<WebElement> products = _webDriver.findElements(productsInCartElement);
		priceInCart = Double.parseDouble(products.get(0).findElement(productPriceInCartElement).getAttribute("value"));
		Assertions.assertEquals(priceInCart, priceInProductList);
	}

	public double convertStringToDouble(String price) {
		String replacedStringfullStop = price.replace(".", "");
		String replacedStringComma = replacedStringfullStop.replace(",", ".");
		String replacedStringTL = replacedStringComma.replace("TL", "");
		String finalString = replacedStringTL.trim();
		return Double.parseDouble(finalString);
	}

}
