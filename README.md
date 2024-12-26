# Pokedex-be

Pokedex-be is a backend application designed to interact with the [PokeAPI v2](https://pokeapi.co/) to fetch and provide details about various Pokémon. This backend serves as the API layer to handle requests related to Pokémon data.

## Prerequisites

Before running the project, ensure that you have the following prerequisites installed on your system:

- **Java**: Version 17
- **IDE**: The project can be developed and run in any of the following IDEs:
    - IntelliJ IDEA
    - Eclipse
    - Visual Studio Code

## Setup

Follow the steps below to set up and run the application:

### 1. Clone the Repository

Start by cloning the repository to your local machine:

```bash
git clone https://github.com/yourusername/pokedex-be.git
```
## 2. Run the Application

Once you’ve cloned the repository, navigate to the project folder and run the main application.

### Using IntelliJ IDEA:
- Open the project in IntelliJ IDEA.
- Click the green run button or select **Run > Run 'Main'** from the menu to start the application.

### Using Eclipse:
- Import the project into Eclipse.
- Right-click on the main class and select **Run As > Java Application**.

### Using Visual Studio Code:
- Open the project in Visual Studio Code.
- Run the application using the terminal command or configure a Java extension in VS Code to run the project.


## 3. Access the Coverage Report

After building the project, you can view the code coverage report by navigating to the following URL:

```bach
/target/site/index.html
```

Open this file in your browser to view the test coverage details and additional information about the application's test suite.

## Project Structure

The Pokedex-be project follows a typical Java application structure:

- **src/main/java**: Contains the core controller, service application logic and important config file.
- **src/test/java**: Contains unit and integration tests.
- **target/**: Generated during the build process, contains compiled files and coverage reports.
