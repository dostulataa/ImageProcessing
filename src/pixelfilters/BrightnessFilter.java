package pixelfilters;

public class BrightnessFilter extends PixelFilter {

	private int brightness = 0;
	
	public BrightnessFilter(int brightness){
		this.brightness = brightness;
	}
	
	@Override
	protected int calculate(int pixel) {
		int r = Math.max(Math.min(getR(pixel) + brightness,255),0);
		int g = Math.max(Math.min(getG(pixel) + brightness,255),0);
		int b = Math.max(Math.min(getB(pixel) + brightness,255),0);
		return rgbPixel(r, g, b);
	}
}
