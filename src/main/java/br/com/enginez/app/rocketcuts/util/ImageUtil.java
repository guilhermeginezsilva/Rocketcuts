package br.com.enginez.app.rocketcuts.util;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import br.com.enginez.app.rocketcuts.RocketcutsApplication;
import org.apache.commons.io.IOUtils;

public class ImageUtil {

	public static Image createImage(String path) {
        String result = "";

        ClassLoader classLoader = RocketcutsApplication.class.getClassLoader();
        Image image = null;
        try {
            image = ImageIO.read(classLoader.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (new ImageIcon(image)).getImage();
	}

}
