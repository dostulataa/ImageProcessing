package pixelfilters;

/**
 * 
 * Ein Filter, der das Bild in Grautoene umrechnet
 * @author Lukas Richter, Benedikt Ringlein
 *
 */
public class MonochromeFilter extends PixelFilter {

	@Override
	protected int calculate(int pixel) {
		int grey = getBrightness(pixel);
		return rgbPixel(grey, grey, grey);
	}
}
