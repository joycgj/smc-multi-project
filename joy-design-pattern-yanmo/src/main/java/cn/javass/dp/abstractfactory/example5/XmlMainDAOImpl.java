package cn.javass.dp.abstractfactory.example5;

public class XmlMainDAOImpl implements OrderMainDAO {
    @Override
    public void saveOrderMain() {
        System.out.println("now in XmlMainDAOImpl saveOrderMain");
    }
}
