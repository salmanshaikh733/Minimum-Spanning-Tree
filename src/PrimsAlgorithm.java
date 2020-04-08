import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PrimsAlgorithm {

    static int vertices;
    static int maxInt = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        //define new scanner
        Scanner newScanner;
        //scanner reads in the new file
        newScanner = new Scanner(new FileReader("input"));
        //get the number of vertices and store them
        vertices = Integer.parseInt(newScanner.nextLine());
        //create an array list to store the edges in a linked list fashion
        ArrayList<LinkedList<Edge>> edgeList = new ArrayList<LinkedList<Edge>>();
        //loop through for number of vertices adding the number of edges
        for (int i = 0; i <= vertices; i++) {
            edgeList.add(new LinkedList<Edge>());
        }
        //iterate through file and get each endpoint and weight
        while (newScanner.hasNext() == true) {
            String s = newScanner.nextLine();

            int x, y, w;
            //use  a string tokenizer to get each value from the text file
            StringTokenizer newToken = new StringTokenizer(s);
            x = Integer.parseInt(newToken.nextToken());
            y = Integer.parseInt(newToken.nextToken());
            w = Integer.parseInt(newToken.nextToken());
            //create the edge and the backwards edge
            Edge newEdge = new Edge(x, y, w);
            Edge backWardsEdge = new Edge(x, y, w);
            //add the edges to edge lsit
            edgeList.get(x).addLast(newEdge);
            edgeList.get(y).addLast(backWardsEdge);
        }

        //iterate through the number of vertices and output adjancent edges with their weights for each edge
        for (int i = 1; i <= vertices; i++) {

            System.out.print("Edges Adjacent to: " + edgeList.get(i).get(0).getFirstEnd() + ": ");

            for (int j = 0; j < edgeList.get(i).size(); j++) {
                System.out.print(edgeList.get(i).get(j).getLastEnd() + " (weight (" + edgeList.get(i).get(j).getWeight() + ")), ");
            }
            System.out.println();

        }

        //define the root node
        int rNode = edgeList.get(1).get(0).getFirstEnd();
        System.out.println();
        //call the minium spanning tree function.
        minSpanTree(edgeList, rNode);

    }

    //minimum spanning tree function
    public static void minSpanTree(ArrayList<LinkedList<Edge>> Graph, int root) {
        System.out.println("---------------------");
        System.out.println("MINIMUM SPANNING TREE");
        System.out.println("---------------------");
        //define the total weight of edges
        int weightTotal = 0;

        //have an array of edges
        Edge keys[] = new Edge[vertices + 1];
        //have an array for the ids
        int id[] = new int[vertices + 1];

        //loop through the keys array and assign it an edge with a max weight and make the weight -1
        for (int i = 0; i <= vertices; i++) {

            Edge newEdge = new Edge(0, 0, maxInt);
            keys[i] = newEdge;
            id[i] = -1;
        }

        //set values for root node
        keys[root].setWeight(0);
        keys[root].setFirstEnd(root);
        keys[root].setLastEnd(root);
        id[root] = root;

        //create the min heap
        minHeap newHeap = new minHeap();
        newHeap.heap(keys, keys.length);

        Edge minimumEdge = new Edge();

        //create the minimum spanning tree and output the results.
        while (((minimumEdge = newHeap.delete_min()).getWeight()) != maxInt) {

            if (minimumEdge.getFirstEnd() != 0 && minimumEdge.getLastEnd() != 0) {

                LinkedList<Edge> arrayList = Graph.get(minimumEdge.getLastEnd());

                for (int i = 0; i < arrayList.size(); i++) {

                    Edge edgeList = arrayList.get(i);

                    if ((newHeap.in_heap(edgeList)) == false && edgeList.getWeight() < keys[edgeList.getLastEnd()].getWeight()) {

                        id[edgeList.getLastEnd()] = edgeList.getFirstEnd();
                        keys[edgeList.getLastEnd()] = edgeList;
                        newHeap.decrease_key(edgeList.getLastEnd(), edgeList.getWeight());
                    }
                }
                if (minimumEdge.getWeight() == 0) {
                    System.out.println("Root Node found and added");
                } else {
                    weightTotal = weightTotal + minimumEdge.getWeight();
                    System.out.println("Added Node: " + minimumEdge.getLastEnd() + ": Weight " + minimumEdge.getWeight());
                }
            }
        }
        //output total weight.
        System.out.println("Total weight: " + weightTotal);
    }
}
