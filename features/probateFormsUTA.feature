@6in1 @Smoke @Regression
Feature: 6in1 probate form UTA Feature

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "$6in1_url"
    When user login using "nikhilankam@benchmarkit.solutions" and "Bits@1234"
    Then user verifies the Home page

  Scenario: Open Estate
    When user opens "Watt, Sara Arik Jr." Estate
    Then user saves entered Estate information for "UTA" form

  Scenario: Verify that selecting "Use Decedent Name" checkbox updates "The Settlor of the Trust was:" field dynamically.
    When user navigates to the probate forms tab
    And user click on the "NTA" form
    And user selects the "Use Decedent Name" checkbox
    Then user verifies The Settlor of the Trust was: field display decedent's name

  Scenario: Verify that the "Name and address" field allows user to select beneficiaries.
    When user click on 'Name and Address' field
    Then user selects multiple beneficiaries from sidebar
    And user verifies form is repeated depending on the number of beneficiaries selected

  Scenario: Verify that the sidebar appears and allows attorney selection.
    Then user verifies for "UTA" form a sidebar appears and attorney can be selected

  Scenario: Verify that correct attorney details are fetched in the subsequent fields.
    Then user verifies Counsel details are populated correctly for "UTA" form

  Scenario: Verify that 'Date of Notice' field is editable.
    Then user verifies the 'Date of Notice' field is editable

  Scenario: Reset the UTA form
    When user resets the "UTA" form

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser