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

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser