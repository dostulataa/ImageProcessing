package filters;

import java.awt.image.BufferedImage;

public class HistogramAnalyser extends AbstractFilter {

	protected int calculate(int pixel) {
		return pixel;
	}

	public BufferedImage process(BufferedImage... img) {
		int[] imgPixels = getRGB(img[0]);
		String[] verteilung = new String[256];
		double[] anzahl = new double[256];

		// Anzahl der Grauwerte berechnen
		for (int i = 0; i < imgPixels.length; i++) {
			int grey = getBrightness(imgPixels[i]);
			anzahl[grey]++;
		}

		for (int i = 0; i < anzahl.length; i++) {
			// Wert, Anzahl zu String hinzufügen
			verteilung[i] = i + ": " + (int) anzahl[i] + " Pixel (";
			// Umrechnen in Prozent
			anzahl[i] = anzahl[i] / imgPixels.length * 100;
			anzahl[i] = Math.round(anzahl[i] * 1000) / 1000.0;
			// Prozent zu String hinzufügen
			verteilung[i] += anzahl[i] + "%) ";
			// Sternchen nach Prozent werden hinzugefügt
			for (int j = 1; j < anzahl[i]; j++) {
				verteilung[i] += '*';
			}
			// Die Ausgabe
			System.out.println(verteilung[i]);
		}
		return img[0];
	}
}