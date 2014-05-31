package filters;

import java.awt.image.BufferedImage;

public abstract class AbstractFilter implements Filter {
	
	protected int current, total;

	@Override
	public abstract BufferedImage process(BufferedImage... img);
	
	@Override
	public int getProgress(){
		return total>0?current*100/total:0;
	}
	
	/**
	 * Gibt den Rotanteil eines Pixels wieder
	 * @param pixel Der Pixel
	 * @return 0-255
	 */
	public static int getR(int pixel){
		return (pixel >> 16) & 0xFF;
	}
	
	/**
	 * Gibt den Gruenanteil eines Pixels wieder
	 * @param pixel Der Pixel
	 * @return 0-255
	 */
	public static  int getG(int pixel){
		return (pixel >> 8) & 0xFF;
	}
	
	/**
	 * Gibt den Blauanteil eines Pixels wieder
	 * @param pixel Der Pixel
	 * @return 0-255
	 */
	public static  int getB(int pixel){
		return pixel & 0xFF;
	}
	
	/**
	 * Wandelt ein Bild in einen eindimensionales int[] um
	 * @param img Das Bild
	 * @return Ein int[] mit img.width * img.height Elementen
	 */
	public static  int[] getRGB(BufferedImage img){
		return img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
	}
	
	/**
	 * Berechnet den int Wert fuer eine RGB Farbe
	 * @param r Der Rotanteil
	 * @param g Der Gruenanteil
	 * @param b Der Blauanteil
	 * @return Der int Wert
	 */
	public static  int rgbPixel(int r, int g, int b) {
		return (0xFF << 24) | (r << 16) | (g << 8) | (b);
	}
	
	/**
	 * Berechnet einen Alphawert fuer eine Maske
	 * @param mask Die Maske als int[] (per BufferedImage.getRGB())
	 * @return Ein Alphawert zwischen 1(bei weisser maske) und 0 (bei schwarzer maske)
	 */
	public static float getAlpha(int mask){
		return getBrightness(mask) / 255f;
	}
	
	/**
	 * Gibt den Helligkeitswert eines Pixels zurueck
	 * @param pixel Der Pixel (in RGB oder ARGB Darstellung)
	 * @return Helligkeits- oder Grauwert des Pixels
	 */
	public static int getBrightness(int pixel){
		return (getR(pixel)+getG(pixel)+getB(pixel))/3;
	}
	
	/**
	 * Berechnet den Index eines Pixels
	 * @param x Die X Koorinate
	 * @param y Die Y Kooridnate des Pixels
	 * @param width Die Breite des Bildes
	 * @return
	 */
	public static int XYtoI(int x, int y, int width){
		return width*y + x;
	}
	
	/**
	 * Berechnet die X Koordinate eines Pixels
	 * @param i Der Index des Pixels
	 * @param width Die Breite des Bildes
	 * @return Die X Koordinate, beginnend mit 0
	 */
	public static int ItoX(int i, int width){
		return i % width;
	}
	
	/**
	 * Berechnet die Y Koordinate eines Pixels
	 * @param i Der Index des Pixels
	 * @param width Die Breite des Bildes
	 * @return Die Y Koordinate, beginnend mit 0
	 */
	protected int ItoY(int i, int width){
		return i / width;
	}
	
	/**
	 * Ueberblendet zwei Pixel ueber eine Maske
	 * @param pixelsOld Unteres Bilder
	 * @param pixelNew Daruebergelegtes Bild
	 * @param maskPixels Die Maske, die die Transparenz des oberen Bildes steuert
	 * @param i Der index des Pixels
	 * @return Pixel, eine Kombination der eingangspixel
	 */
	public static int blend(int[] pixelsOld, int pixelNew, int[] maskPixels, int i){
		float alpha = getAlpha(maskPixels[i]);
		int _new = pixelNew;
		int _old = pixelsOld[i];
		int r, g, b;
		r = (int) (getR(_old) + (getR(_new) - getR(_old)) * alpha);
		g = (int) (getG(_old) + (getG(_new) - getG(_old)) * alpha);
		b = (int) (getB(_old) + (getB(_new) - getB(_old)) * alpha);
		return rgbPixel(r, g, b);
	}

}
