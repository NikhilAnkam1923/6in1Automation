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

  Scenario: Create the contact for Entity Global Contact
    When user navigate to "Global Contact"
    And user "Create" global contact of "Entity Global Contact"
    Then user fills all the details for "Entity Global Contact"
    Then user save the global contact
    And user verifies global contact of "Entity Global Contact" is saved successfully

  Scenario: Edit the contact for Individual Global Contact
    When user navigate to "Global Contact"
    And user "Create" global contact of "Individual Global Contact"
    Then user fills all the details for "Individual Global Contact"
    Then user save the global contact
    And user verifies global contact of "Individual Global Contact" is saved successfully
    And user "Edit" global contact of "Individual Global Contact"
    And user verifies global contact of "Individual Global Contact" is saved successfully

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
    And user verifies global contact of "Individual Global Contact" is saved successfully

  Scenario: Verify user enters entity name then clicks Create Entity Contact and lands on the Entity Contact page with pre-filled fields
    When user navigate to "Global Contact"
    And user "Create" global contact of "Entity Global Contact"
    And Entity Name fields is pre-filled

  Scenario: Verify Select & Proceed button is enabled only after selecting a radio button
    When user navigate to "Global Contact"
    When user clicks on the "Create" button
    When user enters "Joe" as the first name and "Root" as the last name
    Then user navigates to the page with the records
    Then user verifies the "Contact (Select or Create New)" page
    And user selects a radio button for a record
    Then user verifies the "Select & Proceed" button is enabled

  Scenario: verify user authorization for Licensed
    When user navigate to "Global Contact"
    And user verifies authorization for "Licensed"

  Scenario: verify user authorization for Reviewer
    When user navigate to "Global Contact"
    And user verifies authorization for "Reviewer"

  Scenario: verify user authorization for View Only
    When user navigate to "Global Contact"
    And user verifies authorization for "View Only"


# Gaurav Scenarios -
  Scenario Outline: Verify that city, state and county are automatically fetched on entering zip
    When user clicks on the "Create" button
    And user verifies the "Global Contact Creation" page
    And user enters "<firstName>" as the first name and "<lastName>" as the last name
    And user clicks on the "Create Individual Contact" button
    And user verifies the "Individual Global Contact Creation" page
    And "firstName" field is pre-filled with "<firstName>"
    And "lastName" field is pre-filled with "<lastName>"
    And user enter "<zip>" in "Zip" Field
    Then Verify the city "<city>", state "<state>", and county "<country>" are automatically fetched
    Examples:
      | firstName | lastName | zip   | city      | state    | country |
      | Devis36   | Karl36   | 40007 | Bethlehem | Kentucky | Henry   |


  Scenario Outline: Verify that the system validates the EIN and SSN formats correctly
    When user enters SSN and EIN details
      | ssn         | ein        |
      | 123-45-6789 | 12-3456789 |
    And user enter "<AddressLine1>" in "Address Line 1" Field
    Then user verifies that a confirmation message "Contact details have been successfully saved." is displayed
    Examples:
      | AddressLine1 |
      | Sukovia      |

  Scenario Outline: Verify that the system trims leading and trailing spaces from text input fields
    When user clicks on the "Create" button
    And user verifies the "Global Contact Creation" page
    And user enters "  <firstName>" as the first name and "  <lastName>  " as the last name
    And user clicks on the "Create Individual Contact" button
    And user verifies the "Individual Global Contact Creation" page
    And "firstName" field is pre-filled with "<firstName>"
    And "lastName" field is pre-filled with "<lastName>"
    And user enter "  <AddressLine1>  " in "Address Line 1" Field
    And user enter "<zip>" in "Zip" Field
    And user clicks on the "Save" button
    Then user verifies that a confirmation message "Contact details have been successfully saved." is displayed
    And user is on first page
    Examples:
      | firstName | lastName | AddressLine1    | zip   |
      | Smith3    | John3    | 123 Main Street | 40007 |

  Scenario Outline: Verify name fields can be updated
    When user clicks on Name: "Karl15, Devis15" from Global Contact List with Type as "Individual"
    And Verify user is on Edit Contact Page
    And user enter "<firstName>" in "First Name" Field
    And user enter "<middleName>" in "Middle Name" Field
    And user enter "<lastName>" in "Last Name" Field
    And user click on "Save" Button
    Then user verifies that a confirmation message "Contact details have been successfully saved." is displayed
    And user verifies updated values "<lastName>, <firstName>" is reflected in Global Contact List
    Then user clicks on Name: "<lastName>, <firstName>" from Global Contact List with Type as "Individual"
    And "firstName" field is pre-filled with "<firstName>"
    And "middleName" field is pre-filled with "<middleName>"
    And "lastName" field is pre-filled with "<lastName>"
    And user click on "Cancel" Button
    And user is on first page
    Examples:
      | firstName | middleName | lastName |
      | John13    | Smith13    | Karl23   |


  Scenario: Verify SSN added for the contact having empty SSN
    When user clicks on Name: "Karl15, Devis16" from Global Contact List with Type as "Individual"
    And user enters SSN details
      | ssn         |
      | 123-45-6782 |
    And user click on "Save" Button
    Then user verifies that a confirmation message "Contact details have been successfully saved." is displayed
    And user is on first page

  Scenario Outline: Verify display result on entity name
    When user clicks on the "Create" button
    Then user verifies the "Global Contact Creation" page
    When user enters Entity Name: "<entityName>"
    And user clicks on the "Create Entity Contact" button
    Then user verifies all the matching records are displayed for Entity Name: "<entityName>"
    Then Verify background color of the contact type: "<contactType>"
    Then Verify radio buttons are available for all the contacts
    Then Verify "Create Entity Contact" button is available
    And user click on "Close" Button
    Examples:
      | entityName | contactType |
      | mark       | Entity      |

  Scenario Outline: Verify that city, state and county are automatically fetched on entering zip
    When user enters "<firstName>" as the first name and "<lastName>" as the last name
    When user clicks on the "Create Individual Contact" button
    Then user verifies the "Individual Global Contact Creation" page
    When user enter "<zip>" in "Zip" Field
    Then Verify the city "<city>", state "<state>", and county "<country>" are automatically fetched
    Examples:
      | firstName | lastName | zip   | city      | state    | country |
      | Amy       | Jack     | 40007 | Bethlehem | Kentucky | Henry   |


  Scenario: Verify, a contact can be selected from the list.
    When user navigate to "Global Contact"
    When user clicks on the "Create" button
    When user enters "Nikhil" as the first name and "Ankam" as the last name
    Then user navigates to the page with the records
    Then user verifies the "Contact (Select or Create New)" page
    And user selects a radio button for a record
    When user clicks on the Select & Proceed button

  Scenario Outline: Verify all the inputs are by default trimmed while searching both the type of contacts
    When user enters "  <firstName>" as the first name and "  <lastName>  " as the last name
    And user clicks on the "Create Individual Contact" button
    Then user verifies all the matching records are displayed for Contact Name: "<lastName>, <firstName>"
    And user click on "Close" Button
    And user verifies the "Global Contact Creation" page
    Then user enters Entity Name: "    <entityName>    "
    And user clicks on the "Create Entity Contact" button
    Then user verifies all the matching records are displayed for Entity Name: "<entityName>"
    Examples:
      | firstName | lastName | entityName |
      | Smith3    | John3    | mark       |

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser