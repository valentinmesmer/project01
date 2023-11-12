package project01;

import java.util.Arrays;
import java.util.List;

public class Histogram {
  int[] frequency;
  int min, max;

  public Histogram(List<Integer> data) {

    if (data == null || data.isEmpty()) {
      throw new IllegalArgumentException("data cannot be null or empty");
    }
        /*

        min = data.get(0);
        max = data.get(0);

        for (int value : data) {
            if (value < min) min = value;
            if (value > max) max = value;
        }

        */
    min = data.stream().min(Integer::compare).orElseThrow(IllegalArgumentException::new);
    max = data.stream().max(Integer::compare).orElseThrow(IllegalArgumentException::new);

    frequency = new int[max - min + 1];

    for (int value : data) {
      frequency[value - min]++;
    }
  }

  // Note: this constructor is provided as a convenience,
  //       it calls the primary constructor above
  public Histogram(Integer... data) {
    this(Arrays.asList(data));
  }

  public int min() {
    return min;
  }

  public int max() {
    return max;
  }

  public int count(int value) {
    int index = value - min;

      if (0 <= index && index < frequency.length) {
          return frequency[index];
      } else {
          return 0;
      }
  }
}
