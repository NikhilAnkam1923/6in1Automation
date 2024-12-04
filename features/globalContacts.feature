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

  @Smoke
  Scenario: User verify user is on the Global Contact Creation page
    When user clicks on the "Global Contact" button
    Then user verifies the "Global Contacts" page
    When user clicks on the "Create" button
    Then user verifies the "Global Contact Creation" page

  Scenario Outline: Verify user enters first and last name then clicks Create Individual Contact and lands on the Individual Contact page with pre-filled fields
    When user enters "<firstName>" as the first name and "<lastName>" as the last name
    When user clicks on the "Create Individual Contact" button
    Then user verifies the "Individual Global Contact Creation" page
    And "firstName" field is pre-filled with "<firstName>"
    And "lastName" field is pre-filled with "<lastName>"
    Examples:
      | firstName | lastName |
      | Amy       | Jack     |

  Scenario: Verify that a user can save a contact with all required and optional fields filled
    When user enters all required and optional fields
      | middleName | maidenName | entityName | emailAddress      | PTIN      | PINeFile   | BarID      | CAF     | AddressLine1 | AddressLine2 | Zip   |
      | York       | mj         | AMY Corp   | amyjack@email.com | P76543363 | 7687462487 | 7355653750 | 5764654 | DUCK colony  | IAB Market   | 50011 |
    And user enters SSN,EIN,Phone Number,workPhone and fax details
      | ssn         | ein        | phoneNumber    | workPhone      | fax            |
      | 123-45-6789 | 12-3456789 | (123) 456-7880 | (123) 456-7870 | (123) 456-7897 |
    When user clicks on the "Save" button
    And user verifies that a confirmation message "Contact details have been successfully saved." is displayed

  Scenario Outline: Verify that city, state and county are automatically fetched on entering zip
    When user enter "<zip>" in "Zip" Field
    Then Verify the city "<city>", state "<state>", and county "<country>" are automatically fetched
    Examples:
      | zip   | city      | state    | country |
      | 40007 | Bethlehem | Kentucky | Henry   |

  @Setup
  Scenario: SETUP: Close Browser
    Then User close browser