Meta:  
@author Shin Feng  
@theme  Baidu Search  
  
Narrative:

As a user
I want to search the keyword via baidu
So that I can see all the information about the keyword

Scenario: the result can be shown after searching
  
Given initial the browser, '$browser'
And I open the url, '$url'
And the keyword, '$keyword' is input 
When I click the search button
Then the page title should be '$title'
And close the browser

Examples:
| browser	| url					| keyword	| title				|
| chrome	| http://www.baidu.com	| jbehave	| jbehave_百度搜索		|
| chrome	| http://www.baidu.com	| jbehave	| jbehave_搜索		|

