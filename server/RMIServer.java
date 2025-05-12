package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import shared.FileServiceImplement;

public class RMIServer {
    public static void main(String[] args) throws Exception {
        // Custom socket factories to log connections
        RMIClientSocketFactory clientFactory = new LoggingRMIClientSocketFactory();
        RMIServerSocketFactory serverFactory = new LoggingRMIServerSocketFactory();

        // Initialize FileServiceImplement and export it
        FileServiceImplement impl = new FileServiceImplement();

        // Create the registry on port 1099
        Registry registry = LocateRegistry.createRegistry(1099, clientFactory, serverFactory);
        registry.rebind("FileServer", impl);

        // Print a message indicating the server is running
        System.out.println("File Server is running with logging socket factories...");
    }

    // Logs when the *client* side opens a socket to the server
    public static class LoggingRMIClientSocketFactory implements RMIClientSocketFactory, Serializable {
        @Override
        public Socket createSocket(String host, int port) throws IOException {
            System.out.printf("[%s] Outbound connection to %s:%d%n", new java.util.Date(), host, port);
            return new Socket(host, port);
        }
    }

    // Logs when the *server* side accepts an incoming connection
    public static class LoggingRMIServerSocketFactory implements RMIServerSocketFactory, Serializable {
        @Override
        public ServerSocket createServerSocket(int port) throws IOException {
            return new ServerSocket(port) {
                @Override
                public Socket accept() throws IOException {
                    Socket s = super.accept();
                    String remote = s.getInetAddress().getHostAddress() + ":" + s.getPort();
                    System.out.printf("[%s] Inbound connection from %s%n", new java.util.Date(), remote);
                    return s;
                }
            };
        }
    }
}