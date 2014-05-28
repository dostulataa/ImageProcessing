package exceptions;

public class FilterNotAvailableException extends Exception {

	private static final long serialVersionUID = 6958424514019861309L;

	public FilterNotAvailableException(String filtername){
		super("Der ausgewaehlte Filter '" + filtername + "' ist nicht verfuegbar");
	}
}
