package gui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class ImageLoader {
    private static ImageLoader imageLoader;
    private final HashMap<String,Image> imageStorage = new HashMap<>();


    public ImageLoader(){

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
                System.out.println("Can´t read image: " + imageName);
                return null;
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
                System.out.println("Can´t read image: "+ imageName);
                return null;
            }
        }
    }
}
