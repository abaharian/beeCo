package beeColony;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

import primitives.FilteredDocument;

public class G {
	public static boolean Person_table_nationalID_sw = true;
	private static String Person_table_nationalID = "Person_table_nationalID_sw";
	public static boolean Person_table_firstName_sw = true;
	private static String Person_table_firstName = "Person_table_firstName_sw";
	public static boolean Person_table_lastName_sw = true;
	private static String Person_table_lastName = "Person_table_lastName_sw";
	public static boolean Person_table_fatherName_sw = true;
	private static String Person_table_fatherName = "Person_table_fatherName_sw";
	public static boolean Person_table_certNo_sw = true;
	private static String Person_table_certNo = "Person_table_certNo_sw";
	public static boolean Person_table_birthDate_sw = true;
	private static String Person_table_birthDate = "Person_table_birthDate_sw";
	public static boolean Person_table_insuranceNo_sw = true;
	private static String Person_table_insuranceNo = "Person_table_insuranceNo_sw";
	public static boolean Person_table_address_sw = true;
	private static String Person_table_address = "Person_table_address_sw";
	public static boolean Person_table_postalCode_sw = true;
	private static String Person_table_postalCode = "Person_table_postalCode_sw";
	public static boolean Person_table_phoneNo_sw = true;
	private static String Person_table_phoneNo = "Person_table_phoneNo_sw";
	public static boolean Person_table_mobileNo_sw = true;
	private static String Person_table_mobileNo = "Person_table_mobileNo_sw";
	public static boolean Person_table_cooperationNo_sw = true;
	private static String Person_table_cooperationNo = "Person_table_nationalID_sw";
	
	public static boolean licenceTableSerialNoSw = true;
	public static boolean licenceTableLicenceNoSw = true;
	public static boolean licenceTableProvinceSw = true;
	public static boolean licenceTableCitySw = true;
	public static boolean licenceTableIssueDateSw = true;
	public static boolean licenceTableIssueNumberSw = true;
	public static boolean licenceTableEducationLevelSw = false;
	public static boolean licenceTableBackgroundSw = true;
	public static boolean licenceTableModernColonyNoSw = true;
	public static boolean licenceTableTraditionalColonyNoSw = true;
	public static boolean licenceTableExpireDateSw = true;
	public static boolean licenceTableAttachedFileSw = true;
	
	public static final int licenceLifeTimeInYear = 5;
	
	public static final String companyFirstName = "شرکت";
	
	public static Font getDefaultFont(){
		return new Font("IRANSans(FaNum)", 0, 14);
	}
	
	public static Font getDefaultFont(int addedSize){
		return new Font("IRANSans(FaNum)", 0, 14 + addedSize);
	}
	
	public static void loadSwitches(){
		Config conf = new Config();
		
		Person_table_address_sw = conf.getBooleanConfig(Person_table_address, true);
		Person_table_birthDate_sw = conf.getBooleanConfig(Person_table_birthDate, true);
		Person_table_certNo_sw = conf.getBooleanConfig(Person_table_certNo, true);
		Person_table_cooperationNo_sw = conf.getBooleanConfig(Person_table_cooperationNo, true);
		Person_table_fatherName_sw = conf.getBooleanConfig(Person_table_fatherName, true);
		Person_table_firstName_sw = conf.getBooleanConfig(Person_table_firstName, true);
		Person_table_insuranceNo_sw = conf.getBooleanConfig(Person_table_insuranceNo, true);
		Person_table_lastName_sw = conf.getBooleanConfig(Person_table_lastName, true);
		Person_table_mobileNo_sw = conf.getBooleanConfig(Person_table_mobileNo, true);
		Person_table_nationalID_sw = conf.getBooleanConfig(Person_table_nationalID, true);
		Person_table_phoneNo_sw = conf.getBooleanConfig(Person_table_phoneNo, true);
		Person_table_postalCode_sw = conf.getBooleanConfig(Person_table_postalCode, true);
	}
	
	public static void storeSwitches(){
		Config conf = new Config();
		
		conf.storeConfig(Person_table_address, String.valueOf(Person_table_address_sw));
		conf.storeConfig(Person_table_birthDate, String.valueOf(Person_table_birthDate_sw));
		conf.storeConfig(Person_table_certNo, String.valueOf(Person_table_certNo_sw));
		conf.storeConfig(Person_table_cooperationNo, String.valueOf(Person_table_cooperationNo_sw));
		conf.storeConfig(Person_table_fatherName, String.valueOf(Person_table_fatherName_sw));
		conf.storeConfig(Person_table_firstName, String.valueOf(Person_table_firstName_sw));
		conf.storeConfig(Person_table_insuranceNo, String.valueOf(Person_table_insuranceNo_sw));
		conf.storeConfig(Person_table_lastName, String.valueOf(Person_table_lastName_sw));
		conf.storeConfig(Person_table_mobileNo, String.valueOf(Person_table_mobileNo_sw));
		conf.storeConfig(Person_table_nationalID, String.valueOf(Person_table_nationalID_sw));
		conf.storeConfig(Person_table_phoneNo, String.valueOf(Person_table_phoneNo_sw));
		conf.storeConfig(Person_table_postalCode, String.valueOf(Person_table_postalCode_sw));
		
	}
	
	public static Vector<String> getEduLevel(){
		Vector<String> vec = new Vector<String>();
		vec.addElement("\u0632\u06cc\u0631 \u062f\u06cc\u067e\u0644\u0645");
		vec.addElement("\u062f\u06cc\u067e\u0644\u0645");
		vec.addElement("\u06a9\u0627\u0631\u062f\u0627\u0646\u06cc");
		vec.addElement("\u06a9\u0627\u0631\u0634\u0646\u0627\u0633\u06cc");
		vec.addElement("\u06a9\u0627\u0631\u0634\u0646\u0627\u0633\u06cc \u0627\u0631\u0634\u062f");
		vec.addElement("\u062f\u06a9\u062a\u0631\u06cc");
		return vec;
	}
	
	public static final Dimension prefSize = new Dimension(150, 30);

	public static boolean CheckNationalCode(String nationalCode) {
		try{
			if (nationalCode.length() == 10) {
				if (nationalCode.equals("1111111111")
						|| nationalCode.equals("0000000000")
						|| nationalCode.equals("2222222222")
						|| nationalCode.equals("3333333333")
						|| nationalCode.equals("4444444444")
						|| nationalCode.equals("5555555555")
						|| nationalCode.equals("6666666666")
						|| nationalCode.equals("7777777777")
						|| nationalCode.equals("8888888888")
						|| nationalCode.equals("9999999999")
						|| nationalCode.equals("0123456789")) {
					return false;
				}

				int c = Integer.parseInt(nationalCode.substring(9, 10));

				int n = Integer.parseInt(nationalCode.substring(0, 1)) * 10
						+ Integer.parseInt(nationalCode.substring(1, 2)) * 9
						+ Integer.parseInt(nationalCode.substring(2, 3)) * 8
						+ Integer.parseInt(nationalCode.substring(3, 4)) * 7
						+ Integer.parseInt(nationalCode.substring(4, 5)) * 6
						+ Integer.parseInt(nationalCode.substring(5, 6)) * 5
						+ Integer.parseInt(nationalCode.substring(6, 7)) * 4
						+ Integer.parseInt(nationalCode.substring(7, 8)) * 3
						+ Integer.parseInt(nationalCode.substring(8, 9)) * 2;
				int r = n - (n / 11) * 11;
				if ((r == 0 && r == c) || (r == 1 && c == 1)
						|| (r > 1 && c == 11 - r)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}catch(Throwable t){
			return false;
		}
	}
	
	public static FilteredDocument getNationalIdDocument(){
		return new FilteredDocument("0123456789", 10);
	}
	
	public static FilteredDocument getPhoneNoDocument(){
		return new FilteredDocument("0123456789", 11);
	}
	
	public static FilteredDocument getCertificateNoDocument(){
		return new FilteredDocument("0123456789");
	}
	
	public static FilteredDocument getNumberDocument(){
		return new FilteredDocument("123456789");
	}
	
	public static FilteredDocument getPostalCodeDocument(){
		return new FilteredDocument("0123456789-", 11);
	}
	
	public static FilteredDocument getCooperationMemberIdDocument(){
		return new FilteredDocument("0123456789", 5);
	}
}
