package src;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Trie {
    TrieNode root;
    final int NUMBERFIRSTLETTER = 'a';

    static class TrieNode {
        //33 or 26
        TrieNode[] children = new TrieNode[26];
        char data;
        boolean isWord = false;
        char key;

        public TrieNode() {
        }

        public TrieNode(char data) {
            this.data = data;
        }
    }

    public Trie() {
        root = new TrieNode();
    }

    public ArrayList<String> searchWithPrefix(String key) {
        if (!Pattern.compile("^[a-z]+$").matcher(key).find()) {
            throw new TrieExceptions();
        }
        TrieNode[] current = root.children;
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < key.length(); i++) {
            int charNumber = key.charAt(i) - NUMBERFIRSTLETTER;
            if (contain(key.charAt(i), current)) {
                return null;
            }
            current = current[charNumber].children;
        }
        for (var children : current) {
            if (children != null) {
                ans.add(key + children.key);
            }
        }

        return ans;
    }


    public boolean search(String word) {
        if (!Pattern.compile("^[a-z]+$").matcher(word).find()) {
            throw new TrieExceptions();
        }
        TrieNode[] current = root.children;
        for (int i = 0; i < word.length(); i++) {
            int charNumber = word.charAt(i) - NUMBERFIRSTLETTER;
            if (contain(word.charAt(i), current)) {
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
        if (!Pattern.compile("^[a-z]+$").matcher(word).find()) {
            throw new TrieExceptions();
        }
        TrieNode[] current = root.children;

        int charNumber;
        for (int i = 0; i < word.length(); i++) {
            charNumber = word.charAt(i) - NUMBERFIRSTLETTER;

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
        if (!Pattern.compile("^[a-z]+$").matcher(word).find()) {
            throw new TrieExceptions();
        }
        TrieNode[] current = root.children;
        for (int i = 0; i < word.length(); i++) {
            int charNumber = word.charAt(i) - NUMBERFIRSTLETTER;
            if (contain(word.charAt(i), current)) {
                break;
            }
            if (i == word.length() - 1 && current[charNumber].isWord) {
                for (var elem : current) {
                    if (elem != null) {
                        current[charNumber].isWord = false;
                    }
                }
            }
            current = current[charNumber].children;
        }
    }

    private boolean contain(Character letter, TrieNode[] current) {
        if (current[letter - NUMBERFIRSTLETTER] == null) {
            return true;
        }
        return (current[letter - NUMBERFIRSTLETTER].key != letter);
    }
}
