package areafilters;

/**
 * 
 * Ein Filter, der Pixel zufaellig bewegt
 * @author Lukas Richter, Benedikt Ringlein
 *
 */
public class SandFilter extends AreaFilter {

	private int diffusion = 0;
	
	public SandFilter(int diffusion){
		this.diffusion = diffusion;
	}
	
	@Override
	protected int calculate(int[] pixels, int[] maskPixels, int index,
			int width, int height) {
		int x = Math.max(Math.min((ItoX(index, width) - diffusion + (int) (Math.random() * 2 * diffusion)),width-1),0);
		int y = Math.max(Math.min((ItoY(index, width) - diffusion + (int) (Math.random() * 2 * diffusion)),height-1),0);
		return pixels[XYtoI(x, y, width)];
	}
}
