@Centrifi @Smoke @Regression
Feature: Centrifi CRUD operation functionality

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    When user go to application "$centrifi_url"



  @Smoke
  Scenario Outline: Validate the functionality for Email platform should be integrated with integration tab
    Then user login using "<user email>" and "<password>"
    When user verify home page
    Then user click on client button
    Then user Select one of the clients from the list "<Client Name>" and click on integration button
    Then Enter the valid input from email platform "<ClientName>" "<ReportName>" "<ReportFlag>" and click on connect button

    Examples:
      | user email                        | password  | Client Name               | ClientName        | ReportName  | ReportFlag |
      | adityaghosh@benchmarkit.solutions | Aditya@27 | 22_aug_new_client_update  | Tests           | e-mail OMS  | No        |



  @Smoke
  Scenario Outline: Validate the functionality for Facebook platform should be integrated with integration tab
    Then user login using "<user email>" and "<password>"
    When user verify home page
    Then user click on client button
    Then user Select one of the clients from the list "<Client Name>" and click on integration button
    Then Enter the valid input from facebook platform "<AccountId>" "<PageId>" "<AuthorizationId>" "<ReportFlag>" and click on connect button

    Examples:
      | user email                        | password  | Client Name               | AccountId                  | PageId           | AuthorizationId | ReportFlag   |
      | adityaghosh@benchmarkit.solutions | Aditya@27 | 22_aug_new_client_update  | act_1122918478935848       | 328785513661717  |  Facebook_5474  | No        |

  @Smoke
  Scenario Outline: Validate the functionality for creating a new client and integrate new with Email platform in integration tab
    Then user login using "<user email>" and "<password>"
    When user verify home page
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
    Then user Select one of the clients from the list "<Client Name>" and click on integration button
    Then Enter the valid input from email platform "<ClientName>" "<ReportName>" "<ReportFlag>" and click on connect button

    Examples:
      | user email                        | password  | Client Name               | ClientName        | ReportName  | ReportFlag  |  Primary Contact Name | Primary Contact Email       | Primary Contact Phone | Primary Contact Title | Business Sector | Organization   | Website                | Tags              |
      | adityaghosh@benchmarkit.solutions | Aditya@27 | 04_sep_new_client_integrate  | Tests             | e-mail OMS  | No          | Elizabeth Smith      | Elizabeth.Smith@example.com | (876) 545-3535        | Agent                 | Sports          | Organisation B | http://www.example.com | Digital marketing |


  @Smoke
  Scenario Outline: Validate the functionality for Simpli platform should be integrated with integration tab
    Then user login using "<user email>" and "<password>"
    When user verify home page
    Then user click on client button
    Then user Select one of the clients from the list "<Client Name>" and click on integration button
    Then Enter the valid input from Simpli platform "<OrgId>" "<ReportName>" "<ReportFlag>" and click on connect button

    Examples:
      | user email                        | password  | Client Name               | OrgId        | ReportName  | ReportFlag |
      | adityaghosh@benchmarkit.solutions | Aditya@27 | 22_aug_new_client_update  | 12345             | Simpli.fi 22  | No        |

  @Smoke
  Scenario Outline: Validate the functionality for Email platform should be unlink with integration tab
    Then user login using "<user email>" and "<password>"
    When user verify home page
    Then user click on client button
    Then user Select one of the clients from the list "<Client Name>" and click on integration button
    Then user click on "<PlatformName>" disconnect button and verify connected platform removed successfully or not


    Examples:
      | user email                        | password  | Client Name               | PlatformName       |
      | adityaghosh@benchmarkit.solutions | Aditya@27 | 22_aug_new_client_update  | Email              |
    #  | adityaghosh@benchmarkit.solutions | Aditya@27 | 22_aug_new_client_update  | Facebook Ads        |
    #  | adityaghosh@benchmarkit.solutions | Aditya@27 | 22_aug_new_client_update  | Simpli.Fi        |


  @Setup
  Scenario: SETUP: Close Browser
    When user logged out from the application
    Then User close browser