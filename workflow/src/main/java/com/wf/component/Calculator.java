package com.wf.component;

import com.wf.util.ComponentName;

public class Calculator {
	@ComponentName(value = "Component_A")
	public Integer add() {

		System.out.println("This is Add");
return 7;
	}

	@ComponentName(value = "Component_B")
	public Integer multiply(Integer z) {
		System.out.println("This is Multiply : " );
		return (z*10);
	}
	
	@ComponentName(value = "Component_D")
	public Integer multiply() {
		System.out.println("This is Dummy Multiply : ");
		return 10*10;
	}
	
	public void Dummy() {

		System.out.println("This is Dummy");

	}
}
