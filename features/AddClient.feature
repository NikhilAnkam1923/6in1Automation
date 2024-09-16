@Centrifi @Smoke @Regression
Feature: Centrifi CRUD operation functionality

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    When user go to application "$centrifi_url"
    Then user login using "adityaghosh@benchmarkit.solutions" and "Aditya@27"

  @Smoke
  Scenario Outline: Validate the functionality for creating a new client
    Given user verify home page
    When user click on new client button
    Then user verify add client page
    And user enter below detail to add new client
      | FieldName             | Value                   |
      | Client Name           | <Client Name>           |
      | Primary Contact Name  | <Primary Contact Name>  |
      | Primary Contact Email | <Primary Contact Email> |
      | Primary Contact Phone | <Primary Contact Phone> |
      | Primary Contact Title | <Primary Contact Title> |
      | Business Sector       | <Business Sector>       |
      | Organization          | <Organization>          |
      | Website               | <Website>               |
      | Tags                  | <Tags>                  |
      | profileName           | <profileName>           |
    And user clicks the Save button to add a new client and verify success message
    Examples:
      | Client Name | Primary Contact Name | Primary Contact Email       | Primary Contact Phone | Primary Contact Title | Business Sector | Organization   | Website                | Tags              | profileName   |
      | Noah Root   | Elizabeth Smith      | Elizabeth.Smith@example.com | (876) 545-3535        | Agent                 | Sports          | Organisation B | http://www.example.com | Digital marketing | TestImage.png |

  @Smoke
  Scenario Outline: Validate the functionality for creating a new client with invalid data
    Given user verify home page
    When user click on new client button
    Then user verify add client page
    And user enter below detail to add new client
      | FieldName             | Value                   |
      | Client Name           | <Client Name>           |
      | Primary Contact Name  | <Primary Contact Name>  |
      | Primary Contact Email | <Primary Contact Email> |
      | Primary Contact Phone | <Primary Contact Phone> |
      | Primary Contact Title | <Primary Contact Title> |
      | Business Sector       | <Business Sector>       |
      | Organization          | <Organization>          |
      | Website               | <Website>               |
      | Tags                  | <Tags>                  |
      | profileName           | <profileName>           |
    And user click on the Save button and verify error message
    Examples:
      | Client Name | Primary Contact Name | Primary Contact Email       | Primary Contact Phone | Primary Contact Title | Business Sector | Organization   | Website   | Tags   | profileName   |
      |             |                      | Elizabeth                   | 12353                 |                       |                 |                | 1234      |  test  | TestImage.png |

  @Smoke
  Scenario Outline: Validate the functionality for updating a client
    Then user search and update the client record with below details for Primary Contact Name
      | FieldName            | Value                  |
      | Client Name          | <Client Name>          |
      | Primary Contact Name | <Primary Contact Name> |
      | Business Sector      | <Business Sector>      |
      | Organization         | <Organization>         |
      | Website              | <Website>              |
      | Tags                 | <Tags>                 |
    And user clicks the Save button and verify updated successfully message
    Examples:
      | Client Name | Primary Contact Name | Business Sector | Organization   | Website | Tags    |
      | Emily smith | Elizabeth Smith      | Technology      | Test 5225 12/8 |         | Digital |

  @Smoke
  Scenario Outline: Validate the functionality for update the client invalid data
    Given user verify home page
    When user click on client button
    Then user select the client "<Client Name>"
    And user click on edit client button and enter empty name "<Name>"
    And user clicks the Save button and verify error message
    Examples:
      | Client Name          | Name |
      | TestAutomationClient |      |

  @Smoke
  Scenario Outline: Validate the functionality for client is searched successfully.
    Given user verify home page
    When user click on client button
    Then user search the client "<Primary Contact Name>"
    Examples:
      | Primary Contact Name |
      | hem                  |

  @Smoke
  Scenario Outline: Validate the functionality for client details are correctly displayed in the client list.
    Given user verify home page
    When user click on client button
    Then user search the client "<Primary Contact Name>"
    And user verify the below details are correctly displayed in the client list
      | FieldName             | Value                   |
      | Client Name           | <Client Name>           |
      | Primary Contact Name  | <Primary Contact Name>  |
      | Business Sector       | <Business Sector>       |
      | Organization          | <Organization>          |
      | Status                | <Status>                |
    Examples:
      | Primary Contact Name | Client Name          | Business Sector | Organization   | Status |
      | hemanna c            | TestAutomationClient | Education       | Organisation C | Active |

  @Smoke
  Scenario Outline: Validate the functionality for client details are correctly displayed in the client list.
    Given user verify home page
    When user click on client button
    Then user search the client "<Primary Contact Name>"
    And user verify the below details are correctly displayed in the client list
      | FieldName             | Value                   |
      | Client Name           | <Client Name>           |
      | Primary Contact Name  | <Primary Contact Name>  |
      | Business Sector       | <Business Sector>       |
      | Organization          | <Organization>          |
      | Status                | <Status>                |
    Examples:
      | Primary Contact Name | Client Name          | Business Sector | Organization   | Status |
      | hemanna c            | TestAutomationClient | Education       | Organisation C | Active |

  @Smoke
  Scenario Outline: Validate the functionality for selected client is successfully Activated
    Given user verify home page
    When user click on client button
    Then user select the inactive client "<Status>"
    And user search the client "<Primary Contact Name>"
    And user click on active button "<Primary Contact Name>"

    Examples:
      | Primary Contact Name | Status   |
      | John Doe             | Inactive |

  @Smoke
  Scenario Outline: Validate the functionality for deleting a client
    Then user deactivating record with client name "<Primary Contact Name>"
    Examples:
      | Primary Contact Name |
      | Elizabeth Smith      |

  @Setup
  Scenario: SETUP: Close Browser
    When user logged out from the application
    Then User close browser