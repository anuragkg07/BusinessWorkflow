package com.wf.component;

import com.wf.util.ComponentName;

public class Printer {

	@ComponentName(value = "Component_C")
	public void printMyName() {
		System.out.println("Anurag");
	}
	
	@ComponentName(value = "Component_D")
	public void printMyAddress() {
		System.out.println("Bangalore");
	}
}
