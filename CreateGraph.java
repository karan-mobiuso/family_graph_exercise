import java.util.ArrayList;
import java.util.HashMap;

public class CreateGraph {

    HashMap<String,Integer> personal_data = new HashMap<String,Integer>(); //store email and member id in hashmap  as key value pair
    ArrayList<String[]> relation_data = new ArrayList<String[]>();
    int total_nodes=0;
    ArrayList<Edge>[] graph_of_relations;

    public CreateGraph(HashMap<String,Integer> personal_data,ArrayList<String[]> relation_data,int total_nodes){
        this.personal_data = personal_data;
        this.relation_data=relation_data;
        this.total_nodes=total_nodes;
        graph_of_relations=new ArrayList[total_nodes];
        for(int i=0;i<total_nodes;i++)
        {
            graph_of_relations[i]=new ArrayList<>();
        }
    }

    static class Edge{
        String email_of_source_person;
        String email_of_destination_person;
        String relation_type;

        public Edge(String email_of_source_person,String email_of_destination_person,String relation_type)
        {
            this.email_of_source_person=email_of_source_person;
            this.email_of_destination_person=email_of_destination_person;
            this.relation_type=relation_type;
        }
    }
    
    public void insertRelation()
    {
        for(int i=0;i<relation_data.size();i++)
        {
            String[] data_of_individual_relation = relation_data.get(i);

            int position_ofSourcePerson_in_graph=personal_data.get(data_of_individual_relation[0]); //email of source person
            int position_ofDestinationPerson_in_graph=personal_data.get(data_of_individual_relation[2]); //email of destination person

            Edge edge_from_source_to_destination = new Edge(data_of_individual_relation[0],data_of_individual_relation[2],data_of_individual_relation[1]);
            Edge edge_from_destination_to_source = new Edge(data_of_individual_relation[2],data_of_individual_relation[0],data_of_individual_relation[1]);

            graph_of_relations[position_ofSourcePerson_in_graph].add(edge_from_source_to_destination);
            graph_of_relations[position_ofDestinationPerson_in_graph].add(edge_from_destination_to_source);
        }
    }

    public void displayTotalrelation(String email_to_be_searched)
    {
        System.out.println("Total relations of "+email_to_be_searched+" is "+graph_of_relations[personal_data.get(email_to_be_searched)].size());
    }

    public void displayExtendedFamilyCount(String email_to_be_searched)
    {
        int extended_family_count=0;//graph_of_relations[personal_data.get(email_to_be_searched)].size();
        ArrayList<String> list_of_family_relations = new ArrayList<String>();
        int index = personal_data.get(email_to_be_searched);
        for(int i=0;i<graph_of_relations[index].size();i++)
        {
            if(graph_of_relations[index].get(i).relation_type.equals("FAMILY"))
            {
                extended_family_count++;
                list_of_family_relations.add(graph_of_relations[index].get(i).email_of_destination_person);
            }
        }
        
        for(int i=0;i<list_of_family_relations.size();i++)
        {
            for(int j=0;j<graph_of_relations[personal_data.get(list_of_family_relations.get(i))].size();j++)
            {
                if(graph_of_relations[personal_data.get(list_of_family_relations.get(i))].get(j).relation_type.equals("FAMILY") && !list_of_family_relations.contains(graph_of_relations[personal_data.get(list_of_family_relations.get(i))].get(j).email_of_destination_person))
                {
                    System.out.println(graph_of_relations[personal_data.get(list_of_family_relations.get(i))].get(j).email_of_destination_person);
                    extended_family_count++;
                    list_of_family_relations.add(graph_of_relations[personal_data.get(list_of_family_relations.get(i))].get(j).email_of_destination_person);
                }
            }
        }
        System.out.println("Extended family count of "+email_to_be_searched+" is "+extended_family_count);

    }

}

        
