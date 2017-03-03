package primitives;

public class City {
	private int provinceId;
	private int cityId;
	private String name;

	public City() {
	}

	public City(int provinceId, int cityId, String name) {
		super();
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.name = name;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
