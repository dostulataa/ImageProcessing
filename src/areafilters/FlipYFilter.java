package areafilters;

public class FlipYFilter extends AreaFilter {

	@Override
	protected int calculate(int[] pixels, int[] maskPixels, int index,
			int width, int height) {
		return pixels[(height-(index/width)-1)*width+(index%width)];
	}
	
}
