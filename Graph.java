import java.util.LinkedList;

public class Graph {
    LinkedList<Node> nodes = new LinkedList<Node>(); //List of nodes

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
        String originWord; //The word the edge originates from
        String linkedWord; //The word the edge links to
        int linkOccurencies; //The number of links between the two words

        Edge(String origin, String linkedTo){
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
            newNode.wordOccurencies += 1;
            nodes.add(newNode);
        }
    }
    public void addEdge(String originWord, String linkedTo){
        int additions = 0;
        for (Node node: nodes){
            if(node.word.equals(originWord)){
                for (Edge adjacent: node.adjacentEdges) {
                    if(adjacent.originWord.equals(originWord)){
                        if(adjacent.linkedWord.equals(linkedTo)){
                            adjacent.linkOccurencies += 1;
                            additions++;
                            break;
                        }
                        else{
                            Edge newEdge = new Edge(originWord, linkedTo);
                            node.adjacentEdges.add(newEdge);
                            newEdge.linkOccurencies += 1;
                            additions++;
                            break;
                        }
                    }
                }
            }
        }
        if(additions == 0){
            Edge newEdge = new Edge(originWord, linkedTo);
            newEdge.linkOccurencies += 1;
            for (Node node : nodes) {
                if(node.word == originWord){
                    node.adjacentEdges.add(newEdge);
                }
            }
        }
    }
}