package com.nhnacademy.jdbc.board.file.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
@Controller
public class FileController {
    @GetMapping("/fileDownload/{fileName}")
    public void getDownload(@PathVariable String fileName,
                            HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        ServletContext servletContext = request.getServletContext();
        String path = servletContext.getRealPath("");

        String fullPath = path + fileName;
        File downloadFile = new File(fullPath);
        OutputStream outStream;
        try (FileInputStream inputStream = new FileInputStream(downloadFile)) {

            String mimeType = servletContext.getMimeType(fullPath);
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

            response.setContentType(mimeType);
            response.setContentLength((int) downloadFile.length());

            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"",
                    downloadFile.getName());
            response.setHeader(headerKey, headerValue);

            outStream = response.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

        }
        outStream.close();
    }
}
