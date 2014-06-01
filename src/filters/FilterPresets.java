package filters;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import pixelfilters.BrightnessFilter;
import pixelfilters.ColorBandFilter;
import pixelfilters.ColorReplacementFilter;
import pixelfilters.ContrastFilter;
import pixelfilters.InvertFilter;
import pixelfilters.MonochromeFilter;
import pixelfilters.MultiThresholdFilter;
import pixelfilters.ThresholdFilter;
import areafilters.FlipXFilter;
import areafilters.FlipYFilter;
import areafilters.PixelGraphicFilter;
import areafilters.SandFilter;
import exceptions.FilterNotAvailableException;

public class FilterPresets {
	
	private static HashMap<String, Filter> availableFilters = new HashMap<String, Filter>();
	
	public static void init(){
		availableFilters.put("monochrom", new MonochromeFilter());
		availableFilters.put("colorband_red", new ColorBandFilter(ColorBand.RED));
		availableFilters.put("colorband_green", new ColorBandFilter(ColorBand.GREEN));
		availableFilters.put("colorband_blue", new ColorBandFilter(ColorBand.BLUE));
		availableFilters.put("threshold_128", new ThresholdFilter(128));
		availableFilters.put("threshold_192", new ThresholdFilter(192));
		availableFilters.put("multithreshold", new MultiThresholdFilter(64, 128, 192));
		availableFilters.put("colorreplacement_64", new ColorReplacementFilter(AbstractFilter.rgbPixel(64, 64, 64)));
		availableFilters.put("colorreplacement_128", new ColorReplacementFilter(AbstractFilter.rgbPixel(128, 128, 128)));
		availableFilters.put("colorreplacement_192", new ColorReplacementFilter(AbstractFilter.rgbPixel(192, 192, 192)));
		availableFilters.put("pixel_20", new PixelGraphicFilter(20));
		availableFilters.put("pixel_40", new PixelGraphicFilter(40));
		availableFilters.put("pixel_60", new PixelGraphicFilter(60));
		availableFilters.put("flip_x", new FlipXFilter());
		availableFilters.put("flip_y", new FlipYFilter());
		availableFilters.put("warhol", new WarholFilter());
		availableFilters.put("invert", new InvertFilter());
		availableFilters.put("brightness_plus10", new BrightnessFilter(10));
		availableFilters.put("brightness_minus10", new BrightnessFilter(-10));
		availableFilters.put("brightness_plus50", new BrightnessFilter(50));
		availableFilters.put("brightness_minus50", new BrightnessFilter(-50));
		availableFilters.put("contrast_plus10%", new ContrastFilter(0.1f));
		availableFilters.put("contrast_minus10%", new ContrastFilter(-0.1f));
		availableFilters.put("contrast_plus50%", new ContrastFilter(0.5f));
		availableFilters.put("contrast_minus50%", new ContrastFilter(-0.5f));
		availableFilters.put("sand_5", new SandFilter(5));
		availableFilters.put("sand_20", new SandFilter(20));
		availableFilters.put("histogramm", new HistogramAnalyser());
	}
	
	public static Set<Entry<String,Filter>> getAllFilters(){
		return availableFilters.entrySet();
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
