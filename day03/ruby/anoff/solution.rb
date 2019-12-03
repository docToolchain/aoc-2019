#!/usr/bin/ruby
require_relative './geom'

# take a wire and return a hash with Point->wire ID for each point the wire crossed
#   if a hash was passed it will be modified in place, otherwise a blank one will be created
def parseWire(wire, wireID, map = Hash.new)
  pos = Point.new(0, 0)
  steps = 0
  for instruction in wire
    delta = Point.new(0, 0)
    dir = instruction[0]
    length = instruction[1..-1].to_i
    case dir
    when 'R'
      delta.x = 1
    when 'D'
      delta.y = -1
    when 'L'
      delta.x = -1
    when 'U'
      delta.y = 1
    else
      raise "Unexpected direction instruction %s" % dir
    end
    
    while length > 0
      map[pos.to_s] = [wireID + "-" + steps.to_s].concat(map[pos.to_s] || Array.new())
      pos = pos + delta
      length -= 1
      steps += 1
    end
  end
  return map
end

def part1(wires)
  map = Hash.new
  wireID = 1
  for wire in wires
    parseWire(wire, wireID.to_s, map)
    wireID += 1
  end
  closestPoint = nil
  shortestDistance = 1/0.0 # infinity
  origin = Point.new(0, 0)
  map.each do |key, value|
    if value.find {|item| item.include?("1-")} && value.find {|item| item.include?("2-")}
      p1 = Point.from_s(key)
      distance = p1.manhattan(origin)
      if distance < shortestDistance && p1 != origin
        closestPoint = p1
        shortestDistance = distance
      end
    end
  end
  shortestDistance
end


def part2(wires)
  map = Hash.new
  wireID = 1
  for wire in wires
    parseWire(wire, wireID.to_s, map)
    wireID += 1
  end
  closestPoint = nil
  shortestDistance = 1/0.0 # infinity
  origin = Point.new(0, 0)
  map.each do |key, value|
    if value.find {|item| item.include?("1-")} && value.find {|item| item.include?("2-")}
      p1 = Point.from_s(key)
      steps1 = value.select {|item| item.include?("1-")}.map{|item| item[2..-1].to_i}.reduce(1/0.0){|prev, cur| prev = cur if cur < prev}
      steps2 = value.select {|item| item.include?("2-")}.map{|item| item[2..-1].to_i}.reduce(1/0.0){|prev, cur| prev = cur if cur < prev}
      distance = steps1 + steps2
      if distance < shortestDistance && p1 != origin
        closestPoint = p1
        shortestDistance = distance
      end
    end
  end
  shortestDistance
end

if caller.length == 0
  input = File.read("./input.txt")

  wires = input.split("\n").map{ |wire| wire.split(",") }
  puts "Solution for part1: %d" % part1(wires)
  puts "Solution for part2: %d" % part2(wires)
end