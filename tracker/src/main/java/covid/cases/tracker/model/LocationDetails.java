package covid.cases.tracker.model;

public class LocationDetails
{
	private String state;
	private String country;
	private int totalCase;
	private int diffFromPrevDay;

    public int getDiffFromPrevDay() {
        return diffFromPrevDay;
    }

    public void setDiffFromPrevDay(int diffFromPrevDay) {
        this.diffFromPrevDay = diffFromPrevDay;
    }

	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getTotalCase() {
		return totalCase;
	}
	public void setTotalCase(int totalCase) {
		this.totalCase = totalCase;
	}
	@Override
	public String toString() {
		return "LocationDetails [state=" + state + ", country=" + country + ", totalCase=" + totalCase + ", getState()="
				+ getState() + ", getCountry()=" + getCountry() + ", getTotalCase()=" + getTotalCase() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	

}
