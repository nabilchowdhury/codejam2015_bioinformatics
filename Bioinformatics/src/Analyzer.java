import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Analyzer {
	
	static ArrayList<Patient> patients = new ArrayList<Patient>();
	static ArrayList<Patient> remissions = new ArrayList<Patient>();
	
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
			
			//sets remissions
			setRemissions();
			for(int i=0; i<remissions.size(); i++){
				System.out.println(remissions.get(i).properties[266]);
			}
			
			//System.out.println(patients.get(5).properties[266]);
		}catch(Exception e){
			
		}
	}
	
	public static void setRemissions(){
		for(int i=0; i<patients.size(); i++){
			if(patients.get(i).properties[266].equals("COMPLETE_REMISSION")){
				remissions.add(patients.get(i));
			}
		}
	}
	
	
}
