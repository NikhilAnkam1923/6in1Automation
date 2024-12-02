@6in1 @Smoke @Regression
Feature: Verify 6in1 New Firm Creation

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "$6in1_url"

  @Smoke
  Scenario Outline: User verify login with valid credentials
    When user login using "<user-email>" and "<password>"
    Then user verify home page
    And user logged out from the application
    Examples:
      | user-email                        | password      |
      | nikhilankam@benchmarkit.solutions | Password@1223 |



