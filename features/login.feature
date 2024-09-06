@Centrifi @Smoke @Regression
Feature: Centrifi Login Feature

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"

  @Smoke
  Scenario Outline: User verify login with valid credentials
    Given user go to application "$centrifi_url"
    Then user verify login page ui attributes
    And user login using "adityaghosh@benchmarkit.solutions" and "Aditya@27"
    Then user verify home page
    And user logged out from the application
    Examples:
      | useremail                         | password  |
      | adityaghosh@benchmarkit.solutions | Aditya@27 |

  @Smoke
  Scenario Outline: User tries to log in with invalid credentials
    And user login using "adityaghosh@benchmarkit.solutions" and "Aditya@27"
    Then user verify "<error-message>" for invalid credentials
    And user logged out from the application
    Examples:
      | useremail                           | password  | error-message       |
      | adityaghosh@bencashmarkit.solutions | Aditya@27 | invalid credentials |

  @Setup
  Scenario: SETUP: Close Browser
    Then User close browser