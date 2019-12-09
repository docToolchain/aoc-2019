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