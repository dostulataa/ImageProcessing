package pixelfilters;


public class MonochromeFilter extends PixelFilter {

	@Override
	protected int calculate(int pixel) {
		int grey =( getR(pixel)+getG(pixel)+getB(pixel))/3;
		return rgbPixel(grey, grey, grey);
	}
}
