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

//        String number = "1234";
        String space = " ";
        String specSymbols = "!!!";
//        assertThrows(TrieExceptions.class, () -> a.insert(number));
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
//        assertThrows(TrieExceptions.class, () -> a.search(number));
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
        assertNull(trie.searchWithPrefix("1"));
    }
    @Test
    void insertMixedCaseAndNumbers() {
        // Слова с разным регистром и цифрами
        String lowerCaseWord = "test123";
        String upperCaseWord = "TEST456";
        String mixedCaseWord = "TeSt789";
        String numbersOnly = "123456";
        String wordWithNumbers = "12abc34";

        assertDoesNotThrow(() -> a.insert(lowerCaseWord));
        assertDoesNotThrow(() -> a.insert(upperCaseWord));
        assertDoesNotThrow(() -> a.insert(mixedCaseWord));
        assertDoesNotThrow(() -> a.insert(numbersOnly));
        assertDoesNotThrow(() -> a.insert(wordWithNumbers));
    }

    @Test
    void searchCaseInsensitive() {
        // Проверка поиска без учета регистра
        a.insert("TestWord123");

        assertFalse(a.search("testword123"));
        assertFalse(a.search("TESTWORD123"));
        assertFalse(a.search("tEsTwOrD123"));
    }

    @Test
    void searchWithNumbers() {
        // Проверка поиска слов с цифрами
        a.insert("abc123");
        a.insert("123abc");
        a.insert("a1b2c3");

        assertTrue(a.search("abc123"));
        assertTrue(a.search("123abc"));
        assertTrue(a.search("a1b2c3"));
        assertFalse(a.search("a1b2c")); // Неполное совпадение
    }

    @Test
    void prefixSearchWithNumbers() {
        a.insert("data123");
        a.insert("data456");
        a.insert("dataset789");

        ArrayList<String> result = a.searchWithPrefix("data");
        assertEquals(3, result.size());
        assertTrue(result.contains("data123"));
        assertTrue(result.contains("data456"));
        assertTrue(result.contains("dataset789"));
    }

    @Test
    void caseSensitivePrefixSearch() {
        // Проверка чувствительности к регистру в префиксах
        a.insert("Apple123");
        a.insert("apple456");

        ArrayList<String> upperCaseResult = a.searchWithPrefix("APPLE");
        ArrayList<String> lowerCaseResult = a.searchWithPrefix("apple");

        // В зависимости от реализации - ожидать 0 или 2 результата
        assertTrue(upperCaseResult == null || upperCaseResult.isEmpty());
        assertEquals(1, lowerCaseResult.size());
    }

    @Test
    void deleteMixedCaseWord() {
        // Удаление слова с разным регистром
        String word = "DeLeTeMe123";
        a.insert(word);

        assertTrue(a.search(word));
        a.deletion(word);
        assertFalse(a.search(word));
    }

    @Test
    void specialCharactersInWord() {
        // Проверка обработки спецсимволов
        String validWord = "valid123";
        String invalidWord = "inv@lid!";

        assertDoesNotThrow(() -> a.insert(validWord));
        assertThrows(TrieExceptions.class, () -> a.insert(invalidWord));
    }
}