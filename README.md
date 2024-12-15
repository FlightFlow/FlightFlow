# FlightCoordinator

![GitHub License](https://img.shields.io/github/license/FlightCoordinator/FlightCoordinator)
![GitHub repo size](https://img.shields.io/github/repo-size/FlightCoordinator/FlightCoordinator)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/FlightCoordinator/FlightCoordinator)

## Overview

This project is an automated flight manager app for final year computer engineering project. The project is a full-stack application consisting of a Spring Boot backend, a React frontend with TypeScript, and a Python scripts for automation related algorithms.

The projects also includes utility scripts for configuring the environment, setting up the project, running the application (both frontend and backend at once), and executing tests (for both frontend and backend in order).

## Prerequisites

To setup the project locally, ensure following are present on your system:

- Bash Shell for running provided scripts. (This project is developed in a GNU/Linux environment which is WSL2 with Ubuntu, but you can install the following to a different OS environment to run the project.)

- [Git](https://git-scm.com/)

- [Node.js](https://nodejs.org/en) (version managed with [NVM](https://github.com/nvm-sh/nvm))

- [pnpm](https://pnpm.io/)

- Java Development Kit (JDK)

- [Maven](https://maven.apache.org/)

## Setup

You can setup the project easily using the provided scripts (located in `scripts/` folder in the project root).

### Installing Required Apps

```bash
cd scripts/config # from the project root
./install_apps.sh
```

This script installs:

- nvm: Node Version Manager (then installs the version of node specified in the script).

- pnpm: Package manager for using in the frontend and the project root.

- JDK/JRE: Java Development Kit and Java Runtime Environment for the backend.

- Maven: Build automation tool for backend.

### Setting Up the Project

```bash
cd scripts/config # from the project root
./setup_project.sh
```

This script does the following:

- Renames the `example.application.yml` in the Spring Boot backend to `application.yml`.

- Renams the `.env.sample` in the React frontend to `.env`.

- Installs the frontend dependencies using `pnpm i`.

- Installs the root dependencies using `pnpm i` (only `concurrently` for running the frontend and backend concurrently).

- Runs the `./mvnw clean install` to build the backend.

### Setting Up Environment Variables

Both the frontend and the backend require environment variables to function correctly. The setup script renames sample config files to their correct names.

- **Frontend:** The frontend have a `.env` file (located in `frontend/`) which have contents like the one shown below:

  ```bash
  VITE_APP_SERVER_URL=""
  ```

  This variable should have the backend server url. If you are running the project locally, this would probably be something like `http://localhost:0000` with `0000` replaced with backend port. This port should match the one you put in the backend's config folder.

- **Backend:** The backend have a `application.yml` file (located in `server/src/main/resources`) which have contents like the one shown below:

  ```yml
  spring:
    application:
      name: FlightCoordinator Backend Server
    datasource:
      url: REQUIRED # Database URI
      username: REQUIRED # Username for database connection
      password: REQUIRED # Password for database connection
      driver-class-name: org.postgresql.Driver
    jpa:
      hibernate:
        ddl-auto: validate
      show-sql: false
      properties:
        hibernate:
          format-sql: true
      database: postgresql
      database-platform: org.hibernate.dialect.PostgreSQLDialect

  springdoc:
    swagger-ui:
      path: /swagger.html # The url that will contain the OpenAPI docs

  server:
    port: 8081 # The port that backend will be on

  api:
    version: v1 # Current backend version
  ```

  Port value here should be same as the one in the URL that you wrote at the frontend config file.

Ensure these config files contain valid values before running the application.

## Running the Project

To starth both backend and frontend at the same time, you can use the `run_app.sh` script:

```bash
cd scripts/ # from the project root
./run_app.sh
```

This script uses a npm package named `concurrently` to run the backend and the frontend in parallel, in the same terminal.

## Running the Tests

To run tests for both the backend and the frontend, you can use the `run_tests.sh` script:

```bash
cd scripts/ # from the project root
./run_tests.sh
```

This script executes:

- `pnpm test` for frontend tests.

- `./mvnw test` for backend tests.

## VSCode Configuration

A `.vscode` folder is included in the project with recommended extensions and settings for a consistent development experience. You can install these recommended extensions for better development experience if you decide to work on the project.

## Bug Reports and Feature Requests

We use GitHub templates to streamline reporting bugs and requesting new features. To contribute:

- **Bug Reports:** Open a new issue and select "Bug Report" template. Provide as much detail as possible to help us reproduce the issue.

- **Feature Request:** Open a new issue and select "Feature Request" template. Describe the new feature and its intended purpose clearly.

## License

This project is open-source and licensed under [MIT License](https://github.com/FlightCoordinator/FlightCoordinator/blob/main/LICENSE).
