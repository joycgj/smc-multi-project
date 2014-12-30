package cn.javass.dp.prototype.example8;

/**
 * 克隆的具体实现对象
 */
public class ConcretePrototype2 implements Prototype {
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Prototype clone() {
        ConcretePrototype2 prototype = new ConcretePrototype2();
        prototype.setName(this.name);
        return prototype;
    }

    public String toString() {
        return "Now in Prototype2，name=" + name;
    }
}