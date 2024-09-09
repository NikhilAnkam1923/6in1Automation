# Completed Testcase id : CD21050,CD21051,CD21057,CD21058,CD21162,CD21163,CD21164

@Centrifi @Smoke @Regression
Feature: Centrifi CRUD operation functionality

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    When user go to application "$centrifi_url"
    Then user login using "adityaghosh@benchmarkit.solutions" and "Aditya@27"


  @Smoke
  Scenario Outline: Validate the functionality for Email platform should be integrated with integration tab
    Given user verify home page
    Then user click on client button
    And user select the client "<Client Name>"
    And user click on integration button
    And user enter below details to connect email platform details
    | FieldName  | Value        |
    | ClientName | <ClientName> |
    | authName   | <authName>   |
    | ReportFlag | <ReportFlag> |
    | ReportName | <ReportName> |
  And click on Email connect button

    Examples:
      | Client Name          | ClientName | authName   | ReportFlag | ReportName |
      | TestAutomationClient | TestsEmail | e-mail OMS | No         | ReportName |

  @Smoke
  Scenario Outline: Validate the functionality for Connected Integrations platform should be unlink with integration tab
    Given user verify home page
    When user click on client button
    Then user select the client "<Client Name>"
    And user click on integration button
    And user click on "<PlatformName>" disconnect button and verify disconnected successfully Message


    Examples:
      | Client Name          | PlatformName |
      | TestAutomationClient | Email        |
      | TestAutomationClient | Facebook Ads |
    # | TestAutomationClient | Facebook Ads |
    # | TestAutomationClient | Simpli.Fi    |


  @Smoke
  Scenario Outline: Validate the functionality for Facebook platform should be integrated with integration tab
    Given user verify home page
    Then user click on client button
    Then user select the client "<Client Name>"
    And user click on integration button
    And user enter below detail to connect to facebook
      | FieldName       | Value             |
      | AccountId       | <AccountId>       |
      | PageId          | <PageId>          |
      | AuthorizationId | <AuthorizationId> |
      | ReportFlag      | <ReportFlag>      |
      | ReportName      | <ReportName>      |
    And click on facebook connect button

    Examples:
      | Client Name          | AccountId            | PageId          | AuthorizationId  | ReportFlag | ReportName |
      | TestAutomationClient | act_1122918478935848 | 328785513661717 |  Facebook_TestHK | No         | Facebook   |

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
    And user select the client "<Client Name>"
    And user click on integration button
    And user enter below details to connect email platform details
      | FieldName  | Value        |
      | ClientName | <ClientName> |
      | authName   | <authName>   |
      | ReportFlag | <ReportFlag> |
      | ReportName | <ReportName> |
    And click on Email connect button
    And user deactivating record with client name "<Primary Contact Name>"
    Examples:
      | Client Name                | ClientName       | authName    | ReportName | ReportFlag | Primary Contact Name | Primary Contact Email       | Primary Contact Phone | Primary Contact Title | Business Sector | Organization   | Website                | Tags              |
      | TestEmailClientIntegration | EmailIntegration | e-mail OMS  | ReportName | No         | hemanth              | Elizabeth.Smith@example.com | (876) 545-3535        | Agent                 | Sports          | Organisation B | http://www.example.com | Digital marketing |

  @Smoke
  Scenario Outline: Validate the functionality for Simpli platform should be integrated with integration tab
    Given user verify home page
    When user click on client button
    Then user select the client "<Client Name>"
    And user click on integration button
    And user enter below details to connect Simpli platform
      | FieldName  | Value        |
      | OrgId      | <OrgId>      |
      | authName   | <authName>   |
      | ReportFlag | <ReportFlag> |
      | ReportName | <ReportName> |
    And click on Simpli connect button

    Examples:
      | Client Name          | OrgId | authName     | ReportFlag | ReportName      |
      | TestAutomationClient | 12345 | Simpli.fi 22 | Yes        | Simpli.fi_stage |

  @Smoke
  Scenario Outline: Validate the functionality for Google platform should be integrated with integration tab
    Given user verify home page
    When user click on client button
    Then user select the client "<Client Name>"
    And user click on integration button
    And user enter below details to connect google platform
      | FieldName             | Value                   |
      | IntegrationType       | <IntegrationType>       |
      | AccountId_Name        | <AccountId_Name>        |
      | OwnerAccId_PropertyId | <OwnerAccId_PropertyId> |
      | authName              | <authName>              |
      | ReportFlag            | <ReportFlag>            |
      | ReportName            | <ReportName>            |
      | LocationName          | <LocationName>          |
    And click on google connect button

    Examples:
      | Client Name          | OwnerAccId_PropertyId | AccountId_Name | authName    | ReportFlag | ReportName      | IntegrationType        | LocationName |
      | TestAutomationClient | 12345                 | 123456         | Google 5445 | No         | Simpli.fi_stage | Google Analytics V4-id | Bangalore    |

  @Setup
  Scenario: SETUP: Close Browser
    When user logged out from the application
    Then User close browser