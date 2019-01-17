package com.wf.util;

import com.wf.component.Calculator;
import com.wf.component.Printer;

public class Factory {

	public static WorkflowConnection getWorkflowConnection(String filePath) throws Exception {
		WorkflowConnection wfc=new WorkflowConnection();
		wfc.addClassToScan(Calculator.class);
		wfc.addClassToScan(Printer.class);
		wfc.setFilepath(filePath);
		wfc.init();
		return wfc;
		
	}
}
