package areafilters;

public class PixelGraphicFilter extends AreaFilter {
	
	private int size = 0;
	
	public PixelGraphicFilter(int size){
		this.size = size;
	}

	@Override
	protected int calculate(int[] pixels, int[] maskPixels, int index,
			int width, int height) {
		return pixels[XYtoI(ItoX(index, width)/size*size, ItoY(index, width)/size*size, width)];
	}
	
}
