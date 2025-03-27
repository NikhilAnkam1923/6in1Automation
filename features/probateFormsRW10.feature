@6in1 @Smoke @Regression
Feature: 6in1 probate form RW10 Feature

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "$6in1_url"
    When user login using "nikhilankam@benchmarkit.solutions" and "Bits@1234"
    Then user verifies the Home page

  Scenario: Open Estate
    When user opens "Kris Warner" Estate
    And user saves entered Estate information for "RW10" form

  Scenario: Verify, title of the form and if county is fetched from the decedent info.
    When user navigates to the probate forms tab
    And user click on the "RW 10" form
    Then user verifies title of the form is correctly displayed and county is fetched from decedent info

  Scenario: Verify, "Name of Decedent", "Date of Death", "File Number" is fetched from the decedent info.
    Then user verifies 'Name of Decedent', 'Date of Death', 'File Number' is fetched from the decedent info

  Scenario: Verify, if above fetched details are editable.
    Then user verifies Name, Date of death and File number fields are not editable

  Scenario: Verify, questionnaire is correctly displayed with yes or no options.
    Then user verifies all the questionnaire is properly displayed, with options yes or no

  Scenario: Verify, either yes or no is clickable.
    Then user verifies for all the questionnaire either yes or no option can be selected

  Scenario: Verify, if option "Yes" is selected in point 1, point 2 is enabled or not.
    Then user verifies when 'Yes' option is selected in point 1, then the text box in point 2 is not enabled and not editable

  Scenario: Verify, if "No" is checked in point 1, then text box in point 2 is enabled.
    Then user verifies when 'No' option is selected in point 1, then the text box in point 2 is enabled and editable

  Scenario: Verify, reason for to be mentioned, if no is ticked.
      #Verify, all the other input boxes are editable.
      #Verify, the in fields marked as yellow in background are only editable.
    Then user verifies reason text box field is able to accept the text

  Scenario: Verify, if "Yes" is ticked, point 2 is disabled and text entered disappear.
    Then user verifies when Yes is ticked in point 1, then Text entered in point 2 gets disappear

  Scenario: Verify, if "Yes" option is selected in point 1, then point 3 is enabled
    Then user verifies when Yes is ticked in point 1, then text field in point 3 is enabled and editable

  Scenario: Verify, if "No" is checked in point 1, then point 3 is disabled.
    Then user verifies when No is ticked in point 1, then text field in point 3 is disabled

  Scenario: Verify, if "NO" is clicked text box in point 2 is enabled and empty.
    Then user verifies when No is ticked in point 1, then text box in point 2 is editable, enabled and empty

  Scenario: Verify, if No is checked, point 3 is disabled and text entered disappear.
    Then user verifies when No is ticked in point 1, then point 3 is disabled and text entered in it gets disappear

  Scenario: Verify, date field follows correct format.
    When user enters date in date field
    Then user verifies entered date follows the correct date format

  Scenario: Verify, corporate fiduciary type of contact can be selected.
    Then user verifies any one of the corporate fiduciary contacts can be selected for "RW10" form

  Scenario: Verify, based on capacity contact can be selected.
    When user selects capacity as "Personal Representative"
    And user clicks on name of person field
    Then user verifies for "RW10" form fiduciary type of contacts are displayed in the list and can be selected
    When user selects capacity as "Counsel"
    And user clicks on name of person field
    Then user verifies for "RW10" form attorney type of contacts are displayed in the list and can be selected

  Scenario: Verify, these 2 sections are common for RW07, RW08, RW10 and anything updated is reflected in all the forms.
    When user click on the "RW 07" form
    Then user verifies Corporate Fiduciary and Person sections information is common for RW07 and RW10
    When user click on the "RW 08" form
    Then user verifies Corporate Fiduciary and Person sections information is common for RW08 and RW10

  Scenario: Verify form can be printed in pdf
    When user click on the "RW 10" form
    When user click on print form button
    Then verify form can be printed in pdf with name as 'Rw10'
    And verify all the fields entered are correctly reflected in the 'Rw10' pdf

  Scenario: Verify, selection is cleared on clicking clear selection button.
    When user clicks on Clear Selection buttons
    Then user verifies selected contacts on "RW10" form are cleared

  Scenario: Reset the RW10 form
    When user resets the "RW10" form

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser