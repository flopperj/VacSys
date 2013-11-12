import java.io.IOException;

public class VacSysTest {

	public static void main(String[] args) {
		VacSys v;
		try {
			v = new VacSys("src/testdata/test1K.csv");
			
			System.out.println("Testing the removal of first 10 requests: \n");
			v.remove(10, "src/testdata/test1K-results.csv");
			
			System.out.println("\n\nTesting the removal of second 10 requests: \n");
			v.remove(10, "src/testdata/test1K-results.csv");
			
			System.out.println("\n\nTesting the removal of third 10 requests: \n");
			v.remove(10, "src/testdata/test1K-results.csv");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
