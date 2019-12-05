def op(program, ptr)
  cmd = program[ptr]
  opcode = cmd % 100
  modes = Array.new()
  modes[2] = cmd / 10000
  modes[1] = cmd % 10000 / 1000
  modes[0] = cmd % 1000 / 100

  puts "Running opcode:%d on pos:%d with modes:%s from cmd:%d" % [opcode, ptr, modes.map(&:to_s).join(","), cmd]
  case opcode
  when 99
    return 0
  when 1..8
    if opcode == 1
      in1 = modes[0] == 1 ? program[ptr+1] : program[program[ptr+1]] # switch between position mode (0) and immediate/value mode (1)
      in2 = modes[1] == 1 ? program[ptr+2] : program[program[ptr+2]]
      out = program[ptr+3]
      program[out] = in1 + in2
      return 4
    elsif opcode == 2
      in1 = modes[0] == 1 ? program[ptr+1] : program[program[ptr+1]] # switch between position mode (0) and immediate/value mode (1)
      in2 = modes[1] == 1 ? program[ptr+2] : program[program[ptr+2]]
      out = program[ptr+3]
      program[out] = in1 * in2
      return 4
    elsif opcode == 3
      out = program[ptr+1]
      puts "Please provide numeric input:"
      in1 = gets.to_i
      puts "Storing %d at position %d" % [in1, out]
      program[out] = in1
      return 2
    elsif opcode == 4
      in1 = modes[0] == 1 ? program[ptr+1] : program[program[ptr+1]] # switch between position mode (0) and immediate/value mode (1)
      puts "Running output for ptr:%d mode:%d at:%d" % [ptr, modes[0], program[ptr+1]]
      puts "Output: %d" % in1
      return 2
    elsif opcode == 5
      in1 = modes[0] == 1 ? program[ptr+1] : program[program[ptr+1]] # switch between position mode (0) and immediate/value mode (1)
      in2 = modes[1] == 1 ? program[ptr+2] : program[program[ptr+2]]
      if in1 > 0
        return in2 - ptr
      else
        return 3
      end
    elsif opcode == 6
      in1 = modes[0] == 1 ? program[ptr+1] : program[program[ptr+1]] # switch between position mode (0) and immediate/value mode (1)
      in2 = modes[1] == 1 ? program[ptr+2] : program[program[ptr+2]]
      if in1 == 0
        return in2 - ptr
      else
        return 3
      end
    elsif opcode == 7
      in1 = modes[0] == 1 ? program[ptr+1] : program[program[ptr+1]] # switch between position mode (0) and immediate/value mode (1)
      in2 = modes[1] == 1 ? program[ptr+2] : program[program[ptr+2]]
      out = program[ptr+3]
      if in1 < in2
        program[out] = 1
      else
        program[out] = 0
      end
      return 4
    elsif opcode == 8
      in1 = modes[0] == 1 ? program[ptr+1] : program[program[ptr+1]] # switch between position mode (0) and immediate/value mode (1)
      in2 = modes[1] == 1 ? program[ptr+2] : program[program[ptr+2]]
      out = program[ptr+3]
      if in1 == in2
        program[out] = 1
      else
        program[out] = 0
      end
      return 4
    end
  else
    puts "Unknown opcode: %d" % opcode
    return -1 # unknown opcode (not 1..4)
  end
end

def run_to_termination(program)
  puts program.length
  ptr = 0
  step = op(program, ptr)
  while step > 0
    ptr += step
    step = op(program, ptr)
  end
  if step < 0
    throw "Unexpected error occured: %d" % step
  end
end

def part1(program)
  run_to_termination(program)
end


if caller.length == 0
  input = File.read("./input.txt")

  program = input.split(",").map(&:to_i)

  puts "Running part1.."
  part1(Array.new(program))
  puts "..Done"
  # puts "Solution for part2: %d" % part2(Array.new(program))
end