package c45;

import java.util.ArrayList;

public class Attribute {
	public String _name   = new String();
	public double _gain   = 0;
	public ArrayList<ValuesWrapper> _valuesWrapperList = new ArrayList<ValuesWrapper>();

	public Attribute(String name){
		_name = name;
	}

	private void calculateGain(double info, int totalNumClasses){
		int totalValClasses = 0;
		for(ValuesWrapper valWrapper : _valuesWrapperList){
			for(int i : valWrapper.getClassesCount()){
				totalValClasses += i;
			}
			_gain += (totalValClasses/(double)totalNumClasses) * valWrapper.getGain();
			totalValClasses = 0;
		}
		_gain = info - _gain;
	}

	public void insertValue(Value inValue){
        _gain = 0;
		if(_valuesWrapperList.isEmpty()){
			_valuesWrapperList.add(new ValuesWrapper(inValue._name, inValue._itClass));
		}
		else{
			for(ValuesWrapper valWrapper : _valuesWrapperList){
				if(valWrapper.getName().equals(inValue._name)){
					valWrapper.update(inValue);
					return;
				}
			}
			_valuesWrapperList.add(new ValuesWrapper(inValue._name, inValue._itClass));
		}
	}

	public String getName(){
		return _name;
	}

    public double getGain(double info, int totalNumClasses){
        if(_gain == 0){
            calculateGain(info, totalNumClasses);
        }
        return _gain;
    }

	public String toString(){
		String out = new String("attribute: " + _name + "\n");
		for(ValuesWrapper valWrapper : _valuesWrapperList) {
			out += "\tvalue: " + valWrapper.getName() + ", ";
			out += "\n\t\tclasses: ";
			for (String c : valWrapper.getClasses()) {
				out += c + ", ";
			}
			out += "\n\t\tcounts: ";
			for (Integer i : valWrapper.getClassesCount()) {
				out += i + ", ";
			}
			out += "\n";
		}
		return out;
	}

}
