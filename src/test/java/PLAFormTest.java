import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class PLAFormTest {

  public static final String GECKO_DRIVER_PATH = "D:\\Dev\\sandbox\\IT485\\geckodriver.exe";
  WebDriver webDriver;

  @Before
  public void goToStudentForm() {

    System.setProperty("webdriver.gecko.driver", GECKO_DRIVER_PATH);

    FirefoxOptions options = new FirefoxOptions();
    options.addPreference("javascript.enabled", false);

    webDriver = new FirefoxDriver(options);
    webDriver.manage().window().maximize();
    webDriver.get("http://msreedaran.greenrivertech.net/plaform");
  }

  @Test
  public void pageTitleVerification() {
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

    WebElement reflection0Input = webDriver.findElement(By.name("reflection0"));
    reflection0Input.sendKeys("reflection0 TEST TEST TEST TEST");

    WebElement reflection1Input = webDriver.findElement(By.name("reflection1"));
    reflection1Input.sendKeys("reflection1 TEST TEST TEST TEST");

    WebElement reflection2Input = webDriver.findElement(By.name("reflection2"));
    reflection2Input.sendKeys("reflection2 TEST TEST TEST TEST");

    WebElement reflection3Input = webDriver.findElement(By.name("reflection3"));
    reflection3Input.sendKeys("reflection3 TEST TEST TEST TEST");

    WebElement reflection4Input = webDriver.findElement(By.name("reflection4"));
    reflection4Input.sendKeys("reflection4 TEST TEST TEST TEST");

    dutiesDescriptionInput.submit();

    final String expectedConfirmation = "Thank you for your submission!";

    (new WebDriverWait(webDriver, 10)).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver webDriver) {
        String entryViewLinkText = webDriver.findElement(By.tagName("a")).getText();
        assertEquals("Click Here", entryViewLinkText);
        return entryViewLinkText.equals("Click Here");
      }

    });
  }

  @Test
  public void blankFormSubmissionTest() {
    webDriver.findElement(By.name("student-id")).submit();
    try { // TODO: replace with WebDriver wait
      Thread.sleep(10000);

      List<WebElement> validationErrors = webDriver.findElements(By.className("validationError"));
      assertTrue(validationErrors.size() > 0);
    } catch (InterruptedException exn) {
      System.err.println(exn.getMessage() + "\nBlank Form Submission Test thread interrupted!");
    }
  }

  @Test
  public void startDateAfterEndDateTest() {
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
    startDateInput.sendKeys("2018-01-01");

    WebElement endDateInput = webDriver.findElement(By.name("end-date"));
    endDateInput.sendKeys("2017-12-31");

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

    WebElement reflection0Input = webDriver.findElement(By.name("reflection0"));
    reflection0Input.sendKeys("reflection0 TEST TEST TEST TEST");

    WebElement reflection1Input = webDriver.findElement(By.name("reflection1"));
    reflection1Input.sendKeys("reflection1 TEST TEST TEST TEST");

    WebElement reflection2Input = webDriver.findElement(By.name("reflection2"));
    reflection2Input.sendKeys("reflection2 TEST TEST TEST TEST");

    WebElement reflection3Input = webDriver.findElement(By.name("reflection3"));
    reflection3Input.sendKeys("reflection3 TEST TEST TEST TEST");

    WebElement reflection4Input = webDriver.findElement(By.name("reflection4"));
    reflection4Input.sendKeys("reflection4 TEST TEST TEST TEST");

    dutiesDescriptionInput.submit();

    final String expectedConfirmation = "Thank you for your submission!";

    try {
      Thread.sleep(10000);
    } catch (InterruptedException exn) {
      String validationErrorMessage = webDriver.findElements(By.className("validationError")).get(0).getText();
      String expected = "* (must be on/before End Date)";
      assertEquals(expected, validationErrorMessage);

      System.err.println(exn.getMessage() + " \nInvalid Date Test thread interrupted");
    }
  }

  @Test
  public void approvalFormTest() {

  }

  @After
  public void quitBrowser(){
    webDriver.quit();
  }

}
