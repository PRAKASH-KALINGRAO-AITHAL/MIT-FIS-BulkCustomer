package com.example.demo.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<String> processFile(MultipartFile file) {
        List<String> errorLogs = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Skip header row
                Row row = sheet.getRow(i);

                try {
                    Long userId = (long) row.getCell(0).getNumericCellValue(); // Assuming userId is numeric
                    String name = row.getCell(1).getStringCellValue();
                    String email = row.getCell(2).getStringCellValue();

                    User user = new User(userId, name, email);
                    userRepository.save(user);
                } catch (Exception e) {
                    logger.error("Error processing row {}: {}", i, e.getMessage());
                    errorLogs.add("Row " + i + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            logger.error("Error reading file: {}", e.getMessage());
            errorLogs.add("File error: " + e.getMessage());
        }

        return errorLogs;
    }
}
