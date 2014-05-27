package filters;

import java.awt.image.BufferedImage;

public abstract class AbstractFilter implements Filter {

	@Override
	public abstract BufferedImage process(BufferedImage... img);
	
	protected int getR(int pixel){
		return (pixel >> 16) & 0xFF;
	}
	
	protected  int getG(int pixel){
		return (pixel >> 8) & 0xFF;
	}
	
	protected  int getB(int pixel){
		return pixel & 0xFF;
	}
	
	protected  int[] getRGB(BufferedImage img){
		return img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
	}
	
	protected  int rgbPixel(int r, int g, int b) {
		return (0xFF << 24) | (r << 16) | (g << 8) | (b);
	}
	
	protected float alpha(int mask){
		return (((mask & 0xFF)
				+ ((mask >> 8) & 0xFF) + ((mask >> 16) & 0xFF)) / 3f) / 255f;
	}

}
