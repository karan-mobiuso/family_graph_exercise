# family_graph_exercise
A graph data structure based exercise which shows the total relations of a person and total relations of type "family" with other people in the network/graph.
Step-1:
  Read two csv files namely "people.csv" and "relationships.csv"
  Create a **hashmap** named **personal_data** which stores **email of user** as key and a **specific number** as its **value**;
  Convert the data in **form convinient to work** with like **ignore blank rows** etc.
  Send the data to class **CreateGraph** to create a graph
Step-2
  In class **CreateGraph**
    Create a **hashmap** to **store** the **personal data** sent from step-1
    Create an **arraylist** of **string array** to store **relation between entities**
    Create a **nested arraylist** which will **act** as a **graph**
    Create a local **data structuire** named **"Edge"** to store the **details of edge between two nodes**
    Initialize the graph with total number of people in people file and adjcency list for all of them which will be empty
    Create a **insertRelation()** method which will **read relation_data** and will create **edge between source and destination nodes**
    Create another method **displayTotalrelation()** which will show the **total relation an individual have**
    Create a method **displayExtendedFamilyCount()** that displays **total relations of type "family"** an individual have
