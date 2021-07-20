package jiraApi;

import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraException;
import stepDefinitions.APIBaseTest;

import java.io.IOException;

public class JIRAIssuesApi extends APIBaseTest {

    public static Long getJiraIdForIssueName(String issueName) throws IOException, JiraException {
        Issue issue = jira.getIssue(issueName);
        return Long.parseLong(issue.getId());
    }

    public static Issue getIssueDetails(String issueName) throws JiraException {
        return jira.getIssue(issueName);
    }

}
