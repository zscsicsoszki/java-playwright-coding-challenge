@ui-automation
Feature: Login feature

  Background:
    Given user navigates to the login page
    Then the login page is loaded

  # Expected to fail as user does not exist
  Scenario: User successfully signs in
    When user enters the email "test@aldi.com"
    And user enters the password "test123"
    Then the login button is enabled
    When user clicks on the login button
    Then the user is logged in

  Scenario Outline: Login button is disabled when only the <fieldName> is filled
    When user enters the <fieldName> "<fieldValue>"
    Then the login button is disabled

    Examples:
      | fieldName | fieldValue    |
      | email     | test@aldi.com |
      | password  | test123       |

  Scenario: Login fails with invalid password usage