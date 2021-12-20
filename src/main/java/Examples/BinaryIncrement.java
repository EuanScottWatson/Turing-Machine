package Examples;

import TuringMachine.*;
import java.util.HashMap;
import java.util.Map;

public class BinaryIncrement {

  Map<Entry, Value> f = new HashMap<>();
  String b;

  TuringMachine M;

  public BinaryIncrement(String b) {
    f.put(new Entry("right", '1'), new Value("right", '1', Direction.RIGHT));
    f.put(new Entry("right", '0'), new Value("right", '0', Direction.RIGHT));
    f.put(new Entry("right", ' '), new Value("carry", ' ', Direction.LEFT));

    f.put(new Entry("carry", '1'), new Value("carry", '0', Direction.LEFT));
    f.put(new Entry("carry", '0'), new Value("done", '1', Direction.LEFT));
    f.put(new Entry("carry", ' '), new Value("done", '1', Direction.LEFT));

    this.b = b;
    M = new TuringMachine(b, f, "right", "done");
  }

  public String getS() {
    return M.getS();
  }

  public void runExample() {
    runExample(false);
  }

  public void runExample(boolean detailed) {
    M.run(detailed);
    System.out.format("%s + 1 = %s\n", b, M.getS());
  }

}
