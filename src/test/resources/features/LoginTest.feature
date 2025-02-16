@LoginTest
Feature: Jules Ai Login scenario

  Scenario: TC01_Login_Test
    Given User launches the browser with url
    When User enters email and password and Clicks on Login
    Then Verify user logged in successfully


  Scenario: TC02_Blank_Email_And_Password_Login_Test
    Given User launches the browser with url
    When User not enters email and password and Clicks on Login
    And Verify error message as "Required" for email field
    Then Verify error message for "Required" password field

  Scenario: TC03_Invalid_Email_And_Password_Login_Test
    Given User launches the browser with url
    When User enters email as "test@123.com"
    And User enters password as "1234566"
    And User Clicks on Login
    And Verify error message as "Your email and/or password are incorrects"

  Scenario: TC04_Invalid_Email_And_Password_Login_Test
    Given User launches the browser with url
    When User enters email as "test123gmail.com"
    And User enters password as "1234"
    And Verify error message as "Email not valid" for email field
    Then Verify error message for "Password too short" password field


