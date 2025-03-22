package src;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Trie {
    private static final Pattern ALLOWED_SYMBOLS = Pattern.compile("^[a-zA-Z0-9]+$");
    TrieNode root;
    final int NUMBERFIRSTLETTER = 'a';

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
        if (!ALLOWED_SYMBOLS.matcher(key).find()) {
            throw new TrieExceptions();
        }

        TrieNode[] currentNode = root.children;
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < key.length(); i++) {
            int charNumber = getNumberInArray(key.charAt(i));
            if (currentNode[charNumber] == null || !contain(key.charAt(i), currentNode)) {
                return null;
            }
            currentNode = currentNode[charNumber].children;
        }
        for (var children : currentNode) {
            if (children != null) {
                ans.add(key + children.key);
            }
        }

        return ans;
    }


    public boolean search(String word) {
        if (!ALLOWED_SYMBOLS.matcher(word).find()) {
            throw new TrieExceptions();
        }
        TrieNode[] current = root.children;
        for (int i = 0; i < word.length(); i++) {
            int charNumber = getNumberInArray(word.charAt(i));
            if (current[charNumber] == null || !contain(word.charAt(i), current)) {
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
        if (!ALLOWED_SYMBOLS.matcher(word).find()) {
            throw new TrieExceptions();
        }
        TrieNode[] current = root.children;

        int charNumber;
        for (int i = 0; i < word.length(); i++) {
             charNumber = getNumberInArray(word.charAt(i));


            if (current[charNumber] == null) {
                current[charNumber] = new TrieNode(word.charAt(i));
            }
            current[charNumber].key = word.charAt(i);
            if (i == word.length() - 1) {
                current[charNumber].isWord = true;
            }
            current = current[charNumber].children;
        }
    }

    public void deletion(String word) {
        //todo вынести этот патерн
        if (!ALLOWED_SYMBOLS.matcher(word).find()) {
            throw new TrieExceptions();
        }
        TrieNode[] current = root.children;
        for (int i = 0; i < word.length(); i++) {
            int charNumber = getNumberInArray(word.charAt(i));

            if (current[charNumber] == null || !contain(word.charAt(i), current)) {
                throw new TrieExceptions();
            }
            if (i == word.length() - 1 && current[charNumber].isWord) {
                current[charNumber].isWord = false;
            }
            current = current[charNumber].children;
        }
    }

    private boolean contain(char num, TrieNode[] current) {
        int charNumber = getNumberInArray(num);
        return (current[charNumber].key == num);
    }

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
