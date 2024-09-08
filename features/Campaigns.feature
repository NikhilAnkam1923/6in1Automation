@Centrifi @Smoke @Regression
Feature: Centrifi CRUD operation functionality

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    When user go to application "$centrifi_url"
    Then user login using "adityaghosh@benchmarkit.solutions" and "Aditya@27"

  @Smoke
  Scenario Outline: Validate the functionality for creating a new Campaign page
    Given user verify home page
    When user click on Campaigns button
    Then user click on Add new campaign button
    And user enter the campaign input details and click on next "<CampaignName>" "<Budget>" "<ClientName>" "<CampaignGoal>"
    Examples:
      | CampaignName    | Budget  | ClientName      | CampaignGoal         |
      | TestHK          | 15      | unique          | Increase Phone Calls |

  @Setup
  Scenario: SETUP: Close Browser
    When user logged out from the application
    Then User close browser