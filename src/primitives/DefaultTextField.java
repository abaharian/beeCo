package primitives;

import java.awt.ComponentOrientation;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import beeColony.G;

public class DefaultTextField extends JTextField{
	private static final long serialVersionUID = 1L;
	public DefaultTextField() {
		super();
		setFont(G.getDefaultFont());
		setPreferredSize(G.prefSize);
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				DefaultTextField.this.selectAll();
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				
			}
		});
	}
}
