package mountainhuts;

public class Range implements Comparable<Range> {
	private Integer min,max;

	public Range(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public void setMax(int max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return min+"-"+max;
	}

	@Override
	public int compareTo(Range arg0) {
		int c=this.min-arg0.min;
		if(c==0)
			return this.max-arg0.max;
		return c;
	}
	
	public boolean altitudeIn(int altitude) {
		if(min<=altitude && altitude<=max)
			return true;
		return false;
	}
	
}
