import java.util.ArrayList;

/**
 * class that interfaces with the FileHandler class to load all of the information stored in the txt file
 * into the RBT. This is also the class that bridges the gap between the front end and back end, i.e.,
 * the program implements back end functionality through this class.
 *
 * @author anush
 *
 */
public class DataProcessor {

    private RedBlackTreeDelete<Patient> queue;

    private static String fileName = "PatientList.txt";
    private FileHandler patientFile;


    /**
     * constructor that loads up the txt file and transfers all the patient data to a new queue.
     */
    public DataProcessor() {
        patientFile = new FileHandler(fileName);
        ArrayList<String> patientList = patientFile.getPatients();
        queue = new RedBlackTreeDelete<Patient>();
        for (String s : patientList) {

            String info[] = s.split("/");
            String name = info[0];
            int age = Integer.parseInt(info[1]);
            double weight = Double.parseDouble(info[2]);
            double height = Double.parseDouble(info[3]);
            String sex = info[4];

            boolean essentialWorker;

            if (info[5].contains("T") || info[5].contains("t")) {
                essentialWorker = true;
            } else {
                essentialWorker = false;
            }

            boolean immunoCompromise;

            if (info[6].contains("T") || info[6].contains("t")) {
                immunoCompromise = true;
            }

            else {
                immunoCompromise = false;
            }

            int diabetesStage = Integer.parseInt(info[7]);
            int heartConditions = Integer.parseInt(info[8]);
            int respiratoryConditions = Integer.parseInt(info[9]);

            Patient p = new Patient(name, age, sex, height, weight, essentialWorker, diabetesStage, heartConditions, immunoCompromise, respiratoryConditions);

            queue.insert(p);

        }
    }
    /**
     * method that lists a new patient in the txt file
     * @param p the patient to list
     */
    public void listPatient(Patient p) {
        String profession = p.isEssentialWorker() ? "T" : "F";
        String immune = p.isImmunoCompromise() ? "T" : "F";
        String patientLine =
                p.getName() + ("/") + p.getAge() + ("/") + p.getWeight() + ("/") + p.getHeight() + ("/")
                        + p.getSex() + ("/") + profession + ("/") + immune + ("/") + p.getDiabetesStage()
                        + ("/") + p.getHeartConditions() + ("/") + p.getRespiratoryConditions();
        queue.insert(p);
        patientFile.addToPatientList(patientLine);
        patientFile.updateFile();

    }

    /**
     * method that deletes a patient from the queue and txt file
     * @param p
     */
    public void deletePatient(Patient p) {
        String profession = p.isEssentialWorker() ? "T" : "F";
        String immune = p.isImmunoCompromise() ? "T" : "F";
        String patientLine =
                p.getName() + ("/") + p.getAge() + ("/") + p.getWeight() + ("/") + p.getHeight() + ("/")
                        + p.getSex() + ("/") + profession + ("/") + immune + ("/") + p.getDiabetesStage()
                        + ("/") + p.getHeartConditions() + ("/") + p.getRespiratoryConditions();
        queue.remove(p);
        patientFile.removeFromPatientList(patientLine);
        patientFile.updateFile();
    }


    public Patient vaccinate() {
        Patient nextUp= queue.getRightMostNode();
        Patient temp = nextUp;
        deletePatient(nextUp);
        return nextUp;
    }

}