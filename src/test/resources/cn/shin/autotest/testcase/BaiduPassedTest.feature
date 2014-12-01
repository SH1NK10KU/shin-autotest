Feature: Cucumber测试用例

  Scenario: 使用Chrome浏览器搜索
    Given 打开chrome浏览器
    And 打开网页http://www.baidu.com
    And 搜索Cucumber
    When 点击搜索按钮
    Then 页面标题Cucumber_百度搜索
    But 关闭浏览器