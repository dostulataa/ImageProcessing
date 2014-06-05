package filters;

import java.awt.image.BufferedImage;

/**
 * 
 * Das Filter Interface, das jeder Filter implementiert
 * @author Lukas Richter, Benedikt Ringlein
 *
 */
public interface Filter {
	
	// Verarbeitet das Bild
	BufferedImage process(BufferedImage... img);
	
	//Gibt aktuellen Fortschritt der Berechnung zurueck
	int getProgress();
}
