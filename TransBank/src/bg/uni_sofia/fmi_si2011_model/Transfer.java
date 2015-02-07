package bg.uni_sofia.fmi_si2011_model;

import java.io.Serializable;

public class Transfer implements Serializable {
	private static final long serialVersionUID = 1L;
	private String mReceiverIBAN;
	private String mOrdererIBAN;
	private String mDescription;
	private Double sum;
	public Transfer() {
	}
	public Transfer(String mReceiverIBAN, String mOrdererIBAN, String mDescription, Double sum) {
		super();
		this.mReceiverIBAN = mReceiverIBAN;
		this.mOrdererIBAN = mOrdererIBAN;
		this.mDescription = mDescription;
		this.sum = sum;
	}
	public String getmReceiverIBAN() {
		return mReceiverIBAN;
	}
	public void setmReceiverIBAN(String mReceiverIBAN) {
		this.mReceiverIBAN = mReceiverIBAN;
	}
	public String getmOrdererIBAN() {
		return mOrdererIBAN;
	}
	public void setmOrdererIBAN(String mOrdererIBAN) {
		this.mOrdererIBAN = mOrdererIBAN;
	}
	public String getmDescription() {
		return mDescription;
	}
	public void setmDescription(String mDescription) {
		this.mDescription = mDescription;
	}
	public Double getSum() {
		return sum;
	}
	public void setSum(Double sum) {
		this.sum = sum;
	}
}
