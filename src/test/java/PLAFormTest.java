import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class PLAFormTest {

  public static final String GECKO_DRIVER_PATH = "D:\\Dev\\sandbox\\IT485\\geckodriver.exe";
  WebDriver webDriver;

  @Before
  public void goToStudentForm(){

    System.setProperty("webdriver.gecko.driver", GECKO_DRIVER_PATH);
    webDriver = new FirefoxDriver();
    webDriver.manage().window().maximize();
    webDriver.get("http://msreedaran.greenrivertech.net/plaform");
  }

  @Test
  public void pageTitleVerification(){
    WebElement element = webDriver.findElement(By.tagName("h1"));
    assertEquals("Prior Learning Assessment Request Form", element.getAttribute("innerHTML"));
  }

  @Test
  public void validFormSubmissionTest() {
    WebElement studentid = webDriver.findElement(By.name("student-id"));
    studentid.sendKeys("123456789");

    WebElement firstNameInput = webDriver.findElement(By.name("first-name"));
    firstNameInput.sendKeys("TestFirstName");

    WebElement lastNameInput = webDriver.findElement(By.name("last-name"));
    lastNameInput.sendKeys("TestLastName");

    WebElement studentEmailInput = webDriver.findElement(By.name("student-email"));
    studentEmailInput.sendKeys("student@school.edu");

    WebElement internshipTitleInput = webDriver.findElement(By.name("internship-title"));
    internshipTitleInput.sendKeys("TestInternshipTitle");

    WebElement companyInput = webDriver.findElement(By.name("company"));
    companyInput.sendKeys("TestCompany");

    WebElement startDateInput = webDriver.findElement(By.name("start-date"));
    startDateInput.sendKeys("2000-01-01");

    WebElement endDateInput = webDriver.findElement(By.name("end-date"));
    endDateInput.sendKeys("2020-12-31");

    WebElement hoursWorkedInput = webDriver.findElement(By.name("hours-worked"));
    hoursWorkedInput.sendKeys("10008");

    WebElement supervisorNameInput = webDriver.findElement(By.name("supervisor-name"));
    supervisorNameInput.sendKeys("TestSupervisorName");

    WebElement supervisorTitleInput = webDriver.findElement(By.name("supervisor-title"));
    supervisorTitleInput.sendKeys("Boss");

    WebElement supervisorEmailInput = webDriver.findElement(By.name("supervisor-email"));
    supervisorEmailInput.sendKeys("supervisor@work.biz");

    WebElement supervisorPhoneInput = webDriver.findElement(By.name("supervisor-phone"));
    supervisorPhoneInput.sendKeys("1234567899");

    WebElement dutiesDescriptionInput = webDriver.findElement(By.name("duties-description"));
    dutiesDescriptionInput.sendKeys("TEST TEST TEST TEST");

    dutiesDescriptionInput.submit();

    final String expectedConfirmation = "Thank you for your submission!";

    (new WebDriverWait(webDriver, 10)).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver webDriver) {
        WebElement confirmationFromServer = webDriver.findElement(By.name("body"));
        String confirmationMessage = confirmationFromServer.getAttribute("innerHTML");
        assertTrue(confirmationMessage.contains(expectedConfirmation));
        return confirmationMessage.contains(expectedConfirmation);
      }

      });
  }

  @Test
  public void approvalFormTest() {

  }

  @After
  public void quitBrowser(){
    webDriver.quit();
  }

}
