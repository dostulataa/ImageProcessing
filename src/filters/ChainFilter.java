package filters;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class ChainFilter implements Filter {

	private ArrayList<Filter> filters = new ArrayList<Filter>();

	@Override
	public BufferedImage process(BufferedImage... img) {
		for(Filter filter : filters){
			img[0] = filter.process(img);
		}
		return img[0];
	}

	/**
	 * Fuegt der Filterkette einen Filter hinzu
	 * 
	 * @param filter
	 *            Der hinzuzufuegende Filter
	 */
	public void add(Filter filter) {
		filters.add(filter);
	}

}
