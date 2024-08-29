@Centrifi @Smoke @Regression
Feature: Centrifi CRUD operation functionality

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"

  @Smoke
  Scenario Outline: Validate the functionality for creating a new client
    Given user go to application "$centrifi_url"
    And user login using "<user email>" and "<password>"
    Then user verify home page
    Then user click on new client button
    Then user verify add client page
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
    And user logged out from the application



    Examples:
      | user email                        | password  | Client Name | Primary Contact Name | Primary Contact Email | Primary Contact Phone | Primary Contact Title | Business Sector | Organization   | Website                | Tags              |
      | adityaghosh@benchmarkit.solutions | Aditya@27 | Emily Davis | John Doe             | test.user@example.com | (876) 545-3535        | Agent                 | Sports          | Organisation B | http://www.example.com | Digital marketing |
#      | adityaghosh@benchmarkit.solutions | Aditya@27 | Emily Davis1 | John Doe 1           | test.user@example.com | (876) 545-3535        | Consultant            | Entertainment/Events | Organisation C | http://www.google.com  | Digital marketing |

  @Smoke
  Scenario Outline: Validate the functionality for updating a client
    Given user go to application "$centrifi_url"
    And user login using "<user email>" and "<password>"
    Then user verify home page
    Then user search and update the client record with below details for Primary Contact Name
      | FieldName            | Value                  |
      | Client Name          | <Client Name>          |
      | Primary Contact Name | <Primary Contact Name> |
      | Business Sector      | <Business Sector>      |
      | Organization         | <Organization>         |
      | Website              | <Website>              |
      | Tags                 | <Tags>                 |
    And user clicks the Save button and verify updated successfully message
    And user logged out from the application

    Examples:
      | user email                        | password  | Client Name | Primary Contact Name | Business Sector | Organization   | Website               | Tags    |
      | adityaghosh@benchmarkit.solutions | Aditya@27 | Emily smith | John Doe             | Technology      | Test 5225 12/8 | http://www.google.com | Digital |

  @Smoke
  Scenario Outline: Validate the functionality for deleting a client
    Given user go to application "$centrifi_url"
    And user login using "<user email>" and "<password>"
    Then user verify home page
    Then user deactivating record with client name "<Primary Contact Name>"
    And user logged out from the application
    Examples:
      | user email                        | password  | Primary Contact Name |
      | adityaghosh@benchmarkit.solutions | Aditya@27 | John Doe             |


  @Setup
  Scenario: SETUP: Close Browser
    Then User close browser