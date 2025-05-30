@6in1 @Smoke @Regression
Feature: 6in1 probate form OC03 Feature

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "$6in1_url"
    When user login using "nikhilankam@benchmarkit.solutions" and "Bits@1234"
    Then user verifies the Home page

  Scenario: Open Estate
    When user opens "Watt, Sara Arik Jr." Estate
    And user saves entered Estate information for "OC03" form

  Scenario: Verify that the “Estate of” field is populated automatically from estate records.
    When user navigates to the probate forms tab
    And user click on the "OC 03" form
    Then user verifies the 'Estate of' field is populated automatically with the correct estate name for "OC03" form

  Scenario: Verify that the “Account of” field is auto-filled with the first Fiduciary contact’s name.
    Then user verifies the 'Account of' field is populated with the Fiduciary’s name

  Scenario: Verify that selecting Counsel from the sidebar populates Counsel details correctly.
    When user clicks on Name of Counsel field
    Then user verifies for "OC03" form a sidebar appears and attorney can be selected
    Then user verifies Counsel details are populated correctly for "OC03" form

  Scenario: Verify that the estate name is auto-populated from estate records.
    When user navigates to page number: "2"
    Then user verifies the estate name is pre-filled from the estate records for "OC03" form

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
    Then user verifies the field is populated with the correct estate name for "OC03" form

  Scenario: Verify that fee claims (amount, start date, and end date) are saved and displayed correctly.
    Then user verifies fee claims (amount, start date, and end date) are saved and displayed correctly for "OC03" form

  Scenario: Verify that the "Estate of" field is auto-populated with the estate name.
    When user navigates to page number: "4"
    Then user verifies the field is populated with the correct estate name on page 4 for "OC03" form

  Scenario: Verify, rest of the selected beneficiaries are displayed as a part of attachment.
    When user saves selected beneficiaries details for "OC03" form
    Then user verifies for "OC03" form rest of the selected beneficiaries are displayed on the attachment

  Scenario: Verify correct count of main and attachment is displayed.
    When user clicks on 'Display ALL Beneficiaries on attachment schedule' checkbox
    Then user verifies for "OC03" form all the beny users are displayed as a part of attachment
    Then user verifies for "OC03" form the Main's count is turn to zero and only Attachment count is displayed correctly

  Scenario: Verify, if the initials are added then name address disappears. if initials are removed then name and address appears.
    When user adds initials for "OC03"
    Then user verifies name and address gets disappear for "OC03"
    When user removes initials for "OC03"
    Then user verifies name and address of the beneficiaries is displayed for "OC03"

  Scenario: Verify, comments can be added for that particular beneficiary.
    When user adds comments for "OC03" form
    Then user verifies comments are added and auto saved for "OC03" form

  Scenario: Verify, relationship of the beneficiary with the given estate/trust is displayed under relationship section.
    Then user verifies correct relationship is auto fetched and displayed under relationship section for "OC03" form

  Scenario: Verify, interest value for each beneficiary.
    Then user verifies interest is auto fetched from beny worksheet for "OC03" form

  Scenario: Verify that the "Estate of" field is auto-populated with the estate name.
    When user navigates to page number: "5"
    Then user verifies the field is populated with the correct estate name on page 5 for "OC03" form

  Scenario: Verify that the sidebar opens when "Edit Amounts/Proportions" is clicked.
    When user clicks on the 'Edit Amounts/Proportions' button for income
    Then user verifies the sidebar opens displaying a list of beneficiaries for "OC03" form

  Scenario: Verify that users can specify amounts and proportions for beneficiaries.
    When user enters amounts and proportions for beneficiaries for "OC03" form
    Then user verifies the entered data is saved and displayed correctly on the "OC03" form

  Scenario: Verify that excess distributees are included in an attachment, with the form displaying "See attached schedule."
    Then user verifies the "OC03" form displays 'See continuation schedule attached' and extra beneficiaries appear in an attachment

  Scenario: Verify, trust's name is auto fetched and correctly displayed.
    When user navigates to page number: "6"
    Then user verifies correct trust name is displayed on page 6 for "OC03" form

  Scenario: Verify, first individual petitioner selected in page 2 is displayed here under individual petitioner.
    Then user verifies 1st individual petitioner selected on page 2 is displayed under individual petitioner on "OC03" form

  Scenario: Verify, rest of the individual petitioners are displayed as a part of attachment.
    Then user verifies all the remaining petitioners are displayed as a part of attachment for "OC03" form

  Scenario: Verify, if any new petitioner is added, it is reflected in the attachment.
    When user navigates to page number: "2"
    And user click on Petitioner name field
    And user adds new petitioner for "OC03" form
    When user navigates to page number: "6"
    Then user verifies newly added petitioner is displayed in the attachment for "OC03" form

  Scenario: Verify form can be printed in pdf
    When user click on print form button
    Then verify form can be printed in pdf with name as 'OC03'
    And verify all the fields entered are correctly reflected in the 'OC03' pdf

  Scenario: Verify, if the notification if the selected contact is removed from the estate.
    When user navigates to Estate Contacts tab
    Then user verifies for "OC03" form notification is displayed when the contact selected as the petitioner is removed from the estate contacts
      #Verify, notification popup is shown for removal of contact already in use.
    Then user verifies for "OC03" form notification is displayed when the beneficiary contact is removed from the estate contacts

  Scenario: Verify, if the contact is removed, it is removed from the form as well.
    When user navigates to the probate forms tab
    And user click on the "OC 03" form
    And user navigates to page number: "2"
    Then user verifies removed petitioner contacts from the estate contacts is also gets removed from the "OC03" form

  Scenario: Verify, if for a user role of beny is removed.
    When user navigates to page number: "4"
    Then user verifies removed beneficiary contact from the estate contacts is also gets removed from the "OC03" form

  Scenario: Verify, if the 1st petitioner is removed, next in line is displayed on the form.
    When user navigates to page number: "6"
    Then user verifies after removing the existing contact next inline contact is displayed on the "OC03" form

  Scenario: Verify, if any petitioner removed, same is reflected in the attachment.
    Then user verifies the contact removed from the estate contacts is also removed from the attachment as well for "OC03" form

  Scenario: Reset Roles of Removed Contacts
    When user resets roles of removed contacts from the Estate Contacts of "OC03" form

  Scenario: Reset the OC03 form
    When user navigates to the probate forms tab
    And user click on the "OC 03" form
    Then user resets the "OC03" form

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser