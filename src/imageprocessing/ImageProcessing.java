package imageprocessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import exceptions.FilterNotAvailableException;
import filters.AbstractFilter;
import filters.Filter;
import filters.FilterPresets;

public class ImageProcessing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int index = 0;
		boolean test = false;
		String inputFile, outputFile, maskFile = "", filterName = "";
		BufferedImage img = null, mask = null;

		// Filter initialisieren
		FilterPresets.init();

		// Programmparameter auswerten
		try {
			if (args[index].equals("test")) {
				test = true;
				index++;
			} else {
				filterName = args[index++];
			}
			inputFile = args[index++];
			if (args[index].equals("-m")) {
				maskFile = args[++index];
				index++;
			}
			outputFile = args[index];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Es fehlen Programmparameter!");
			return;
		}
		
		// Bild laden
		try {
			img = ImageIO.read(new File(inputFile));
		} catch (IOException e) {
			System.err.println("Fehler beim Laden der Datei " + inputFile);
			return;
		}
		
		// Maske laden
		if(!maskFile.isEmpty()){
			try {
				mask = ImageIO.read(new File(maskFile));
			} catch (IOException e) {
				System.err.println("Fehler beim Laden der Datei " + inputFile);
				return;
			}
		}
			
		if (test) {
			// Alle Filter testen
			// TODO
		} else {
			// Einen Filter anwenden
			Filter filter;
			try {
				filter = FilterPresets.getFilter(filterName);
			} catch (FilterNotAvailableException e) {
				System.err.println(e.getMessage());
				return;
			}
			
			// Filter anwenden
			BufferedImage result;
			long time = System.nanoTime();
			if(mask != null){
				result = filter.process(img, mask);
			}else{
				result = filter.process(img);
			}
			System.out.println("Bild verarbeitet in "+(System.nanoTime()-time)/1000000+"ms");
			
			// Ergebnis speichern
			try {
				ImageIO.write(result, "bmp", new File(outputFile));
			} catch (IOException e) {
				System.err.println("Fehler beim Speichern der Datei " + outputFile);
			}

			
		}
	}

}
