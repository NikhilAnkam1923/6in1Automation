@6in1 @Smoke @Regression
Feature: 6in1 probate form RW02 Feature

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
    When user opens "Kris Warner" Estate
    And user saves entered Estate information for "RW02" form
    When user navigates to Estate contacts tab
    Then user saves entered Information of all the Estate Contacts for "RW02" form

  Scenario: Verify, correct county name is auto fetched.
    When user navigates to the probate forms tab
    And user click on the "RW 02" form
    Then user verifies correct county name is fetched from the decedent info

  Scenario: Verify, names of fiduciary type of contact is displayed at the top.
    Then user verifies Fiduciary type of contacts are displayed at the top

  Scenario: Verify, if the names exceed the line, contacts are displayed in the attachment.
    Then user verifies when names exceed the line, the contacts are displayed in the attachment

  Scenario: Verify, county, estate and aka names are auto populated on the form.
    Then user verifies decedent information is auto populated on the form

  Scenario: Verify, the auto populated fields are not editable.
    Then user verifies the auto-populated fields of "RW02" form are not editable

  Scenario: Verify, names can be added in aka fields.
    Then user verifies multiple aka names can be added separated by comma

  Scenario: Verify, values can be selected from the dropdown of "Estimate of value of decedents property at death"
    And user selects options from all the dropdowns of 'Estimate of value of decedents property at death'
    Then user verifies All the selected values are retained and auto saved

  Scenario: Verify, amount can be entered in the input fields.
    Then user verifies Amount can be entered in all the fields
    Then user verifies Amount entered in all the fields are auto saved

  Scenario: Verify, total estimated value should display total of 1st and last field only.
    Then user verifies total estimated value is the total of first and last fields only

  Scenario: Verify that checking the "Use Principal Residence" checkbox copies the address details from the "principal residence at" field to the "Real estate in Pennsylvania situated at" field.
    And user checks 'Use Principal Residence' checkbox
    Then user verifies The address from the 'principal residence at' field is copied to the 'Real estate in Pennsylvania situated at' field

  Scenario: Verify that unchecking the checkbox does not clear the "Real estate in Pennsylvania situated at" field.
    Then user verifies unchecking the checkbox does not clear the 'Real estate in Pennsylvania situated at' field

  Scenario: Verify that the form auto-saves after the address is copied via the checkbox.
    Then user verifies the copied address is retained and auto-saved

  Scenario: Verify that the "Real estate in Pennsylvania situated at" field remains editable after copying the address.
    Then user modifies the address in the 'Real estate in Pennsylvania situated at' field
    Then user verifies the modifications are saved successfully

  Scenario: Verify that selecting option A keeps it selected without affecting option B.
    And user checks Option A checkbox
    Then user verifies Option A remains selected, and option B is unaffected

  Scenario: Verify, decedent died date is auto fetched.
    Then user verifies decedent died date is auto fetched

  Scenario: Verify, codicil dates are auto fetched and on updating it, updates the values in decedent tab.
#/** TC is commented cause its getting failed due to system issue**/
#    Then user verifies codicil date values are auto fetched
    Then user updates the codicil dates
    Then user verifies updated codicil dates in form are reflected in the codicil dates in decedent tab

  Scenario: Verify, text can be entered state relevant circumstances and exception on checking exceptions checkbox.
    When user navigates to the probate forms tab
    And user click on the "RW 02" form
    Then user verifies text can be entered in the state relevant circumstances text fields
    And user checks exceptions checkbox from Option A
    Then user verifies the text field is enabled and text can be entered

  Scenario: Verify that selecting option B keeps it selected without affecting option A.
      #Verify that both options A and B can be selected simultaneously.
    And user checks Option B checkbox
    Then user verifies Option B remains selected, and option A is unaffected

  Scenario: Verify that selecting option B enables the beneficiaries' selection at the bottom of page 1.
    Then user verifies the beneficiaries' selection field at the bottom of page 1 is enabled

  Scenario: Verify, multiple beneficiaries can be selected.
    Then user verifies multiple beneficiaries can be added

  Scenario: Verify, bene contacts in the table.
    And user clicks on Accept Button
    Then user verifies correct beneficiary name, relationship and address is displayed in the table

  Scenario: Verify, if the selected contacts are exceed count of 4 then, it should be transferred to attachment.
    Then user verifies if the selected contacts are exceed count of 4 then it should be transferred to attachment

  Scenario: Verify, on checking "Display all heirs on attachment".
    And user checks 'Display ALL heirs on attachment' checkbox
    Then user verifies all the contacts are transferred to attachment

  Scenario:Verify that deselecting option B disables the beneficiaries' selection.
    And user deselects Option B
    Then user verifies the beneficiaries' selection field is disabled

  Scenario: Verify, on page 2 petitioner's name are by default printed on the table.
    Then user verifies on page 2 petitioner's name are by default printed on the table

  Scenario: Verify fees section.
    And user enters values in letters fields
    And user enters data in Other field
    And user adds amount in front of the respective fields
    Then user verifies total is displayed correctly

  Scenario: Verify, attorney can be selected.
    Then user verifies only 1 contact can be selected from the list
    Then user verifies selected attorney contact's information is displayed correctly

  Scenario: Verify, information in decree of the register.
    Then user verifies decree of the register information is displayed correctly

  Scenario: Verify form can be printed in pdf
    When user click on print form button
    Then verify form can be printed in pdf with name as 'Rw02'
    And verify all the fields entered are correctly reflected in the 'Rw02' pdf

  Scenario: Reset the RW02 form
    When user resets the "RW02" form

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser