#!/usr/bin/env ruby

# tag::equation[]
# calculate the fuel needs for one module
def rocket_equation(mass)
    neededFuel = 0
    additionalFuel = mass/3-2
    while (additionalFuel>=0) do
        neededFuel += additionalFuel
        additionalFuel = additionalFuel/3-2
    end
    return neededFuel
end
# end::equation[]

# tag::sum_up[]
# sum up the requirements for a list of modules
def sum_up(mass_list)
    sum = 0
    mass_list.each { |mass|
        sum += rocket_equation(mass)
    }
    return sum
end
# end::sum_up[]

# tag::read_file[]
def read_input()
    input = File.open('input.txt').read
    masses = []
    input.each_line { |line|
        masses << Integer(line)
    }
    return masses
end
# end::read_file[]
puts sum_up(read_input)