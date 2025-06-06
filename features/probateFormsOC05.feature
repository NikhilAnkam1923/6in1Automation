@6in1 @Smoke @Regression
Feature: 6in1 probate form OC05 Feature

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "$6in1_url"
    When user login using "nikhilankam@benchmarkit.solutions" and "Bits@1234"
    Then user verifies the Home page

  Scenario: Open Estate
    When user opens "Watt, Sara Arik Jr." Estate
    Then user saves entered Estate information for "OC05" form

  Scenario: Verify, correct County of the decedent is fetched from the decedent info. tab.
    When user navigates to the probate forms tab
    And user click on the "OC 05" form
    Then user verifies by default page 1 should is opened and correct county name is fetched and displayed at the top of the "OC05" form

  Scenario: Verify, No can be entered in the field.
    Then user verifies for "OC05" form file number field is editable

  Scenario: Verify, Counsel can be selected from the side bar.
    When user clicks on Name of Counsel field
    Then user verifies for "OC05" form a sidebar appears and attorney can be selected
    Then user verifies Counsel details are populated correctly for "OC05" form

  Scenario: Verify, counsel can be changed.
    When user clicks on Name of Counsel field
    Then user selects new Counsel
    Then user verifies new Counsel details are populated correctly on the form

   Scenario: Verify, petitioner can be selected from the side bar.
     When user navigates to page number: "2"
     When user click on Petitioner name field
     And user selects multiple petitioners for "OC05" form
     Then user verifies selected names of petitioner are displayed with address on "OC05" form
     Then user verifies out of the selected petitioners only 2 are visible on the form and rest are on the attachment for "OC05" form

   Scenario: Verify, selected petitioner can be swapped and deleted.
     When user click on Petitioner name field
     And user swap the selected petitioner contacts for "OC05" form
     Then user verifies for "OC05" form swapped petitioner names are reflected on UI accordingly

   Scenario: Verify, agent's name and address can be added in the agent field.
      #Verify, multiple agents can be added.
     When user enters agent's name and address details
     Then user verifies agent's name and address details are auto saved

   Scenario: Verify, agents name and address can be edited.
     When user edit agent's name and address details
     Then user verifies agent's name and address details are auto saved

   Scenario: Verify, trust's name is auto fetched.
     When user navigates to page number: "3"
     Then user verifies estate's name is auto fetched and correctly displayed on page 3

  Scenario: Verify, trust's name is auto fetched.
    When user navigates to page number: "4"
    Then user verifies estate's name is auto fetched and correctly displayed on page 4

  Scenario: Verify, institution's address can be added.
    When user clicks on address section
    And user add institution address details
    Then user verifies address is displayed on the form

   Scenario: Verify, trust's name is auto fetched.
     When user navigates to page number: "5"
     Then user verifies estate's name is auto fetched and correctly displayed on "OC05" form

   Scenario: Verify that additional beneficiaries are listed in an attachment, and the form displays "See continuation schedule attached."
     When user saves selected beneficiaries details for "OC05" form
     Then user verifies for "OC05" form rest of the selected beneficiaries are displayed on the attachment

   Scenario: Verify, name and address disappears on entering initials.
     When user adds initials for "OC05"
     Then user verifies name and address gets disappear for "OC05"

   Scenario: Verify, initials field is empty if the name and address is displayed.
     When user removes initials for "OC05"
     Then user verifies name and address of the beneficiaries is displayed for "OC05"

   Scenario: Verify, if display all beneficiaries checkbox is checked then all the beneficiaries are moved to attachment or not.
     When user clicks on 'Display ALL Beneficiaries on attachment schedule' checkbox
     Then user verifies for "OC05" form all the beny users are displayed as a part of attachment

   Scenario: Verify, interest rate is fetched from beny worksheet.
      #Verify, interest value for each beneficiary.
     Then user verifies interest is auto fetched from beny worksheet for "OC05" form

   Scenario: Verify that on page 5, comments can be added for that particular beneficiary.
     When user adds comments for "OC05" form
     Then user verifies comments are added and auto saved for "OC05" form

   Scenario: Verify, relationship of the beneficiary with the given estate is displayed under relationship section.
     Then user verifies correct relationship is auto fetched and displayed under relationship section for "OC05" form

   Scenario: Verify, estate name is auto fetched.
     When user navigates to page number: "6"
     Then user verifies estate's name is auto fetched and correctly displayed on page 6

   Scenario: Verify that Q.B & C should allow user to input values through sidebar.
     Then user verifies the system allow the user to add description through sidebar

  Scenario: Verify, estate name is auto fetched.
    When user navigates to page number: "7"
    Then user verifies estate's name is auto fetched and correctly displayed on page 7

  Scenario: Verify, if displayed on checkbox is checked, then the contacts are displayed on the form.
    When user checks the Display checkbox for beneficiaries of "OC05" form
    Then user verifies the contacts for which the checkbox is checked are displayed on the "OC05" form

  Scenario: Verify, all the contacts are moved to attachment if the display all bene in attachment checkbox is checked.
    When user checks 'Display ALL INCOME Distributees on attachment' checkbox
    Then user verifies all the beneficiary contacts are moved to the attachment for "OC05" form

  Scenario: Verify, Estate name is auto fetched and correctly displayed.
    When user navigates to page number: "8"
    Then user verifies estate's name is auto fetched and correctly displayed on page 8

  Scenario: Verify, first individual petitioner selected in page 2 is displayed here under individual petitioner.
    Then user verifies 1st individual petitioner selected on page 2 is displayed under individual petitioner on "OC05" form

  Scenario: Verify, rest of the individual petitioners are displayed as a part of attachment.
    Then user verifies all the remaining petitioners are displayed as a part of attachment for "OC05" form

  Scenario: Verify, if any new petitioner is added, it is reflected in the attachment.
    When user navigates to page number: "2"
    And user click on Petitioner name field
    And user adds new petitioner for "OC05" form
    When user navigates to page number: "8"
    Then user verifies newly added petitioner is displayed in the attachment for "OC05" form

  Scenario: Verify form can be printed in pdf
    When user click on print form button
    Then verify form can be printed in pdf with name as 'OC05'
    And verify all the fields entered are correctly reflected in the 'OC05' pdf

  Scenario: Verify, if the contact removed from estate contacts is also removed from the form.
    When user navigates to Estate Contacts tab
    Then user verifies notification is displayed when the contact selected as the counsel is removed from the estate contacts
      #Verify, notification popup is shown for removal of contact already in use.
    Then user verifies for "OC05" form notification is displayed when the beneficiary contact is removed from the estate contacts

  Scenario: Verify, if the contact selected as counsel is removed from the estate contact.
    When user navigates to the probate forms tab
    And user click on the "OC 05" form
    And user navigates to page number: "1"
    Then user verifies removed counsel contact from the estate contacts is also gets removed from form

  Scenario: Verify, if for a user role of beny is removed.
    When user navigates to page number: "5"
    Then user verifies removed beneficiary contact from the estate contacts is also gets removed from the "OC05" form

  Scenario: Reset Roles of Removed Contacts
    When user resets roles of removed contacts from the Estate Contacts of "OC05" form

  Scenario: Reset the OC05 form
    When user navigates to the probate forms tab
    And user click on the "OC 05" form
    Then user resets the "OC05" form

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser