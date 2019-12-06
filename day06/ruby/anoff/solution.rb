#!/usr/bin/ruby
# the orbit map points from satellite to its orbit center
def build_map(map_data)
  map = Hash.new()
  for entry in map_data
    parts = entry.split(")")
    center = parts[0]
    satellite = parts[1]
    map[satellite] = center
  end
  map
end
# calculate how many orbits a single satellite has in relation to the center of mass
def orbit_count(orbit_map, satellite)
  count = 0
  while satellite = orbit_map[satellite]
    count += 1
  end
  count
end
# total sum of orbits of all satellites in the system
def total_orbit_count(orbit_map)
  count = 0
  orbit_map.each{|satellite, _|
    central_objects = orbit_count(orbit_map, satellite)
    # puts "Satellite %s orbits %d objects" % [satellite, central_objects]
    count += central_objects
  }
  count
end
def part1(map_data)
  map = build_map(map_data)
  total_orbit_count(map)
end

def get_orbit_centers(orbit_map, satellite)
  centers = []
  while satellite = orbit_map[satellite]
    centers.push(satellite)
  end
  centers
end
# tag::p2[]
def part2(map_data)
  orbit_map = build_map(map_data)
  target_center = orbit_map["SAN"] # the object we want to orbit as well
  santas_orbits = get_orbit_centers(orbit_map, "SAN")
  my_orbits = get_orbit_centers(orbit_map, "YOU")
  first_common_center = my_orbits.each_with_index.find{|(center, ix)| santas_orbits.include?(center)}
  my_hops_to_common_center = first_common_center[1] # the index of the array
  hops_from_common_to_santa = santas_orbits.each_with_index.find{|(center, ix)| center == first_common_center[0]}[1]
  my_hops_to_common_center + hops_from_common_to_santa
end
# end::p2[]
if caller.length == 0
  input = File.read("./input.txt")

  data = input.split("\n")
  puts "Solution for part1: %d" % part1(data)
  puts "Solution for part2: %d" % part2(data)
end
