package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface FileServerInterface extends Remote {
    void uploadFile(String filename, byte[] data) throws RemoteException;
    byte[] downloadFile(String filename) throws RemoteException;
    List<String> listFiles() throws RemoteException;
}