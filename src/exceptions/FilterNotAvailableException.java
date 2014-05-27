package exceptions;

public class FilterNotAvailableException extends Exception {

	public FilterNotAvailableException(String filtername){
		super("Der ausgewaehlte Filter '" + filtername + "' ist nicht verfuegbar");
	}
}
