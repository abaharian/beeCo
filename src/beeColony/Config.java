package beeColony;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	private static final String fileName = "bee.cnf"; 
	private Properties prop;
	
	
	public Config() {
		prop = new Properties();
	}
	
	public String getConfig(String key) throws IOException{
		String cnf;
		
		FileInputStream input;
		input = new FileInputStream(fileName);
		prop.load(input);
		cnf = prop.getProperty(key);
		return cnf;
	}
	
	public String getConfig(String key, String defaultValue){
		String cnf = defaultValue;
		
		FileInputStream input;
		
		try {
			input = new FileInputStream(fileName);
			prop.load(input);
			cnf = prop.getProperty(key);
			if(cnf == null)
				cnf = defaultValue;
		} catch (Throwable t){
			cnf = defaultValue;
		}
		
		return cnf;
		
	}
	
	public boolean getBooleanConfig(String key, boolean defaultValue){
		String cnf = getConfig(key, String.valueOf(defaultValue));
		boolean ret;
		try{
			if(cnf == null)
				ret = defaultValue;
			else
				ret = Boolean.valueOf(cnf);
		}catch(Exception ex){
			ret = defaultValue;
		}
		return ret;
	}
	
	public int getIntConfig(String key, int defaultValue){
		String cnf = getConfig(key, String.valueOf(defaultValue));
		int ret;
		try{
			if(cnf == null)
				ret = defaultValue;
			else
				ret = Integer.valueOf(cnf);
		}catch(Exception ex){
			ret = defaultValue;
		}
		return ret;
	}
	
	
	public void storeConfig(String key, String value){
		try{
			FileInputStream in = new FileInputStream(fileName);
			prop.load(in);
			in.close();
		} catch(Throwable t) {}

		try{
			FileOutputStream out = new FileOutputStream(fileName);
			prop.setProperty(key, value);
			prop.store(out, null);
			out.close();
		} catch (Throwable t){

		}
	}
}
