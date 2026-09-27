# StayFinder

> Multi-agent hotel recommendation system built with Java, JADE, Java Swing, and SQL.

[![Java](https://img.shields.io/badge/Java-17%2B-orange)](https://www.oracle.com/java/)
[![JADE](https://img.shields.io/badge/JADE-Multi--Agent%20System-blue)](https://jade.tilab.com/)
[![MySQL](https://img.shields.io/badge/MySQL-Database-blue)](https://www.mysql.com/)
[![Swing](https://img.shields.io/badge/Java-Swing-red)](https://docs.oracle.com/javase/tutorial/uiswing/)

## Overview

StayFinder is a multi-agent accommodation search and recommendation system developed in Java to help users find suitable hotels across Spanish cities.

The application combines a desktop graphical interface, autonomous software agents, relational database access, and context-aware recommendation rules to transform user preferences into ranked accommodation options.

The system was developed as an academic project focused on **Intelligent Systems and Multi-Agent Systems**.

## Objectives

The main objectives of StayFinder are to:

- Provide an intuitive interface for searching accommodation.
- Retrieve available hotels according to user-defined constraints.
- Separate perception, processing, and presentation responsibilities across specialized agents.
- Use contextual recommendation rules to rank accommodation options.
- Demonstrate communication and service discovery in a multi-agent environment.
- Integrate Java, JADE, SQL, and a desktop user interface into a single application.

## User Preferences

Users can define criteria such as:

- Destination city
- Number of guests
- Check-in and check-out dates
- Minimum and maximum nightly price
- Trip type, such as tourism or leisure

These preferences are processed by the agent system and used to retrieve and rank compatible accommodation options.

## System Architecture

StayFinder follows a multi-agent architecture implemented with **JADE (Java Agent DEvelopment Framework)**.

### Main Agents

**UIAgent**

Responsible for the presentation layer and user interaction.

- Collects search preferences from the graphical interface.
- Creates and sends search requests.
- Receives ranked accommodation results.
- Presents recommendations to the user.

**PerceptionAgent**

Responsible for retrieving and filtering accommodation data.

- Receives search requests.
- Queries the SQL database.
- Filters accommodations by destination, dates, capacity, and price.
- Creates the data structures required by the processing layer.
- Discovers the processing service through JADE's Directory Facilitator.

**ProcessingAgent**

Responsible for recommendation and ranking.

- Receives candidate accommodations.
- Applies context-aware scoring rules.
- Considers seasonality and trip type.
- Evaluates accommodation characteristics and amenities.
- Sorts the available options according to their calculated score.

### Communication Flow

```text
User
  │
  ▼
UIAgent
  │
  │ SearchRequest
  ▼
PerceptionAgent
  │
  │ SQL query
  ▼
Relational Database
  │
  │ Matching accommodations
  ▼
PerceptionAgent
  │
  │ Accommodation data
  ▼
ProcessingAgent
  │
  │ Context-aware scoring
  ▼
UIAgent
  │
  ▼
Ranked recommendations
```

Agents communicate through **JADE ACL messages**, while the Directory Facilitator is used for service discovery.

## Recommendation Strategy

The processing layer currently uses a **rule-based scoring system rather than a trained machine-learning model**.

The ranking logic considers multiple accommodation characteristics, including:

- Price per night
- Guest capacity
- Bathrooms per guest
- Accommodation size
- Distance to the city centre
- Customer rating
- Wi-Fi availability
- Parking
- Garden
- Swimming pool
- Air conditioning
- Heating
- Seasonality
- Trip type

Different scoring strategies are selected according to the travel context, allowing the system to adapt the ranking to different combinations of season and trip type.

This approach provides a clear foundation for a future recommendation engine based on configurable weights or machine-learning ranking models.

## Technology Stack

| Technology | Purpose |
|---|---|
| **Java** | Core application and agent implementation |
| **JADE** | Multi-agent architecture, communication, and service discovery |
| **Java Swing** | Desktop graphical user interface |
| **MySQL / SQL** | Accommodation data storage and retrieval |
| **JDBC** | Database connectivity |
| **JCalendar** | Date selection in the user interface |
| **JGoodies** | UI components and styling |
| **JUnit** | Testing support |

## Project Structure

The current repository reflects the original academic Java project structure:

```text
StayFinder/
├── src/
│   ├── es/
│   │   └── upm/
│   │       ├── agentes/
│   │       │   ├── Data.java
│   │       │   ├── Hotel.java
│   │       │   ├── MainGUI.java
│   │       │   ├── PerceptionAgent.java
│   │       │   ├── ProcessingAgent.java
│   │       │   ├── SearchRequest.java
│   │       │   └── UIAgent.java
│   │       └── interfaces/
│   │           ├── InterfazSalida.java
│   │           └── InterfazUsuario.java
│   └── resources/
│       └── Logo.png
│
├── lib/
│   └── Java dependencies
│
├── bin/
│   └── Compiled classes
│
├── APDescription.txt
└── MTPs-Main-Container.txt
```

## Getting Started

### Prerequisites

- Java Development Kit (JDK)
- JADE
- MySQL
- Dependencies included in the `lib/` directory

### Database Configuration

The application requires a MySQL database containing the accommodation and reservation data used by the perception agent.

Before running the application:

1. Create and configure the required MySQL database.
2. Update the database connection settings in `PerceptionAgent.java`.
3. Make sure the required dependencies are available in `lib/`.
4. Start the JADE platform/main container.
5. Launch the application through the main GUI.

> **Security note:** Database credentials should not be committed to the repository. The current implementation reflects the original academic project and should be refactored to use environment variables or external configuration.

## Engineering Concepts

This project demonstrates several software engineering and AI-related concepts:

- Multi-agent system design
- Autonomous software agents
- Agent-to-agent communication
- JADE ACL messaging
- Service discovery
- Event-driven GUI interaction
- Object serialization
- Relational database access
- Context-aware recommendation
- Rule-based scoring
- Separation of concerns
- Object-oriented programming

## Engineering Roadmap

The current implementation provides a functional academic prototype. A production-oriented evolution could include:

### Architecture

- Migrate the project to **Maven or Gradle**.
- Replace the current package structure with a domain-oriented structure.
- Separate domain, application, infrastructure, and presentation layers.
- Extract recommendation strategies into dedicated classes.

### Configuration and Security

- Move database credentials to environment variables.
- Externalize JADE platform configuration.
- Remove machine-specific network configuration from committed files.
- Add a clear configuration profile for local development.

### Quality

- Add unit and integration tests.
- Introduce logging instead of console output.
- Improve database connection management with a connection pool.
- Add automated build and test execution.
- Remove generated `bin/` artifacts from version control.

### Recommendation Engine

- Make recommendation weights configurable.
- Add explainability for recommendation scores.
- Evaluate alternative ranking strategies.
- Introduce a machine-learning ranking model as a future extension.

### Deployment

- Add Docker support for the database and application environment.
- Document the complete local setup.
- Automate application startup and database initialization.

## Academic Context

StayFinder was developed as an academic project focused on **Intelligent Systems and Multi-Agent Systems**.

The project explores how specialized software agents can collaborate to:

1. Capture user requirements.
2. Retrieve relevant information from a database.
3. Process and rank candidate accommodations.
4. Return context-aware recommendations through a graphical interface.

The project provides a practical example of combining distributed agent communication with rule-based recommendation logic.

## Author

**Gonzalo Salas Saiz**

Computer Engineering · Software Engineering · AI Engineering
