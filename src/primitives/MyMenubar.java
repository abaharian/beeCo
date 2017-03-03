package primitives;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import beeColony.G;

public class MyMenubar {
	private JFrame parent;
	private JMenuBar menuBar;
	
	private JMenu menuSetting;
	private JMenuItem menuPersonTableSetting;
	public JMenuBar getMenuBar(){
		return menuBar;
	}
	
	public MyMenubar(JFrame parent){
		this.parent = parent;
		menuSetting = new JMenu("تنظیمات");
		menuSetting.setFont(G.getDefaultFont(-2));
		menuPersonTableSetting = new JMenuItem("تنظیمات جدول افراد");
		menuPersonTableSetting.setFont(G.getDefaultFont(-4));
		menuSetting.add(menuPersonTableSetting);
		
		
		menuBar = new JMenuBar();
		menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menuBar.add(menuSetting);
		
		addListeners();
	}
	
	private void addListeners(){
		menuPersonTableSetting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JPanel panel = createPersonTableSettingPanel();
				showDialog("test", panel, new Dimension(400, 300));
			}
		});
	}
	
	private JPanel createPersonTableSettingPanel(){
		JPanel res = new JPanel();
		res.setLayout(new GridLayout(1, 2));
		JPanel rightP = new JPanel();
		JPanel leftP = new JPanel();
		rightP.setLayout(new BoxLayout(rightP, BoxLayout.Y_AXIS));
		leftP.setLayout(new BoxLayout(leftP, BoxLayout.Y_AXIS));
		rightP.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		leftP.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		rightP.setAlignmentX(Component.RIGHT_ALIGNMENT);
		leftP.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		JCheckBox checks[] = new JCheckBox[12];
		checks[0] = new JCheckBox(Person.getAttribNationalId());
		checks[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				G.Person_table_nationalID_sw = !G.Person_table_nationalID_sw;
			}
		});
		
		checks[1] = new JCheckBox(Person.getAttribFirstName());
		checks[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				G.Person_table_firstName_sw = !G.Person_table_firstName_sw;
				
			}
		});
		
		checks[2] = new JCheckBox(Person.getAttribLastName());
		checks[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				G.Person_table_lastName_sw = !G.Person_table_lastName_sw;
				
			}
		});
		
		checks[3] = new JCheckBox(Person.getAttribFatherName());
		checks[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				G.Person_table_fatherName_sw = !G.Person_table_fatherName_sw;
			}
		});
		
		checks[4] = new JCheckBox(Person.getAttribCertificateNo());
		checks[4].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				G.Person_table_certNo_sw = !G.Person_table_certNo_sw;
				
			}
		});
		checks[5] = new JCheckBox(Person.getAttribBirthDate());
		checks[5].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				G.Person_table_birthDate_sw = !G.Person_table_birthDate_sw;
				
			}
		});
		
		checks[6] = new JCheckBox(Person.getAttribInsuranceNo());
		checks[6].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				G.Person_table_insuranceNo_sw = !G.Person_table_insuranceNo_sw;
				
			}
		});
		
		checks[7] = new JCheckBox(Person.getAttribAddress());
		checks[7].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				G.Person_table_address_sw = !G.Person_table_address_sw;
				
			}
		});
		
		checks[8] = new JCheckBox(Person.getAttribPostalCode());
		checks[8].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				G.Person_table_postalCode_sw = !G.Person_table_postalCode_sw;
				
			}
		});
		
		checks[9] = new JCheckBox(Person.getAttribPhoneNo());
		checks[9].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				G.Person_table_phoneNo_sw = !G.Person_table_phoneNo_sw;
				
			}
		});
		
		checks[10] = new JCheckBox(Person.getAttribMobileNo());
		checks[10].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				G.Person_table_mobileNo_sw = !G.Person_table_mobileNo_sw;
			}
		});
		
		checks[11] = new JCheckBox(Person.getAttribCooperationMemberId());
		checks[11].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				G.Person_table_cooperationNo_sw = !G.Person_table_cooperationNo_sw;
				
			}
		});
		checks[0].setSelected(G.Person_table_nationalID_sw);
		checks[1].setSelected(G.Person_table_firstName_sw);
		checks[2].setSelected(G.Person_table_lastName_sw);
		checks[3].setSelected(G.Person_table_fatherName_sw);
		checks[4].setSelected(G.Person_table_certNo_sw);
		checks[5].setSelected(G.Person_table_birthDate_sw);
		checks[6].setSelected(G.Person_table_insuranceNo_sw);
		checks[7].setSelected(G.Person_table_address_sw);
		checks[8].setSelected(G.Person_table_postalCode_sw);
		checks[9].setSelected(G.Person_table_phoneNo_sw);
		checks[10].setSelected(G.Person_table_mobileNo_sw);
		checks[11].setSelected(G.Person_table_cooperationNo_sw);
		
		for(int i = 0; i < checks.length; i++){
			checks[i].setFont(G.getDefaultFont(-2));
			checks[i].setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			checks[i].setAlignmentX(Component.RIGHT_ALIGNMENT);
			checks[i].setPreferredSize(new Dimension(50, 20));
			if(i < checks.length / 2)
				rightP.add(checks[i]);
			else
				leftP.add(checks[i]);
		}
		JButton btn= new JButton("تایید");
		btn.setFont(G.getDefaultFont(-2));
		btn.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				G.storeSwitches();
				SwingUtilities.getWindowAncestor(btn).setVisible(false);
			}
		});
		
		res.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);		
		res.add(rightP);
		res.add(leftP);
		BorderLayout layout = new BorderLayout();
		
		JLabel title = new JLabel("لطفاً ستونهای مورد نظر خود را انتخاب نمایید");
		title.setFont(G.getDefaultFont());
		title.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		title.setAlignmentX(Component.RIGHT_ALIGNMENT);
		JPanel panel = new JPanel(layout);
		
		panel.add(title, BorderLayout.NORTH);
		panel.add(res,BorderLayout.CENTER);
		panel.add(btn, BorderLayout.SOUTH);
		return panel;
	}
	
	private void showDialog(String title, JPanel panel, Dimension size) {
		if (panel == null)
			return;
		parent.setEnabled(false);
		myDialog dialog = new myDialog();
		dialog.add(panel);
		dialog.setModal(true);
		dialog.setTitle(title);
		dialog.setSize(size);
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}

	
	class myDialog extends JDialog {
		private static final long serialVersionUID = 9141475671591841840L;

		public void setVisible(boolean b) {
			if (b) {
				parent.setEnabled(false);
			} else {
				parent.setEnabled(true);
			}
			try {
				super.setVisible(b);
			} catch (Exception e) {
			}
		}
	}

}
