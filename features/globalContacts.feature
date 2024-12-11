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
    And user clicks on the "Save" button
    Then user verifies global contact of "Individual Global Contact" is saved successfully

  Scenario Outline: Verify name fields can be updated
    **
    When user clicks on Name: "Karl36, Devis36" from Global Contact List with Type as "Individual"
    And Verify user is on Edit Contact Page
    And user enter "<firstName>" in "First Name" Field
    And user enter "<middleName>" in "Middle Name" Field
    And user enter "<lastName>" in "Last Name" Field
    And user click on "Save" Button
    Then user verifies that a confirmation message "Contact details have been successfully saved." is displayed
  **
    And user verifies updated values "<lastName>, <firstName>" is reflected in Global Contact List
    Then user clicks on Name: "<lastName>, <firstName>" from Global Contact List with Type as "Individual"
    And "firstName" field is pre-filled with "<firstName>"
    And "middleName" field is pre-filled with "<middleName>"
    And "lastName" field is pre-filled with "<lastName>"
    And user click on "Cancel" Button
    And user is on first page
    Examples:
      | firstName | middleName   | lastName |
      | John15     | Smith15     | Karl25   |

  Scenario: Verify SSN added for the contact having empty SSN
    When user clicks on Name: "Karl15, Devis16" from Global Contact List with Type as "Individual"
    And user enters SSN details
      | ssn         |
      | 123-42-6732 |
    And user click on "Save" Button
    Then user verifies that a confirmation message "Contact details have been successfully saved." is displayed
    And user is on first page

  Scenario Outline: Verify all the other fields of individual details can be updated
    When user clicks on Name: "Karl12, Devis17" from Global Contact List with Type as "Individual"
    And user enters data in all the individual details fields
      | firstName | middleName | lastName | maidenName |
      | Cammer    | York       | Will     | CV         |
    And user select "Suffix" as "<Suffix>"
    And user click on "Save" Button
    Then user verifies that a confirmation message "Contact details have been successfully saved." is displayed
    And user is on first page
    Then user verifies updated values "<lastName>, <firstName>" is reflected in Global Contact List
    Then user clicks on Name: "<lastName>, <firstName>" from Global Contact List with Type as "Individual"
    And "firstName" field is pre-filled with "<firstName>"
    And "middleName" field is pre-filled with "<middleName>"
    And "lastName" field is pre-filled with "<lastName>"
    And "maidenName" field is pre-filled with "<maidenName>"
    And verify "<Suffix>" option is selected from Suffix Dropdown
    Then user enters data in all the individual details fields
      | firstName  | middleName | lastName   | maidenName |
      | Devis17    | Harry      | Karl12     | DK         |
    And user select "Suffix" as "Atty."
    And user click on "Save" Button
    Then user verifies that a confirmation message "Contact details have been successfully saved." is displayed
    And user is on first page
    Then user verifies updated values "Karl12, Devis17" is reflected in Global Contact List
    And user is on first page
    Examples:
      | Suffix | firstName | middleName | lastName | maidenName |
      |   Sr.  | Cammer    | York       | Will     | CV         |

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
      |   mark     | Entity      |


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
      | firstName   | lastName  | entityName |
      | Smith3      | John3     | mark       |

  @Setup
  Scenario: SETUP: Close Browser
    Then User close browser