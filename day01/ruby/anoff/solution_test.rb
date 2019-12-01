require "test/unit"
require_relative './solution'

class TestSolution < Test::Unit::TestCase
  def test_required_fuel
    d = {
      12 => 2,
      14 => 2,
      1969 => 654,
      100756 => 33583
    }
    d.each{ |mass, fuel|
      assert_equal fuel, required_fuel(mass), "Incorrect fuel for mass=%d" % mass
    }
  end
end
