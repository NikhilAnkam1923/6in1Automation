@6in1 @Smoke @Regression
Feature: 6in1 probate form RWxx Feature

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "$6in1_url"
    When user login using "nikhilankam@benchmarkit.solutions" and "Bits@1234"
    Then user verifies the Home page

  Scenario: Open Estate
    When user opens "John, William Arik Jr." Estate
    And user saves entered Estate information for "RWxx" form

  Scenario: Verify that the county, estate name, and "Also Known As" (AKA) values are auto-populated from the selected estate.
    When user navigates to the probate forms tab
    And user click on the "UWA" form
    Then user selects the aka checkbox
    Then user verifies the county, estate and aka names are auto-populated on the "RWxx" form

  Scenario: Verify, text can be entered in all the text areas.
    Then user verifies text can be entered in all the text areas and is auto saved

  Scenario: Verify, the name entered in 1st text area is reflected in the signature.
    Then user verifies name entered in first text field is reflected in signature field

  Scenario: Verify that changes in the witness name field are reflected under the signature line and vice-versa.
    Then user verifies name updated in signature field is reflected in the reviewer name Field

  Scenario: Verify form can be printed in pdf
    When user click on print form button
    Then verify form can be printed in pdf with name as 'Rwxx'
    And verify all the fields entered are correctly reflected in the 'Rwxx' pdf

  Scenario: Reset the RWxx form
    When user resets the "RWxx" form

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser

