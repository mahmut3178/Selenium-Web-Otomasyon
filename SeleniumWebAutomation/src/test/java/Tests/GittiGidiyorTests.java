package Tests;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Pages.GittiGidiyorCartPage;
import Pages.GittiGidiyorHomePage;
import Pages.GittiGidiyorLoginPage;
import Pages.GittiGidiyorProductsPage;
import io.github.bonigarcia.wdm.WebDriverManager;

@TestMethodOrder(OrderAnnotation.class)
public class GittiGidiyorTests {

	static WebDriver _webDriver;
	static GittiGidiyorHomePage objGittiGidiyorHomePage;
	static GittiGidiyorLoginPage objGittiGidiyorLoginPage;
	static GittiGidiyorProductsPage objGittiGidiyorProductsPage;
	static GittiGidiyorCartPage objGittiGidiyorCartPage;
    static Logger logger;   
   
      
	@BeforeAll
	 static void setUp() {
		
		logger = LogManager.getLogger(GittiGidiyorTests.class);
		
		WebDriverManager.chromedriver().setup();
		_webDriver = new ChromeDriver();
		logger.info("Site açılıyor..");
		//_webDriver.manage().window().maximize();
		_webDriver.get("https://www.gittigidiyor.com");
       
		objGittiGidiyorHomePage = new GittiGidiyorHomePage(_webDriver);
		objGittiGidiyorLoginPage = new GittiGidiyorLoginPage(_webDriver);	
		objGittiGidiyorProductsPage = new GittiGidiyorProductsPage(_webDriver);
		objGittiGidiyorCartPage = new GittiGidiyorCartPage(_webDriver);

	}

	
	@Order(1)
	@Test
	 void navigateToHomePageVerifyUrlAndThenToLoginPage() throws Exception {
		objGittiGidiyorHomePage.verifyPageUrl();
		objGittiGidiyorHomePage.hoverOnUserMenu();
		Thread.sleep(1000);
		objGittiGidiyorHomePage.clickOnSignIn();
		logger.info("navigateToHomePageVerifyUrlAndThenToLoginPage Testi tamamlandı");
	}

	@Order(2)
	@Test
	 void enterCredentialsAndLogin() throws Exception {
		objGittiGidiyorLoginPage.keyInUsername("malezlers@gmail.com");
		objGittiGidiyorLoginPage.keyInPassword("mahmut123");
		Thread.sleep(1000);
		objGittiGidiyorLoginPage.clickOnLogin();	

		logger.info("enterCredentialsAndLogin Testi tamamlandı");
	}

	@Order(3)
	@Test
	public void checkIfLoginSuccessfulThenSearchForProduct() throws Exception {
		objGittiGidiyorHomePage.checkIfUserLoggedIn();
		objGittiGidiyorHomePage.enterProductName("bilgisayar");
		objGittiGidiyorHomePage.clickOnSearchButton();
		logger.info("checkIfLoginSuccessfulThenSearchForProduct Testi tamamlandı");
	}
	
	@Order(4)
	@Test
	 void checkIfSearchCompleteAndSelectProduct() throws Exception
	{
		objGittiGidiyorProductsPage.verifyUrl();
		objGittiGidiyorProductsPage.selectProductArbitrarily();
		Thread.sleep(1000);
		objGittiGidiyorProductsPage.addProductToCart();
		Thread.sleep(3000);		
		logger.info("checkIfSearchCompleteAndSelectProduct Testi tamamlandı");
	}
	
	@Order(5)
	@Test
	 void goToCartyAndCheckIfProductPricesAreSame() throws Exception
	{
		objGittiGidiyorProductsPage.goToCart();
		objGittiGidiyorProductsPage.compareProductPrice();		
		logger.info("goToCartyAndCheckIfProductPricesAreSame Testi tamamlandı");
	}
	
	@Order(6)
	@Test
	 void increaseProductCountThenDeleteAndValidateCart() throws Exception
	{
		Thread.sleep(3000);
		objGittiGidiyorCartPage.selectAndValidateProductQuantity(2);		
		Thread.sleep(1000);
		objGittiGidiyorCartPage.removeItemFromCart();
		Thread.sleep(2000);
		objGittiGidiyorCartPage.validateCartIsEmpty();
		logger.info("increaseProductCountThenDeleteAndValidateCart Testi tamamlandı");
		Thread.sleep(2000);
	}
	
	@AfterAll
	static void tear()
	{
		_webDriver.close();
	}
	
	
	
	
	

}
