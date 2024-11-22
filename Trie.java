public class Trie<T>  {
    class Node<T> {
        //33 or 26
        Node[] Children = new Node[26];
        boolean isTerminal;
        T value;
    }
}
