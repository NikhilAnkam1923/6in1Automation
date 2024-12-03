@6in1 @Smoke @Regression
Feature: Verify 6in1 New Firm Creation

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "$6in1_url"

  @Smoke
  Scenario Outline: User verify login with valid credentials
    When user login using "<user-email>" and "<password>"
    Then user verify home page
#    And user logged out from the application
    Examples:
      | user-email                        | password  |
      | gauravgidye@benchmarkit.solutions | Gaurav#21 |

  @Smoke
  Scenario: Verify that the confirmation message is displayed upon successful user addition
    When user click on tab: "Firms"
    And user verify firm page
    Then user click on "Create" Button
    And user verify create page

  @Smoke
  Scenario Outline: User verify validation messages for invalid input fields on the "Create Firm Page"
    When user enters "<input>" in the "<textBox>"
    And click on "Save" button
    Then user verify the system displays "<expectedMessage>" for "<field>"
    Examples:
      | textBox            | input     | expectedMessage                    | field                  |
      | Firm Name          |           | Firm Name is required              | lawFirm.name           |
      | Firm Email Address | abc@gmail | Please enter a valid Email Address | lawFirm.email          |
      | Phone #            | 898       | Please enter a valid Phone #       | lawFirm.phone          |
      | First Name         |           | First name is required             | accountOwner.firstName |
      | Mobile #           | abdc      | Please enter a valid Mobile #      | accountOwner.phone     |
      | Email Address      | abc@gmail | Please enter a valid Email Address | accountOwner.email     |

  @Setup
  Scenario: SETUP: Close Browser
    Then user logged out from the application
    And User close browser


