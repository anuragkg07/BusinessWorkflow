package com.wf.util;
import java.io.Serializable;

public class Connection implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6908547299812570346L;
	private String fromOperation;
	private String toOperation;

	public Connection() {
		super();
	}

	public Connection(String fromOperation, String toOperation) {
		super();
		this.fromOperation = fromOperation;
		this.toOperation = toOperation;
	}

	public String getFromOperation() {
		return fromOperation;
	}

	public void setFromOperation(String fromOperation) {
		this.fromOperation = fromOperation;
	}

	public String getToOperation() {
		return toOperation;
	}

	public void setToOperation(String toOperation) {
		this.toOperation = toOperation;
	}

	@Override
	public String toString() {
		return "Connection [fromOperation=" + fromOperation + ", toOperation=" + toOperation + "]";
	}

}
