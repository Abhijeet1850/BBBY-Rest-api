

Feature: Fetch the books
  I want to test books dummy api

  
  Scenario: Fetch second book
  When I fetch the books
  Then the total number of books should be "200"
  And the second book name is "ABC"
