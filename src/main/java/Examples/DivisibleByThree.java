package Examples;

import TuringMachine.Direction;
import TuringMachine.Entry;
import TuringMachine.TuringMachine;
import TuringMachine.Value;
import java.util.HashMap;
import java.util.Map;

public class DivisibleByThree {

  Map<Entry, Value> f = new HashMap<>();
  String b;

  TuringMachine M;

  public DivisibleByThree(String b) {
    f.put(new Entry("q0", '0'), new Value("q0", '0', Direction.RIGHT));
    f.put(new Entry("q0", '1'), new Value("q1", '1', Direction.RIGHT));
    f.put(new Entry("q0", ' '), new Value("accept", ' ', Direction.RIGHT));

    f.put(new Entry("q1", '0'), new Value("q2", '0', Direction.RIGHT));
    f.put(new Entry("q1", '1'), new Value("q0", '1', Direction.RIGHT));

    f.put(new Entry("q2", '0'), new Value("q1", '0', Direction.RIGHT));
    f.put(new Entry("q2", '1'), new Value("q2", '1', Direction.RIGHT));

    this.b = b;
    M = new TuringMachine(b, f, "q0", "accept");
  }

  public void runExample() {
    runExample(false);
  }

  public void runExample(boolean detailed) {
    if (M.run(detailed)) {
      System.out.format("%s mod 3 == 0\n", b);
    } else {
      System.out.format("%s mod 3 != 0\n", b);
    }
  }

}
