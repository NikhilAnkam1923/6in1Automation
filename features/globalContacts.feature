@6in1 @Smoke @Regression
Feature: 6in1 Global Contacts Feature

  @Setup
  Scenario Outline: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "$6in1_url"
    When user login using "<user-email>" and "<password>"
    Then user verifies the "Home" page
    Examples:
      | user-email                           | password |
      | nikhilankam+11@benchmarkit.solutions | Watch@22 |

  Scenario: User verify user is on the Global Contact Creation page
    When user navigate to "Global Contact"
    Then user verifies the "Global Contacts" page
    When user clicks on the "Create" button
    Then user verifies the "Global Contact Creation" page

  Scenario: Verify first and last name fields are pre-filled
    When user navigate to "Global Contact"
    And user "Create" global contact of "Individual Global Contact"
    And First Name and Last Name fields are pre-filled

  Scenario: Create and then Edit the same contact for Individual Global Contact
    When user navigate to "Global Contact"
    And user "Create" global contact of "Individual Global Contact"
    Then user fills all the details for "Individual Global Contact"
    Then user save the global contact
    And user verifies global contact saved successful message
    And user "Edit" global contact of "Individual Global Contact"
    And user verifies global contact saved successful message

  Scenario: Create the contact for Entity Global Contact
    When user navigate to "Global Contact"
    And user "Create" global contact of "Entity Global Contact"
    Then user fills all the details for "Entity Global Contact"
    Then user save the global contact
    And user verifies global contact saved successful message

  Scenario: Attempt to create a duplicate entity contact with the same EIN
    When user navigate to "Global Contact"
    And user "Create" global contact of "Entity Global Contact"
    Then user enters already existed EIN
    Then user save the global contact
    And user should see an error message for duplicate EIN

  Scenario: Verify that validation error messages are removed when required fields are corrected
    When user navigate to "Global Contact"
    And user "Create" global contact of "Individual Global Contact"
    And user attempts to save the global contact without filling the required fields
    Then user should see validation error messages for the required fields
    When user fills in the previously empty required fields for "Individual Global Contact"
    Then user save the global contact
    And user verifies global contact saved successful message

  Scenario: Verify Entity Name fields are pre-filled
    When user navigate to "Global Contact"
    And user "Create" global contact of "Entity Global Contact"
    And Entity Name fields is pre-filled

  Scenario: Verify Select & Proceed button is enabled after selecting a radio button
    When user navigate to "Global Contact"
    When user clicks on the "Create" button
    When user enters "Joe" as the first name and "Root" as the last name
    Then user navigates to the page with the records
    Then user verifies the "Contact (Select or Create New)" page
    Then Verify "Create Individual Contact" button is available
    And user selects a radio button for a record
    Then user verifies the "Select & Proceed" button is enabled

  Scenario Outline: verify user authorization for "<userType>"
    When user navigate to "Global Contact"
    Then user verifies authorization for "<userType>"
    Examples:
      | userType  |
      | Licensed  |
      | Reviewer  |
      | View Only |

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser