public class Trie {

    TrieNode root;

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
        TrieNode[] current = root.children;
        for (int i = 0; i < word.length(); i++) {
            int charNumber = word.charAt(i) - 'a';
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
        TrieNode[] current = root.children;

        int charNumber;
        for (int i = 0; i < word.length(); i++) {
            try  {
                charNumber = word.charAt(i) - 'a';

                if (current[charNumber] == null) {
                    current[charNumber] = new TrieNode();
                    current[charNumber].value = word.charAt(i);
                }
                current[charNumber].value = word.charAt(i);
                if (i == word.length() - 1) {
                    current[charNumber].isWord = true;
                }
                current = current[charNumber].children;
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new TrieExceptions(ex);
            }
        }
    }

    private boolean charInBranch(Character letter, TrieNode[] current) {
        try {
            if (current[(int) (letter - 'a')] == null) {
                return false;
            }
        }catch (TrieExceptions ex){
            throw new TrieExceptions(ex);
        }
        return (current[(int) (letter - 'a')].value == letter);
    }

    public static void main(String[] args) {
        Trie a = new Trie();
        a.insert("asd");
        System.out.println(a.search("asfd"));


    }

}
