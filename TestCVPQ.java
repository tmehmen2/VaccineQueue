import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestCVPQ {

  /**
   * Test the score calculations from Patient class
   * Method fails if the score calculated isn't as expected
   */
  @Test
  void pointsAlgoTest() {
    // test 1
    Patient zero = new Patient("zero", 25, "M", 180, 75, true, 0, 1, false, 3);
    if(zero.getScore() != 20)
      fail("pointsAlgoTest() has failed");
    
    // test 2
    Patient one = new Patient("one", 35, "F", 160, 65, false, 2, 2, true, 3);
    if(one.getScore() != 43)
      fail("pointsAlgoTest() has failed");
    
    // test 3
    Patient two = new Patient("two", 35, "M", 160, 245, true, 2, 4, false, 3);
    if(two.getScore() != 47)
      fail("pointsAlgoTest() has failed");
  }
  
  /**
   * Tests the remove method of the binary search tree
   * method fails if the string representation isn't as expected after remove is called
   */
  @Test
  void removeNodeTest() {
    // create a red black tree to be tested on
    RedBlackTreeDelete<Integer> testTree = new RedBlackTreeDelete<Integer>();
    testTree.insert(45);
    testTree.insert(22);
    testTree.insert(72);
    testTree.insert(18);
    testTree.insert(31);
    testTree.insert(68);
    testTree.insert(91);
    testTree.insert(13);
    testTree.insert(28);
    testTree.insert(42);
    testTree.insert(88);
    
    // case 1: remove node with two children
    testTree.remove(22);
    String rbt = testTree.toString();
    String expected = "[45, 28, 72, 18, 31, 68, 91, 13, 42, 88]";

    // compares the toString rbt to the expected string value
    if (!rbt.equals(expected))
      fail("Remove method for case 1 has failed");
    
    // case 2: remove node with one child
    testTree.remove(18);
    rbt = testTree.toString();
    expected = "[45, 28, 72, 13, 31, 68, 91, 42, 88]";

    // compares the toString rbt to the expected string value
    if (!rbt.equals(expected) | !testTree.root.leftChild.leftChild.isBlack)
      fail("Remove method for case 2 has failed");
    
    // case 3: remove red node with no children
    testTree.remove(42);
    rbt = testTree.toString();
    expected = "[45, 28, 72, 13, 31, 68, 91, 88]";

    // compares the toString rbt to the expected string value
    if (!rbt.equals(expected))
      fail("Remove method for case 3 has failed");

  }  
  
  /**
   * Tests if the add method works for Patient node i.e if patients are added
   * according to their score
   * 
   * method fails if the string representation isn't as expected after add method is called
   */
  @Test
  void addPatientTest() {
    // create a red black tree to be tested on
    RedBlackTreeDelete<Patient> testTree = new RedBlackTreeDelete<Patient>();
    testTree.insert(new Patient("zero", 25, "M", 180, 75, true, 0, 1, false, 3));
    testTree.insert(new Patient("one", 35, "F", 160, 65, false, 2, 2, true, 3));
    testTree.insert(new Patient("two", 31, "M", 160, 245, true, 2, 4, false, 3));
    testTree.insert(new Patient("three", 65, "F", 160, 245, true, 2, 4, false, 3));
    testTree.insert(new Patient("four", 21, "F", 150, 120, false, 0, 0, false, 1));
    
    String rbt = testTree.toString();
    String expected = "[one, zero, two, four, three]";
    if (!rbt.equals(expected))
      fail("Add patient has failed");
    
    // add patient with the same score
    testTree.insert(new Patient("five", 25, "M", 180, 75, true, 0, 1, false, 3));
    rbt = testTree.toString();
    expected = "[one, zero, two, four, five, three]";
    if (!rbt.equals(expected))
      fail("Add patient with same score has failed");
  }

  /**
   * Tests the remove method and getRightMostNode of the binary search tree from
   * class RedBlackTree
   * method fails if the string representation isn't as expected after remove is called
   */
  @Test
  void removePatientTest() {
    // create a red black tree to be tested on
    RedBlackTreeDelete<Patient> testTree = new RedBlackTreeDelete<Patient>();
    testTree.insert(new Patient("zero", 25, "M", 180, 75, true, 0, 1, false, 3));
    testTree.insert(new Patient("one", 35, "F", 160, 65, false, 2, 2, true, 3));
    testTree.insert(new Patient("two", 31, "M", 160, 245, true, 2, 4, false, 3));
    testTree.insert(new Patient("three", 65, "F", 160, 245, true, 2, 4, false, 3));
    testTree.insert(new Patient("four", 21, "F", 150, 120, false, 0, 0, false, 1));
    testTree.insert(new Patient("five", 65, "M", 160, 190, true, 1, 0, false, 4));
    
    // remove random patient
    testTree.remove(testTree.root.leftChild.leftChild.data);
    String rbt = testTree.toString();
    String expected = "[one, zero, two, five, three]";
    if (!rbt.equals(expected))
      fail("Add patient with same score has failed");
    
    // remove rightmost node
    testTree.remove(testTree.getRightMostNode());
    rbt = testTree.toString();
    expected = "[one, zero, two, five]";
    if (!rbt.equals(expected))
      fail("Add patient with same score has failed");
  }
  
  /**
   * Tests the List Patient method from Data Processor class
   * method fails if listPatient throws any errors
   */
  @Test
  void ListPatient() {
    try {
      DataProcessor dataProcessor = new DataProcessor();
      dataProcessor.listPatient(new Patient("zero", 25, "M", 180, 75, false, 0, 1, false, 3));
      dataProcessor.listPatient(new Patient("one", 35, "F", 160, 65, false, 2, 2, true, 3));
      dataProcessor.listPatient(new Patient("two", 31, "M", 160, 245, true, 2, 4, false, 3));
      dataProcessor.listPatient(new Patient("three", 65, "F", 160, 245, true, 2, 4, false, 3));
      dataProcessor.listPatient(new Patient("four", 21, "F", 150, 120, false, 0, 0, false, 1));
      dataProcessor.listPatient(new Patient("five", 65, "M", 160, 190, true, 1, 0, false, 4));
    } catch(Exception e) {
      e.printStackTrace();
      fail("List patient method has failed");
    }
  }
  
  /**
   * Tests the vaccinate method from the data processor class
   * method fails if the removed patient isn't the expected one
   */
  @Test
  void vaccinatePatientTest() {
    DataProcessor dataProcessor = new DataProcessor();
    dataProcessor.listPatient(new Patient("zero", 25, "M", 180, 75, false, 0, 1, false, 3));
    dataProcessor.listPatient(new Patient("one", 35, "F", 160, 65, false, 2, 2, true, 3));
    dataProcessor.listPatient(new Patient("two", 31, "M", 160, 245, true, 2, 4, false, 3));
    
    if(!dataProcessor.vaccinate().getName().equals("two"))
      fail("Vaccinate method has failed");
  }  

  
  
  

}
