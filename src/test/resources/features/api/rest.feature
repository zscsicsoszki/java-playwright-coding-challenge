@api-automation
Feature: REST Api testing

  Scenario: Create a new task
    Given a request payload as
    """
    {
      "title": "Test title",
      "description": "Test description"
    }
    """
    When a POST request is sent
    Then the status code is 201
    And the response body is equal to
    """
    {
      "id": 1,
      "title": "Test title",
      "description": "Test description"
    }
    """

  Scenario: Retrieve a task by ID
    When a GET request is sent with id 1
    Then the status code is 200
    And the response body should be equal to "sample-response.json"

  Scenario: Update a task by ID
    Given a request payload from "sample-request.json"
    When a PUT request is sent with id 1
    Then the status code is 204

  Scenario: Delete a task by ID
    When a DELETE request is sent with id 1
    Then the status code is 204
