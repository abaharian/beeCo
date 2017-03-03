package primitives;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class InnerLinePanel extends JPanel{
	private static final long serialVersionUID = 4699403855523737104L;

	public InnerLinePanel() {
		super();
		setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 3));
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	}
}
