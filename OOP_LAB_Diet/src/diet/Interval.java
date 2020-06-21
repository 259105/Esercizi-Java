package diet;

public class Interval {
	
	private Orario open,close;

	public Interval(String open,String close) {
		this.open=new Orario(open);
		this.close=new Orario(close);
	}
	
		
	public Orario getOpen() {
		return open;
	}
	public Orario getClose() {
		return close;
	}

	public String toStringOpen() {
		return open.toString();
	}
	public String toStringClose() {
		return close.toString();
	}

}
