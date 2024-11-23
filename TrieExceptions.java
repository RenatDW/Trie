public class TrieExceptions extends RuntimeException {
    public TrieExceptions(Exception message) {
        super( "Введены некорректные данныe");
    }
}
