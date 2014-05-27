package pixelfilters;

public class ThresholdFilter extends PixelFilter {

	private int threshold;

	public ThresholdFilter(int threshold) {
		this.threshold = threshold;
	}

	@Override
	protected int calculate(int pixel) {
		int grey = (getR(pixel) + getG(pixel) + getB(pixel)) / 3;
		if (grey > threshold) {
			return rgbPixel(255, 255, 255);
		} else {
			return rgbPixel(0, 0, 0);
		}
	}
}
