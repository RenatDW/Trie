import java.util.regex.Pattern;

public class Trie {
    TrieNode root;
    final int NUMBERFIRSTLETTER = 'a';

    class TrieNode {
        //33 or 26
        TrieNode[] children = new TrieNode[26];
        boolean isWord = false;
        char value;
    }

    public Trie() {
        this.root = new TrieNode();
    }

    public boolean search(String word) {
        if (!Pattern.compile("^[a-z]+$").matcher(word).find()) {
            throw new TrieExceptions();
        }
        TrieNode[] current = root.children;
        for (int i = 0; i < word.length(); i++) {
            int charNumber = word.charAt(i) - NUMBERFIRSTLETTER;
            if (!charInBranch(word.charAt(i), current)) {
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
                current[charNumber] = new TrieNode();
                current[charNumber].value = word.charAt(i);
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
        TrieNode[] current = root.children;
        for (int i = 0; i < word.length(); i++) {
            int charNumber = word.charAt(i) - NUMBERFIRSTLETTER;
            if (!charInBranch(word.charAt(i), current)) {
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

    private boolean charInBranch(Character letter, TrieNode[] current) {
        if (current[(int) (letter - NUMBERFIRSTLETTER)] == null) {
            return false;
        }
        return (current[(int) (letter - NUMBERFIRSTLETTER)].value == letter);
    }
}
