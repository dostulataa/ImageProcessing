package areafilters;

public class SandFilter extends AreaFilter {

	private int diffusion = 0;
	
	public SandFilter(int diffusion){
		this.diffusion = diffusion;
	}
	
	@Override
	protected int calculate(int[] pixels, int[] maskPixels, int index,
			int width, int height) {
		int x = (ItoX(index, width) - diffusion + (int) (Math.random() * 2 * diffusion) + width) % width;
		int y = (ItoY(index, width) - diffusion + (int) (Math.random() * 2 * diffusion) + height) % height;
		return pixels[XYtoI(x, y, width)];
	}
}
