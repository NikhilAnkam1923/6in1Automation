@6in1 @Smoke @Regression
Feature: 6in1 Global Contacts Feature

  @Setup
  Scenario Outline: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "$6in1_url"
    When user login using "<user-email>" and "<password>"
    Then user verifies the Home page
    Examples:
      | user-email                           | password |
      | nikhilankam+11@benchmarkit.solutions | Watch@22 |


  Scenario: user verify after filling decedent information clicking on next button other details are opened
    When user clicks on the Create button
    Then user fills the first name,last name and SSN details
    And user click on Proceed button
    Then user see the Create a new estate with the entered name button for new user
    And user click on Create a new estate with the entered name button for new user
    Then user fills decedent basic information for new user
    And user click on Next button
    Then verify Decedent details page is opened

  Scenario: Verify validations for all the fields under last address/domicile
    When user fill Last Address/Domicile details
    Then user verify validations for all the fields
    And user verifies Township and Borough radio buttons toggle correctly



  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser
