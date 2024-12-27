@6in1 @Smoke @Regression
Feature: 6in1 estate contacts Feature

  @Setup
  Scenario Outline: SETUP: Launch Browser and go to application
    Given User launched "chrome"
    And user go to application "$6in1_url"
    When user login using "<user-email>" and "<password>"
    Then user verifies the Home page
    Examples:
      | user-email                           | password |
      | nikhilankam+14@benchmarkit.solutions | Watch@22 |

    Scenario:Verify, add estate contact appears on clicking add button
      When user navigates to the estate contacts tab
      Then user verifies that the left pane contains Name and Roles columns
      When user clicks on the Add Contact button
      Then user verifies the list of global contacts with Add button at the start
      And user verifies that Create New Individual and Create New Entity buttons are displayed

    Scenario: Verify, new individual contact can be created
      When user clicks on the Create New Individual Contact button
      And user fills the details for "New Individual Global Contact"
      And user click on Next button
      Then user verifies global contact created successful message
      #Verify, roles can be assigned for newly added contact.
      And user verifies that the newly created contact is selected by default
      And user selects the Role for Contact
      Then user verifies that the role is assigned successfully
      And user verifies the contact is visible in the Estate Contacts list
      #Verify, notification is displayed on removing the role.
      When user selects the Estate Contact
      And user clicks on Estate-Specific Fields
      And user clicks on Select Role button and uncheck the checked role
      Then user verifies that notification is displayed on removing the role
      #Verify, remove contact from estate button is enabled on removing the role.
      And user saves the Estate Contact without roles
      Then user verifies that the Remove Contact from Estate button is enabled
      #Verify, contact can be removed.
      And user clicks on Remove Contact from Estate button
      And user clicks on Remove button
      Then user verifies global contact removed from estate successful message
      #Verify, removed contact is displayed in the contact list to add in the estate.
      And user clicks on the Add Contact button
      Then user verifies removed contact is displayed in the contact list to add back to the estate
      And user verifies the contact is visible in the Global Contacts list

  @Setup
  Scenario:SETUP: Close Browser
    When user logged out from the application
    Then User close browser