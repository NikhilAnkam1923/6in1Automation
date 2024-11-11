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
    When user click on client button
    Then user select the client "<Client Name>"
    And user click on Contact button
    And user search and add the client contact record with below details
      | FieldName          | Value                |
      | Client Name        | <Client Name>        |
      | Contact First Name | <Contact First Name> |
      | Contact Last Name  | <Contact Last Name>  |
      | Contact Phone      | <Contact Phone>      |
      | Contact Title      | <Contact Title>      |
      | Contact Email      | <Contact Email>      |
      | Contact Address    | <Contact Address>    |
    And user clicks the create contact button to add a new client contact and verify success message

    Examples:
      | Client Name          | Contact First Name | Contact Last Name | Contact Phone  | Contact Title | Contact Email             | Contact Address                          |
      | TestAutomationClient | Mando              | Mando Paul        | (323) 232-3232 | Agent         | mando_$timestamp@test.com | 132, My Street, Kingston, New York 12401 |

  @Smoke
  Scenario Outline: Validate the functionality for updating a client Contact
    Given user click on client button
    When user select the client "<Client Name>"
    Then user click on Contact button
    And user search and update the client contact record with below details
      | FieldName          | Value                |
      | Client Name        | <Client Name>        |
      | Contact First Name | <Contact First Name> |
      | Contact Last Name  | <Contact Last Name>  |
      | Contact Phone      | <Contact Phone>      |
      | Contact Title      | <Contact Title>      |
      | Contact Email      | <Contact Email>      |
      | Contact Address    | <Contact Address>    |
      | Contact Name       | <Contact Name>       |
    And user click on edit contact button to verify update successfully message

    Examples:
      | Client Name          | Contact First Name | Contact Last Name | Contact Phone  | Contact Title | Contact Email             | Contact Address                                              | Contact Name     |
      | TestAutomationClient | Paulo              | Paul0 mando       | (323) 232-3243 | Manager       | paulo_$timestamp@test.com | 401 East Benton Place: Chicago, Cook County, Illinois, 60601 | Mando Mando Paul |

  @Smoke
  Scenario Outline: Validate the functionality for creating a new client Contact with invalid data
    Given user click on client button
    When user select the client "<Client Name>"
    Then user click on Contact button
    And user search and add the client contact record with below details
      | FieldName          | Value                |
      | Client Name        | <Client Name>        |
      | Contact First Name | <Contact First Name> |
      | Contact Last Name  | <Contact Last Name>  |
      | Contact Phone      | <Contact Phone>      |
      | Contact Title      | <Contact Title>      |
      | Contact Email      | <Contact Email>      |
      | Contact Address    | <Contact Address>    |
    And user clicks the create contact button to add a new client contact and verify error message

    Examples:
      | Client Name          | Contact First Name | Contact Last Name | Contact Phone | Contact Title | Contact Email | Contact Address                                              |
      | TestAutomationClient |                    |                   |               |               | paulotest     | 401 East Benton Place: Chicago, Cook County, Illinois, 60601 |

  @Smoke
  Scenario Outline: Validate the functionality for updating a client Contact with Invalid Data
    Given user click on client button
    When user select the client "<Client Name>"
    Then user click on Contact button
    And user search and update the client contact record with below details
      | FieldName          | Value                |
      | Client Name        | <Client Name>        |
      | Contact First Name | <Contact First Name> |
      | Contact Last Name  | <Contact Last Name>  |
      | Contact Phone      | <Contact Phone>      |
      | Contact Title      | <Contact Title>      |
      | Contact Email      | <Contact Email>      |
      | Contact Address    | <Contact Address>    |
      | Contact Name       | <Contact Name>       |
    And user click on edit contact button to verify update contact with invalid dat and verify error message

    Examples:
      | Client Name          | Contact First Name | Contact Last Name | Contact Phone | Contact Title | Contact Email | Contact Address                                              | Contact Name      |
      | TestAutomationClient |                    |                   | 12345         | Manager       | paulotest     | 401 East Benton Place: Chicago, Cook County, Illinois, 60601 | Paulo Paul0 mando |

  @Smoke
  Scenario Outline: Validate the functionality for deleting a client contact
    Given user click on client button
    When user select the client "<Client Name>"
    Then user click on Contact button
    And user deactivating record with contact name "<Contact First Name>" "<Contact Last Name>"
    Examples:
      | Contact First Name | Contact Last Name | Client Name          |
      | Paulo              | Paul0 mando       | TestAutomationClient |

  @Smoke
  Scenario Outline: Validate the functionality for campaigns associated with the client are displayed on the Campaign tab
    Given user verify home page
    When user click on client button
    Then user select the client "<Client Name>"
    And user click on campaign button
    And user Select the Campaign Name "<Campaign  Name>"
    And user verify the redirected to the campaign page "<Campaign  Name>"
    Examples:
      | Client Name          | Campaign  Name         |
      | TestAutomationClient | AutomationTestCampaign |

  @Smoke
  Scenario Outline: Validate the functionality for report associated with the client are displayed on the Report tab
    Given user verify home page
    When user click on client button
    Then user select the client "<Client Name>"
    And user click on report button
    And user click on the "<Report Name>" report button
    And user verify the redirected to the report page
    Examples:
      | Client Name          | Report Name  |
      | TestAutomationClient | Facebook Ads |

  @Smoke
  Scenario Outline: Validate the functionality for proposal associated with the client are displayed on the proposal tab
    Given user verify home page
    When user click on client button
    Then user select the client "<Client Name>"
    And user click on proposal button
    And user Select the Campaign Name "<Campaign  Name>"
    And user verify the redirected to the proposal page "<Campaign  Name>"
    Examples:
      | Client Name          | Campaign  Name |
      | TestAutomationClient | TestAutomation |

  @Setup
  Scenario: SETUP: Close Browser
    When user logged out from the application
    Then User close browser