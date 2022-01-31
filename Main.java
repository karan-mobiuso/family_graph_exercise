
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws FileNotFoundException, IOException
    {
        String email_to_be_searched;
        System.out.print("Enter email of person whose data is required:");
        Scanner sc = new Scanner(System.in);
        email_to_be_searched=sc.nextLine();

        ReadData rd = new ReadData(email_to_be_searched);
        rd.readData();
    }
}