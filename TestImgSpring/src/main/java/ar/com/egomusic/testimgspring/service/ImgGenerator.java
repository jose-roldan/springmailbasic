/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.egomusic.testimgspring.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Component;

/**
 *
 * @author jose.roldan
 */
@Component
public class ImgGenerator {

    public BufferedImage getJpgImage(String numberText) throws URISyntaxException, IOException {

        URL resource = null;
        if ((Integer.parseInt(numberText) % 2) > 0) {
            resource = getClass().getClassLoader().getResource("static/testejpg01.jpg");
        } else {
            resource = getClass().getClassLoader().getResource("static/testejpg00.jpg");
        }

        File img = new File(resource.toURI());
        BufferedImage imgBuf = ImageIO.read(img);

        return imgBuf;

    }

    public BufferedImage getPngImage(String numberText) throws URISyntaxException, IOException {

        URL resource = null;
        if ((Integer.parseInt(numberText) % 2) > 0) {
            resource = getClass().getClassLoader().getResource("static/testepng01.png");
        } else {
            resource = getClass().getClassLoader().getResource("static/testepng00.png");
        }

        File img = new File(resource.toURI());
        BufferedImage imgBuf = ImageIO.read(img);

        return imgBuf;
    }

}
