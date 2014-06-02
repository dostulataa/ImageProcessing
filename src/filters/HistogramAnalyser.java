package filters;

import java.awt.image.BufferedImage;

public class HistogramAnalyser extends AbstractFilter {

	public BufferedImage process(BufferedImage... img) {
		
		int[] imgPixels = getRGB(img[0]);
		int[] maskPixels = img.length>=2?getRGB(img[1]):null;
		int numberPixels = 0;
		double[] count = new double[256];
		double normalFactor;

		// Anzahl der Grauwerte berechnen und in count speichern
		numberPixels = getHistogramm(imgPixels, maskPixels, count);
		
		// Normalisierungsfaktor bestimmen
		normalFactor = getNormalFactor(imgPixels.length, count);
		
		// Histogramm ausgeben
		printHistogramm(imgPixels.length, count , normalFactor);
		
		// Eingabebild wieder zurueckgeben
		return img[0];
	}
	
	/**
	 * Ermittelt das Histogramm eines Pixel Arrays und speichert es 
	 * in ein double Array
	 * @param imgPixels Der Pixel Array
	 * @param count Der Array zum speichern des Histogrammes
	 */
	private int getHistogramm(int[] imgPixels, int[] maskPixels, double[] count){

		int numberPixels = 0;
		
		// Helligkeit bestimmen und zaehlen
		for (int i = 0; i < imgPixels.length; i++) {
			if(maskPixels == null || getAlpha(maskPixels[i]) > 0){
				int grey = getBrightness(imgPixels[i]);
				count[grey]++;
				numberPixels++;
			}
		}
		return numberPixels;
	}
	
	/**
	 * Berechnet den Normalisierungsfaktor fuer das Histogramm
	 * Die Laenge aller Balken wird damit multipliziert
	 * @param numberPixels Die anzahl an Pixeln
	 * @param Der Array mit der totalen Anzahl an Pixeln pro Helligkeitswert
	 * @return Faktorzum normalisieren des Graphen
	 */
	private double getNormalFactor(int numberPixels, double[] count){
		int biggest = 0;
		
		// Groesste Anzahl suchen (lineare Suche)
		for (int i = 0; i < count.length; i++) {
			if(count[i] > count[biggest]) {
				biggest = i;
			}
		}
		
		// Normalisierungsfaktor berechnen
		return 1 / (count[biggest] / numberPixels);
	}
	
	/**
	 * Gibt ein Histogramm auf der Kosole aus
	 * @param numberPixels Die Anzahl an Pixeln im Bild
	 * @param count Der Array mit den Pixelanzahlen pro Helligkeitsstufe
	 * @param normalFactor Der Faktor zum Normalisieren (oder 1 fuer nicht-normalisierte Ansicht)
	 */
	private void printHistogramm(int numberPixels, double[] count, double normalFactor){
		
		for (int i = 0; i < count.length; i++) {
			StringBuilder histogramm;
			
			// Wert, Anzahl zu String hinzufügen
			histogramm = new StringBuilder(i + ": " + (int) count[i] + " Pixel (");
			
			// Umrechnen in Prozent
			count[i] = count[i] / numberPixels * 100;
			count[i] = Math.round(count[i] * 1000) / 1000.0;
			
			// Prozent zu String hinzufügen
			histogramm.append(count[i] + "%) ");
			
			// Sternchen nach Prozent werden hinzugefügt
			for (int j = 1; j < count[i]*normalFactor; j++) {
				histogramm.append('*');
			}
			
			// Die Ausgabe
			System.out.println(histogramm);
		}
		
	}
}