package primitives;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6486675412028365375L;

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}
	
	public MyTableModel(Object[][] data, Object[] header) {
		super(data, header);
	}
}
