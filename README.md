# Demo project to demonstrate Playwright UI & API Automation Framework

## Overview

This project is a Java-based test automation framework built using **Playwright**, **Cucumber**, and **Spring Framework**.

It demonstrates automated testing for both **UI** and **REST API** scenarios following the Page Object Model (POM) and a layered API design. 
The API tests serve as a **sample framework setup** for the interview task and demonstrate how REST API automation is structured within the project.

---

## Prerequisites

- Java 21
- Maven 3.9+
- Google Chrome, Mozilla Firefox or Safari/WebKit (for local execution)
- Docker (optional, for containerized execution)

---

## Tools & Technologies

- Java 21
- Maven
- Playwright
- Cucumber
- Spring Framework (Dependency Injection)
- Lombok
- Docker
- GitHub Actions

---

## Running the Tests

Run all automated tests:

```bash
mvn clean install
```

Run UI tests only:

```bash
mvn clean install -Pui-tests
```

Run API tests only:

```bash
mvn clean install -Papi-tests
```

The **default Maven profile** executes all available automated tests.

---

## Configuration

The following configuration options are available:

| Property | Description | Default  |
|----------|-------------|----------|
| `browserName` | Browser to execute UI tests (`chrome`, `firefox`, `safari`) | `chrome` |
| `headless` | Executes browser in headless mode | `true`   |

Example:

```bash
mvn clean install -Pui-tests -DbrowserName=firefox -Dheadless=false
```

---

## Reports

After execution, the generated Cucumber HTML report is available at:

```text
target/cucumber-pretty.html
```

---

## CI/CD

A **GitHub Actions** workflow is included.

On every push to the **main** branch, the workflow:

1. Builds the Docker image.
2. Executes the automated tests inside the container.
3. Collects the generated test artifacts.
4. Uploads the Cucumber report as a workflow artifact.

---

## Docker

The project includes a Dockerfile for running the automated test suite in an isolated environment.

Build the image:

```bash
docker build -t playwright-automation .
```

Run the tests:

```bash
docker run --rm playwright-automation
```

A sample **docker-compose.yml** is also included. It provides an alternative way to execute the automated tests:

```bash
docker compose up --build
```
