package com.codebusters.valocb.service;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public interface FileService {

    List<String[]> getRowsFromFileName(String filename, int startedRow) throws IOException;

    <T> void writeToCsv(List<T> elements, Function<T, String> transformElement, String header, String fileName) throws IOException;
}
