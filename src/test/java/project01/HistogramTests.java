package project01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Random;
import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.NotEmpty;
import net.jqwik.api.constraints.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class HistogramTests {
  // Note: this method can be used to run the unit test without a test framework (e.g. to debug)
  public static void main(String[] args) {
    HistogramTests h = new HistogramTests();
    h.example();
  }

  @Test
  void example() {
    Histogram histogram = new Histogram(2, 3, 0, 5, 5, 6, -2, 9, 0, 3, 5);
    assertEquals(-2, histogram.min());
    assertEquals(9, histogram.max());

    assertEquals(0, histogram.count(-3));
    assertEquals(1, histogram.count(-2));
    assertEquals(0, histogram.count(-1));
    assertEquals(2, histogram.count(0));
    assertEquals(0, histogram.count(1));
    assertEquals(1, histogram.count(2));
    assertEquals(2, histogram.count(3));
    assertEquals(0, histogram.count(4));
    assertEquals(3, histogram.count(5));
    assertEquals(1, histogram.count(6));
    assertEquals(0, histogram.count(7));
    assertEquals(0, histogram.count(8));
    assertEquals(1, histogram.count(9));
    assertEquals(0, histogram.count(10));
  }


  int countOccurrences(int value, List<Integer> data) {
    return (int) data.stream().filter(i -> i == value).count();
  }

  @Property
  void histogramDoesNotCrash(
      @ForAll @NotEmpty List<@IntRange(min = -100, max = 100) Integer> data) {
    new Histogram(data);
  }

  @Property
  void histogramCount(@ForAll @NotEmpty List<@IntRange(min = -100, max = 100) Integer> data,
                      @ForAll @IntRange(min = -100, max = 100) int value) {
    Histogram histogram = new Histogram(data);
    int expectedCount = countOccurrences(value, data);
    int actualCount = histogram.count(value);

    assertEquals(expectedCount, actualCount);
  }

  @Property
  @Disabled("Test soll beispielshaft fehlschlagen")
  void histogramRangeBroken(@ForAll @NotEmpty List<@IntRange(min = -100000, max = 100000) Integer> data,
                      @ForAll @IntRange(min = -100000, max = 100000) int value) {
    Histogram histogram = new Histogram(data);
    Assume.that(countOccurrences(value, data) > 0);
    Assertions.assertTrue(value >= histogram.min() && value <= histogram.max());
    /*
        Bei einer großen Range von data und einer kleinen range von value werden extrem viele
        Testfälle durch das assume that herausgefiltert was zu einer extrem niedrigen
        Testabdeckung führen kann. jqwik lässt diese texts dann fehlschlagen.

    */
  }

  //Verbesserter Test
  @Property
  void histogramRange(
      @ForAll @NotEmpty List<@IntRange(min = -100000, max = 100000) Integer> data
  ) {
    /*
        Unsere Lösung: Anstatt mögliche Testfälle herauszufiltern können wir einfach über alle
        tatsächlichen Werte in data interieren und für diese testen
        ob sie zwischen min und max des Histogramms liegen.

    */

      Histogram histogram = new Histogram(data);
      for(int value : data){
        Assertions.assertTrue(value >= histogram.min() && value <= histogram.max());
      }

  }


}
