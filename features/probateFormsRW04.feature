@6in1 @Smoke @Regression
Feature: 6in1 probate form RW04 Feature

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
    And user saves entered Estate information

  Scenario: Verify, correct title is displayed on the form's header.
    When user navigate to the Probate forms Tab
    And user click on the "RW 04" Form
    And user selects the aka checkbox
    Then user verifies correct estate, county and AKA names are displayed under header

  Scenario: Verify, county, and aka names are auto populated on the form.
    Then user verifies county and aka names are auto populated on the form

  Scenario: Verify, correct estate's name is displayed on the form.
    Then user verifies correct estate's name is displayed across the form

  Scenario: Verify, name of the decedent should be auto populated from the form.
    Then user verifies decedent name on the form is auto populated from the estate

  Scenario: Verify, witnesses  name, address and signature should be editable and in yellow background.
    Then user verifies witnesses name, address and signature fields are Editable and in yellow background

  Scenario: Verify, names can be entered in witness fields.
    Then user verifies names can be entered in both the witness fields and reflected below in signature fields

  Scenario: Verify, names updated from signature are reflected in witness names fields.
    Then user verifies names updated in Signature fields are reflected in the Witness fields


  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser