import 'dart:io';
import 'dart:convert';

int calcRocketFuelCons (int mass) {
  // using the truncating division operator
  return (mass ~/ 3) - 2; 
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
    .map(calcRocketFuelCons)
    .fold(0, (accu, curVal) => accu + curVal)
    .then(print) ;

  return 1;
}