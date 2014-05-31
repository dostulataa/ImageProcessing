package filters;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ChainFilter implements Filter {
	
	private int current, total;

	private ArrayList<Filter> filters = new ArrayList<Filter>();

	@Override
	public BufferedImage process(BufferedImage... img) {
		total = filters.size();
		current = 0;
		for(Filter filter : filters){
			img[0] = filter.process(img);
			current++;
		}
		current = total;
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

	@Override
	public int getProgress() {
		return total>0?current*100/total:0;
	}

}
