@Centrifi @Smoke @Regression
Feature: Centrifi CRUD operation functionality

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    When user go to application "$centrifi_url"
    Then user login using "$username" and "$password"

  @Smoke
  Scenario Outline: Validate the functionality for facebook platform connected with Authorization tab
    Given user verify home page
    Then user click on authorization button
    Then user click on facebook button
    Then user select the permissions and click continue with facebook
    Then user login using "<user email>" and "<password>" with facebook
    Then user click on Connect or Reconnect button verify the page
    Examples:
      | user email                        | password  |
      | adityaghosh@benchmarkit.solutions | Aditya@27 |



  @Smoke
  Scenario Outline: Validate the functionality for facebook platform unlinked successfully
    Given user verify home page
    Then user click on authorization button
    Then user click on "<platFormName>" platform ande delete the record "<name>"


    Examples:
      | platFormName  | name            |
      | Facebook      | Facebook_Stage_12 |


  @Smoke
  Scenario Outline: Validate the functionality for Google platform connected with Authorization tab
    Given user verify home page
    Then user click on authorization button
    Then user click on google button "<name>"
    Then user select the permissions and click continue with facebook
    Then user login using "<google email>" and "<google password>" with google
    Then user click all option on Connect or Reconnect button verify the page


    Examples:
      | google email                    | google password | name   |
      | hemannac@benchmarkit.solutions  | hem2023$$       | HK_123 |

  @Setup
  Scenario: SETUP: Close Browser
    When user logged out from the application
    Then User close browser