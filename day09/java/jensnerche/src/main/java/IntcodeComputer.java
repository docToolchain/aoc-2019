import static java.util.concurrent.TimeUnit.DAYS;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

class IntcodeComputer {
  private static final Logger LOGGER = Logger.getLogger(IntcodeComputer.class.getName());

  private long relativeBase = 0;
  private final ArrayList<Long> extraMemory = new ArrayList<>(1000);

  static long[] reinitializeMemory(String input) {
    int index = 0;
    String[] parts = input.split(",");
    long[] intcodeProgram = new long[parts.length];
    for (String part : parts) {
      intcodeProgram[index] = Long.valueOf(part);
      index++;
    }
    return intcodeProgram;
  }

  void interpret(BlockingQueue<Long> inputQueue, BlockingQueue<Long> outputQueue, String input) {
    interpret(inputQueue, outputQueue, reinitializeMemory(input));
  }

  void interpret(BlockingQueue<Long> inputQueue, BlockingQueue<Long> outputQueue, long... intcodeProgram) {
    int instructionPointer = 0;

    while (true) {

      try {
        long opcode = intcodeProgram[instructionPointer];
        long mode3 = 0;
        long mode2 = 0;
        long mode1 = 0;
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

        switch (Math.toIntExact(opcode)) {
          case 1 :
          {
            // add
            long readAddress1 = intcodeProgram[instructionPointer + 1];
            long readAddress2 = intcodeProgram[instructionPointer + 2];
            long writeAddress = intcodeProgram[instructionPointer + 3];
            final long value1 = parameterFor(mode1, readAddress1, intcodeProgram);
            final long value2 = parameterFor(mode2, readAddress2, intcodeProgram);
            writeMemory(mode3, writeAddress, value1 + value2, intcodeProgram);
            instructionPointer += 4;
            break;
          }
          case 2 :
          {
            // mult
            long readAddress1 = intcodeProgram[instructionPointer + 1];
            long readAddress2 = intcodeProgram[instructionPointer + 2];
            long writeAddress = intcodeProgram[instructionPointer + 3];
            final long value1 = parameterFor(mode1, readAddress1, intcodeProgram);
            final long value2 = parameterFor(mode2, readAddress2, intcodeProgram);
            writeMemory(mode3, writeAddress, value1 * value2, intcodeProgram);
            instructionPointer += 4;
            break;
          }
          case 3:
          {
            // input
            final Long value = inputQueue.poll(1, DAYS);
            long writeAddress = intcodeProgram[instructionPointer + 1];
            writeMemory(mode1, writeAddress, value, intcodeProgram);
            instructionPointer += 2;
            break;
          }
          case 4:
          {
            // output
            long readAddress1 = intcodeProgram[instructionPointer + 1];
            final long value = parameterFor(mode1, readAddress1, intcodeProgram);
            outputQueue.add(value);
            instructionPointer += 2;
            break;
          }
          case 5:
          {
            // jump-if-true
            long readAddress1 = intcodeProgram[instructionPointer + 1];
            long readAddress2 = intcodeProgram[instructionPointer + 2];
            final long value1 = parameterFor(mode1, readAddress1, intcodeProgram);
            final long value2 = parameterFor(mode2, readAddress2, intcodeProgram);
            if(value1 != 0L) {
              instructionPointer = Math.toIntExact(value2);
            } else {
              instructionPointer += 3;
            }
            break;
          }
          case 6:
          {
            // jump-if-false
            long readAddress1 = intcodeProgram[instructionPointer + 1];
            long readAddress2 = intcodeProgram[instructionPointer + 2];
            final long value1 = parameterFor(mode1, readAddress1, intcodeProgram);
            final long value2 = parameterFor(mode2, readAddress2, intcodeProgram);
            if(value1 == 0L) {
              instructionPointer = Math.toIntExact(value2);
            } else {
              instructionPointer += 3;
            }
            break;
          }
          case 7:
          {
            // less than
            long readAddress1 = intcodeProgram[instructionPointer + 1];
            long readAddress2 = intcodeProgram[instructionPointer + 2];
            long writeAddress = intcodeProgram[instructionPointer + 3];
            final long value1 = parameterFor(mode1, readAddress1, intcodeProgram);
            final long value2 = parameterFor(mode2, readAddress2, intcodeProgram);
            writeMemory(mode3, writeAddress, value1 < value2 ? 1 : 0, intcodeProgram);
            instructionPointer += 4;
            break;
          }
          case 8:
          {
            // less than
            long readAddress1 = intcodeProgram[instructionPointer + 1];
            long readAddress2 = intcodeProgram[instructionPointer + 2];
            long writeAddress = intcodeProgram[instructionPointer + 3];
            final long value1 = parameterFor(mode1, readAddress1, intcodeProgram);
            final long value2 = parameterFor(mode2, readAddress2, intcodeProgram);
            writeMemory(mode3, writeAddress, value1 == value2 ? 1 : 0, intcodeProgram);
            instructionPointer += 4;
            break;
          }
          case 9:
          {
            // modify relative base
            long readAddress1 = intcodeProgram[instructionPointer + 1];
            final long value1 = parameterFor(mode1, readAddress1, intcodeProgram);
            relativeBase += value1;
            instructionPointer += 2;
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

  private void writeMemory(long mode, long address, long value, long[] intcodeProgram) {
    if(mode == 0L) {
      writeMemory(address, value, intcodeProgram);
      return;
    }
    if(mode == 2L) {
      writeMemory(relativeBase + address, value, intcodeProgram);
      return;
    }

    throw new IllegalArgumentException("Illegal mode for write operations: " + mode);
  }

  private long parameterFor(long mode, long readAddress, long[] intcodeProgram) {
    if (mode == 0L) {
      return readMemory(readAddress, intcodeProgram);
    }
    if (mode == 2L) {
      return readMemory(relativeBase + readAddress, intcodeProgram);
    }
    return readAddress;
  }

  private long readMemory(long readAddress, long[] intcodeProgram) {
    if(readAddress < intcodeProgram.length) {
      return intcodeProgram[Math.toIntExact(readAddress)];
    }
    final long extraMemoryOffset = readAddress - intcodeProgram.length;
    ensureCapacity(extraMemoryOffset + 1);
    return extraMemory.get(Math.toIntExact(extraMemoryOffset));
  }

  private void writeMemory(long address, long value, long[] intcodeProgram) {
    if(address < intcodeProgram.length) {
      intcodeProgram[Math.toIntExact(address)] = value;
      return;
    }

    final long extraMemoryOffset = address - intcodeProgram.length;
    ensureCapacity(extraMemoryOffset + 1);
    extraMemory.set(Math.toIntExact(extraMemoryOffset), value);
  }

  private void ensureCapacity(long extraMemoryOffset) {
    final int currentCapacity = extraMemory.size();
    final long neededSpace = extraMemoryOffset - currentCapacity;
    if(neededSpace <= 0) {
      return;
    }
    for(int i = 0; i < neededSpace; i++) {
      extraMemory.add(0L);
    }

  }

}
