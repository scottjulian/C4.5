import c45.Attribute;
import c45.Value;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.Math;

public class Main {

	public static void main(String[] args) throws IOException {
		// .csv data sets
		String files[] = {"data_sets/tic_tac_toe.csv"};
		Scanner scan;

		// start loop for all files HERE
		scan = new Scanner(new File(files[0]));
		String headerLine = scan.nextLine();
		String headers[]  = headerLine.split(",");

		// class index is assumed to be the last column
		int classIndex    = headers.length - 1;
		int numAttributes = headers.length - 1;

		// store data set attributes
		Attribute attributes[] = new Attribute[numAttributes];
		for(int x = 0; x < numAttributes; x++) {
			attributes[x] = new Attribute(headers[x]);
		}

		// for storing classes and class count
		List<String>  classes      = new ArrayList<String>();
		List<Integer> classesCount = new ArrayList<Integer>();

		// store are values into respected attributes
		// along with respected classes
		while(scan.hasNextLine()){
			Value data = null;
			String inLine = scan.nextLine();
			String lineData[] = inLine.split(",");

			// insert class into classes List
			if(classes.isEmpty()){
				classes.add(lineData[classIndex]);
				classesCount.add(classes.indexOf(lineData[classIndex]), 1);
			}
			else{
				if(!classes.contains(lineData[classIndex])){
					classes.add(lineData[classIndex]);
					classesCount.add(classes.indexOf(lineData[classIndex]), 1);
				}
				else {
					classesCount.set(classes.indexOf(lineData[classIndex]),
							classesCount.get(classes.indexOf(lineData[classIndex])) + 1);
				}
			}

			// insert data into attributes
			for(int x = 0; x < numAttributes; x++){
				data = new Value(lineData[x], lineData[classIndex]);
				attributes[x].insertValue(data);
			}
		}

		double IofD = calcIofD(classesCount); // Set information criteria

		// TESTING DATA
		/*
		Attribute age = new Attribute("age");

		Value inV = new Value("30","yes"); age.insertValue(inV);
		inV = new Value("30","yes"); age.insertValue(inV);
		inV = new Value("30","no"); age.insertValue(inV);
		inV = new Value("30","no"); age.insertValue(inV);
		inV = new Value("30","no"); age.insertValue(inV);
		inV = new Value("35","yes"); age.insertValue(inV);
		inV = new Value("35","yes"); age.insertValue(inV);
		inV = new Value("35","yes"); age.insertValue(inV);
		inV = new Value("35","yes"); age.insertValue(inV);
		inV = new Value("40","yes"); age.insertValue(inV);
		inV = new Value("40","yes"); age.insertValue(inV);
		inV = new Value("40","yes"); age.insertValue(inV);
		inV = new Value("40","no"); age.insertValue(inV);
		inV = new Value("40","no"); age.insertValue(inV);

		System.out.println(age.toString());

		List<Integer> testCount = new ArrayList<Integer>();
		testCount.add(9);
		testCount.add(5);

		double testIofD = calcIofD(testCount);
		age.calculateGain(testIofD,14);

		System.out.println("I of D: " + testIofD);
		System.out.println("age: " + age._gain);
		*/


		for(Attribute a : attributes){
			System.out.println(a.toString());
		}


	}

	public static double calcIofD(List<Integer> classesCount){
		double IofD = 0.0;
		int totalNumClasses = 0;
		for(int i : classesCount){
			totalNumClasses += i;
		}
		for(double d : classesCount){
			double temp = (-1 * (d/totalNumClasses)) * (Math.log((d/totalNumClasses)) / Math.log(2));
			IofD += temp;
		}
		return IofD;
	}
}
