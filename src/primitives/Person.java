package primitives;

import java.util.Vector;

import beeColony.G;

public class Person implements TableObject{
	private String nationalID;
	private String firstName;
	private String lastName;
	private String fatherName;
	private int certificateNo;
	private String birthDate;
	private long insuranceNo;
	private String address;
	private String postalCode;
	private String phoneNo;
	private String mobileNo;
	private int cooperationMemberId;

	public static String getAttribNationalId(){
		return "کد ملی / شماره ثبت"; // code Melli
	}
	
	public static String getAttribFirstName(){
		return "\u0646\u0627\u0645"; 								// naam
	}
	
	public static String getAttribLastName(){
		String str = "\u0646\u0627\u0645 \u062e\u0627\u0646\u0648\u0627\u062f\u06af\u06cc";	//naame khanevadegi
		str += " / " + "نام شرکت";
		return str;
	}
	
	public static String getAttribFatherName(){
		return "\u0646\u0627\u0645 \u067e\u062f\u0631";	//name pedar
	}
	
	public static String getAttribCertificateNo(){
	return "\u0634\u0645\u0627\u0631\u0647 \u0634\u0646\u0627\u0633\u0646\u0627\u0645\u0647";	// shomare shenasname
	}
	public static String getAttribBirthDate(){
		String str = "\u062a\u0627\u0631\u06cc\u062e \u062a\u0648\u0644\u062f";	//tarikhe tavalod
		str += " / " + "ثبت شرکت";
		return str;
	}
	public static String getAttribInsuranceNo(){
		return "\u0634\u0645\u0627\u0631\u0647 \u0628\u06cc\u0645\u0647";	//shomare bime
	}
	public static String getAttribAddress(){
		return "\u0622\u062f\u0631\u0633";	//address
	}
	public static String getAttribPostalCode(){
		return "\u06a9\u062f \u067e\u0633\u062a\u06cc";	//code posti
	}
	public static String getAttribPhoneNo(){
		return "\u0634\u0645\u0627\u0631\u0647 \u062a\u0644\u0641\u0646";	//telephone
	}
	public static String getAttribMobileNo(){
		return "\u062a\u0644\u0641\u0646 \u0647\u0645\u0631\u0627\u0647";	//hamrah
	}
	public static String getAttribCooperationMemberId(){
		return "\u0634\u0645\u0627\u0631\u0647 \u0639\u0636\u0648\u06cc\u062a \u062f\u0631 \u062a\u0639\u0627\u0648\u0646\u06cc";	//shomare ozviat
	}
	
	public String[] getAttribs() {
		Vector<String> vec = new Vector<String>();
		if(G.Person_table_nationalID_sw)
			vec.add(getAttribNationalId());				
		if(G.Person_table_firstName_sw)
			vec.add(getAttribFirstName());
		if(G.Person_table_lastName_sw)
			vec.add(getAttribLastName());
		if(G.Person_table_fatherName_sw)
			vec.add(getAttribFatherName());
		if(G.Person_table_certNo_sw)
			vec.add(getAttribCertificateNo());
		if(G.Person_table_birthDate_sw)	
			vec.add(getAttribBirthDate());
		if(G.Person_table_insuranceNo_sw)
			vec.add(getAttribInsuranceNo());
		if(G.Person_table_address_sw)
			vec.add(getAttribAddress());
		if(G.Person_table_postalCode_sw)
			vec.add(getAttribPostalCode());
		if(G.Person_table_phoneNo_sw)
			vec.add(getAttribPhoneNo());
		if(G.Person_table_mobileNo_sw)
			vec.add(getAttribMobileNo());
		if(G.Person_table_cooperationNo_sw)
			vec.add(getAttribCooperationMemberId());
		String[] res = new String[vec.size()];
		for(int i = 0; i < res.length; i++){
			res[i] = vec.elementAt(i);
		}
		return res;
	}
	
	public Person(String nationalID, String firstName, String lastName,
			String fatherName, int certificateNo, String birthDate,
			long insuranceNo, String address, String potalCode,
			String phoneNo, String mobileNo, int cooperationMemberId) {
		super();
		this.nationalID = nationalID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fatherName = fatherName;
		this.certificateNo = certificateNo;
		this.birthDate = birthDate;
		this.insuranceNo = insuranceNo;
		this.address = address;
		this.postalCode = potalCode;
		this.phoneNo = phoneNo;
		this.mobileNo = mobileNo;
		this.cooperationMemberId = cooperationMemberId;
	}


	public Person(){
		super();
	}

	public String getNationalID() {
		return nationalID;
	}


	public void setNationalID(String nationalID) {
		this.nationalID = nationalID;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getFatherName() {
		return fatherName;
	}


	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}


	public int getCertificateNo() {
		return certificateNo;
	}


	public void setCertificateNo(int certificateNo) {
		this.certificateNo = certificateNo;
	}


	public String getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}


	public long getInsuranceNo() {
		return insuranceNo;
	}


	public void setInsuranceNo(long insuranceNo) {
		this.insuranceNo = insuranceNo;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}


	public String getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	public int getCooperationMemberId() {
		return cooperationMemberId;
	}


	public void setCooperationMemberId(int cooperationMemberId) {
		this.cooperationMemberId = cooperationMemberId;
	}
	
	public Object[] getObjectArray(){
		Vector<Object> vec = new Vector<Object>();
		if(G.Person_table_nationalID_sw)
			vec.add(nationalID);
		if(G.Person_table_firstName_sw)
			vec.add(firstName);
		if(G.Person_table_lastName_sw)
			vec.add(lastName);
		if(G.Person_table_fatherName_sw)
			vec.add(fatherName);
		if(G.Person_table_certNo_sw)
			vec.add(certificateNo);
		if(G.Person_table_birthDate_sw)	
			vec.add(birthDate);
		if(G.Person_table_insuranceNo_sw)
			vec.add(insuranceNo);
		if(G.Person_table_address_sw)
			vec.add(address);
		if(G.Person_table_postalCode_sw)
			vec.add(postalCode);
		if(G.Person_table_phoneNo_sw)
			vec.add(phoneNo);
		if(G.Person_table_mobileNo_sw)
			vec.add(mobileNo);
		if(G.Person_table_cooperationNo_sw)
			vec.add(cooperationMemberId);
		Object[] obj = new Object[vec.size()];
		for(int i = 0; i < obj.length; i++){
			obj[i] = vec.elementAt(i);
		}

		return obj;
	}
}
