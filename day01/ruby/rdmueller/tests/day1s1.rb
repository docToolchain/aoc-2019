#!/usr/bin/env ruby
require_relative "../solution/day1s1"
require "test/unit"

class TestEquation < Test::Unit::TestCase
    # tag::acceptance_tests1[]
    def test_acceptance_criteria1
        assert rocket_equation(    12) ==     2
        assert rocket_equation(    14) ==     2
        assert rocket_equation(  1969) ==   654
        assert rocket_equation(100756) == 33583
    end
    # end::acceptance_tests1[]

    # tag::acceptance_tests2[]
    def test_acceptance_criteria2
        assert sum_up( [12, 14, 1969, 100756] ) == 2 + 2 + 654 + 33583
    end
    # end::acceptance_tests2[]
end
