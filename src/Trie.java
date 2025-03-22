package src;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Trie {
    private static final Pattern ALLOWED_SYMBOLS = Pattern.compile("^[a-zA-Z0-9]+$");
    TrieNode root;

    static class TrieNode {
        //33 or 26
        TrieNode[] children = new TrieNode[62];
        boolean isWord = false;
        char key;

        public TrieNode() {

        }
        public TrieNode(char key) {
            this.key = key;

        }
    }

    public Trie() {
        root = new TrieNode();
    }

    public ArrayList<String> searchWithPrefix(String key) {
        validateInput(key);

        TrieNode[] currentNode = root.children;
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < key.length(); i++) {
            int charNumber = getNumberInArray(key.charAt(i));
            if (currentNode[charNumber] == null || !containSymbolInNode(key.charAt(i), currentNode)) {
                return null;
            }
            currentNode = currentNode[charNumber].children;
        }
        for (var children : currentNode) {
            if (children != null) {
                collectWords(children, key + children.key, ans, 5);
            }
        }
        return ans;

    }

    private void collectWords(TrieNode node, String currentWord, ArrayList<String> result, int limit) {
        if (result.size() >= limit) {
            return; // Остановить обход, если найдено достаточно слов
        }

        if (node.isWord) {
            result.add(currentWord); // Добавить слово в результат
        }

        for (TrieNode child : node.children) {
            if (child != null) {
                collectWords(child, currentWord + child.key, result, limit);
            }
        }
    }




    public boolean search(String word) {
        validateInput(word);
        TrieNode[] current = root.children;
        for (int i = 0; i < word.length(); i++) {
            int charNumber = getNumberInArray(word.charAt(i));
            if (current[charNumber] == null || !containSymbolInNode(word.charAt(i), current)) {
                return false;
            }
            if (i == word.length() - 1 && current[charNumber].isWord) {
                return true;
            }
            current = current[charNumber].children;
        }
        return false;
    }

    public void insert(String word) {
        validateInput(word);
        TrieNode[] currentNode = root.children;

        int charNumber;
        for (int i = 0; i < word.length(); i++) {
             charNumber = getNumberInArray(word.charAt(i));


            if (currentNode[charNumber] == null) {
                currentNode[charNumber] = new TrieNode(word.charAt(i));
            }
            currentNode[charNumber].key = word.charAt(i);
            if (i == word.length() - 1) {
                currentNode[charNumber].isWord = true;
            }
            currentNode = currentNode[charNumber].children;
        }
    }

    public void deletion(String word) {
        //todo вынести этот патерн
        validateInput(word);
        TrieNode[] currentNode = root.children;
        for (int i = 0; i < word.length(); i++) {
            int charNumber = getNumberInArray(word.charAt(i));

            if(currentNode[charNumber] == null || !containSymbolInNode(word.charAt(i), currentNode)){
                throw new TrieExceptions();
            }
            if (i == word.length() - 1 && currentNode[charNumber].isWord) {
                currentNode[charNumber].isWord = false;
            }
            currentNode = currentNode[charNumber].children;
        }
    }

    private boolean containSymbolInNode(char num, TrieNode[] current) {
        int charNumber = getNumberInArray(num);
        return (current[charNumber].key == num);
    }

    private static void validateInput(String word) {
        if (!ALLOWED_SYMBOLS.matcher(word).find()) {
            throw new TrieExceptions();
        }
    }

    //Этот метод переводит номер в символе unicode по особому принципу. Чтобы ограничить количество
    // веток в TrieNode, мы пропускаем все запрещенные символы, такие как (: ; < = > ? @) или  ([ \ ] ^ _ ` )
    // и соответственно вычитаем количество этих элементов. Также мы начинаем 48 чтобы отсчет начинался с
    // нулевого элемента.
    private int getNumberInArray(int num){
        // 48-57, 65-90, 97-122 включительно границы
        if (num > 47 && num < 58){ // From A letter to Z
            return num - 48;
        }else if (num > 64 && num < 91){ // skip (: ; < = > ? @) From 0 to 9
            return num - 48 - 7;
        } else if (num > 96 && num<123) { //skip ([ \ ] ^ _ ` ) From a to z
            return num - 48 - 7 - 6;
        }
        return -1;
    }
}
