import java.util.Date;
import java.util.Random;

public class Patient implements Comparable<Patient> {
    /**
     * Patient class to be used in the covid vaccine queue. contains patient data and returns a score
     * for how vulnerable the patient is.
     * @author anush
     */
    private String name;
    private int age; // height in cm, weight in KG
    private double weight, height;
    private double BMI;
    private String sex; // M for Male, F for Female
    private boolean essentialWorker; // for medical/frontline workers
    private boolean immunoCompromise;
    private int diabetesStage;// 0 for none, 1 for type 1 , and 2 for type 2
    private int heartConditions; // 0-5 on increasing scales of severity, 0 being none
    private int respiratoryConditions; // 0-5 on increasing scales of severity, 0 being none

    /**
     * @param age              age of patient in years
     * @param height           in cm
     * @param weight           in KG
     * @param sex              //0 for Male, 1 for Female
     * @param essentialWorker  //for medical/frontline workers
     * @param diabetesStage    //0 for none, 1 for type 1 , and 2 for type 2
     * @param heartConditions  0-5 on increasing scales of severity, 0 being none
     * @param immunoCompromise true if immunoCompromised, false if not
     */
    public Patient(String name,int age, String sex, double height, double weight, boolean essentialWorker,
                   int diabetesStage, int heartConditions, boolean immunoCompromise, int respiratoryConditions) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.BMI = weight / (Math.pow((height / 100), 2));
        this.sex = sex;
        this.essentialWorker = essentialWorker;
        this.diabetesStage = diabetesStage;
        this.heartConditions = heartConditions;
        this.immunoCompromise = immunoCompromise;
        this.respiratoryConditions = respiratoryConditions;

    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @return the essentialWorker
     */
    public boolean isEssentialWorker() {
        return essentialWorker;
    }

    /**
     * @return the immunoCompromise
     */
    public boolean isImmunoCompromise() {
        return immunoCompromise;
    }

    /**
     * @return the diabetesStage
     */
    public int getDiabetesStage() {
        return diabetesStage;
    }

    /**
     * @return the heartConditions
     */
    public int getHeartConditions() {
        return heartConditions;
    }

    /**
     * @return the respiratoryConditions
     */
    public int getRespiratoryConditions() {
        return respiratoryConditions;
    }

    /**
     * returns a score from 0-100 to determine how vulnerable the patient is to severe illness/death
     * from COVID 19, with age and immune health being the largest contributing factors. Everything
     * else contributes 10 points to the score, with heart and respiratory conditions being scaled by
     * a factor of 2, while diabetes is scaled by a factor of 5.
     *
     * @return the score for the patient.
     */
    public int getScore() {

        int wtFactor;

        if (this.BMI >= 30 || this.BMI <= 40) {
            wtFactor = 5;
        }

        if (this.BMI >= 40) {
            wtFactor = 10;
        }

        else {
            wtFactor = 0;
        }

        int ageFactor;

        if ((age / 10) >= 6) {
            ageFactor = (age / 10) * 2;
        }

        else {
            ageFactor = age / 10;
        }

        int immuneFactor = immunoCompromise ? 20 : 0;

        int professionFactor = essentialWorker ? 10 : 0;

        int patientScore = ageFactor + (diabetesStage * 5) + (wtFactor) + (respiratoryConditions * 2)
                + (heartConditions * 2) + immuneFactor + professionFactor;

        return patientScore;

    }
    /**
     * compares two patient objects.
     * Returns 1 if this paient's score is greater, -1 if lesser, and chooses one of the two
     * randomly if both scores are equal.
     * @param o
     * @return
     */
    @Override
    public int compareTo(Patient o) {

        if (o.getScore() < this.getScore()) {
            return 1;
        }

        if (o.getScore() == this.getScore()) {
            return new Random().nextBoolean() ? 1 : -1;
        }

        else {
            return -1;
        }

    }

    /**
     * returns a string representation of the patient
     */
    @Override
    public String toString() {

        return name;
        + "\n age=" + age + "\n weight=" + weight + "\n height=" + height
        + "\n sex=" + sex + "\n Medical Worker=" + essentialWorker + "\n Immuno Compromised="
        + immunoCompromise + "\n Diabetes Type=" + diabetesStage + "\n heartConditions="
        + heartConditions + "\n Severity of Respiratory Conditions=" + respiratoryConditions;
    }


}
