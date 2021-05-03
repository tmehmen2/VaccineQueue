import java.util.Scanner;

/**
 * This class is the front end of the application with which the user interacts
 * 
 * @author rishi_agrawal
 *
 */
public class FrontEnd {

  /**
   * The user adds a patient using this method
   * 
   * @param dataProcessor
   */
  public static void addPatient(DataProcessor dataProcessor) {

    Scanner scnr = new Scanner(System.in);

    String name;
    int age; // height in cm, weight in KG
    double weight;
    double height;
    double BMI;
    String sex; // 0 for Male, 1 for Female
    boolean essentialWorker; // for medical/frontline workers
    int diabetesStage;// 0 for none, 1 for type 1 , and 2 for type 2
    int heartConditions; // 0-5 on increasing scales of severity, 0 being none
    boolean immunoCompromise;
    int respiratoryConditions; // 0-5 on increasing scales of severity, 0 being none



    System.out.println("What is the name of the Patient? \n");
    name = scnr.nextLine();

    System.out.println("What is the age of the Patient? \n");
    age = scnr.nextInt();

    System.out.println("What is the weight of the Patient? \n");
    weight = scnr.nextDouble();

    System.out.println("What is the height of the Patient? \n");
    height = scnr.nextDouble();

    BMI = weight / (Math.pow((height / 100), 2));

    System.out.println("What is the sex of the Patient? \n");
    sex = scnr.next();

    System.out.println(
        "Is the patient diabetic? If so, enter the type of diabetes (1 or 2). If not, enter zero \n");
    diabetesStage = scnr.nextInt();

    System.out.println("Is the patient an essential worker? (Enter True or False)\n");
    essentialWorker = scnr.nextBoolean();

    System.out.println("Is the patient's immunity compromised? (Enter True or False) \n");
    immunoCompromise = scnr.nextBoolean();

    System.out
        .println("Does the patient have a heart condition? (Enter severity between 0 and 5) \n");
    heartConditions = scnr.nextInt();

    System.out
        .println("Does the patient have respiratory issues? (Enter severity between 0 and 5) \n");
    respiratoryConditions = scnr.nextInt();


    if(diabetesStage==1) {
      diabetesStage=2;
    }
    
    else if(diabetesStage==2) {
      diabetesStage=1;
    }
    
    Patient newPatient = new Patient(name, age, sex, height, weight, essentialWorker, diabetesStage,
        heartConditions, immunoCompromise, respiratoryConditions);

    dataProcessor.listPatient(newPatient);

    System.out.println("The following patient has been added to the Vaccine queue. \n");
    System.out.println(newPatient + "\n");

  }


  /**
   * 
   * calls the Vaccinate method from the dataProcessor class
   * 
   * @param dataProcessor
   */
  public static void vaccinate(DataProcessor dataProcessor) {

    try {
    System.out.println(dataProcessor.vaccinate());
    }
    catch (NullPointerException e) {
      System.out.println("No more patients in the queue");
      return;
    }
    
    System.out.println("The following patient has been vaccinated. \n ");
    
    


  }

  /**
   * 
   * Main method which calls all the other methods in this class and handles user inputs
   * 
   * @param args
   */
  public static void main(String[] args) {

    DataProcessor dataProcessor = new DataProcessor();

    System.out.println("Welcome to the Covid Vaccination Priority Queue");

    int input = 0;

    while (input != 3) {
      System.out.println("");
      System.out.println("Are you ( Please Enter a value of 1 or 2 )");
      System.out.println("");
      System.out.println("      - > 1) Adding a Patient");
      System.out.println("");
      System.out.println("           or");
      System.out.println("");
      System.out.println("      - > 2) Vaccinating a patient");
      System.out.println("");
      System.out.println("           or");
      System.out.println("");
      System.out.println("      - > 3) Exiting the program");
      System.out.println("");



      Scanner scnr = new Scanner(System.in);

      try {
        input = scnr.nextInt();
        
        if (input == 1) {
          addPatient(dataProcessor);
        } else if (input == 2) {
          vaccinate(dataProcessor);
        } else if (input == 3) {
          System.out.println("You are now exiting the program.");
        } else {
          throw new IndexOutOfBoundsException("");
        }
      } catch (IndexOutOfBoundsException | java.util.InputMismatchException e) {
        System.out.println("You did not input an appropriate digit");
      }
    }
  }
}
