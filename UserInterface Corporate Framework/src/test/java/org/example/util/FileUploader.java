package org.example.util;

import java.awt.*;
import java.awt.event.KeyEvent;

public class FileUploader {
    public static void uploadFile(String filePath) throws AWTException, InterruptedException {
        Robot robot = new Robot();

        // Delay needed for the dialog to appear
        Thread.sleep(1000); // Wait for 1 second

        // Simulate typing the file path
        for (char c : filePath.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (keyCode != KeyEvent.VK_UNDEFINED) {
                robot.keyPress(keyCode);
                robot.keyRelease(keyCode);
            } else {
                // Handle special characters if needed (e.g., using Toolkit.getDefaultToolkit().getSystemClipboard())
                System.err.println("Cannot type character: " + c);
            }
        }

        Thread.sleep(500); // Wait for input

        // Simulate pressing Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Thread.sleep(1000); // Wait for the upload to complete
    }
//    public static void uploadFiles(List filePaths) throws AWTException, InterruptedException {
//        for (String filePath : filePaths) {
//            uploadFile(filePath);
//            Thread.sleep(1000); // Add a delay between file uploads
//        }
//    }
}
