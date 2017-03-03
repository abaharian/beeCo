package primitives;

import java.awt.Component;
import java.awt.ComponentOrientation;

import javax.swing.JLabel;

import beeColony.G;

public class MyLabel extends JLabel {
	private static final long serialVersionUID = 8378202138873517331L;
	
	private int id;

	public int getId(){
		return id;
	}
	
	public MyLabel(int id) {
		super();
		this.id = id;
		setFont(G.getDefaultFont());
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setAlignmentX(Component.RIGHT_ALIGNMENT);
		setOpaque(true);
	}
	
	public MyLabel(int id, String str){
		this(id);
		setText(str);
	}
}
