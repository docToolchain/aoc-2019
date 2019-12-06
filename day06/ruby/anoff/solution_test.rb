require "test/unit"
require_relative './solution'

class TestSolution < Test::Unit::TestCase
  def test_build_map
    d = {
      ["COM)B",
        "B)C",
        "C)D"] => { "D"=>"C", "C"=>"B", "B"=>"COM"},
        ["COM)B",
          "B)C",
          "C)D",
          "B)E"] => { "E"=>"B", "D"=>"C", "C"=>"B", "B"=>"COM"}
    }
    d.each{ |input, result|
      assert_equal result, build_map(input), "for input %s" % input
    }
  end
  def test_orbit_count
    map_data = ["COM)B",
      "B)C",
      "C)D",
      "D)E",
      "E)F",
      "B)G",
      "G)H",
      "D)I",
      "E)J",
      "J)K",
      "K)L"]
    d = {
      "B" => 1,
      "D" => 3,
      "L" => 7,
      "COM" => 0
    }
    map = build_map(map_data)
    d.each{ |input, result|
      assert_equal result, orbit_count(map, input), "for input %s" % input
    }
  end
  # tag::full_tdd[]
  def test_total_orbit_count
    d = {
       ["COM)B",
        "B)C",
        "C)D",
        "D)E",
        "E)F",
        "B)G",
        "G)H",
        "D)I",
        "E)J",
        "J)K",
        "K)L"] => 42
    }
    d.each{ |input, result|
      map = build_map(input)
      assert_equal result, total_orbit_count(map), "for input %s" % input
    }
  end
  # end::full_tdd[]
end
