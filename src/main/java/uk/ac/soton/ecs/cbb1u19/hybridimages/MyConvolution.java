package uk.ac.soton.ecs.cbb1u19.hybridimages;
import org.openimaj.image.FImage;
import org.openimaj.image.processor.SinglebandImageProcessor;

public class MyConvolution implements SinglebandImageProcessor<Float, FImage> {
	private float[][] kernel;
	FImage convolved;
	
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
		
		convolved = image.clone();
		convolved.fill(0); // makes image black
		
		for(int j = 0; j<imageWidth; j++) {
			for(int i = 0; i<imageHeight; i++) {
				float sum = 0;
				for(int n = 0; n < tcol; n++) {
					for(int m = 0; m < trow; m++) {
						int c = (int) (j+n-tc);
						int r = (int) (i+m-tr);
						if(c < 0) {
							c = imageWidth + c;
						}
						else if(c >= imageWidth){
							c = c - imageWidth;
						}
						if(r < 0) {
							r = imageHeight + r;
						}
						else if(r >= imageHeight){
							r = r - imageHeight;
						}
						sum = sum + image.getPixel(c, r)*kernel[trow-m-1][tcol-n-1];
					}
					convolved.pixels[i][j]=sum;
				}
			}
		}
		
		image.internalAssign(convolved);
	}
}
