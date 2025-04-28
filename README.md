# RMI Distributed System

## Overview
This project demonstrates a basic **Distributed System** using **Java RMI (Remote Method Invocation)**. The system allows different machines (clients and servers) to communicate with each other by invoking methods on remote objects over a network.

## Features
- **Remote Object Communication**: Clients can invoke methods on remote objects hosted by the server.
- **Distributed Processing**: The client-server model, with distributed processing powered by RMI.
- **Object Serialization**: Remote objects are serialized and sent over the network.
- **Simple Example**: Demonstrates a basic "HelloWorld" example and a calculation service.

## Technologies
- **Java 8 or later**
- **RMI (Remote Method Invocation)**
- **Socket Communication**
- **Java Security Manager (for sandboxing)**

## Setup and Installation

### Prerequisites
Ensure you have the following installed on your machine:
- **Java Development Kit (JDK)** version 8 or later
- A **terminal/command prompt**
- **RMI Registry** (typically bundled with JDK)

### Installation Steps
1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-repository/rmi-distributed-system.git
   cd rmi-distributed-system