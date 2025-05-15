@6in1 @Smoke @Regression
Feature: 6in1 probate form OC04 Feature

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
    And user saves entered Estate information for "OC04" form

  Scenario: Verify that the “Estate of” field is populated automatically from estate records.
    When user navigates to the probate forms tab
    And user click on the "OC 04" form
    Then user verifies the 'Estate of' field is populated automatically with the correct estate name for "OC04" form

  Scenario: Verify that selecting Counsel from the sidebar populates Counsel details correctly.
    When user clicks on Name of Counsel field
    Then user verifies for "OC04" form a sidebar appears and attorney can be selected
    Then user verifies Counsel details are populated correctly for "OC04" form

  Scenario: Verify that the estate name is auto-populated from estate records.
    When user navigates to page number: "2"
    Then user verifies the estate name is pre-filled from the estate records for "OC04" form

  Scenario: Verify, only 2 petitioners are visible on the form and rest are attached.
    When user click on Petitioner name field
    And user selects multiple petitioners for "OC03" form
      #Verify, attachment.
    Then user verifies out of the selected petitioners only 2 are visible on the form and rest are on the attachment for "OC03" form

  Scenario: Verify, selected petitioner can be swapped and deleted.
    When user click on Petitioner name field
    And user swap the selected petitioner contacts for "OC03" form
    Then user verifies for "OC03" form swapped petitioner names are reflected on UI accordingly

  Scenario: Verify that the "Estate of" field is auto-populated with the estate name.
    When user navigates to page number: "3"
    Then user verifies the field is populated with the correct estate name for "OC04" form

  Scenario: Verify that fee claims (amount, start date, and end date) are saved and displayed correctly.
    Then user verifies fee claims (amount, start date, and end date) are saved and displayed correctly for "OC04" form

  Scenario: Verify that the "Estate of" field is auto-populated with the estate name.
    When user navigates to page number: "4"
    Then user verifies the field is populated with the correct estate name on page 4 for "OC04" form

  Scenario: Verify, rest of the selected beneficiaries are displayed as a part of attachment.
    When user saves selected beneficiaries details for "OC04" form
    Then user verifies for "OC04" form rest of the selected beneficiaries are displayed on the attachment

  Scenario: Verify, if the display as attachment checkbox is checked then all the beneficiaries are displayed in attachment.
    When user clicks on 'Display ALL Beneficiaries on attachment schedule' checkbox
    Then user verifies for "OC04" form all the beny users are displayed as a part of attachment

  Scenario: Verify correct count of main and attachment is displayed.
    Then user verifies for "OC04" form the Main's count is turn to zero and only Attachment count is displayed correctly

  Scenario: Verify, if the initials are added then name address disappears. if initials are removed then name and address appears.
    When user adds initials for "OC04"
    Then user verifies name and address gets disappear for "OC04"
    When user removes initials for "OC04"
    Then user verifies name and address of the beneficiaries is displayed for "OC04"

  Scenario: Verify, comments can be added for that particular beneficiary.
    When user adds comments for "OC04" form
    Then user verifies comments are added and auto saved for "OC04" form

  Scenario: Verify, relationship of the beneficiary with the given estate/trust is displayed under relationship section.
    Then user verifies correct relationship is auto fetched and displayed under relationship section for "OC04" form

  Scenario: Verify, interest value for each beneficiary.
    Then user verifies interest is auto fetched from beny worksheet for "OC04" form

  Scenario: Verify that the "Estate of" field is auto-populated with the estate name.
    When user navigates to page number: "5"
    Then user verifies the field is populated with the correct estate name on page 5 for "OC04" form

  Scenario: Verify that the sidebar opens when "Edit Amounts/Proportions" is clicked.
    When user clicks on the 'Edit Amounts/Proportions' button for income
    Then user verifies the sidebar opens displaying a list of beneficiaries for "OC04" form

#  Scenario: Verify that users can specify amounts and proportions for beneficiaries.
#    When user enters amounts and proportions for beneficiaries
#    Then user verifies the entered data is saved and displayed correctly on the form
#
#  Scenario: Verify that excess distributees are included in an attachment, with the form displaying "See attached schedule."
#    Then user verifies the form displays 'See continuation schedule attached' and extra beneficiaries appear in an attachment

  Scenario: Reset the OC02 form
    When user navigates to the probate forms tab
    And user click on the "OC 04" form
    Then user resets the "OC04" form

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser