import 'dart:io';
import 'dart:convert';

int recurCalcFuelCons (int mass) {
  int neededFuel = (mass ~/ 3) - 2;

  // using the truncating division operator
  if (neededFuel <= 0) {
    return 0;
  }

  return neededFuel + recurCalcFuelCons(neededFuel);
}

/*
  Playing around with streams
*/
int main (List<String> args) {
  File input = new File('../input.txt');
  
  input
    .openRead()
    .transform(utf8.decoder)
    .transform(new LineSplitter())
    .map(int.parse)
    .map(recurCalcFuelCons)
    .fold(0, (accu, curVal) => accu + curVal)
    .then(print) ;

  return 1;
}