package c45;

import java.io.*;
import java.util.ArrayList;

public class C45 {

    private int                _totalNumClasses;
    private double             _info;
    private Attribute[]        _attributes;
    private ArrayList<String>  _classes      = new ArrayList<String>();
    private ArrayList<Integer> _classesCount = new ArrayList<Integer>();

    public C45(File csvFile) throws FileNotFoundException {
        try{
            FileReader fileReader = new FileReader(csvFile);
            BufferedReader buffReader = new BufferedReader(fileReader);

            // read in first line of attribute names, and the last column is not an attribute name
            String line = buffReader.readLine();
            String[] attrNames = line.split(",");
            _attributes = new Attribute[attrNames.length - 1];
            int classIndex = attrNames.length - 1;
            for(int i = 0; i < _attributes.length; i++){
                _attributes[i] = new Attribute(attrNames[i]);
            }

            // read in rest of data, where last column is class
            while((line = buffReader.readLine()) != null){
                Value data;
                String lineData[] = line.split(",");

                // insert class into _classes List
                if(_classes.isEmpty()){
                    _classes.add(lineData[classIndex]);
                    _classesCount.add(_classes.indexOf(lineData[classIndex]), 1);
                }
                else{
                    if(!_classes.contains(lineData[classIndex])){
                        _classes.add(lineData[classIndex]);
                        _classesCount.add(_classes.indexOf(lineData[classIndex]), 1);
                    }
                    else {
                        _classesCount.set(_classes.indexOf(lineData[classIndex]),
                                _classesCount.get(_classes.indexOf(lineData[classIndex])) + 1);
                    }
                }

                // insert data into attributes
                for(int x = 0; x < _attributes.length; x++){
                    data = new Value(lineData[x], lineData[classIndex]);
                    _attributes[x].insertValue(data);
                }
            }

            buffReader.close();
            fileReader.close();

            // set information criteria
            calculateInformation();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public C45(Attribute[] attrs){
        _attributes = attrs;
        calculateInformation();
    }

    private void calculateInformation(){
        _info = 0.0;
        _totalNumClasses = 0;
        for(int i : _classesCount){
            _totalNumClasses += i;
        }
        for(double d : _classesCount){
            double temp = (-1 * (d/_totalNumClasses)) * (Math.log((d/_totalNumClasses)) / Math.log(2));
            _info += temp;
        }
    }

    public double getInfo(){
        return _info;
    }

    public double getGainForAttribute(String attrName){
        for(Attribute a : _attributes){
            if(a.getName() == attrName){
                return a.getGain(_info, _totalNumClasses);
            }
        }
        return 0;
    }

    public void printOut(){
        System.out.println("Info: " + _info);
        for(Attribute a : _attributes){
            System.out.println(a.toString());
        }
    }

}
