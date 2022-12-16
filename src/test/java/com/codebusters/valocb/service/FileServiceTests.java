package com.codebusters.valocb.service;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FileServiceTests {

    FileService fileService = null;

    @BeforeEach
    void init() {
        this.fileService = new FileServiceImpl();
    }

}
