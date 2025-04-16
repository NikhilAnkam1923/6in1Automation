@6in1 @Smoke @Regression
Feature: 6in1 probate form OC02 Feature

  @Setup
  Scenario Outline: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "$6in1_url"
    When user login using "<user-email>" and "<password>"
    Then user verifies the Home page
    Examples:
      | user-email                        | password  |
      | nikhilankam@benchmarkit.solutions | Bits@1234 |


  Scenario: Open Estate
    When user opens "Sara Watt" Estate
    And user saves entered Estate information for "OC02" form

  Scenario: Verify, correct County of the decedent, file no is fetched from the decedent info. tab.
    When user navigates to the probate forms tab
    And user click on the "OC 02" form
    Then user verifies by default page 1 should is opened and correct county name is fetched and displayed at the top of the form

  Scenario: Verify, trust under deed and trust under will fields are enabled based on the checkbox checked.
    When user checks trust under will checkbox
    Then user verifies trust under will field is enabled
      #Verify, Decedent name can be selected in either of the 2 Name of Trust input fields.
    Then user verifies entered name is displayed on the form against the "Trust under will" field and other field is empty
    When user checks trust under deed checkbox
    Then user verifies trust under deed field is enabled
    Then user verifies entered name is displayed on the form against the "Trust under deed" field and other field is empty

  Scenario: Verify, the name entered is visible on all the pages.
    Then user verifies entered name of decedent is displayed on all the OC02 pages

  Scenario: Verify, file no can be edited.
    When user navigates to page number: "1"
    Then user verified file number field is editable

  Scenario: Verify that the sidebar appears and allows attorney selection.
    When user click on Name of Counsel field
    Then user verify a sidebar appears and attorney can be selected
    And user verify selected attorney’s details are populated in the 'Name of Counsel' field

  Scenario: Verify, correct attorney details are fetched in the subsequent fields.
    Then user verify attorney details are auto fetched and correctly displayed
    
  Scenario: Verify form can be printed in pdf
    When user click on print form button
    Then verify form can be printed in pdf with name as 'OC02'
    And verify all the fields entered are correctly reflected in the 'OC02' pdf

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser