package generics;

/**
 * Created by chengaojie on 15/7/10.
 */
public class Holder3<T> {
    private T a;

    public Holder3(T a) {
        this.a = a;
    }

    public T get() {
        return a;
    }

    public void set(T a) {
        this.a = a;
    }

    public static void main(String[] args) {
        Holder3<String> h3 = new Holder3<String>("test");
        String str = h3.get();


    }
}
