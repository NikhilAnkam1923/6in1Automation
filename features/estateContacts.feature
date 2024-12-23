@6in1 @Smoke @Regression
Feature: 6in1 estate contacts Feature

  @Setup
  Scenario Outline: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "$6in1_url"
    When user login using "<user-email>" and "<password>"
    Then user verifies the Home page
    Examples:
      | user-email                           | password |
      | nikhilankam+14@benchmarkit.solutions | Watch@22 |

    Scenario:Verify, add estate contact appears on clicking add button
      When user navigates to the estate contacts tab

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser