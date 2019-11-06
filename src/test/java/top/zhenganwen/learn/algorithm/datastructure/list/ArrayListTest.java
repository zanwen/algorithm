package top.zhenganwen.learn.algorithm.datastructure.list;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author zhenganwen
 * @date 2019/11/411:22
 */
class ArrayListTest {

    private ArrayList list;

    @Before
    void setUp() {
        list = new ArrayList();
    }

    @Test
    void add() {
        list.add(1);
        assertEquals(list.get(0), 1);
        list.add(2);
        assertEquals(list.get(1), 2);
        list.add(3);
        assertEquals(list.get(2), 3);
        for (int i = 0; i < 50; i++) {
            list.add(i);
        }
    }

    @Test
    void insert() {
        list.insert(0, 4);
        assertEquals(list.get(0), 4);
        list.insert(1, 5);
        assertEquals(list.get(1), 5);
    }

    @Test
    void remove() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(1);
        assertEquals(list.size, 2);
        assertEquals(list.get(1), 3);
    }

    @Test
    void set() {
        list.add(1);
        list.add(2);
        list.set(0, 3);
        assertEquals(list.get(0), 3);
        assertEquals(list.get(1), 2);
    }

    @Test
    void get() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(list.get(0), 1);
        assertEquals(list.get(1), 2);
        assertEquals(list.get(2), 3);
    }

    @Test
    void indexOf() {
        for (int i = 0; i < 3; i++) {
            list.add(i);
        }
        assertEquals(list.indexOf(2), 2);
        assertEquals(list.indexOf(4), -1);
    }
}
