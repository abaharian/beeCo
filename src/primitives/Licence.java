package primitives;

import java.util.Vector;

import beeColony.G;

public class Licence implements TableObject{
	private int serialNO;
	private int licenceNo;
	private Province province;
	private City city;
	private String issueDate;
	private String issueNumber;
	private String educationLevel;
	private int backGround;
	private int modernColonyNo;
	private int traditionalColonyNo;
	private String expireDate;

	public Licence() {
	}

	public Licence(int serialNO, int licenceNo, Province province, City city,
			String issueDate, String issueNumber,
			String educationLevel, int backGround,
			int modernColonyNo, int traditionalColonyNo, String expireDate) {
		super();
		this.serialNO = serialNO;
		this.licenceNo = licenceNo;
		this.province = province;
		this.city = city;
		this.issueDate = issueDate;
		this.issueNumber = issueNumber;
		this.educationLevel = educationLevel;
		this.backGround = backGround;
		this.modernColonyNo = modernColonyNo;
		this.traditionalColonyNo = traditionalColonyNo;
		this.expireDate = expireDate;
	}

	public int getSerialNO() {
		return serialNO;
	}

	public void setSerialNO(int serialNO) {
		this.serialNO = serialNO;
	}

	public int getLicenceNo() {
		return licenceNo;
	}

	public void setLicenceNo(int licenceNo) {
		this.licenceNo = licenceNo;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public int getBackGround() {
		return backGround;
	}

	public void setBackGround(int backGround) {
		this.backGround = backGround;
	}

	public int getModernColonyNo() {
		return modernColonyNo;
	}

	public void setModernColonyNo(int modernColonyNo) {
		this.modernColonyNo = modernColonyNo;
	}

	public int getTraditionalColonyNo() {
		return traditionalColonyNo;
	}

	public void setTraditionalColonyNo(int traditionalColonyNo) {
		this.traditionalColonyNo = traditionalColonyNo;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	@Override
	public String toString() {
		return "Licence [serialNO=" + serialNO + ", licenceNo=" + licenceNo
				+ ", province=" + province + ", city=" + city
				+ ", issueDate=" + issueDate + ", issueNumber=" + issueNumber
				+ ", educationLevel=" + educationLevel + ", backGround="
				+ backGround + ", modernColonyNo=" + modernColonyNo
				+ ", traditionalColonyNo=" + traditionalColonyNo
				+ ", expireDate=" + expireDate + "]";
	}

	public static String getAttribSerialNO() {
		return "شماره سریال";
	}

	public static String getAttribLicenceNo() {
		return "شماره پروانه";
	}

	public static String getAttribProvince() {
		return "استان";
	}

	public static String getAttribCity() {
		return "شهر";
	}

	public static String getAttribIssueDate() {
		return "تاریخ صدور";
	}

	public static String getAttribIssueNumber() {
		return "شماره صدور";
	}

	public static String getAttribEducationLevel() {
		return "سطح تحصیلات";
	}

	public static String getAttribBackGround() {
		return "سابقه زنبورداری";
	}

	public static String getAttribModernColonyNo() {
		return "تعداد کلنی مدرن";
	}

	public static String getAttribTraditionalColonyNo() {
		return "تعداد کلنی سنتی";
	}

	public static String getAttribExpireDate() {
		return "تاریخ انقضا";
	}

	@Override
	public String[] getAttribs() {
		Vector<String> vec = new Vector<String>();

		if (G.licenceTableSerialNoSw)
			vec.add(getAttribSerialNO());
		if (G.licenceTableLicenceNoSw)
			vec.add(getAttribLicenceNo());
		if (G.licenceTableProvinceSw)
			vec.add(getAttribProvince());
		if (G.licenceTableCitySw)
			vec.add(getAttribCity());
		if (G.licenceTableIssueDateSw)
			vec.add(getAttribIssueDate());
		if (G.licenceTableIssueNumberSw)
			vec.add(getAttribIssueNumber());
		if (G.licenceTableEducationLevelSw)
			vec.add(getAttribEducationLevel());
		if (G.licenceTableBackgroundSw)
			vec.add(getAttribBackGround());
		if (G.licenceTableModernColonyNoSw)
			vec.add(getAttribModernColonyNo());
		if (G.licenceTableTraditionalColonyNoSw)
			vec.add(getAttribTraditionalColonyNo());
		if (G.licenceTableExpireDateSw)
			vec.add(getAttribExpireDate());
		
		String[] res = new String[vec.size()];
		for(int i = 0; i < res.length; i++){
			res[i] = vec.elementAt(i);
		}
		return res;
	}

	@Override
	public Object[] getObjectArray() {
		Vector<Object> vec = new Vector<Object>();

		if (G.licenceTableSerialNoSw)
			vec.add(getSerialNO());
		if (G.licenceTableLicenceNoSw)
			vec.add(getLicenceNo());
		if (G.licenceTableProvinceSw)
			vec.add(getProvince());
		if (G.licenceTableCitySw)
			vec.add(getCity().getName());
		if (G.licenceTableIssueDateSw)
			vec.add(getIssueDate());
		if (G.licenceTableIssueNumberSw)
			vec.add(getIssueNumber());
		if (G.licenceTableEducationLevelSw)
			vec.add(getEducationLevel());
		if (G.licenceTableBackgroundSw)
			vec.add(getBackGround());
		if (G.licenceTableModernColonyNoSw)
			vec.add(getModernColonyNo());
		if (G.licenceTableTraditionalColonyNoSw)
			vec.add(getTraditionalColonyNo());
		if (G.licenceTableExpireDateSw)
			vec.add(getExpireDate());
		
		Object[] res = new Object[vec.size()];
		for(int i = 0; i < res.length; i++){
			res[i] = vec.elementAt(i);
		}
		return res;
	}
}
