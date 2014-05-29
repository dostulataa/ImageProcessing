package pixelfilters;

public class MultiThresholdFilter extends PixelFilter {
	
	private int[] threshold;
	
	public MultiThresholdFilter(int... threshold){
		this.threshold = threshold;
	}

	@Override
	protected int calculate(int pixel) {
		if(getBrightness(pixel)>threshold[threshold.length-1]){
			return rgbPixel(255, 255, 255);
		}
		for (int i = threshold.length - 2; i >= 0; i--) {
			if(getBrightness(pixel)>=threshold[i]){
				return rgbPixel(threshold[i+1], threshold[i+1], threshold[i+1]);
			}
		}
		return rgbPixel(threshold[0], threshold[0], threshold[0]);
	}
}
