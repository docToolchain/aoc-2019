import 'dart:io';

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

List<int> iterateOpCodes(List<int> intCodes) {
  List<int> output = new List<int>.from(intCodes);

  for (int i = 0; i < output.length;) {
    List<int> opCodes = parseOpCodes(output[i]);

    if (opCodes[0] == 99) {
      break;
    }

    switch (opCodes[0]) {
      case 1:
        int firstInst = opCodes[1];
        int secInst = opCodes[2];

        int firstParam = firstInst == 0 ? output[output[i + 1]] : output[i + 1];
        int secParam = secInst == 0 ? output[output[i + 2]] : output[i + 2];
        int outPos = output[i + 3];

        output[outPos] = firstParam + secParam;
        i += 4;
        print('Addition called: $firstParam + $secParam to $outPos');
        break;

      case 2:
        int firstInst = opCodes[1];
        int secInst = opCodes[2];

        int firstParam = firstInst == 0 ? output[output[i + 1]] : output[i + 1];
        int secParam = secInst == 0 ? output[output[i + 2]] : output[i + 2];
        int outPos = output[i + 3];

        output[outPos] = firstParam * secParam;
        i += 4;
        print('Multiplication called: $firstParam * $secParam to $outPos');
        break;

      case 3:
        int firstParam = output[i + 1];
        print('Please enter the System ID to run the test on:');
        int userIn = int.parse(stdin.readLineSync());

        output[firstParam] = userIn;
        i += 2;
        print('Storing called: $userIn in $firstParam');
        break;

      case 4:
        int firstInst = opCodes[1];
        int firstParam = firstInst == 0 ? output[output[i + 1]] : output[i + 1];

        i += 2;
        print('Printing called: $firstParam');
        break;

      case 5:
        int firstInst = opCodes[1];
        int secInst = opCodes[2];

        int firstParam = firstInst == 0 ? output[output[i + 1]] : output[i + 1];
        int secParam = secInst == 0 ? output[output[i + 2]] : output[i + 2];
        bool doJump = firstParam != 0;

        i = doJump ? secParam : i + 3;
        print('Jump if True: $firstParam is $doJump, $secParam');
        break;

      case 6:
        int firstInst = opCodes[1];
        int secInst = opCodes[2];

        int firstParam = firstInst == 0 ? output[output[i + 1]] : output[i + 1];
        int secParam = secInst == 0 ? output[output[i + 2]] : output[i + 2];
        bool doJump = firstParam == 0;

        i = doJump ? secParam : i + 3;
        print('Jump if False: $firstParam is $doJump, $secParam');
        break;

      case 7:
        int firstInst = opCodes[1];
        int secInst = opCodes[2];

        int firstParam = firstInst == 0 ? output[output[i + 1]] : output[i + 1];
        int secParam = secInst == 0 ? output[output[i + 2]] : output[i + 2];
        int out = output[i + 3];

        output[out] = firstParam < secParam ? 1 : 0;
        print('Store 1 if less else 0: $firstParam < $secParam, ${output[out]}');
        i += 4;
        break;

      case 8:
        int firstInst = opCodes[1];
        int secInst = opCodes[2];

        int firstParam = firstInst == 0 ? output[output[i + 1]] : output[i + 1];
        int secParam = secInst == 0 ? output[output[i + 2]] : output[i + 2];
        int out = output[i + 3];

        output[out] = firstParam == secParam ? 1 : 0;
        print('Store 1 if equal else 0: $firstParam = $secParam, ${output[out]}');
        i += 4;
        break;
      default:
        throw new UnsupportedError(
            "This opcode is not yet supported! ${opCodes[0]}");
    }

    print('');
  }

  return output;
}

int main(List<String> args) {
  File input = new File('./input.txt');

  List<int> intCodes =
      input.readAsStringSync().split(",").map(int.parse).toList();

  List<int> output = iterateOpCodes(intCodes);

  print(output);

  return 1;
}
