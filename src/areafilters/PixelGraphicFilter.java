package areafilters;

import java.awt.image.BufferedImage;

public class PixelGraphicFilter extends AreaFilter {

	private int size = 0;
	private int[][] calculatedPixels;

	public PixelGraphicFilter(int size) {
		this.size = size;
	}

	@Override
	public BufferedImage process(BufferedImage... img) {
		calculatedPixels = new int[img[0].getWidth()][img[0].getHeight()];
		return super.process(img);
	}

	@Override
	protected int calculate(int[] pixels, int[] maskPixels, int index,
			int width, int height) {

		// Startpunkt finden
		int posX = ItoX(index, width);
		int posY = ItoY(index, width);
		int startX = posX / size * size;
		int startY = posY / size * size;
		int r = 0, g = 0, b = 0;

		// Farbwert zurueckgeben, falls er schon berechnet wurde
		if (posX != startX && posY != startY) {
			//return calculatedPixels[startX / size][startY / size];
		}

		// Summe der umgebenden Farbwerte bilden
		int count = 0;
		for (int x = startX; x < startX + size && x < width; x++) {
			for (int y = startY; y < startY + size && y < height; y++) {
				r += getR(pixels[XYtoI(x, y, width)]);
				g += getG(pixels[XYtoI(x, y, width)]);
				b += getB(pixels[XYtoI(x, y, width)]);
				count++;
			}
		}

		// Durchschnittsfarbe berechnen
		r /= count;
		g /= count;
		b /= count;

		// Pixel zusammensetzen
		int pixel = rgbPixel(r, g, b);

		// Berechnete Farbe speichern (Performance Optimierung)
		calculatedPixels[startX / size][startY / size] = pixel;

		return pixel;
	}

}
