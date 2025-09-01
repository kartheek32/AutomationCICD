Feature: Purchase the order from Ecommerce Website
	I want to this template for my feature files
	
	Background:
	Given I landed on Ecommerce Page
	
	@Regression
	Scenario Outline: Positive test for submitting order
	Given Logged in with username <name> and password <password>
	When I add product <productName> to cart
	And Checkout <productName> and submit the order
	Then "THANKYOU FOR THE ORDER." message displayed on ConfirmationPage
	
	Examples:
		|name                 |password   |productName|
		|virat1@gmail.com     |Virat@123  |IPHONE 13 PRO|
		
		
