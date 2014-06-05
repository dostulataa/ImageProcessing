package areafilters;

/**
 * 
 * Ein Filter, der das Bild mit einem simplen, linearen Verfahren weichzeichnet.
 * @author Lukas Richter, Benedikt Ringlein
 *
 */
public class BlurFilter extends AreaFilter {

	private int radius;

	public BlurFilter(int radius) {
		this.radius = radius;
	}

	@Override
	protected int calculate(int[] pixels, int[] maskPixels, int index,
			int width, int height) {

		int startX = Math.max(Math.min(ItoX(index, width) - radius, width), 0);
		int startY = Math.max(Math.min(ItoY(index, width) - radius, height), 0);
		int r = 0;
		int g = 0;
		int b = 0;
		int count = 0;
		
		// R, G und B Werte aller Umgebenden Pixel summieren
		for (int x = startX; x < startX + 2 * radius && x < width; x++) {
			x = Math.max(Math.min(x, width), 0);
			for (int y = startY; y < startY + 2 * radius && y < height; y++) {
				y = Math.max(Math.min(y, height), 0);
				r += getR(pixels[XYtoI(x, y, width)]);
				g += getG(pixels[XYtoI(x, y, width)]);
				b += getB(pixels[XYtoI(x, y, width)]);
				count ++;
			}
		}
		
		// Durchschnittswerte berechnen
		r /= count;
		g /= count;
		b /= count;
		
		// Pixel zurueckgeben
		return rgbPixel(r, g, b);
	}

}
