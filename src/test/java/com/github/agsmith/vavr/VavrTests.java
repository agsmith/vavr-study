package com.github.agsmith.vavr;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;

import io.vavr.collection.Queue;
import io.vavr.control.Option;
import org.junit.Test;

import java.util.Optional;

import static io.vavr.API.Some;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;


public class VavrTests {
    @Test
    public void TupleTest() {
        Tuple2<String, Integer> java8 = Tuple.of("Meaning of Life", 42);
        String s = java8._1;
        Integer i = java8._2;

        assertEquals(s,"Meaning of Life");
        assertEquals(i, Integer.valueOf(42));
    }

    @Test
    public void ListTest() {
        List<Integer> list1 = List.of(1, 2, 3);
        List<Integer> list2 = list1.tail().prepend(0);

        List<Integer> list3 = list2.map(x -> x+2);

        assertEquals(list1, List.of(1,2,3));
        assertEquals(list2, List.of(0,2,3));
        assertEquals(list3, List.of(2,4,5));

    }

    @Test
    public void QueueTest() {
        Queue<Integer> queue1 = Queue.of(1, 2, 3);
        Queue<Integer> queue2 =
                Queue.of(1, 2, 3)
                    .enqueue(4)
                    .enqueue(5);

        Tuple2<Integer, Queue<Integer>> deq = queue1.dequeue();

        assertEquals(queue1, Queue.of(1,2,3));
        assertEquals(queue2, Queue.of(1,2,3,4,5));
        assertEquals(deq._1, Integer.valueOf(1));
        assertEquals(deq._2, Queue.of(2,3));

    }

    @Test
    public void OptionTest() {
        Option<Integer> option1 = Some(1);
        Option<Integer> option2 = Option.None;

        assertEquals(option1, Some(1));
        assertEquals(option2, Option.None);


    }
}

