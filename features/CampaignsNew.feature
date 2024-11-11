@Centrifi @Smoke @Regression
Feature: Centrifi Campaign edit functionality.

  @Setup
  Scenario: Launch Browser and go to application
    Given User launched "chrome"
    When user go to application "$centrifi_url"
    Then user login using "adityaghosh@benchmarkit.solutions" and "Aditya@27"

  @Smoke
  Scenario: Verify whether user is able to select the campaign from the menu bar
    When user click on Campaign in the menu
    Then user observe the page

  @Smoke
  Scenario Outline: Verify whether a user is able to select the All Date Ranges
    When user click on Campaign in the menu
    And user select All Date Ranges as "<All Date Ranges>"
    Then user observe the page
    Examples:
      | All Date Ranges |
      | Last 15 Days    |

  @Smoke
  Scenario Outline: Verify whether a user is able to select the All Statuses option
    When user click on Campaign in the menu
    And user select All Statuses as "<Add Statuses>"
    Then user observe the page
    Examples:
      | Add Statuses    |
      | Draft , Pending |

  @Smoke
  Scenario Outline: Verify whether a user is able to select the Organization
    When user click on Campaign in the menu
    And user select Organization as "<Organization>"
    Then user observe the page
    Examples:
      | Organization |
      | Test BITS    |

  @Smoke
  Scenario Outline: User searches for a client or campaign name using the search field
    When user click on Campaign in the menu
    And User enters in the search field as "<search_term>"
    Then user observe the page
    Examples:
      | search_term                |
      | AutomationFacebookCampaign |
      | abc                        |

  @Smoke
  Scenario: Validate user able to click on New Campaign button
    When user click on Campaign in the menu
    And user click on New Campaign button
    Then user observe the page

  @Smoke
  Scenario Outline: Validate the Overview Field input functionality for creating a new Campaign page
    When user click on Campaign in the menu
    And user click on New Campaign button
    And user enter the campaign overview input details "<CampaignName>" "<CampaignGoal>" "<Client>" "<Budget>" and click on next
    Then user observe the page
    Examples:
      | CampaignName | CampaignGoal         | Client | Budget |
      | TestHK       | Increase Phone Calls | unique | 15     |

  @Setup
  Scenario: Close Browser
    When user logged out from the application
    Then User close browser
