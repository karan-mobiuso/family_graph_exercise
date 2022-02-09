import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DataReaderClass {
    String emailToBeSearched;
    final String peopleFilePath="E:\\COMPANIES\\MOBIUSO\\TRAINING\\FAMILYGRAPH_EXERCISE\\familygraph_exercise\\familygraph_exercise\\src\\test\\resources\\people.csv";
    final String relationshipFilePath="E:\\COMPANIES\\MOBIUSO\\TRAINING\\FAMILYGRAPH_EXERCISE\\familygraph_exercise\\familygraph_exercise\\src\\test\\resources\\relationships.csv";
    public DataReaderClass(String emailToBeSearched)
    {
        this.emailToBeSearched=emailToBeSearched;
    }
    //reads data from both files
    public void readData() throws FileNotFoundException, IOException
    {
        int memberId=0;
        HashMap<String,Integer> personId = new HashMap<>(); //for mapping a mail id of a person to a specific memberId(integer)
        ArrayList<String[]> relationData = new ArrayList<String[]>(); //to store relation among people 
        File peopleFile = new File(peopleFilePath);
        
        try (BufferedReader br = new BufferedReader(new FileReader(peopleFile))) 
        {
                String data="";
                String[] personalData;
                while ((data=br.readLine())!=null)
                {
                        personalData=data.split(","); //contains email ,email and age
                        personId.put(personalData[1],memberId); //email and member id pushed in hashmap
                        memberId++; 
                }
        }

        File relationshipFile = new File(relationshipFilePath);
        try (BufferedReader br = new BufferedReader(new FileReader(relationshipFile))) 
        {
                String data="";
                String[] relationshipData;

                while ((data=br.readLine())!=null)
                {
                        if(data.length()==2) //skip blank lines
                        {
                                continue;
                        }
                        else
                        {
                                relationshipData=data.split(",");
                                relationData.add(relationshipData);  
                        }
                }
        }
        GraphCreatorClass sendData = new GraphCreatorClass(personId,relationData,memberId); //send personal,relationship and total nodes via constructor
        sendData.insertRelation(); 
        sendData.displayTotalRelationCount(emailToBeSearched);
        sendData.displayExtendedFamilyCount(emailToBeSearched);
    }
}
