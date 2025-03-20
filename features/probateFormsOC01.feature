@6in1 @Smoke @Regression
Feature: 6in1 probate form OC01 Feature

  @Setup
  Scenario Outline: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "https://benchmark1.benchmarkits.in/"
    When user login using "<user-email>" and "<password>"
    Then user verifies the Home page
    Examples:
      | user-email                               | password  |
      | bhaveshkulkarni+13@benchmarkit.solutions | Bits@1234 |

  Scenario: Open Estate
    When user opens "William John" Estate
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



  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser