package myc45;

import java.util.ArrayList;
import java.util.List;

public class Values {
	public String        valueName    = new String();
	public List<String>  classes      = new ArrayList<String>();
	public List<Integer> classesCount = new ArrayList<Integer>();
	public int           totalClassCount = 0;
	public double        gain         = 0.0;
	
	public Values(String valName, String newClass){
		this.valueName = valName;
		this.classes.add(newClass);
		this.classesCount.add(1);
	}
	
	public void setGain(){
		double temp = 0.0;
		
		int totalNumClasses = 0;
		for(int i : this.classesCount)
			totalNumClasses += i;
		
		for(double d : classesCount){
			temp = (-1 * (d/totalNumClasses)) * (Math.log((d/totalNumClasses)) / Math.log(2));
			this.gain += temp; temp = 0.0;
		}
	}


	public void update(Val inVal) {
		if(this.classes.contains(inVal.itClass)){
			this.classesCount.set(this.classes.indexOf(inVal.itClass),
								  this.classesCount.get(this.classes.indexOf(inVal.itClass)) + 1);
		}
		else{
			this.classes.add(inVal.itClass);
			this.classesCount.add(this.classes.indexOf(inVal.itClass), 1);
		}
		
	}


}
