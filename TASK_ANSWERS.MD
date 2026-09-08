# Answers for Homework Assignment questions and tasks

---

# Test Case Creation

## Test Case 1 - Positive

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-001 |
| **Title** | Add a single product to the shopping list as a signed-in user |
| **Description** | Verify that a signed-in user can add a product from the **Best Sellers** section to the shopping list. |
| **Preconditions** | User account exists. |
| **Test Data** | **Username:** `test@test.com`<br>**Password:** `test123` |

### Test Steps

1. Navigate to the ALDI US homepage.
2. Sign in using the provided credentials.
3. Click **Add to Shopping List** for any product from the **Best Sellers** section.
4. Open the Shopping List.

### Expected Result

- The selected product is displayed in the Shopping List.
- The Shopping List contains exactly one instance of the selected product.

---

## Test Case 2 - Positive

| Field | Value                                                                                                                 |
|-------|-----------------------------------------------------------------------------------------------------------------------|
| **Test Case ID** | TC-002                                                                                                                |
| **Title** | Add a single suggested item to an empty shopping list as a logged-out user                                            |
| **Description** | Verify that a logged-out user can add a product from the **Suggested Items** section when the shopping list is empty. |
| **Preconditions** | User is not signed in.<br> Shopping list is empty.                                                                    |

### Test Steps

1. Navigate to the ALDI US homepage.
2. Click the **Shopping List** button.
3. In the **Suggested Items** section, click **Add to Shopping List** for any product.

### Expected Result

- The empty shopping list message is no longer displayed.
- The **Shop Now** button is no longer displayed.
- The **Suggested Items** section is no longer displayed.
- The selected product is displayed in the Shopping List.
- The Shopping List contains exactly one instance of the selected product.

---

## Test Case 3 - Negative

| Field | Value |
|-------|-------|
| **Test Case ID** | TC-003 |
| **Title** | Attempt to add a quantity exceeding the maximum allowed limit to the shopping list |
| **Description** | Verify that the application prevents users from adding more than the maximum allowed quantity of a product. |
| **Preconditions** | User account exists. |
| **Test Data** | **Username:** `test@test.com`<br>**Password:** `test123` |

### Test Steps

1. Navigate to the ALDI US homepage.
2. Sign in using the provided credentials.
3. Open any product.
4. Click **Add to Shopping List**.
5. Enter **501** as the quantity.
6. Observe the **Add to Shopping List** button.

### Expected Result

- The **Add to Shopping List** button is disabled.
- The product cannot be added to the shopping list.

---

# Sample Bug Report

| Field | Value                                                                                                                                                   |
|-------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Bug ID** | BUG-001                                                                                                                                                 |
| **Title** | Product quantity exceeding the maximum allowed limit can be added to the shopping list                                                                  |
| **Description** | The application allows a product quantity greater than the maximum supported limit (**500**) to be added to the shopping list under certain conditions. |
| **Environment** | ALDI US Website<br> Google Chrome 139<br> Windows 11                                                                                                    |
| **Severity** | High                                                                                                                                                    |
| **Priority** | High                                                                                                                                                    |
| **Preconditions** | User is signed in.<br> Product details page is open.                                                                                                    |

### Steps to Reproduce

1. Navigate to any product page.
2. Click **Add to Shopping List**.
3. Enter **501** as the quantity.
4. Click **Add to Shopping List**.

### Expected Result

- The application should prevent quantities greater than **500** from being submitted.
- The **Add to Shopping List** button should remain disabled or a validation message should be displayed.

### Actual Result

- The product is successfully added to the shopping list with a quantity of **501**.

### Attachments / Evidence

- Screenshot showing the quantity field set to **501**.
- Screenshot of the Shopping List showing the product added with quantity **501**.
- Browser console log.
- Network log (HAR) showing the request payload and server response.

---

# Docker

Docker is a tool that lets you package an application together with everything it needs to run, such as the runtime, libraries, and dependencies, into a container. The main advantage is that the application behaves the same regardless of where it's executed, whether that's on a developer's machine, a test environment, or a CI/CD pipeline.

## Benefits for a QA Engineer

- Simplifies test environment setup and teardown for local execution and CI/CD.
- Provides a consistent execution environment across local machines, test environments, and CI/CD pipelines.
- Makes automated test execution isolated and repeatable.
- Enables parallel execution by running multiple containers simultaneously (e.g. Selenium Grid nodes).
- Makes defects easier to reproduce by sharing the same Docker image or Docker Compose configuration.

## Example

As part of this project, I implemented two Docker-based solutions for running the automated test suite:

- **Dockerfile** – Builds a Docker image using the official Playwright Java image and executes the automated tests.
- **docker-compose.yml** – Demonstrates how the Docker image can be orchestrated and executed using Docker Compose.

Both files are available in the project repository:

- **Dockerfile:**  
  https://github.com/zscsicsoszki/java-playwright-coding-challenge/blob/main/Dockerfile

- **docker-compose.yml:**  
  https://github.com/zscsicsoszki/java-playwright-coding-challenge/blob/main/docker-compose.yml

---

# JUnit + Selenium for Automating the Delete Task Feature

As the task explicitly requires **Selenium** and **JUnit**, I would use **Selenium WebDriver** to automate the user's interactions with the web application and **JUnit** as the test framework.

## Test Flow

1. Navigate to the **Task** page.
2. Verify the page is loaded and the task to be deleted (including the **Delete** button) is displayed.
3. Click the **Delete** button.
4. Confirm the deletion if required (also verify that the confirmation dialog is displayed if expected).
5. Verify that the task has been deleted.

## Example Implementation

```java
class DeleteTaskTest {

    private WebDriver driver;
    private TasksPage tasksPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://example.com/tasks");

        tasksPage = new TasksPage(driver);
    }

    @Test
    void shouldDeleteTask() {
        tasksPage.deleteTask("Test Task");

        assertFalse(tasksPage.isTaskDisplayed("Test Task"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
```

The implementation assumes that a **Page Object** class is already in place containing the required locators and reusable methods.
In a production framework, I would additionally:

- Use explicit waits (e.g. `WebDriverWait`).
- Prepare test data through API calls or database setup/interaction to improve test reliability.

---

# CI Integration

I would integrate the automated tests into the CI pipeline so they're executed automatically whenever new code is pushed or a pull request is created. That way, the team gets immediate feedback if a change introduces a regression, instead of finding it later during manual testing or after deployment.

In the project, I used **GitHub Actions** as the CI tool. The workflow:

- Checks out the repository.
- Builds the Docker image.
- Executes the automated tests inside the container.
- Uploads the generated Cucumber report as a workflow artifact.

## Workflow Triggers

The workflow is executed when:

- Code is pushed to the `main` branch.
- Code is pushed to any branch matching `feature/**` (e.g. `feature/login`, `feature/api-tests`).
- A pull request targets the `main` branch.
- A pull request targets another `feature/**` branch.

Besides GitHub Actions, I'm also familiar with **Jenkins**, where I have configured CI pipelines to execute automated UI and API test suites, publish test reports, and integrate the results into the development workflow.

## Example Workflow Configuration

GitHub Actions workflow:

https://github.com/zscsicsoszki/java-playwright-coding-challenge/blob/main/.github/workflows/playwright-tests.yml

Workflow executions:

https://github.com/zscsicsoszki/java-playwright-coding-challenge/actions