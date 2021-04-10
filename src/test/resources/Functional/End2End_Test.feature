Feature: End to End API Automation Tests for Pet Store (https://petstore.swagger.io/#/)
Description: API Automation for 4 scenarios with Pet Store.
Pet Store Swagger URL: https://petstore.swagger.io/#/

Background: Perform CRUD Operations with the provided API
    Given APIs are functional and contains data set

Scenario: Fetch all the Pets having status available.
    Given A list of pets are present with status available
        When I search the pets with status as available
            Then all the pets are returned having status as available
        When I add the pet to the pet store with status available
            Then the pet is added to the store having status available
        When I update the pet I added with the status as sold
            Then the pet status changes from available to sold
        When I delete the pet which I created and updated
            Then the pet is removed from the pet store