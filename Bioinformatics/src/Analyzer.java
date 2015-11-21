import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//there is an error with parsing the txt file (will explain tomorrow).
//patients.size() = 167 (1 dummy)
//remissions.size() + resistant.size() = 166

public class Analyzer {
	
	static ArrayList<Patient> patients     = new ArrayList<Patient>();
	
	//patients split
	static ArrayList<Patient> remissions   = new ArrayList<Patient>();
	static ArrayList<Patient> resistant    = new ArrayList<Patient>();
	
	static ArrayList<Patient> remNumbs     = new ArrayList<Patient>();//

	//patient averages
	static ArrayList<Average> remAverage = new ArrayList<Average>();
	static ArrayList<Average> resistAverage = new ArrayList<Average>();
	
	//patient standard deviations
	/*static ArrayList<STD> remSTD = new ArrayList<Patient>();
	static ArrayList<STD> resistSTD = new ArrayList<Patient>();*/
	
	
	
	public static void main(String[] args){
		try{
			PropertyImport dummy1 = new PropertyImport("dummy");
			patients.add(new Patient(dummy1.finalprops));
			
			File file = new File("trainingData.txt");
			Scanner scanner = new Scanner(file);
			String line = scanner.nextLine();
			
			while(scanner.hasNext()){
				line = scanner.nextLine();
				PropertyImport a = new PropertyImport(line);
				Patient p = new Patient(a.finalprops);
				patients.add(p);
			}
			scanner.close();
			
			//sets remissions & resistant patients
			setRemissions();
			setResistant();
	
			setAverage();
			
			for(int i=0; i<resistAverage.size(); i++){
				System.out.println(resistAverage.get(i).average+" , column:"+resistAverage.get(i).index);
			}
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	//SETS ARRAYLIST "REMISSIONS"
	public static void setRemissions(){
		for(int i=1; i<patients.size(); i++){
			if(patients.get(i).properties[266].propertyValue.equals("COMPLETE_REMISSION")){
				remissions.add(patients.get(i));
			}
		}
	}
	
	//SETS ARRAYLIST "RESISTANT'
	public static void setResistant(){
		for(int i=1; i<patients.size(); i++){
			if(patients.get(i).properties[266].propertyValue.equals("RESISTANT")){
				resistant.add(patients.get(i));
			}
		}
	}
	
	//Calculates binary average
	public static void calculateBinaryAverage(int column){
		ArrayList<String> uniqueValues = PropertyElements(column);
		for(int i=0; i<uniqueValues.size(); i++){
			yesNo(column, uniqueValues.get(i));
		}
			
	}
	//FINDS AVG OF BINARY PROPERTIES. EDIT TO INCLUDE MORE INPUTS (USE METHOD "PROPERTYELEMENTS")
	public static void yesNo(int column, String comparator) {
		int rem = 0;
		int res = 0;
		for(int i = 0; i<remissions.size(); i++){
			if(remissions.get(i).properties[column].propertyValue.equals(comparator)) { 
				rem++;
			}
		}
			System.out.println("");
			
		for(int i = 0; i<resistant.size(); i++){
			if(resistant.get(i).properties[column].propertyValue.equals(comparator)) { 
				res++;
			}
		}
		System.out.println("Remission: number of " + comparator + " " + rem + "/" + remissions.size());
		System.out.println("Non Remission: number of " + comparator + " " + res + "/" + resistant.size());
	}
	
	//EDIT: DETERMINES UNIQUE VALUES OF A PROPERTY AND STORES IT IN AN ARRAY TO CALCULATE AVG
	public static ArrayList<String> PropertyElements(int column) {
			
		ArrayList<String> result = new ArrayList<String>();
		result.add(patients.get(1).properties[column].propertyValue);
			
		for(int i=1; i<patients.size();i++) {
			String value = patients.get(i).properties[column].propertyValue;
			int duplicates = 0;
			for(int j = 0; j<result.size(); j++){
				if(value.equals(result.get(j)))
					duplicates++;
				}
			if(duplicates==0)
				result.add(value);
			}	
		return result;
	}
	//CALCULATES AVERAGE OF INT TABLE DATA
	public static double calculateAverageInt(int column, ArrayList<Patient> p){
		int patients = p.size();
		double sum = 0;
		for(int i=0; i<p.size(); i++){
			if(p.get(i).properties[column].propertyValue.equals("NA")){ 
				patients--;
			}else{
				sum += Double.valueOf(p.get(i).properties[column].propertyValue); 
			}
		}
		
		double average = sum/patients;
		
		return average;
	}
	
	//SETS AVERAGE OF REMISSIONS AND RESISTANTS (INT DATA ONLY)
	public static void setAverage(){
		int column = 0;
		while(column < patients.get(1).properties.length){
			if(!(column == 0 || column == 1 || column == 4 || column == 5 || column == 6 || column == 7 || column == 8 || column == 9 || column == 10 || column == 11 || column == 266)){
				remAverage.add(new Average(calculateAverageInt(column, remissions), column));
				resistAverage.add(new Average(calculateAverageInt(column, resistant), column));
			}
			column++;
		}
	}
	
	//CALCULATES STANDARD DEVIATION OF INT TABLE DATA
	public static double calculateSTD(int column, double average, ArrayList<Patient> p){
		double variance = 0;
		int patients = p.size();
		for(int i=0; i<p.size(); i++){
			if(p.get(i).properties[column].propertyValue.equals("NA")){
				patients--;
			}else{
				double difference = Double.valueOf(p.get(i).properties[column].propertyValue)-average; 
				variance += difference*difference;
			}
		}
		
		variance = variance/patients;
		
		double std = Math.sqrt(variance);	
		
		return std;
	}
	
	//Just ignore for now.
	public static void createNumbsArray(ArrayList<Patient> L, int property) {
		for(int i = 0; i < L.get(1).properties.length; i++)
		{
			String blah = L.get(1).properties[i].propertyValue; 
			if(blah.contains("0") || blah.contains("1") || blah.contains("2")|| blah.contains("3") || blah.contains("4") || blah.contains("5") || blah.contains("6") || blah.contains("7")|| blah.contains("8") || blah.contains("9"))
			{
				for(int j = 1; j < L.size(); j++)
				{
					
				}
			}
		}
	}
}
