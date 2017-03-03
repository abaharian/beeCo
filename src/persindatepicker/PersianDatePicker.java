package persindatepicker;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JPanel;

import primitives.MyComboBox;
	
public class PersianDatePicker extends JPanel {
	private static final long serialVersionUID = 795651717505840892L;
	
	MyComboBox<Integer> day;
	MyComboBox<String> month;
	MyComboBox<Integer> year;
	JLabel lbl1;
	JLabel lbl2;
	int yearBefore = 100;
	int yearAfter = 100;
	public PersianDatePicker() {
		setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 3));
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		PersianCalendar pc = new PersianCalendar();
		
		lbl1 = new JLabel("/");
		lbl2 = new JLabel("/");
		day = new MyComboBox<>(getDays(31));
		month = new MyComboBox<>(getMonth());
		year = new MyComboBox<>(getYear(pc));
		
		year.setSelectedItem(pc.getPersianYear());
		month.setSelectedIndex(pc.getPersianMonth() - 1);
		day.setSelectedItem(pc.getPersianDay());
		
		month.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Vector<Integer> dayVec;
				int selectedIndex = day.getSelectedIndex();
				int m = month.getSelectedIndex() + 1;
				if(m <= 6)
					dayVec = getDays(31);
				else if (m < 12)
					dayVec = getDays(30);
				else {
					PersianCalendar pc = new PersianCalendar();
					pc.setPersianDate((int)year.getSelectedItem(), month.getSelectedIndex() + 1, (int)day.getSelectedItem());
					if(pc.isPersianLeapYear())
						dayVec = getDays(30);
					else
						dayVec = getDays(29);
				}
				day.setModel(new DefaultComboBoxModel<>(dayVec));
				if(selectedIndex < dayVec.size())
					day.setSelectedIndex(selectedIndex);
				else 
					day.setSelectedIndex(dayVec.size() - 1);
			}
		});
		
		year.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Vector<Integer> dayVec;
				int m = month.getSelectedIndex() + 1;
				if(m != 12)
					return;
				int selectedIndex = day.getSelectedIndex();
				PersianCalendar pc = new PersianCalendar();
				pc.setPersianDate((int)year.getSelectedItem(), 
						month.getSelectedIndex() + 1, 
						(int)day.getSelectedItem());
				if(pc.isPersianLeapYear())
					dayVec = getDays(30);
				else
					dayVec = getDays(29);
				day.setModel(new DefaultComboBoxModel<>(dayVec));
				if(selectedIndex < dayVec.size())
					day.setSelectedIndex(selectedIndex);
				else 
					day.setSelectedIndex(dayVec.size() - 1);

				
			}
		});
		
		this.add(day);
		this.add(lbl1);
		this.add(month);
		this.add(lbl2);
		this.add(year);
	}
	
	public PersianDatePicker(int yearBefore, int yearAfter){
		this();
		this.yearBefore = yearBefore;
		this.yearAfter = yearAfter;
	}
	
	private Vector<Integer> getDays(int len){
		Vector<Integer> vec = new Vector<Integer>();
		for(int i = 1; i <= len; i++){
			vec.add(i);
		}
		return vec;
	}
	
	private Vector<String> getMonth(){
		Vector<String> vec = new Vector<String>();
		vec.addElement("\u0641\u0631\u0648\u0631\u062f\u06cc\u0646");
		vec.addElement("\u0627\u0631\u062f\u06cc\u0628\u0647\u0634\u062a");
		vec.addElement("\u062e\u0631\u062f\u0627\u062f");
		vec.addElement("\u062a\u06cc\u0631");
		vec.addElement("\u0645\u0631\u062f\u0627\u062f");
		vec.addElement("\u0634\u0647\u0631\u06cc\u0648\u0631");
		vec.addElement("\u0645\u0647\u0631");
		vec.addElement("\u0622\u0628\u0627\u0646");
		vec.addElement("\u0622\u0630\u0631");
		vec.addElement("\u062f\u06cc");
		vec.addElement("\u0628\u0647\u0645\u0646");
		vec.addElement("\u0627\u0633\u0641\u0646\u062f");
		return vec;
	}
	
	private Vector<Integer> getYear(PersianCalendar pc){
		Vector<Integer> vec = new Vector<Integer>();
		int thisYear = pc.getPersianYear();
		
		for(int i = thisYear - yearBefore; i <= thisYear + yearAfter; i++)
			vec.addElement(i);
		return vec;
	}

	public void setText(String dateStr) {
		PersianCalendar pc;
		try{
			PersianDateParser pp = new PersianDateParser(dateStr);
			pc = pp.getPersianDate();
		} catch(Exception e){
			pc = new PersianCalendar();
		}
		
		year.setSelectedItem(pc.getPersianYear());
		month.setSelectedIndex(pc.getPersianMonth() - 1);
		day.setSelectedItem(pc.getPersianDay());
	}

	public String getText() {
		PersianCalendar pc = new PersianCalendar();
		pc.setPersianDate((int)year.getSelectedItem(), 
				month.getSelectedIndex() + 1, 
				(int)day.getSelectedItem());
		return pc.getPersianShortDate();
		
	}
	
	@Override
	public void setEnabled(boolean arg0) {
		super.setEnabled(arg0);
		day.setEnabled(arg0);
		month.setEnabled(arg0);
		year.setEnabled(arg0);
	}
	
	public PersianCalendar getCalendar(){
		PersianCalendar pc = new PersianCalendar();
		pc.setPersianDate((int)year.getSelectedItem(), 
				month.getSelectedIndex() + 1, 
				(int)day.getSelectedItem());
		return pc;
		
	}

	public void setEditable(boolean en) {
		setEnabled(en);
	}
}