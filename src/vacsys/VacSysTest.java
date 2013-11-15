package vacsys;
import java.io.IOException;

public class VacSysTest {

	public static void main(String[] args) {
		VacSys v;
		try {
			v = new VacSys("src/testdata/test10K.csv");
			
			// Testing removal of requests
			//=============================
			System.out.println("Testing the removal of first 10 requests: \n");
			v.remove(10, "src/testdata/test1K-results.csv");
			
			System.out.println("\n\nTesting the removal of second 10 requests: \n");
			v.remove(10, "src/testdata/test1K-results.csv");
			
			System.out.println("\n\nTesting the removal of third 10 requests: \n");
			v.remove(10, "src/testdata/test1K-results.csv");
			
			// Testing of insertion of new requests
			//=====================================
			System.out.println("\n\nTesting Insertion of new data.");
			v.insert("James Arama", 21, "12601");
			System.out.println("New Size should be similar to as before when we removed a request: " );
			v.remove(1, "src/testdata/test1K-results.csv");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
