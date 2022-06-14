package book.toby1.template_callback;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CalcSumTest {
    @Test
    void sumOfNumbers() throws IOException {
        Calculator calculator = new Calculator();
        // classpath에 txt 파일 추가
        Integer sum = calculator.calcSum(getClass().getResource("./numbers.txt").getPath());
        assertThat(sum).isEqualTo(15);
    }
}
