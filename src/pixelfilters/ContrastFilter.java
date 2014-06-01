package pixelfilters;

public class ContrastFilter extends PixelFilter {

	private float contrast = 0;
	
	public ContrastFilter(float contrast){
		this.contrast = Math.min(Math.max(contrast,-1),1);
	}
	
	@Override
	protected int calculate(int pixel) {
		int r = getR(pixel);
		r = clamp((int) (r + (r - 128) * contrast));
		int g = getG(pixel);
		g = clamp((int) (g + (g - 128) * contrast));
		int b = getB(pixel);
		b = clamp((int) (b + (b - 128) * contrast));
		return rgbPixel(r, g, b);
	}
	
}
