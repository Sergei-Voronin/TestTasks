public class NameGenerator {
    private final String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final char[] symbolsChar = symbols.toCharArray();

    public String generate(){
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 6; i++){
            name.append(symbolsChar[(int)(Math.random() * symbolsChar.length)]);
        }
        return name.toString();
    }
}
