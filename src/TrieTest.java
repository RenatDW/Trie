import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    Trie<Integer> a = new Trie<>();

    @Test
    void insert() {
        String normalWord = "aassad";
        String identicalWord = "askls";
        String normalWord2 = "als";

        assertDoesNotThrow(() -> a.insert(normalWord, 2));
        assertDoesNotThrow(() -> a.insert(identicalWord, 3));
        assertDoesNotThrow(() -> a.insert(identicalWord, 4));
        assertDoesNotThrow(() -> a.insert(normalWord2, 5));

        //Обработка ошибок

        String number = "1234";
        String space = " ";
        String specSymbols = "!!!";
        assertThrows(TrieExceptions.class, () -> a.insert(number, 1));
        assertThrows(TrieExceptions.class, () -> a.insert(space, 1));
        assertThrows(TrieExceptions.class, () -> a.insert(specSymbols, 1));
    }

    @Test
    void search() {
        //узнать подробнее реализацию trie
        String searchWord = "aassad";
        String notInTrie = "asdasmfnsdfmdsf";
        String notInTrie2 = "aas";
       a.insert(searchWord, 6);
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

        assertDoesNotThrow(() -> a.insert(deletedWord, 7));
        assertDoesNotThrow(() -> a.deletion(deletedWord));
        assertFalse(a.search(deletedWord));

        //Обработка ошибок
        assertThrows(TrieExceptions.class, () -> a.deletion(haventInTrie));

    }
}