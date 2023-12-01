import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        
        Scanner sysIn = new Scanner(System.in);
        System.out.println("Type TWO numbers between 1 and 60, you will read ALL files from the FIRST NUMBER to the SECOND NUMBER you entered: \nType 0 to read ALL FILES");
        int firstIndex = sysIn.nextInt();


        if(firstIndex == 0){
            graph = FileLoader.createGraph(1, 60);
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
                if(firstIndex < 0 || lastIndex > 60){
                    if(firstIndex < 0 && lastIndex > 60){
                        System.out.println("There are no files with those numbers, switching the first to 1 and the second to 60...");
                        firstIndex = 1;
                        lastIndex = 60;
                    }
                    else{
                        if(firstIndex < 0){
                            System.out.println("The first number is too small, switching it to 1...");
                            firstIndex = 1;
                        }
                        if(lastIndex > 60){
                            System.out.println("The second number is too large, switching it to 60...");
                            lastIndex = 60;
                        }
    
                    }
    
                }
                graph = FileLoader.createGraph(firstIndex, lastIndex);
                
        }
        sysIn.close();

        System.out.println("Processing...");

        graph.cleanUpGraph();
        graph.sortGraph();
        System.out.println("Relevant authors:\n----------------------------------");
        for (Graph.Author author : graph.authors) {
                System.out.println("Author: " + author.name + " Contributed to: " + author.authorContributions + " articles.");
        }

        System.out.println("Relevant Articles:\n------------------------------------");
        for (Graph.Topic topic : graph.topics){
            System.out.println("Topic: " + topic.name + " has occurred: " + topic.topicOccurrences + " times.");
        }
    }
}
