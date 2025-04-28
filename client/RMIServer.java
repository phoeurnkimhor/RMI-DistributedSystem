package client;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import server.HelloServiceInterface;
import server.HelloServiceImpl;

public class RMIServer {
    public static void main(String[] args) {
        try {
            // Create and export the registry
            LocateRegistry.createRegistry(1099);

            // Create the remote object implementation
            HelloServiceInterface remoteObject = new HelloServiceImpl();

            // Bind the remote object to the registry with the correct name
            Naming.rebind("rmi://localhost/MyRemoteService", remoteObject);

            System.out.println("Server is ready...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
