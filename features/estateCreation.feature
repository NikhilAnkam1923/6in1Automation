@6in1 @Smoke @Regression
Feature: 6in1 Global Contacts Feature

  @Setup
  Scenario Outline: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "$6in1_url"
    When user login using "<user-email>" and "<password>"
    Then user verifies the Home page
    Examples:
      | user-email                           | password |
      | nikhilankam+11@benchmarkit.solutions | Watch@22 |


  Scenario: user verify after filling decedent information clicking on next button other details are opened
    When user clicks on the Create button
    Then user fills the first name,last name and SSN details
    And user click on Proceed button
    Then user see the Create a new estate with the entered name button for new user
    And user click on Create a new estate with the entered name button for new user
    Then user fills decedent basic information for new user
    And user click on Next button
    Then verify Decedent details page is opened

  Scenario: Verify Life Details fields, validations, and behaviors
    #verify Last Residence field validation for invalid input
    When user enters invalid input in the Last Residence field
    Then the system displays the respective validation error messages
    And user enters valid input in the Last Residence field
    Then the system accepts the input without any error
    #verify, date picker opens for the date fields
    And user clicks on the date fields date picker should open for these fields
    #Verify, date of divorced decree field appears, only after selecting marital status as divorced
    And user selects Divorced in the Marital Status dropdown
    Then the Date Divorced Decree field should be displayed
    And user selects Marital Status from Divorced to any other
    Then the Date Divorced Decree field should be hidden
    #Verify, age at death is calculated correctly on adding date of birth and date of death
    And user enters valid Date of Birth and Date of Death
    Then the system calculate and displays correct Age at Death
#   And user enters an invalid Date of Death earlier than Date of Birth
#   Then the validation error message should be displayed

  Scenario Outline: verify user authorization for "View Only" user
    When user logged out from the application
    And user go to application "https://delltab.benchmarkits.in"
    When user login using "<user-email>" and "<password>"
    Then user verifies authorization for "View Only"
    Examples:
      | user-email                           | password |
      | nikhilankam+13@benchmarkit.solutions | Watch@22 |

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser
