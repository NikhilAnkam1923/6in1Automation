@6in1 @Smoke @Regression
Feature: 6in1 probate form RW02 Feature

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
    When user opens "Kris Warner" Estate
    And user saves entered Estate Information

  Scenario: Verify RW02 form
    When user navigate to the Probate forms tab
    And user clicks on the "RW 02" Form
     #Verify, correct county name is auto fetched.
    Then user verifies correct county name is fetched from the decedent info
      #Verify, county, estate and aka names are auto populated on the form.
    Then user verifies decedent information is auto populated on the form
     #Verify, the auto populated fields are not editable.
    Then user verifies the auto-populated fields are not Editable
      #Verify, names can be added in aka fields.
    Then user verifies multiple aka names can be added separated by comma


  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser