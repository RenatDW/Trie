import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    Trie a = new Trie();

    @Test
    void insert() {
        assertThrows(TrieExceptions.class, () -> a.insert("1234"));
        assertThrows(TrieExceptions.class, () -> a.insert(" "));
        assertThrows(TrieExceptions.class, () -> a.insert("asd!!!"));
        assertThrows(TrieExceptions.class, () -> a.insert("!!!,"));

        a.insert("aassad");
        a.insert("askls");
        a.insert("askls");
        a.insert("als");
    }

    @Test
    void search() {
        a.insert("aassad");
        assertTrue(a.search("aassad"));
        assertFalse(a.search("asdasmfnsdfmdsf"));
        assertFalse(a.search("aas"));

        assertThrows(TrieExceptions.class, () -> a.search("aas1123sad"));
        assertThrows(TrieExceptions.class, () -> a.search("!!!aas1123sad"));
        assertThrows(TrieExceptions.class, () -> a.search(",,!!!aas1123sad"));
        assertThrows(TrieExceptions.class, () -> a.search(" "));
        assertThrows(TrieExceptions.class, () -> a.search(" asdasd"));

    }

    @Test
    void deletion() {
        a.insert("aassad");
        a.deletion("aassad");
        assertThrows(TrieExceptions.class, () -> a.deletion("1aas1sad"));

        assertFalse(a.search("aassad"));
    }
}