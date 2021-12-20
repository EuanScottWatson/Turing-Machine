package TuringMachine;

import java.util.Objects;

public class Value {

  String newState;
  Character newSymbol;
  Direction direction;

  public Value(String newState, Character newSymbol, Direction direction) {
    this.newState = newState;
    this.newSymbol = newSymbol;
    this.direction = direction;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Value value = (Value) o;
    return Objects.equals(newState, value.newState) &&
        Objects.equals(newSymbol, value.newSymbol) &&
        direction == value.direction;
  }

  @Override
  public int hashCode() {
    return Objects.hash(newState, newSymbol, direction);
  }

  @Override
  public String toString() {
    return "TuringMachine.Value{" +
        "newState='" + newState + '\'' +
        ", newSymbol=" + newSymbol +
        ", direction=" + direction +
        '}';
  }
}


