package generics.exercise;

import generics.RandomList;
import net.mindview.util.CountingGenerator;
import net.mindview.util.Generator;

public class E06_RandomListTest {
    private static void dump(RandomList<?> rl) {
        for (int i = 0; i < 11; i++) {
            System.out.print(rl.select() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        RandomList<String> rs = new RandomList<String>();
        for (String s : ("The quick brown fox").split(" ")) {
            rs.add(s);
        }
        dump(rs);

        RandomList<Integer> ri = new RandomList<Integer>();
        Generator<Integer> gi = new CountingGenerator.Integer();
        for (int i = 0; i < 11; i++) {
            ri.add(gi.next());
        }
        dump(ri);

        RandomList<Character> rc = new RandomList<Character>();
        Generator<Character> gc = new CountingGenerator.Character();
        for (int i = 0; i < 11; i++) {
            rc.add(gc.next());
        }
        dump(rc);
    }
}
