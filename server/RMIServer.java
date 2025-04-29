package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import shared.FileServiceImplement;

public class RMIServer {
    public static void main(String[] args) {
        try {
            FileServiceImplement server = new FileServiceImplement();
            Registry registry = LocateRegistry.createRegistry(1099); // Default RMI port
            registry.rebind("FileServer", server);
            System.out.println("File Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

