Feature: Cucumber Test Case

  Scenario: set data
    Given Open the browser, chrome
    And Go to the page, http://www.baidu.com
    And Search the content, Cucumber
    When Click the search button
    Then Page title is Cucumber
    But Close the browser