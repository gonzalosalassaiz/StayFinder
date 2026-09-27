# 🏨 StayFinder

> Multi-Agent Hotel Recommendation System using Java, JADE and SQL

This repository contains **StayFinder**, a multi-agent accommodation search and recommendation system developed in Java as an academic project focused on **Intelligent Systems and Multi-Agent Systems**.

The project explores how autonomous software agents can collaborate to collect user requirements, retrieve accommodation data from a relational database, and generate context-aware hotel recommendations.

---

## 📌 Overview

Finding suitable accommodation requires considering multiple factors such as price, capacity, dates, location, amenities, seasonality and trip type.

The main objective of StayFinder is to investigate how a **Multi-Agent System** can be used to automate this process and provide personalized accommodation recommendations.

The project follows a complete agent-based workflow:

```text
User Preferences
        │
        ▼
User Interface
        │
        ▼
Perception Agent
        │
        ▼
SQL Database
        │
        ▼
Accommodation Data
        │
        ▼
Processing Agent
        │
        ▼
Context-Aware Ranking
        │
        ▼
Hotel Recommendations
```

---

## 🎯 Objectives

The main objectives of the project are:

* Design and implement a **Multi-Agent System** using JADE.
* Develop a graphical interface for accommodation searches.
* Process user preferences such as destination, dates, guests and price range.
* Retrieve available accommodation data from a relational database.
* Apply context-aware rules to rank accommodation options.
* Explore communication and service discovery between autonomous agents.
* Separate perception, processing and presentation responsibilities.
* Investigate how intelligent recommendation logic can improve accommodation searches.

---

## 🏨 Accommodation Search

Users can define several search parameters through the graphical interface:

* 📍 Destination city
* 👥 Number of guests
* 📅 Check-in and check-out dates
* 💰 Minimum and maximum nightly price
* 🧳 Trip type
* 🏊 Accommodation amenities
* ⭐ Customer ratings

The **PerceptionAgent** processes these preferences and queries the SQL database to identify compatible accommodations.

---

## 🤖 Multi-Agent System

StayFinder is implemented using **JADE (Java Agent DEvelopment Framework)**.

The system is divided into three main agents.

### UIAgent

Responsible for interaction with the user.

* Collects search preferences.
* Creates `SearchRequest` messages.
* Communicates with the perception agent.
* Receives the ranked accommodation results.
* Displays recommendations through the graphical interface.

### PerceptionAgent

Responsible for collecting and filtering accommodation information.

* Receives user search requests.
* Queries the SQL database.
* Filters accommodations according to availability, destination, capacity and price.
* Creates the required data structures.
* Discovers the processing service through JADE's Directory Facilitator.

### ProcessingAgent

Responsible for processing and ranking accommodation options.

* Receives candidate accommodations.
* Applies recommendation rules.
* Considers seasonality and trip type.
* Evaluates accommodation characteristics and amenities.
* Calculates a recommendation score.
* Sorts the available accommodations before returning the results.

---

## 🔄 Agent Communication

The communication architecture can be summarized as follows:

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
  │ SQL Query
  ▼
MySQL Database
  │
  │ Matching Hotels
  ▼
PerceptionAgent
  │
  │ Accommodation Data
  ▼
ProcessingAgent
  │
  │ Recommendation Score
  ▼
UIAgent
  │
  ▼
Ranked Hotel Recommendations
```

Agents communicate through **JADE ACL messages**, while the Directory Facilitator is used to discover available services.

---

## 🧠 Recommendation Logic

The processing layer currently uses a **rule-based scoring approach rather than a trained Machine Learning model**.

The recommendation score can consider several characteristics:

* 💰 Price per night
* 👥 Guest capacity
* 🛁 Bathrooms per guest
* 📐 Accommodation size
* 📍 Distance to the city centre
* ⭐ Customer rating
* 📶 Wi-Fi
* 🚗 Parking
* 🌳 Garden
* 🏊 Swimming pool
* ❄️ Air conditioning
* 🔥 Heating
* ☀️ Seasonality
* 🧳 Trip type

Different scoring strategies are applied depending on the combination of **season and trip type**, allowing the system to adapt the ranking to different travel contexts.

---

## 🛠️ Technologies

<p align="left">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/JADE-2C3E50?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
  <img src="https://img.shields.io/badge/JDBC-007396?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Java%20Swing-5382A1?style=for-the-badge"/>
</p>

### Main Technologies

* **Java** — Core application and agent implementation
* **JADE** — Multi-Agent System and ACL communication
* **Java Swing** — Graphical user interface
* **MySQL** — Relational database
* **JDBC** — Database connectivity
* **JCalendar** — Date selection
* **JGoodies** — UI components and styling
* **JUnit** — Testing support

---

## 📁 Repository Structure

```text
StayFinder/
│
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
│   │       │
│   │       └── interfaces/
│   │           ├── InterfazSalida.java
│   │           └── InterfazUsuario.java
│   │
│   └── resources/
│       └── Logo.png
│
├── lib/
│       └── Java dependencies
│
├── bin/
│       └── Compiled classes
│
├── APDescription.txt
├── MTPs-Main-Container.txt
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites

Make sure you have installed:

* Java Development Kit (JDK)
* JADE
* MySQL
* Project dependencies

Clone the repository:

```bash
git clone https://github.com/gonzalosalassaiz/StayFinder.git
```

Navigate to the project:

```bash
cd StayFinder
```

Configure the MySQL database and update the connection settings in `PerceptionAgent.java`.

Then start the JADE platform and launch the main application.

> **Note:** The project was originally developed as an academic implementation. The current version contains environment-specific configuration that should be externalized before production use.

---

## 🔍 Key Areas Explored

This project combines several areas that are relevant to modern Software Engineering and AI applications:

```text
User Interaction
      ↓
Multi-Agent Systems
      ↓
Agent Communication
      ↓
Data Retrieval
      ↓
Context-Aware Processing
      ↓
Recommendation
      ↓
Ranked Results
```

This makes StayFinder a practical example of applying **agent-based software architecture and intelligent recommendation logic** to a real-world domain.

---

## 📚 Academic Context

This project was developed as an academic project focused on **Intelligent Systems and Multi-Agent Systems**.

**Author:** Gonzalo Salas Saiz  
**Degree:** Computer Engineering  
**Project:** StayFinder — Multi-Agent Hotel Recommendation System

---

## 🔮 Future Improvements

Potential future developments include:

* Migrating the project to **Maven or Gradle**.
* Refactoring the current package structure into a more professional architecture.
* Extracting recommendation strategies into dedicated classes.
* Moving database credentials to environment variables.
* Externalizing JADE platform configuration.
* Adding unit and integration tests.
* Introducing automated build and test execution.
* Improving database connection management.
* Adding configurable recommendation weights.
* Adding recommendation explanations.
* Introducing a Machine Learning ranking model as a future extension.
* Adding Docker support for the database and application environment.
* Building a more modern web-based user interface.

---

## ⭐ About the Project

StayFinder is a project that combines **Software Engineering, Artificial Intelligence, Multi-Agent Systems, databases and user interface development** in a single application.

It represents an early project in my continued development toward **Software Engineering and AI Engineering**, particularly in the areas of intelligent systems, recommendation systems and distributed software architectures.

---

<p align="center">
  <i>Connecting intelligent agents to turn user preferences into personalized hotel recommendations.</i>
</p>
