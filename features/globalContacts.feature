@6in1 @Smoke @Regression
Feature: 6in1 Global Contacts Feature

  @Setup
  Scenario Outline: SETUP:Verify that a user can save a entity contact with all required and optional fields filled
    Given User launched "chrome"
    And user go to application "https://pumashoes.benchmarkits.in"
    When user login using "<user-email>" and "<password>"
    Then user verifies the "Home" page
    Examples:
      | user-email                           | password | userType |
      | nikhilankam+11@benchmarkit.solutions | Watch@22 | Licensed |

  Scenario Outline: Create the contact for <contactType>
    When user navigate to "<tab>"
    And user "<action>" global contact of "<contactType>"
    Then user fills all the details for "<contactType>"
    Then user save the global contact
    And user verifies global contact of "<contactType>" is saved successfully
    And user logged out from the application
    Examples:
      | tab            | action | contactType               |
      | Global Contact | Create | Individual Global Contact |
      | Global Contact | Create | Entity Global Contact     |
      | Global Contact | Edit   | Individual Global Contact |

  Scenario Outline:verify user authorization for <userType>
    When user navigate to "<tab>"
    And user verifies authorization for "<userType>"
    Examples:
      | tab            | userType  |
      | Global Contact | Licensed  |
      | Global Contact | Reviewer  |
      | Global Contact | View Only |

  Scenario Outline: Attempt to create a duplicate entity contact with the same EIN
    When user navigate to "<tab>"
    And user "<action>" global contact of "<contactType>"
    And user fills all the details for "<contactType>"
    Then user save the global contact
    Then user should see an error message for duplicate EIN
    Examples:
      | tab            | action | contactType               |
      | Global Contact | Create | Individual Global Contact |

  Scenario Outline: Verify that validation error messages are removed when required fields are corrected
    When user navigate to "<tab>"
    And user "<action>" global contact of "<contactType>"
    And user attempts to save the global contact without filling the required fields
    Then user should see validation error messages for the required fields
    When user fills in the previously empty required fields for "<contactType>"
    Then user save the global contact
    And user verifies global contact of "<contactType>" is saved successfully
    Examples:
      | tab            | action | contactType               |
      | Global Contact | Create | Individual Global Contact |

  @Smoke
  Scenario Outline: Verify user enters first and last name then clicks Create Individual Contact and lands on the Individual Contact page with pre-filled fields
    When user navigate to "<tab>"
    And user "<action>" global contact of "<contactType>"
    And "firstName" field is pre-filled with "<firstName>"
    And "lastName" field is pre-filled with "<lastName>"
    Examples:
      | firstName | lastName | tab            | action | contactType               |
      | Amy       | Jack     | Global Contact | Create | Individual Global Contact |

  Scenario Outline: Verify user enters entity name then clicks Create Entity Contact and lands on the Entity Contact page with pre-filled fields
    When user navigate to "<tab>"
    Then user enters "<entityName>" as the entity name
    And user "<action>" global contact of "<contactType>"
    And "entityName" field is pre-filled with "<entityName>"
    Examples:
      | entityName | tab            | action | contactType            |
      | highmark   | Global Contact | Create | Entity  Global Contact |

  @Smoke
  Scenario Outline: Verify that city, state and county are automatically fetched on entering zip
    When user enters "<firstName>" as the first name and "<lastName>" as the last name
    When user clicks on the "Create Individual Contact" button
    Then user verifies the "Individual Global Contact Creation" page
    When user enter "<zip>" in "Zip" Field
    Then Verify the city "<city>", state "<state>", and county "<country>" are automatically fetched
    Examples:
      | firstName | lastName | zip   | city      | state    | country |
      | Amy       | Jack     | 40007 | Bethlehem | Kentucky | Henry   |

  Scenario Outline: Verify Select & Proceed button is enabled only after selecting a radio button
    When user navigate to "<tab>"
    When user enters "<firstName>" as the first name and "<lastName>" as the last name
    Then user navigates to the page with the records
    Then user verifies the "Contact (Select or Create New)" page
    And user selects a radio button for a record
    Then user verifies the "Select & Proceed" button is enabled
    Examples:
      | firstName | lastName | tab    |
      | Nikhil    | Ankam    | Create |

  @Setup
  Scenario:SETUP: Close Browser
    Then User close browser