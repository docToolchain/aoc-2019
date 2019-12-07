import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.MINUTES;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class IntcodeComputerTest {
  private static final Logger                 LOGGER      = Logger.getLogger(IntcodeComputerTest.class.getName());

  private final BlockingQueue<Integer> inputQueue  = new ArrayBlockingQueue<>(10);
  private final BlockingQueue<Integer> outputQueue = new ArrayBlockingQueue<>(10);

  @BeforeEach
  void cleanup() {
    inputQueue.clear();
  }

  @Test
  void thatExample1Works() {
    String input = "1,9,10,3,2,3,11,0,99,30,40,50";
    final int[] intcodeProgram = reinitializeMemory(input);

    interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(3500, intcodeProgram[0]);
  }

  @Test
  void thatExample2Works() {
    String input = "1,0,0,0,99";
    final int[] intcodeProgram = reinitializeMemory(input);

    interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(2, intcodeProgram[0]);
  }

  @Test
  void thatExample3Works() {
    String input = "2,3,0,3,99";
    final int[] intcodeProgram = reinitializeMemory(input);

    interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(6, intcodeProgram[3]);
  }

  @Test
  void thatExample4Works() {
    String input = "2,4,4,5,99,0";
    final int[] intcodeProgram = reinitializeMemory(input);

    interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(9801, intcodeProgram[5]);
  }

  @Test
  void thatExample5Works() {
    String input = "1,1,1,4,99,5,6,0,99";
    final int[] intcodeProgram = reinitializeMemory(input);

    interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(30, intcodeProgram[0]);
  }

  @Test
  void thatOpcode3TakesAnInput() {
    String input = "3,0,99";
    final int[] intcodeProgram = reinitializeMemory(input);
    inputQueue.add(30);

    interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(30, intcodeProgram[0]);
  }

  @Test
  void thatOpcode4OutputsAValue() {
    String program = "4,2,99";
    final int[] intcodeProgram = reinitializeMemory(program);

    interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(99, outputQueue.remove());
  }

  @Test
  void thatDay05InputOutputExampleWorks() {
    String program = "3,0,4,0,99";
    final int[] intcodeProgram = reinitializeMemory(program);
    inputQueue.add(42);

    interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(42, outputQueue.remove());
  }

  @Test
  void thatParameterModeWorks() {
    String program = "1002,4,3,4,33";
    final int[] intcodeProgram = reinitializeMemory(program);

    interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(99, intcodeProgram[4]);
  }

  @Test
  void thatNegativeIntegersWereTreated() {
    String program = "1101,100,-1,4,0";
    final int[] intcodeProgram = reinitializeMemory(program);

    interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(99, intcodeProgram[4]);
  }

  @ParameterizedTest(name = "when {0} is less than 8 the output is {1}")
  @CsvSource({"8,0","7,1"})
  void thatLessThanWorksInPositionMode(int input, int output) {
    String program = "3,9,7,9,10,9,4,9,99,-1,8";
    final int[] intcodeProgram = reinitializeMemory(program);
    inputQueue.add(input);

    interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(output, outputQueue.remove());
  }

  @ParameterizedTest(name = "when {0} is less than 8 the output is {1}")
  @CsvSource({"8,0","7,1"})
  void thatLessThanWorksInImmediateMode(int input, int output) {
    String program = "3,3,1107,-1,8,3,4,3,99";
    final int[] intcodeProgram = reinitializeMemory(program);
    inputQueue.add(input);

    interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(output, outputQueue.remove());
  }

  @ParameterizedTest
  @CsvSource({"8,1","7,0"})
  void thatEqualsWorksWithPositionMode(int input, int output) {
    String program = "3,9,8,9,10,9,4,9,99,-1,8";
    final int[] intcodeProgram = reinitializeMemory(program);
    inputQueue.add(input);

    interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(output, outputQueue.remove());
  }

  @ParameterizedTest
  @CsvSource({"8,1","7,0"})
  void thatEqualsWorksWithImmediateMode(int input, int output) {
    String program = "3,3,1108,-1,8,3,4,3,99";
    final int[] intcodeProgram = reinitializeMemory(program);
    inputQueue.add(input);

    interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(output, outputQueue.remove());
  }

  @ParameterizedTest
  @CsvSource({"0,0", "42,1"})
  void thatJumpsWorkInPositionMode(int input, int output) {
    String program = "3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9";
    final int[] intcodeProgram = reinitializeMemory(program);
    inputQueue.add(input);

    interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(output, outputQueue.remove());
  }

  @ParameterizedTest
  @CsvSource({"0,0", "42,1"})
  void thatJumpsWorkInImmediateMode(int input, int output) {
    String program = "3,3,1105,-1,9,1101,0,0,12,4,12,99,1";
    final int[] intcodeProgram = reinitializeMemory(program);
    inputQueue.add(input);

    interpret(inputQueue, outputQueue, intcodeProgram);

    assertEquals(output, outputQueue.remove());
  }

  @Disabled("For manual execution of the riddle")
  @Test
  void day05Part1() {
    String program = "99";
    final int[] intcodeProgram = reinitializeMemory(program);
    inputQueue.add(1);

    interpret(inputQueue, outputQueue, intcodeProgram);

    LOGGER.info(format("Output queue has %d elements", outputQueue.size()));
    for (Integer output : outputQueue) {
      LOGGER.info(format("Program output: %d", output));
    }
  }

  @Disabled("For manual execution of the riddle")
  @Test
  void day05Part2() {
    String program = "99";
    final int[] intcodeProgram = reinitializeMemory(program);
    inputQueue.add(5);

    interpret(inputQueue, outputQueue, intcodeProgram);

    LOGGER.info(format("Output queue has %d elements", outputQueue.size()));
    for (Integer output : outputQueue) {
      LOGGER.info(format("Program output: %d", output));
    }
  }

  @Test
  void day07Example1() {
    String program = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0";

    final int maxOutputSignal = maxOutputSignal(program);

    assertEquals(43210, maxOutputSignal);
  }

  @Test
  void day07Example2() {
    String program = "3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0";

    final int maxOutputSignal = maxOutputSignal(program);

    assertEquals(54321, maxOutputSignal);
  }

  @Test
  void day07Example3() {
    String program = "3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0";

    final int maxOutputSignal = maxOutputSignal(program);

    assertEquals(65210, maxOutputSignal);
  }

  @Test
  void day07Part1() {
    String program = "3,8,1001,8,10,8,105,1,0,0,21,42,67,84,109,126,207,288,369,450,99999,3,9,102,4,9,9,1001,9,4,9,102,2,9,9,101,2,9,9,4,9,99,3,9,1001,9,5,9,1002,9,5,9,1001,9,5,9,1002,9,5,9,101,5,9,9,4,9,99,3,9,101,5,9,9,1002,9,3,9,1001,9,2,9,4,9,99,3,9,1001,9,2,9,102,4,9,9,101,2,9,9,102,4,9,9,1001,9,2,9,4,9,99,3,9,102,2,9,9,101,5,9,9,1002,9,2,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,99,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,99,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,99";

    final int maxOutputSignal = maxOutputSignal(program);

    assertEquals(298586, maxOutputSignal);
  }

  @Test
  void day07Part2Example1() {

    int thrusterSignal = maxOutputSignalWithFeedback(
        "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5"
    );

    assertEquals(139629729, thrusterSignal);
  }

  @Test
  void day07Part2Example2() {

    int thrusterSignal = maxOutputSignalWithFeedback(
      "3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10"
    );

    assertEquals(18216, thrusterSignal);
  }

  @Test
  void day07Part2() {
    String program = "3,8,1001,8,10,8,105,1,0,0,21,42,67,84,109,126,207,288,369,450,99999,3,9,102,4,9,9,1001,9,4,9,102,2,9,9,101,2,9,9,4,9,99,3,9,1001,9,5,9,1002,9,5,9,1001,9,5,9,1002,9,5,9,101,5,9,9,4,9,99,3,9,101,5,9,9,1002,9,3,9,1001,9,2,9,4,9,99,3,9,1001,9,2,9,102,4,9,9,101,2,9,9,102,4,9,9,1001,9,2,9,4,9,99,3,9,102,2,9,9,101,5,9,9,1002,9,2,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,99,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,99,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,99";

    final int maxOutputSignal = maxOutputSignalWithFeedback(program);

    assertEquals(9246095, maxOutputSignal);
  }

  private int maxOutputSignal(String program) {
    final int[] maxOutputSignal = { 0 };
    permute(new int[]{0,1,2,3,4}, 0, sequence -> {
      var outputSignal = amplifierSequence(program, sequence);
      if(outputSignal > maxOutputSignal[0]) {
        maxOutputSignal[0] = outputSignal;
      }
    });
    return maxOutputSignal[0];
  }

  private int maxOutputSignalWithFeedback(String program) {
    final int[] maxOutputSignal = { 0 };
    permute(new int[]{5,6,7,8,9}, 0, sequence -> {
      var outputSignal = amplifierRunningParallel(program, sequence);
      if(outputSignal > maxOutputSignal[0]) {
        maxOutputSignal[0] = outputSignal;
      }
    });
    return maxOutputSignal[0];
  }
  /*
    With a little help from https://stackoverflow.com/questions/2920315/permutation-of-array
    Kudos!
   */

  private void permute(int[] sequence, int start, Consumer<int[]> consumer) {
    for(int i = start; i < sequence.length; i++){
      int temp = sequence[start];
      sequence[start] = sequence[i];
      sequence[i] = temp;
      permute(sequence, start + 1, consumer);
      sequence[i] = sequence[start];
      sequence[start] = temp;
    }
    if (start == sequence.length - 1) {
      consumer.accept(sequence);
    }
  }

  private Integer amplifierSequence(String program, int[] phaseSettingSequence) {
    int[] intcodeProgram = reinitializeMemory(program);
    inputQueue.add(phaseSettingSequence[0]);
    inputQueue.add(0);
    interpret(inputQueue, outputQueue, intcodeProgram);
    var amplifierAOutput = outputQueue.remove();

    intcodeProgram = reinitializeMemory(program);
    inputQueue.add(phaseSettingSequence[1]);
    inputQueue.add(amplifierAOutput);
    interpret(inputQueue, outputQueue, intcodeProgram);
    var amplifierBOutput = outputQueue.remove();

    intcodeProgram = reinitializeMemory(program);
    inputQueue.add(phaseSettingSequence[2]);
    inputQueue.add(amplifierBOutput);
    interpret(inputQueue, outputQueue, intcodeProgram);
    var amplifierCOutput = outputQueue.remove();

    intcodeProgram = reinitializeMemory(program);
    inputQueue.add(phaseSettingSequence[3]);
    inputQueue.add(amplifierCOutput);
    interpret(inputQueue, outputQueue, intcodeProgram);
    var amplifierDOutput = outputQueue.remove();

    intcodeProgram = reinitializeMemory(program);
    inputQueue.add(phaseSettingSequence[4]);
    inputQueue.add(amplifierDOutput);
    interpret(inputQueue, outputQueue, intcodeProgram);

    return outputQueue.remove();
  }

  private Integer amplifierRunningParallel(String program, int[] phaseSettingSequence) {
    final BlockingQueue<Integer> inputQueueAmpA  = new ArrayBlockingQueue<>(10);
    final BlockingQueue<Integer> inputQueueAmpB  = new ArrayBlockingQueue<>(10);
    final BlockingQueue<Integer> inputQueueAmpC = new ArrayBlockingQueue<>(10);
    final BlockingQueue<Integer> inputQueueAmpD  = new ArrayBlockingQueue<>(10);
    final BlockingQueue<Integer> inputQueueAmpE  = new ArrayBlockingQueue<>(10);
    final CountDownLatch done = new CountDownLatch(1);

    inputQueueAmpA.add(phaseSettingSequence[0]);
    inputQueueAmpB.add(phaseSettingSequence[1]);
    inputQueueAmpC.add(phaseSettingSequence[2]);
    inputQueueAmpD.add(phaseSettingSequence[3]);
    inputQueueAmpE.add(phaseSettingSequence[4]);

    inputQueueAmpA.add(0);

    new Thread(() -> interpret(inputQueueAmpA, inputQueueAmpB, reinitializeMemory(program))).start();
    new Thread(() -> interpret(inputQueueAmpB, inputQueueAmpC, reinitializeMemory(program))).start();
    new Thread(() -> interpret(inputQueueAmpC, inputQueueAmpD, reinitializeMemory(program))).start();
    new Thread(() -> interpret(inputQueueAmpD, inputQueueAmpE, reinitializeMemory(program))).start();
    new Thread(() -> {
      interpret(inputQueueAmpE, inputQueueAmpA, reinitializeMemory(program));
      done.countDown();
    }).start();

    try {
      done.await(5, MINUTES);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    return inputQueueAmpA.remove();
  }

  private void interpret(BlockingQueue<Integer> inputQueue, BlockingQueue<Integer> outputQueue, int... intcodeProgram) {
    int instructionPointer = 0;

    while (true) {

      try {
        int opcode = intcodeProgram[instructionPointer];
        int mode3;
        int mode2 = 0;
        int mode1 = 0;
        if(opcode >= 10_000) {
          mode3 = opcode / 10_000;
          opcode = opcode - mode3 * 10_000;
        }
        if(opcode >= 1_000) {
          mode2 = opcode / 1_000;
          opcode = opcode - mode2 * 1_000;
        }
        if(opcode >= 100) {
          mode1 = opcode / 100;
          opcode = opcode - mode1 * 100;
        }

        switch (opcode) {
          case 1 :
          {
            // add
            int readAddress1 = intcodeProgram[instructionPointer + 1];
            int readAddress2 = intcodeProgram[instructionPointer + 2];
            int writeAddress = intcodeProgram[instructionPointer + 3];
            final int value1 = mode1 == 0 ? intcodeProgram[readAddress1] : readAddress1;
            final int value2 = mode2 == 0 ? intcodeProgram[readAddress2] : readAddress2;
            int akku = value1 + value2;
            intcodeProgram[writeAddress] = akku;
            instructionPointer += 4;
            break;
          }
          case 2 :
          {
            // mult
            int readAddress1 = intcodeProgram[instructionPointer + 1];
            int readAddress2 = intcodeProgram[instructionPointer + 2];
            int writeAddress = intcodeProgram[instructionPointer + 3];
            final int value1 = mode1 == 0 ? intcodeProgram[readAddress1] : readAddress1;
            final int value2 = mode2 == 0 ? intcodeProgram[readAddress2] : readAddress2;
            int akku = value1 * value2;
            intcodeProgram[writeAddress] = akku;
            instructionPointer += 4;
            break;
          }
          case 3:
          {
            // input
            final Integer value = inputQueue.poll(1, DAYS);
            int writeAddress = intcodeProgram[instructionPointer + 1];
            intcodeProgram[writeAddress] = value;
            instructionPointer += 2;
            break;
          }
          case 4:
          {
            // output
            int readAddress1 = intcodeProgram[instructionPointer + 1];
            final int value = mode1 == 0 ? intcodeProgram[readAddress1] : readAddress1;
            outputQueue.add(value);
            instructionPointer += 2;
            break;
          }
          case 5:
          {
            // jump-if-true
            int readAddress1 = intcodeProgram[instructionPointer + 1];
            int readAddress2 = intcodeProgram[instructionPointer + 2];
            final int value1 = mode1 == 0 ? intcodeProgram[readAddress1] : readAddress1;
            final int value2 = mode2 == 0 ? intcodeProgram[readAddress2] : readAddress2;
            if(value1 != 0) {
              instructionPointer = value2;
            } else {
              instructionPointer += 3;
            }
            break;
          }
          case 6:
          {
            // jump-if-false
            int readAddress1 = intcodeProgram[instructionPointer + 1];
            int readAddress2 = intcodeProgram[instructionPointer + 2];
            final int value1 = mode1 == 0 ? intcodeProgram[readAddress1] : readAddress1;
            final int value2 = mode2 == 0 ? intcodeProgram[readAddress2] : readAddress2;
            if(value1 == 0) {
              instructionPointer = value2;
            } else {
              instructionPointer += 3;
            }
            break;
          }
          case 7:
          {
            // less than
            int readAddress1 = intcodeProgram[instructionPointer + 1];
            int readAddress2 = intcodeProgram[instructionPointer + 2];
            int writeAddress = intcodeProgram[instructionPointer + 3];
            final int value1 = mode1 == 0 ? intcodeProgram[readAddress1] : readAddress1;
            final int value2 = mode2 == 0 ? intcodeProgram[readAddress2] : readAddress2;
            int akku = value1 < value2 ? 1 : 0;
            intcodeProgram[writeAddress] = akku;
            instructionPointer += 4;
            break;
          }
          case 8:
          {
            // less than
            int readAddress1 = intcodeProgram[instructionPointer + 1];
            int readAddress2 = intcodeProgram[instructionPointer + 2];
            int writeAddress = intcodeProgram[instructionPointer + 3];
            final int value1 = mode1 == 0 ? intcodeProgram[readAddress1] : readAddress1;
            final int value2 = mode2 == 0 ? intcodeProgram[readAddress2] : readAddress2;
            int akku = value1 == value2 ? 1 : 0;
            intcodeProgram[writeAddress] = akku;
            instructionPointer += 4;
            break;
          }
          case 99 :
            // stop
            return;
          default :
            LOGGER.severe("Execution error. Unknown opcode " + opcode);
            return;
        }
      } catch (Exception e) {
        LOGGER.log(Level.SEVERE, e.toString(), e);
        return;
      }

    }
  }


  public static int[] reinitializeMemory(String input) {
    int index = 0;
    String[] parts = input.split(",");
    int[] intcodeProgram = new int[parts.length];
    for (String part : parts) {
      intcodeProgram[index] = Integer.valueOf(part);
      index++;
    }
    return intcodeProgram;
  }
}
