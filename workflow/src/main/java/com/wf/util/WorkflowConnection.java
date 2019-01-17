package com.wf.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class WorkflowConnection {
	private List<Class> classToScan = new ArrayList<Class>();
	private Map<String, CMDetails> aliasConfigMap = new HashMap<String, CMDetails>();
	private Map<String, Object> wfResult = new HashMap<String, Object>();
	private Object previousResult = null;
	private String filepath=null;
	public void addClassToScan(Class classz) {
		classToScan.add(classz);
	}

	public void init() throws Exception {
		for (Class clasz : classToScan) {
			for (Method method : clasz.getDeclaredMethods()) {
				if (method.getAnnotation(ComponentName.class) != null) {
					String key = method.getAnnotation(ComponentName.class).value();
					if (!key.isEmpty()) {
						CMDetails cmDetails = new CMDetails(clasz, method);
						aliasConfigMap.put(key, cmDetails);
					} else {
						throw new Exception("Invalid Component Name present in class : " + clasz.getName() + " :"
								+ method.getName());
					}
				}
				// System.out.println(method.getName());
				// System.out.println(method.getAnnotation(ComponentName.class));
			}
		}

	}

	public void run() throws JsonParseException, JsonMappingException, IOException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Workflow workflow = Converter.getWorkflow(this.filepath);
		List<Connection> connections = workflow.getConnections();
		this.exploreConnections(connections);
	}

	private void exploreConnections(List<Connection> connections)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		
		Connection lastConnection = null;
		int sequenceNo = 0;
		for (Connection connection : connections) {
			System.out.println(connection.getFromOperation());
			lastConnection = connection;
			CMDetails cmDetails = aliasConfigMap.get(connection.getFromOperation());
			if (cmDetails != null) {
				this.execute(cmDetails, sequenceNo);
				sequenceNo++;
			}
		}
		CMDetails cmDetails = aliasConfigMap.get(lastConnection.getToOperation());
		if (cmDetails != null)
			this.execute(cmDetails, sequenceNo);

	}

	private void execute(CMDetails cmDetails, int sequenceNo)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		System.out.println(sequenceNo+"_Executing method  " + cmDetails.getMethod().getName() + "  from class  "
				+ cmDetails.getClazz().getName());
		Class<?> retType = cmDetails.getMethod().getReturnType();
		Object out = null;
		if (cmDetails.getMethod().getParameterCount() == 0) {
			
			// System.out.println(retType.getName());
			if (retType.getName().equalsIgnoreCase("void")) {
				out = cmDetails.getMethod().invoke(cmDetails.getClazz().newInstance());
				wfResult.put(cmDetails.getMethod().getAnnotation(ComponentName.class).value() + "_" + sequenceNo,
						"void");
			} else {
				out = cmDetails.getMethod().invoke(cmDetails.getClazz().newInstance());
				wfResult.put(cmDetails.getMethod().getAnnotation(ComponentName.class).value() + "_" + sequenceNo, out);
			}
			previousResult = out;

		} else {
			out =cmDetails.getMethod().invoke(cmDetails.getClazz().newInstance(), previousResult);
			wfResult.put(cmDetails.getMethod().getAnnotation(ComponentName.class).value() + "_" + sequenceNo, out);
			
		}

	}


	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Map<String, Object> getResult() {
		return this.wfResult;
	}
}
