import java.util.LinkedList;

public class Graph {
    LinkedList<Node> nodes = new LinkedList<Node>(); //List of nodes
    int nNodes; //The number of nodes
    
    public class Node{
        String word; //The word the node represents
        int wordOccurencies; //The amount of times the word appears in the text
        LinkedList<Edge> adjacentEdges = new LinkedList<Edge>(); //The adjecencies of the word
        
        Node(String word){
            this.word = word;
            adjacentEdges = new LinkedList<Edge>();
        }
    }


    public static class Edge{
        int edgeIndex;
        String originWord; //The word the edge originates from
        String linkedWord; //The word the edge links to
        int linkOccurencies; //The number of links between the two words

        Edge(int index, String origin, String linkedTo){
            this.edgeIndex = index;
            this.originWord = origin;
            this.linkedWord = linkedTo;
        }
    }

    public void addNode(String word){
        int additions = 0;
        for (Node node: nodes) {
            if(node.word.equals(word)){
                node.wordOccurencies += 1;
                additions++;
            }
        }
        if(additions == 0){
            Node newNode = new Node(word);
            nodes.add(newNode);
        }
    }
    /*
    public static void addEdge(Node node, int edgeIndex, String originWord, String linkedTo){

        if(node.adjacencies[edgeIndex].equals(originWord)){

        }
        Edge newEdge = new Edge(edgeIndex, originWord, linkedTo);
        graph.adjacencies[edgeIndex].add(newEdge);
    }
    */

}
