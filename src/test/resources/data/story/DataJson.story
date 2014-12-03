Meta:  
@author Shin Feng  
@theme  DataJson  
  
Narrative:

As a user
I want to initialize a DataJson
So that I can use the POJO

Scenario: 初始化DataJson成功
  
Given 有一个DataJson
When 创建DataJson：
| FirstName	| LastName	|
| Shin		| Feng		|
Then 姓应该是：Feng
