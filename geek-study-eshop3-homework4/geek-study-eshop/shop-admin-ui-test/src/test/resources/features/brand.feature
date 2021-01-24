Feature: Brand

  Scenario Outline: Successful Login to the page and logout after
    Given I open web browser
    When I navigate to login page
    And I provide username as "<username>" and password as "<password>"
    And I click on login button
    Then name should be "<name>"
    And I click on brand li
    And I click on create button
    And I provide brand as "<brand>"
    And I click on submit button
    Then brand should be "<brand>"

    Examples:
      | username | password | name | brand
      | admin | admin | admin | adidas