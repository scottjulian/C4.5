package c45;

import java.util.ArrayList;
import java.util.List;

public class Attribute {
	public String       _name   = new String();
	public double       _gain   = 0.0;
	public List<ValuesWrapper> _valuesWrapperList = new ArrayList<ValuesWrapper>();

	public Attribute(String name){
		_name = name;
	}

	public void calculateGain(double IofD, int totalNumClasses){
		int totalValClasses = 0;
		for(ValuesWrapper valWrapper : _valuesWrapperList){
			valWrapper.calculateGain();
			for(int i : valWrapper._classesCount){
				totalValClasses += i;
			}
			_gain += (totalValClasses/(double)totalNumClasses) * valWrapper._gain;
			totalValClasses = 0;
		}
		_gain = IofD - _gain;
	}

	public void insertValue(Value inValue){
		if(_valuesWrapperList.isEmpty()){
			_valuesWrapperList.add(new ValuesWrapper(inValue._name, inValue._itClass));
		}
		else{
			for(ValuesWrapper valWrapper : _valuesWrapperList){
				if(valWrapper._name.equals(inValue._name)){
					valWrapper.update(inValue);
					return;
				}
			}
			_valuesWrapperList.add(new ValuesWrapper(inValue._name, inValue._itClass));
		}
	}

	public void insertValue(String name, String itClass){
		insertValue(new Value(name, itClass));
	}

	public String toString(){
		String out = new String("attribute: " + _name + "\n");
		for(ValuesWrapper valWrapper : _valuesWrapperList) {
			out += "\tvalue: " + valWrapper._name + ", ";
			out += "\n\t\tclasses: ";
			for (String c : valWrapper._classes) {
				out += c + ", ";
			}
			out += "\n\t\tcounts: ";
			for (Integer i : valWrapper._classesCount) {
				out += i + ", ";
			}
			out += "\n";
		}
		return out;
	}

}
