package lesson8;

public class HomeWorkApp8 {
    public static void main(String[] args){
        System.out.println(findSymbolOccurance("Hello, world!", ' '));

    }

    static int findSymbolOccurance(String str, char symbol){
        int counterSymbol = 0;
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if(charArray[i] == symbol){
                counterSymbol++;
            }
        }
        return counterSymbol;
    }
}
