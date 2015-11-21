import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Analyzer {
	
	static ArrayList<Patient> patients = new ArrayList<Patient>();
	static ArrayList<Patient> remissions = new ArrayList<Patient>();
	static ArrayList<Patient> resistant = new ArrayList<Patient>();
	static ArrayList<String> 
	
	public static void main(String[] arg){
		try{
			patients.add(new Patient("dummy"));
			File file = new File("trainingData.txt");
			Scanner scanner = new Scanner(file);
			
			String line = scanner.nextLine();
			
			while(scanner.hasNext()){
				line = scanner.nextLine();
				patients.add(new Patient(line));
			}
			scanner.close();
			
			//sets remissions & resistants
			setRemissions();
			setResistant();
	
			//System.out.println(remissions.size() + resistant.size());
			
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	//sets remissions
	public static void setRemissions(){
		for(int i=1; i<patients.size(); i++){
			if(patients.get(i).properties[266].equals("COMPLETE_REMISSION")){
				remissions.add(patients.get(i));
			}
		}
	}
	
	//sets resistants
	public static void setResistant(){
		for(int i=1; i<patients.size(); i++){
			if(patients.get(i).properties[266].equals("RESISTANT")){
				resistant.add(patients.get(i));
			}
		}
	}
	
	//finds avg of binary propertiers //edit
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
	
	//Determines the unique values of a property element stores in 
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
	
	
}
