package beeColony;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import Database.Database;
import persindatepicker.PersianCalendar;
import persindatepicker.PersianDatePicker;
import primitives.City;
import primitives.DefaultButton;
import primitives.DefaultLabel;
import primitives.DefaultTextField;
import primitives.DuplicateKeyException;
import primitives.InnerLinePanel;
import primitives.Licence;
import primitives.MyComboBox;
import primitives.MyTable;
import primitives.Person;
import primitives.Province;
import primitives.TableObject;

public class PersonDetail {
	private String addPersonFail = "افزودن شخص مورد نظر با خطا مواجه شد";
	private String addPersonSuccess = "شخص مورد نظر با موفقیت اضافه شد";
	private static final String dialogWidth = "dialogWidth";
	private static final String dialogHeight = "dialogHeight";
	private static final String fileListDialogWidth = "fileListWidth";
	private static final String fileListDialogHeight = "fileListHeight";

	
	private Person lastPersonLoaded;
	private MyTable tableOfLicences;
	
//	InnerLinePanel
	InnerLinePanel panel1 = new InnerLinePanel();
	InnerLinePanel panel2 = new InnerLinePanel();
	InnerLinePanel panel3 = new InnerLinePanel();
	InnerLinePanel panel4 = new InnerLinePanel();
	InnerLinePanel panel5 = new InnerLinePanel();
	InnerLinePanel panel6 = new InnerLinePanel();
	InnerLinePanel panel7 = new InnerLinePanel();
	InnerLinePanel panel8 = new InnerLinePanel();
	InnerLinePanel panel9 = new InnerLinePanel();

	
	DefaultLabel lblNationalId = new DefaultLabel("کد ملی: ");
	DefaultLabel lblLastName = new DefaultLabel("نام خانوادگی: ");
	DefaultLabel lblFirstName = new DefaultLabel("نام: ");
	DefaultLabel lblfatherName = new DefaultLabel("نام پدر: ");
	DefaultLabel lblCertNo = new DefaultLabel("شماره شناسنامه: ");
	DefaultLabel lblInsurranceNo = new DefaultLabel("شماره بیمه: ");
	DefaultLabel lblBirthDate = new DefaultLabel("تاریخ تولد: ");
	DefaultLabel lblAddress = new DefaultLabel("آدرس: ");
	DefaultLabel lblPostalCode = new DefaultLabel("کد پستی: ");
	DefaultLabel lblPhone = new DefaultLabel("تلفن: ");
	DefaultLabel lblCellPhone = new DefaultLabel("همراه: ");
	DefaultLabel lblCooperationMemberId = new DefaultLabel("شماره عضویت در تعاونی: ");
	DefaultLabel lblResult = new DefaultLabel("");
	
	DefaultTextField tfCellPhone;
	DefaultTextField tfPhone;
	DefaultTextField tfCertNo;
	DefaultTextField tfInsurranceNo;
	DefaultTextField tfNationalId;
	DefaultTextField tfFirstName;
	DefaultTextField tfLastName;
	DefaultTextField tffatherName;
	PersianDatePicker tfBirthDate;
	DefaultTextField tfAddress;
	DefaultTextField tfPostalCode;
	DefaultTextField tfCooperationMemberId;

	DefaultLabel lblPersonTitle = new DefaultLabel("");

	DefaultButton btnSearch = new DefaultButton("جستجو");
	DefaultButton btnAddNewUser = new DefaultButton("افزودن شخص جدید");
	DefaultButton btnAddnewLicence = new DefaultButton("صدور پروانه جدید");
	DefaultButton btnInsertLicence = new DefaultButton("ثبت پروانه جدید");
	
	DefaultLabel lbltemp1 = new DefaultLabel("");
	DefaultLabel lbltemp2 = new DefaultLabel("");
	DefaultLabel lbltemp3 = new DefaultLabel("");
	DefaultLabel lbltemp4 = new DefaultLabel("");
	DefaultLabel lbltemp5 = new DefaultLabel("");
	DefaultLabel lbltemp6 = new DefaultLabel("");
	DefaultLabel lbltemp7 = new DefaultLabel("");
	DefaultLabel lbltemp8 = new DefaultLabel("");
	DefaultLabel lbltemp9 = new DefaultLabel("");
	DefaultLabel lbltemp10 = new DefaultLabel("");
	DefaultLabel lbltemp11 = new DefaultLabel("");
	DefaultLabel lbltemp12 = new DefaultLabel("");
	
	public PersonDetail() {
		 tfCellPhone = new DefaultTextField();
		 tfPhone = new DefaultTextField();
		 tfCertNo = new DefaultTextField();
		 tfInsurranceNo = new DefaultTextField();
		 tfNationalId = new DefaultTextField();
		 tfFirstName = new DefaultTextField();
		 tfLastName = new DefaultTextField();
		 tffatherName = new DefaultTextField();
		 tfBirthDate = new PersianDatePicker(100, 0);
		 tfAddress = new DefaultTextField();
		 tfPostalCode = new DefaultTextField();
		 tfCooperationMemberId = new DefaultTextField();
		 
		 tfCellPhone.setDocument(G.getPhoneNoDocument());
		 tfPhone.setDocument(G.getPhoneNoDocument());
		 tfCertNo.setDocument(G.getCertificateNoDocument());
		 tfInsurranceNo.setDocument(G.getNumberDocument());
		 tfNationalId.setDocument(G.getNationalIdDocument());
		 tfPostalCode.setDocument(G.getPostalCodeDocument());
		 tfCooperationMemberId.setDocument(G.getCooperationMemberIdDocument());
		 
		 tfNationalId.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSearch.doClick();
			}
		});
		
		lblResult.setForeground(Color.RED);
		lblResult.setPreferredSize(new Dimension(G.prefSize.width * 4, G.prefSize.height));
		
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadPersonDetail();
			}
		});
		
		btnAddNewUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addNewPersonOrCompany();
			}
		});
		
		btnAddnewLicence.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showNewLicenceDialog();
			}
		});
		
		btnInsertLicence.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Licence l = insertNewLicence();
				if(l != null)
					prepareFileChooser(l);
			}
		});
	}
	
	private void loadPersonDetail(){
		Database db = new Database();
		try {
			panel2.setVisible(false);
			panel3.setVisible(false);
			panel4.setVisible(false);
			panel5.setVisible(false);
			panel6.setVisible(false);
			panel7.setVisible(false);
			panel8.setVisible(false);
			panel9.setVisible(false);

			if(isCompany){
				try{
					Integer.parseInt(tfNationalId.getText());
				}catch(Exception e){
					lblResult.setText("شماره ثبت نادرست است. باید حتماً یک عدد باشد");
					panel8.setVisible(true);
					return;
				}
			} else {
				if(!G.CheckNationalCode(tfNationalId.getText())){
					lblResult.setText("کد ملی وارد شده نادرست است");
					panel8.setVisible(true);
					return;
				}
			}
			
			db.openConnection();
			Person p = db.getPersonsByNationalId(tfNationalId.getText());
			
			panel2.setVisible(true);
			panel3.setVisible(true);
			panel4.setVisible(true);
			panel5.setVisible(true);
			panel6.setVisible(true);

			if(p != null){
				lastPersonLoaded = p;
				
				panel7.setVisible(false);
				panel8.setVisible(false);
				
				lblPersonTitle.setText(isCompany ?"مشخصات شرکت" :"مشخصات شخص");
				tfFirstName.setText(p.getFirstName());
				tfLastName.setText(p.getLastName());
				tffatherName.setText(p.getFatherName());
				tfCertNo.setText(String.valueOf(p.getCertificateNo()));
				tfInsurranceNo.setText("" + p.getInsuranceNo());
				tfBirthDate.setText(p.getBirthDate());
				tfAddress.setText(p.getAddress());
				tfCellPhone.setText(p.getMobileNo());
				tfCooperationMemberId.setText("" + p.getCooperationMemberId());
				tfPhone.setText(p.getPhoneNo());
				tfPostalCode.setText(p.getPostalCode());
				
				setAllTextFieldsEnable(false);
				panel9.setVisible(true);
		
				Vector<TableObject> vec = db.getAllLicences(lastPersonLoaded.getNationalID());
				tableOfLicences.updateData(vec, new Licence());
			} else {
				panel7.setVisible(true);
				panel8.setVisible(false);
				
				if(isCompany)
					lblPersonTitle.setText("افزودن شرکت جدید با این شماره ثبت: ");
				else
					lblPersonTitle.setText("افزودن شخص جدید با این کد ملی");
				
				tfFirstName.setText("");
				tfLastName.setText("");
				tffatherName.setText("");
				tfCertNo.setText("");
				tfInsurranceNo.setText("");
				tfBirthDate.setText("");
				tfAddress.setText("");
				tfCellPhone.setText("");
				tfCooperationMemberId.setText("");
				tfPhone.setText("");
				tfPostalCode.setText("");
				
				setAllTextFieldsEnable(true);
				panel9.setVisible(false);
			}		

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	private void setAllTextFieldsEnable(boolean en){
		tfAddress.setEditable(en);
		tfBirthDate.setEditable(en);
		tfCellPhone.setEditable(en);
		tfCertNo.setEditable(en);
		tfCooperationMemberId.setEditable(en);
		tffatherName.setEditable(en);
		tfFirstName.setEditable(en);
		tfLastName.setEditable(en);
		tfPhone.setEditable(en);
		tfPostalCode.setEditable(en);
		tfInsurranceNo.setEditable(en);
	}
	
	public JPanel getTopPanel(){
		JPanel panelAll = new JPanel();
		panelAll.setLayout(new BoxLayout(panelAll, BoxLayout.Y_AXIS));
		panelAll.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		panel1.add(lblNationalId);
		panel1.add(tfNationalId);
		panel1.add(lbltemp1);
		panel1.add(lbltemp2);
		panel1.add(btnSearch);
		
		panel2.add(lblPersonTitle);
		lblPersonTitle.setPreferredSize(new Dimension(G.prefSize.width * 2, G.prefSize.height));
		lblPersonTitle.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblPersonTitle.setText("      مشخصات شخص مورد نظر:");
		
		panel3.add(lblFirstName);
		panel3.add(tfFirstName);
		panel3.add(lblLastName);
		panel3.add(tfLastName);
		panel3.add(lblfatherName);
		panel3.add(tffatherName);
		
		panel4.add(lblCertNo);
		panel4.add(tfCertNo);
		panel4.add(lblBirthDate);
		panel4.add(tfBirthDate);
		panel4.add(lblInsurranceNo);
		panel4.add(tfInsurranceNo);
		
		panel5.add(lblPostalCode);
		panel5.add(tfPostalCode);
		panel5.add(lblPhone);
		panel5.add(tfPhone);
		panel5.add(lblCellPhone);
		panel5.add(tfCellPhone);
		
		panel6.add(lblCooperationMemberId);
		panel6.add(tfCooperationMemberId);
		tfAddress.setPreferredSize(new Dimension(G.prefSize.width * 3, G.prefSize.height));
		panel6.add(lblAddress);
		panel6.add(tfAddress);
		
		panel7.add(lbltemp3);
		panel7.add(lbltemp4);
		panel7.add(lbltemp5);
		panel7.add(lbltemp6);
		panel7.add(btnAddNewUser);
		
		panel8.add(lblResult);
		
		panel9.add(lbltemp7);
		panel9.add(lbltemp8);
		panel9.add(lbltemp9);
		panel9.add(lbltemp10);
		panel9.add(btnAddnewLicence);
		
		panel2.setVisible(false);
		panel3.setVisible(false);
		panel4.setVisible(false);
		panel5.setVisible(false);
		panel6.setVisible(false);
		panel7.setVisible(false);
		panel8.setVisible(false);
		panel9.setVisible(false);


		panelAll.add(Box.createRigidArea(new Dimension(0,10)));
		panelAll.add(panel1);
		panelAll.add(Box.createRigidArea(new Dimension(0,5)));
		panelAll.add(panel2);
		panelAll.add(Box.createRigidArea(new Dimension(0,5)));
		panelAll.add(panel3);
		panelAll.add(Box.createRigidArea(new Dimension(0,5)));
		panelAll.add(panel4);
		panelAll.add(Box.createRigidArea(new Dimension(0,5)));
		panelAll.add(panel5);
		panelAll.add(Box.createRigidArea(new Dimension(0,5)));
		panelAll.add(panel6);
		panelAll.add(Box.createRigidArea(new Dimension(0,5)));
		panelAll.add(panel7);
		panelAll.add(Box.createRigidArea(new Dimension(0,5)));
		panelAll.add(panel8);
		panelAll.add(Box.createRigidArea(new Dimension(0,5)));
		panelAll.add(panel9);
		panelAll.add(Box.createRigidArea(new Dimension(0,10)));

		JPanel retPanel = new JPanel();
		retPanel.setLayout(new BoxLayout(retPanel, BoxLayout.Y_AXIS));
		retPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		TitledBorder b = new TitledBorder("صدور پروانه جدید");
		b.setTitleFont(G.getDefaultFont());
		panelAll.setBorder(b);
		retPanel.add(Box.createRigidArea(new Dimension(0,20)));
		retPanel.add(panelAll);


		return retPanel;
	}
	
	private void addNewPersonOrCompany(){
		Person p = new Person();
		
		try{
			p.setAddress(tfAddress.getText());
			p.setBirthDate(tfBirthDate.getText());
			try{
				p.setCertificateNo(Integer.parseInt(tfCertNo.getText()));
			}catch(Exception e){
				p.setCertificateNo(0);
			}
			p.setCooperationMemberId(Integer.parseInt(tfCooperationMemberId.getText()));
			p.setFatherName(tffatherName.getText());
			if(isCompany)
				p.setFirstName("شرکت");
			else
				p.setFirstName(tfFirstName.getText());
			try{
				p.setInsuranceNo(Long.parseLong(tfInsurranceNo.getText()));
			}catch(Exception e){
				p.setInsuranceNo(0);
			}
			p.setLastName(tfLastName.getText());
			p.setMobileNo(tfCellPhone.getText());
			p.setNationalID(tfNationalId.getText());
			p.setPhoneNo(tfPhone.getText());
			p.setPostalCode(tfPostalCode.getText());
		} catch (Exception e){
			lblResult.setText("در ورود مشخصات دقت نمایید. برخی داده ها درست وارد نشده اند");
			panel8.setVisible(true);
			return;
		}
		
		Database db = new Database();
		try{
			db.openConnection();
			db.insertNewPerson(p);
			lblResult.setText(addPersonSuccess);
			panel7.setVisible(false);
			lastPersonLoaded = p;
			setAllTextFieldsEnable(false);
			panel9.setVisible(true);
		} catch(Exception e){
			lblResult.setText(addPersonFail);
		} finally {
			db.closeConnection();
		}
		panel8.setVisible(true);
	}

	public JPanel getTablePanel() {
		JPanel panel = new JPanel(new GridLayout());
		Vector<TableObject> vec = new Vector<TableObject>(); 

		tableOfLicences = new MyTable(vec, new Licence(), "licence_table");
		tableOfLicences.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				
				if(e.getClickCount() == 2) {
					showFileListWindow((int)tableOfLicences.getValueAt(tableOfLicences.getSelectedRow(), 0));
				}
			}
		});
        
        panel.setOpaque(true);
        JScrollPane scroll = new JScrollPane(tableOfLicences);
        scroll.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		panel.add(scroll);
		return panel;

	}
	
	private JLabel lblTitle;
	private DefaultLabel lblSerialNo;
	private DefaultLabel lblLicenceNo;
	private DefaultLabel lblProvince;
	private DefaultLabel lblCity;
	private DefaultLabel lblIssueDate;
	private DefaultLabel lblIssueNo;
	private DefaultLabel lblEduLevel;
	private DefaultLabel lblBackground;
	private DefaultLabel lblModernColony;
	private DefaultLabel lblTradColony;
	private DefaultLabel lblErr;
	
	private DefaultTextField tfSerialNo;
	private DefaultTextField tfLicenceNo;
	private MyComboBox<Province> comboProvince;
	private MyComboBox<City> comboCity;
	private PersianDatePicker dtIssuedate;
	private DefaultTextField tfIssueNo;
	private MyComboBox<String> comboEduLevel;
	private DefaultTextField tfBackground;
	private DefaultTextField tfModernColony;
	private DefaultTextField tfTradColony;
		
	private InnerLinePanel line0;
	private InnerLinePanel line1 = new InnerLinePanel();
	private InnerLinePanel line2 = new InnerLinePanel();
	private InnerLinePanel line3 = new InnerLinePanel();
	private InnerLinePanel line4 = new InnerLinePanel();
	private InnerLinePanel line5 = new InnerLinePanel();
	private InnerLinePanel line6 = new InnerLinePanel();
	private InnerLinePanel line7 = new InnerLinePanel();
	private InnerLinePanel line8 = new InnerLinePanel();
	private InnerLinePanel line9 = new InnerLinePanel();
	private InnerLinePanel line10 = new InnerLinePanel();
	private InnerLinePanel line11;
	private InnerLinePanel line12;
	
	private JPanel panelDialog;
	private FileManager fileManagerm;
	
	private void showNewLicenceDialog(){
		lblTitle = new DefaultLabel("");
		lblTitle.setFont(G.getDefaultFont(2));
		lblTitle.setPreferredSize(new Dimension(G.prefSize.width * 3, G.prefSize.height));
		lblSerialNo = new DefaultLabel(Licence.getAttribSerialNO());
		lblLicenceNo = new DefaultLabel(Licence.getAttribLicenceNo());
		lblProvince = new DefaultLabel(Licence.getAttribProvince());
		lblCity = new DefaultLabel(Licence.getAttribCity());
		lblIssueDate = new DefaultLabel(Licence.getAttribIssueDate());
		lblIssueNo = new DefaultLabel(Licence.getAttribIssueNumber());
		lblEduLevel = new DefaultLabel(Licence.getAttribEducationLevel());
		lblBackground = new DefaultLabel(Licence.getAttribBackGround());
		lblModernColony = new DefaultLabel(Licence.getAttribModernColonyNo());
		lblTradColony = new DefaultLabel(Licence.getAttribTraditionalColonyNo());
		lblErr = new DefaultLabel("");
		lblErr.setForeground(Color.RED);
		lblErr.setPreferredSize(new Dimension(G.prefSize.width * 3, G.prefSize.height));
		
		tfSerialNo = new DefaultTextField();
		tfLicenceNo = new DefaultTextField();
		dtIssuedate = new PersianDatePicker();
		tfIssueNo = new DefaultTextField();
		comboEduLevel = new MyComboBox<>(G.getEduLevel());
		tfBackground = new DefaultTextField();
		tfModernColony = new DefaultTextField();
		tfTradColony = new DefaultTextField();
		
		tfSerialNo.setDocument(G.getNumberDocument());
		tfLicenceNo.setDocument(G.getNumberDocument());
		tfBackground.setDocument(G.getNumberDocument());
		tfModernColony.setDocument(G.getNumberDocument());
		tfTradColony.setDocument(G.getNumberDocument());
		
		line0 = new InnerLinePanel();
		line1 = new InnerLinePanel();
		line2 = new InnerLinePanel();
		line3 = new InnerLinePanel();
		line4 = new InnerLinePanel();
		line5 = new InnerLinePanel();
		line6 = new InnerLinePanel();
		line7 = new InnerLinePanel();
		line8 = new InnerLinePanel();
		line9 = new InnerLinePanel();
		line10 = new InnerLinePanel();
		line11 = new InnerLinePanel();
		line12 = new InnerLinePanel();
		
		Database db = new Database();
		try {
			db.openConnection();
			Vector<Province> province = db.getAllProvince();
			comboProvince = new MyComboBox<Province>(province);
			if(province.size() > 0){
				Vector<City> city = db.getAllCity(province.elementAt(0));
				comboCity = new MyComboBox<>(city);
				selectDefaultCity(comboCity, city);
			}
			else{
				comboCity = new MyComboBox<>();
			}
			comboProvince.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					comboCity = new MyComboBox<>(db.getAllCity((Province)comboProvince.getSelectedItem()));
				}
			});
		} catch (Exception e) {
		}
		line0.add(lblTitle);
		line1.add(lblSerialNo);
		line1.add(tfSerialNo);
		line2.add(lblLicenceNo);
		line2.add(tfLicenceNo);
		line3.add(lblProvince);
		line3.add(comboProvince);
		line4.add(lblCity);
		line4.add(comboCity);
		line5.add(lblIssueDate);
		line5.add(dtIssuedate);
		line6.add(lblIssueNo);
		line6.add(tfIssueNo);
		line7.add(lblEduLevel);
		line7.add(comboEduLevel);
		if(isCompany) line7.setVisible(false);
		line8.add(lblBackground);
		line8.add(tfBackground);
		line9.add(lblModernColony);
		line9.add(tfModernColony);
		line10.add(lblTradColony);
		line10.add(tfTradColony);
		line11.add(lblErr);
		line12.add(lbltemp11);
		line12.add(lbltemp12);
		line12.add(btnInsertLicence);
		
		panelDialog = new JPanel();
		panelDialog.setLayout(new BoxLayout(panelDialog, BoxLayout.PAGE_AXIS));
		panelDialog.add(line0);
		panelDialog.add(line1);
		panelDialog.add(line2);
		panelDialog.add(line3);
		panelDialog.add(line4);
		panelDialog.add(line5);
		panelDialog.add(line6);
		panelDialog.add(line7);
		panelDialog.add(line8);
		panelDialog.add(line9);
		panelDialog.add(line10);
		panelDialog.add(line11);
		panelDialog.add(line12);
		
		fileManagerm = new FileManager();
		panelDialog.add(fileManagerm);
		fileManagerm.setVisible(false);
		
		JScrollPane scroll = new JScrollPane(panelDialog);
		
		JFrame dialog = new JFrame();
		dialog.add(scroll);
//		dialog.setModal(true);
		dialog.setTitle("صدور پروانه جدید");
		int w = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;
		int h = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 ;
		
		Config conf = new Config();
		w = conf.getIntConfig(dialogWidth, w);
		h = conf.getIntConfig(dialogHeight, h);
		dialog.setSize(new Dimension(w, h));
//		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		
		dialog.addComponentListener(new ComponentAdapter() {			
			@Override
			public void componentResized(ComponentEvent arg0) {
				conf.storeConfig(dialogWidth, String.valueOf(dialog.getWidth()));
				conf.storeConfig(dialogHeight, String.valueOf(dialog.getHeight()));
			}
			
			@Override
			public void componentShown(ComponentEvent arg0) {
				super.componentShown(arg0);
				String str = "ثبت پروانه جدید برای ";
				str += lastPersonLoaded.getFirstName() + " ";
				str += lastPersonLoaded.getLastName() + " (";
				str += lastPersonLoaded.getNationalID() + ") ";
				lblTitle.setText(str);
			}
			
		});
		
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				super.windowClosing(arg0);
				btnSearch.doClick();
			}
		});
	}
	
	
	private void selectDefaultCity(MyComboBox<City> combo, Vector<City> vec){
		for(City c : vec){
			if(c.getName().equals("ساری"))
				combo.setSelectedItem(c);
		}
	}

	private Licence insertNewLicence(){
		Database db = new Database();
		Licence licence = new Licence();

		try{
			licence.setBackGround(Integer.parseInt(tfBackground.getText()));
			licence.setCity((City)comboCity.getSelectedItem());
			if(isCompany) 
				licence.setEducationLevel("---");
			else
				licence.setEducationLevel((String)comboEduLevel.getSelectedItem());
			licence.setIssueDate(dtIssuedate.getText());
			PersianCalendar pc = dtIssuedate.getCalendar();
			pc.addPersianDate(Calendar.YEAR, G.licenceLifeTimeInYear);
			licence.setExpireDate(pc.getPersianShortDate());
			licence.setIssueNumber(tfIssueNo.getText());
			licence.setLicenceNo(Integer.parseInt(tfLicenceNo.getText()));
			licence.setSerialNO(Integer.parseInt(tfSerialNo.getText()));
			licence.setModernColonyNo(Integer.parseInt(tfModernColony.getText()));
			licence.setTraditionalColonyNo(Integer.parseInt(tfTradColony.getText()));
			licence.setProvince((Province)comboProvince.getSelectedItem());
		}catch(Exception e){
			lblErr.setText("یک یا برخی از ورودیها نادرست است");
			return null;
		}
		
		try {
			db.openConnection();
			db.InsertNewLicence(lastPersonLoaded, licence);
		} catch (DuplicateKeyException e){
			lblErr.setText(e.getMessage());
			return null;
		} catch (Exception e) {
			lblErr.setText("ارتباط با پایگاه داده دچار مشکل شده است، لطفاً با مسئول سیستم تماس بگیرید");
			return null;
		}
		
		return licence;
	}

	private void prepareFileChooser(Licence l){
		if(l == null) return;
		btnSearch.doClick();
		lblTitle.setText("افزودن نامه ها و فایل های مرتبط");
		line1.setVisible(false);
		line2.setVisible(false);
		line3.setVisible(false);
		line4.setVisible(false);
		line5.setVisible(false);
		line6.setVisible(false);
		line7.setVisible(false);
		line8.setVisible(false);
		line9.setVisible(false);
		line10.setVisible(false);
		line11.setVisible(false);
		line12.setVisible(false);

		fileManagerm.setLicenceSerianNo(l.getSerialNO());
		fileManagerm.setVisible(true);
	}

	JFrame fileListWindow;
	private void showFileListWindow(int licenceSerialNo){
		lblTitle = new DefaultLabel("");
		String str = "فایل ها و نامه های مرتبط با پروانه شماره  ";
		str += licenceSerialNo;
		lblTitle.setText(str);
		lblTitle.setFont(G.getDefaultFont(2));
		lblTitle.setPreferredSize(new Dimension(G.prefSize.width * 3, G.prefSize.height));
		InnerLinePanel line1 = new InnerLinePanel();
		line1.add(lblTitle);
		
		FileManager fileManager = new FileManager(licenceSerialNo); 
		JScrollPane scroll = new JScrollPane(fileManager);
		
		
		
		if(fileListWindow != null){
			fileListWindow.setVisible(false);
		}
		
		fileListWindow = new JFrame();
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(line1, BorderLayout.NORTH);
		contentPane.add(scroll, BorderLayout.CENTER);
		contentPane.setBorder(BorderFactory.createRaisedBevelBorder());
		fileListWindow.setContentPane(contentPane);
		fileListWindow.setTitle("جزییات پروانه");
		int w = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;
		int h = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 ;
		
		Config conf = new Config();
		w = conf.getIntConfig(fileListDialogWidth, w);
		h = conf.getIntConfig(fileListDialogHeight, h);
		fileListWindow.setSize(new Dimension(w, h));
		fileListWindow.setLocationRelativeTo(null);
		fileListWindow.setVisible(true);
		
		fileListWindow.addComponentListener(new ComponentAdapter() {			
			@Override
			public void componentResized(ComponentEvent arg0) {
				conf.storeConfig(fileListDialogWidth, String.valueOf(fileListWindow.getWidth()));
				conf.storeConfig(fileListDialogHeight, String.valueOf(fileListWindow.getHeight()));
			}			
		});		
	}
	
	private boolean isCompany;
	public PersonDetail(boolean isCompany){
		this();
		this.isCompany = isCompany;
		if(isCompany){
			lblBirthDate.setText("تاریخ تاسیس: ");
			lblCertNo.setVisible(false);
			tfCertNo.setVisible(false);
			lblfatherName.setVisible(false);
			tffatherName.setVisible(false);
			lblFirstName.setVisible(false);
			tfFirstName.setText("شرکت");
			tfFirstName.setVisible(false);
			lblInsurranceNo.setVisible(false);
			tfInsurranceNo.setVisible(false);
			lblLastName.setText("نام شرکت: ");
			lblNationalId.setText("شماره ثبت شرکت: ");
			lblPersonTitle.setText("افزودن شرکت جدید با این شماره ثبت: ");
			btnAddNewUser.setText("افزودن شرکت جدید");
			
			addPersonFail = "افزودن شرکت مورد نظر با خطا مواجه شد";
			addPersonSuccess = "شرکت مورد نظر با موفقیت اضافه شد";
		}
	}
}
