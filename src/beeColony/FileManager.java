package beeColony;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.apache.commons.io.FileUtils;

import Database.Database;
import primitives.DefaultButton;
import primitives.DefaultLabel;
import primitives.DefaultTextField;
import primitives.InnerLinePanel;
import primitives.MyFile;

public class FileManager extends JPanel {
	Dimension prefSizeBigger2 = new Dimension(G.prefSize.width * 2, G.prefSize.height);
	Dimension prefSizeSmaler3 = new Dimension(G.prefSize.width / 3, G.prefSize.height);
	private class FileLinePanel extends JPanel{
		private static final long serialVersionUID = 4699403855523737104L;

		private DefaultLabel lblName;
		private DefaultLabel lblOpen;
		private DefaultLabel lblRemove;
		private DefaultLabel lblTitle;
		private MyFile file;
		
		public FileLinePanel(MyFile file, boolean isEditable) {
			super();
			this.file = file;
			
			setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 3));			
			setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			
			lblName = new DefaultLabel(file.getFilename());
			lblName.setPreferredSize(prefSizeBigger2);
			lblName.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			
			lblTitle = new DefaultLabel(file.getTitle());
			lblTitle.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			
			lblOpen = new DefaultLabel("باز کردن");
			lblRemove = new DefaultLabel("حذف");
			
			lblOpen.setPreferredSize(prefSizeSmaler3);
			lblRemove.setPreferredSize(prefSizeSmaler3);
			
			lblOpen.setForeground(Color.BLUE);
			lblRemove.setForeground(Color.BLUE);
			lblOpen.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblRemove.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
			lblOpen.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					super.mouseClicked(arg0);
					try {
						Desktop.getDesktop().open(FileLinePanel.this.file.getFile());
					} catch (IOException e) {
					}
				}
			});
			
			lblRemove.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					super.mouseClicked(arg0);
					removeFile();
				}
			});
			


			ImageIcon image = new ImageIcon("attach.png");
			JLabel label = new JLabel("", image, JLabel.CENTER);
			this.add(label);
			this.add(lblName);
			this.add(lblTitle);
			this.add(lblOpen);
			this.add(lblRemove);
			
			if(!isEditable)
				lblRemove.setVisible(false);
		}
		
		private void removeFile(){
			fileCount--;
			setVisible(false);
			if(fileCount <= 0){
				fileCount = 0;
				panelNoFile.setVisible(true);
			}
			Database db = new Database();
			try {
				db.openConnection();
				db.removeFile(file);
			} catch (Exception e) {
			}
		}
		
	}

	private static final long serialVersionUID = -6580594193574549304L;

	private int fileCount;
	
	private int licenceSerialNo;
	private JPanel fileListPanel;
	
	private InnerLinePanel line1;
	private InnerLinePanel line2;
	private InnerLinePanel line3;
	private InnerLinePanel line4;
	private InnerLinePanel panelNoFile;
	
	private DefaultTextField tfFilePath;
	private DefaultButton btnBrowse;
	private DefaultButton btnAttach;
	private JFileChooser fileChooser;
	private DefaultLabel lblLetterNo;
	private DefaultTextField tfLetterNo;
	private DefaultLabel lblTitle;
	private DefaultTextField tfTitle;
	private DefaultLabel lbltemp = new DefaultLabel("");
	private DefaultLabel lblNofile = new DefaultLabel("فایل یا نامه ای برای مشاهده وجود ندارد");
	
	private File lastFileSelect;
	
	public FileManager() {
		fileCount = 0;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		fileListPanel = new JPanel();
		fileListPanel.setLayout(new BoxLayout(fileListPanel, BoxLayout.Y_AXIS));
		fileListPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		panelNoFile = new InnerLinePanel();
		panelNoFile.add(lblNofile);
		lblNofile.setPreferredSize(prefSizeBigger2);
		lblNofile.setForeground(Color.RED);
		fileListPanel.add(panelNoFile);
		TitledBorder b = BorderFactory.createTitledBorder("فهرست فایلهای مرتبط");
		b.setTitleFont(G.getDefaultFont());
		fileListPanel.setBorder(b);
//		File f = new File("d:\\temp.txt");
//		fileListPanel.add(new FileLinePanel(f));
		this.add(fileListPanel);
		
		line1 = new InnerLinePanel();
		lblLetterNo = new DefaultLabel("شماره نامه");
		tfLetterNo = new DefaultTextField();
		line1.add(lblLetterNo);
		line1.add(tfLetterNo);
		
		line2 = new InnerLinePanel();
		lblTitle = new DefaultLabel("عنوان نامه یا فایل");
		tfTitle = new DefaultTextField();
		line2.add(lblTitle);
		line2.add(tfTitle);
		
		fileChooser = new JFileChooser();
		line4 = new InnerLinePanel();
		tfFilePath = new DefaultTextField();
		tfFilePath.setPreferredSize(prefSizeBigger2);
		btnBrowse = new DefaultButton("انتخاب فایل");
		line4.add(tfFilePath);
		line4.add(btnBrowse);
		btnBrowse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = fileChooser.showOpenDialog(FileManager.this);
	            
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	               lastFileSelect = fileChooser.getSelectedFile();
	               tfFilePath.setText(lastFileSelect.getPath() + lastFileSelect.getName());
	            } else {
	                          
	            }      
				
			}
		});
		line3 = new InnerLinePanel();
		btnAttach = new DefaultButton("افزودن فایل");
		btnAttach.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addNewFile();
			}
		});
		lbltemp.setPreferredSize(prefSizeBigger2);
		line3.add(lbltemp);
		line3.add(btnAttach);
		
		this.add(line1);
		this.add(line2);
		this.add(line4);
		this.add(line3);
		
	}
	
	public FileManager(int serialNo){
		this();
		licenceSerialNo = serialNo;
		
		btnAttach.setVisible(false);
		btnBrowse.setVisible(false);
		tfFilePath.setVisible(false);
		tfLetterNo.setVisible(false);
		tfTitle.setVisible(false);
		lblTitle.setVisible(false);
		lblLetterNo.setVisible(false);
				
		Database db = new Database();
		try {
			db.openConnection();
			Vector<MyFile> files = db.getAllfiles(serialNo);
			
			for(MyFile f : files){
				fileListPanel.add(new FileLinePanel(f, false));
				panelNoFile.setVisible(false);
			}
		} catch (Exception e) {
			
		}
	}
	
	public void setLicenceSerianNo(int serialNo){
		licenceSerialNo = serialNo;
	}
	
	public void addNewFile(){
		Database db = new Database();
		try {
			db.openConnection();
		} catch (Exception e1) {
			return;
		}
		int id = db.getMaxFileId() + 1;
		String newpath = "files/" + id + "_" + lastFileSelect.getName();
		File newFile = new File(newpath);
		try {
			FileUtils.copyFile(lastFileSelect, newFile);
		} catch (IOException e1) {
			newFile = lastFileSelect;
		}
		MyFile f = new MyFile();
		f.setId(id);
		f.setFile(newFile);
		f.setLetterNo(tfLetterNo.getText());
		f.setTitle(tfTitle.getText());
		
		db.addNewFile(licenceSerialNo, f);
		
		FileLinePanel fp = new FileLinePanel(f, true);
		fileListPanel.add(fp);
		fileCount++;
		panelNoFile.setVisible(false);
		tfFilePath.setText("");
		tfTitle.setText("");
		tfLetterNo.setText("");
		fileListPanel.revalidate();
		fileListPanel.repaint();			
	}
}
