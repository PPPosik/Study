package book.toby1.template_callback;

public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);
}
