package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraException;

@Slf4j
public class JiraAPIStepDefs extends APIBaseTest {

    private Issue issue;
    private String issueKey;

    @Given("I setup GET API to fetch details for {string}")
    public void iSetupGETAPIToFetchDetailsFor(String issue) {
        issueKey = issue;
        log.debug("Fetching details for IssueKey = " + issue);
    }

    @When("I make call to GET API")
    public void iMakeCallToGETAPI() throws JiraException {
        issue = jira.getIssue(issueKey);
    }

    @Then("I validate type is {string}")
    public void iValidateTypeIs(String type) {
        assert issue.getIssueType().getName().equalsIgnoreCase(type)
                : "Issue Type did not match!";
    }
}
