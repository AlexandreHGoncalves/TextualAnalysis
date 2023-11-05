import java.util.LinkedList;

public class Graph {
    LinkedList<Node> nodes = new LinkedList<Node>(); //List of nodes

    public class Node{
        String word; //The word the node represents
        int wordOccurrences; //The amount of times the word appears in the text
        LinkedList<Edge> adjacentEdges = new LinkedList<Edge>(); //The adjecencies of the word
        
        Node(String word){
            this.word = word;
            adjacentEdges = new LinkedList<Edge>();
        }
    }


    public static class Edge{
        String originWord; //The word the edge originates from
        String linkedWord; //The word the edge links to
        int linkOccurrences; //The number of links between the two words

        Edge(String origin, String linkedTo){
            this.originWord = origin;
            this.linkedWord = linkedTo;
        }
    }

    public void addNode(String word){
        int additions = 0;
        for (Node node: nodes) {
            if(node.word.equals(word)){
                node.wordOccurrences += 1;
                additions++;
            }
        }
        if(additions == 0){
            Node newNode = new Node(word);
            newNode.wordOccurrences += 1;
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
                            adjacent.linkOccurrences += 1;
                            additions++;
                            break;
                        }
                        else{
                            Edge newEdge = new Edge(originWord, linkedTo);
                            node.adjacentEdges.add(newEdge);
                            newEdge.linkOccurrences += 1;
                            additions++;
                            break;
                        }
                    }
                }
            }
        }
        if(additions == 0){
            Edge newEdge = new Edge(originWord, linkedTo);
            newEdge.linkOccurrences += 1;
            for (Node node : nodes) {
                if(node.word.equals(originWord)){
                    node.adjacentEdges.add(newEdge);
                }
            }
        }
    }

    public void cleanUpGraph(){
        LinkedList<Node> nodesToDelete = new LinkedList<Node>();
        LinkedList<Edge> edgesToDelete = new LinkedList<Edge>();
        for (Node node : nodes) {
            for(Edge adjacentEdge: node.adjacentEdges){
                if(adjacentEdge.linkOccurrences < 2){
                    edgesToDelete.add(adjacentEdge);
                }
            }
            node.adjacentEdges.removeAll(edgesToDelete);
            if (node.wordOccurrences < 2 || node.adjacentEdges.isEmpty()) {
                nodesToDelete.add(node);
            }
        }

        nodes.removeAll(nodesToDelete);

    }

    public void sortGraph(){
        boolean swapped;
        
        do {
            swapped = false;
            for (int i = 1; i < nodes.size(); i++) {
                if (getLinkOccurrences(nodes.get(i - 1)) < getLinkOccurrences(nodes.get(i))) {
                    Node temp = nodes.get(i - 1);
                    nodes.set(i - 1, nodes.get(i));
                    nodes.set(i, temp);
                    swapped = true;
                }
            }
        } while (swapped);
    }

    private int getLinkOccurrences(Node node) {
        int totalOccurrences = 0;
        for (Edge edge: node.adjacentEdges) {
            totalOccurrences += edge.linkOccurrences;
        }
        return totalOccurrences;
    }

}