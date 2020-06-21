package trail;

public class Passage {
	private Delegate delegate;
	private Location location;
	private Runner runner;
	private long time;
	
	public Passage(Delegate delegate, Location location, Runner runner,long time) {
		super();
		this.delegate = delegate;
		this.location = location;
		this.runner = runner;
		this.time=time;
	}

	/**
	 * @return the delegate
	 */
	public Delegate getDelegate() {
		return delegate;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @return the runner
	 */
	public Runner getRunner() {
		return runner;
	}
	
	public long getTime() {
		return time;
	}
	
}
