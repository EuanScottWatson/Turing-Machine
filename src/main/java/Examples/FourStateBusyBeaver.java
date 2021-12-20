package Examples;

import static Examples.ThreeStateBusyBeaver.numberOfOnes;

import TuringMachine.Direction;
import TuringMachine.Entry;
import TuringMachine.TuringMachine;
import TuringMachine.Value;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class FourStateBusyBeaver {

  Map<Entry, Value> f = new HashMap<>();
  String s = "00000000000000";

  TuringMachine M;

  public FourStateBusyBeaver() {
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

    M = new TuringMachine(s, f, "A", "H", 10);
  }

  public int runExample() {
    return runExample(false);
  }

  public int runExample(boolean detailed) {
    M.run(detailed);
    String output = M.getS();
    System.out.format("Output to the 4-state busy beaver is %s\n", output);
    return numberOfOnes(output);
  }

}
