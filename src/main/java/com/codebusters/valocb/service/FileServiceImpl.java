package com.codebusters.valocb.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public List<String[]> getRowsFromFileName(String filename, int startedRow) throws IOException {
        Resource resource = new ClassPathResource(filename);

        InputStream inputStream = resource.getInputStream();
        byte[] dataAsBytes = FileCopyUtils.copyToByteArray(inputStream);
        String resourceAsString = new String(dataAsBytes, StandardCharsets.UTF_8);
        // always use higher level interface
        ArrayList<String> strings = new ArrayList<>(Arrays.asList(resourceAsString.split("\n")));
        List<String[]> collect = strings.stream().map(row -> row.split(",")).collect(Collectors.toList());

        // You should check Files API
        // Files.readAllLines(Paths.get(resource.getURI()));

        return collect.subList(startedRow, collect.size());
    }

}
