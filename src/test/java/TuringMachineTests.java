import Examples.BinaryIncrement;
import Examples.DivisibleByThree;
import Examples.FourStateBusyBeaver;
import Examples.ThreeEqualLengths;
import Examples.ThreeStateBusyBeaver;
import org.junit.Test;

public class TuringMachineTests {

  @Test
  public void testBinaryIncrementNormal() {
    BinaryIncrement bi = new BinaryIncrement("1001");
    bi.runExample();
    assert(bi.getS().replaceAll(" ", "").equals("1010"));
  }

  @Test
  public void testBinaryIncrementCarry() {
    BinaryIncrement bi = new BinaryIncrement("11111");
    bi.runExample();
    assert(bi.getS().replaceAll(" ", "").equals("100000"));
  }

  @Test
  public void testDivisibleByThreeNormal() {
    DivisibleByThree dbt = new DivisibleByThree("1111");
    assert dbt.runExample();
  }

  @Test
  public void testDivisibleByThreeNotDivisible() {
    DivisibleByThree dbt = new DivisibleByThree("1011");
    assert !dbt.runExample();
  }

  @Test
  public void testDivisibleByThreeLong() {
    DivisibleByThree dbt = new DivisibleByThree("1000000001");
    assert dbt.runExample();
  }

  @Test
  public void testThreeEqualLengthsNormal() {
    ThreeEqualLengths tel = new ThreeEqualLengths("abc");
    assert tel.runExample();
  }

  @Test
  public void testThreeEqualLengthsNotTrue() {
    ThreeEqualLengths tel = new ThreeEqualLengths("aabbccc");
    assert !tel.runExample();
  }

  @Test
  public void testThreeEqualLengthsLong() {
    ThreeEqualLengths tel = new ThreeEqualLengths("aaaaabbbbbccccc");
    assert tel.runExample();
  }

  @Test
  public void threeState() {
    ThreeStateBusyBeaver tsbb = new ThreeStateBusyBeaver();
    assert tsbb.runExample() == 6;
  }

  @Test
  public void fourState() {
    FourStateBusyBeaver fsbb = new FourStateBusyBeaver();
    assert fsbb.runExample() == 13;
  }

}
