package primitives;

import java.util.Vector;

import javax.swing.JComboBox;

import beeColony.G;

public class MyComboBox<E> extends JComboBox<E> {
	private static final long serialVersionUID = -3261120145388026184L;

	public MyComboBox() {
		super();
		setFont(G.getDefaultFont());
	}
	
	public MyComboBox(Vector<E> vec){
		super(vec);
		setFont(G.getDefaultFont());
	}
}
