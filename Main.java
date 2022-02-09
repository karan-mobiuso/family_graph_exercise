
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws FileNotFoundException, IOException
    {
        String emailToBeSearched;
        System.out.print("Enter email of person whose data is required:");
        Scanner sc = new Scanner(System.in);
        emailToBeSearched=sc.nextLine();

        DataReaderClass rd = new DataReaderClass(emailToBeSearched);
        rd.readData();
    }
}