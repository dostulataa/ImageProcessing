package areafilters;

public class FlipXFilter extends AreaFilter {

	@Override
	protected int calculate(int[] pixels, int[] maskPixels, int index,
			int width, int height) {
		return pixels[XYtoI(width-1-ItoX(index, width), ItoY(index, width), width)];
	}
}
