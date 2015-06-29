package c45;

import java.util.ArrayList;

public class Attribute {
	public String _name   = new String();
	public double _gain   = 0;
	public ArrayList<ValuesCollection> _valuesCollectionList = new ArrayList<ValuesCollection>();

	public Attribute(String name){
		_name = name;
	}

	private void calculateGain(double info, int totalNumClasses){
		int totalValClasses = 0;
		for(ValuesCollection valWrapper : _valuesCollectionList){
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
		if(_valuesCollectionList.isEmpty()){
			_valuesCollectionList.add(new ValuesCollection(inValue._name, inValue._itClass));
		}
		else{
			for(ValuesCollection valWrapper : _valuesCollectionList){
				if(valWrapper.getName().equals(inValue._name)){
					valWrapper.update(inValue);
					return;
				}
			}
			_valuesCollectionList.add(new ValuesCollection(inValue._name, inValue._itClass));
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
		for(ValuesCollection valWrapper : _valuesCollectionList) {
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
