@6in1 @Smoke @Regression
Feature: 6in1 Login Feature

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
      | user-email          | password  |
      | admin@malinator.com | Bits@123  |

  @Smoke
  Scenario Outline: User verify validation message is thrown for incorrect email-id
    When user login using "<user-email>"
    Then user verify "<error-message>"
    Examples:
      | user-email   | error-message                                 |
      | adminmal.com | Please include an '@' in the email address.   |
      | admin@mal    | Please enter a valid domain, such as '.com'.  |
#      | adminmal.com | Invalid username or password.                 |
#      | admin@mal    | Invalid username or password.                 |


  Scenario Outline: User verify login using invalid credentials
    When user login using "<user-email>" and "<password>"
    Then user verify "<error-message>"
    Examples:
      | user-email           | password   | error-message                  |
      | admin@malinator.comm | Bits@12345 | Invalid username or password.  |

  @Smoke
  Scenario Outline: User verify if user is able to login without entering username
    When user enter "<password>"
    And user click on Login button
    Then user verify "<error-message>"
    Examples:
      | password   | error-message                  |
      | Bits@1234  | Invalid username or password.  |

  @Setup
  Scenario: SETUP: Close Browser
    Then User close browser