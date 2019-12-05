import 'dart:io';
import 'dart:math';

import 'util/Instruction.dart';
import 'util/util.dart';
import 'util/WirePoint.dart';

int main (List<String> args) {
  File input = new File('input.txt');
  
  List<List<Instruction>> wires = input
    .readAsStringSync()
    .split("\n")
    .map((wire) => wire.split(","))
    .map((wire) => 
      wire.map((instruct) => parseInstruction(instruct))
      .toList())
    .toList();

  Set<WirePoint> visitedPoints = new Set<WirePoint>();
  Set<WirePoint> intersections = new Set<WirePoint>();

  // for each wire, get all the instructions
  // and get the list of points on the way to the next instruction
  // adding only the points to the intersections that are already in visitedPoints
  // (only take into account x and y and then compare wireNum to not overlap)
  for (int i = 0; i < wires.length; i++) {
    List<Instruction> wire = wires[i];
    WirePoint currPoint = new WirePoint(0, 0, i);

    for (Instruction inst in wire) {
      List<WirePoint> steps = getWirePointSteps(currPoint, inst);

      for (WirePoint step in steps) {
        WirePoint contained = visitedPoints.lookup(step);

        if (contained != null && contained.wireNum != step.wireNum) {
          WirePoint intersection = new WirePoint(step.x, step.y, step.wireNum, step.steps + contained.steps);
          intersections.add(intersection);

          // print('Intersection found: $intersection');
        }

        visitedPoints.add(step); 
      }
    }
  }

  // get the element with the shortest manhatten dist from 0, 0
  // start with the minDist of 1000000 to make sure we override
  int minManDist = intersections
    .toList()
    .fold(1000000, (minDist, currIntersect) 
      => min(getManhattenDistance(currIntersect.x, 0, currIntersect.y, 0), minDist));

  // get the element with the lowest number of steps 
  // start with the minSteps of 1000000 to make sure we override
  int minStepDist = intersections
    .toList()
    .fold(1000000, (minSteps, currIntersect) 
      => min(currIntersect.steps , minSteps));

  print('Minimum Distance: $minManDist');
  print('Minimum Steps: $minStepDist');

  return 1;
}