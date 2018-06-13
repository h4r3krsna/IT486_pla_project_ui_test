import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class PLAFormTestNoJS {

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

  private void fillFormWithValidDefaults(WebDriver webDriver) {
    WebElement studentid = webDriver.findElement(By.name("student-id"));
    studentid.sendKeys("123456789");

    WebElement firstNameInput = webDriver.findElement(By.name("first-name"));
    firstNameInput.sendKeys("TestFirstName");

    WebElement lastNameInput = webDriver.findElement(By.name("last-name"));
    lastNameInput.sendKeys("TestLastName");

    WebElement studentEmailInput = webDriver.findElement(By.name("student-email"));
    studentEmailInput.sendKeys("msreedaran@mail.greenriver.edu");//"student@school.edu");

    WebElement studentPhoneInput = webDriver.findElement(By.name("student-phone"));
    studentPhoneInput.sendKeys("9987654321");

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
  }

  @Test
  public void validFormSubmissionTest() {
    fillFormWithValidDefaults(webDriver);

    webDriver.findElement(By.name("reflection4")).submit();

    final String expectedConfirmation = "Thank you for your submission!";

    (new WebDriverWait(webDriver, 10)).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver webDriver) {
        boolean submissionConfirmed = false;
        try {
          String confirmationText = webDriver.findElement(By.id("confirmation")).getText();
          submissionConfirmed = confirmationText.contains(expectedConfirmation);
        } catch (NoSuchElementException exn) {
          System.err.println(exn.getMessage());
        } finally {
          assertTrue(submissionConfirmed);
          return submissionConfirmed;
        }
      }
    });
  }

  @Test
  public void blankFormSubmissionTest() {
    webDriver.findElement(By.name("student-id")).submit();
    List<WebElement> validationErrors = null;
    try { // TODO: replace with WebDriver wait
      Thread.sleep(10000);
      validationErrors = webDriver.findElements(By.className("validationError"));
    } catch (InterruptedException exn) {
      System.err.println(exn.getMessage() + "\nBlank Form Submission Test thread interrupted!");
    } catch (NoSuchElementException exn) {
      System.err.println("No elements found with the class name \"validationError\"");
    } finally {
      assertNotNull(validationErrors);
    }
  }

  @Test
  public void startDateAfterEndDateTest() {
    fillFormWithValidDefaults(webDriver);

    WebElement startDateInput = webDriver.findElement(By.name("start-date"));
    startDateInput.clear();
    startDateInput.sendKeys("2018-01-01");

    WebElement endDateInput = webDriver.findElement(By.name("end-date"));
    endDateInput.clear();
    endDateInput.sendKeys("2017-12-31");

    webDriver.findElement(By.name("reflection4")).submit();

    final String expectedError = "(must be on/before End Date)";
    boolean foundExpectedError = false;

    try { // TODO: replace with WebDriver wait
      Thread.sleep(10000);
      List<WebElement> validationErrors = webDriver.findElements(By.className("validationError"));
      for (WebElement validationError : validationErrors) {
        foundExpectedError = foundExpectedError || validationError.getText().contains(expectedError);
      }
    } catch (InterruptedException exn) {
      System.err.println(exn.getMessage() + " \nInvalid Date Test thread interrupted");
    } catch (NoSuchElementException exn) {
      System.err.println("No elements found with the class name \"validationError\"");
    } finally {
      assertTrue(foundExpectedError);
    }
  }

  @After
  public void quitBrowser(){
    webDriver.quit();
  }

}
