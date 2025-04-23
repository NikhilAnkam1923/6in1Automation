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
    Then user verifies selected names of petitioner are displayed with address

  Scenario: Verify, attachment.
    When user click on Petitioner name field
    And user selects multiple petitioners
    Then user verifies attachment icon is visible and the petitioner details are correctly visible on the attachment

  Scenario: Verify, selected petitioner can be swapped and deleted.
    When user click on Petitioner name field
    And user swap the selected petitioner contacts
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


  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser