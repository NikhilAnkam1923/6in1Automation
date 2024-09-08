@Centrifi @Smoke @Regression
Feature: Centrifi CRUD operation functionality

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    When user go to application "$centrifi_url"
    Then user login using "adityaghosh@benchmarkit.solutions" and "Aditya@27"

  @Smoke
  Scenario Outline: Validate the functionality for facebook platform connected with Authorization tab
    Given user verify home page
    Then user click on authorization button
    Then user click on facebook button
    Then user select the permissions and click continue with facebook "<name>" "<REPORT_FLAG>"
    Then user login using "<user email>" and "<password>" with facebook
    Then user click on Connect or Reconnect button verify the page
    Examples:
      | user email                        | password  | REPORT_FLAG | name  |
      | adityaghosh@benchmarkit.solutions | Aditya@27 | No          | FB_ HK |



  @Smoke
  Scenario Outline: Validate the functionality for facebook platform unlinked successfully
    Given user verify home page
    Then user click on authorization button
    Then user click on "<platFormName>" platform ande delete the record "<name>"
    Examples:
      | platFormName  | name            |
      | Facebook      | Facebook_Stage_12 |
      | Google      | GoogleHK_123 |


  @Smoke
  Scenario Outline: Validate the functionality for Google platform connected with Authorization tab
    Given user verify home page
    Then user click on authorization button
    Then user click on google button
    Then user select the permissions and click continue with facebook "<name>" "<REPORT_FLAG>"
    Then user login using "<google email>" and "<google password>" with google
    Then user click all option on Connect or Reconnect button verify the page
    Examples:
      | google email                    | google password | name   | REPORT_FLAG|
      | hemannac@benchmarkit.solutions  | hem2023$$       | HK_123 | No         |

  @Smoke
  Scenario Outline: Validate the functionality for Email platform connected with Authorization tab
    Given user verify home page
    Then user click on authorization button
    Then user click on email button
    Then user select the permissions and click continue with Email "<name>" "<ecsKey>" "<omsKey>" "<ReportFlag>"
    Then user login using "<user email>" and "<password>" with Email
    Then user click on Connect or Reconnect button verify the page
    Examples:
      | user email                        | password  | ecsKey | omsKey | ReportFlag | name     |
      | adityaghosh@benchmarkit.solutions | Aditya@27 | Test123 | Test234 | No          | HK_SIMP  |

  @Smoke
  Scenario Outline: Validate the functionality for Simpli platform connected with Authorization tab
    Given user verify home page
    When user click on authorization button
    And user click on Simpli button
    And user select the permissions and click continue with Simpli "<name>" "<UserKey>" "<ReportFlag>"
    Then user login using "<user email>" and "<password>" with Simpli
    Then user click on Connect or Reconnect button verify the page
    Examples:
      | user email                        | password  | UserKey |  ReportFlag | name     |
      | adityaghosh@benchmarkit.solutions | Aditya@27 | Test123 |  No          | HK_SIMP  |

  @Setup
  Scenario: SETUP: Close Browser
    When user logged out from the application
    Then User close browser