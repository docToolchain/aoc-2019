require "test/unit"
require_relative './solution'

class TestSolution < Test::Unit::TestCase
  def test_manhattan
    assert_equal 3, Point.new(0,0).manhattan(Point.new(3, 0))
    assert_equal 5, Point.new(0,-2).manhattan(Point.new(3, 0))
    assert_equal 7+4, Point.new(10,4).manhattan(Point.new(3, 0))
  end
  def test_point_add
    assert Point.new(3,3) == Point.new(1, 1) + Point.new(2, 2)
  end
  def test_point_from_s
    assert Point.from_s("(x:3,y:4)") == Point.new(3, 4)
    assert Point.from_s("(x:153,y:246)") == Point.new(153, 246)
  end
  def test_part1
    d = {
      6 => [['R8','U5','L5','D3'], ['U7','R6','D4','L4']],
      159 => [['R75','D30','R83','U83','L12','D49','R71','U7','L72'],
      ['U62','R66','U55','R34','D71','R55','D58','R83']],
      135 => [['R98','U47','R26','D63','R33','U87','L62','D20','R33','U53','R51'],
      ['U98','R91','D20','R16','D67','R40','U7','R15','U6','R7']]
    }
    d.each{ |score, input|
      assert_equal score, part1(input)
    }
  end
  def test_part2
    d = {
      30 => [['R8','U5','L5','D3'], ['U7','R6','D4','L4']],
      610 => [['R75','D30','R83','U83','L12','D49','R71','U7','L72'],
      ['U62','R66','U55','R34','D71','R55','D58','R83']],
      410 => [['R98','U47','R26','D63','R33','U87','L62','D20','R33','U53','R51'],
      ['U98','R91','D20','R16','D67','R40','U7','R15','U6','R7']]
    }
    d.each{ |score, input|
      assert_equal score, part2(input)
    }
  end
end
