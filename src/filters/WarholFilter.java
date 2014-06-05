package filters;

import pixelfilters.ColorReplacementFilter;
import pixelfilters.MultiThresholdFilter;

/**
 * 
 * Wendet zuerst Multithreshhold Filter an und ersetzt danach zufaellig Farben
 * @author Lukas Richter, Benedikt Ringlein
 *
 */
public class WarholFilter extends ChainFilter {

	public WarholFilter(){
		add(new MultiThresholdFilter(64, 128, 192));
		add(new ColorReplacementFilter(AbstractFilter.rgbPixel(64, 64, 64)));
		add(new ColorReplacementFilter(AbstractFilter.rgbPixel(128, 128, 128)));
		add(new ColorReplacementFilter(AbstractFilter.rgbPixel(192, 192, 192)));
		add(new ColorReplacementFilter(AbstractFilter.rgbPixel(255, 255, 255)));
	}
	
}
