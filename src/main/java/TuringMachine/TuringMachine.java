package TuringMachine;

import Examples.*;
import java.util.Map;

public class TuringMachine {
  // A Turing Machine is specified by a quadruple:
  // M = (Q, S, s, f)
  // where
  //    Q = finite set of machine states
  //    S = finite set of tape symbols
  //    s = initial state
  //    f = partial transition function

  String s;
  Map<Entry, Value> f;
  int pos; // The position of the head

  String currentState;
  String endState;

  public String getS() {
    return s;
  }

  public TuringMachine(String s, Map<Entry, Value> f, String currentState, String endState,
      int initialPos) {
    this.s = s;
    this.f = f;
    this.currentState = currentState;
    this.endState = endState;
    this.pos = initialPos;
  }

  public TuringMachine(String s, Map<Entry, Value> f, String currentState, String endState) {
    this.s = s;
    this.f = f;
    this.currentState = currentState;
    this.endState = endState;
    this.pos = 0;
  }

  public boolean run(boolean detailed) {
    while (!currentState.equals(endState)) {
      if (detailed) {
        String space = new String(new char[Math.max(0, pos)]).replace("\0", " ");
        System.out.println(s);
        System.out.println(space + "^");
      }

      if (!step()) {
        System.out.println("Failure");
        return false;
      }
    }

    return true;
  }

  public void runBeaver() {
    int step = 0;
    while (!currentState.equals("H")) {
      step();

      String space = new String(new char[Math.max(0, pos)]).replace("\0", " ");
      System.out.println(s);
      System.out.println(space + currentState);
      System.out.format("Step: %d\tOnes: %d\n", step, s.replaceAll("0", "").length());
      step++;
    }
  }

  public boolean step() {
    char symbolUnderHead = ' ';
    if (pos > -1 && pos != s.length()) {
      symbolUnderHead = s.charAt(pos);
    }
    Entry e = new Entry(currentState, symbolUnderHead);
    Value v = f.get(e);

    if (v == null) {
      return false;
    }

    String newS = s.substring(0, Math.max(0, Math.min(pos, s.length()))) + v.newSymbol;
    if (pos < s.length()) {
      newS += s.substring(pos + 1);
    }
    s = newS;

    currentState = v.newState;
    if (v.direction == Direction.LEFT) {
      pos--;
    } else {
      pos++;
    }

    return true;
  }

  public static void main(String[] args) {
//    new BinaryIncrement("10011").runExample();
//    new DivisibleByThree("1111").runExample();
//    new ThreeEqualLengths("aaaabbbbcccc").runExample();
//    new ThreeStateBusyBeaver().runExample(true);
//    new FourStateBusyBeaver().runExample();
  }

}
