# Java RMI Example

This project demonstrates a simple Java RMI (Remote Method Invocation) application with a server and a client. The server offers a remote service, and the client invokes the remote method to retrieve a message from the server.

## Project Structure


### Prerequisites

- Java Development Kit (JDK) 8 or higher installed.
- `rmiregistry` tool (comes with the JDK).

### Setting Up the RMI Server and Client

Follow these steps to set up and run the RMI server and client.

#### 1. **Compile the Code**

You need to compile both the server-side and client-side Java files. Open your terminal and navigate to the root directory of the project (where `server/` and `client/` directories are located). Then, run the following command to compile both the server and client files:

```bash
javac server/*.java client/*.java
javac -d . server/*.java client/*.java shared/*.java


java RMIServer


Server is ready...


java RMIClient


Server Response: Hello from the server!



+-----------------+                        +------------------------+
|   Client App    |                        |   Remote Object Impl   |
+--------+--------+                        +-----------+------------+
         |                                             ^
         | 1. Call method on stub                      |
         v                                             |
+--------+--------+                                    |
|      Stub       |                                    |
| (Client Proxy)  |                                    |
+--------+--------+                                    |
         |                                             |
         | 2. Marshal method name & arguments          |
         | 3. Send over network (TCP/IP)               |
         v                                             |
+------------------------+                    +--------+---------+
|   Transport Layer       | --------------->  |  Transport Layer |
+------------------------+                    +------------------+
         |                                             ^
         v                                             |
+------------------------+                    +--------+--------+
|   Skeleton / Dispatcher | <---------------  | Unmarshal data  |
+------------------------+                    +--------+--------+
         |                                             ^
         | 4. Invoke actual method                     |
         v                                             |
+------------------------+                    +--------------------------+
|  Remote Object Impl     |                   | Return value or exception|
+------------------------+                    +--------------------------+
         |                                             ^
         | 5. Marshal result or exception              |
         | 6. Send back over network (TCP/IP)          |
         v                                             |
+------------------------+                    +------------------+
|   Transport Layer       | <---------------  |  Transport Layer |
+------------------------+                    +------------------+
         | 
         v
+--------+--------+
|      Stub       |
+--------+--------+
         |
         | 7. Unmarshal result
         v
+--------+--------+
|   Client App    |
+-----------------+
