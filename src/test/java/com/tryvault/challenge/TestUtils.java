package com.tryvault.challenge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.util.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    public static <T> List<T> readFile(String filePath, Class<T> clazz) throws JsonProcessingException {
        File file = new File(filePath);
        List<String> lines = FileUtil.readAsLines(file);
        ObjectMapper mapper = new ObjectMapper();
        List<T> result = new ArrayList<>();
        for (String line: lines) {
            result.add(mapper.readValue(line, clazz));
        }
        return result;
    }
}
