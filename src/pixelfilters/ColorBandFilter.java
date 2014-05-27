package pixelfilters;

import filters.ColorBand;

public class ColorBandFilter extends PixelFilter {
	
	private ColorBand colorBand;
	
	public ColorBandFilter(ColorBand colorBand){
		this.colorBand = colorBand;
	}

	@Override
	protected int calculate(int pixel) {
		switch(colorBand){
		case RED:
			return rgbPixel(getR(pixel), 0, 0);
		case GREEN:
			return rgbPixel(0, getG(pixel), 0);
		case BLUE:
			return rgbPixel(0, 0, getB(pixel));
		default:
			return rgbPixel(0, 0, 0);
		}
	}
}
