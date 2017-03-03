package primitives;

import javax.swing.JButton;

import beeColony.G;

public class DefaultButton extends JButton{
	private static final long serialVersionUID = -6054530070518700835L;

	public DefaultButton(String str) {
		super(str);
		setFont(G.getDefaultFont());
		setPreferredSize(G.prefSize);
	}
}

