package Pages;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GittiGidiyorCartPage {

	WebDriver _webDriver;

	public GittiGidiyorCartPage(WebDriver webDriver) {
		_webDriver = webDriver;
	}

	List<WebElement> products;
	WebElement dynamicElement;
	By productsInCartElement = By.cssSelector(".product-item-box");
	By productQuantitySelectElement = By.cssSelector(".gg-input-select select");
	By btnDeleteItem = By.cssSelector("[title='Sil'] i");

	public void selectAndValidateProductQuantity(int value) {
		products = _webDriver.findElements(productsInCartElement);
		products.get(0).findElement(By.xpath("//select/option[@value='" + value + "']")).click();

		String quantity = products.get(0).findElement(productQuantitySelectElement).getAttribute("value");

		ValidateProductQuantity(value, Integer.parseInt(quantity));
	}

	public void ValidateProductQuantity(int quantityExpected, int quantity)
	{
		Assertions.assertEquals(quantityExpected, quantity);
	}	
	
	public void removeItemFromCart() {
		dynamicElement = products.get(0).findElement(btnDeleteItem);
		dynamicElement.click();
	}
	
	public void validateCartIsEmpty()
	{
		int itemsInCart = _webDriver.findElements(productsInCartElement).size();
		Assertions.assertEquals(0, itemsInCart);
	}
	

}
