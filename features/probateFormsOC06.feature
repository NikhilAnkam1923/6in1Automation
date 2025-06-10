@6in1 @Smoke @Regression
Feature: 6in1 probate form OC06 Feature

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "$6in1_url"
    When user login using "nikhilankam@benchmarkit.solutions" and "Bits@1234"
    Then user verifies the Home page

  Scenario: Open Estate
    When user opens "Watt, Sara Arik Jr." Estate
    Then user saves entered Estate information for "OC06" form

  Scenario: Verify that the estate name and file number are auto-filled when estate records exist
    When user navigates to the probate forms tab
    And user click on the "OC 06" form
    Then user verifies by default page 1 should be opened and correct estate name and file number are auto-filled

  Scenario: Verify that the file number can be edited and saved without restrictions
    When user enters a valid numeric value into the file number field
    Then the file number should be updated and saved automatically

  Scenario: Verify that county is fetched correctly from the decedent information
    Then user verifies correct county name is fetched and displayed at the top of the "OC06" form

  Scenario: Verify that user has provision to select 'Use Four Digit Year' checkbox for file number.
    When user clicks the Use 4 digit year checkbox
    Then user verifies file number with four digit year format is displayed in the file number box

  Scenario: Verify that on page 1 there are two checkboxes "Settlor & Deceased" in front of "Estate Of" field and either of them can be selected.
    Then user verifies "Settlor & Deceased" these two checkboxes are there on the page 1 and either of them can be selected

  Scenario: Verify that the estate name is auto-filled from estate records.
    When user navigates to page number: "2"
    Then user verifies the estate name is preloaded correctly from the estate records

  Scenario: Verify that "Settlor & Deceased" checkbox selections made on Page 1 are carried forward and displayed correctly on Page 2.
    Then user verifies selected checkboxes "Settlor & Deceased" from Page 1 are displayed accurately on Page 2

  Scenario: Verify that the estate name is auto-filled from estate records.
    When user navigates to page number: "3"
    Then user verifies the estate name is preloaded correctly from the estate records on page 3

  Scenario: Verify that "Settlor & Deceased" checkbox selections made on Page 1 are carried forward to Page 3.
    Then user verifies selected checkboxes from "Settlor & Deceased" are displayed accurately on Page 3

  Scenario: Verify that the estate name is preloaded from estate records.
    When user navigates to page number: "4"
    Then user verifies the estate name is preloaded correctly from the estate records on page 4

  Scenario: Verify that (Settlor & Deceased) checkbox selections from Page 1 are carried forward to Page 4.
    Then user verifies the selections made on Page 1 are accurately displayed on Page 4

  Scenario: Verify that the default attorney displayed is the first attorney entered for the estate.
    When user selects attorney contact
    Then user verifies Counsel details are populated correctly for "OC06" form

  Scenario: Reset the OC06 form
    Then user resets the "OC06" form

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser