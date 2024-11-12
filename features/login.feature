@6in1 @Smoke @Regression
Feature: 6in1 Login Feature

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"

  @Smoke
  Scenario Outline: User verify login with valid credentials
    Given user go to application "$6in1_url"
    When user login using "<user-email>" and "<password>"
    Then user verify home page
    And user logged out from the application
    Examples:
      | user-email          | password  |
      | admin@malinator.com | Bits@123  |

  @Setup
  Scenario: SETUP: Close Browser
    Then User close browser