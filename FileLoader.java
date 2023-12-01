import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

public class FileLoader{
    public static String text = "";
    public static LinkedList<String> authors = new LinkedList<String>();

    public static Graph createGraph(int firstIndex, int lastIndex){
        Graph graph = new Graph();

        for (int i = firstIndex; i <= lastIndex; i++) {
            Path filePath = Path.of("./Documents/artigo_" + i + ".txt");
            try {
                text = Files.readString(filePath);

                //Authors
                String authors = PreProcessText.processText(text, "authors");
                String[] phrases = PreProcessText.splitPhrases(authors);
                String[] current_authors = PreProcessText.splitAuthors(phrases[phrases.length - 1]);
                for (String author : current_authors) {
                    graph.addAuthor(author.trim());
                }

                //Topics
                String topics = PreProcessText.processText(text, "topics");
                phrases = PreProcessText.splitPhrases(topics);
                String[] current_topics = PreProcessText.splitTopics(phrases[phrases.length - 2]);
                for (String topic : current_topics) {
                    graph.addTopic(topic.trim());
                }

                for (String author_name : current_authors) {
                    for (Graph.Author author : graph.authors) {
                        if(author.name == author_name){
                            for (String topic_name : current_topics) {
                                for (Graph.Topic topic : graph.topics) {
                                    if(topic.name == topic_name){
                                        graph.addEdge(author.name, topic.name, "author");
                                    }
                                }
                            }
                        }
                    }
                }

                for (int j = 1; j < graph.topics.size(); j++) {
                    if(graph.topics.get(j).name != ""){
                        for (int k = 0; k < current_topics.length; k++) {
                            graph.addTopic(current_topics[k]);
                            
                        }
                        if(j + 1 < graph.topics.size() - 1)
                        {
                            if(graph.topics.get(j + 1) != null && graph.topics.get(j + 1).name != ""){
                                graph.addEdge(graph.topics.get(j).name, graph.topics.get(j + 1).name, "topic");
                            }
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            
            

        }
        return graph;
    }
}