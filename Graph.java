import java.util.LinkedList;

public class Graph {

    LinkedList<Author> authors = new LinkedList<Author>();
    LinkedList<Topic> topics = new LinkedList<Topic>();

    public class Author{
        String name;
        int authorContributions;
        LinkedList<Edge> contributions = new LinkedList<Edge>();

        Author(String author){
            this.name = author;
            contributions = new LinkedList<Edge>();
        }
    }

    public class Topic{
        String name;
        int topicOccurrences;
        LinkedList<Edge> adjacentEdges = new LinkedList<Edge>();

        Topic(String topic){
            this.name = topic;
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

    public void addAuthor(String current_author){
        int additions = 0;
        for (Author author: authors){
            if(author.name.equals(current_author)){
                author.authorContributions += 1;
                additions++;
            }
        }
        if(additions == 0){
            Author newAuthor = new Author(current_author);
            newAuthor.authorContributions += 1;
            authors.add(newAuthor);
        }
    }

    public void addTopic(String current_topic){
        int additions = 0;
        for (Topic topic: topics){
            if(topic.name.equals(current_topic)){
                topic.topicOccurrences += 1;
                additions++;
            }
        }
        if(additions == 0){
            Topic newTopic = new Topic(current_topic);
            newTopic.topicOccurrences += 1;
            topics.add(newTopic);
        }
    }

    public void addEdge(String originWord, String linkedTo, String type){
        int additions = 0;
        if(type == "author"){
            for (Author author: authors){
                if(author.name.equals(originWord)){
                    for (Edge contribution: author.contributions) {
                        if(contribution.originWord.equals(originWord)){
                            if(contribution.linkedWord.equals(linkedTo)){
                                contribution.linkOccurrences += 1;
                                additions++;
                                break;
                            }
                            else{
                                Edge newContribution = new Edge(originWord, linkedTo);
                                author.contributions.add(newContribution);
                                newContribution.linkOccurrences += 1;
                                additions++;
                                break;
                            }
                        }
                    }
                }
            }
            if(additions == 0){
                Edge newContribution = new Edge(originWord, linkedTo);
                newContribution.linkOccurrences += 1;
                for (Author author : authors) {
                    if(author.name.equals(originWord)){
                        author.contributions.add(newContribution);
                    }
                }
            }
        }
        else if(type == "topic"){
            for (Topic topic: topics){
                if(topic.name.equals(originWord)){
                    for (Edge adjacency: topic.adjacentEdges) {
                        if(adjacency.originWord.equals(originWord)){
                            if(adjacency.linkedWord.equals(linkedTo)){
                                adjacency.linkOccurrences += 1;
                                additions++;
                                break;
                            }
                            else{
                                Edge newEdge = new Edge(originWord, linkedTo);
                                topic.adjacentEdges.add(newEdge);
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
                for (Topic topic : topics) {
                    if(topic.name.equals(originWord)){
                        topic.adjacentEdges.add(newEdge);
                    }
                }
            }
        }
        
    }

    public void cleanUpGraph(){
        LinkedList<Author> authorsToDelete = new LinkedList<Author>();
        LinkedList<Topic> topicsToDelete = new LinkedList<Topic>();
        LinkedList<Edge> edgesToDelete = new LinkedList<Edge>();

        for (Author author : authors) {
            if (author.authorContributions < 2 || author.contributions.isEmpty()) {
                authorsToDelete.add(author);
            }
        }

        for (Topic topic : topics) {
            for (Edge adjacency : topic.adjacentEdges) {
                if(adjacency.linkOccurrences < 2){
                    edgesToDelete.add(adjacency);
                }
            }
            topic.adjacentEdges.removeAll(edgesToDelete);
            if(topic.topicOccurrences < 2 || topic.adjacentEdges.isEmpty()){
                topicsToDelete.add(topic);
            }
        }

        authors.removeAll(authorsToDelete);
        topics.removeAll(topicsToDelete);

    }

    public void sortGraph(){
        boolean swapped;
        
        do {
            swapped = false;
            for (int i = 1; i < authors.size(); i++) {
                if (authors.get(i - 1).authorContributions < authors.get(i).authorContributions) {
                    Author temp = authors.get(i - 1);
                    authors.set(i - 1, authors.get(i));
                    authors.set(i, temp);
                    swapped = true;
                }
            }
            for (int i = 1; i < topics.size(); i++){
                if (getTopicOccurrences(topics.get(i - 1)) < getTopicOccurrences(topics.get(i))){
                    Topic temp = topics.get(i - 1);
                    topics.set(i - 1, topics.get(i));
                    topics.set(i, temp);
                    swapped = true;
                }
            }
        } while (swapped);
    }
    
    /*
    private int getAuthorContributions(Author author) {
        int totalOccurrences = 0;
        for (Edge contribution: author.contributions) {
            totalOccurrences += contribution.linkOccurrences;
        }
        return totalOccurrences;
    }
    */
    public static int getTopicOccurrences(Topic topic){
        int totalOccurrences = 0;
        for (Edge adjacency: topic.adjacentEdges){
            totalOccurrences += adjacency.linkOccurrences;
        }
        return totalOccurrences;
    }

}