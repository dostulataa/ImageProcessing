package pixelfilters;


public class MonochromeFilter extends PixelFilter {

	@Override
	protected int calculate(int pixel) {
		int grey = getBrightness(pixel);
		return rgbPixel(grey, grey, grey);
	}
}
