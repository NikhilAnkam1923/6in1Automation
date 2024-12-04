@6in1 @Smoke @Regression
Feature: 6in1 Global Contacts Feature

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "$6in1_url"

  @Smoke
  Scenario Outline: User verify login with valid credentials
    When user login using "<user-email>" and "<password>"
    Then user verify Landing page
    And user click on Global Contact tab
    Then verify user is on the Global Contact page
    When user click on Create button
    Then verify user is on the Global Contact Creation page
#    And user logged out from the application
    Examples:
      | user-email                           | password   |
      | nikhilankam+11@benchmarkit.solutions | Watch@22   |

  Scenario Outline: Verify user enters first and last name, clicks "Create Individual Contact," and lands on the Individual Contact page with pre-filled fields
    When user enters "<firstName>" as the first name and "<lastName>" as the last name
    And user clicks on the Create Individual Contact button
    Then user lands on the Individual Global Contact Creation page
    And "contact.firstName" field is pre-filled with "<firstName>"
    And "contact.lastName" field is pre-filled with "<lastName>"
    Examples:
      | firstName  | lastName  |
      | Devis12     | Karl12     |

#  Scenario: Verify that a user can save a contact with all required and optional fields filled
#    When user enters all required and optional fields
#      | firstName | lastName | phoneNumber | emailAddress        | middleName | maidenName | entityName |
#      | Niks      | Ankam    | 1234567890  | niksankam@email.com | Y          | Nik        | NRN Corp   |
#    And user enters SSN and EIN details
#      | ssn         | ein        |
#      | 123-45-6789 | 12-3456789 |
#    And user clicks on the save button
#    Then user verifies that a confirmation message is displayed

  Scenario Outline: Verify that city, state and county are automatically fetched on entering zip
    When user enter "<zip>" in "Zip" Field
    Then Verify the city "<city>", state "<state>", and county "<country>" are automatically fetched
    Examples:
      | zip    | city      | state    | country |
      | 40007  | Bethlehem | Kentucky | Henry   |

  Scenario Outline: Verify that the system validates the EIN and SSN formats correctly
    When user enters SSN and EIN details
      | ssn         | ein        |
      | 123-45-6789 | 12-3456789 |
    And user enter "<AddressLine1>" in "Address Line 1" Field
    Then user verifies that a confirmation message is displayed
    Examples:
      | AddressLine1   |
      | Sukovia        |

  @Setup
  Scenario: SETUP: Close Browser
    Then User close browser