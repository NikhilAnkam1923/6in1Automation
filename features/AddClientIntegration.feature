# Completed Testcase id : CD21050,CD21051,CD21057,CD21058,CD21162,CD21163,CD21164

@Centrifi @Smoke @Regression
Feature: Centrifi CRUD operation functionality

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    When user go to application "$centrifi_url"
    Then user login using "$username" and "$password"


  @Smoke
  Scenario Outline: Validate the functionality for Email platform should be integrated with integration tab
    Given user verify home page
    Then user click on client button
    Then user Select one of the clients from the list "<Client Name>" and click on integration button
    Then Enter the valid input from email platform "<ClientName>" "<authName>" "<ReportFlag>" "<ReportName>" and click on connect button

    Examples:
      | Client Name               | ClientName        | authName  | ReportFlag | ReportName|
      | 22_aug_new_client_update  | Tests           | e-mail OMS  | No        | ReportName   |



  @Smoke
  Scenario Outline: Validate the functionality for Facebook platform should be integrated with integration tab
    Given user verify home page
    Then user click on client button
    Then user Select one of the clients from the list "<Client Name>" and click on integration button
    Then Enter the valid input from facebook platform "<AccountId>" "<PageId>" "<AuthorizationId>" "<ReportFlag>" "<ReportName>" and click on connect button

    Examples:
      | Client Name               | AccountId                  | PageId           | AuthorizationId | ReportFlag   | ReportName|
      | 22_aug_new_client_update  | act_1122918478935848       | 328785513661717  |  Facebook_5474  | No           | Facebook  |

  @Smoke
  Scenario Outline: Validate the functionality for creating a new client and integrate new with Email platform in integration tab
    Given user verify home page
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
    Then Enter the valid input from email platform "<ClientName>" "<authName>" "<ReportFlag>" "<ReportName>" and click on connect button

    Examples:
      | Client Name               | ClientName        | authName    | ReportName | ReportFlag  |  Primary Contact Name | Primary Contact Email       | Primary Contact Phone | Primary Contact Title | Business Sector | Organization   | Website                | Tags              |
      | 04_sep_new_client_integrate  | Tests          | e-mail OMS  | ReportName |No          | Elizabeth Smith      | Elizabeth.Smith@example.com | (876) 545-3535        | Agent                 | Sports          | Organisation B | http://www.example.com | Digital marketing |

  @Smoke
  Scenario Outline: Validate the functionality for creating a new client and integrate new with facebook platform in integration tab
    Given user verify home page
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
    Then Enter the valid input from facebook platform "<AccountId>" "<PageId>" "<AuthorizationId>" "<ReportFlag>" "<ReportName>" and click on connect button

    Examples:
      | Client Name                  | AccountId               | PageId  |AuthorizationId| ReportFlag  |  Primary Contact Name | Primary Contact Email       | Primary Contact Phone | Primary Contact Title | Business Sector | Organization   | Website                | Tags              | ReportName|
      | 04_sep_new_client_integrate  | act_1122918478935848   | 328785513661717  |Facebook_5474| No          | Elizabeth Smith      | Elizabeth.Smith@example.com | (876) 545-3535   | Agent                 | Sports          | Organisation B | http://www.example.com | Digital marketing | Facebook  |

  @Smoke
  Scenario Outline: Validate the functionality for Simpli platform should be integrated with integration tab
    Given user verify home page
    Then user click on client button
    Then user Select one of the clients from the list "<Client Name>" and click on integration button
    Then Enter the valid input from Simpli platform "<OrgId>" "<authName>" "<ReportFlag>" "<ReportName>" and click on connect button

    Examples:
      | Client Name               | OrgId             | authName      | ReportFlag | ReportName|
      | 04_sep_new_client_integrate  | 12345          | Simpli.fi 22  | Yes         | Simpli.fi_stage |

  @Smoke
  Scenario Outline: Validate the functionality for Google platform should be integrated with integration tab
    Given user verify home page
    Then user click on client button
    Then user Select one of the clients from the list "<Client Name>" and click on integration button
    Then Enter the valid input from google platform "<Integration_Type>" "<AccountId_Name>" "<OwnerAccId_PropertyId>" "<authName>" "<ReportFlag>" "<ReportName>" "<LocationName>" and click on connect button

    Examples:
      | Client Name                  | OwnerAccId_PropertyId  | AccountId_Name  | authName      | ReportFlag  | ReportName      | Integration_Type        | LocationName |
      | 04_sep_new_client_integrate  | 12345                  | 123456          | Google 5445  | No          | Simpli.fi_stage | Google Analytics V4-id  | Bangalore    |

  @Smoke
  Scenario Outline: Validate the functionality for Email platform should be unlink with integration tab
    Given user verify home page
    Then user click on client button
    Then user Select one of the clients from the list "<Client Name>" and click on integration button
    Then user click on "<PlatformName>" disconnect button and verify connected platform removed successfully or not


    Examples:
      | Client Name               | PlatformName       |
     # | 22_aug_new_client_update  | Email              |
      | 22_aug_new_client_update  | Facebook Ads              |
    #  | 22_aug_new_client_update  | Facebook Ads        |
    #  | 22_aug_new_client_update  | Simpli.Fi        |


  @Setup
  Scenario: SETUP: Close Browser
    When user logged out from the application
    Then User close browser