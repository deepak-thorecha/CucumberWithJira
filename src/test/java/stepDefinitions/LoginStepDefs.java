package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginStepDefs extends BaseTest {

    @Given("I am on the homepage for {string}")
    public void iAmOnTheHomepageFor(String url) {
        getDriver().get(url);
    }

    @When("I enter valid credentials")
    public void iEnterValidCredentials() {
        new LoginPage()
                .setUserName("int@automation.com")
                .setPassword("Test1234").clickSubmitButton();
    }

    @Then("I am successfully logged in")
    public void amSuccessfullyLoggedIn() {
        assert new LoginPage().isTitleDisplayed() : "Expected title Not Displayed after successful Login";
    }

    @When("I enter invalid credentials")
    public void iEnterInvalidCredentials() {
        new LoginPage()
                .setUserName("int@automation.com")
                .setPassword("Test12345").clickSubmitButton();
    }
}
