@6in1 @Smoke @Regression
Feature: 6in1 probate form RW03 Feature

  @Setup
  Scenario Outline: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "$6in1_url"
    When user login using "<user-email>" and "<password>"
    Then user verifies the Home page
    Examples:
      | user-email                           | password |
      | nikhilankam+14@benchmarkit.solutions | Watch@22 |

Scenario: Verify county, estate and aka names are autopopulated on the form
  When user select estate and retrieve the entered county, estate and aka names
  Then user navigates to the probate forms tab
  And user click on the RW03
  Then user selects the aka checkbox
  And user verifies the county, estate and aka names are autopopulated on the form






  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser

