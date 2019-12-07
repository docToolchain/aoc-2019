require "test/unit"
require_relative './solution'

class TestSolution < Test::Unit::TestCase
  def test_is_decreasing
    d = {
       321 => true,
       123456 => false,
       222222 => false,
       412341 => true,
       65555 => true,
       12345 => false
    }
    d.each{ |input, result|
      assert_equal result, is_decreasing(input), "for input %d" % input
    }
  end
  def test_is_valid
    d = {
       111111 => true,
       223450 => false,
       123789 => false
    }
    d.each{ |input, result|
      assert_equal result, is_valid(input)
    }
  end
  def test_is_valid_p2
    d = {
       111111 => false,
       223450 => false,
       112233 => true,
       22 => true,
       444 => false,
       123789 => false,
       111122 => true,
       1111222447789 => true,
       1111 => false,
       123444 => false
    }
    d.each{ |input, result|
      assert_equal result, is_valid_p2(input), "wrong result for input %d" % input
    }
  end
  def test_has_real_dupes
    d = {
      111111 => false,
      223450 => true,
      112233 => true,
      22 => true,
      444 => false,
      123789 => false,
      111122 => true,
      1111222447789 => true,
      1111 => false,
      123444 => false
   }
   d.each{ |input, result|
     assert_equal result, has_real_dupes(input), "wrong result for input %d" % input
   }
 end
end
