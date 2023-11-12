package project01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Test;

public class RunLengthTests {

  // Note: this method can be used to run the unit test without a test framework (e.g. to debug)
  public static void main(String[] args) {
    RunLengthTests r = new RunLengthTests();
    r.example();
  }

  @Test
  void example() {
    List<String> input = List.of("A", "A", "B");
    List<Run<String>> expected = List.of(new Run<>(2, "A"), new Run<>(1, "B"));
    assertEquals(expected, RunLength.encode(input));
  }

  @Property
  void canDecode(@ForAll List<String> input) {
    List<Run<String>> encodedList = RunLength.encode(input);
    List<String> decodedList = RunLength.decode(encodedList);

    assertEquals(input, decodedList);
  }

  @Test
  void sumOfEncodedList() {
    List<Run<Integer>> encodedList = List.of(new Run<>(1, 4), new Run<>(2, 3));
    int sum = RunLength.sum(encodedList);
    assertEquals(10, sum);
  }

  public static Integer sum(List<Integer> elems) {
    return elems.stream().reduce(0, Integer::sum);
  }

  @Property
  void optimizedSum(@ForAll List<Integer> input) {
    List<Run<Integer>> encodedList = RunLength.encode(input);
    int optimizedSum = RunLength.sum(encodedList);
    int referenceSum = sum(input);

    assertEquals(referenceSum, optimizedSum);
  }

}
