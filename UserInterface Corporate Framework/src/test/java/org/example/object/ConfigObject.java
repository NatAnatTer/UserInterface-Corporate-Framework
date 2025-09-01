package org.example.object;

public record ConfigObject(String timer, int countOfInterests, String filePath,
                           int countOfPauseOfUploadImages,
                           PersonalDataObject personalDataObject) {
}
