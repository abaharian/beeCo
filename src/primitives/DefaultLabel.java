package primitives;

import javax.swing.JLabel;

import beeColony.G;

public class DefaultLabel extends JLabel{
	private static final long serialVersionUID = 1L;

	public DefaultLabel(String str) {
		super(str);
		setFont(G.getDefaultFont());
		setPreferredSize(G.prefSize);
	}
}