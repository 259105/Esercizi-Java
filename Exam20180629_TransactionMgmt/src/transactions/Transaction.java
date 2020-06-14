package transactions;

public class Transaction {
	private String transacionId;
	private Carrier carrier;
	private Request request;
	private Offer offer;
	private int score=0;
	public Transaction(String transacionId, Carrier carrier, Request request, Offer offer) {
		super();
		this.transacionId = transacionId;
		this.carrier = carrier;
		this.request = request;
		this.offer = offer;
	}
	/**
	 * @return the transacionId
	 */
	public String getTransacionId() {
		return transacionId;
	}
	/**
	 * @return the carriername
	 */
	public Carrier getCarrier() {
		return carrier;
	}
	/**
	 * @return the request
	 */
	public Request getRequest() {
		return request;
	}
	/**
	 * @return the offer
	 */
	public Offer getOffer() {
		return offer;
	}
	/**
	 * @param transacionId the transacionId to set
	 */
	public void setTransacionId(String transacionId) {
		this.transacionId = transacionId;
	}
	/**
	 * @param carriername the carriername to set
	 */
	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}
	/**
	 * @param request the request to set
	 */
	public void setRequest(Request request) {
		this.request = request;
	}
	/**
	 * @param offer the offer to set
	 */
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	public void setScore(int score) {
		this.score=score;
	}
	public int getScore() {
		return score;
	}
	
}
