package server;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServer {

    public static void main(String[] args) {
        try {
            // Create and export a registry on port 1099
            LocateRegistry.createRegistry(1099);

            // Create a remote object
            HelloServiceImpl remoteObject = new HelloServiceImpl();

            // Bind the remote object to the RMI registry
            Naming.rebind("MyRemoteService", remoteObject);

            System.out.println("Server is ready...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

