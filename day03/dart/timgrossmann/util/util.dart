import 'Instruction.dart';
import 'WirePoint.dart';

int getManhattenDistance (int xVal, int xGoal, yVal, yGoal) {
  return (xVal - xGoal).abs() + (yVal - yGoal).abs();
}

Instruction parseInstruction (String instruction) {
  String direction = instruction[0];
  int amount = int.parse(instruction.substring(1));

  switch (direction) {
    case 'R':
      return new Instruction(Direction.RIGHT, amount);

    case 'L':
      return new Instruction(Direction.LEFT, amount);

    case 'U':
      return new Instruction(Direction.UP, amount);

    case 'D':
      return new Instruction(Direction.DOWN, amount);
    
    default:
      throw new UnsupportedError('Provided direction is not supported $direction');
  }
} 

List<WirePoint> getWirePointSteps (WirePoint currPoint, Instruction inst) {
  List<WirePoint> steps = new List<WirePoint>();

  for (int i = 0; i < inst.amount; i++) {
    currPoint.steps++;
    
    switch (inst.dir) {
      case Direction.RIGHT:
        currPoint.x++;
        break;

      case Direction.LEFT:
        currPoint.x--;
        break;

      case Direction.UP:
        currPoint.y++;
        break;

      case Direction.DOWN:
        currPoint.y--;
        break;

      default:
        throw new UnsupportedError('Direction not supported for walking ${inst.dir}');
    }

    steps.add(new WirePoint(currPoint.x, currPoint.y, currPoint.wireNum, currPoint.steps));
  }

  return steps;
}