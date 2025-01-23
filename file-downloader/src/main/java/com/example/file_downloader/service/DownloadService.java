package com.example.file_downloader.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class DownloadService {

    private final FileDownloader fileDownloader;

    public DownloadService(FileDownloader fileDownloader) {
        this.fileDownloader = fileDownloader;
    }

    public void downloadFiles(List<String> urls, String saveDir, int speedLimitKBps) {
        ExecutorService executorService = Executors.newFixedThreadPool(3); // Используем 3 потока

        for (String url : urls) {
            executorService.submit(() -> {
                try {
                    fileDownloader.downloadFile(url, saveDir, speedLimitKBps);
                    System.out.println("Downloaded: " + url);
                } catch (Exception e) {
                    System.err.println("Error downloading file: " + url);
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }
}
