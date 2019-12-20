from solution import fft
import pytest

#result = fft("12345678", 4)
#assert(result == "01029498")

result = fft("80871224585914546619083218645595", 100)
assert(result == "24176176")

result = fft("19617804207202209144916044189917", 100)
assert(result == "73745418")

result = fft("69317163492948606335995924319873", 100)
assert(result == "52432133")


