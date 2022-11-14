package book.toby1.template_callback;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CalcSumTest {
    Calculator calculator;
    String numFilePath;

    @BeforeEach
    void setUp() {
        this.calculator = new Calculator();
        // /out/test/classes/book/toby1/template_callback 에 txt 파일 추가
        this.numFilePath = getClass().getResource("numbers.txt").getPath();
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
