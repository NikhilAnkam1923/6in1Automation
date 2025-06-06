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
    Then user saves entered Estate information for "OC06" form

  Scenario: Verify that the estate name and file number are auto-filled when estate records exist
    When user navigates to the probate forms tab
    And user click on the "OC 06" form
    Then user verifies by default page 1 should be opened and correct estate name and file number are auto-filled

  Scenario: Verify that the file number can be edited and saved without restrictions
    When user enters a valid numeric value into the file number field
    Then the file number should be updated and saved automatically

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser