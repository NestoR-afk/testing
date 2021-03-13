import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

Parser parser = new Parser();


    @Test
    void incorrectInputTest() {
        String incorrect = "3[[[[2]";
        Throwable exception = assertThrows(ParserException.class, () -> parser.parse(incorrect));
        assertEquals(exception.getMessage(), "Incorrect input");
    }

    @Test
    void incorrectBracketsTest() {
        String incorrect = "3[2[abc?%123]y]";
        Throwable exception = assertThrows(ParserException.class, () -> parser.parse(incorrect));
        assertEquals(exception.getMessage(), "Incorrect input");
    }
    @Test
    void correctInputTest(){
        String[] correctStrings = {"3[xyz]4[xy]z",
                                   "2[3[x]y]",
                                    "10[x]"};
        assertAll("correctStrings",
                () -> assertEquals(parser.parse(correctStrings[0]), "xyzxyzxyzxyxyxyxyz"),
                () -> assertEquals(parser.parse(correctStrings[1]), "xxxyxxxy"),
                () -> assertEquals(parser.parse(correctStrings[2]),"xxxxxxxxxx"));
    }
}