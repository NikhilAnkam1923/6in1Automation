@Centrifi @Smoke @Regression
Feature: Centrifi CRUD operation functionality

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    When user go to application "$centrifi_url"
    Then user login using "adityaghosh@benchmarkit.solutions" and "Aditya@27"

  @Smoke
  Scenario Outline: Validate the functionality for creating a new client Contact
    Given user verify home page
    When user search and add the client contact record with below details
      | FieldName             | Value                   |
      | Client Name           | <Client Name>           |
      | Contact First Name    | <Contact First Name>    |
      | Contact Last Name     | <Contact Last Name>     |
      | Contact Phone         | <Contact Phone>         |
      | Contact Title         | <Contact Title>         |
      | Contact Email         | <Contact Email>         |
      | Contact Address       | <Contact Address>       |
    Then user clicks the create contact button to add a new client contact and verify success message
    And user deactivating record with contact name "<Contact First Name>" "<Contact Last Name>"
    Examples:
      | Client Name           | Contact First Name | Contact Last Name | Contact Phone  | Contact Title | Contact Email   | Contact Address   |
      | TestAutomationClient  | hemanth            | test              | (876) 545-3535 | Agent         | Sports@msti.com |    #45 ABC        |

  @Smoke
  Scenario Outline: Validate the functionality for campaigns associated with the client are displayed on the Campaign tab
    Given user verify home page
    When user click on client button
    Then user select the client "<Client Name>"
    And user click on campaign button
    And user Select the Campaign Name "<Campaign  Name>"
    And user verify the redirected to the campaign page
    Examples:
      | Client Name          | Campaign  Name          |
      | TestAutomationClient | AutomationTestCampaign  |

  @Smoke
  Scenario Outline: Validate the functionality for report associated with the client are displayed on the Report tab
    Given user verify home page
    When user click on client button
    Then user select the client "<Client Name>"
    And user click on report button
    And user click on the "<Report Name>" report button
    And user verify the redirected to the report page
    Examples:
      | Client Name           | Report Name  |
      | TestAutomationClient  | Facebook Ads |

  @Setup
  Scenario: SETUP: Close Browser
    When user logged out from the application
    Then User close browser