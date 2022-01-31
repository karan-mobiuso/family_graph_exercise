import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class CreateGraph {

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

    HashMap<String,Integer> personal_data = new HashMap<String,Integer>();
    ArrayList<String[]> relation_data = new ArrayList<String[]>();
    String email_source_person;
    String[] email_destination_person;
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
        int extended_family_count=0;
        Boolean visited[]= new Boolean[total_nodes];
        extended_family_count=getExtendedFamilyCount(email_to_be_searched,extended_family_count,visited,email_to_be_searched);

        /*ArrayList<Edge> list = graph_of_relations[personal_data.get(email_to_be_searched)];
        for(int j=0;j<list.size();j++)
        {
            if(list.get(j).relation_type.equals("FAMILY"))
            {
                extended_family_count++;
            }
        }*/
        System.out.println("Extended family count for "+email_to_be_searched+" is "+extended_family_count);
    }

    public int getExtendedFamilyCount(String email_to_be_searched,int extended_family_count,Boolean[] visited,String visited_email)
    {
        visited[personal_data.get(visited_email)]=true;
        Iterator<CreateGraph.Edge> graph_iterator= graph_of_relations[personal_data.get(email_to_be_searched)].listIterator();

        while(graph_iterator.hasNext())
        {
            Edge ed=graph_iterator.next();
            
            if(visited[personal_data.get(ed.email_of_source_person)].equals("null") && ed.relation_type.equals("FAMILY") && ed.email_of_source_person.equals(email_to_be_searched))
            {
                extended_family_count++;
                System.out.println(ed.relation_type+" "+ed.email_of_source_person+" "+ed.email_of_destination_person+" "+email_to_be_searched);
                getExtendedFamilyCount(email_to_be_searched, extended_family_count, visited, ed.email_of_source_person);
            }
            else
            {
                break;
            }
        }
        for(int i=0;i<visited.length;i++)
        {
            System.out.print(visited[i]+" ");
        }
        return extended_family_count;
    }

}

        
