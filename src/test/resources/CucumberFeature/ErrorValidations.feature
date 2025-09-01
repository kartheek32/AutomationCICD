Feature: Error Validations
	I want to use this template for my feature file
	
	@ErrorValidation
	Scenario Outline:Error validation test scenario
		Given I landed on Ecommerce Page
			When Logged in with username <name> and password <password>
				Then "Incorrect email or password." message is displayed
				
						Examples:
						|name                 |password   |
						|virat1@gmail.com     |Virat@1234  |