package primitives;

public class Attribute implements TableObject{
		private String nameEnglish;
		private String namePersian;
		private String value;
		public Attribute(String nameEnglish, String namePersian,
				String value) {
			super();
			this.nameEnglish = nameEnglish;
			this.namePersian = namePersian;
			this.value = value;
		}
		public String getNameEnglish() {
			return nameEnglish;
		}
		public void setNameEnglish(String nameEnglish) {
			this.nameEnglish = nameEnglish;
		}
		public String getNamePersian() {
			return namePersian;
		}
		public void setNamePersian(String namePersian) {
			this.namePersian = namePersian;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		@Override
		public String[] getAttribs() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Object[] getObjectArray() {
			// TODO Auto-generated method stub
			return null;
		}
		
		
}
