package book.toby1.template_callback;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

public class CalcSumTest {
    Calculator calculator;
    String numFilePath;

    @BeforeEach
    void setUp() {
        this.calculator = new Calculator();
        /**
         * /src/main/resources/numbers.txt 를
         * /out/test/classes/book/toby1/template_callback 에 추가
         * */
        try {
            this.numFilePath = new URI(getClass().getResource("numbers.txt").toString()).getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    void sumOfNumbers() throws IOException {
        assertThat(calculator.calcSum(numFilePath)).isEqualTo(15);
    }

    @Test
    void multiplyOfNumbers() throws IOException {
        assertThat(calculator.calcMultiply(numFilePath)).isEqualTo(120);
    }

    @Test
    void concatenate() throws IOException {
        assertThat(calculator.concatenate(numFilePath)).isEqualTo("12345");
    }
}
