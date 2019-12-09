import static java.util.concurrent.TimeUnit.MINUTES;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

class AmplifierSequence {
  private final BlockingQueue<Long> inputQueue  = new ArrayBlockingQueue<>(10);
  private final BlockingQueue<Long> outputQueue = new ArrayBlockingQueue<>(10);

  static long maxOutputSignal(String program) {
    final long[] maxOutputSignal = { 0 };
    new AmplifierSequence().permute(new int[]{0,1,2,3,4}, 0, sequence -> {
      var outputSignal = new AmplifierSequence().amplifierSequence(program, sequence);
      if(outputSignal > maxOutputSignal[0]) {
        maxOutputSignal[0] = outputSignal;
      }
    });
    return maxOutputSignal[0];
  }

  static long maxOutputSignalWithFeedback(String program) {
    final long[] maxOutputSignal = { 0 };
    new AmplifierSequence().permute(new int[]{5,6,7,8,9}, 0, sequence -> {
      var outputSignal = new AmplifierSequence().amplifierRunningParallel(program, sequence);
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

  private Long amplifierSequence(String program, int[] phaseSettingSequence) {
    long[] intcodeProgram = IntcodeComputer.reinitializeMemory(program);
    inputQueue.add((long) phaseSettingSequence[0]);
    inputQueue.add(0L);
    new IntcodeComputer().interpret(inputQueue, outputQueue, intcodeProgram);
    var amplifierAOutput = outputQueue.remove();

    intcodeProgram = IntcodeComputer.reinitializeMemory(program);
    inputQueue.add((long) phaseSettingSequence[1]);
    inputQueue.add(amplifierAOutput);
    new IntcodeComputer().interpret(inputQueue, outputQueue, intcodeProgram);
    var amplifierBOutput = outputQueue.remove();

    intcodeProgram = IntcodeComputer.reinitializeMemory(program);
    inputQueue.add((long) phaseSettingSequence[2]);
    inputQueue.add(amplifierBOutput);
    new IntcodeComputer().interpret(inputQueue, outputQueue, intcodeProgram);
    var amplifierCOutput = outputQueue.remove();

    intcodeProgram = IntcodeComputer.reinitializeMemory(program);
    inputQueue.add((long) phaseSettingSequence[3]);
    inputQueue.add(amplifierCOutput);
    new IntcodeComputer().interpret(inputQueue, outputQueue, intcodeProgram);
    var amplifierDOutput = outputQueue.remove();

    intcodeProgram = IntcodeComputer.reinitializeMemory(program);
    inputQueue.add((long) phaseSettingSequence[4]);
    inputQueue.add(amplifierDOutput);
    new IntcodeComputer().interpret(inputQueue, outputQueue, intcodeProgram);

    return outputQueue.remove();
  }

  private Long amplifierRunningParallel(String program, int[] phaseSettingSequence) {
    final BlockingQueue<Long> inputQueueAmpA  = new ArrayBlockingQueue<>(10);
    final BlockingQueue<Long> inputQueueAmpB  = new ArrayBlockingQueue<>(10);
    final BlockingQueue<Long> inputQueueAmpC = new ArrayBlockingQueue<>(10);
    final BlockingQueue<Long> inputQueueAmpD  = new ArrayBlockingQueue<>(10);
    final BlockingQueue<Long> inputQueueAmpE  = new ArrayBlockingQueue<>(10);
    final CountDownLatch done = new CountDownLatch(1);

    inputQueueAmpA.add((long) phaseSettingSequence[0]);
    inputQueueAmpB.add((long) phaseSettingSequence[1]);
    inputQueueAmpC.add((long) phaseSettingSequence[2]);
    inputQueueAmpD.add((long) phaseSettingSequence[3]);
    inputQueueAmpE.add((long) phaseSettingSequence[4]);

    inputQueueAmpA.add(0L);

    new Thread(
        () -> new IntcodeComputer().interpret(inputQueueAmpA, inputQueueAmpB,
                                              IntcodeComputer.reinitializeMemory(program))).start();
    new Thread(
        () -> new IntcodeComputer().interpret(inputQueueAmpB, inputQueueAmpC,
                                              IntcodeComputer.reinitializeMemory(program))).start();
    new Thread(
        () -> new IntcodeComputer().interpret(inputQueueAmpC, inputQueueAmpD,
                                              IntcodeComputer.reinitializeMemory(program))).start();
    new Thread(
        () -> new IntcodeComputer().interpret(inputQueueAmpD, inputQueueAmpE,
                                              IntcodeComputer.reinitializeMemory(program))).start();
    new Thread(() -> {
      new IntcodeComputer().interpret(inputQueueAmpE, inputQueueAmpA, IntcodeComputer.reinitializeMemory(program));
      done.countDown();
    }).start();

    try {
      done.await(5, MINUTES);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    return inputQueueAmpA.remove();
  }

}
