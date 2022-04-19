package onelogin.codingchallenge;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestJunit {
	
   CodeChallenge codeChallenge = new CodeChallenge();
   
   
   @Test
   public void testDoMath() {
	  //Correct Answer
      assertEquals("3_4/5",codeChallenge.add(3, 4, 1, 5));
      
      //Wrong Answer
      assertEquals("1_4/5",codeChallenge.add(3, 4, 1, 5));
   }
}