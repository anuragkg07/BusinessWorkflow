package com.wf.util;
import java.io.Serializable;
import java.util.List;

public class Workflow implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8438651730631437677L;
	String name;
	String type;
	List<Connection> connections;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Connection> getConnections() {
		return connections;
	}

	public void setConnections(List<Connection> connections) {
		this.connections = connections;
	}

	@Override
	public String toString() {
		return "Workflow [name=" + name + ", type=" + type + ", connections=" + connections + "]";
	}

	

}
