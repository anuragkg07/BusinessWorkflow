import com.wf.util.Factory;
import com.wf.util.WorkflowConnection;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException {

		try {
			// this is for sharing output to next component
			// String filePath="resources\\input.json";
			String filePath = "resources\\input2.json";

			WorkflowConnection wfc = Factory.getWorkflowConnection(filePath);
			wfc.run();
			System.out.println(wfc.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
