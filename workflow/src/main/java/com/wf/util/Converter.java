package com.wf.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter {
	
	public static String createWorkFlowJson(Workflow workflow) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(workflow);
	}

	public static Workflow getWorkflow(String filepath) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(new File(filepath), Workflow.class);
	}

	/*private String createDummyWorkfowJson() {
		try {
			Connection c1 = new Connection("Component1", "Component2");
			Connection c2 = new Connection("Component2", "Component3");
			Connection c3 = new Connection("Component3", "Component4");
			Workflow workflow = new Workflow();
			workflow.setName("Myworkflow");
			workflow.setType("Lookup");
			List<Connection> connections = new ArrayList<Connection>();
			connections.add(c1);
			connections.add(c2);
			connections.add(c3);
			workflow.setConnections(connections);

			return createWorkFlowJson(workflow);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}*/
}
