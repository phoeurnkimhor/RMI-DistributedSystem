package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;
import shared.FileServerInterface;

public class RMIClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            FileServerInterface server = (FileServerInterface) registry.lookup("FileServer");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter your user ID: ");
            String userId = scanner.nextLine();

            while (true) {
                System.out.println("\n=== Remote File Client ===");
                System.out.println("Options: [list] [upload] [download] [exit]");
                System.out.print("Choose option: ");
                String option = scanner.nextLine();

                if (option.equalsIgnoreCase("list")) {
                    List<String> files = server.listFiles(userId);
                    if (files.isEmpty()) {
                        System.out.println("No files found on server.");
                    } else {
                        System.out.println("Files on Server:");
                        for (String file : files) {
                            System.out.println("- " + file);
                        }
                    }

                } else if (option.equalsIgnoreCase("upload")) {
                    System.out.print("Enter full path of file to upload: ");
                    String filePath = scanner.nextLine();
                    Path path = Paths.get(filePath);

                    if (Files.exists(path)) {
                        byte[] data = Files.readAllBytes(path);
                        server.uploadFile(userId, path.getFileName().toString(), data);
                        System.out.println("Uploaded: " + path.getFileName());
                    } else {
                        System.out.println("File does not exist: " + filePath);
                    }

                } else if (option.equalsIgnoreCase("download")) {
                    System.out.print("Enter filename to download: ");
                    String filename = scanner.nextLine();

                    try {
                        byte[] data = server.downloadFile(userId, filename);
                        Path downloadsDir = Paths.get("client_downloads");
                        if (!Files.exists(downloadsDir)) Files.createDirectory(downloadsDir);

                        Files.write(downloadsDir.resolve(filename), data);
                        System.out.println("Downloaded to client_downloads/" + filename);
                    } catch (Exception e) {
                        System.out.println("Error downloading file: " + e.getMessage());
                    }

                } else if (option.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting client.");
                    break;
                } else {
                    System.out.println("Invalid option. Try again.");
                }
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}