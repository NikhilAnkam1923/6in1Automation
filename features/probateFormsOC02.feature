@6in1 @Smoke @Regression
Feature: 6in1 probate form OC02 Feature

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "$6in1_url"
    When user login using "nikhilankam@benchmarkit.solutions" and "Bits@1234"
    Then user verifies the Home page

  Scenario: Open Estate
    When user opens "Watt, Sara Arik Jr." Estate
    Then user saves entered Estate information for "OC02" form

  Scenario: Verify, correct County of the decedent, file no is fetched from the decedent info. tab.
    When user navigates to the probate forms tab
    And user click on the "OC 02" form
    Then user verifies by default page 1 should is opened and correct county name is fetched and displayed at the top of the "OC02" form

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
    Then user verifies for "OC02" form file number field is editable

  Scenario: Verify that the sidebar appears and allows attorney selection.
    When user clicks on Name of Counsel field
    Then user verifies for "OC02" form a sidebar appears and attorney can be selected
    And user verifies for "OC02" form selected attorney’s details are populated in the 'Name of Counsel' field

  Scenario: Verify, correct attorney details are fetched in the subsequent fields.
    Then user verifies for "OC02" form attorney details are auto fetched and correctly displayed

  Scenario: Verify, petitioner can be selected from the side bar.
    When user navigates to page number: "2"
    And user click on Petitioner name field
    And user selects two petitioners
    Then user verifies selected names of petitioner are displayed with address on "OC02" form

  Scenario: Verify, attachment.
    When user click on Petitioner name field
    And user selects multiple petitioners for "OC02" form
    Then user verifies attachment icon is visible and the petitioner details are correctly visible on the attachment

  Scenario: Verify, selected petitioner can be swapped and deleted.
    When user click on Petitioner name field
    And user swap the selected petitioner contacts for "OC02" form
    Then user verifies for "OC02" form swapped petitioner names are reflected on UI accordingly

  Scenario: Verify, Testamentary trust section is enabled on selecting the radio button with the same name.
    When user navigates to page number: "3"
    And user select Testamentary option
    Then user verifies Testamentary trust section is enabled and inter vivos trust section is disabled

  Scenario: Verify, "Decedent date of death" is fetched from the decedent info.
    When user select Testamentary option
    Then user verifies Date of death is auto fetched from the decedent info

  Scenario: Verify, "Date of Codicil" is fetched from the decedent tab.
    Then user verifies Codicil dates are auto fetched from decedent tab

  Scenario: Verify, codicil dates changed are reflected in the decedent tab as well.
    When user modifies the codicil dates
    Then user verifies updated codicil dates are reflected in the decedent tab

  Scenario: Verify, Judicial County is auto fetched from the decedent.
    When user navigates to the probate forms tab
    And user click on the "OC 02" form
    And user navigates to page number: "3"
    Then user verifies Judicial county is auto fetched from the decedent info tab

  Scenario: Verify, Inter vivos section is enabled if the checkbox with the same name is selected.
    When user select Inter Vivos option
    Then user verifies Inter Vivos trust section is enabled and Testamentary trust section is disabled

  Scenario: Verify, Input fields in inter vivos trust are editable and auto saved.
    Then user verifies all the details can be entered and auto saved

  Scenario: Verify, "Name of Trust" from page 2 is auto fetched at the top of this and next page.
    When user navigates to page number: "6"
    Then user verifies Name of the Trust is auto fetched from page 2

  Scenario: Verify, rest of the  beneficiaries are displayed as a part of attachment.
    When user saves selected beneficiaries details for "OC02" form
    Then user verifies for "OC02" form rest of the selected beneficiaries are displayed on the attachment

  Scenario: Verify, attachment is displayed on both the pages.
    When user navigates to page number: "6"
    Then user verifies attachment is displayed on page 6
    When user navigates to page number: "7"
    And user verifies same attachment is displayed on page 7

  Scenario: Verify, if the display as attachment checkbox is checked then all the beneficiaries are displayed in attachment.
    When user navigates to page number: "6"
    And user clicks on 'Display ALL Beneficiaries on attachment schedule' checkbox
    Then user verifies for "OC02" form all the beny users are displayed as a part of attachment

  Scenario: Verify correct count of main and attachment is displayed.
    Then user verifies for "OC02" form the Main's count is turn to zero and only Attachment count is displayed correctly

  Scenario: Verify, if the initials are added then name address disappears. if initials are removed then name and address appears.
    When user adds initials for "OC02"
    Then user verifies name and address gets disappear for "OC02"
    When user removes initials for "OC02"
    Then user verifies name and address of the beneficiaries is displayed for "OC02"

  Scenario: Verify, relationship of the beneficiary with the given estate/trust is displayed under relationship section.
    Then user verifies correct relationship is auto fetched and displayed under relationship section for "OC02" form

  Scenario: Verify, interest value for each beneficiary.
    Then user verifies interest is auto fetched from beny worksheet for "OC02" form

  Scenario: Verify, name of the trust is auto fetched and is not editable.
    When user navigates to page number: "8"
    Then user verifies correct trust name is displayed on page 8 and is not editable

  Scenario: Verify, name of the trust is auto fetched and is not editable.
    When user navigates to page number: "9"
    Then user verifies correct trust name is displayed on page 9 and is not editable

  Scenario: Verify, date and principal amount can be added for previously paid commissions in multi lines.
    Then user verifies principal amount and date can be added

  Scenario: Verify, reserve amount and purpose fields are editable.
    Then user verifies both the fields are editable and accept values

  Scenario: Verify, trust's name is auto fetched.
    When user navigates to page number: "10"
    Then user verifies estate's name is auto fetched and correctly displayed on "OC02" form

  Scenario: Verify, if displayed on checkbox is checked, then the contacts are displayed on the form.
    When user checks the Display checkbox for beneficiaries of "OC02" form
    Then user verifies the contacts for which the checkbox is checked are displayed on the "OC02" form

  Scenario: Verify, all the contacts are moved to attachment if the display all bene in attachment checkbox is checked.
    When user checks 'Display ALL INCOME Distributees on attachment' checkbox
    Then user verifies all the beneficiary contacts are moved to the attachment for "OC02" form

  Scenario: Verify, trust's name is auto fetched and correctly displayed.
    When user navigates to page number: "11"
    Then user verifies correct trust name is displayed on the "OC02" form

  Scenario: Verify, first individual petitioner selected in page 2 is displayed here under individual petitioner.
    Then user verifies 1st individual petitioner selected on page 2 is displayed under individual petitioner on "OC02" form

  Scenario: Verify, rest of the individual petitioners are displayed as a part of attachment.
    Then user verifies all the remaining petitioners are displayed as a part of attachment for "OC02" form

  Scenario: Verify form can be printed in pdf
    When user click on print form button
    Then verify form can be printed in pdf with name as 'OC02'
    And verify all the fields entered are correctly reflected in the 'OC02' pdf

  Scenario: Verify, if the notification if the selected contact is removed from the estate.
    When user navigates to Estate Contacts tab
    Then user verifies for "OC02" form notification is displayed when the contact selected as the petitioner is removed from the estate contacts
      #Verify, notification popup is shown for removal of contact already in use.
    Then user verifies for "OC02" form notification is displayed when the beneficiary contact is removed from the estate contacts

  Scenario: Verify, if the contact is removed, it is removed from the form as well.
    When user navigates to the probate forms tab
    And user click on the "OC 02" form
    And user navigates to page number: "2"
    Then user verifies removed petitioner contacts from the estate contacts is also gets removed from the "OC02" form

  Scenario: Verify, if for a user role of beny is removed.
    When user navigates to page number: "6"
    Then user verifies removed beneficiary contact from the estate contacts is also gets removed from the "OC02" form

  Scenario: Reset Roles of Removed Contacts
    When user resets roles of removed contacts from the Estate Contacts of "OC02" form

  Scenario: Reset the OC02 form
    When user navigates to the probate forms tab
    And user click on the "OC 02" form
    Then user resets the "OC02" form

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser