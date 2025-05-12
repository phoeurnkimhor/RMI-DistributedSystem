package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface FileServerInterface extends Remote {
    void uploadFile(String userId, String filename, byte[] data) throws RemoteException;
    byte[] downloadFile(String userId, String filename) throws RemoteException;
    List<String> listFiles(String userId) throws RemoteException;
}