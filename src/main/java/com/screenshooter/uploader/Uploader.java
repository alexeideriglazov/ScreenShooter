package com.screenshooter.uploader;

import com.dropbox.core.DbxException;
import com.screenshooter.config.Config;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Uploader implements Runnable {
    private final BufferedImage image;
    private final String date;

    public Uploader(BufferedImage image, String date) {
        this.image = image;
        this.date = date;
    }

    @Override
    public void run() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", os);
            byte[] bytes = os.toByteArray();
            InputStream is = new ByteArrayInputStream(bytes);
            Config.getConfig().client().files().uploadBuilder("/" + date + ".png")
                    .uploadAndFinish(is);
        } catch (IOException | DbxException e) {
            e.printStackTrace();
        }
    }
}
