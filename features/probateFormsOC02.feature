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

  Scenario: Verify, Inter vivos section is enabled if the checkbox with the same name is selected.
    When user select Inter Vivos option
    Then user verifies Inter Vivos trust section is enabled and Testamentary trust section is disabled


  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser