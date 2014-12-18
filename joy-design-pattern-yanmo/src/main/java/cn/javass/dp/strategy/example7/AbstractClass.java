package cn.javass.dp.strategy.example7;

/**
 * Created by gaojiechen on 2014/12/18.
 */
public abstract class AbstractClass implements Strategy {
    @Override
    public void algorithmInterface() {
        stepOneOpe();
        stepTwoOpe();
        stepThreeOpe();
    }

    private void stepThreeOpe() {
    }

    protected abstract void stepOneOpe();

    protected abstract void stepTwoOpe();
}
