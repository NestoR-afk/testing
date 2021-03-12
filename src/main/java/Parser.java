import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
    public String parse(String input) throws ParserException {
        if (!isValid(input)) {
            throw new ParserException("Incorrect input");
        }
        for (int i = input.length() - 1; i >= 0; i--) {
            if (Character.isDigit(input.charAt(i))) {
                String brackets = input.substring(i,input.indexOf(']', i) + 1);
                input = input.replace(brackets,expand(brackets));
            }
        }
        return input;
    }

    private String expand(String brackets) throws ParserException {
        String opened = "";
        int repeats = brackets.charAt(0) - '0';
        String phrase;
        Matcher matcher = Pattern.compile("\\[(\\w+)\\]").matcher(brackets);

        if (matcher.find()){
            phrase=matcher.group(1);
        } else {
            throw new ParserException("Incorrect expression in brackets");
        }

        for(int i = 0; i < repeats; i++) {
            opened += phrase;
        }
        return opened;
    }

    private boolean isValid(String input){
        if (input.chars().filter(c -> c == '[').count() !=
                input.chars().filter(c -> c == ']').count()) return false;

        for (int i = 0; i < input.length()-1; i++) {
            if (Character.isDigit(input.charAt(i)) & input.charAt(i+1) != '[')
                return false;
        }
        return true;
    }
}
