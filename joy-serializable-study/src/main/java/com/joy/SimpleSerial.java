package com.joy;

import java.io.*;

/**
 * SimpleSerial，是一个简单的序列化程序，它先将一个Person对象保存到文件person.out中，
 * 然后再从该文件中读出被存储的Person对象，并打印该对象。
 */
public class SimpleSerial {
    public static void main(String[] args) throws Exception {
        File file = new File("person.out");
        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file));
        oout.writeObject(Person.getInstance()); // 保存单例对象
        oout.close();

        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));
        Object newPerson = oin.readObject();
        oin.close();
        System.out.println(newPerson);

        System.out.println(Person.getInstance() == newPerson); // 将获取的对象与Person类中的单例对象进行相等性比较
    }
}

/**
 * 上述程序的输出的结果为：
 * arg constructor
 * [John, 31, MALE]
 */
