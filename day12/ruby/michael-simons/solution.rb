#!/usr/bin/env ruby

require 'set'

# tag::monkeyPatching[]
class Array 
	def add(other)
    [self,other].transpose.map { |x| x.reduce(:+) }
	end
end
# end::monkeyPatching[]

# tag::moon[]
class Moon
  attr_reader :position
  attr_reader :vel
  attr_reader :gravity
  # end::moon[]

  def initialize(position) 
    @position = position
    @vel = [0, 0, 0]
    @gravity = [0, 0, 0]
  end
    
  def ==(other_moon) 
    @position == other_moon.position and @vel == other_moon.vel
  end
  # tag::moon[]
  
  def add_gravity_of(other_moon) 
    
    @gravity = @gravity.add([
      compare_value_of_axis(@position[0], other_moon.position[0]),
      compare_value_of_axis(@position[1], other_moon.position[1]),
      compare_value_of_axis(@position[2], other_moon.position[2]),
    ])
    
    self
  end
  # end::moon[]
  
  def apply_gravity 
    @vel = @vel.add(@gravity)
    @gravity = [0, 0, 0]
    
    self
  end
  
  def apply_velocity
    @position = @position.add(@vel)
    
    self
  end
  
  def energy
    @position.map(&:abs).reduce(:+) * @vel.map(&:abs).reduce(:+)
  end

  private
    def compare_value_of_axis(this, other) 
      if other > this
        1
      elsif other < this
        -1
      else 
        0
      end
    end
end
# end::moon[]

input = File.read(ARGV.length == 1 ? ARGV[0] : "input.txt").split("\n").map do |v| 
  positions = v.match(/<x=(-?\d+), y=(-?\d+), z=(-?\d+)>/)[1, 4].map(&:to_i)
  Moon.new(positions)
end

# tag::starOne[]
moons = input.map(&:clone) # <.>

(1..1000).each do
  moons
    .product(moons).select { |p| p[0] != p[1] } # <.>
    .each{ |m1,m2| m1.add_gravity_of(m2) }
  moons.each{ |m| m.apply_gravity.apply_velocity }
end 

total_energy = moons.map(&:energy).reduce(:+)  # <.>

puts "Star one #{total_energy}"
# end::starOne[]

# tag::starTwo[]
moons = input.map(&:clone)

cnt = 0
cycles = [-1, -1, -1]
c = [nil, nil, nil]
axes = Array.new(3) { Set.new }

loop do
  moons
    .product(moons).select { |p| p[0] != p[1] }
    .each{ |m1,m2| m1.add_gravity_of(m2) }
  moons.each{ |m| m.apply_gravity.apply_velocity }
  
  (0..2).each.select { |i| cycles[i] < 0}.each do |i| # <.>
    axis = moons.map { |m| [m.position[i], m.vel[i]].flatten }
    cycles[i] = cnt if axes[i].include? axis
    axes[i].add(axis)
  end

  cnt += 1 
  break if moons == input or cycles.all? {|v| v != -1} # <.>
end

cycles_until_start = cycles.reduce(&:lcm) # <.>

puts "Star two #{cycles_until_start}"
# end::starTwo[]
