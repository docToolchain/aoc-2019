require "test/unit"
require_relative './solution'

class TestSolution < Test::Unit::TestCase
  def test_required_fuel
    d = {
      3500 => [1,9,10,3,2,3,11,0,99,30,40,50],
      30 => [1,1,1,4,99,5,6,0,99]
    }
    d.each{ |result, program|
      assert_equal result, run_to_termination(program), "Incorrect result for input" % program
    }
  end
end
