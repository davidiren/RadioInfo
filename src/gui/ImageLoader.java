package gui;

import org.xml.sax.InputSource;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class ImageLoader {
    private static ImageLoader imageLoader;
    private final HashMap<String,Image> imageStorage = new HashMap<>();


    public ImageLoader(){
        try {
            String imageName = "images/nopic.png";
            Image im = ImageIO.read(
                    ClassLoader.getSystemResourceAsStream(imageName));
            imageStorage.put(imageName, im);
        }catch (IOException e){
            //do nothing
        }
    }

    public static synchronized ImageLoader getImageLoader(){
        if (imageLoader!=null){
            return imageLoader;
        }else {
            return imageLoader = new ImageLoader();
        }
    }

    public synchronized Image getImage(String imageName){
        if(imageStorage.containsKey(imageName)){
            return imageStorage.get(imageName);
        }else{
            try {
                Image im = ImageIO.read(ClassLoader.getSystemResourceAsStream(imageName));
                imageStorage.put(imageName, im);
                return im;

            } catch (IOException e) {
                return imageStorage.get("images/nopic.png").getScaledInstance(
                        50,50, Image.SCALE_SMOOTH);
            }
        }
    }

    public synchronized Image getScaledImage(String imageName, int width, int height){
        if(imageStorage.containsKey(imageName)){
            return imageStorage.get(imageName).getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }else{
            try{
                Image im = ImageIO.read(ClassLoader.getSystemResourceAsStream(imageName));
                imageStorage.put(imageName,im);
                return im.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            } catch (IOException e) {
                return imageStorage.get("images/nopic.png").getScaledInstance(
                        50,50, Image.SCALE_SMOOTH);
            }
        }
    }
    public synchronized Image getScaledImageFromUrl(URL imageName, int width,
                                                    int height){
        try {
            if (imageStorage.containsKey(imageName.toString())){
                return imageStorage.get(imageName.toString()).getScaledInstance(width,
                        height, Image.SCALE_SMOOTH);
            } else{
                Image image;
                BufferedImage buffim = ImageIO.read(imageName);
                ImageIcon imcon = new ImageIcon(buffim);
                image = imcon.getImage();
                imageStorage.put(imageName.toString(), image);
                return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            }
        } catch (IOException | NullPointerException e) {
            return imageStorage.get("images/nopic.png").getScaledInstance(
                    50,50, Image.SCALE_SMOOTH);
        }
    }
}

