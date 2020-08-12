import com.screenshooter.shooter.ScreenShooter;

import java.awt.*;

public class Main {

    public static void main(String[] args) throws AWTException {
        Thread myThread = new Thread(new ScreenShooter(5000));
        myThread.start();
    }
}