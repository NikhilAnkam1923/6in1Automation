@Centrifi @Smoke @Regression
Feature: Centrifi Login Feature

  @Setup
  Scenario: SETUP: Launch Browser and go to application
    Given User launched "chrome"

  Scenario: verify_login_with_valid_email
    Given User go to application "$centrifi_url"

  @Setup
  Scenario: SETUP: Close Browser
    Then User close browser