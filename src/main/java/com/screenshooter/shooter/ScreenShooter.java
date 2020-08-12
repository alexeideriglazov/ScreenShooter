package com.screenshooter.shooter;

import com.screenshooter.uploader.Uploader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShooter implements Runnable {
    private final Robot robot;
    private final Rectangle rectangle;
    private final SimpleDateFormat format;
    private final int timeWait;

    public ScreenShooter(int timeWait) throws AWTException {
        robot = new Robot();
        rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        format = new SimpleDateFormat("yyyyMMdd_HHmmss");
        this.timeWait = timeWait;
    }

    public ScreenShooter() throws AWTException {
        robot = new Robot();
        rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        format = new SimpleDateFormat("yyyyMMdd_HHmmss");
        this.timeWait = 10000;
    }

    @Override
    public void run() {
        while (true) {
            try {
                BufferedImage capture = robot.createScreenCapture(rectangle);
                String date = format.format(new Date());
                Thread uploadThread = new Thread(new Uploader(capture, date));
                uploadThread.start();
                Thread.sleep(timeWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}