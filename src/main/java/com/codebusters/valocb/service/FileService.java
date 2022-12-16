package com.codebusters.valocb.service;

import java.io.IOException;
import java.util.List;

public interface FileService {

    List<String[]> getRowsFromFileName(String filename, int startedRow) throws IOException;
}
