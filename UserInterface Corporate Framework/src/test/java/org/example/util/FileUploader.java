package org.example.util;

import java.awt.*;
import java.awt.event.KeyEvent;

public class FileUploader {
    public static void uploadFile(String filePath) throws AWTException, InterruptedException {
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
        String os = System.getProperty("os.name");
        if(os.toLowerCase().contains("mac")){
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(500);
    }
}
