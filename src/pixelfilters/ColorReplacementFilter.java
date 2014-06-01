package pixelfilters;

import java.awt.Color;

public class ColorReplacementFilter extends PixelFilter {
	
	private int replace, replacement;
	
	public ColorReplacementFilter(int replace, int replacement){
		this.replace = replace;
		this.replacement = replacement;
	}
	
	public ColorReplacementFilter(Color replace, Color replacement){
		this(replace.getRGB(), replacement.getRGB());
	}
	
	public ColorReplacementFilter(int replace){
		this(replace, rgbPixel((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
	}
	
	public ColorReplacementFilter(Color replace){
		this(replace.getRGB());
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
