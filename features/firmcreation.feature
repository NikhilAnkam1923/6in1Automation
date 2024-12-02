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
    And user logged out from the application
    Examples:
      | user-email                        | password      |
      | nikhilankam@benchmarkit.solutions | Password@1223 |

  @Smoke
  Scenario : User verify validation messages for input fields on the "Create Firm Page"
    When user enters <inputs> in the <field>
    And clicks "Save"
    Then the system displays <expectedMessage>
    Examples:
      | field              | input     | expectedMessage                    |
      | Firm Name          | ""        | Firm Name is required              |
      | Firm Email Address | abc@gmail | Please enter a valid Email Address |
      | Phone #            | 898       | Please enter a valid Phone #       |
      | First Name         | ""        | First name is required             |



