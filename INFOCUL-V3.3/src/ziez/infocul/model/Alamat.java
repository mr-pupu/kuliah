package ziez.infocul.model;

public class Alamat {
	private int id;
	private String jalan;
	private String kota;
	private String latitude;
	private String longitude;
	
	public Alamat(String jalan, String kota) {
		this.jalan = jalan;
		this.kota = kota;
	}
	
	public Alamat(int id, String jalan, String kota, String latitude,
			String longitude) {
		this.id = id;
		this.jalan = jalan;
		this.kota = kota;
		this.latitude = latitude;
		this.longitude = longitude;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJalan() {
		return jalan;
	}
	public void setJalan(String jalan) {
		this.jalan = jalan;
	}
	public String getKota() {
		return kota;
	}
	public void setKota(String kota) {
		this.kota = kota;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String toString(){
		return jalan+"- "+kota;
	}
	
	public String getLatlang(){
		return getLatitude()+","+getLongitude();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result
				+ ((longitude == null) ? 0 : longitude.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alamat other = (Alamat) obj;
		if (id != other.id)
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		return true;
	}
	
	
	
}
