package org.example.util;

import org.example.object.ConfigObject;
import org.slf4j.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;

public class FileUploader {
    private static final Logger logger = LoggerUtil.getLogger(FileUploader.class);
    private static final ConfigObject configObject = ParseDataUtil.getConfigObject();
    public static void uploadFile(String filePath)  {
        logger.info("Ввод пути к файлу с изображением профиля");
        try {
            Robot robot = new Robot();
            robot.delay(configObject.countOfPauseOfUploadImages());
            for (char c : filePath.toCharArray()) {
                int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
                if (keyCode != KeyEvent.VK_UNDEFINED) {
                    if (':' == c) {
                        robot.keyPress(KeyEvent.VK_SHIFT);
                        robot.keyPress(KeyEvent.VK_SEMICOLON);
                        robot.keyRelease(KeyEvent.VK_SEMICOLON);
                        robot.keyRelease(KeyEvent.VK_SHIFT);
                    } else {
                        if (Character.isUpperCase(c)) {
                            robot.keyPress(KeyEvent.VK_SHIFT);
                        }
                        robot.keyPress(keyCode);
                        robot.keyRelease(keyCode);
                        if (Character.isUpperCase(c)) {
                            robot.keyRelease(KeyEvent.VK_SHIFT);
                        }
                    }
                } else {
                    System.err.printf("Cannot type character: %c%n", c);
                }
            }
            robot.delay(configObject.countOfPauseOfUploadImages());
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(configObject.countOfPauseOfUploadImages());
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(configObject.countOfPauseOfUploadImages());
        }catch (AWTException e) {
            System.err.printf("Error uploading file: %s, error: %s%n", filePath, e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            Thread.currentThread().interrupt();
            System.err.printf("Upload interrupted for file: %s, error: %s%n%n", filePath, e.getMessage());
            e.printStackTrace();
        }
    }
}
