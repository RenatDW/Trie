public class Trie {

    TrieNode root;

    class TrieNode {
        //33 or 26
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
        char value;
    }

    public Trie() {
        this.root = new TrieNode();
    }

    public boolean search(String word){
        TrieNode[] current = root.children;
        for(int i = 0; i < word.length(); i++){
            int charNumber = word.charAt(i) - 'a';
            if(!charInBranch(word.charAt(i), current)){
                return false;
            }
            current = current[charNumber].children;
        }
        return true;
    }

    private boolean charInBranch(Character letter, TrieNode[] current) {
        return (current[(int) (letter - 'a')].value == letter);
    }

    public static void main(String[] args) {
        int a =  'a';
        int powerAlphabet = 'a' - 'z';
        System.out.println(a);
        System.out.println(powerAlphabet);

    }

}
