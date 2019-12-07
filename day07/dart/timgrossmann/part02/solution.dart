import 'dart:io';

class Amplifier {
  int pointer = 0;
  int phaseSetting;
  List<int> instructions;

  Amplifier(this.instructions);

  @override
  String toString() {
    return '$pointer, $phaseSetting';
  }
}

List<int> parseOpCodes(int number) {
  String operationDesc = number.toString();
  print(operationDesc);

  int opCodeLength = operationDesc.length > 1 ? 2 : 1;
  int opCode =
      int.parse(operationDesc.substring(operationDesc.length - opCodeLength));

  List<int> paramOpCodes = operationDesc
      .substring(0, operationDesc.length - opCodeLength)
      .split('')
      .map(int.parse)
      .toList()
      .reversed
      .toList();

  List<int> fillZero = List<int>.generate(3 - paramOpCodes.length, (_) => 0);

  print([opCode, ...paramOpCodes, ...fillZero]);
  return [opCode, ...paramOpCodes, ...fillZero];
}

int iterateOpCodes(Amplifier currAmp, int input, int phaseSetting) {
  List<int> intCodes = currAmp.instructions;

  for (int i = currAmp.pointer; i < intCodes.length;) {
    List<int> opCodes = parseOpCodes(intCodes[i]);

    if (opCodes[0] == 99) {
      currAmp.pointer = i;
      return 99;
    }

    switch (opCodes[0]) {
      case 1:
        int firstInst = opCodes[1];
        int secInst = opCodes[2];

        int firstParam = firstInst == 0 ? intCodes[intCodes[i + 1]] : intCodes[i + 1];
        int secParam = secInst == 0 ? intCodes[intCodes[i + 2]] : intCodes[i + 2];
        int outPos = intCodes[i + 3];

        intCodes[outPos] = firstParam + secParam;
        i += 4;
        print('Addition called: $firstParam + $secParam to $outPos');
        break;

      case 2:
        int firstInst = opCodes[1];
        int secInst = opCodes[2];

        int firstParam = firstInst == 0 ? intCodes[intCodes[i + 1]] : intCodes[i + 1];
        int secParam = secInst == 0 ? intCodes[intCodes[i + 2]] : intCodes[i + 2];
        int outPos = intCodes[i + 3];

        intCodes[outPos] = firstParam * secParam;
        i += 4;
        print('Multiplication called: $firstParam * $secParam to $outPos');
        break;

      case 3:
        int firstParam = intCodes[i + 1];
        print('Please enter the System ID to run the test on:');
        int userIn = currAmp.phaseSetting != null ? input : phaseSetting;

        if (currAmp.phaseSetting == null) {
          currAmp.phaseSetting = phaseSetting;
        }

        intCodes[firstParam] = userIn;
        i += 2;
        print('Storing called: $userIn in $firstParam');
        break;

      case 4:
        int firstInst = opCodes[1];
        int firstParam = firstInst == 0 ? intCodes[intCodes[i + 1]] : intCodes[i + 1];

        i += 2;
        print('Printing called: $firstParam');
        currAmp.pointer = i;
        return firstParam;

      case 5:
        int firstInst = opCodes[1];
        int secInst = opCodes[2];

        int firstParam = firstInst == 0 ? intCodes[intCodes[i + 1]] : intCodes[i + 1];
        int secParam = secInst == 0 ? intCodes[intCodes[i + 2]] : intCodes[i + 2];
        bool doJump = firstParam != 0;

        i = doJump ? secParam : i + 3;
        print('Jump if True: $firstParam is $doJump, $secParam');
        break;

      case 6:
        int firstInst = opCodes[1];
        int secInst = opCodes[2];

        int firstParam = firstInst == 0 ? intCodes[intCodes[i + 1]] : intCodes[i + 1];
        int secParam = secInst == 0 ? intCodes[intCodes[i + 2]] : intCodes[i + 2];
        bool doJump = firstParam == 0;

        i = doJump ? secParam : i + 3;
        print('Jump if False: $firstParam is $doJump, $secParam');
        break;

      case 7:
        int firstInst = opCodes[1];
        int secInst = opCodes[2];

        int firstParam = firstInst == 0 ? intCodes[intCodes[i + 1]] : intCodes[i + 1];
        int secParam = secInst == 0 ? intCodes[intCodes[i + 2]] : intCodes[i + 2];
        int out = intCodes[i + 3];

        intCodes[out] = firstParam < secParam ? 1 : 0;
        print('Store 1 if less else 0: $firstParam < $secParam, ${intCodes[out]}');
        i += 4;
        break;

      case 8:
        int firstInst = opCodes[1];
        int secInst = opCodes[2];

        int firstParam = firstInst == 0 ? intCodes[intCodes[i + 1]] : intCodes[i + 1];
        int secParam = secInst == 0 ? intCodes[intCodes[i + 2]] : intCodes[i + 2];
        int out = intCodes[i + 3];

        intCodes[out] = firstParam == secParam ? 1 : 0;
        print('Store 1 if equal else 0: $firstParam = $secParam, ${intCodes[out]}');
        i += 4;
        break;
      default:
        throw new UnsupportedError(
            "This opcode is not yet supported! ${opCodes[0]}");
    }

    print('');
  }

  return -1;
}

List<List<int>> getAllPermutations(List<int> source) {
  List<List<int>> allPermutations = [];

  void permutate(List<int> list, int cursor) {
    if (cursor == list.length) {
      allPermutations.add(list);
      return;
    }

    for (int i = cursor; i < list.length; i++) {
      List permutation = new List<int>.from(list);
      permutation[cursor] = list[i];
      permutation[i] = list[cursor];
      permutate(permutation, cursor + 1);
    }
  }

  permutate(source, 0);
  return allPermutations;
}

int main(List<String> args) {
  File input = new File('../input.txt');

  List<int> intCodes =
      input.readAsStringSync().split(",").map(int.parse).toList();

  List<List<int>> permuts = getAllPermutations([5, 6, 7, 8, 9]);
  int highestThrust = 0;
  List<int> highestSetting = [];

  for (List<int> permut in permuts) {
    int nextInput = 0;
    int lastInput = 0;

    List<Amplifier> amplifiers = permut.map((phaseSetting) => Amplifier(List<int>.of(intCodes))).toList();

    int i = 0;
    while (true) {
      Amplifier currAmp = amplifiers[i];
      lastInput = nextInput;
      nextInput = iterateOpCodes(currAmp, nextInput, permut[i]);
      i = (i+1) % permut.length;

      if (nextInput == 99) {
        break;
      }
    }

    if (highestThrust < lastInput) {
      highestThrust = lastInput;
      highestSetting = permut;
    }
  }
  
  print(highestThrust);
  print(highestSetting);

  return 1;
}
