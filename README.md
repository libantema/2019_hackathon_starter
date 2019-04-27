# 2019 Shasta Networks / SOU CS Club Hackathon - Telnet Starter Project (Java 8)

A starter project that implements a basic multi-threaded server and client handling code.

## Getting Started

Download this project, import into your IDE of choice, and run TelnetServer.main() to start the server. Alternatively, set your build settings to create an application with the TelnetServer.main() entry point.
 
**Note**: you can specify the listening port in the TelnetServer class.

To connect from a terminal, type ```telnet [server.ip.addr.ess] [portNum]``` or use a telnet client to connect to your IP and the specified port.

## Starting Classes
* ```TelnetServer```: The main() entry point into the telnet server. Creates a MultiThreadedServer thread.
* ```MultiThreadedServer```: Starts listening on the specified port for new connections. Creates new Client objects for each connection. 
* ```Client```: Main class to interface with a client. Contains method stubs for handling new connections, disconnects, and receiving/sending text. This will be your direct way to interface with clients.
* ```ClientRunnable```: Parent class for Client, allows for multiple clients to connect at a time.


### Prerequisites

All code is using Java standard libraries, clients can access the server via standard terminals with ```telnet``` installed.


## References and Useful Links

* [MultiThreadedServer](http://tutorials.jenkov.com/java-multithreaded-servers/multithreaded-server.html) - Original reference for the MultiThreadedServer and UserRunnable classes.
* [MessageFormatter](https://github.com/amahdy/telnet-chat/blob/master/telnet-chat/src/net/amahdy/chat/tools/MessageFormatter.java) - Colors and Text Format for Messages

## Submissions

Submission rules TBD. Will require a GitHub account.

## Versioning

Git should be used for versioning in this project.

## Authors

* **Chandler Severson** - Initial work
