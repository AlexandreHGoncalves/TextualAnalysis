import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String text = "";
        
        Scanner sysIn = new Scanner(System.in);
        System.out.println("Type a number between 1 and 92 to read a SINGLE file; \nType 0 to read MULTIPLE files: ");
        int choice = sysIn.nextInt();
        
        if(choice > 0){
            text = FileLoader.loadSingleFile(choice);
            
        }
        else if(choice == 0){
            System.out.println("Type TWO numbers between 1 and 92, you will read ALL files from the FIRST NUMBER to the SECOND NUMBER you entered: ");
            int firstIndex = sysIn.nextInt();
            System.out.println("First number: " + firstIndex + "\nType the second number: ");
            int lastIndex = sysIn.nextInt();
            if(firstIndex > lastIndex){
                System.out.println("The first number is greater than the second, switching...");
                int temp = firstIndex;
                firstIndex = lastIndex;
                lastIndex = temp;
            }
            text = FileLoader.loadMultipleFiles(firstIndex, lastIndex);
            
        }
        sysIn.close();

        text = PreProcessText.processText(text);

        String[] splitPhrases = PreProcessText.splitPhrases(text);

        String[] splitWords = PreProcessText.splitWords(text);

        for (String phrase: splitWords) {
            System.out.println(phrase);
        }


    }
}
