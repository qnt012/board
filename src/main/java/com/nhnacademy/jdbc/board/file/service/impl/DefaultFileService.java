package com.nhnacademy.jdbc.board.file.service.impl;

import com.nhnacademy.jdbc.board.file.mapper.FileMapper;
import com.nhnacademy.jdbc.board.file.service.FileService;
import org.springframework.stereotype.Service;

@Service
public class DefaultFileService implements FileService {
    private final FileMapper fileMapper;

    public DefaultFileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Override
    public void uploadFile(long postNum, String fileName) {
        fileMapper.insertFile(postNum, fileName);
    }

    @Override
    public String getFileName(long postNum) {
        return fileMapper.selectFileName(postNum);
    }
}
