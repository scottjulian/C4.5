package c45;

import java.util.ArrayList;

public class ValuesCollection {
	private String _name;
	private double _gain = 0;
	private ArrayList<String>  _classes      = new ArrayList<String>();
	private ArrayList<Integer> _classesCount = new ArrayList<Integer>();

	public ValuesCollection(String valName, String newClass){
		_name = valName;
		_classes.add(newClass);
		_classesCount.add(1);
	}

	public double getGain(){
        if(_gain == 0) {
            double temp;
            int totalNumClasses = 0;
            for (int i : _classesCount) {
                totalNumClasses += i;
            }
            for (double d : _classesCount) {
                temp = (-1 * (d / totalNumClasses)) * (Math.log((d / totalNumClasses)) / Math.log(2));
                _gain += temp;
            }
        }
		return _gain;
	}

	public void update(Value inValue) {
		_gain = 0;
		if(_classes.contains(inValue._itClass)){
			_classesCount.set(_classes.indexOf(inValue._itClass),
					_classesCount.get(_classes.indexOf(inValue._itClass)) + 1);
		}
		else{
			_classes.add(inValue._itClass);
			_classesCount.add(_classes.indexOf(inValue._itClass), 1);
		}
	}

	public String getName() {
		return _name;
	}

    public ArrayList<Integer> getClassesCount(){
        return _classesCount;
    }

    public ArrayList<String> getClasses() {
        return _classes;
    }
}
