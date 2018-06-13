import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class ApprovalFormTest {

  public static final String GECKO_DRIVER_PATH = "D:\\Dev\\sandbox\\IT485\\geckodriver.exe";
  WebDriver webDriver;

  @Before
  public void goToStudentForm(){

    System.setProperty("webdriver.gecko.driver", GECKO_DRIVER_PATH);
    webDriver = new FirefoxDriver();
    webDriver.manage().window().maximize();
    webDriver.get("http://msreedaran.greenrivertech.net/plaform/entries/1");
  }

  @Test
  public void authenticationChallenge() {
    WebElement usernameInput = null;
    try {
      usernameInput = webDriver.findElement(By.id("username"));
    } catch (NoSuchElementException exn) {
      System.err.println(exn.getMessage());
    } finally {
      assertNotNull(usernameInput);
    }
  }

  @Test
  public void sidVerification(){
    webDriver.findElement(By.id("username")).sendKeys("admin");
    WebElement passwordInput = webDriver.findElement(By.id("password"));
    passwordInput.sendKeys("nimdadrowssap");
    passwordInput.submit();
    (new WebDriverWait(webDriver, 10)).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver webDriver) {
        WebElement element = webDriver.findElement(By.id("student-id"));
        String expected = "123456789", actual = element.getAttribute("value");
        assertEquals(expected, actual);
        return expected.equals(actual);
      }
    });
  }

  @After
  public void quitBrowser(){
    webDriver.quit();
  }

}
