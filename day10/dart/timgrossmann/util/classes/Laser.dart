import 'dart:math';

import '../util.dart';
import 'Vector2.dart';

enum MoveDir { RIGHT, DOWN, LEFT, UP }

class Laser {
  Vector2 direction = Vector2(-1.0, 0.0);
  double stepSize = 0.01;
  MoveDir moveDir = MoveDir.RIGHT;

  Laser();

  void roateStep() {
    switch (moveDir) {
      case MoveDir.RIGHT:
        direction.x = min(roundToDecimal(direction.x + stepSize, 2), 1.0);

        if (direction.x == 1.0) {
          moveDir = MoveDir.DOWN;
        }
        break;

      case MoveDir.DOWN:
        direction.y = min(roundToDecimal(direction.y + stepSize, 2), 1.0);

        if (direction.y == 1.0) {
          moveDir = MoveDir.LEFT;
        }
        break;

      case MoveDir.LEFT:
        direction.x = max(roundToDecimal(direction.x - stepSize, 2), -1.0);

        if (direction.x == -1.0) {
          moveDir = MoveDir.UP;
        }
        break;

      case MoveDir.UP:
        direction.y = max(roundToDecimal(direction.y - stepSize, 2), -1.0);

        if (direction.y == -1.0) {
          moveDir = MoveDir.RIGHT;
        }
        break;

      default:
        break;
    }
  }
}