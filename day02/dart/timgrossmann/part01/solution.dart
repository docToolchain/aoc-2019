import 'dart:io';
import 'dart:convert';

List<int> iterateOpCodes (List<int> intCodes) {
  List<int> output = new List<int>.from(intCodes);

  for (int i = 0; i < output.length - 3; i+=4) {
    int opCode = output[i];

    if (opCode == 99) {
      break;
    }

    int firstParam = output[output[i + 1]];
    int secParam = output[output[i + 2]];
    int outPos = output[i + 3];

    switch (opCode) {
      case 1:
        output[outPos] = firstParam + secParam;
        break;
        
      case 2:
        output[outPos] = firstParam * secParam;
        break;

      default:
        throw new UnsupportedError("This opcode is not yet supported! $opCode"); 
    }
  }

  return output;
}

int main (List<String> args) {
  File input = new File('../input.txt');
  
  input
    .openRead()
    .transform(utf8.decoder)
    .transform(new LineSplitter())
    .map((String line) => line.split(","))
    .map((List<String> intCodes) => 
        intCodes.map((code) => int.parse(code)).toList())
    .map(iterateOpCodes)
    .forEach(print) ;

  return 1;
}