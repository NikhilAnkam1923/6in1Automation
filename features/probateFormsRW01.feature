@6in1 @Smoke @Regression
Feature: 6in1 probate form RW01 Feature

  @Setup
  Scenario Outline: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "$6in1_url"
    When user login using "<user-email>" and "<password>"
    Then user verifies the Home page
    Examples:
      | user-email                           | password |
      | nikhilankam+14@benchmarkit.solutions | Watch@22 |

  Scenario: Open Estate
    When user opens "William John" Estate
    And user save entered Estate Information

  Scenario: Verify RW01 form
    When user navigates to the Probate forms tab
     #Verify, file no. is displayed at the top of the form.
    And user clicks on the "RW 01" form
    Then user verifies correct file number is displayed at the top of the form
     #Verify, decedent information is displayed in section1 of the form.
    Then user verifies all fields of section 1 displays correct information fetched from the decedent info tab
     #Verify, on clicking section 2 an informative text box appears.
    And user clicks on "Section II"
    Then user verifies on clicking section 2 an informative text box is displayed
     #Verify, in section 2 only 1 checkbox can be checked.
    Then user verifies in section 2 only one checkbox should be able to be checked
     #Verify, on clicking section 3 an informative text box appears.
    And user clicks on "Section III"
    Then user verifies on clicking section 3 an informative text box is displayed
     #Verify, in section 3 only 1 checkbox can be checked.
    Then user verifies in section 3 only one checkbox should be able to be checked
     #Verify, on clicking other checkbox, text area is enabled.
    Then user verifies on clicking 'Other' checkbox, text area is enabled
      #Verify, on clicking section 4 an informative text box appears.
    And user clicks on "Section IV"
    Then user verifies on clicking section 4 an informative text box is displayed
     #Verify, on clicking last name, a side bar is displayed.
    And user click on Last Name field
    Then user verifies a side bar with title 'Select Attorney/Correspondent' is displayed
     #Verify, only 1 contact can be selected.
    And user selects Role as "Fiduciary"
    Then user verifies out of the available roles and contact name, only 1 contact is able to be selected
    And user click on the Proceed button
     #Verify, on selecting the contact its information is displayed in section 4.
    Then user verifies on selecting the contact its information is displayed in section 4
     #Verify, on clicking executor last name, a side bar is displayed.
    And user click on Executor Last Name field
    Then user verifies a side bar with title 'Select Representatives' is displayed
     #Verify, 3 types of selection is available.
    Then user verifies Executor, co-executor, secondary co-executor selection types are displayed
     #Verify, only 1 contact can be dragged and dropped in a each of the type.
    Then user verifies only 1 contact can be dragged and dropped into each selection type
    And user clicks on Accept button
     #Verify, on clicking section 5 an informative text box appears.
    And user clicks on "Section V"
    Then user verifies on clicking section 5 an informative text box is displayed
     #Verify the selected contacts are displayed under executor, co executor and secondary co executor.
    Then user verifies the selected contacts are displayed under executor, co-executor and secondary co-executor

  Scenario: Reset the RW01 form
    When user reset the RW01 form

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser