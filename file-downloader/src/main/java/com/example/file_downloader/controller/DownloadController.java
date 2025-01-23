package com.example.file_downloader.controller;

import com.example.file_downloader.model.DownloadRequest;
import com.example.file_downloader.service.DownloadService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/download")
public class DownloadController {

    private final DownloadService downloadService;

    public DownloadController(DownloadService downloadService) {
        this.downloadService = downloadService;
    }

    @PostMapping
    public String downloadFiles(@RequestBody DownloadRequest request) {
            downloadService.downloadFiles(request.getUrls(), request.getSaveDir(), request.getSpeedLimitKBps());
        return "Download started!";
    }
}
