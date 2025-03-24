@6in1 @Smoke @Regression
Feature: 6in1 probate form RW08 Feature

  @Setup
  Scenario Outline: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "https://nikeshoe.uat.lacknersoftware.cloud"
    When user login using "<user-email>" and "<password>"
    Then user verifies the Home page
    Examples:
      | user-email                        | password  |
      | nikhilankam@benchmarkit.solutions | Bits@1234 |

  Scenario: Open Estate
    When user opens "Kris Warner" Estate
    And user saves entered Estate information for "RW08" form

  Scenario: Verify, county, estate and file number aka names are auto populated on the form.
    When user navigates to the probate forms tab
    And user click on the "RW 08" form
    Then user selects the aka checkbox
    Then user verifies the county, estate, file number and aka names are auto-populated on the "RW08" form

  Scenario: Verify, the auto populated fields are not editable.
    Then user verifies the auto-populated fields of "RW08" form are not editable

  Scenario: Verify, Will number and other dates can be entered in correct format.
    Then user verifies will number and dates can be entered and are auto saved

  Scenario: Verify, checkboxes for file no field.
    When user check the "Use Will Number" checkbox
    Then user verifies "will number" is displayed in file number
    When user check the "Use 4 digit year" checkbox
    Then user verifies "4 digit year" is displayed in file number

  Scenario: Verify, multiple beneficiaries can be selected.
    Then user verifies multiple beneficiary contacts can be selected and displayed on the form

  Scenario: Verify, the beneficiaries selected beyond 6 are displayed on the attachment.
    Then user verifies beneficiary contacts selected beyond 6 are displayed on the attachment

  Scenario: Verify, count is correctly displayed.
    Then user verifies Main and Attachment count is displayed correctly

  Scenario: Verify, on clicking "Display ALL beneficiary on attachment" checkbox all the contacts are transferred on attachment.
    When user checks Display ALL beneficiary on attachment checkbox
    Then user verifies all the beneficiaries are transferred to attachment and count is reflected accordingly

  Scenario: Verify, corporate fiduciary type of contact can be selected.
    Then user verifies any one of the corporate fiduciary contacts can be selected for "RW08" form

  Scenario: Verify, based on capacity contact can be selected.
    When user selects capacity as "Personal Representative"
    And user clicks on name of person field
    Then user verifies for "RW08" form fiduciary type of contacts are displayed in the list and can be selected
    When user selects capacity as "Counsel"
    And user clicks on name of person field
    Then user verifies for "RW08" form attorney type of contacts are displayed in the list and can be selected

  Scenario: Verify, these 2 sections are common for RW07, RW08 and anything updated is reflected in all the forms.
    When user click on the "RW 07" form
    Then user verifies Corporate Fiduciary and Person sections information is common for RW07 and RW08
    And user click on the "RW 08" form

  Scenario: Verify form can be printed in pdf
    When user click on print form button
    Then verify form can be printed in pdf with name as 'Rw08'
    And verify all the fields entered are correctly reflected in the 'Rw08' pdf

  Scenario: Verify, selection is cleared on clicking clear selection button.
    When user clicks on Clear Selection buttons
    Then user verifies selected contacts on "RW08" form are cleared

  Scenario: Reset the RW08 form
    When user resets the "RW08" form

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser