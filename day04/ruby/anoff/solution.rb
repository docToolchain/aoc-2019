#!/usr/bin/ruby
def is_decreasing(number)
  diff = diff_vector(number)
  return diff.find{|e| e < 0} != nil
end

def is_valid(number)
  diff = diff_vector(number)
  has_dupe = diff.find{|e| e == 0} != nil
  
  has_dupe && !is_decreasing(number)
end

def has_real_dupes(number)
  map = Hash.new()
  digits = number.to_s.split("")
  for digit in digits
    if map[digit] == nil
      map[digit] = 1
    else
      map[digit] += 1
    end
  end

  for digit in digits
    if map[digit] == 2
      return true
    end
  end
  return false
end

def is_valid_p2(number)
  diff = diff_vector(number)
  is_decreasing = diff.find{|e| e < 0} != nil
  
  has_real_dupes(number) && !is_decreasing
end
def diff_vector(number)
  arr = number.to_s.split("").map(&:to_i)
  arr[1..-1].map.with_index{|e,ix| e - arr[ix]} # arr[ix] = previous element because index in map starts with an offset
end
def part1(start, stop)
  (start..stop).select{|n| is_valid(n)}.length
end

def part2(start, stop)
  (start..stop).select{|n| !is_decreasing(n) && has_real_dupes(n)}.length
end

if caller.length == 0
  puts "Solution for part1: %d" % part1(245318, 765747)
  puts "Solution for part2: %d" % part2(245318, 765747)
end
