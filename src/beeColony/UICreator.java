package beeColony;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Database.Database;
import primitives.MyLabel;
import primitives.MyTable;
import primitives.Person;
import primitives.TableObject;

public class UICreator {
	private static String menuWidth = "menuWidth";
	public static final int IndivisualList = 1;
	public static final int NewLicence = 2;
	public static final int NewLicenceCompany = 3;
	private static final int PrefMenuHeight = 70;

	private MyTable tableOfPersons;
	
	private int selectedMenu;
	private JPanel rightPanel;
	private JPanel leftPanel;
	
	private Config conf;
	
	private MainForm form;
	
	public JPanel getRightPanel() {
		return rightPanel;
	}

	public JPanel getLeftPanel() {
		return leftPanel;
	}

	public UICreator(MainForm parent) {
		conf = new Config();
		selectedMenu = NewLicence;
		form = parent;
	}
	
	public void CreateLeftSidePanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		MyLabel lblIndivisual = new MyLabel(IndivisualList, "          فهرست افراد");
		MyLabel lblNewLicence = new MyLabel(NewLicence, "          صدور پروانه جدید");
		MyLabel lblNewLicenceCompany = new MyLabel(NewLicenceCompany, "          پروانه جدید اشخاص حقوقی");
		MyLabel[] labels = new MyLabel[] {lblNewLicence, lblNewLicenceCompany, lblIndivisual};
		
		makeMenu(labels, panel.getBackground(), Color.LIGHT_GRAY);
		
		panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		for(int i = 0; i < labels.length; i++){
			panel.add(labels[i]);
		}

		panel.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent arg0) {
				
			}
			
			@Override
			public void componentResized(ComponentEvent arg0) {
				setLabelSizeByPanel(panel, labels);
				conf.storeConfig(menuWidth, String.valueOf(panel.getWidth()));
			}
			
			@Override
			public void componentMoved(ComponentEvent arg0) {
				setLabelSizeByPanel(panel, labels);
			}
			
			@Override
			public void componentHidden(ComponentEvent arg0) {
			}
		});
		
		int firstWidth = conf.getIntConfig(menuWidth, 300);
		for(int i = 0; i < labels.length; i++){
			labels[i].setMaximumSize(new Dimension(firstWidth, PrefMenuHeight));
		}

		leftPanel = panel;
	}

	public void makeMenu(MyLabel[] labels, Color defaultColor, Color selectColor){
		for(int i = 0; i < labels.length; i++){
			final MyLabel lbl = labels[i];
			lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lbl.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					setBackgroundColor(labels, defaultColor);
					lbl.setBackground(selectColor);
					selectedMenu = lbl.getId();
					form.refreshPage();
				}
			});
		}
		
		if(labels.length > 0)
			labels[0].setBackground(selectColor);
	}
	
	private void setBackgroundColor(MyLabel[] labels, Color bckColor){
		for(int i = 0; i < labels.length; i++){
			labels[i].setBackground(bckColor);
		}
	}
	
	private void setLabelSizeByPanel(JPanel panel, JLabel[] labels){
		Dimension dim = panel.getSize();
		int width = dim.width;
		dim.setSize(width, PrefMenuHeight);
		for(int i = 0; i < labels.length; i++){
			labels[i].setMaximumSize(dim);
		}

	}
	
	public void createRightSidePanel(){
		switch(selectedMenu){
		case IndivisualList:
			createIndivisualListOnright();
			break;
		case NewLicence:
			createLicenceListOnRight();
			break;
		case NewLicenceCompany:
			createCompanyLicenceListOnRight();
			break;
		}
	}
	
	public void createIndivisualListOnright(){
		rightPanel = new JPanel(new BorderLayout());
		rightPanel.add(getSearchPanel(), BorderLayout.NORTH);
		Vector<TableObject> personVec;
		try {
			Database db = new Database();
			db.openConnection();
			personVec = db.getAllPersons();
		} catch (Exception e) {
			personVec = new Vector<TableObject>();
		}

		JPanel tablePanel = getTableOfPersons(personVec);
		rightPanel.add(tablePanel,BorderLayout.CENTER);
	}
	
	public void createLicenceListOnRight(){
		rightPanel = new JPanel(new BorderLayout());
		PersonDetail pd = new PersonDetail();
		
		JPanel topPanel = pd.getTopPanel();

		rightPanel.add(topPanel, BorderLayout.NORTH);
		JPanel tablePanel = pd.getTablePanel();
		rightPanel.add(tablePanel,BorderLayout.CENTER);
	}
	private JPanel getTableOfPersons(Vector<TableObject> personVec){
		JPanel panel = new JPanel(new GridLayout());

		tableOfPersons = new MyTable(personVec, new Person(), "person_table");		
        
        panel.setOpaque(true);
        JScrollPane scroll = new JScrollPane(tableOfPersons);
        scroll.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		panel.add(scroll);
		return panel;
	}
	
	private JPanel getSearchPanel(){
		Font f = G.getDefaultFont();
		Dimension prefSize = new Dimension(150, 30);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 3));
		panel1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 3));
		panel2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 3, 3));
		panel3.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		JLabel lblNationalId = new JLabel("کد ملی / شماره ثبت: ");
		JLabel lblLastName = new JLabel("نام خانوادگی / نام شرکت: ");
		JLabel lblFirstName = new JLabel("نام: ");
		JLabel lblCertNo = new JLabel("شماره شناسنامه: ");
		JLabel lblPhone = new JLabel("تلفن: ");
		JLabel lblCellPhone = new JLabel("همراه: ");
		JTextField tfCellPhone = new JTextField();
		JTextField tfPhone = new JTextField();
		JTextField tfCertNo = new JTextField();
		JTextField tfNationalId = new JTextField();
		JTextField tfFirstName = new JTextField();
		JTextField tfLastName = new JTextField();
		
		tfNationalId.setDocument(G.getNationalIdDocument());
		tfCellPhone.setDocument(G.getPhoneNoDocument());
		tfPhone.setDocument(G.getPhoneNoDocument());
		tfCertNo.setDocument(G.getCertificateNoDocument());
		
		JButton btnSearch = new JButton("جستجو");
		JLabel lbltemp1 = new JLabel("");
		JLabel lbltemp2 = new JLabel("");
		JLabel lbltemp3 = new JLabel("");
		JLabel lbltemp4 = new JLabel("");
		JLabel lbltemp5 = new JLabel("");
		JLabel lbltemp6 = new JLabel("");
		
		lblNationalId.setFont(f);
		lblFirstName.setFont(f);
		lblLastName.setFont(f);
		lblCellPhone.setFont(f);
		lblCertNo.setFont(f);
		lblPhone.setFont(f);
		tfLastName.setFont(f);
		tfFirstName.setFont(f);
		tfNationalId.setFont(f);
		tfCellPhone.setFont(f);
		tfCertNo.setFont(f);
		tfPhone.setFont(f);
		btnSearch.setFont(f);
		
		
		lblFirstName.setPreferredSize(prefSize);
		lblLastName.setPreferredSize(prefSize);
		lblNationalId.setPreferredSize(prefSize);
		lblCellPhone.setPreferredSize(prefSize);
		lblCertNo.setPreferredSize(prefSize);
		lblPhone.setPreferredSize(prefSize);
		tfFirstName.setPreferredSize(prefSize);
		tfLastName.setPreferredSize(prefSize);
		tfNationalId.setPreferredSize(prefSize);
		tfCellPhone.setPreferredSize(prefSize);
		tfCertNo.setPreferredSize(prefSize);
		tfPhone.setPreferredSize(prefSize);
		btnSearch.setPreferredSize(prefSize);
		lbltemp1.setPreferredSize(prefSize);
		lbltemp2.setPreferredSize(prefSize);
		lbltemp3.setPreferredSize(prefSize);
		lbltemp4.setPreferredSize(prefSize);
		lbltemp5.setPreferredSize(prefSize);
		lbltemp6.setPreferredSize(prefSize);

		panel1.add(lblNationalId);
		panel1.add(tfNationalId);
		panel1.add(lblFirstName);
		panel1.add(tfFirstName);
		panel1.add(lblLastName);
		panel1.add(tfLastName);

		panel2.add(lblCertNo);
		panel2.add(tfCertNo);
		panel2.add(lblPhone);
		panel2.add(tfPhone);
		panel2.add(lblCellPhone);
		panel2.add(tfCellPhone);
		
		panel3.add(lbltemp1);
		panel3.add(lbltemp2);
		panel3.add(lbltemp3);
		panel3.add(lbltemp4);
		panel3.add(lbltemp5);
//		panel3.add(lbltemp6);
		panel3.add(btnSearch);
		
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.add(panel1);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		panel.add(panel2);
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.add(panel3);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		
		JPanel retPanel = new JPanel();
		retPanel.setLayout(new BoxLayout(retPanel, BoxLayout.Y_AXIS));
		TitledBorder b = new TitledBorder("جستجو در لیست اعضا");
		b.setTitleFont(f);
		panel.setBorder(b);
		retPanel.add(Box.createRigidArea(new Dimension(0,20)));
		retPanel.add(panel);
		
		ActionListener al = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Database db = new Database();
				Vector<TableObject> vec;
				int certNo = 0;
				if(tfCertNo.getText().length() > 0){
					try{
					certNo = Integer.parseInt(tfCertNo.getText());
					}catch(Exception e){
						System.err.println("Error in certNo!");
						return;
					}
				}
				try {
					db.openConnection();
					vec = db.getAllPersonsBySearch(tfNationalId.getText(),
							tfFirstName.getText(), tfLastName.getText(), 
							certNo, tfPhone.getText(), 
							tfCellPhone.getText());
					tableOfPersons.updateData(vec, new Person());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		btnSearch.addActionListener(al);
		tfCellPhone.addActionListener(al);
		tfCertNo.addActionListener(al);
		tfFirstName.addActionListener(al);
		tfLastName.addActionListener(al);
		tfNationalId.addActionListener(al);
		tfPhone.addActionListener(al);
		return retPanel;
	}

	public void createCompanyLicenceListOnRight(){
		rightPanel = new JPanel(new BorderLayout());
		PersonDetail pd = new PersonDetail(true);
		
		JPanel topPanel = pd.getTopPanel();

		rightPanel.add(topPanel, BorderLayout.NORTH);
		JPanel tablePanel = pd.getTablePanel();
		rightPanel.add(tablePanel,BorderLayout.CENTER);
	}
	
}
