public class PreProcessText{
    public static String[] splitWords(String text){
        text = text.replaceAll("[?.:;!]", "");  
        return text.split("\\s+");
    }

    public static String[] splitAuthors(String text){
        return text.split(",");
    }

    public static String[] splitTopics(String text){
        return text.trim().split("[,;]");
    }

    public static String[] splitPhrases(String text){
        return text.split("[?\n:.!]+");
    }

    public static String processText(String text, String type){
        text = text.toLowerCase();
        
        String[] specialCharacters = {"ã", "â", "á", "à", "é", "ê", "í", "ó", "õ", "ô", "ú", "ç"};
        String[] replacementCharacters = {"a", "a", "a", "a", "e", "e", "i", "o", "o", "o", "u", "c"};

        boolean finishedReplacement = false;
        int index = 0;
        while(!finishedReplacement){
            if(index > 11){
                finishedReplacement = true;
                break;
            }
            if(text.contains(specialCharacters[index])){
                text = text.replaceAll(specialCharacters[index], replacementCharacters[index]);
            }
            index++;
        }
        if(type != "authors" && type != "topics"){
            text = text.replaceAll("[^\n?:;.!a-zA-Z -]", "");
            text = text.replaceAll("[-]", " ");
        }
        return text;
    }


    public static String[] removeStopWords(String[] text){
        String[] stopWords = {"a", "agora", "ainda", "alguem", "algum", "alguma", "algumas", "alguns", "ampla", "amplas", "amplo", "amplos", "ante", "antes", 
        "ao", "aos", "apos", "aquela", "aquelas", "aquele", "aqueles", "aquilo", "as", "ate", "atraves", "cada", "coisa", "coisas", "com", "como", "contra", 
        "contudo", "da", "daquele", "daqueles", "das", "de", "dela", "delas", "dele", "deles", "depois", "dessa", "dessas", "desse", "desses", "desta", "destas",
        "deste", "deste", "destes", "deve", "devem", "devendo", "dever", "devera", "deverao", "deveria", "deveriam", "devia", "deviam", "disse", "disso", "disto", 
        "dito", "diz", "dizem", "do", "dos", "e", "ela", "elas", "ele", "eles", "em", "enquanto", "entre", "era", "essa", "essas", "esse", "esses", "esta", 
        "estamos", "estao", "estas", "estava", "estavam", "estavamos", "este", "estes", "estou", "eu", "fazendo", "fazer", "feita", "feitas", "feito", 
        "feitos", "foi", "for", "foram", "fosse", "fossem", "grande", "grandes", "i", "ii", "iii", "iv", "ha", "isso", "isto", "ja", "la", "lhe", "lhes", "lo", 
        "mas", "me", "mesma", "mesmas", "mesmo", "mesmos", "meu", "meus", "minha", "minhas", "muita", "muitas", "muito", "muitos", "na", "nao", "nas", "nem", 
        "nenhum", "nessa", "nessas", "nesta", "nestas", "ninguem", "no", "nos", "nossa", "nossas", "nosso", "nossos", "num", "numa", "nunca", "o", "os", "ou", 
        "outra", "outras", "outro", "outros", "para", "pela", "pelas", "pelo", "pelos", "pequena", "pequenas", "pequeno", "pequenos", "per", "perante", "pode", 
        "pude", "podendo", "poder", "poderia", "poderiam", "podia", "podiam", "pois", "por", "porem", "porque", "posso", "pouca", "poucas", "pouco", "poucos",
        "primeiro", "primeiros", "propria", "proprias", "proprio", "proprios", "quais", "qual", "quando", "quanto", "quantos", "que", "quem", "sao", "se", 
        "seja", "sejam", "sem", "sempre", "sendo", "sera", "serao", "seu", "seus", "si", "sido", "so", "sob", "sobre", "sua", "suas", "talvez", "tambem", 
        "tampouco", "te", "tem", "tendo", "tenha", "ter", "teu", "teus", "ti", "tido", "tinha", "tinham", "toda", "todas", "todavia", "todo", "todos", "tu", 
        "tua", "tuas", "tudo", "ultima", "ultimas", "ultimo", "ultimos", "um", "uma", "umas", "uns", "v", "vendo", "ver", "vez", "vindo", "vir", "vos"};

        for(int i = 0; i < text.length; i++) {
            for(int j = 0; j < stopWords.length; j++) {
                if(text[i].equals(stopWords[j])){
                    text[i] = "";
                }
            }
        }
        
        return text;
    }
}