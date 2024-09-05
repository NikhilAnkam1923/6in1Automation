@Centrifi @Smoke @Regression
Feature: Centrifi CRUD operation functionality

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    When user go to application "$centrifi_url"


  @Smoke
  Scenario Outline: Validate the functionality for creating a new client Contact
    Then user login using "<user email>" and "<password>"
    When user verify home page
   # Then user click on new client button
    Then user search and add the client contact record with below details
      | FieldName             | Value                   |
      | Client Name           | <Client Name>           |
      | Contact First Name    | <Contact First Name>    |
      | Contact Last Name     | <Contact Last Name>     |
      | Contact  Phone        | <Contact Phone>         |
      | Contact Title         | <Contact Title>         |
      | Contact Email         | <Contact Email>         |
      | Contact Address       | <Contact Address>       |

    And user clicks the create contact button to add a new client contact and verify success message


    Examples:
      | user email                        | password  | Client Name              | Contact First Name | Contact Last Name       | Contact Phone  | Contact Title | Contact Email          | Contact Address   |
      | adityaghosh@benchmarkit.solutions | Aditya@27 | 22_aug_new_client_update  | macro              | milo1                   | (876) 545-3535 | Agent          | Sports@msti.com       |    #45 ABC        |



  @Smoke
  Scenario Outline: Validate the functionality for campaigns associated with the client are displayed on the Campaign tab
    Then user login using "<user email>" and "<password>"
    When user verify home page
    Then user click on client button
    Then user Select one of the clients from the list "<Client Name>" and click on campaign button
    Then user Select one of the Campaign  Name from the list "<Campaign  Name>" and verify user should be redirected to the campaign page


    Examples:
      | user email                        | password  | Client Name               | Campaign  Name            |
      | adityaghosh@benchmarkit.solutions | Aditya@27 | 22_aug_new_client_update  | 22_Aug_Email              |

  @Smoke
  Scenario Outline: Validate the functionality for report associated with the client are displayed on the Report tab
    Then user login using "<user email>" and "<password>"
    When user verify home page
    Then user click on client button
    Then user Select one of the clients from the list "<Client Name>" and click on report button
    Then user click on "<Report Name>" report button and verify user should be redirected to the report page


    Examples:
      | user email                        | password  | Client Name               | Report Name            |
      | adityaghosh@benchmarkit.solutions | Aditya@27 | 22_aug_new_client_update  | Google Ads              |


  @Setup
  Scenario: SETUP: Close Browser
    When user logged out from the application
    Then User close browser