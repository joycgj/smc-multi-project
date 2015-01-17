//: enumerated/menu/Course.java
package concurrency.exercise.restaurant2;

import net.mindview.util.*;

public enum Course {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);
    private Food[] values;

    private Course(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }

    public Food randomSelection() {
        return Enums.random(values);
    }

    public static void main(String[] args) {
        for (Course course : Course.values()) {
            Food food = course.randomSelection();
            System.out.println(food);
        }
    }
} ///:~
