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
      Then user verifies that the left pane contains Name and Roles columns
      When user clicks on the Add Contact button
      Then user verifies the list of global contacts with Add button at the start
      And user verifies that Create New Individual and Create New Entity buttons are displayed
      And user click on the Close button
      

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser