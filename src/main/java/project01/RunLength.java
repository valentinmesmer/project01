package de.vale.fsv.project01;

import java.util.ArrayList;
import java.util.List;

public class RunLength {


  public static <T> List<Run<T>> encode(List<T> input) {
    // Note: you may assume without checking that input contains no null elements.
    // Java will complain if you try something like this:
    //     if(input.contains(null))


    List<Run<T>> result = new ArrayList<>();

    if (input.isEmpty()) {
      return result;
    }

    T c = input.get(0);
    int count = 0;

    for (T e : input) {
      if (c.equals(e)) {
        count++;
      } else {
        result.add(new Run<>(count, c));
        c = e;
        count = 1;
      }
    }

    result.add(new Run<>(count, c));
    return result;
  }

  public static <T> List<T> decode(List<Run<T>> runs) {
    List<T> result = new ArrayList<>();

    for (Run<T> run : runs) {
      for (int i = 0; i < run.count(); i++) {
        result.add(run.elem());
      }
    }

    return result;
  }

  public static Integer sum(List<Run<Integer>> runs) {
    int sum = 0;
    for (Run<Integer> run : runs) {
      sum += run.count() * run.elem();
    }
    return sum;
  }
}
