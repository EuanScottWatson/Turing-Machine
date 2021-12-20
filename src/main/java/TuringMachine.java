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
  int pos = 0; // The position of the head
  String currentState;
  String endState;


  public TuringMachine(String s, Map<Entry, Value> f, String currentState, String endState) {
    this.s = s;
    this.f = f;
    this.currentState = currentState;
    this.endState = endState;
  }

  public String run() {
    while (!currentState.equals(endState)) {
      step();
    }

    return s;
  }

  public void step() {
    char symbolUnderHead = ' ';
    if (pos != -1 && pos != s.length()) {
      symbolUnderHead = s.charAt(pos);
    }
    Entry e = new Entry(currentState, symbolUnderHead);
    Value v = f.get(e);

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
  }

  public static void main(String[] args) {
    Map<Entry, Value> f = new HashMap<>();

    f.put(new Entry("right", '1'), new Value("right", '1', Direction.RIGHT));
    f.put(new Entry("right", '0'), new Value("right", '0', Direction.RIGHT));
    f.put(new Entry("right", ' '), new Value("carry", ' ', Direction.LEFT));

    f.put(new Entry("carry", '1'), new Value("carry", '0', Direction.LEFT));
    f.put(new Entry("carry", '0'), new Value("done", '1', Direction.LEFT));
    f.put(new Entry("carry", ' '), new Value("done", '1', Direction.LEFT));

    TuringMachine M = new TuringMachine("1111", f, "right", "done");

    System.out.println(M.run());
  }

}
