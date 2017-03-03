package primitives;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class FilteredDocument extends PlainDocument
{
	private static final long serialVersionUID = 2982361177797263595L;
	
	public enum Case {
		UPPER_CASE,
		LOWER_CASE,
		NO_CHANGE
	}
	
	private int limit;
	private String filter;
	private Case caseState;
	
	/////////////////////////////////////////////// Constructors
	public FilteredDocument(String filter, int limit, Case casestate) {
        super();
        this.filter=filter;
        this.limit=limit;
        this.caseState=casestate;
    }
	public FilteredDocument(String filter, int limit) {
		this(filter,limit,Case.NO_CHANGE);
    }
	public FilteredDocument(String filter) {
		this(filter,0,Case.NO_CHANGE);
    }
    
    public void insertString(int offset, String  str, AttributeSet attr) throws BadLocationException {
        if (str == null) return;
        if(caseState==Case.UPPER_CASE)
        	str = str.toUpperCase();
        if(caseState==Case.LOWER_CASE)
        	str = str.toLowerCase();
        boolean f = true;
        for(int i=0; i<str.length(); i++){
        	f = filter.contains(str.substring(i, i+1));
        	if(!f) break;
        }
    	if(f){
    		if(limit!=0){
                if ( (getLength() + str.length()) <= limit )
                    super.insertString(offset, str, attr);
    		}
    		else
    			super.insertString(offset, str, attr);
    	}
    }//insertString
}