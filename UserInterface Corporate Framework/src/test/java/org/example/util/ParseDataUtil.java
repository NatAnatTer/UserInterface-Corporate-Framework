package org.example.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.object.ConfigObject;

import java.io.File;
import java.io.IOException;

public class ParseDataUtil {

    private static final ConfigObject configObject;
    private static final File fileTest = new File("src/test/resources/config_test.json");
    private static final File fileSettings = new File("src/test/resources/settings.json");

    static {
        try {
            configObject = parseFile(fileTest, ConfigObject.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseFile(File file, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        T configObject;
        configObject = objectMapper.readValue(file, clazz);
        return configObject;
    }

    public static String parseSettings(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(fileSettings);
            return rootNode.get("application").get("url").asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read config %s", e);
        }
    }

    public static ConfigObject getConfigObject() {
        return configObject;
    }

}
