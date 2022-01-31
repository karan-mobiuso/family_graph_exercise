import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadData {
        String email_to_be_searched;
    public ReadData(String email_to_be_searched)
    {
        this.email_to_be_searched=email_to_be_searched;
    }     
    public void readData() throws FileNotFoundException, IOException
    {
        int total_nodes=0;
        HashMap<String,Integer> person_id = new HashMap<>();
        //HashMap<String,String[]> relation_data = new HashMap<>();
        ArrayList<String[]> relation_data = new ArrayList<String[]>();
        File people_file = new File("E:\\COMPANIES\\MOBIUSO\\TRAINING\\FAMILYGRAPH_EXERCISE\\familygraph_exercise\\familygraph_exercise\\src\\test\\resources\\people.csv");
        
        try (BufferedReader br = new BufferedReader(new FileReader(people_file))) 
        {
                String data="";
                String[] personal_data;
                while ((data=br.readLine())!=null)
                {
                        personal_data=data.split(","); //contains email ,email and age
                        person_id.put(personal_data[1],total_nodes);
                        total_nodes++; 
                }
        }

        File relationship_file = new File("E:\\COMPANIES\\MOBIUSO\\TRAINING\\FAMILYGRAPH_EXERCISE\\familygraph_exercise\\familygraph_exercise\\src\\test\\resources\\relationships.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(relationship_file))) 
        {
                String data="";
                String[] relationship_data;

                while ((data=br.readLine())!=null)
                {
                        if(data.length()==2)
                        {
                                continue;
                        }
                        else
                        {
                                relationship_data=data.split(",");
                                relation_data.add(relationship_data);   
                        }
                }
        }
        CreateGraph sendMembers = new CreateGraph(person_id,relation_data,total_nodes);
        sendMembers.insertRelation();
        sendMembers.displayTotalrelation(email_to_be_searched);
        sendMembers.displayExtendedFamilyCount(email_to_be_searched);
    }
}
