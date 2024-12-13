package lesson8;

public class Main {
    public static void main(String[] args){
//        System.out.println(findSymbolOccurance("Hello, world!", ' '));
//        System.out.println(findWordPosition("Apollo", "Apple"));
//        System.out.println(stringReverse("Hello, world!"));
//        System.out.println(isPolindrome("level"));

          WordGuessingGame game = new WordGuessingGame();
          game.startGame();

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

    static boolean isPolindrome(String str){
        int firstIndex = 0;
        int lastIndex = str.length() - 1;
        while (firstIndex < lastIndex){
            if(str.charAt(firstIndex) != str.charAt(lastIndex)){
                return false;
            }
            firstIndex++;
            lastIndex--;
        }
        return true;
    }

}
