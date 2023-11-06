import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String text = "";
        
        Scanner sysIn = new Scanner(System.in);
        System.out.println("Type a number between 1 and 92 to read a SINGLE file; \nType 0 to read MULTIPLE files: ");
        int choice = sysIn.nextInt();
        
        if(choice != 0){
            if(choice < 0){
                System.out.println("The number is too small, switching it to 1...");
                choice = 1;
            }
            else if(choice > 92){
                System.out.println("The number is too large, switching it to 92...");{
                    choice = 92;
                }
            }
            text = FileLoader.loadSingleFile(choice);
            
        }
        else if(choice == 0){
            System.out.println("Type TWO numbers between 1 and 92, you will read ALL files from the FIRST NUMBER to the SECOND NUMBER you entered: \nType 0 to read ALL FILES");
            int firstIndex = sysIn.nextInt();
            if(firstIndex == 0){
                text = FileLoader.loadMultipleFiles(1, 92);
            }
            else{
                
                System.out.println("First number: " + firstIndex + "\nType the second number: ");
                int lastIndex = sysIn.nextInt();
               
                if(firstIndex > lastIndex){
                    System.out.println("The first number is greater than the second, switching...");
                    int temp = firstIndex;
                    firstIndex = lastIndex;
                    lastIndex = temp;
                }
                if(firstIndex < 0 || lastIndex > 92){
                    if(firstIndex < 0 && lastIndex > 92){
                        System.out.println("There are no files with those numbers, switching the first to 1 and the second to 92...");
                        firstIndex = 1;
                        lastIndex = 92;
                    }
                    else{
                        if(firstIndex < 0){
                            System.out.println("The first number is too small, switching it to 1...");
                            firstIndex = 1;
                        }
                        if(lastIndex > 92){
                            System.out.println("The second number is too large, switching it to 92...");
                            lastIndex = 92;
                        }
    
                    }
    
                }
                text = FileLoader.loadMultipleFiles(firstIndex, lastIndex);
                
            }
        }
        sysIn.close();

        System.out.println("Processing...");

        text = PreProcessText.processText(text);

        //String[] splitPhrases = PreProcessText.splitPhrases(text);

        String[] splitWords = PreProcessText.splitWords(text);

        splitWords = PreProcessText.removeStopWords(splitWords);


        Graph graph = new Graph();

        for (int i = 0; i < splitWords.length; i++) {
            if(splitWords[i] != ""){
                graph.addNode(splitWords[i]);
                if(i + 1 < splitWords.length - 1)
                {
                    if(splitWords[i + 1] != null && splitWords[i + 1] != ""){
                        graph.addEdge(splitWords[i], splitWords[i + 1]);
                    }
                }
            }
        }

        graph.cleanUpGraph();
        graph.sortGraph();

        for (Graph.Node node: graph.nodes) {
            System.out.println("------------------------------------------------------");
            System.out.println("Word: " + node.word + " | " + "Occurrencies: " + node.wordOccurrences);
            for (Graph.Edge adjacent : node.adjacentEdges){
                System.out.println("Edge origin: " + adjacent.originWord + " | " + "linked to: " + adjacent.linkedWord + " | " + "Link occurencies: " + adjacent.linkOccurrences);
            }        
        }

    }
}
