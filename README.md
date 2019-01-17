# Business Workflow

This app define workflow for business and execute the defined workflow as a single process.

  - To define workflow this app use json file
  - This json file contains list of component
  - App execute the component in sequence as defined in json file
  - One component output can be passed to another component  as input (A-->B) and make sure that if B is expecting some input then A should return some output else it will break the execution of workflow. Hence the workflow should be properly defined in json file.

Sample  json 

```sh
{
	"name": "Myworkflow",
	"type": "Lookup",
	"connections": [
		{
			"fromOperation": "Component_A",
			"toOperation": "Component_B"
		},
		{
			"fromOperation": "Component_B",
			"toOperation": "Component_C"
		},
		{
			"fromOperation": "Component_C",
			"toOperation": "Component_D"
		}]
}

```
### Defining and Mapping Component 

  - Component is nothing but a normal java method define under a class.
  - Here we have one restriction that method to have only one argument. 
  - Annotate method which you want to define as component as below.
  - Mapping with json is done by defining value under @ComponentName  .
```sh
    @ComponentName(value = "Component_B")
    	public Integer multiply(Integer z) {
    		System.out.println("This is Multiply : ");
    		return (z * 10);
    	}
```
### Registering 

  - You can register the component by registring the class in which component method is define
  - You also need to register the workflow json file to application
```sh
        String filePath="workflow.json"
        WorkflowConnection wfc=new WorkflowConnection();
		wfc.addClassToScan(Calculator.class);
		wfc.addClassToScan(Printer.class);
		wfc.setFilepath(filePath);
		wfc.init();
```

### Execution 
  - Execute workflow by calling run method
```sh
    wfc.run();
```
### Output of workflow
  - Output of workflow is of type Map 
  - This contains output of all component executed as per workflow in order of execution
```sh
    wfc.getResult();
```

### High-level design
![High-level design](/hld.PNG)
