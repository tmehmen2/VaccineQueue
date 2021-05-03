// --== CS400 File Header Information ==--
// Name: Taylor Mehmen
// Email: tmehmen@wisc.edu
// Team: LD
// TA: Divyanashu
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>


import org.junit.Test;
import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
/**
 * This is a junit test suite designed to test different high-level cases
 * of adding and removing patients from the vaccine queue.
 */
public class ProjectTest {

    /**
     * This junit test method tests a making Patient objects and getting
     * getting their scores. Then checks to make sure the scores are correct.
     */
    @Test
    public void makePatients() {
        Patient patient1 = new Patient("Taylor", 23, "M", 200, 100,
                false, 0, 0,
                false, 0);
        int score1 = patient1.getScore();
        assertEquals(2,patient1.getScore());
        Patient patient2 = new Patient("Bob", 33, "M", 150, 70,
                false, 0, 2,
                true, 0);
        int score2 = patient2.getScore();
        assertEquals(27,patient2.getScore());
        Patient patient3 = new Patient("Sarah", 43, "F", 120, 50,
                true, 2, 0,
                true, 0);
        int score3 = patient3.getScore();
        assertEquals(44,patient3.getScore());
        Patient patient4 = new Patient("Hannah", 53, "F", 140, 60,
                false, 0, 0,
                false, 0);
        int score4 = patient4.getScore();
        assertEquals(5,patient4.getScore());
        Patient patient5 = new Patient("John", 63, "M", 220, 110,
                true, 1, 4,
                true, 1);
        int score5 = patient5.getScore();
        assertEquals(57,patient5.getScore());
    }



    /**
     * This junit test method tests if the tree works to add Patient objects to it
     * and then checks the tree. It checks to see if the tree prints out in the
     * correct order.
     */

    @Test
    public void addToTree() {
        Patient patient1 = new Patient("Taylor", 23, "M", 200, 100,
                false, 0, 0,
                false, 0);
        Patient patient2 = new Patient("Bob", 33, "M", 150, 70,
                false, 0, 2,
                true, 0);
        Patient patient3 = new Patient("Sarah", 43, "F", 120, 50,
                true, 2, 0,
                true, 0);
        Patient patient4 = new Patient("Hannah", 53, "F", 140, 60,
                false, 0, 0,
                false, 0);
        Patient patient5 = new Patient("John", 63, "M", 220, 110,
                true, 1, 4,
                true, 1);
        RedBlackTreeDelete<Patient> tree1 = new RedBlackTreeDelete<Patient>();
        tree1.insert(patient1);
        tree1.insert(patient2);
        tree1.insert(patient3);
        tree1.insert(patient4);
        tree1.insert(patient5);
        assertEquals("[Bob, Taylor, Sarah, Hannah, John]",tree1.toString());
    }


    /**
     * This junit test method tests if the tree works to delete Patient objects from it
     * and then checks the tree. First it adds all of the Patients.
     * Then it deletes a few and it checks to see if the tree prints out in the
     * correct order.
     */
    @Test
    public void RemoveFromTree() {
        Patient patient1 = new Patient("Taylor", 23, "M", 200, 100,
                false, 0, 0,
                false, 0);
        Patient patient2 = new Patient("Bob", 33, "M", 150, 70,
                false, 0, 2,
                true, 0);
        Patient patient3 = new Patient("Sarah", 43, "F", 120, 50,
                true, 2, 0,
                true, 0);
        Patient patient4 = new Patient("Hannah", 53, "F", 140, 60,
                false, 0, 0,
                false, 0);
        Patient patient5 = new Patient("John", 63, "M", 220, 110,
                true, 1, 4,
                true, 1);
        RedBlackTreeDelete<Patient> tree1 = new RedBlackTreeDelete<Patient>();
        tree1.insert(patient1);
        tree1.insert(patient2);
        tree1.insert(patient3);
        tree1.insert(patient4);
        tree1.insert(patient5);
        tree1.remove(patient4);
        assertEquals("[Bob, Taylor, Sarah, John]",tree1.toString());
        tree1.remove(patient2);
        assertEquals("[Sarah, Taylor, John]",tree1.toString());
    }
    /**
     * This junit test method tests if the DataProccessor class works to List patients.
     * It creates patients and lists them and it should return no errors when
     * adding them to the file or the tree
     */
    @Test
    public void TestListPatient() {
        Patient patient1 = new Patient("Taylor", 23, "M", 200, 100,
                false, 0, 0,
                false, 0);
        Patient patient2 = new Patient("Bob", 33, "M", 150, 70,
                false, 0, 2,
                true, 0);
        Patient patient3 = new Patient("Sarah", 43, "F", 120, 50,
                true, 2, 0,
                true, 0);
        Patient patient4 = new Patient("Hannah", 53, "F", 140, 60,
                false, 0, 0,
                false, 0);
        Patient patient5 = new Patient("John", 63, "M", 220, 110,
                true, 1, 4,
                true, 1);
        DataProcessor dataProcessor = new DataProcessor();
        dataProcessor.listPatient(patient1);
        dataProcessor.listPatient(patient2);
        dataProcessor.listPatient(patient3);
        dataProcessor.listPatient(patient4);
        dataProcessor.listPatient(patient5);
    }
    /**
     * This junit test method tests if the DataProccessor class works to Vaccinate a patient.
     * It creates a patient that should be vaccined first all the time and then
     * it adds it to the list. When it calls the vaccinate command it should
     * always return the same patient.
     */
    @Test
    public void TestVaccinate() {
        DataProcessor dataProcessor = new DataProcessor();
        Patient patient1 = new Patient("Topper", 99, "M", 220, 100,
                true, 2, 5,
                true, 5);
        dataProcessor.listPatient(patient1);
        assertEquals("Topper",dataProcessor.vaccinate().getName());
    }

}
