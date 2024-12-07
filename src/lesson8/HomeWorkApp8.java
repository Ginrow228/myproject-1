package lesson8;

public class HomeWorkApp8 {
    public static void main(String[] args){
//        System.out.println(findSymbolOccurance("Hello, world!", ' '));
//        System.out.println(findWordPosition("Apollo", "pollo"));
          System.out.println(stringReverse("Hello, world!"));
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

    static int findWordPosition(String source, String target) {
        return source.indexOf(target);
    }

    static String stringReverse(String str){
        String emptyStr = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            emptyStr += str.charAt(i);
        }
        return emptyStr;
    }
}
