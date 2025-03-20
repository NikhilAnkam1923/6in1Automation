@6in1 @Smoke @Regression
Feature: 6in1 probate form RW06 Feature

  @Setup
  Scenario Outline: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "https://benchmark1.benchmarkits.in/"
    When user login using "<user-email>" and "<password>"
    Then user verifies the Home page
    Examples:
      | user-email                               | password  |
      | bhaveshkulkarni+13@benchmarkit.solutions | Bits@1234 |

  Scenario: Open Estate
    When user opens "William John" Estate
    And user saves entered Estate information for "RW06" form

  Scenario: Verify county, estate and aka names are auto-populated on the form
    When user navigates to the probate forms tab
    And user click on the "RW 06" form
    Then user selects the aka checkbox
    And user verifies the county, estate and aka names are auto-populated on the "RW06" form

  Scenario: Verify, the auto-populated fields are not editable.
    Then user verifies the auto-populated fields of "RW06" form are not editable

  Scenario: Verify, form is repeated based on the number of contacts selected.
    And user selects multiple Corporate Fiduciary contacts
    Then user verifies form is repeated based on the number of fiduciary contacts selected

  Scenario: Verify, corporate fiduciary selected is reflected in the corporate name field.
    Then user verifies selected contacts names are reflected in the corporate fiduciary name and signature field on each form

  Scenario: Verify, details of the selected contact's is displayed in the block under it.
      #Verify, contact details on each page.
    Then user verifies correct corporate fiduciary contact details are displayed on each form

  Scenario: Verify, on clicking signature of person, beneficiary contact can be selected.
    Then user verifies multiple beneficiary contacts can be selected

  Scenario: Verify, form is repeated based on the number of contacts selected.
    Then user verifies form is repeated based on the number of beneficiary contacts selected

  Scenario: Verify, contact details are correctly displayed on each page.
    Then user verifies correct beneficiary contact details are displayed correctly on each form

  Scenario: Verify, date and reason text box fields are not same for each field.
      #Verify, date and text can be entered.
    Then user verifies entered date and reason details on each form are not same

  Scenario: Verify form can be printed in pdf
    When user click on print form button
    Then verify form can be printed in pdf with name as 'Rw06'
    And verify all the fields entered are correctly reflected in the 'Rw06' pdf

  Scenario: Reset the RW06 form
    When user resets the "RW06" form

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser