import 'dart:io';

import './util/helper_classes.dart';
import 'util/util.dart';

int getParamWithInst(
    int inst, int index, int offset, int base, CustomList intCodes) {
  
  print('Inst: $inst, Index: $index, Offset: $offset, Base: $base');
  
  switch (inst) {
    // positioned mode
    case 0:
      return intCodes[intCodes[index + offset]];
      break;

    // immediate mode
    case 1:
      return intCodes[index + offset];
      break;

    // positioned mode with base shift
    case 2:
      return intCodes[intCodes[index + offset] + base];
      break;

    default:
      throw new UnsupportedError('Unsupported paramCode $inst');
  }
}

int iterateOpCodes(Amplifier currAmp) {
  CustomList intCodes = currAmp.instructions;

  for (int i = currAmp.pointer; i < intCodes.instructions.length;) {
    List<int> opCodes = parseOpCodes(intCodes[i]);

    if (opCodes[0] == 99) {
      currAmp.pointer = i;
      break;
    }

    switch (opCodes[0]) {
      case 1:
        int firstInst = opCodes[1];
        int secInst = opCodes[2];

        int firstParam =
            getParamWithInst(firstInst, i, 1, currAmp.relativeBase, intCodes);
        int secParam =
            getParamWithInst(secInst, i, 2, currAmp.relativeBase, intCodes);
        int outPos = getParamWithInst(1, i, 3, currAmp.relativeBase, intCodes);

        intCodes[outPos] = firstParam + secParam;
        i += 4;
        print('Addition called: $firstParam + $secParam to $outPos');
        break;

      case 2:
        int firstInst = opCodes[1];
        int secInst = opCodes[2];

        int firstParam =
            getParamWithInst(firstInst, i, 1, currAmp.relativeBase, intCodes);
        int secParam =
            getParamWithInst(secInst, i, 2, currAmp.relativeBase, intCodes);
        int outPos = getParamWithInst(1, i, 3, currAmp.relativeBase, intCodes);

        intCodes[outPos] = firstParam * secParam;
        i += 4;
        print('Multiplication called: $firstParam * $secParam to $outPos');
        break;

      case 3:
        int firstParam = intCodes[i + 1];
        print('Please enter the System ID to run the test on:');
        int userIn = int.parse(stdin.readLineSync());

        intCodes[firstParam] = userIn;
        i += 2;
        print('Storing called: $userIn in $firstParam');
        break;

      case 4:
        int firstInst = opCodes[1];
        int firstParam =
            getParamWithInst(firstInst, i, 1, currAmp.relativeBase, intCodes);

        i += 2;
        print('Printing called: $firstParam');
        currAmp.pointer = i;

        break;

      case 5:
        int firstInst = opCodes[1];
        int secInst = opCodes[2];

        int firstParam =
            getParamWithInst(firstInst, i, 1, currAmp.relativeBase, intCodes);
        int secParam =
            getParamWithInst(secInst, i, 2, currAmp.relativeBase, intCodes);
        bool doJump = firstParam != 0;

        i = doJump ? secParam : i + 3;
        print('Jump if True: $firstParam is $doJump, $secParam');
        break;

      case 6:
        int firstInst = opCodes[1];
        int secInst = opCodes[2];

        int firstParam =
            getParamWithInst(firstInst, i, 1, currAmp.relativeBase, intCodes);
        int secParam =
            getParamWithInst(secInst, i, 2, currAmp.relativeBase, intCodes);
        bool doJump = firstParam == 0;

        i = doJump ? secParam : i + 3;
        print('Jump if False: $firstParam is $doJump, $secParam');
        break;

      case 7:
        int firstInst = opCodes[1];
        int secInst = opCodes[2];

        int firstParam =
            getParamWithInst(firstInst, i, 1, currAmp.relativeBase, intCodes);
        int secParam =
            getParamWithInst(secInst, i, 2, currAmp.relativeBase, intCodes);
        int outPos = getParamWithInst(1, i, 3, currAmp.relativeBase, intCodes);

        intCodes[outPos] = firstParam < secParam ? 1 : 0;
        print(
            'Store 1 if less else 0: $firstParam < $secParam, ${intCodes[outPos]}');
        i += 4;
        break;

      case 8:
        int firstInst = opCodes[1];
        int secInst = opCodes[2];

        int firstParam =
            getParamWithInst(firstInst, i, 1, currAmp.relativeBase, intCodes);
        int secParam =
            getParamWithInst(secInst, i, 2, currAmp.relativeBase, intCodes);
        int outPos = getParamWithInst(1, i, 3, currAmp.relativeBase, intCodes);

        intCodes[outPos] = firstParam == secParam ? 1 : 0;
        print(
            'Store 1 if equal else 0: $firstParam = $secParam, ${intCodes[outPos]}');
        i += 4;
        break;

      case 9:
        int firstInst = opCodes[1];

        int firstParam =
            getParamWithInst(firstInst, i, 1, currAmp.relativeBase, intCodes);

        currAmp.relativeBase += firstParam;
        print(
            'Adjusting relative base of Amplifier $currAmp by $firstParam to ${currAmp.relativeBase}');
        i += 2;
        break;

      default:
        throw new UnsupportedError(
            "This opcode is not yet supported! ${opCodes[0]}");
    }

    print('');
  }

  return -1;
}

int main(List<String> args) {
  File input = new File('./input.txt');

  List<int> intCodes =
      input.readAsStringSync().split(",").map(int.parse).toList();

  Amplifier amplifier = Amplifier(CustomList(List<int>.of(intCodes)));

  iterateOpCodes(amplifier);
  return 1;
}
