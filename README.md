# FlightCoordinator

![GitHub License](https://img.shields.io/github/license/FlightCoordinator/FlightCoordinator)
![GitHub repo size](https://img.shields.io/github/repo-size/FlightCoordinator/FlightCoordinator)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/FlightCoordinator/FlightCoordinator)

## Overview

This project is an automated flight manager app for final year computer engineering project. The project is a full-stack application consisting of a Spring Boot backend, a React frontend with TypeScript, and Python scripts for automation related algorithms.

The project also includes utility scripts for configuring the environment, setting up the project, running the application (both frontend and backend simultaneously), executing tests (for both frontend and backend in sequence), and building the frontend, backend, or the entire application.

## Prerequisites

To setup the project locally, ensure following are present on your system:

- Bash Shell for running provided scripts (This project is developed in a GNU/Linux environment using WSL2 with Ubuntu. However, you can set up and run it on other operating systems by installing the required tools.)
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
- Renames the `.env.sample` in the React frontend to `.env`.
- Installs the frontend dependencies using `pnpm i`.
- Installs the root dependencies using `pnpm i` (only `concurrently` for running the frontend and backend at the same time).
- Runs the `./mvnw clean install` to build the backend.

### Setting Up Environment Variables

Both the frontend and the backend require environment variables to function correctly. The setup script renames sample config files to their correct names.

- **Frontend:** The frontend have a `.env` file (located in `frontend/`) which have contents like the one shown below:

  ```bash
  VITE_APP_SERVER_URL=""
  VITE_APP_SERVER_PORT=""
  VITE_APP_SERVER_API_VERSION=""

  VITE_APP_AUTH0_DOMAIN=""
  VITE_APP_AUTH0_CLIENT_ID=""
  VITE_APP_AUTH0_CALLBACK_URL=""
  ```

  `VITE_APP_SERVER_URL` variable should have the backend server url. If you are running the project locally, this would just be `localhost`. This variable should NOT include the http/https at the beginning, and a slash at the end.

  `VITE_APP_SERVER_PORT` should match the port value you put in the backend's config file (application.yml).

  `VITE_APP_SERVER_API_VERSION` should match the api version in th backend's config file (application.yml).

  The backend request urls, by default, looks like `http://localhost:0000/api/v1/plane/create`. In this url, localhost is the base url for the backend, `0000` is the `VITE_APP_SERVER_PORT` value, `v1` is the `VITE_APP_SERVER_API_VERSION`, `plane` is the controller, and `create` is the action.

  The frontend constructs the request urls for the backend in the requester utility using the environment variables, and some values like `/api/` is added in the requester utility so if you decide to update the backend, do not forget to also update this utility.

  The project uses Auth0 for authentication and authorization, and the last three variables are related to it. You can see [Auth0 React SDK docs](https://auth0.com/docs/libraries/auth0-react) for more information about this variables.

- **Backend:** The backend have a `application.yml` file (located in `server/src/main/resources`) which have contents like the one shown below:

  ```yml
  spring:
  application:
    name: FlightCoordinator Backend Server
  datasource:
    url: jdbc:postgresql://localhost:5432/flightcoordinator_db # Database URI
    username: local # Database Connection Username
    password: local # Database Connection Password
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 10
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
    port: 8081 # The port backend server will be listening
    api-version: v1 # Current backend version
  ```

Port value here should be same as the one in the URL that you wrote at the frontend config file.

Ensure these config files contain valid values before running the application.

## Running the Project

To starth both backend and frontend concurrently, you can use the `run_app.sh` script:

```bash
cd scripts/ # from the project root
./run_app.sh
```

This script uses a npm package named [concurrently](https://www.npmjs.com/package/concurrently) to run the backend and the frontend in parallel, in the same terminal.

## Running the Tests

To run tests for both the backend and the frontend, you can use the `run_tests.sh` script:

```bash
cd scripts/ # from the project root
./run_tests.sh
```

This script executes:

- `pnpm test` for frontend tests.
- `./mvnw test` for backend tests.

## Building the Project

To build the project (frontend, backend, or both) you can use the `build.sh` script:

```bash
cd scripts/ # from the project root
./build.sh <frontend|server|full_app>
```

You can specify which part of the application to build (frontend or backend) by passing `frontend` or `server` as parameter to the script or you can pass `full_app` as parameter to build both.

## VSCode Configuration

A `.vscode` folder is included in the project with recommended extensions and settings for a consistent development experience. You can install these recommended extensions for better development experience if you decide to work on the project.

## Bug Reports and Feature Requests

We use GitHub templates to streamline reporting bugs and requesting new features. To contribute:

- **Bug Reports:** Open a new issue and select "Bug Report" template. Provide as much detail as possible to help us reproduce the issue.
- **Feature Request:** Open a new issue and select "Feature Request" template. Describe the new feature and its intended purpose clearly.

## Documentation

For detailed documentation, please see the [project documentation](https://github.com/FlightCoordinator/Documentation).

## License

This project is open-source and licensed under [MIT License](https://github.com/FlightCoordinator/FlightCoordinator/blob/main/LICENSE).
