package server;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloServiceInterface extends Remote {
    String sayHello() throws RemoteException;
}
