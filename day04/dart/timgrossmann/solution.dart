import 'dart:io';

bool checkConditions (int number) {
  String numberStr = number.toString();
  
  // part01 solution
  // bool duplicate = false;
  
  Map<int, int> occur = new Map<int, int>();

  // put first value before loop since we start looping
  // at one to allow lookback without additional condition
  occur.putIfAbsent(int.parse(numberStr[0]), () => 1);

  for (int i = 1; i < numberStr.length; i++) {
    int lastNum =  int.parse(numberStr[i - 1]);
    int currNum = int.parse(numberStr[i]);

    if (currNum < lastNum) {
      return false;
    }
    
    // part01 solution
    /*
    if (currNum == lastNum) {
      duplicate = true;
    }
    */

    if (occur.containsKey(currNum)) {
      occur.update(currNum, (value) => ++value);
    } else {
      occur.putIfAbsent(currNum, () => 1);
    }
  }

  // part01 solution
  // return duplicate;

  return occur
    .entries
    .any((MapEntry mapEntry) => (mapEntry.value == 2));
}

int main (List<String> args) {
  File input = new File('input.txt');
  
  List<int> range = input
    .readAsStringSync()
    .split("-")
    .map(int.parse)
    .toList();

  List<int> passwords = new List<int>();

  for (int i = range[0]; i <= range[1]; i++) {
    if (checkConditions(i)) {
      passwords.add(i);
    }
  }

  print(passwords.length);

  return 1;
}