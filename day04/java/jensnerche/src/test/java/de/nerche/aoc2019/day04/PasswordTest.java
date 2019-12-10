package de.nerche.aoc2019.day04;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

class PasswordTest {
  private static final Logger LOGGER = Logger.getLogger(PasswordTest.class.getName());

  @Test
  void thatPasswordMatchesCriteria() {
    assertFalse(meetsCriteria(223450));
    assertFalse(meetsCriteria(123789));
    assertFalse(meetsCriteria(120089));
    assertFalse(meetsCriteria(111111));

    assertTrue(meetsCriteria(112233));
    assertFalse(meetsCriteria(123444));
    assertTrue(meetsCriteria(111122));

    // when all tests are green, do the real calculation
    int counter = 0;
    for (int i = 138241; i <= 674034; i++) {
      if (meetsCriteria(i))
        counter++;
    }

    LOGGER.info("Counter = " + counter);

  }


  private boolean meetsCriteria(int candidate) {
    var firstDigit = candidate / 100_000;
    var rest = candidate - firstDigit * 100_000;
    var secondDigit = rest / 10_000;
    rest = rest - secondDigit * 10_000;
    var thirdDigit = rest / 1_000;
    rest = rest - thirdDigit * 1_000;
    var forthDigit = rest / 100;
    rest = rest - forthDigit * 100;
    var fifthDigit = rest / 10;
    rest = rest - fifthDigit * 10;
    var sixthDigit = rest;

    return neverDecrease(firstDigit, secondDigit, thirdDigit, forthDigit, fifthDigit, sixthDigit)
           && atLeastTwoAdjacentDigitsAreTheSame(firstDigit, secondDigit, thirdDigit, forthDigit, fifthDigit,
                                                  sixthDigit);
  }

  private boolean neverDecrease(int firstDigit, int secondDigit, int thirdDigit, int forthDigit, int fifthDigit,
                                int sixthDigit) {
    return firstDigit <= secondDigit && secondDigit <= thirdDigit && thirdDigit <= forthDigit
           && forthDigit <= fifthDigit && fifthDigit <= sixthDigit;
  }

  private boolean atLeastTwoAdjacentDigitsAreTheSame(int... digits) {
    final int max = digits.length - 1;
    for (int i = 0; i < max; i++) {
      if (digits[i] == digits[i + 1]) {

        // additional condition of another elf
        if (i + 2 > max || digits[i + 2] != digits[i]) {
          if (i - 1 < 0 || digits[i - 1] != digits[i]) {
            return true;
          }
        }
      }
    }

    return false;
  }
}
