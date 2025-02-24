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
    And user saves entered Estate information for "RW04" form

  Scenario: Verify, correct title is displayed on the form's header.
    When user navigates to the probate forms tab
    And user click on the "RW 04" form
    And user selects the aka checkbox
    Then user verifies correct estate, county and AKA names are displayed under header

  Scenario: Verify, county, and aka names are auto populated on the form.
    Then user verifies county and aka names are auto populated on the form

  Scenario: Verify, correct estate's name is displayed on the form.
    Then user verifies correct estate's name is displayed across the form

  Scenario: Verify, name of the decedent should be auto populated from the form.
    Then user verifies decedent name on the form is auto populated from the estate

  Scenario: Verify, witnesses  name, address and signature should be editable and in yellow background.
    Then user verifies witnesses name, address and signature fields of "RW04" form are editable and in yellow background

  Scenario: Verify, names can be entered in witness fields.
    Then user verifies names can be entered in both the witness fields and reflected below in signature fields

  Scenario: Verify, names updated from signature are reflected in witness names fields.
    Then user verifies names updated in signature fields of "RW04" form are reflected in the witness fields

  Scenario: Verify, text can be entered in address, city, zip fields.
    Then user verifies both the address, city, zip fields of "RW04" form accept correct text

  Scenario: Verify, form is auto saved.
    And user click on the "RW 03" form
    And user click on the "RW 04" form
    Then user verifies all the input fields in the "RW04" form are auto saved

  Scenario: Verify form can be printed in pdf
    When user click on print form button
    Then verify form can be printed in pdf with name as 'Rw04'
    And verify all the fields entered are correctly reflected in the 'Rw04' pdf

  Scenario: Reset the RW04 form
    When user resets the "RW04" form

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser