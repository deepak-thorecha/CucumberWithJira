package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import stepDefinitions.BaseTest;

import java.time.Duration;

public class LoginPage extends BaseTest {

    WebDriver driver;
    public LoginPage() {
        this.driver = getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "username")
    private WebElement userNameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "btnSubmit")
    private WebElement submitBtn;

    @FindBy(css = "main.multiple-acc-content-wrapper h1.responsive-title")
    private WebElement titleContent;

    public LoginPage setUserName(String uname) {
        userNameField.sendKeys(uname);
        return this;
    }

    public LoginPage setPassword(String pswd) {
        passwordField.sendKeys(pswd);
        return this;
    }

    public void clickSubmitButton() {
        submitBtn.click();
    }

    public boolean isTitleDisplayed() {
        return getWaitInSeconds(10).pollingEvery(Duration.ofSeconds(1))
                .until(driver -> ExpectedConditions.visibilityOf(titleContent).apply(driver).isDisplayed());
    }

}
