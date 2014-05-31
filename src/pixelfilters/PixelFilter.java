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
		total = imgPixels.length;
			current = 0;
		if (img.length >= 2) {
			// Mit Maske
			maskPixels = getRGB(img[1]);
			outputPixels = imgPixels.clone();
			for (int i = 0; i < imgPixels.length; i++) {
				// Weiße Bereche der Maske komplett ignorieren
				if(getAlpha(maskPixels[i])>0){
					// Neuen Pixel berechnen
					outputPixels[i] = calculate(imgPixels[i]);
					// Maske anwenden (Transparenz des Filters wird durch Helligkeit geregelt)
					outputPixels[i] = blend(imgPixels, outputPixels[i], maskPixels, i);
				}
				current ++;
			}
		} else {
			outputPixels = new int[imgPixels.length];
			for (int i = 0; i < imgPixels.length; i++) {
				// Ohne Maske
				outputPixels[i] = calculate(imgPixels[i]);
				current ++;
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
