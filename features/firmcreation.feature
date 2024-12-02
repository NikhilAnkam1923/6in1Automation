@6in1 @Smoke @Regression
Feature: Verify 6in1 New Firm Creation

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "$6in1_url"

  @Smoke
  Scenario Outline: User verify login with valid credentials
    When user login using "<user-email>" and "<password>"
    Then user verify home page
    Examples:
      | user-email                        | password      |
      | gauravgidye@benchmarkit.solutions | Gaurav#21     |

  @Smoke
  Scenario Outline: User verify that the confirmation message is display on successful firm addition
    When user click on tab: "Firms"
    And user verify firm page
    Then user click on "Create" Button
    And user verify create page
    Then user enter "<FirmName>" in "Firm Name" Field
    And user enter "<FirmEmail>" in "Firm Email Address" Field
    And user enter phone "<Phone>" for Firm
    And user enter "<AddressLine1>" in "Address Line 1" Field
    And user enter "<AddressLine2>" in "Address Line 2" Field
    And user enter "<Zip>" in "Zip" Field
    And user enter "<EFIN>" in "EFIN" Field
    And user enter "<EIN>" in "EIN" Field
    And user enter "<EROPTIN>" in "ERO PTIN" Field
    And user enter "<SubdomainKey>" in "Subdomain Key" Field
    And user select "License Type" as "<licenseType>"
    And user enter "<FirstName>" in "First Name" Field
    And user enter "<MiddleName>" in "Middle Name" Field
    And user enter "<LastName>" in "Last Name" Field
    And user select "Suffix" as "<Suffix>"
    And user enter "<userEmailAddress>" in "Email Address" Field
    And user enter phone "<userPhone>" for Primary User
    Then user click on "Save" Button
    And user click on "Save" Button
    Then Verify the success message "Firm added successfully"
    And user verify firm page
    Examples:
      | FirmName | FirmEmail        | Phone       | AddressLine1 | AddressLine2    | Zip   | EFIN      | EIN       | EROPTIN | SubdomainKey | licenseType | FirstName | MiddleName | LastName | userEmailAddress                  | userPhone  | Suffix |
      | Swiggy   | swiggy@india.com | 1234567890  | Lass         | opposite xmart  | 40011 | 8989hj989 | 483984ujr | 90845ds | swiggyin     |  Licensed   | Gaurav    | Ajit       | Gidye    | gauravgidye@benchmarkit.solutions | 7218906971 | Sr.    |



  @Setup
  Scenario: SETUP: Close Browser
    Then user logged out from the application
    And User close browser



