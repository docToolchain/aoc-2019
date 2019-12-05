#!/usr/bin/env ruby

# tag::starOne[]
passwords_matching_first_rules = (134792..675810).select do |v| # <1>
  pairs = v.to_s.chars.each_cons(2) # <2>
  pairs.any? { |p| p[0] == p[1] } && pairs.all? { |p| p[0].to_i <= p[1].to_i } # <3>
end

puts "Star one #{passwords_matching_first_rules.count}"
# end::starOne[]

# tag::starTwo[]
number_of_passwords_matching_third_rule = passwords_matching_first_rules.count do |p| 
  p.to_s.scan(/((\d)\2+)/).map{ |a| a[0]}.any? { |v| v.length == 2 } # <1>
end
  
puts "Star two #{number_of_passwords_matching_third_rule}"
# end::starTwo[]
