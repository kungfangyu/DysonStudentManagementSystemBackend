package com.dysonstudentmanagement.dsm.controller.filehandler;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/file")
/*
FileHandler

A rest api for uploading and retrieving file data. THe directory that files are stored in is declared by workingDirectory field.

Original Author: Billy Peters 02/05/2024.
 */
public class FileHandler {

    private static final String workingDirectory = "dsmFiles/";
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> storeFile(@RequestPart("file") MultipartFile file,@RequestPart String filepath) throws IOException {

        File directory = new File(workingDirectory+ filepath);
        if (!directory.exists()) {
            System.out.println(directory.mkdirs());
        }

        File convertFile;
        convertFile = new File((workingDirectory+ filepath + "/" + file.getOriginalFilename()));
        convertFile.createNewFile();

        try (FileOutputStream fout = new FileOutputStream(convertFile)) {
            fout.write(file.getBytes());
        } catch (FileNotFoundException e) {
            throw new IOException("File could not be accessed");
        }

        return ResponseEntity.ok(filepath+"/"+file.getOriginalFilename());
    }

    @GetMapping
    public  ResponseEntity<Resource> getFile(@RequestBody String filelocation) throws MalformedURLException {

        Path filepath = Paths.get(workingDirectory+filelocation);
        File file = filepath.toFile();


        String[] filepathParts = filelocation.split("/");
        String filename = filepathParts[filepathParts.length -1 ];

        if (file.exists()) {
            Resource resource = new UrlResource(file.toURI());

            // Set appropriate headers for the response
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } else {
            throw new RuntimeException("File not found: " + filename);
        }
    }

    @DeleteMapping
    public  ResponseEntity<String> deleteFile(@RequestBody String filelocation){
        Path filepath = Paths.get(workingDirectory + filelocation);
        File file = filepath.toFile();

        if (file.exists()) {
            if (file.delete()) {
                return ResponseEntity.ok("File deleted successfully: " + filelocation);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to delete file: " + filelocation);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("File not found: " + filelocation);
        }
    }
}
