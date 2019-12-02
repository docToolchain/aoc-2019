def op(program, ptr)
  opcode = program[ptr]
  # puts "Running opcode:%d on pos:%d" % [opcode, ptr]
  case opcode
  when 99
    return 1
  when 1..2
    if (program.length - 1) <= ptr + 3
      return -2
    end
    in1 = program[ptr+1]
    in2 = program[ptr+2]
    out = program[ptr+3]
    if opcode == 1
      program[out] = program[in1] + program[in2]
    elsif opcode == 2
      program[out] = program[in1] * program[in2]
    end
    return 0
  else
    return -1
  end
end

def run_to_termination(program)
  ptr = 0
  while op(program, ptr) == 0
    ptr += 4
  end
  return program[0]
end

def try_parameter(program, arg1, arg2)
  prog_copy = Array.new(program)
  prog_copy[1] = arg1
  prog_copy[2] = arg2
  res = run_to_termination(prog_copy)
  # puts "Ran %d, %d = %d" % [arg1, arg2, res]
  res
end

def part1(program)
  program[1] = 12
  program[2] = 2
  run_to_termination(program)
end

def part2(program)
  noun = 0
  verb = 0
  while try_parameter(program, noun, verb) != 19690720
    noun += 1
    if noun > 99
      noun = 0
      verb += 1
    end
    if noun == 99 && verb == 99
      puts "No solution found"
      exit(1)
    end
  end
  return 100 * noun + verb
end

if caller.length == 0
  input = File.read("./input.txt")

  program = input.split(",").map(&:to_i)

  puts "Solution for part1: %d" % part1(Array.new(program))
  puts "Solution for part2: %d" % part2(Array.new(program))
end