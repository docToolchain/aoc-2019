import solution
import pytest

@pytest.mark.parametrize('mem_init, mem, input, output, desc',[
    [[1101, 7, 6, 5, 99, 0, 0, 0],[1101, 7, 6, 5, 99, 13, 0, 0],[], [], "add immediate"],
    [[1102, 7, 6, 5, 99, 0, 0, 0],[1102, 7, 6, 5, 99, 42, 0, 0],[], [], "mul immediate"],
    [[1107, 7, 6, 5, 99, None, 0, 0],[1107, 7, 6, 5, 99, 0, 0, 0],[], [], "lt immediate"],
    [[1108, 7, 6, 5, 99, None, 0, 0],[1108, 7, 6, 5, 99, 0, 0, 0],[], [], "eq immediate"],
    [[101, 7, 4, 5, 99, 0, 0, 0],[101, 7, 4, 5, 99, 106, 0, 0],[], [], "add immediate and position"],
    [[102, 7, 4, 5, 99, 0, 0, 0],[102, 7, 4, 5, 99, 693, 0, 0],[], [], "mul immediate and position"],
    [[107, 7, 4, 5, 99, None, 0, 0],[107, 7, 4, 5, 99, 1, 0, 0],[], [], "lt immediate and position"],
    [[108, 7, 4, 5, 99, None, 0, 0],[108, 7, 4, 5, 99, 0, 0, 0],[], [], "eq immediate and position"],
    [[1001, 3, 6, 5, 99, 0, 0, 0],[1001, 3, 6, 5, 99, 11, 0, 0],[], [], "add position and immediate "],
    [[1002, 3, 6, 5, 99, 0, 0, 0],[1002, 3, 6, 5, 99, 30, 0, 0],[], [], "mul position and immediate "],
    [[1007, 3, 6, 5, 99, None, 0, 0],[1007, 3, 6, 5, 99, 1, 0, 0],[], [], "lt position and immediate"],
    [[1008, 3, 6, 5, 99, None, 0, 0],[1008, 3, 6, 5, 99, 0, 0, 0],[], [], "eq position and immediate"],
    [[1, 3, 4, 5, 99, 0, 0, 0],[1, 3, 4, 5, 99, 104, 0, 0],[], [], "add positions"],
    [[2, 3, 4, 5, 99, 0, 0, 0],[2, 3, 4, 5, 99, 495, 0, 0],[], [], "mul positions"],
    [[7, 3, 4, 5, 99, None, 0, 0],[7, 3, 4, 5, 99, 1, 0, 0],[], [], "lt positions"],
    [[8, 3, 4, 5, 99, None, 0, 0],[8, 3, 4, 5, 99, 0, 0, 0],[], [], "eq positions"],

    [[3, 4, 99, 0, 0, 0],[3, 4, 99, 0, 42, 0],[42], [], "load from input to position"],
    [[4, 2, 99, 0, 0, 0],[4, 2, 99, 0, 0, 0],[], [99], "store from position to output"],

    [[1106, 0, 4, None, 1101, 0, 0, 0, 99],[0, 0, 4, None, 1101, 0, 0, 0, 99],[], [], "jz all immediates"],
    [[1105, 1, 4, None, 1101, 0, 0, 0, 99],[0, 1, 4, None, 1101, 0, 0, 0, 99],[], [], "jnz all immediates"],
    
    [[3,9,8,9,10,9,4,9,99,-1,8], [3,9,8,9,10,9,4,9,99,1,8], [8], [1], "example from puzzle: positional true eq"],
    [[3,9,8,9,10,9,4,9,99,-1,8], [3,9,8,9,10,9,4,9,99,0,8], [7], [0], "example from puzzle: positional false eq"],
    [[3,9,7,9,10,9,4,9,99,-1,8], [3,9,7,9,10,9,4,9,99,1,8], [7], [1], "example from puzzle: positional true lt"],
    [[3,9,7,9,10,9,4,9,99,-1,8], [3,9,7,9,10,9,4,9,99,0,8], [8], [0], "example from puzzle: positional false lt"],

    [[3,3,1108,-1,8,3,4,3,99], [3,3,1108,1,8,3,4,3,99], [8], [1], "example from puzzle: immediate true eq"],
    [[3,3,1108,-1,8,3,4,3,99], [3,3,1108,0,8,3,4,3,99], [7], [0], "example from puzzle: immediate false eq"],
    [[3,3,1107,-1,8,3,4,3,99], [3,3,1107,1,8,3,4,3,99], [7], [1], "example from puzzle: immediate true lt"],
    [[3,3,1107,-1,8,3,4,3,99], [3,3,1107,0,8,3,4,3,99], [8], [0], "example from puzzle: immediate false lt"],

    [[3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9], [3,12,6,12,15,1,13,14,13,4,13,99,0,0,1,9], [0], [0], "example from puzzle: jump test positional"],
    [[3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9], [3,12,6,12,15,1,13,14,13,4,13,99,1,1,1,9], [1], [1], "example from puzzle: jump test positional"],

    [[3,3,1105,-1,9,1101,0,0,12,4,12,99,1], [3,3,1105,0,9,1101,0,0,12,4,12,99,0], [0], [0], "example from puzzle: jump test immediate"],
    [[3,3,1105,-1,9,1101,0,0,12,4,12,99,1], [3,3,1105,1,9,1101,0,0,12,4,12,99,1], [1], [1], "example from puzzle: jump test immediate"],

    [[3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99], None, [7], [999], "large example from puzzle description < 8"],
    [[3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99], None, [8], [1000], "large example from puzzle description == 8"],
    [[3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99], None, [9], [1001], "large example from puzzle description > 8"],
])
def test_solution(mem_init, mem, input, output, desc):
    c = solution.IntcodeComputer(mem_init)
    out = c.compute(input)
    if mem is not None:
        assert c.m == mem
    assert out == output
