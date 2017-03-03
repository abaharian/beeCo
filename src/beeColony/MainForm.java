package beeColony;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import primitives.MyMenubar;

public class MainForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7555091221638309011L;
	
	
	private static final String LAST_WIDTH = "lastWidth";
	private static final String LAST_HEIGHT = "lastHeight";
	private static final String LAST_X = "lastX";
	private static final String LAST_Y = "lastY";
	private static final String IS_MAXIMIZED = "ismaximized";
	private static final String DIVIDER_LOCATION = "dividerLocation";
	
	private Config conf;
	private JSplitPane splitPane;
	private JMenuBar toolbar;
	private UICreator creator;
	
	private void setFirstSize(){
		Dimension dim;
		String maximized = "false";
		final String defaultVal = "-100";
		int defaultDim = Integer.parseInt(defaultVal);

		int w = Integer.parseInt(conf.getConfig(LAST_WIDTH, defaultVal));
		int h = Integer.parseInt(conf.getConfig(LAST_HEIGHT, defaultVal));
		maximized = conf.getConfig("ismaximized", "false");

		if(w == defaultDim)
			w = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 700;
		if(h == defaultDim)
			h = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 300;
		setMinimumSize(new Dimension(w, h));

		dim = getMinimumSize();
		setSize(dim);
		setMinimumSize(new Dimension(0,0));
		
		if(maximized != null && maximized.toLowerCase().equals("true"))
			setExtendedState(MAXIMIZED_BOTH);
	}
	
	private void setFirstLocation(){
		int x = Integer.parseInt(conf.getConfig(LAST_X, "100"));
		int y = Integer.parseInt(conf.getConfig(LAST_Y, "100"));
		setLocation(x, y);		
	}

	private void setFirstDividerLocation(){
		int dividerLocation = Integer.parseInt(conf.getConfig(DIVIDER_LOCATION, "300"));
		splitPane.setDividerLocation(dividerLocation);
	}
	
	private void saveCurrentSize(){
		String w, h;
		String maximized = "false";
		boolean wsw = false, hsw = false;
		
		w = String.valueOf((int)getWidth());
		h = String.valueOf((int)getHeight());
		
		int state = getExtendedState();
		switch (state) {
		case MAXIMIZED_BOTH:
			maximized = "true";
			wsw = false;
			hsw = false;
			break;
		case MAXIMIZED_VERT:
			wsw = true;
			hsw = false;
			break;
		case MAXIMIZED_HORIZ:
			wsw = false;
			hsw = true;
			break;
		default:
			wsw = true;
			hsw = true;
			break;
		}
		
		if(wsw)
			conf.storeConfig(LAST_WIDTH, w);
		if(hsw)
			conf.storeConfig(LAST_HEIGHT, h);
		conf.storeConfig(IS_MAXIMIZED, maximized);
	}
	
	private void saveCurrentLocation(){
		Point p = getLocation();
		conf.storeConfig(LAST_X, "" + (int)p.getX());
		conf.storeConfig(LAST_Y, "" + (int)p.getY());
	}

	private void saveDividerLocation(){
		int dividerLocation = splitPane.getDividerLocation();
		conf.storeConfig(DIVIDER_LOCATION, String.valueOf(dividerLocation));
	}

	private void addListener(){
		addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent arg0) {
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				saveCurrentSize();
				setFirstDividerLocation();
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				saveCurrentLocation();
			}

			@Override
			public void componentHidden(ComponentEvent arg0) {
			}
		});	
		
		splitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
					saveDividerLocation();
			}
		});
	}
	
	public JSplitPane createSplitPane(){
		JPanel rightP;
		JPanel leftP;
		splitPane = new JSplitPane();
		splitPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setDividerSize(5);
		setFirstDividerLocation();
		

		creator.CreateLeftSidePanel();
		creator.createRightSidePanel();
		leftP = creator.getLeftPanel();
		rightP = creator.getRightPanel();
		
		splitPane.setLeftComponent(leftP);
		splitPane.setRightComponent(rightP);
		return splitPane;
	}
	
	public MainForm() {
		creator = new UICreator(this);
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		MyMenubar mmenu = new MyMenubar(this);
		toolbar = mmenu.getMenuBar();
		setJMenuBar(toolbar);
		conf = new Config();
		G.loadSwitches();
		splitPane = createSplitPane();
		setFirstSize();
		setFirstLocation();
		setFirstDividerLocation();
		addListener();
		
		setTitle("شرکت تعاونی زنبورداران استان مازندران");
		getContentPane().add(splitPane, BorderLayout.CENTER);
	}
	
	public void refreshPage(){
		JPanel rightP;
		creator.createRightSidePanel();
		rightP = creator.getRightPanel();
		setFirstDividerLocation();
		splitPane.setRightComponent(rightP);
	}

	public static void main(String[] args) {
		MainForm form = new MainForm();
		form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		form.setVisible(true);

	}

}
