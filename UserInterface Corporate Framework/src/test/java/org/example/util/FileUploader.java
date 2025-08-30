package org.example.util;

import org.slf4j.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;

public class FileUploader {
    private static final Logger logger = LoggerUtil.getLogger(FileUploader.class);
    public static void uploadFile(String filePath) throws AWTException, InterruptedException {
        logger.info("Ввод пути к файлу с изображением профиля");
        Robot robot = new Robot();
        Thread.sleep(1000);
        for (char c : filePath.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (keyCode != KeyEvent.VK_UNDEFINED) {
                if( ':' == c ){
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_SEMICOLON);
                    robot.keyRelease(KeyEvent.VK_SEMICOLON);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else {
                    if(Character.isUpperCase(c)){
                        robot.keyPress(KeyEvent.VK_SHIFT);
                    }
                    robot.keyPress(keyCode);
                    robot.keyRelease(keyCode);

                    if(Character.isUpperCase(c)){
                        robot.keyRelease(KeyEvent.VK_SHIFT);
                    }
                }
            } else {
                System.err.println("Cannot type character: " + c);
            }
        }
        Thread.sleep(1500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(500);
    }
}
