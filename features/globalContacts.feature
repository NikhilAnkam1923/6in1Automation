@6in1 @Smoke @Regression
Feature: 6in1 Global Contacts Feature

  @Setup
  Scenario Outline: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "$6in1_url"
    When user login using "<user-email>" and "<password>"
    Then user verifies the "Home" page
    Examples:
      | user-email                           | password   |
      | nikhilankam+11@benchmarkit.solutions | Watch@22   |

  @Smoke
  Scenario: User verify user is on the Global Contact Creation page
    When user navigate to "Global Contact"
    Then user verifies the "Global Contacts" page
    When user clicks on the "Create" button
    Then user verifies the "Global Contact Creation" page

  Scenario: Verify user enters first and last name then clicks Create Individual Contact and lands on the Individual Contact page with pre-filled fields
    When user navigate to "Global Contact"
    And user "Create" global contact of "Individual Global Contact"
    And First Name and Last Name fields are pre-filled

  Scenario: Create the contact for Individual Global Contact
    When user navigate to "Global Contact"
    And user "Create" global contact of "Individual Global Contact"
    Then user fills all the details for "Individual Global Contact"
    Then user save the global contact
    And user verifies global contact of "Individual Global Contact" is saved successfully

  Scenario: Edit the contact for Individual Global Contact
    When user navigate to "Global Contact"
    And user "Create" global contact of "Individual Global Contact"
    Then user fills all the details for "Individual Global Contact"
    Then user save the global contact
    And user verifies global contact of "Individual Global Contact" is saved successfully
    And user "Edit" global contact of "Individual Global Contact"
    And user verifies global contact of "Individual Global Contact" is saved successfully
#    And user logged out from the application

  Scenario: Verify that city, state and county are automatically fetched on entering zip
    When user navigate to "Global Contact"
    And user "Create" global contact of "Individual Global Contact"
    And First Name and Last Name fields are pre-filled
    And user enters data in Zip Field
    Then verify that city, state, and county are automatically fetched

  Scenario: Verify that the system validates the EIN and SSN formats correctly
    When user enters SSN and EIN details
    And user enters data in Address Line 1 Field
    Then user verifies global contact of "Individual Global Contact" is saved successfully

  Scenario: Verify that the system trims leading and trailing spaces from text input fields
    When user navigate to "Global Contact"
    And user "Create" global contact of "Individual Global Contact" with leading and trailing spaces
    And First Name and Last Name fields are pre-filled
    And user enters data in Address Line 1 Field
    And user enters data in Zip Field
    And user save the global contact
    Then user verifies global contact of "Individual Global Contact" is saved successfully

  Scenario: Verify name fields can be updated
    When user navigate to "Global Contact"
    When user edit contact from Global Contact List
    And user enter data in name fields
    And user save the global contact
    Then user verifies global contact of "Individual Global Contact" is saved successfully
    And user verifies updated values are reflected in Global Contact List
    And Name fields are pre-filled


  Scenario: Verify SSN added for the contact having empty SSN
    When user navigate to "Global Contact"
    When user edit contact from Global Contact List
    And user enters SSN details
    And user save the global contact
    Then user verifies global contact of "Individual Global Contact" is saved successfully

  Scenario: Verify all the other fields of individual details can be updated
    Given user navigate to "Global Contact"
    When user edit contact from Global Contact List
    And user enter data in name fields
    And user selects Suffix
    And user save the global contact
    Then user verifies global contact of "Individual Global Contact" is saved successfully
    And user navigate to "Global Contact"
    Then user verifies updated values are reflected in Global Contact List
    And Name fields are pre-filled
    And user verifies Suffix is selected from Dropdown

  Scenario: Verify display result on entity name
    When user navigate to "Global Contact"
    And user "Create" global contact of "Entity Global Contact"
    Then user verifies all the matching records are displayed for Entity Global Contact
    Then user verifies background color of the contact type
    Then user verifies radio buttons are available for all the contacts
    Then user verifies Create Entity Contact button is available

  Scenario: Verify all the inputs are by default trimmed while searching both the type of contacts
    When user navigate to "Global Contact"
    And user "Create" global contact of "Individual Global Contact" with leading and trailing spaces
    Then user verifies all the matching records are displayed for Individual Global Contact
    And user click on "Close" Button
    And user navigate to "Global Contact"
    And user "Create" global contact of "Entity Global Contact" with leading and trailing spaces
    Then user verifies all the matching records are displayed for Entity Global Contact

  @Setup
  Scenario: SETUP: Close Browser
    Then User close browser