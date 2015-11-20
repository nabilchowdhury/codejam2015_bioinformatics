import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//there is an error with parsing the txt file (will explain tomorrow).

public class Analyzer {
	
	static ArrayList<Patient> patients     = new ArrayList<Patient>();
	static ArrayList<Patient> remissions   = new ArrayList<Patient>();
	static ArrayList<Patient> remNumbs     = new ArrayList<Patient>();
	static ArrayList<Patient> resistant    = new ArrayList<Patient>();
	
	public static void main(String[] arg){
		try{
			patients.add(new Patient())
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			//-----------------
			patients.add(new Patient("dummy"));
			File file = new File("trainingData.txt");
			Scanner scanner = new Scanner(file);
			
			String line = scanner.nextLine();
			
			while(scanner.hasNext()){
				line = scanner.nextLine();
				patients.add(new Patient(line));
			}
			scanner.close();
			
			//sets remissions & resistant patients
			setRemissions();
			setResistant();
	
			//remove print statement after test
			System.out.println(calculateSTD(15, calculateAverageInt(15, remissions), remissions));
			
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	//SETS ARRAYLIST "REMISSIONS"
	public static void setRemissions(){
		for(int i=1; i<patients.size(); i++){
			if(patients.get(i).properties[266].equals("COMPLETE_REMISSION")){
				remissions.add(patients.get(i));
			}
		}
	}
	
	//SETS ARRAYLIST "RESISTANT'
	public static void setResistant(){
		for(int i=1; i<patients.size(); i++){
			if(patients.get(i).properties[266].equals("RESISTANT")){
				resistant.add(patients.get(i));
			}
		}
	}
	
	//EDIT: FINDS AVG OF BINARY PROPERTIES. EDIT TO INCLUDE MORE INPUTS (USE METHOD "PROPERTYELEMENTS")
	public void yesNo(int column, String comparator) {
		int rem = 0;
		int res = 0;
		for(int i = 0; i<remissions.size(); i++){
			if(remissions.get(i).properties[column].equals(comparator)) {
				rem++;
			}
		}
			System.out.println("");
			
		for(int i = 0; i<resistant.size(); i++){
			if(resistant.get(i).properties[column].equals(comparator)) {
				res++;
			}
		}
		System.out.println("Remission: number of" + comparator + rem + "/" + remissions.size());
		System.out.println("Non Remission: number of" + comparator + res + "/" + resistant.size());
	}
	
	//EDIT: DETERMINES UNIQUE VALUES OF A PROPERTY AND STORES IT IN AN ARRAY TO CALCULATE AVG
	public ArrayList<String> PropertyElements(int property) {
		ArrayList<String> result = new ArrayList<String>();	
		for(int i=0; i<166; i++){
			for(int j=0; j<result.size(); j++){
				if(!patients.get(i).properties[property].equals(patients.get(j).properties[property]))
						result.add(patients.get(i).properties[property]);
			}
		}
		return result;
	}
	
	//CALCULATES AVERAGE OF INT TABLE DATA
	public static double calculateAverageInt(int column, ArrayList<Patient> p){
		int patients = p.size();
		double sum = 0;
		for(int i=0; i<p.size(); i++){
			if(p.get(i).properties[column].equals("NA")){
				patients--;
			}else{
				sum += Double.valueOf(p.get(i).properties[column]);
			}
		}
		
		double average = sum/patients;
		
		return average;
	}
	
	//CALCULATES STANDARD DEVIATION OF INT TABLE DATA
	public static double calculateSTD(int column, double average, ArrayList<Patient> p){
		double variance = 0;
		int patients = p.size();
		for(int i=0; i<p.size(); i++){
			if(p.get(i).properties[column].equals("NA")){
				patients--;
			}else{
				double difference = Double.valueOf(p.get(i).properties[column])-average;
				variance += difference*difference;
			}
		}
		
		variance = variance/patients;
		
		double std = Math.sqrt(variance);	
		
		return std;
	}
	
	public static void createNumbsArray(ArrayList<Patient> L, int property) {
		for(int i = 0; i < L.get(1).properties.length; i++)
		{
			String blah = L.get(1).properties[i];
			if(blah.contains("0") || blah.contains("1") || blah.contains("2")|| blah.contains("3") || blah.contains("4") || blah.contains("5") || blah.contains("6") || blah.contains("7")|| blah.contains("8") || blah.contains("9"))
			{
				for(int j = 1; j < L.size(); j++)
				{
					
				}
			}
		}
	}
}
