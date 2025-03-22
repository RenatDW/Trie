package src;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    Trie a = new Trie();

    @Test
    void insert() {
        String normalWord = "aassad";
        String identicalWord = "askls";
        String normalWord2 = "als";

        assertDoesNotThrow(() -> a.insert(normalWord));
        assertDoesNotThrow(() -> a.insert(identicalWord));
        assertDoesNotThrow(() -> a.insert(identicalWord));
        assertDoesNotThrow(() -> a.insert(normalWord2));

        //Обработка ошибок

        String number = "1234";
        String space = " ";
        String specSymbols = "!!!";
        assertThrows(TrieExceptions.class, () -> a.insert(number));
        assertThrows(TrieExceptions.class, () -> a.insert(space));
        assertThrows(TrieExceptions.class, () -> a.insert(specSymbols));
    }

    @Test
    void search() {
        //узнать подробнее реализацию trie
        String searchWord = "aassad";
        String notInTrie = "asdasmfnsdfmdsf";
        String notInTrie2 = "aas";
        a.insert(searchWord);
        assertTrue(a.search(searchWord));
        assertFalse(a.search(notInTrie));
        assertFalse(a.search(notInTrie2));


        //Обработка ошибок
        String number = "1234";
        String space = " ";
        String specSymbols = "!!!";
        assertThrows(TrieExceptions.class, () -> a.search(number));
        assertThrows(TrieExceptions.class, () -> a.search(space));
        assertThrows(TrieExceptions.class, () -> a.search(specSymbols));

    }

    @Test
    void deletion() {
        String deletedWord = "aassad";
        String haventInTrie = "1aas1sad";

        assertDoesNotThrow(() -> a.insert(deletedWord));
        assertDoesNotThrow(() -> a.deletion(deletedWord));
        assertFalse(a.search(deletedWord));

        //Обработка ошибок
        assertThrows(TrieExceptions.class, () -> a.deletion(haventInTrie));

    }


    @Test
    void testSearchWithPrefix_ValidPrefix() {
        a = new Trie();
        a.insert("aa");
        a.insert("ab");
        a.insert("ac");
        a.insert("bb");
        a.insert("cc");
        ArrayList<String> result = a.searchWithPrefix("a");
        assertEquals(3, result.size());
        assertTrue(result.contains("aa"));
        assertTrue(result.contains("ab"));
        assertTrue(result.contains("ac"));
    }

    @Test
    void testSearchWithPrefix_NonExistingPrefix() {
        Trie trie;
        trie = new Trie();
        a.insert("aa");
        a.insert("ab");
        a.insert("ac");
        a.insert("bb");
        a.insert("cc");
        ArrayList<String> result = trie.searchWithPrefix("d");
        assertTrue(result == null);
    }

    @Test
    void testSearchWithPrefix_EmptyPrefix() {
        Trie trie;
        trie = new Trie();
        a.insert("aa");
        a.insert("ab");
        a.insert("ac");
        a.insert("bb");
        a.insert("cc");
        assertThrows(TrieExceptions.class, () -> trie.searchWithPrefix(""));
    }

    @Test
    void testSearchWithPrefix_InvalidPrefix() {
        Trie trie;
        trie = new Trie();
        a.insert("aa");
        a.insert("ab");
        a.insert("ac");
        a.insert("bb");
        a.insert("cc");
        assertThrows(TrieExceptions.class, () -> {
            trie.searchWithPrefix("1");
        });
    }
}