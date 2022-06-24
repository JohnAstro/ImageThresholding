/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thresholding22;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Jonathan Gonzalez
 */
public class Threshold {
    
    // Given the image data and the percentile, get the threshold image
    public byte[][] thresholdImage(byte[][] byteData, int percentile) {
        int h = byteData.length;
        int w = byteData[0].length;
        int imageSize = h * w;
        
        // get histogram to get threshold with given percentile
        int[] histogram = getHistogram(byteData);
        int threshold = getThreshold(imageSize, histogram, percentile);
        
        byte[][] outByteData = new byte[h][w];
        int countBinarization = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if ((byteData[i][j] & 0xFF) >= threshold)
                    outByteData[i][j] = (byte) 255;
                else {
                    outByteData[i][j] = (byte) 0;
                    countBinarization++;
                }
            }
        }
        
        System.out.println("Percentile: " + percentile + "\t Threshold: " + threshold);
        return outByteData;
    }
    
    public byte[][] globalThresholdImage(byte[][] byteData, int threshold) {
        int h = byteData.length;
        int w = byteData[0].length;
        
        threshold = getGlobalThreshold(byteData, threshold);
        System.out.println("Last Threshold: " + threshold);
        
        // get global thresholded image
        byte [][] outByteData = new byte[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if ((byteData[i][j] & 0xFF) >= threshold)
                    outByteData[i][j] = (byte) 255;
                else
                    outByteData[i][j] = (byte) 0;
            }
        }
        
        return outByteData;
    }
    
    // Calculates the global threshold given a predefined threshold
    private int getGlobalThreshold(byte[][] byteData, int threshold) {
        int h = byteData.length;
        int w = byteData[0].length;
        
        // group 1 and 2  and thier means
        List<Byte> G1 = new ArrayList<>();
        List<Byte> G2 = new ArrayList<>();
        float meanG1;
        float meanG2;
        
        // intialize predefined threshold
        int T0 = threshold;
        
        // repeat until threshold is smaller than the predefined threshold
        do {
            System.out.println("Starting Threshold: " + T0 + "\t Threshold: " + threshold);
            
            // produce two groups of pixels given threshold
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if ((byteData[i][j] & 0xFF) >= threshold)
                        G1.add(byteData[i][j]);
                    else
                        G2.add(byteData[i][j]);
                }
            }
            
            // get averages of each group and compute new threshold
            meanG1 = getImageAverage(G1);
            meanG2 = getImageAverage(G2);
            threshold = (int) Math.round((meanG1 + meanG2) / 2);
            G1.clear();
            G2.clear();
        } while(threshold > T0);
        
        return threshold;
    }
    
    // calculates the mean value of the whole image
    private float getImageAverage(List<Byte> inArray) {
        float average = 0.0f;
        int size = inArray.size();
        
        for (int i = 0; i < size; i++) {
                
            average += (inArray.get(i) & 0xFF);
        }

        average = average/size;
        return average;
    }
    
    // gets the threshold given histogram, percentile, and image size
    private int getThreshold(int imageSize, int[] histogram, int percentile) {
        
        // initialize threshold
        int threshold = 0;
        
        // initialize first element of the cumulative histogram
        int[] HC = new int [256];
        HC[0] = histogram[0];
        
        // get the cumulative histogram
        for (int i = 1; i < histogram.length; i++) {
            HC[i] = HC[i-1] + histogram[i];
        }
        
        // the first and the following percentage in the HC
        // uses floor to get the number closest to the percentile
        double first = ((double) HC[0]/imageSize) * 100;
        double second = ((double) HC[1]/imageSize) * 100;
        
        // initialize the first minimum difference to the percentile
        double minDif = Math.abs(first - percentile);
        
        // Iterate through the HC and get the percentage values for comparison
        for (int i = 1; i < histogram.length; i++) {
            first = (((double) HC[i-1]/imageSize) * 100);
            second = (((double) HC[i]/imageSize) * 100);
            
            // compare |first - percentile| < |second - percentile|
            if (Math.abs(first - percentile) < Math.abs(second - percentile)) {
                // If the new difference is less than the previous minDif
                // instantiate it and the threshold. Else, break the loop
                if (Math.abs(first - percentile) <= minDif) {
                    threshold = i-1;
                    minDif = Math.abs(first - percentile);
                }
                else
                    break;
            }
            else {     
                // If the new difference is less than the previous minDif
                // instantiate it and the threshold. Else, break the loop
                if (Math.abs(second - percentile) <= minDif) {
                    threshold = i;
                    minDif = Math.abs(second - percentile);
                }
                else
                    break;
            }
        }
        return threshold;
    }
    
    // creates histogram from gray image
    private int[] getHistogram(byte[][] inArray) {
        int h = inArray.length;
        int w = inArray[0].length;
        int[] histogram = new int[256];
        
        for (int i = 0; i < h; i++){
            for (int j = 0; j < w; j++) {
                histogram[(inArray[i][j] & 0xFF)]++;
            }
        }
        
        return histogram;
    }
}
