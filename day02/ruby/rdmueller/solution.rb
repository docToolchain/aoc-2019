#!/usr/bin/env ruby

# tag::star1[]

def run(noun, verb)
    inst = File.read("./input.txt").split(",").map(&:to_i)
    inst[1]=noun
    inst[2]=verb
    pointer = 0
    while inst[pointer]!=99 do
        if inst[pointer]==1 then
            inst[inst[pointer+3]]=inst[inst[pointer+1]]+inst[inst[pointer+2]]
        end
        if inst[pointer]==2 then
            inst[inst[pointer+3]]=inst[inst[pointer+1]]*inst[inst[pointer+2]]
        end
        if inst[pointer]!=1 && inst[pointer]!=2 then
            puts "error"
            puts pointer
            puts isntructions
            puts "/error"
        end
        pointer += 4
    end
    return inst[0]
end
puts "star 1:"
puts run(12,2)

# end::star1[]

# tag::star2[]

puts "star 2:"
(0..99).each { |noun|
    (0..99).each { |verb|
        if (run(noun,verb)==19690720) then
            puts 100*noun+verb
        end
   }
}

# end::star2[]
