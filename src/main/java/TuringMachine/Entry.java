package TuringMachine;

import java.util.Objects;

public class Entry {

  String state;
  Character symbol;

  public Entry(String state, Character symbol) {
    this.state = state;
    this.symbol = symbol;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Entry entry = (Entry) o;
    return Objects.equals(state, entry.state) &&
        Objects.equals(symbol, entry.symbol);
  }

  @Override
  public int hashCode() {
    return Objects.hash(state, symbol);
  }

  @Override
  public String toString() {
    return "TuringMachine.Entry{" +
        "state='" + state + '\'' +
        ", symbol=" + symbol +
        '}';
  }
}
