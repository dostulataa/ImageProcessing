package filters;

import java.util.HashMap;

import pixelfilters.ColorBandFilter;
import pixelfilters.MonochromeFilter;
import pixelfilters.ThresholdFilter;
import areafilters.FlipXFilter;
import areafilters.FlipYFilter;
import exceptions.FilterNotAvailableException;

public class FilterPresets {
	
	private static HashMap<String, Filter> availableFilters = new HashMap<String, Filter>();
	
	public static void init(){
		// TODO Filter initialisieren
		availableFilters.put("monochrom", new MonochromeFilter());
		availableFilters.put("colorband_red", new ColorBandFilter(ColorBand.RED));
		availableFilters.put("colorband_green", new ColorBandFilter(ColorBand.GREEN));
		availableFilters.put("colorband_blue", new ColorBandFilter(ColorBand.BLUE));
		availableFilters.put("threshold_128", new ThresholdFilter(128));
		availableFilters.put("threshold_192", new ThresholdFilter(192));
		availableFilters.put("flip_x", new FlipXFilter());
		availableFilters.put("flip_y", new FlipYFilter());
	}
	
	public static Filter getFilter(String filtername) throws FilterNotAvailableException{
		Filter auswahl = availableFilters.get(filtername);
		if(auswahl != null){
			return auswahl;
		}else{
			throw new FilterNotAvailableException(filtername);
		}
	}
}
