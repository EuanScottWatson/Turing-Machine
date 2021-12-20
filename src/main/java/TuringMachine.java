import java.util.HashMap;
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

  public TuringMachine(String s, Map<Entry, Value> f, String currentState, String endState, int initialPos) {
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

  public boolean step() {
    char symbolUnderHead = ' ';
    if (pos > -1 && pos != s.length()) {
      symbolUnderHead = s.charAt(pos);
    }
    Entry e = new Entry(currentState, symbolUnderHead);
    Value v = f.get(e);

    if (v == null) return false;

    String newS = s.substring(0, Math.max(0, Math.min(pos, s.length()))) + v.newSymbol;
    if (pos < s.length()) {
      newS += s.substring(pos+1);
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

  public static void incrementBinary(String b) {
    incrementBinary(b, false);
  }

  public static void incrementBinary(String b, boolean detailed) {
    Map<Entry, Value> f = new HashMap<>();

    f.put(new Entry("right", '1'), new Value("right", '1', Direction.RIGHT));
    f.put(new Entry("right", '0'), new Value("right", '0', Direction.RIGHT));
    f.put(new Entry("right", ' '), new Value("carry", ' ', Direction.LEFT));

    f.put(new Entry("carry", '1'), new Value("carry", '0', Direction.LEFT));
    f.put(new Entry("carry", '0'), new Value("done", '1', Direction.LEFT));
    f.put(new Entry("carry", ' '), new Value("done", '1', Direction.LEFT));

    TuringMachine M = new TuringMachine(b, f, "right", "done");

    M.run(detailed);
    System.out.format("%s + 1 = %s\n", b, M.s);
  }

  public static void divisibleBy3(String b) {
    divisibleBy3(b, false);
  }

  public static void divisibleBy3(String b, boolean detailed) {
    Map<Entry, Value> f = new HashMap<>();

    f.put(new Entry("q0", '0'), new Value("q0", '0', Direction.RIGHT));
    f.put(new Entry("q0", '1'), new Value("q1", '1', Direction.RIGHT));
    f.put(new Entry("q0", ' '), new Value("accept", ' ', Direction.RIGHT));

    f.put(new Entry("q1", '0'), new Value("q2", '0', Direction.RIGHT));
    f.put(new Entry("q1", '1'), new Value("q0", '1', Direction.RIGHT));

    f.put(new Entry("q2", '0'), new Value("q1", '0', Direction.RIGHT));
    f.put(new Entry("q2", '1'), new Value("q2", '1', Direction.RIGHT));

    TuringMachine M = new TuringMachine(b, f, "q0", "accept");

    if (M.run(detailed)) {
      System.out.format("%s mod 3 == 0\n", b);
    } else {
      System.out.format("%s mod 3 != 0\n", b);
    }
  }

  public static void threeEqualLengths(String s) {
    threeEqualLengths(s, false);
  }

  public static void threeEqualLengths(String s, boolean detailed) {
    Map<Entry, Value> f = new HashMap<>();

    f.put(new Entry("qA", 'a'), new Value("qB", 'A', Direction.RIGHT));
    f.put(new Entry("qA", 'B'), new Value("scan", 'B', Direction.RIGHT));

    f.put(new Entry("qB", 'a'), new Value("qB", 'a', Direction.RIGHT));
    f.put(new Entry("qB", 'B'), new Value("qB", 'B', Direction.RIGHT));
    f.put(new Entry("qB", 'b'), new Value("qC", 'B', Direction.RIGHT));

    f.put(new Entry("qC", 'b'), new Value("qC", 'b', Direction.RIGHT));
    f.put(new Entry("qC", 'C'), new Value("qC", 'C', Direction.RIGHT));
    f.put(new Entry("qC", 'c'), new Value("back", 'C', Direction.LEFT));

    f.put(new Entry("back", 'a'), new Value("back", 'a', Direction.LEFT));
    f.put(new Entry("back", 'B'), new Value("back", 'B', Direction.LEFT));
    f.put(new Entry("back", 'b'), new Value("back", 'b', Direction.LEFT));
    f.put(new Entry("back", 'C'), new Value("back", 'C', Direction.LEFT));
    f.put(new Entry("back", 'A'), new Value("qA", 'A', Direction.RIGHT));

    f.put(new Entry("scan", 'B'), new Value("scan", 'B', Direction.RIGHT));
    f.put(new Entry("scan", 'C'), new Value("scan", 'C', Direction.RIGHT));
    f.put(new Entry("scan", ' '), new Value("accept", ' ', Direction.RIGHT));

    TuringMachine M = new TuringMachine(s, f, "qA", "accept");

    if (M.run(detailed)) {
      System.out.format("%s has equal length characters\n", s);
    } else {
      System.out.format("%s does not have equal length characters\n", s);
    }
  }

  public static void threeStateBusyBeaver() {
    threeStateBusyBeaver(false);
  }

  public static void threeStateBusyBeaver(boolean detailed) {
    Map<Entry, Value> f = new HashMap<>();

    f.put(new Entry("A", '0'), new Value("B", '1', Direction.RIGHT));
    f.put(new Entry("A", ' '), new Value("B", '1', Direction.RIGHT));
    f.put(new Entry("A", '1'), new Value("H", '1', Direction.RIGHT));

    f.put(new Entry("B", '0'), new Value("C", '0', Direction.RIGHT));
    f.put(new Entry("B", ' '), new Value("C", '0', Direction.RIGHT));
    f.put(new Entry("B", '1'), new Value("B", '1', Direction.RIGHT));

    f.put(new Entry("C", '0'), new Value("C", '1', Direction.LEFT));
    f.put(new Entry("C", ' '), new Value("C", '1', Direction.LEFT));
    f.put(new Entry("C", '1'), new Value("A", '1', Direction.LEFT));

    TuringMachine M = new TuringMachine("000000", f, "A", "H", 1);

    M.run(detailed);
    System.out.format("Output to the 3-state busy beaver is %s\n", M.s);
  }

  public static void fourStateBusyBeaver() {
    fourStateBusyBeaver(false);
  }

  public static void fourStateBusyBeaver(boolean detailed) {
    Map<Entry, Value> f = new HashMap<>();

    f.put(new Entry("A", '0'), new Value("B", '1', Direction.RIGHT));
    f.put(new Entry("A", ' '), new Value("B", '1', Direction.RIGHT));
    f.put(new Entry("A", '1'), new Value("B", '1', Direction.LEFT));

    f.put(new Entry("B", '0'), new Value("A", '1', Direction.LEFT));
    f.put(new Entry("B", ' '), new Value("A", '1', Direction.LEFT));
    f.put(new Entry("B", '1'), new Value("C", '0', Direction.LEFT));

    f.put(new Entry("C", '0'), new Value("H", '1', Direction.RIGHT));
    f.put(new Entry("C", ' '), new Value("H", '1', Direction.RIGHT));
    f.put(new Entry("C", '1'), new Value("D", '1', Direction.LEFT));

    f.put(new Entry("D", '0'), new Value("D", '1', Direction.RIGHT));
    f.put(new Entry("D", ' '), new Value("D", '1', Direction.RIGHT));
    f.put(new Entry("D", '1'), new Value("A", '0', Direction.RIGHT));

    TuringMachine M = new TuringMachine("00000000000000", f, "A", "H", 10);

    M.run(detailed);
    System.out.format("Output to the 4-state busy beaver is %s\n", M.s);
  }

  public static void main(String[] args) {
//    incrementBinary("110111", false);
//    divisibleBy3("111001", false);
//    threeEqualLengths("aabbcc", false);
//    threeStateBusyBeaver(false);
    fourStateBusyBeaver(true);
  }

}
