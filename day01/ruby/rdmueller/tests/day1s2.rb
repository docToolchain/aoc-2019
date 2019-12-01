#!/usr/bin/env ruby
require_relative "../solution/day1s2"
require "test/unit"

class TestEquation < Test::Unit::TestCase
    # tag::acceptance_tests[]
    def test_acceptance_criteria1
        assert_equal     2, rocket_equation(    14)
        assert_equal   966, rocket_equation(  1969)
        assert_equal 50346, rocket_equation(100756)
    end
    # end::acceptance_tests[]

end
