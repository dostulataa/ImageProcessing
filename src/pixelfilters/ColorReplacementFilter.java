package pixelfilters;

public class ColorReplacementFilter extends PixelFilter {
	
	private int replace, replacement;
	
	public ColorReplacementFilter(int replace, int replacement){
		this.replace = replace;
		this.replacement = replacement;
	}
	
	public ColorReplacementFilter(int replace){
		this(replace, rgbPixel((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
	}
	
	@Override
	protected int calculate(int pixel) {
		if(pixel == replace){
			return replacement;
		}else{
			return pixel;
		}
	}

}
