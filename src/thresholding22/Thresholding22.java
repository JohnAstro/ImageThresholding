/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package thresholding22;

import java.awt.image.BufferedImage;

/**
 *
 * @author Jonathan Gonzalez
 */
public class Thresholding22 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
                
        // 1- Read an image:
        BufferedImage keyImage1 = ImageIo.readImage("Key1.jpg");
        ImageIo.getBufferedImageType(keyImage1,"First key: keyImage");
        
        BufferedImage keyImage2 = ImageIo.readImage("Key2.jpg");
        ImageIo.getBufferedImageType(keyImage2,"Second key: KeyImage2");
        
        BufferedImage keyImage3 = ImageIo.readImage("Key3.jpg");
        ImageIo.getBufferedImageType(keyImage3,"Third key: KeyImage3");
        
        // 2- Convert to Gray (3-channels go to one)
        BufferedImage grayKey1 = ImageIo.toGray(keyImage1);
        BufferedImage grayKey2 = ImageIo.toGray(keyImage2);
        BufferedImage grayKey3 = ImageIo.toGray(keyImage3);
        
        //3 Extract 2-d byte arrays
        //Get 1 array
        byte[][] Key1Data = ImageIo.getGrayByteImageArray2DFromBufferedImage(grayKey1);
        byte[][] Key2Data = ImageIo.getGrayByteImageArray2DFromBufferedImage(grayKey2);
        byte[][] Key3Data = ImageIo.getGrayByteImageArray2DFromBufferedImage(grayKey3);
        
        // processing
        Threshold thobject = new Threshold();
        
        // Key1 thresholded
        byte[][] firstKey30 = thobject.thresholdImage(Key1Data, 30);
        byte[][] firstKey50 = thobject.thresholdImage(Key1Data, 50);
        byte[][] firstKey60 = thobject.thresholdImage(Key1Data, 60);
        byte[][] firstKeyGlobal = thobject.globalThresholdImage(Key1Data, 128);
        
        // Key2 thresholded
        byte[][] secondKey30 = thobject.thresholdImage(Key2Data, 30);
        byte[][] secondKey50 = thobject.thresholdImage(Key2Data, 50);
        byte[][] secondKey60 = thobject.thresholdImage(Key2Data, 60);
        byte[][] secondKeyGlobal = thobject.globalThresholdImage(Key2Data, 128);
        
        // Key3 thresholded
        byte[][] thirdKey30 = thobject.thresholdImage(Key3Data, 30);
        byte[][] thirdKey50 = thobject.thresholdImage(Key3Data, 50);
        byte[][] thirdKey60 = thobject.thresholdImage(Key3Data, 60);
        byte[][] thirdKeyGlobal = thobject.globalThresholdImage(Key3Data, 128);
        
        
        //5- Put back in a buffered image
        
        // Key1 images
        BufferedImage firstKey30Image = ImageIo.setGrayByteImageArray2DToBufferedImage(firstKey30);
        BufferedImage firstKey50Image = ImageIo.setGrayByteImageArray2DToBufferedImage(firstKey50);
        BufferedImage firstKey60Image = ImageIo.setGrayByteImageArray2DToBufferedImage(firstKey60);
        BufferedImage firstKeyGlobalImage = ImageIo.setGrayByteImageArray2DToBufferedImage(firstKeyGlobal);
        
        // Key2 images
        BufferedImage secondKey30Image = ImageIo.setGrayByteImageArray2DToBufferedImage(secondKey30);
        BufferedImage secondKey50Image = ImageIo.setGrayByteImageArray2DToBufferedImage(secondKey50);
        BufferedImage secondKey60Image = ImageIo.setGrayByteImageArray2DToBufferedImage(secondKey60);
        BufferedImage secondKeyGlobalImage = ImageIo.setGrayByteImageArray2DToBufferedImage(secondKeyGlobal);
        
        // Key2 images
        BufferedImage thirdKey30Image = ImageIo.setGrayByteImageArray2DToBufferedImage(thirdKey30);
        BufferedImage thirdKey50Image = ImageIo.setGrayByteImageArray2DToBufferedImage(thirdKey50);
        BufferedImage thirdKey60Image = ImageIo.setGrayByteImageArray2DToBufferedImage(thirdKey60);
        BufferedImage thirdKeyGlobalImage = ImageIo.setGrayByteImageArray2DToBufferedImage(thirdKeyGlobal);
        
        //6- Write out as a file
        
        // write Key1
        ImageIo.writeImage(firstKey30Image, "jpg", "Thresholded30-Key1.jpg");
        ImageIo.writeImage(firstKey50Image, "jpg", "Thresholded50-Key1.jpg");
        ImageIo.writeImage(firstKey60Image, "jpg", "Thresholded60-Key1.jpg");
        ImageIo.writeImage(firstKeyGlobalImage, "jpg", "GlobalThreshold-Key1.jpg");
        
        // Write Key2
        ImageIo.writeImage(secondKey30Image, "jpg", "Thresholded30-Key2.jpg");
        ImageIo.writeImage(secondKey50Image, "jpg", "Thresholded50-Key2.jpg");
        ImageIo.writeImage(secondKey60Image, "jpg", "Thresholded60-Key2.jpg");
        ImageIo.writeImage(secondKeyGlobalImage, "jpg", "GlobalThreshold-Key2.jpg");
        
        // Write Key2
        ImageIo.writeImage(thirdKey30Image, "jpg", "Thresholded30-Key3.jpg");
        ImageIo.writeImage(thirdKey50Image, "jpg", "Thresholded50-Key3.jpg");
        ImageIo.writeImage(thirdKey60Image, "jpg", "Thresholded60-Key3.jpg");
        ImageIo.writeImage(thirdKeyGlobalImage, "jpg", "GlobalThreshold-Key3.jpg");
    }
    
}
