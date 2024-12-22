package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        List<String> errorLogs = userService.processFile(file);

        if (errorLogs.isEmpty()) {
            return ResponseEntity.ok("File processed successfully!");
        } else {
            return ResponseEntity.status(207).body(errorLogs); // Partial success
        }
    }
}
