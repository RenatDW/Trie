import java.util.regex.Pattern;

public class Trie<T> {
    TrieNode<T> root;
    final int NUMBERFIRSTLETTER = 'a';

    static class TrieNode<T> {
        //33 or 26
        TrieNode<T>[] children = new TrieNode[26];
        T data;
        boolean isWord = false;
        char value;

        public TrieNode() {
        }

        public TrieNode(T data, char value) {
            this.data = data;
            this.value = value;
        }
    }

    public Trie() {
        root = new TrieNode<>();
    }

    public boolean search(String word) {
        if (!Pattern.compile("^[a-z]+$").matcher(word).find()) {
            throw new TrieExceptions();
        }
        TrieNode<T>[] current = root.children;
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

    public void insert(String word, T data) {
        if (!Pattern.compile("^[a-z]+$").matcher(word).find()) {
            throw new TrieExceptions();
        }
        TrieNode<T>[] current = root.children;

        int charNumber;
        for (int i = 0; i < word.length(); i++) {
            charNumber = word.charAt(i) - NUMBERFIRSTLETTER;

            if (current[charNumber] == null) {
                current[charNumber] = new TrieNode<>(data, word.charAt(i));
            }
            current[charNumber].value = word.charAt(i);
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
        TrieNode<T>[] current = root.children;
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

    private boolean contain(Character letter, TrieNode<T>[] current) {
        if (current[letter - NUMBERFIRSTLETTER] == null) {
            return true;
        }
        return (current[letter - NUMBERFIRSTLETTER].value != letter);
    }
}