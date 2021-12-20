package Examples;

import TuringMachine.Direction;
import TuringMachine.Entry;
import TuringMachine.TuringMachine;
import TuringMachine.Value;
import java.util.HashMap;
import java.util.Map;

public class ThreeStateBusyBeaver {

  Map<Entry, Value> f = new HashMap<>();
  String s = "000000";

  TuringMachine M;

  public ThreeStateBusyBeaver() {
    f.put(new Entry("A", '0'), new Value("B", '1', Direction.RIGHT));
    f.put(new Entry("A", ' '), new Value("B", '1', Direction.RIGHT));
    f.put(new Entry("A", '1'), new Value("H", '1', Direction.RIGHT));

    f.put(new Entry("B", '0'), new Value("C", '0', Direction.RIGHT));
    f.put(new Entry("B", ' '), new Value("C", '0', Direction.RIGHT));
    f.put(new Entry("B", '1'), new Value("B", '1', Direction.RIGHT));

    f.put(new Entry("C", '0'), new Value("C", '1', Direction.LEFT));
    f.put(new Entry("C", ' '), new Value("C", '1', Direction.LEFT));
    f.put(new Entry("C", '1'), new Value("A", '1', Direction.LEFT));

    M = new TuringMachine(s, f, "A", "H", 1);
  }

  public static int numberOfOnes(String output) {
    int occurences = output.replaceAll("0", "").length();
    System.out.format("\tIt contains %d 1s\n", occurences);
    return occurences;
  }

  public int runExample() {
    M.runBeaver();

    String output = M.getS();
    System.out.format("Output to the 3-state busy beaver is %s\n", output);
    return numberOfOnes(output);
  }

}
