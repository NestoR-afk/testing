import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
    public String parse(String input) throws ParserException {
        if (!isValid(input)) {
            throw new ParserException("Incorrect input");
        }

        Matcher matcher = Pattern.compile(".*?(\\d+\\[\\w+?\\])").matcher(input);
        while (matcher.find()) {
            int start = matcher.start(1);
            int end = matcher.end(1);
            String bracketsWithRepeats = input.substring(start, end);
            input = input.replace(bracketsWithRepeats,expand(bracketsWithRepeats));
            matcher.reset(input);
            }
        return input;
    }

    private String expand(String brackets) throws ParserException {
        String expanded = "";
        int repeats;
        Matcher matcher = Pattern.compile("^\\d+").matcher(brackets);
        if (matcher.find()) {
            repeats = Integer.parseInt(matcher.group());
        } else {
            throw new ParserException();
        }

        String phrase;
        matcher = Pattern.compile("\\[(\\w+)\\]").matcher(brackets);
        if (matcher.find()){
            phrase=matcher.group(1);
        } else {
            throw new ParserException("Incorrect expression in brackets");
        }

        for(int i = 0; i < repeats; i++) {
            expanded += phrase;
        }
        return expanded;
    }

    private boolean isValid(String input){
        if (input.chars().filter(c -> c == '[').count() !=
                input.chars().filter(c -> c == ']').count()) return false;

        for (int i = 0; i < input.length()-1; i++) {
            if (Character.isDigit(input.charAt(i)) & (input.charAt(i+1) != '[' & !Character.isDigit(input.charAt(i+1))))
                return false;
        }
        return true;
    }
}
