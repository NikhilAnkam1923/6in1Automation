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

  Scenario: User Creates an Estate
    When user click on Create button
    Then user fill first name,last name and SSN details
    And user clicks on Proceed Button
    And user clicks on Create a new estate with the entered name button
    Then user fill decedent basic information
    And user clicks on Next Button
    When user fills Last Address/Domicile Details
    When user fill Life Details
    When user fill the Place of Death details
    And user click on Estate Tab
    And user fill the Estate details

  Scenario: Verify RW01 form
    When user navigates to the probate forms tab
     #Verify, file no. is displayed at the top of the form.
    And user click on the "RW 01" form
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
      #Verify, on clicking section 4 an informative text box appears.
    Then user verifies on clicking section 4 an informative text box is displayed

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser