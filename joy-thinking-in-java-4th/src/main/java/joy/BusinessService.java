package joy;

/**
 * Created by gaojiechen on 2015/1/12.
 */
public class BusinessService {
    public void businessMethod() {
        Context context = MyThreadLocal.get();
        System.out.println(context.getTransactionId());
    }
}
