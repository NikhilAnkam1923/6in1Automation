@6in1 @Smoke @Regression
Feature: 6in1 probate form RW05 Feature

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
    When user opens "William John" Estate
    And user saves entered Estate information for "RW05" form

  Scenario: Verify, county, estate and aka names are auto populated on the form.
    When user navigates to the probate forms tab
    And user click on the "RW 05" form
    Then user selects the aka checkbox
    And user verifies the county, estate and aka names are auto-populated on the "RW05" form

  Scenario: Verify, the auto-populated fields are not editable.
    Then user verifies the auto-populated fields of "RW05" form are not editable

  Scenario: Verify, witness's name is not auto populated and the fields are empty.
    Then user verifies witness's name is not auto populated and the fields of "RW05" form are empty

  Scenario: Verify, witnesses  name, address and signature should be editable and in yellow background.
    Then user verifies witnesses name, address and signature fields of "RW05" form are editable and in yellow background

  Scenario: Verify, names can be entered in witness fields.
    Then user verifies witness fields of "RW05" form accept names and same names are reflected in signature fields

  Scenario: Verify, names updated from signature are reflected in witness names fields.
    Then user verifies names updated in signature fields of "RW05" form are reflected in the witness fields

  Scenario: Verify, text can be entered in address, city, zip fields.
    Then user verifies both the address, city, zip fields of "RW05" form accept correct text

  Scenario: Verify, on checking notary checkbox, notary section displays.
    When user check the notary checkbox
    Then user verifies notary block is displayed

  Scenario: Verify, form is auto saved.
    And user click on the "RW 04" form
    And user click on the "RW 05" form
    Then user verifies all the input fields in the "RW05" form are auto saved

  Scenario: Verify form can be printed in pdf
    When user click on print form button
    Then verify form can be printed in pdf with name as 'Rw05'
    And verify all the fields entered are correctly reflected in the 'Rw05' pdf

  Scenario: Reset the RW05 form
    When user resets the "RW05" form

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser

