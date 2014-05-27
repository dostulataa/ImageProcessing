package areafilters;

public class FlipXFilter extends AreaFilter {

	@Override
	protected int calculate(int[] pixels, int[] maskPixels, int index,
			int width, int height) {
		return pixels[index-2*(index%width)+width-1];
	}
}
