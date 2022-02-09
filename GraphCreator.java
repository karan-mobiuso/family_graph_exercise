import java.util.ArrayList;
import java.util.HashMap;

public class GraphCreatorClass {

    HashMap<String,Integer> personalData = new HashMap<String,Integer>(); //store email and member id in hashmap  as key value pair
    ArrayList<String[]> relationData = new ArrayList<String[]>(); //stores data of relations 
    int totalNodes=0;
    ArrayList<Edge>[] graphOfRelations; //graph which contains relations

    //constructor to initialize data structures
    public GraphCreatorClass(HashMap<String,Integer> personalData,ArrayList<String[]> relationData,int totalNodes){
        this.personalData = personalData;
        this.relationData=relationData;
        this.totalNodes=totalNodes;
        graphOfRelations=new ArrayList[totalNodes];
        for(int i=0;i<totalNodes;i++)
        {
            graphOfRelations[i]=new ArrayList<>();
        }
    }

    //local data structure to store the data of edge between two nodes
    static class Edge{
        String emailOfSourcePerson;
        String emailOfDestinationPerson;
        String relationType;

        public Edge(String emailOfSourcePerson,String emailOfDestinationPerson,String relationType)
        {
            this.emailOfSourcePerson=emailOfSourcePerson;
            this.emailOfDestinationPerson=emailOfDestinationPerson;
            this.relationType=relationType;
        }
    }
    
    //insert a relation between two nodes
    public void insertRelation()
    {
        for(int i=0;i<relationData.size();i++) //iterate over all the relations
        {
            String[] dataOfIndividualRelation = relationData.get(i);

            int positionOfSourcePersonInGraph=personalData.get(dataOfIndividualRelation[0]); //get node number of source person in graph
            int positionOfDestinationPersonInGraph=personalData.get(dataOfIndividualRelation[2]); //get node number of destination person in graph

            Edge edgeFromSourceToDestination = new Edge(dataOfIndividualRelation[0],dataOfIndividualRelation[2],dataOfIndividualRelation[1]); //create an edge from source to destination
            Edge edgeFromDestinationToSource = new Edge(dataOfIndividualRelation[2],dataOfIndividualRelation[0],dataOfIndividualRelation[1]); //create an edge from destination to source

            graphOfRelations[positionOfSourcePersonInGraph].add(edgeFromSourceToDestination); //add an edge to source node
            graphOfRelations[positionOfDestinationPersonInGraph].add(edgeFromDestinationToSource); //add an edge to destination node
        }
    }

    public void displayTotalRelationCount(String emailToBeSearched)
    {
        System.out.println("Total relations of "+emailToBeSearched+" is "+graphOfRelations[personalData.get(emailToBeSearched)].size()); //total sum of edges connected to person
    }

    public void displayExtendedFamilyCount(String emailToBeSearched)
    {
        int extendedFamilyCount=0;
        ArrayList<String> listOfFamilyCount = new ArrayList<String>(); //contains list of people who has family as relation with source person
        int index = personalData.get(emailToBeSearched);//get node number whose relation is needed
        for(int i=0;i<graphOfRelations[index].size();i++) //iterate over all the edges connected directly to person whose details are needed
        {
            if(graphOfRelations[index].get(i).relationType.equals("FAMILY")) //if relation is family then 
            {
                extendedFamilyCount++; //increase counter
                listOfFamilyCount.add(graphOfRelations[index].get(i).emailOfDestinationPerson);//add that email in list of family as relation
            }
        }
        
        for(int i=0;i<listOfFamilyCount.size();i++) //iterate over all nodes of list of family relation
        {
            for(int j=0;j<graphOfRelations[personalData.get(listOfFamilyCount.get(i))].size();j++) //retreive the node number of email stored from personal data and traverse it thoroughly
            {
                if(graphOfRelations[personalData.get(listOfFamilyCount.get(i))].get(j).relationType.equals("FAMILY") && !listOfFamilyCount.contains(graphOfRelations[personalData.get(listOfFamilyCount.get(i))].get(j).emailOfDestinationPerson)) //check if relation is family and that person is not added in list of family relations
                {
                    extendedFamilyCount++; //increase the ocunt
                    listOfFamilyCount.add(graphOfRelations[personalData.get(listOfFamilyCount.get(i))].get(j).emailOfDestinationPerson); // add that person in list
                }
            }
        }
        System.out.println("Extended family count of "+emailToBeSearched+" is "+extendedFamilyCount); //print the count

    }

}

        
