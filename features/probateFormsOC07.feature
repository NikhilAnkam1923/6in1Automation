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
    Then user saves entered Estate information for "OC07" form

  Scenario: Verify that the estate name, address, date of death, and file number are auto-filled from estate records
    When user navigates to the probate forms tab
    And user click on the "OC 07" form
    Then user verifies the estate name, address, date of death, and file number are auto-filled from estate records

  Scenario: Verify that user can edit the file number on the form and has provision to check "Use 4 digit year" checkbox
    When user enter a valid numeric value into the file number field
    Then the file number should be update and saved automatically
    And user click the Use 4 digit year checkbox
    Then user verifies the file number with four digit year format is displayed in the file number box

  Scenario: Verify that the amount field is editable and only accepts numeric characters
    When the user navigates to the amount field
    Then user verify that able to edit the amount field
    And user verify amount field should accept only numeric characters


  Scenario: Verify that user can add will number on the form
    Then user verify that user can add will number on the form

  Scenario: Verify that 'Claim of' and 'Claimant' fields are synchronized
    When user enters claimantNameX in the Claim of field
    Then the Claimant field should display claimantNameX
    When user clears and enters claimantNameY in the Claimant field
    Then the Claim of field should display claimantNameY

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser