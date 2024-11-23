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
    }

    @Test
    void search() {
        a.insert("aassad");
        assertTrue(a.search("aassad"));
        assertFalse(a.search("asdasmfnsdfmdsf"));
        assertFalse(a.search("aas"));

        assertThrows(TrieExceptions.class, () -> a.insert("aas1123sad"));
        assertThrows(TrieExceptions.class, () -> a.insert("!!!aas1123sad"));
        assertThrows(TrieExceptions.class, () -> a.insert(",,!!!aas1123sad"));
        assertThrows(TrieExceptions.class, () -> a.insert(" "));

    }

}