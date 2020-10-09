package com.github.agsmith.vavr;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Queue;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.Test;

import java.util.Optional;

import static io.vavr.API.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


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
    public void OptionNone() {
        Option<Integer> option2 = Option.of(null);
        assertEquals(option2.toString(), "None");

    }
    @Test
    public void OptionSome() {
        Option<Integer> option1 = Some(1);
        assertEquals(option1, Some(1));
    }
    @Test
    public void EitherLeft() {
        Either<String, Integer> e2 = Either.left("Error");
        assertEquals(e2.left().get(), "Error");
    }
    @Test
    public void EitherRight() {
        Either<String, Integer> e1 = Either.right(42);
        assertEquals(e1.right().get(), Integer.valueOf(42));

    }

    @Test
    public void TryFail() {

        Try<Integer> result = Try.of(() -> 1 / 0);

        assertTrue(result.isFailure());
    }
    @Test
    public void TrySuccess() {

        Try<Integer> result = Try.of(() -> 1 / 1);

        assertTrue(result.isSuccess());
    }

    @Test
    public void PatternMatchIntToString() {
        int i = 1;

        String s = Match(i).of(
            Case($(1), "one"),
            Case($(2), "two"),
            Case($(), "?")
        );
        assertEquals(s, "one");
    }
    @Test
    public void PatternMatchIntToStringBaseCase() {
        int i = 3;

        String s = Match(i).of(
            Case($(1), "one"),
            Case($(2), "two"),
            Case($(), "?")
        );
        assertEquals(s, "?");
    }
}

