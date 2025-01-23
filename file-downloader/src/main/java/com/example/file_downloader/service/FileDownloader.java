package com.example.file_downloader.service;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

@Service
public class FileDownloader {

    public void downloadFile(String fileUrl, String saveDir, int speedLimitKBps) throws Exception {
        URL url = new URL(fileUrl);
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();

        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        FileOutputStream outputStream = new FileOutputStream(saveDir + "/" + fileName);

        byte[] buffer = new byte[1024];
        int bytesRead;
        long startTime = System.currentTimeMillis();

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);

            // Ограничение скорости загрузки
            long elapsedTime = System.currentTimeMillis() - startTime;
            long expectedTime = (bytesRead * 1000L) / (speedLimitKBps * 1024L);
            if (expectedTime > elapsedTime) {
                Thread.sleep(expectedTime - elapsedTime);
            }
            startTime = System.currentTimeMillis();
        }

        outputStream.close();
        inputStream.close();
    }
}
