package         com.example.file_downloader.model;

import lombok.Data;

import java.util.List;

@Data
public class DownloadRequest {
    private List<String> urls; // Список URL для
    private String saveDir;    // Директория для сохранения файлов
    private int speedLimitKBps = 500; // Ограничение скорости (по умолчанию 500 КБ/с)
}
