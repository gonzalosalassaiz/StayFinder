# StayFinder

> Multi-agent hotel recommendation system built with Java, JADE, Java Swing, and SQL.

## Overview

StayFinder is a multi-agent accommodation search and recommendation system designed to help users find suitable hotels across Spanish cities.

The application combines a graphical user interface, agent-based communication, SQL data retrieval, and rule-based recommendation logic to transform user preferences into ranked accommodation options.

Users can define criteria such as:

- Destination city
- Number of guests
- Check-in and check-out dates
- Minimum and maximum nightly price
- Trip type, such as tourism or leisure

The system retrieves matching accommodation data from a relational database and applies context-aware scoring rules based on seasonality, trip type, price, location, capacity, and available amenities.

## Architecture

StayFinder follows a multi-agent architecture implemented with **JADE (Java Agent DEvelopment Framework)**.

### Main agents

**UIAgent**
- Handles interaction with the graphical user interface.
- Collects the user's search preferences.
- Sends search requests to the perception layer.
- Displays the ranked accommodation results.

**PerceptionAgent**
- Receives the user's search request.
- Queries the SQL database for compatible accommodations.
- Filters results according to availability, destination, capacity, and price constraints.
- Sends the retrieved accommodation data to the processing agent.

**ProcessingAgent**
- Receives the candidate accommodations.
- Applies recommendation and scoring rules.
- Considers factors such as seasonality, trip type, price, location, capacity, and amenities.
- Ranks the available accommodations before returning the results.

### Communication flow

```text
User
  │
  ▼
UIAgent
  │  SearchRequest
  ▼
PerceptionAgent
  │  SQL query
  ▼
Relational Database
  │  Matching accommodations
  ▼
PerceptionAgent
  │  Accommodation data
  ▼
ProcessingAgent
  │  Context-aware scoring
  ▼
UIAgent
  │
  ▼
Ranked recommendations
```

Agents communicate through JADE using ACL messages and service discovery through the Directory Facilitator.

## Recommendation Logic

The processing layer uses a rule-based scoring system rather than a trained machine-learning model.

The recommendation score can take into account:

- **Price per night**
- **Guest capacity**
- **Bathrooms per guest**
- **Accommodation size**
- **Distance to the city centre**
- **Customer rating**
- **Wi-Fi**
- **Parking**
- **Garden**
- **Swimming pool**
- **Air conditioning**
- **Heating**
- **Seasonality**
- **Trip type**

Different scoring strategies are applied depending on the combination of season and trip type, allowing the system to adapt recommendations to different travel contexts.

## Technology Stack

| Technology | Purpose |
|---|---|
| Java | Core application and agent implementation |
| JADE | Multi-agent system and ACL communication |
| Java Swing | Desktop graphical user interface |
| MySQL / SQL | Accommodation data storage and retrieval |
| JDBC | Database connectivity |
| JCalendar | Date selection in the UI |
| JGoodies | UI components and styling |
| JUnit | Testing support |

## Project Structure

The repository currently follows a Java project structure:

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

## Running the Project

The project requires:

- Java Development Kit (JDK)
- JADE
- MySQL
- The dependencies included in the `lib/` directory

Before running the application, configure the database connection in `PerceptionAgent.java` with the appropriate local MySQL credentials and database configuration.

The project also relies on a JADE main container and platform configuration.

> **Note:** The current repository reflects the original academic implementation. A future iteration can migrate the project to a standard Maven or Gradle structure and move environment-specific configuration outside the source code.

## Engineering Considerations

This project demonstrates several software engineering concepts relevant to distributed and intelligent systems:

- Multi-agent system design
- Agent-to-agent communication
- Service discovery
- Object serialization
- Event-driven GUI interaction
- Relational database access
- Context-aware recommendation logic
- Separation between perception, processing, and presentation responsibilities

## Future Improvements

Potential improvements include:

- Migrate the project to **Maven or Gradle**
- Replace hard-coded database credentials with environment variables
- Extract recommendation rules into dedicated strategy classes
- Add unit and integration tests
- Improve database connection management with a connection pool
- Replace generated `bin/` artifacts with build automation
- Introduce a clearer domain/application/infrastructure structure
- Add logging instead of console output
- Add configurable recommendation weights
- Introduce a machine-learning ranking model as an alternative to the current rule-based scoring system
- Add Docker support for the database and application environment

## Academic Context

StayFinder was developed as an academic project focused on **Intelligent Systems and Multi-Agent Systems**, exploring how autonomous software agents can collaborate to process user requests and generate context-aware recommendations.

## Author

**Gonzalo Salas Saiz**

Computer Engineering · Software Engineering · AI Engineering
