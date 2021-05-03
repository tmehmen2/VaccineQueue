// --== CS400 File Header Information ==--
// Name: Anush Ram Reddy Kethi Reddy
// Email: akethireddy@wisc.edu
// Team: LD
// Role: Backend Engineer
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>


/**
 * This class handles the IO of the program to a file "ApartmentList.txt"
 * The purpose of this is to hold memory of the listed apartments
 * When the program is not running
 * It holds a working list of apartments while it is active and will
 * write back to the file with update()
 *
 * <p>Bugs: (a list of bugs and other problems)
 *
 * @author Taylor Mehmen, Anush
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler {
    private ArrayList<String> patientList = new ArrayList<String>();
    String fileName;
    /**
     * (This constructor method opens the file and reads the apartments
     * line by line. It then puts them into a list stored within the class.
     * After reading it closes the file for safety.)
     *
     * @param (fileName) (the name of the file for IO)
     */

    public FileHandler(String fileName) {
        this.fileName=fileName;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while(line !=null){
                patientList.add(line);
                //System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * (The getPatients() method is used for whichever class makes this object
     * The class can get a list of all the patients in the file)
     *
     * @return (patientList - a list of all patients in the txt file)
     */
    public ArrayList<String> getPatients() {
        return patientList;
    }

    /**
     * (The addToPatientList() method adds a patient string to the working list)
     */
    public void addToPatientList(String patientLine){
        patientList.add(patientLine);
    }
    
    /**
     * removes the specified patient from the working arrayList of patients 
     * @param patient
     */
    public void removeFromPatientList(String patientLine) {
      patientList.remove(patientLine);
    }


    /**
     * (The updateFile() method opens the file for writing
     * It then rewrites the file with all apartments currently in working list.
     *
     * @param (writer) (BufferedWriter object to output to)
     */
    public void updateFile(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            for (int x = 0; x<patientList.size(); x++){
                writer.write(patientList.get(x));
                writer.newLine();
            }
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}