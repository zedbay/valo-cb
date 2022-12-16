package com.codebusters.valocb.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public List<String[]> getRowsFromFileName(String filename, int startedRow) throws IOException {
        Resource resource = new ClassPathResource(filename);
        InputStream inputStream = resource.getInputStream();
        byte[] dataAsBytes = FileCopyUtils.copyToByteArray(inputStream);
        String resourceAsString = new String(dataAsBytes, StandardCharsets.UTF_8);
        List<String> strings = new ArrayList<>(Arrays.asList(resourceAsString.split("\n")));
        List<String[]> collect = strings.stream()
                .map(row -> row.split(",(?!\\d\")"))
                .collect(Collectors.toList());
        return collect.subList(startedRow, collect.size());
    }

    @Override
    public <T> void writeToCsv(List<T> elements,Function<T, String> transformElement, String header, String fileName) throws IOException {
        String elementsToString = elements.stream()
                .map(transformElement)
                .collect(Collectors.joining("\n"));

        String fileContentAsString = header + "\n" + elementsToString;
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(fileContentAsString);
        writer.close();
    }

}
