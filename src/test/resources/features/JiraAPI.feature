@JiraIssueType
Feature: JIRA Issue Type Check

  @ASSET-7
  Scenario: Check type for JIRA Ticket Scenario_PASS
    Given I setup GET API to fetch details for "ASSET-7"
    When I make call to GET API
    Then I validate type is "Test"

  @ASSET-8
  Scenario: Check type for JIRA Ticket Scenario_FAIL
    Given I setup GET API to fetch details for "ASSET-8"
    When I make call to GET API
    Then I validate type is "Task"
