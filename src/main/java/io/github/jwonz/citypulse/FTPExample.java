package io.github.jwonz.citypulse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FTPExample {
    public void upload(String[] args) {
        String server = "ftp.example.com";
        int port = 21;
        String username = "yourUsername";
        String password = "yourPassword";
        String localFilePath = "C:\\path\\to\\local\\file.txt";
        String remoteFilePath = "/path/to/remote/file.txt";

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(server, port);
            ftpClient.login(username, password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            InputStream inputStream = new FileInputStream(localFilePath);
            OutputStream outputStream = ftpClient.storeFileStream(remoteFilePath);
            byte[] bytesIn = new byte[4096];
            int read = 0;
            while ((read = inputStream.read(bytesIn)) != -1) {
                outputStream.write(bytesIn, 0, read);
            }
            inputStream.close();
            outputStream.close();
            boolean completed = ftpClient.completePendingCommand();
            if (completed) {
                System.out.println("File has been successfully uploaded to FTP server.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void download(String[] args) {
        String server = "ftp.example.com";
        int port = 21;
        String username = "yourUsername";
        String password = "yourPassword";
        String remoteFilePath = "/path/to/remote/file.txt";
        String localFilePath = "C:\\path\\to\\local\\file.txt";

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(server, port);
            ftpClient.login(username, password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(localFilePath));
            boolean success = ftpClient.retrieveFile(remoteFilePath, outputStream);
            outputStream.close();

            if (success) {
                System.out.println("File has been successfully downloaded from FTP server.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}