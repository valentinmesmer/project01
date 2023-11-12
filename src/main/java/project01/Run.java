package de.vale.fsv.project01;

// If you don't like code like this, maybe you find Scala really nice :)
public class Run<T> {
  final int count;
  final T elem;

  public Run(int count, T elem) {
    this.count = count;
    this.elem = elem;
  }

  public int count() {
    return count;
  }

  public T elem() {
    return elem;
  }

  public String toString() {
    return "Run[count=" + count + ",elem=" + elem + "]";
  }

  public boolean equals(Object other) {
    if (other instanceof Run that) {
      return this.count == that.count && this.elem.equals(that.elem);
    } else {
      return false;
    }
  }
}
