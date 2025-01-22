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

  Scenario: User Creates an Estate
#    When Temperary
    When user clicks on Create button
    Then user fills first name,last name and SSN details
    And user clicks on Proceed button
    And user click on Create a new estate with the entered name button
    Then user fills decedent basic information
    And user clicks on Next button
    When user fills Last Address/Domicile details
    When user fills Life Details
    When user fills the Place of Death details
    And user click on Estate tab
    And user fills the Estate details

  Scenario: Verify county, estate and aka names are auto-populated on the form
    When user navigates to the probate forms tab
    And user click on the "RW 03" form
    Then user selects the aka checkbox
    And user verifies the county, estate and aka names are auto-populated on the form
     #Verify, the auto-populated fields are not editable.
    Then user verifies the auto-populated fields are not editable
     #Verify, witness's name is not auto populated and the fields are empty.
    Then user verifies witness's name is not auto populated and the fields are empty

#  Scenario: Verify form can be printed in pdf
#    When user click on print form button
#    Then verify form can be printed in pdf with name as 'Rw01'
#    And verify all the fields entered are correctly reflected in the pdf

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser

