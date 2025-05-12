package shared;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.io.*;
import java.util.*;

public class FileServiceImplement extends UnicastRemoteObject implements FileServerInterface {
    private static final String STORAGE_DIR = "server_storage";
    private static final String LOG_FILE = "log/server_log.txt";

    public FileServiceImplement() throws RemoteException {
        super();
        File dir = new File(STORAGE_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    // Logs user actions to a log file
    private void logAction(String userId, String action, String filename) {
        String timestamp = new Date().toString();
        String logMessage = String.format("[%s] User: %s Action: %s File: %s\n", timestamp, userId, action, filename);

        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(logMessage);
            // Display log on the console as well
            System.out.println(logMessage);
        } catch (IOException e) {
            System.err.println("Logging failed: " + e.getMessage());
        }
    }

    @Override
    public void uploadFile(String userId, String filename, byte[] data) throws RemoteException {
        try (FileOutputStream fos = new FileOutputStream(STORAGE_DIR + "/" + filename)) {
            fos.write(data);
            System.out.println("Uploaded: " + filename);
            logAction(userId, "UPLOAD", filename);
        } catch (IOException e) {
            throw new RemoteException("File upload failed", e);
        }
    }

    @Override
    public byte[] downloadFile(String userId, String filename) throws RemoteException {
        try {
            File file = new File(STORAGE_DIR + "/" + filename);
            if (!file.exists()) {
                throw new RemoteException("File not found");
            }
            byte[] fileData = new byte[(int) file.length()];
            try (FileInputStream fis = new FileInputStream(file)) {
                fis.read(fileData);
            }
            logAction(userId, "DOWNLOAD", filename);
            return fileData;
        } catch (IOException e) {
            throw new RemoteException("File download failed", e);
        }
    }

    @Override
    public List<String> listFiles(String userId) throws RemoteException {
        File dir = new File(STORAGE_DIR);
        String[] files = dir.list();
        logAction(userId, "LIST_FILES", "-");
        return Arrays.asList(files != null ? files : new String[0]);
    }
}