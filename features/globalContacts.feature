@6in1 @Smoke @Regression
Feature: 6in1 Global Contacts Feature

  @Setup
  Scenario Outline: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "https://pumashoes.benchmarkits.in"
    When user login using "<user-email>" and "<password>"
    Then user verifies the Home page
    Examples:
      | user-email                           | password |
      | nikhilankam+11@benchmarkit.solutions | Watch@22 |

  Scenario: User verify user is on the Global Contact Creation page
    When user navigate to "Global Contact"
    #verify landed on "Global Contacts" page
    Then user verifies the Global Contacts page
    #verify user authorization for "Licensed" user
    Then user verifies authorization for "Licensed"
    When user clicks on the Create button
    Then user verifies the Global Contact Creation page

  Scenario: Create and then Edit the same contact for Individual Global Contact
    And user "Create" global contact of "Individual Global Contact"
    #Verify first and last name fields are pre-filled
    And First Name and Last Name fields are pre-filled
    #verify validation error messages display for the required fields
    And user attempts to save the global contact without filling the required fields
    Then user should see validation error messages for the required fields
    Then user fills all the details for "Individual Global Contact"
    #Verify that validation error messages are removed when required fields are corrected
    Then user verify all the error messages are removed
    And user click on Next button
    Then user verifies global contact is created successfully
    #Verify, individual contact page is opened in edit mode and all details are auto saved.
    And user switched to edit mode
    Then user fills entity and contact information
    #validates the EIN and SSN formats
    Then Verify that the system validates the EIN and SSN formats correctly
    When user navigate to "Global Contact"
    Then user verifies all details of Individual Global Contact are auto-saved
    #Verify, address can be added.
    #verify that city, state, and county are automatically fetched
    And user clicks on Mange Address button
    And user fills Address information
    Then verify that city, state, and county are automatically fetched
    And user click on Save button
    Then user verifies address information saved successfully
    #Verify, multiple addresses can be added.
    Then user verifies multiple addresses can be added
    #Verify, added address can be edited.
    Then user verifies address can be edited and reflected the changed address
    And user close the Address bar
    #Verify, added address's list is displayed correctly.
    Then user verifies added addresses list displayed correctly
    #Verify user can able to edit the created "Individual Global Contact"
    When user navigate to "Global Contact"
    And user "Edit" global contact of "Individual Global Contact"

  Scenario: Create the contact for Entity Global Contact
    When user navigate to "Global Contact"
    And user "Create" global contact of "Entity Global Contact"
    #Verify Entity Name fields are pre-filled
    And Entity Name fields is pre-filled
    Then user fills all the details for "Entity Global Contact"
    And user click on Next button
    Then user verifies global contact is created successfully
    #Verify, entity contact page is opened in edit mode and all details are auto saved.
    And user switched to edit mode
    Then user fills Contact Person's Details and contact information
    When user navigate to "Global Contact"
    Then user verifies all details of Entity Global Contact are auto-saved
    And user clicks on Mange Address button
    And user fills Address information
    Then verify that city, state, and county are automatically fetched
    And user click on Save button
    Then user verifies address information saved successfully
    #Verify, multiple addresses can be added.
    Then user verifies multiple addresses can be added
    #Verify, added address can be edited.
    Then user verifies address can be edited and reflected the changed address
    And user close the Address bar
    #Verify, added address's list is displayed correctly.
    Then user verifies added addresses list displayed correctly

  Scenario: Attempt to create a duplicate entity contact with the same EIN
    When user navigate to "Global Contact"
    And user "Create" global contact of "Entity Global Contact"
    Then user enters already existed EIN
    Then user click on Next button
    And user should see an error message for duplicate EIN

  Scenario: Verify Select & Proceed button is enabled after selecting a radio button
    When user navigate to "Global Contact"
    When user clicks on the Create button
    When user enters "Joe" as the first name and "Root" as the last name
    Then user navigates to the page with the records
    Then user verifies the Contact (Select or Create New) page
    #verifies "Create Individual Contact" button availability
    And user verifies "Create Individual Contact" button is available
    And user selects a radio button for a record
    #verifies the "Select & Proceed" button is enable
    Then user verifies the "Select & Proceed" button is enabled
    And user click on the Close button

  Scenario: Verify that the system trims leading and trailing spaces from text input fields
    And user "Create" global contact of "Individual Global Contact" with leading and trailing spaces
    And First Name and Last Name fields are pre-filled
    Then user fills all the details for "Individual Global Contact" with spaces
    And user click on Next button
    Then user verifies global contact is created successfully

  Scenario: Verify display result on entity name
    When user navigate to "Global Contact"
    When user clicks on the Create button
    And user "Create" global contact of "Entity Global Contact" with leading and trailing spaces
    Then user verifies all the matching records are displayed for Entity Global Contact
    Then user verifies background color of the "Entity Global Contact"
    Then user verifies radio buttons are available for all the contacts
    Then user verifies "Create Entity Contact" button is available
    And user clicks on the Close button

  Scenario Outline: Verify user authorization for "View Only" user
    When user logged out from the application
    And user go to application "https://delltab.benchmarkits.in"
    When user login using "<user-email>" and "<password>"
    When user navigate to "Global Contact"
    Then user verifies authorization for "View Only"
    Examples:
      | user-email                           | password |
      | nikhilankam+13@benchmarkit.solutions | Watch@22 |

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser
