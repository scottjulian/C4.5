package c45;

import java.util.ArrayList;
import java.util.List;

public class ValuesWrapper {
	public String        _name;
	public double        _gain;
	public List<String>  _classes      = new ArrayList<String>();
	public List<Integer> _classesCount = new ArrayList<Integer>();

	public ValuesWrapper(String valName, String newClass){
		_name = valName;
		_classes.add(newClass);
		_classesCount.add(1);
	}

	public double calculateGain(){
		double temp;
		int totalNumClasses = 0;
		for(int i : _classesCount) {
			totalNumClasses += i;
		}
		for(double d : _classesCount){
			temp = (-1 * (d/totalNumClasses)) * (Math.log((d/totalNumClasses)) / Math.log(2));
			_gain += temp;
		}
		return _gain;
	}

	public void update(Value inValue) {
		if(_classes.contains(inValue._itClass)){
			_classesCount.set(_classes.indexOf(inValue._itClass),
					_classesCount.get(_classes.indexOf(inValue._itClass)) + 1);
		}
		else{
			_classes.add(inValue._itClass);
			_classesCount.add(_classes.indexOf(inValue._itClass), 1);
		}
	}

}
