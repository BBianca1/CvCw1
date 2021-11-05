package uk.ac.soton.ecs.cbb1u19.hybridimages;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.FImage;
import org.openimaj.image.processor.SinglebandImageProcessor;

public class MyConvolution implements SinglebandImageProcessor<Float, FImage> {
	private float[][] kernel;

	public MyConvolution(float[][] kernel) {
		//note that like the image pixel s kernel is indexed by [row][column]
		this.kernel = kernel;
	}

	@Override
	public void processImage(FImage image) {
		
		int imageHeight = image.getHeight(); // no of cols
		int imageWidth = image.getWidth(); // no of raws
		
		int trow = kernel.length;
		int tcol = kernel[0].length;
		
		double tr = Math.floor(trow/2); //half of template rows
		double tc = Math.floor(tcol/2); //half of template cols
		
		FImage convolved = new FImage(imageWidth, imageHeight);
		convolved.fill(0); // makes image black
		System.out.println(tc);
		System.out.println(tcol);
		System.out.println(tr);
		System.out.println(trow);
		
		System.out.println(imageHeight + " " + imageWidth );
		
		for(int j = (int) (tc + 1); j < imageWidth - tc; j++) 
			for(int i = (int) (tr + 1); i < imageHeight - tr; i++) {
				float sum = 0;
				for(int n = 0; n < tcol; n++)
					for(int m = 0; m < trow; m++) {
						sum = sum + image.pixels[(int) (i + m - tr)][(int) (j + n - tc)] * kernel[m][n];
					}
				convolved.pixels[i][j] = sum;
			}
		DisplayUtilities.displayName(convolved, "Convolution");
	}
}
