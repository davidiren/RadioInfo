package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

/**
 * Class: ImageLoader
 *
 * @author - David Irén
 *
 * Used to load in images, also uses a hashmap to store already show images
 * to show again if needed
 */

public class ImageLoader {
    private static ImageLoader imageLoader;
    private final HashMap<String,Image> imageStorage = new HashMap<>();

    /**
     * Constructor
     */
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

    /**
     * returns a ImageLoader class to be used
     * @return - ImageLoader
     */
    public static synchronized ImageLoader getImageLoader(){
        if (imageLoader!=null){
            return imageLoader;
        }else {
            return imageLoader = new ImageLoader();
        }
    }

    /**
     * loads a scaled instance of an image, if no image is found
     * return a "noimage" image with a fixed size
     * @param imageName - String
     * @param width - int with nr of pixels in width
     * @param height - int with nr of pixels in height
     * @return - scaled image
     */
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
                        200,200, Image.SCALE_SMOOTH);
            }
        }
    }

    /**
     * loads a scaled instance of an image from an URL, if no image is found
     * return a "noimage" image with a fixed size
     * @param imageName - String
     * @param width - int with nr of pixels in width
     * @param height - int with nr of pixels in height
     * @return - scaled image
     */
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
                    200,200, Image.SCALE_SMOOTH);
        }
    }
}

