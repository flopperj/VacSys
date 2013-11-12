import java.io.IOException;

public class VacSysTest {

	public static void main(String[] args) {
		VacSys v;
		try {
			v = new VacSys("src/testdata/test1K.csv");

			for (Patient p : v.system.getHeap()) {
				System.out.println(p);
				System.out.println("testomg!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
