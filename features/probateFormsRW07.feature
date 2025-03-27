@6in1 @Smoke @Regression
Feature: 6in1 probate form RW07 Feature

  @Setup
  Scenario : SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "$6in1_url"
    When user login using "nikhilankam@benchmarkit.solutions" and "Bits@1234"
    Then user verifies the Home page

  Scenario: Open Estate
    When user opens "William John" Estate
    And user saves entered Estate information for "RW07" form

  Scenario: Verify, county, estate and file number aka names are auto populated on the form.
    When user navigates to the probate forms tab
    And user click on the "RW 07" form
    Then user selects the aka checkbox
    Then user verifies the county, estate, file number and aka names are auto-populated on the "RW07" form

  Scenario: Verify, the auto populated fields are not editable.
    Then user verifies the auto-populated fields of "RW07" form are not editable

  Scenario: Verify, on checking use 4 digit checkbox, changes in file number
    When user check the Use 4 digit year checkbox
    Then user verifies 4 digit year is displayed in file number

  Scenario: Verify, on clicking bene address field, multiple beneficiaries can be selected.
    When user click on bene address field
    Then user verifies multiple beneficiaries can be selected
    And user verifies form is repeated based on the number of beneficiaries selected

  Scenario: Verify, beneficiary name and address should be displayed in the form.
    Then user verifies name and address of the beneficiary should be correctly displayed on each form

  Scenario: Verify, decedent died and county is auto fetched.
    Then user verifies decedent died date and county is auto fetched and non-editable

  Scenario: Verify, on clicking name fiduciary contact list is displayed and multiple users can be selected.
    And user clicks on name column
    Then user verifies multiple fiduciary contacts can be selected

  Scenario: Verify, these contacts are common for all the forms.
    Then user verifies selected fiduciary contacts are common for all the forms

  Scenario: Verify, date can be entered.
    Then user verifies date is entered in correct format

  Scenario: Verify, registrars address is auto fetched and is editable.
    Then user verifies registrars address is auto fetched and the field is editable

  Scenario: Verify, corporate fiduciary type of contact can be selected.
    Then user verifies any one of the corporate fiduciary contacts can be selected for "RW07" form

  Scenario: Verify, based on capacity contact can be selected.
    When user selects capacity as "Personal Representative"
    And user clicks on name of person field
    Then user verifies for "RW07" form fiduciary type of contacts are displayed in the list and can be selected
    When user selects capacity as "Counsel"
    And user clicks on name of person field
    Then user verifies for "RW07" form attorney type of contacts are displayed in the list and can be selected

  Scenario: Verify form can be printed in pdf
    When user click on print form button
    Then verify form can be printed in pdf with name as 'Rw07'
    And verify all the fields entered are correctly reflected in the 'Rw07' pdf

  Scenario: Verify, selection is cleared on clicking clear selection button.
    When user clicks on Clear Selection buttons
    Then user verifies selected contacts on "RW07" form are cleared

  Scenario: Reset the RW07 form
    When user resets the "RW07" form

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser