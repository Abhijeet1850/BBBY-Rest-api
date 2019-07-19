@test
Feature: Create Orders
  I want to create bulk orders

  @test
  Scenario: Create Orders
  
  Given I create test Data for Carrier Schedule
   |  Carrier |  nonWorking  | Service    |   Reason   | StartDate   |  EndDate    |
   |   UPS    | 2020-11-11   | UPS GROUND |   HOLIDAY  | 2020-11-10  |  2020-11-11 |
  And I fetch Facility Delivery Schedule with below Request
   | Carrier  | Date         |   NumberofDays |   ServiceLevel   |
   |   UPS    | 2020-11-10   |       2        |    UPS GROUND    | 
  Then I get the below Carrier Delivery Schedule
   |  Carrier  |   Date        | ServiceLevel | Available  |  Reason_Code  |  Code_Desc |
   |    UPS    |  2020-11-10   | UPS GROUND   |   Y        |               |            |
   |    UPS    |  2020-11-11   | UPS GROUND   |   Y        |               |            | 