package cn.javass.dp.abstractfactory.example5;

public class XmlDetailDAOImpl implements OrderDetailDAO {

    @Override
    public void saveOrderDetail() {
        System.out.println("now in XmlDAOImpl2 saveOrderDetail");
    }

}
