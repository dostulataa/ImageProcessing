package pixelfilters;

import java.awt.image.BufferedImage;

import filters.AbstractFilter;

public abstract class PixelFilter extends AbstractFilter {

	/**
	 * Wendet einen Filter auf ein Bild an, wenn angegeben mit Maske
	 * 
	 * @param img
	 *            Das zu filternde Bild, optional als Zweites die Maske angeben
	 * @return Das gefilterte Bild
	 */
	@Override
	public BufferedImage process(BufferedImage... img) {
		// TODO
		int[] imgPixels = img[0].getRGB(0, 0, img[0].getWidth(),
				img[0].getHeight(), null, 0, img[0].getWidth());
		int[] maskPixels, outputPixels;

		// Filter fuer jeden Pixel anwenden
		if (img.length >= 2) {
			maskPixels = getRGB(img[1]);
			outputPixels = imgPixels.clone();
			for (int i = 0; i < imgPixels.length; i++) {
				// Mit Maske
				if (alpha(maskPixels[i]) <= 0) {
					// Schwarz/Weiß
					outputPixels[i] = calculate(imgPixels[i]);
				} else {
					// Graustufen
					float alpha = 1 - alpha(maskPixels[i]);
					int _new = calculate(imgPixels[i]);
					int _old = outputPixels[i];
					int r, g, b;
					r = (int) (getR(_old) + (getR(_new) - getR(_old)) * alpha);
					g = (int) (getG(_old) + (getG(_new) - getG(_old)) * alpha);
					b = (int) (getB(_old) + (getB(_new) - getB(_old)) * alpha);
					outputPixels[i] = rgbPixel(r, g, b);
				}
			}
		} else {
			outputPixels = new int[imgPixels.length];
			for (int i = 0; i < imgPixels.length; i++) {
				// Ohne Maske
				outputPixels[i] = calculate(imgPixels[i]);
			}
		}

		BufferedImage result = new BufferedImage(img[0].getWidth(),
				img[0].getHeight(), BufferedImage.TYPE_INT_RGB);
		result.setRGB(0, 0, result.getWidth(), result.getHeight(),
				outputPixels, 0, result.getWidth());
		return result;
	}

	/**
	 * Wendet einen Filter auf einen Pixel an
	 * 
	 * @param pixel
	 *            Die Farbinformationen des Pixels
	 * @return Der veraenderte Pixel
	 */
	protected int calculate(int pixel) {
		return pixel;
	}

}
