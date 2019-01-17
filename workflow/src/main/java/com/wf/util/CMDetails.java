package com.wf.util;

import java.lang.reflect.Method;

public class CMDetails {
	public Class clazz;
	public Method method;

	public CMDetails() {
		super();
	}

	public CMDetails(Class clazz, Method method) {
		super();
		this.clazz = clazz;
		this.method = method;
	}

	public Class getClazz() {
		return clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

}
