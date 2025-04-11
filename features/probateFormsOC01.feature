@6in1 @Smoke @Regression
Feature: 6in1 probate form OC01 Feature

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
    And user saves entered Estate information for "OC01" form

  Scenario: Verify that the Estate Name and County fields, file no are preloaded and displayed correctly as read-only fields.
    When user navigates to the probate forms tab
    And user click on the "OC 01" form
    Then user verifies estate name and county fields display preloaded data and are non-editable

  Scenario: Verify, file no can be edited.
    Then user verifies file number field is editable

  Scenario: Verify that the sidebar appears and allows attorney selection.
    When user clicks on Name of Counsel field
    Then user verifies a sidebar appears and attorney can be selected
    And user verifies selected attorney’s details are populated in the 'Name of Counsel' field

  Scenario: Verify, correct attorney details are fetched in the subsequent fields.
    Then user verifies attorney details are auto fetched and correctly displayed

  Scenario: Verify that selecting an attorney updates the attorney details in related forms.
    When user navigates to page number: "11"
    Then user verifies the attorney details are synced with page 11 of same form

  Scenario: Verify that Estate Name, and Date of Death fields are preloaded and non-editable.
    When user navigates to page number: "2"
    Then user verifies estate name and date of death fields display preloaded data and are non-editable

  Scenario: Verify that clicking on the Petitioner field opens the sidebar and allows fiduciary selection.
    When user click on Petitioner name field
    Then user verifies a sidebar is opens and fiduciary contacts can be selected
    And user verifies the selected fiduciaries populate in the Petitioner fields on the form

  Scenario: Verify, only 2 petitioners are visible on the form and rest are attached.
      #Verify, attachment.
      #Verify, all the petitioner details are auto filled.
    When user click on Petitioner name field
    And user selects multiple fiduciary contacts
    Then user verifies out of the selected petitioners only 2 are visible on the form and rest are on the attachment

  Scenario: Verify, selected petitioner can be swapped and deleted.
    When user click on Petitioner name field
    And user swap the selected fiduciary contacts
    Then user verifies swapped petitioner names are reflected on UI accordingly

  Scenario: Verify that changes to the "Date of Will" and "Date of Codicil" fields update the estate record automatically.
    When user modifies the 'Date of Will' and 'Date of Codicil' fields
    Then user verifies the updated dates are reflected in the estate record

  Scenario: Verify that the decedent's name is preloaded from the estate record and displayed as non-editable.
    When user navigates to the probate forms tab
    And user click on the "OC 01" form
    And user navigates to page number: "3"
    Then user verifies decedent's name is displayed and is non-editable

  Scenario: Verify, can add multiple children and DoB.
    Then user verifies multiple children and DoB can be added

  Scenario: Verify, "Name of Estate" from page 2 is auto fetched at the top of this and next page.
    When user navigates to page number: "4"
    Then user verifies Name of the Trust is auto fetched from page 2 on page 4
    When user navigates to page number: "5"
    Then user verifies Name of the Trust is auto fetched from page 2 on page 5

  Scenario: Verify, rest of the selected beneficiaries are displayed as a part of attachment.
    When user navigates to page number: "4"
    And user saves selected beneficiaries details
    Then user verifies rest of the selected beneficiaries are displayed on the attachment

  Scenario: Verify, attachment is displayed on both the pages.
    When user navigates to page number: "4"
    Then user verifies attachment is displayed on page 4
    When user navigates to page number: "5"
    And user verifies same attachment is displayed on page 5

  Scenario: Verify, if the initials are added then name address disappears, if initials are removed then name and address appears.
    When user navigates to page number: "4"
    And user adds initials
    Then user verifies name and address gets disappear
    When user removes initials
    Then user verifies name and address of the beneficiaries is displayed

  Scenario: Verify, comments can be added for that particular beneficiary.
    When user adds comments
    Then user verifies comments are added and auto saved

  Scenario: Verify, relationship of the beneficiary with the given estate/trust is displayed under relationship section.
    Then user verifies correct relationship is auto fetched and displayed under relationship section

  Scenario: Verify, interest value for each beneficiary.
    Then user verifies interest is auto fetched from beny worksheet

  Scenario: Verify, if the display as attachment checkbox is checked then all the beneficiaries are displayed in attachment.
    When user clicks on 'Display ALL Beneficiaries on attachment schedule' checkbox
    Then user verifies all the beny users are displayed as a part of attachment

  Scenario: Verify correct count of main and attachment is displayed.
    Then user verifies the Main's count is turn to zero and only Attachment count is displayed correctly

  Scenario: Verify that the decedent's name is preloaded and displayed as read-only.
    When user navigates to page number: "6"
    Then user verifies decedent's name is displayed and is not editable

  Scenario: Verify that while adding new claimant if initials is empty then Name and Address fields should be required and vice versa for initials.
    When user clicks on Add/Edit Claimants
    And user clicks on Add New Claimant button
    Then user verifies name and Address fields are required if initial field is empty
    Then user verifies if initials exist then Name and Address are not required

  Scenario: Verify that while adding claimant if Name and Address is there then initials is not required.
    Then user verifies initials are not required if Name and Address is there

  Scenario: Verify that a user can add a new claimant successfully.
    And user adds multiple claimants
    Then user verifies the claimant is added to the list and totals are updated dynamically

  Scenario: Verify that claimants exceeding four are moved to the attachment.
    Then user verifies first four claimants remain in the main table and additional claimants are displayed in the attachment

  Scenario: Verify that totals for "Above" and "Attachment" update dynamically.
      #Verify that totals for "Above" and "Attachment" update in real-time while editing in the sidebar.
    Then user verifies the totals displayed correctly for 'Above' and 'Attachment'

  Scenario: Verify the decedent's name is preloaded as read-only.
    When user navigates to page number: "7"
    Then user verifies the preloaded decedent's name is displayed and is read-only

  Scenario: Verify, if both the checkboxes in point 11 is checked then only spouse field is enabled.
    When user check both the checkboxes as yes
    Then user verifies family exemption claimant‘s name field is enabled

  Scenario: Verify, date, payment and interest can be added.
    Then user verifies date, payment and interest can be added in correct format

  Scenario: Verify that fiduciary fields appear when "Yes" is selected.
    When user select 'Yes' for fiduciary status
    Then user verifies additional fiduciary fields appear and are editable

  Scenario: Verify that the decedent's name is preloaded as a read-only field from the estate details.
    When user navigates to page number: "8"
    Then user verifies the decedent's name is displayed and non-editable

  Scenario: Verify that litigation-related fields are enabled only when litigation status is set to "Yes."
    When user select 'Yes' for litigation status
    Then user verifies text fields are enabled dynamically when litigation status is 'Yes'

  Scenario: Verify that the decedent's name is preloaded as a read-only field.
    When user navigates to page number: "9"
    Then user verifies the decedent's name is displayed correctly and is non-editable

  Scenario: Verify date, description and amount can be added in multiline
    Then user verifies date, description and amount can be added in the receipts and disbursements table

  Scenario: Verify, reserve request amount can be added.
    Then user verifies Reserve request amount can be added

  Scenario: Verify, trust's name is auto fetched.
    When user navigates to page number: "10"
    Then user verifies Estate's name is auto fetched and correctly displayed

  Scenario: Verify, if displayed on checkbox is checked, then the contacts are displayed on the form.
    When user checks the Display checkbox for beneficiaries
      #Verify, if proportion entered in the income part is displayed on the form
    And user enters proportion for beneficiaries
    Then user verifies the contacts for which the checkbox is checked are displayed on the form
    And user verifies entered proportion values are correctly displayed on the form

  Scenario: Verify, all the contacts are moved to attachment if the display all bene in attachment checkbox is checked.
    When user checks 'Display ALL INCOME Distributees on attachment' checkbox
    Then user verifies all the beneficiary contacts are moved to the attachment

  Scenario: Verify, only 1 fiduciary contact can be selected and all its details are displayed.
    Then user verifies only one corporate fiduciary contact can be selected and its details are displayed correctly

  Scenario: Verify, warning is displayed for selecting capacity.
    When user clicks on name of petitioner field
    Then user verifies warning is displayed for selecting capacity

  Scenario: Verify, on the basis of capacity selected, contact can be selected and displayed on the form.
    When user selects capacity as "Personal Representative"
    And user clicks on name of petitioner field
    Then user verifies "fiduciary" type of contacts are displayed and can be selected and its details are displayed correctly on form
    When user selects capacity as "Counsel"
    And user clicks on name of petitioner field
    Then user verifies "attorney" type of contacts are displayed and can be selected and its details are displayed correctly on form

  Scenario: Verify, trust's name is auto fetched and correctly displayed.
    When user navigates to page number: "11"
    Then user verifies correct trust name is displayed on the form

  Scenario: Verify, first individual petitioner selected in page 2 is displayed here under individual petitioner.
    Then user verifies 1st individual petitioner selected on page 2 is displayed under individual petitioner

  Scenario: Verify, rest of the individual petitioners are displayed as a part of attachment.
    Then user verifies all the remaining petitioners are displayed as a part of attachment

  Scenario: Verify form can be printed in pdf
    When user click on print form button
    Then verify form can be printed in pdf with name as 'OC01'
    And verify all the fields entered are correctly reflected in the 'OC01' pdf

  Scenario: Verify, if the notification if the selected contact is removed from the estate.
    When user navigates to Estate Contacts tab
    Then user verifies notification is displayed when the contact selected as the petitioner is removed from the estate contacts
      #Verify, notification popup is shown for removal of contact already in use.
    Then user verifies notification is displayed when the beneficiary contact is removed from the estate contacts
    Then user verifies notification is displayed when the corporate fiduciary and attorney contacts are removed from the estate contacts

  Scenario: Verify, if the contact is removed, it is removed from the form as well.
    When user navigates to the probate forms tab
    And user click on the "OC 01" form
    And user navigates to page number: "2"
    Then user verifies removed petitioner contacts from the estate contacts is also gets removed from the form

  Scenario: Verify, if for a user role of beny is removed.
    When user navigates to page number: "4"
    Then user verifies removed beneficiary contact from the estate contacts is also gets removed from the form

  Scenario: Verify, if for a user role of beny, fiduciary, attorney is removed.
    When user navigates to page number: "10"
    Then user verifies removed corporate fiduciary and attorney contacts from the estate contacts are also gets removed from the form

  Scenario: Reset Roles of Removed Contacts
    When user resets roles of removed contacts from the Estate Contacts

  Scenario: Reset the OC01 form
    When user navigates to the probate forms tab
    And user click on the "OC 01" form
    Then user resets the "OC01" form

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser