@LoginFeature
Feature: Login to application

  @ASSET-5
  Scenario: Login using default credentials
    Given I am on the homepage for "https://ontrack3-int.hilti.com/"
    When I enter valid credentials
    Then I am successfully logged in

  @ASSET-6
  Scenario: Login using default credentials
    Given I am on the homepage for "https://ontrack3-int.hilti.com/"
    When I enter invalid credentials
    Then I am successfully logged in
