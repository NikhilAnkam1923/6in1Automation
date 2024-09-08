@Centrifi @Smoke @Regression
Feature: Centrifi Login Feature

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"

  @Smoke
  Scenario Outline: User verify login with valid credentials
    Given user go to application "$centrifi_url"
    When user login using "<user-email>" and "<password>"
    Then user verify home page
    And user logged out from the application
    Examples:
      | user-email                        | password  |
      | adityaghosh@benchmarkit.solutions | Aditya@27 |

  @Smoke
  Scenario Outline: User tries to log in with invalid credentials
    And user login using "<user-email>" and "<password>"
    Then user verify "<error-message>" for invalid credentials
    Examples:
      | user-email                           | password  | error-message                |
      | adityaghosh@bencashmarkit.solutions | Aditya@27 | Invalid username or password. |

  @Setup
  Scenario: SETUP: Close Browser
    Then User close browser