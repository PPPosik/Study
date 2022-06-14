package book.toby1.template_callback;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
    public Integer calcSum(String filepath) throws IOException {
        return lineReadTemplate(filepath, (line, value) -> Integer.parseInt(line) + value, 0);
    }

    public Integer calcMultiply(String filepath) throws IOException {
        return lineReadTemplate(filepath, (line, value) -> Integer.parseInt(line) * value, 1);
    }

    public String concatenate(String filepath) throws IOException {
        return lineReadTemplate(filepath, (line, value) -> value + line, "");
    }

    public <T> T lineReadTemplate(String filepath, LineCallback<T> callback, T initVal) throws IOException {
        return fileReadTemplate(filepath, br -> {
            T ret = initVal;
            String line = null;

            while ((line = br.readLine()) != null) {
                ret = callback.doSomethingWithLine(line, ret);
            }

            return ret;
        });
    }

    public <T> T fileReadTemplate(String filepath, BufferedReaderCallback<T> callback) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filepath));
            return callback.doSomethingWithReader(br);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
