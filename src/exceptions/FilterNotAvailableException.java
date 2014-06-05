package exceptions;

/**
 * 
 * Eine Exception, die auftritt, wenn ein nicht vohandener Filter ausgewaehlt wird
 * @author Lukas Richter, Benedikt Ringlein
 *
 */
public class FilterNotAvailableException extends RuntimeException {

	private static final long serialVersionUID = 6958424514019861309L;

	public FilterNotAvailableException(String filtername){
		super("Der ausgewaehlte Filter '" + filtername + "' ist nicht verfuegbar");
	}
}
