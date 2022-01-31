import java.util.ArrayList;
import java.util.HashMap;

public class CreateGraph {

    HashMap<String,Integer> personal_data = new HashMap<String,Integer>(); //store email and member id in hashmap  as key value pair
    ArrayList<String[]> relation_data = new ArrayList<String[]>(); //stores data of relations 
    int total_nodes=0;
    ArrayList<Edge>[] graph_of_relations; //graph which contains relations

    //constructor to initialize data structures
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

    //local data structure to store the data of edge between two nodes
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
    
    //insert a relation between two nodes
    public void insertRelation()
    {
        for(int i=0;i<relation_data.size();i++) //iterate over all the relations
        {
            String[] data_of_individual_relation = relation_data.get(i);

            int position_ofSourcePerson_in_graph=personal_data.get(data_of_individual_relation[0]); //get node number of source person in graph
            int position_ofDestinationPerson_in_graph=personal_data.get(data_of_individual_relation[2]); //get node number of destination person in graph

            Edge edge_from_source_to_destination = new Edge(data_of_individual_relation[0],data_of_individual_relation[2],data_of_individual_relation[1]); //create an edge from source to destination
            Edge edge_from_destination_to_source = new Edge(data_of_individual_relation[2],data_of_individual_relation[0],data_of_individual_relation[1]); //create an edge from destination to source

            graph_of_relations[position_ofSourcePerson_in_graph].add(edge_from_source_to_destination); //add an edge to source node
            graph_of_relations[position_ofDestinationPerson_in_graph].add(edge_from_destination_to_source); //add an edge to destination node
        }
    }

    public void displayTotalrelation(String email_to_be_searched)
    {
        System.out.println("Total relations of "+email_to_be_searched+" is "+graph_of_relations[personal_data.get(email_to_be_searched)].size()); //total sum of edges connected to person
    }

    public void displayExtendedFamilyCount(String email_to_be_searched)
    {
        int extended_family_count=0;
        ArrayList<String> list_of_family_relations = new ArrayList<String>(); //contains list of people who has family as relation with source person
        int index = personal_data.get(email_to_be_searched);//get node number whose relation is needed
        for(int i=0;i<graph_of_relations[index].size();i++) //iterate over all the edges connected directly to person whose details are needed
        {
            if(graph_of_relations[index].get(i).relation_type.equals("FAMILY")) //if relation is family then 
            {
                extended_family_count++; //increase counter
                list_of_family_relations.add(graph_of_relations[index].get(i).email_of_destination_person);//add that email in list of family as relation
            }
        }
        
        for(int i=0;i<list_of_family_relations.size();i++) //iterate over all nodes of list of family relation
        {
            for(int j=0;j<graph_of_relations[personal_data.get(list_of_family_relations.get(i))].size();j++) //retreive the node number of email stored from personal data and traverse it thoroughly
            {
                if(graph_of_relations[personal_data.get(list_of_family_relations.get(i))].get(j).relation_type.equals("FAMILY") && !list_of_family_relations.contains(graph_of_relations[personal_data.get(list_of_family_relations.get(i))].get(j).email_of_destination_person)) //check if relation is family and that person is not added in list of family relations
                {
                    extended_family_count++; //increase the ocunt
                    list_of_family_relations.add(graph_of_relations[personal_data.get(list_of_family_relations.get(i))].get(j).email_of_destination_person); // add that person in list
                }
            }
        }
        System.out.println("Extended family count of "+email_to_be_searched+" is "+extended_family_count); //print the count

    }

}

        
