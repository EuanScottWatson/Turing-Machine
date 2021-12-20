package Examples;

import TuringMachine.Direction;
import TuringMachine.Entry;
import TuringMachine.TuringMachine;
import TuringMachine.Value;
import java.util.HashMap;
import java.util.Map;

public class ThreeEqualLengths {

  Map<Entry, Value> f = new HashMap<>();
  String s;

  TuringMachine M;

  public ThreeEqualLengths(String s) {
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

    this.s = s;
    M = new TuringMachine(s, f, "qA", "accept");
  }

  public boolean runExample() {
    return runExample(false);
  }

  public boolean runExample(boolean detailed) {

    if (M.run(detailed)) {
      System.out.format("%s has equal length characters\n", s);
      return true;
    } else {
      System.out.format("%s does not have equal length characters\n", s);
      return false;
    }
  }

}
