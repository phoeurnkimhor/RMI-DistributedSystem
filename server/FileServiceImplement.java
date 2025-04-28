package server;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.io.*;
import java.util.*;

public class FileServiceImplement extends UnicastRemoteObject implements FileServerInterface {
    private static final String STORAGE_DIR = "server_storage";

    protected FileServiceImplement() throws RemoteException {
        super();
        File dir = new File(STORAGE_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    @Override
    public void uploadFile(String filename, byte[] data) throws RemoteException {
        try (FileOutputStream fos = new FileOutputStream(STORAGE_DIR + "/" + filename)) {
            fos.write(data);
            System.out.println("Uploaded: " + filename);
        } catch (IOException e) {
            throw new RemoteException("File upload failed", e);
        }
    }

    @Override
    public byte[] downloadFile(String filename) throws RemoteException {
        try {
            File file = new File(STORAGE_DIR + "/" + filename);
            if (!file.exists()) {
                throw new RemoteException("File not found");
            }
            byte[] fileData = new byte[(int) file.length()];
            try (FileInputStream fis = new FileInputStream(file)) {
                fis.read(fileData);
            }
            return fileData;
        } catch (IOException e) {
            throw new RemoteException("File download failed", e);
        }
    }

    @Override
    public List<String> listFiles() throws RemoteException {
        File dir = new File(STORAGE_DIR);
        String[] files = dir.list();
        return Arrays.asList(files != null ? files : new String[0]);
    }
}