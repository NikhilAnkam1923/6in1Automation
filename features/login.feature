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
      | user-email                        | password      |
      | admin@malinator.com               | Bits@123      |
      | nikhilankam@benchmarkit.solutions | Password@1223 |

  @Smoke
  Scenario Outline: User verify validation message is thrown for incorrect email-id
    When user enter username as "<user-email>"
    Then user verify "<error-message>"
    Examples:
      | user-email   | error-message                 |
      | adminmal.com | Invalid username or password. |
      | admin@mal    | Invalid username or password. |

  @Smoke
  Scenario Outline: User verify login using invalid credentials
    When user login using "<user-email>" and "<password>"
    Then user verify "<error-message>"
    Examples:
      | user-email           | password   | error-message                 |
      | admin@malinator.comm | Bits@12345 | Invalid username or password. |
      | abc@gmail.com        | abc@123    | Invalid username or password. |

  @Smoke
  Scenario Outline: User verify if user is able to login without entering username
    When user enter password as "<password>"
    And user click on Login button
    Then user verify "<error-message>"
    Examples:
      | password  | error-message                 |
      | Bits@1234 | Invalid username or password. |

  Scenario Outline: User verify if user is able to login without entering password
    When user enter username as "<user-email>"
    And user click on Login button
    Then user verify "<error-message>"
    Examples:
      | user-email          | error-message                 |
      | admin@malinator.com | Invalid username or password. |

  Scenario Outline: User verify that password is visible on clicking eye icon
    When user enter password as "<password>"
    Then user clicks the eye icon to toggle visibility
    And user verify password is visible after click on the eye icon
    Examples:
      | password |
      | Bits@123 |

  Scenario: User verify Remember me checkbox available on the login page
    Then user verify Remember me checkbox available

  Scenario: User verify Remember me checkbox is clickable
    Then user verify Remember me checkbox clickable

  Scenario: User verify Forgot password page open after click on forgot password link
    When user click on forgot password link
    Then verify forgot password page is open

  Scenario: User verify user landed on login page after click on Back to Login link
    When user click on Back to Login link
    Then verify user landed on login page

  Scenario Outline: User verify that the logged-in user is admin or support staff
    When user login using "<user-email>" and "<password>"
    And user verify home page
    Then user verify visibility of the "Create Lackner Staff" button
    And verify the user type as "<expected-user-type>"
    And user logged out from the application
    Examples:
      | user-email                          | password      | expected-user-type |
      | nikhilankam@benchmarkit.solutions   | Password@1223 | Admin              |
      | gauravgidye@benchmarkit.solutions   | Gaurav#21     | Admin              |
      | gauravgidye+1@benchmarkit.solutions | Pritav@26     | Admin              |

  @Setup
  Scenario: SETUP: Close Browser
    Then User close browser