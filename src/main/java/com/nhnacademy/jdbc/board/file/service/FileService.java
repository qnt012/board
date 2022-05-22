package com.nhnacademy.jdbc.board.file.service;

public interface FileService {
    void uploadFile(long postNum, String fileName);
    String getFileName(long postNum);
}
