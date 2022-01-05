package registrationTests;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class RegistrationTest {

  private WebDriver webDriver;
  private Logger logger = Logger.getLogger(getClass());

  private String email = "ndividualkim17+2@gmail.com"; // ctrl+r → ndividualkim17+0@gmail.com → email

  @Test
  public void testRegistrationValid(){
    //Setup browser
    File chrome = new File("drivers/chromedriver.exe");
    System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
    webDriver = new ChromeDriver();
    webDriver.manage().window().maximize();
    webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    try{
      webDriver.get("http://automationpractice.com/index.php");
      logger.info("Open url - http://automationpractice.com/index.php");
    } catch (Exception e){
      logger.error("Can't open url - http://automationpractice.com/index.php");
      Assert.fail("can't open url - http://automationpractice.com/index.php");
    }

    // Your personal info
    webDriver.findElement(By.xpath("//a[@class='login']")).click();
//    webDriver.findElement(By.className("login")).click();
//    webDriver.findElement(By.cssSelector(".login")).click();
    logger.info("Click login - //a[@class='login']");

    //Create an account
    webDriver.findElement(By.xpath("//input[@id='email_create']")).sendKeys(email);
    logger.info("Input email " + email);
    webDriver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]/span")).click();
    logger.info("Click Create an account button");

    //Your personal information
    webDriver.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys("Alex");
    logger.info("Input customer firstname - Alex");
    webDriver.findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys("Cortez");
    logger.info("Input customer lastname - Cortez");
    webDriver.findElement(By.xpath("//input[@id='email']")).clear();
    webDriver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
    logger.info("Input email " + email);
    webDriver.findElement(By.id("passwd")).sendKeys("qwerty123");
    logger.info("Input password - qwerty123");

    //Your address
    webDriver.findElement(By.xpath("//input[@id='customer_firstname']")).clear();
    webDriver.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys("Alex");
    logger.info("Input customer firstname - Alex");
    webDriver.findElement(By.xpath("//input[@id='customer_lastname']")).clear();
    webDriver.findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys("Cortez");
    logger.info("Input customer lastname - Cortez");
    webDriver.findElement(By.xpath("//div[@class=\"account_creation\"]/p[4]//input[@type=\"text\"]"))
            .sendKeys("1505 28th St S #17, Arlington, VA 77");
    webDriver.findElement(By.xpath("//input[@id='city']")).sendKeys("Arlington");
    Select state = new Select(webDriver.findElement(By.id("id_state")));
    state.selectByIndex(46);
    logger.info("Select by index - 46");
    webDriver.findElement(By.xpath("//input[@id='postcode']")).sendKeys("22206");
    logger.info("Select by postcode - 22206");
    webDriver.findElement(By.id("id_country")).isDisplayed();
    Select country = new Select(webDriver.findElement(By.id("id_country")));
    country.selectByVisibleText("United States");
    logger.info("Select by visible text - United States");
    webDriver.findElement(By.xpath("//input[@id='phone_mobile']")).sendKeys("0123456789");
    logger.info("Input phone - 0123456789");
    webDriver.findElement(By.xpath("//input[@id='alias']")).clear();
    webDriver.findElement(By.xpath("//input[@id='alias']")).sendKeys(email);
    logger.info("Input alias " + email);
    webDriver.findElement(By.xpath("//button[@name=\"submitAccount\"]")).click();
    logger.info("Click button register");

    // My account
    webDriver.findElement(By.xpath("//h1[contains(text(),'My account')]")).isDisplayed();
    logger.info("Check h1 is displayed - //h1[contains(text(),'My account')]");

    webDriver.quit();
  }
}
