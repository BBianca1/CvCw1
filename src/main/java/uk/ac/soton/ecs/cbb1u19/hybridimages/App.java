package uk.ac.soton.ecs.cbb1u19.hybridimages;

import java.io.File;
import java.io.IOException;

import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;

/**
 * OpenIMAJ Hello world!
 *
 */
public class App {
//	static float[][] kernel = {{0, 0, 0, 0, 0},{0, -1, -2, -1, 0},{0, 0, 0, 0, 0},{0, 1, 2, 1, 0},{0, 0, 0, 0, 0}};
//	static float[][] kernel = {{0, -1, -2, -1, 0},{0, 0, 0, 0, 0},{0, 1, 2, 1, 0}};
//	static float[][] kernel = {{-1, -2, -1},{0, 0, 0},{1, 2, 1}};
    
	public static void main( String[] args ) throws IOException {
    	
    	
    	MBFImage highImage = ImageUtilities.readMBF(new File("data/cats.PNG"));
    	MBFImage lowImage = ImageUtilities.readMBF(new File("data/og.png"));
    	
//    	MyConvolution myConvo = new MyConvolution(kernel);
    	
    	DisplayUtilities.display(highImage);
    	DisplayUtilities.display(lowImage);
//    	myConvo.processImage(highImage);
    	
    	DisplayUtilities.displayName(MyHybridImages.makeHybrid(lowImage,8,highImage,8),"Hybrid image");
    }
}
