@Centrifi @Smoke @Regression
Feature: Centrifi CRUD operation functionality

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    When user go to application "$centrifi_url"
    Then user login using "$username" and "$password"

  @Smoke
  Scenario Outline: Validate the functionality for creating a new client
    Given user verify home page
    When user click on new client button
    And user verify add client page
    Then user enter below detail to add new client
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
    And user clicks the Save button to add a new client and verify success message

    Examples:
      | Client Name | Primary Contact Name | Primary Contact Email       | Primary Contact Phone | Primary Contact Title | Business Sector | Organization   | Website                | Tags              |
      | Noah Root   | Elizabeth Smith      | Elizabeth.Smith@example.com | (876) 545-3535        | Agent                 | Sports          | Organisation B | http://www.example.com | Digital marketing |

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
  Scenario Outline: Validate the functionality for deleting a client
    Then user deactivating record with client name "<Primary Contact Name>"
    Examples:
      | Primary Contact Name |
      | Elizabeth Smith      |

  @Setup
  Scenario: SETUP: Close Browser
    When user logged out from the application
    Then User close browser