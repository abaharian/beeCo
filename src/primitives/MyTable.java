package primitives;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableCellRenderer;

import beeColony.Config;
import beeColony.G;

public class MyTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5138259103037813058L;

	private boolean setListenerOn;
	private static final String columnPrefix = "ColumnWidth";
	private String name;
	private MyTableModel tableModel;

	public MyTable(Vector<TableObject> personVec, TableObject dummyObj, String name) {
		this.name = name;
		setListenerOn = false;
		
		String columns[] = dummyObj.getAttribs();
		Object[][] data = new Object[personVec.size()][columns.length];
		for(int i = 0; i < personVec.size(); i++){
			data[i] = personVec.elementAt(i).getObjectArray();
		}
		
		tableModel = new MyTableModel(data, columns);
		setModel(tableModel);
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		addListener();
		
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        setPreferredScrollableViewportSize(new Dimension(500, 70));
        setFillsViewportHeight(true);
        loadColumnWidth();
        setFont(G.getDefaultFont(-2));
        getTableHeader().setFont(G.getDefaultFont());
        setRowHeight(45);
        setRightAlign();
        setFonts();
		setListenerOn = true;
	}
	
	public void updateData(Vector<TableObject> dataVec, TableObject dummyObj){
		setListenerOn = false;
		String columns[] = dummyObj.getAttribs();
		Object[][] data = new Object[dataVec.size()][columns.length];
		for(int i = 0; i < dataVec.size(); i++){
			data[i] = dataVec.elementAt(i).getObjectArray();
		}
		
		tableModel = new MyTableModel(data, columns);
		setModel(tableModel);
		loadColumnWidth();
		setRightAlign();
		setListenerOn = true;
	}
	
	public void addListener(){
		getColumnModel().addColumnModelListener(new TableColumnModelListener() {
			
			@Override
			public void columnSelectionChanged(ListSelectionEvent arg0) {
			}
			
			@Override
			public void columnRemoved(TableColumnModelEvent arg0) {
			}
			
			@Override
			public void columnMoved(TableColumnModelEvent arg0) {
			}
			
			@Override
			public void columnMarginChanged(ChangeEvent arg0) {
				if(setListenerOn)
					saveColumnWidth();
			}
			
			@Override
			public void columnAdded(TableColumnModelEvent arg0) {
			}
		});
	}
	
	private void saveColumnWidth(){
		Config conf = new Config();
		int width = 0;
		int n = getColumnModel().getColumnCount();
		for(int i = 0; i < n; i++){
			width = getColumnModel().getColumn(i).getWidth();
			conf.storeConfig(name + "_" + columnPrefix + i, String.valueOf(width));
		}
	}

	private void loadColumnWidth(){
		Config conf = new Config();
		int width = 0;
		String str;
		int n = getColumnModel().getColumnCount();
		
		for(int i = 0; i < n; i++){
			width = getColumnModel().getColumn(i).getWidth();
			str = conf.getConfig(name + "_" +columnPrefix + i, String.valueOf(width));
			width = Integer.parseInt(str);
			getColumnModel().getColumn(i).setPreferredWidth(width);
		}
	}

	private void setRightAlign(){
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
//		rightRenderer.setFont(new Font("Nazanin", 0, 14));
		int n = getColumnModel().getColumnCount();

		for(int i = 0; i < n; i++){

			getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
		}

	}
	
	private void setFonts(){
	}


}
