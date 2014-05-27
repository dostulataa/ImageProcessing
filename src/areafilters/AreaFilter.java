package areafilters;

import java.awt.image.BufferedImage;

import filters.AbstractFilter;

public abstract class AreaFilter extends AbstractFilter {

	@Override
	public BufferedImage process(BufferedImage... img) {
		// TODO
		int[] imgPixels = img[0].getRGB(0, 0, img[0].getWidth(),
				img[0].getHeight(), null, 0, img[0].getWidth());
		int[] maskPixels = null;
		int[] outputPixels = null;
		
		if(img.length >= 2){
			maskPixels = img[0].getRGB(0, 0, img[0].getWidth(),
					img[0].getHeight(), null, 0, img[0].getWidth());
		}

		// Filter fuer jeden Pixel anwenden
		outputPixels = new int[imgPixels.length];
		for (int i = 0; i < imgPixels.length; i++) {
			outputPixels[i] = calculate(imgPixels, maskPixels, i, img[0].getWidth(), img[0].getHeight());
		}

		BufferedImage result = new BufferedImage(img[0].getWidth(),
				img[0].getHeight(), BufferedImage.TYPE_INT_RGB);
		result.setRGB(0, 0, result.getWidth(), result.getHeight(),
				outputPixels, 0, result.getWidth());
		return result;
	}
	
	protected int calculate(int[] pixels, int[] maskPixels, int index, int width, int height){
		return pixels[index];
	}

}
