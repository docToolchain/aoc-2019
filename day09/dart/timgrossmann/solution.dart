import 'dart:io';

import './util/helper_classes.dart';
import 'util/util.dart';

int getParamWithInst(
    int inst, int index, int offset, int base, CustomList intCodes) {
  print('Inst: $inst, Index: $index, Offset: $offset, Base: $base');

  switch (inst) {
    // positioned mode
    case 0:
      return intCodes[index + offset];
      break;

    // immediate mode
    case 1:
      return index + offset;
      break;

    // positioned mode with base shift
    case 2:
      return intCodes[index + offset] + base;
      break;

    default:
      throw new UnsupportedError('Unsupported paramCode $inst');
  }
}

int iterateOpCodes(Amplifier currAmp) {
  CustomList intCodes = currAmp.instructions;

  for (int i = currAmp.pointer; i < intCodes.instructions.length;) {
    List<int> opCodes = parseOpCodes(intCodes[i]);
    // print(intCodes.instructions);

    if (opCodes[0] == 99) {
      currAmp.pointer = i;
      break;
    }

    int firstInst = opCodes[1];
    int secInst = opCodes[2];
    int thirdInst = opCodes[3];

    int firstParam =
        getParamWithInst(firstInst, i, 1, currAmp.relativeBase, intCodes);
    int secParam =
        getParamWithInst(secInst, i, 2, currAmp.relativeBase, intCodes);
    int outPos =
        getParamWithInst(thirdInst, i, 3, currAmp.relativeBase, intCodes);


    switch (opCodes[0]) {
      case 1:
        intCodes[outPos] = intCodes[firstParam] + intCodes[secParam];
        i += 4;
        print('Addition called: ${intCodes[firstParam]} + ${intCodes[secParam]} to $outPos');
        break;

      case 2:
        intCodes[outPos] = intCodes[firstParam] * intCodes[secParam];
        i += 4;
        print('Multiplication called: ${intCodes[firstParam]} * ${intCodes[secParam]} to $outPos');
        break;

      case 3:
        print('Please enter the System ID to run the test on:');
        int userIn = int.parse(stdin.readLineSync());

        intCodes[firstParam] = userIn;
        i += 2;
        print('Storing called: $userIn in ${firstParam}');
        break;

      case 4:
        i += 2;
        print('Printing called: ${intCodes[firstParam]}');
        currAmp.pointer = i;
        break;

      case 5:
        bool doJump = intCodes[firstParam] != 0;

        i = doJump ? intCodes[secParam] : i + 3;
        print('Jump if True: ${intCodes[firstParam]} is $doJump, ${intCodes[secParam]}');
        break;

      case 6:
        bool doJump = intCodes[firstParam] == 0;

        i = doJump ? intCodes[secParam] : i + 3;
        print('Jump if False: ${intCodes[firstParam]} is $doJump, ${intCodes[secParam]}');
        break;

      case 7:
        intCodes[outPos] = intCodes[firstParam] < intCodes[secParam] ? 1 : 0;
        print(
            'Store 1 if less else 0: ${intCodes[firstParam]} < ${intCodes[secParam]}, ${intCodes[outPos]} in in $outPos');
        i += 4;
        break;

      case 8:
        intCodes[outPos] = intCodes[firstParam] == intCodes[secParam] ? 1 : 0;
        print(
            'Store 1 if equal else 0: ${intCodes[firstParam]} = ${intCodes[secParam]}, ${intCodes[outPos]} in $outPos');
        i += 4;
        break; 

      case 9:
        currAmp.relativeBase += intCodes[firstParam];
        print(
            'Adjusting relative base of Amplifier $currAmp by ${intCodes[firstParam]} to ${currAmp.relativeBase}');
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
