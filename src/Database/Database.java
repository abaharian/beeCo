package Database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import primitives.City;
import primitives.DuplicateKeyException;
import primitives.Licence;
import primitives.MyFile;
import primitives.Person;
import primitives.Province;
import primitives.TableObject;

public class Database {
	
	public static final String PERSON_TABLE_NAME = "indivisual";
	public static final String PERSON_COLUMN_NATIONAL_ID = "nationalID";
	public static final String PERSON_COLUMN_firstName = "firstName";
	public static final String PERSON_COLUMN_lastName = "lastName";
	public static final String PERSON_COLUMN_fatherName = "fatherName";
	public static final String PERSON_COLUMN_certificateNo = "certificateNo";
	public static final String PERSON_COLUMN_birthDate = "birthDate";
	public static final String PERSON_COLUMN_insuranceNo = "insuranceNo";
	public static final String PERSON_COLUMN_address = "address";
	public static final String PERSON_COLUMN_postalCode = "postalCode";
	public static final String PERSON_COLUMN_phoneNo = "phoneNo";
	public static final String PERSON_COLUMN_mobileNo = "mobileNo";
	public static final String PERSON_COLUMN_cooperationMemberId = "cooperationMemberId";
	
	public static final String LICENCE_TABLE_NAME = "licence";
	public static final String LICENCE_COLUMN_SERIAL_NO = "serialNo"; 
	public static final String LICENCE_COLUMN_LICENCE_NO = "licenceNo";
	public static final String LICENCE_COLUMN_CITY_ID = "cityId";
	public static final String LICENCE_COLUMN_ISSUE_DATE = "issueDate";
	public static final String LICENCE_COLUMN_ISSUE_NUMBER = "issueNumber";
	public static final String LICENCE_COLUMN_EDUCATION_LEVEL = "educationLevel";
	public static final String LICENCE_COLUMN_BACKGROUND = "background";
	public static final String LICENCE_COLUMN_MODERN_COLONY_NO = "modernColonyNo";
	public static final String LICENCE_COLUMN_TRADITIONAL_COLONY_NO = "traditionalColonyNumber";
	public static final String LICENCE_COLUMN_EXPIRE_DATE = "expireDate";
	public static final String LICENCE_COLUMN_NATIONAL_ID = "nationalId";
	
	public static final String PROVINCE_TABLE_NAME = "province";
	public static final String PROVINCE_COLUMN_ID = "id";
	public static final String PROVINCE_COLUMN_NAME = "name";
	
	public static final String CITY_TABLE_NAME = "city";
	public static final String CITY_COLUMN_CITY_ID = "cityId";
	public static final String CITY_COLUMN_PROVINCE_ID = "provinceId";
	public static final String CITY_COLUMN_CITY_NAME = "name";
	
	public static final String FILE_TABLE_NAME = "file";
	public static final String FILE_COLUMN_ID = "id";
	public static final String FILE_COLUMN_LICENCE_ID = "licenceId";
	public static final String FILE_COLUMN_TITLE = "title";
	public static final String FILE_COLUMN_LETTER_NO = "letterNo";
	public static final String FILE_COLUMN_PATH = "path";
	
	private Connection connection;
	private Statement statement;
	
	private String serverName = "localhost";
	private int portNumber = 5432;
	private String dbUsername = "postgres";
	private String dbPassword = "dreadlord";
	private String dbName = "beeDB";

	
	
	
	public void openConnection() throws Exception{
		try {
			Class.forName("org.postgresql.Driver");
			String ConnectionString = "jdbc:postgresql://" + serverName + ":"
					+ String.valueOf(portNumber) + "/" + dbName;
			connection = DriverManager.getConnection(ConnectionString, dbUsername, dbPassword);
			if (connection == null)
				throw new Exception("can not create connection, check your database parameters.");
			statement = connection.createStatement();
		} catch (ClassNotFoundException ex) {
			Exception e = new Exception("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
			throw e;
		} catch (SQLException ex) {
			String err = "Error in open connection. check your database parameters.\n";
			err += ex.getMessage();
			Exception e = new Exception(err);
			throw e;
		}
	}
	
	public void closeConnection(){
		try {
			if(statement != null)
				statement.close();
		} catch (Throwable e) {
		}
		try {
			if(connection!= null)
				connection.close();
		} catch (Throwable e) {
		}
	}
	
	public Vector<TableObject> getAllPersons(){
		Vector<TableObject> vec = new Vector<TableObject>();
		String query = "select * from " + PERSON_TABLE_NAME;
		query += " order by \"" + PERSON_COLUMN_lastName + "\", \"" + PERSON_COLUMN_firstName + "\"";
		try {
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()){
				Person temp = new Person();
				temp.setAddress(rs.getString(PERSON_COLUMN_address));
				temp.setBirthDate(rs.getString(PERSON_COLUMN_birthDate));
				temp.setCertificateNo(rs.getInt(PERSON_COLUMN_certificateNo));
				temp.setCooperationMemberId(rs.getInt(PERSON_COLUMN_cooperationMemberId));
				temp.setFatherName(rs.getString(PERSON_COLUMN_fatherName));
				temp.setFirstName(rs.getString(PERSON_COLUMN_firstName));
				temp.setInsuranceNo(rs.getInt(PERSON_COLUMN_insuranceNo));
				temp.setLastName(rs.getString(PERSON_COLUMN_lastName));
				temp.setMobileNo(rs.getString(PERSON_COLUMN_mobileNo));
				temp.setNationalID(rs.getString(PERSON_COLUMN_NATIONAL_ID));
				temp.setPhoneNo(rs.getString(PERSON_COLUMN_phoneNo));
				temp.setPostalCode(rs.getString(PERSON_COLUMN_postalCode));
				
				vec.addElement(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return vec;
	}
	
	public Vector<TableObject> getAllPersonsBySearch(String nationalId, String firstName,
	                                            String lastName, int certNo, String phoneNo,
	                                            String mobileNo){
		Vector<TableObject> vec = new Vector<TableObject>();
		String query = "select * from " + PERSON_TABLE_NAME 
				 + " Where ";
		if(nationalId != null && nationalId.length() > 0)
			query += " \"" + PERSON_COLUMN_NATIONAL_ID + "\" like '%" + nationalId +"%' and ";
		
		if(firstName != null && firstName.length() > 0)
			query += " \"" + PERSON_COLUMN_firstName + "\" like '%" + firstName +"%' and ";
		if(lastName != null && lastName.length() > 0)
			query += " \"" + PERSON_COLUMN_lastName + "\" like '%" + lastName +"%' and ";
		if(phoneNo != null && phoneNo.length() > 0)
			query += " \"" + PERSON_COLUMN_phoneNo + "\" like '%" + phoneNo +"%' and ";
		if(mobileNo != null && mobileNo.length() > 0)
			query += " \"" + PERSON_COLUMN_mobileNo + "\" like '%" + mobileNo +"%' and ";
		if(certNo > 0)
			query += " \"" + PERSON_COLUMN_certificateNo + "\" = " + certNo +" and ";
		query += " 1 = 1";
		query += " order by \"" + PERSON_COLUMN_lastName + "\", \"" + PERSON_COLUMN_firstName + "\"";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()){
				Person temp = new Person();
				temp.setAddress(rs.getString(PERSON_COLUMN_address));
				temp.setBirthDate(rs.getString(PERSON_COLUMN_birthDate));
				temp.setCertificateNo(rs.getInt(PERSON_COLUMN_certificateNo));
				temp.setCooperationMemberId(rs.getInt(PERSON_COLUMN_cooperationMemberId));
				temp.setFatherName(rs.getString(PERSON_COLUMN_fatherName));
				temp.setFirstName(rs.getString(PERSON_COLUMN_firstName));
				temp.setInsuranceNo(rs.getInt(PERSON_COLUMN_insuranceNo));
				temp.setLastName(rs.getString(PERSON_COLUMN_lastName));
				temp.setMobileNo(rs.getString(PERSON_COLUMN_mobileNo));
				temp.setNationalID(rs.getString(PERSON_COLUMN_NATIONAL_ID));
				temp.setPhoneNo(rs.getString(PERSON_COLUMN_phoneNo));
				temp.setPostalCode(rs.getString(PERSON_COLUMN_postalCode));
				
				vec.addElement(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return vec;
	}
	
	public Person getPersonsByNationalId(String nationalId){
		Person res = null;
		String query = "select * from " + PERSON_TABLE_NAME 
				+ " where \"" + PERSON_COLUMN_NATIONAL_ID + "\" = '" + nationalId + "'";
		try {
			ResultSet rs = statement.executeQuery(query);
			if(rs.next()){
				res = new Person();
				res.setAddress(rs.getString(PERSON_COLUMN_address));
				res.setBirthDate(rs.getString(PERSON_COLUMN_birthDate));
				res.setCertificateNo(rs.getInt(PERSON_COLUMN_certificateNo));
				res.setCooperationMemberId(rs.getInt(PERSON_COLUMN_cooperationMemberId));
				res.setFatherName(rs.getString(PERSON_COLUMN_fatherName));
				res.setFirstName(rs.getString(PERSON_COLUMN_firstName));
				res.setInsuranceNo(rs.getInt(PERSON_COLUMN_insuranceNo));
				res.setLastName(rs.getString(PERSON_COLUMN_lastName));
				res.setMobileNo(rs.getString(PERSON_COLUMN_mobileNo));
				res.setNationalID(rs.getString(PERSON_COLUMN_NATIONAL_ID));
				res.setPhoneNo(rs.getString(PERSON_COLUMN_phoneNo));
				res.setPostalCode(rs.getString(PERSON_COLUMN_postalCode));
			}
		} catch (SQLException e) {
		}
				
		return res;
	}

	public Vector<TableObject> getAllLicences(String nationalId){
		Vector<TableObject> vec = new Vector<TableObject>();
		String query = "select L.*, "
				+ " C.\""	+ CITY_COLUMN_CITY_NAME + "\" as cityname, "
				+ " P.\""  + PROVINCE_COLUMN_ID + "\" as provinceId, "
				+ " P.\""	+ PROVINCE_COLUMN_NAME 	+ "\" as provincename "
				+ " from " + LICENCE_TABLE_NAME + " L"
				+ " join " + CITY_TABLE_NAME + " C "
				+ " on L.\"" + LICENCE_COLUMN_CITY_ID + "\" = C.\"" + CITY_COLUMN_CITY_ID + "\""
				+ " join " + PROVINCE_TABLE_NAME + " P "
				+ " on C.\"" + CITY_COLUMN_PROVINCE_ID +  "\" = P.\"" + PROVINCE_COLUMN_ID + "\""
				+ " where \"" + LICENCE_COLUMN_NATIONAL_ID + "\" = '" + nationalId + "'"
				+ " order by \"" + LICENCE_COLUMN_ISSUE_DATE + "\"";

		
		try {
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()){
				Licence temp = new Licence();
				
				temp.setBackGround(rs.getInt(LICENCE_COLUMN_BACKGROUND));
				temp.setExpireDate(rs.getString(LICENCE_COLUMN_EXPIRE_DATE));
				temp.setIssueDate(rs.getString(LICENCE_COLUMN_ISSUE_DATE));
				temp.setIssueNumber(rs.getString(LICENCE_COLUMN_ISSUE_NUMBER));
				temp.setLicenceNo(rs.getInt(LICENCE_COLUMN_LICENCE_NO));
				temp.setModernColonyNo(rs.getInt(LICENCE_COLUMN_MODERN_COLONY_NO));
				temp.setSerialNO(rs.getInt(LICENCE_COLUMN_SERIAL_NO));
				temp.setTraditionalColonyNo(rs.getInt(LICENCE_COLUMN_TRADITIONAL_COLONY_NO));
				temp.setEducationLevel(rs.getString(LICENCE_COLUMN_EDUCATION_LEVEL));
				
				int cityId = rs.getInt(LICENCE_COLUMN_CITY_ID);
				int provinceId = rs.getInt("provinceId");
				String cityName = rs.getString("cityname");
				String provinceName = rs.getString("provincename");
				temp.setProvince(new Province(provinceId, provinceName));
				
				temp.setCity(new City(provinceId, cityId, cityName));
				vec.addElement(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return vec;
	}
	
	public void insertNewPerson(Person p){
		String query = "insert into " + PERSON_TABLE_NAME + "( "
				+ "\"" + PERSON_COLUMN_address + "\", "
				+ "\"" + PERSON_COLUMN_birthDate + "\", "
				+ "\"" + PERSON_COLUMN_certificateNo + "\", "
				+ "\"" + PERSON_COLUMN_cooperationMemberId + "\", "
				+ "\"" + PERSON_COLUMN_fatherName + "\", "
				+ "\"" + PERSON_COLUMN_firstName + "\", "
				+ "\"" + PERSON_COLUMN_insuranceNo + "\", "
				+ "\"" + PERSON_COLUMN_lastName + "\", "
				+ "\"" + PERSON_COLUMN_mobileNo + "\", "
				+ "\"" + PERSON_COLUMN_NATIONAL_ID + "\", "
				+ "\"" + PERSON_COLUMN_phoneNo + "\", "
				+ "\"" + PERSON_COLUMN_postalCode + "\" "
				+ ") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, p.getAddress());
			ps.setString(2, p.getBirthDate());
			ps.setInt(3, p.getCertificateNo());
			ps.setInt(4, p.getCooperationMemberId());
			ps.setString(5, p.getFatherName());
			ps.setString(6, p.getFirstName());
			ps.setLong(7, p.getInsuranceNo());
			ps.setString(8, p.getLastName());
			ps.setString(9, p.getMobileNo());
			ps.setString(10, p.getNationalID());
			ps.setString(11, p.getPhoneNo());
			ps.setString(12, p.getPostalCode());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Vector<Province> getAllProvince(){
		Vector<Province> vec = new Vector<Province>();
		String query = "select * from " + PROVINCE_TABLE_NAME;
		try {
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()){
				Province p = new Province();
				p.setId(rs.getInt(PROVINCE_COLUMN_ID));
				p.setName(rs.getString(PROVINCE_COLUMN_NAME));
				vec.addElement(p);
			}
		} catch (SQLException e) {
		}
		return vec;
	}
	
	public Vector<City> getAllCity(Province p){
		Vector<City> vec = new Vector<City>();
		String query = "select * from " + CITY_TABLE_NAME 
				+ " where \"" + CITY_COLUMN_PROVINCE_ID + "\" = " + p.getId();
		try {
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()){
				City c = new City();
				c.setCityId(rs.getInt(CITY_COLUMN_CITY_ID));
				c.setProvinceId(rs.getInt(CITY_COLUMN_PROVINCE_ID));
				c.setName(rs.getString(CITY_COLUMN_CITY_NAME));
				vec.addElement(c);
			}
		} catch (SQLException e) {
		}
		return vec;
	}

	public void InsertNewLicence(Person p, Licence l) throws DuplicateKeyException{
		String query = "insert into " + LICENCE_TABLE_NAME + "( "
				+ "\"" + LICENCE_COLUMN_BACKGROUND + "\", "
				+ "\"" + LICENCE_COLUMN_CITY_ID + "\", "
				+ "\"" + LICENCE_COLUMN_EDUCATION_LEVEL + "\", "
				+ "\"" + LICENCE_COLUMN_EXPIRE_DATE + "\", "
				+ "\"" + LICENCE_COLUMN_ISSUE_DATE + "\", "
				+ "\"" + LICENCE_COLUMN_ISSUE_NUMBER + "\", "
				+ "\"" + LICENCE_COLUMN_LICENCE_NO + "\", "
				+ "\"" + LICENCE_COLUMN_MODERN_COLONY_NO + "\", "
				+ "\"" + LICENCE_COLUMN_NATIONAL_ID + "\", "
				+ "\"" + LICENCE_COLUMN_SERIAL_NO + "\", "
				+ "\"" + LICENCE_COLUMN_TRADITIONAL_COLONY_NO + "\" "
				+ ") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, l.getBackGround());
			ps.setInt(2, l.getCity().getCityId());
			ps.setString(3, l.getEducationLevel());
			ps.setString(4, l.getExpireDate());
			ps.setString(5, l.getIssueDate());
			ps.setString(6, l.getIssueNumber());
			ps.setInt(7, l.getLicenceNo());
			ps.setInt(8, l.getModernColonyNo());
			ps.setString(9, p.getNationalID());
			ps.setInt(10, l.getSerialNO());
			ps.setInt(11, l.getTraditionalColonyNo());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			if(e.getMessage().contains("violates unique constraint \"PK_LICENCE\"")){
				throw new DuplicateKeyException("شماره سریال وارد شده قبلاً استفاده شده است");
			}
		} 
		
	}
	
	public void addNewFile(int licenceSerialNo, MyFile f){
		int id = getMaxFileId() + 1;
		String query = "insert into " + FILE_TABLE_NAME + "("
				+ " \"" + FILE_COLUMN_ID + "\", "
				+ " \"" + FILE_COLUMN_LETTER_NO + "\", "
				+ " \"" + FILE_COLUMN_LICENCE_ID + "\", "
				+ " \"" + FILE_COLUMN_PATH + "\", "
				+ " \"" + FILE_COLUMN_TITLE + "\") "
				+ " values (?, ?, ?, ?, ?)" ;
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, f.getLetterNo());
			ps.setInt(3, licenceSerialNo);
			ps.setString(4, f.getAbsolutePath());
			ps.setString(5, f.getTitle());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getMaxFileId(){
		String query = "select max(\"" + FILE_COLUMN_ID + "\") "
				+ " from " + FILE_TABLE_NAME;
		int id = 0;
		try {
			ResultSet rs = statement.executeQuery(query);
			if(rs.next()){
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public Vector<MyFile> getAllfiles(int licenceSerialNo){
		Vector<MyFile> vec = new Vector<MyFile>();
		String query = "select * from " + FILE_TABLE_NAME
				+ " where \"" + FILE_COLUMN_LICENCE_ID + "\" = ? ";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, licenceSerialNo);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				File file;
				MyFile myFile = new MyFile();
				String path = rs.getString(FILE_COLUMN_PATH);
				file = new File(path);
				myFile.setFile(file);
				myFile.setLetterNo(rs.getString(FILE_COLUMN_LETTER_NO));
				myFile.setTitle(rs.getString(FILE_COLUMN_TITLE));
				
				vec.add(myFile);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vec;
	}
	
	public void removeFile(MyFile f){
		String query = "delete from " + FILE_TABLE_NAME + " where "
				+ " \"" + FILE_COLUMN_ID + "\" = ? ";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, f.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
		}
	}
}
