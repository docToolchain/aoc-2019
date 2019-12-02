def op(program, ptr)
  opcode = program[ptr]
  puts "Running opcode:%d on pos:%d" % [opcode, ptr]
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

def part1(program)
  program[1] = 12
  program[2] = 2
  run_to_termination(program)
end

if caller.length == 0
  input = File.read("./input.txt")

  program = input.split(",").map(&:to_i)

  puts "Solution for part1: %d" % part1(program)
end