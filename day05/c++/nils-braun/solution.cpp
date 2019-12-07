// Solution for day05: 1202 Program Alarm
#include <fstream>
#include <iostream>
#include <assert.h>
#include <vector>
#include <tuple>
#include <sstream>

class ParameterModes {
public:
    ParameterModes(unsigned int parameterModes) : m_intValue(parameterModes) {}

    bool getNextMode() {
        // Grab the next parameter mode and delete it from the list of parameter modes
        const bool immediateMode = m_intValue % 10 > 0;
        m_intValue /= 10;

        return immediateMode;
    }

private:
    unsigned int m_intValue = 0;
};

class Memory : public std::vector<int> {
public:
    std::vector<int> run(std::vector<int>&& input) {
        m_input.swap(input);

        while(hasNextInstruction()) {
            const int instruction = getNextInstruction();
            const unsigned int opCode = instruction % 100;
            ParameterModes parameterModes(instruction / 100);

            if(not apply(opCode, parameterModes)) {
                break;
            }
        }

        return m_output;
    }

private:
    // The address of the current instruction
    unsigned int m_ic = 0;

    // The input vector
    std::vector<int> m_input;

    // The output vector
    std::vector<int> m_output;

    /// Apply the operation given by the opcode and the parameter modes
    bool apply(unsigned int opCode, ParameterModes parameterModes) {
        if(opCode == 1) {
            // addition
            const auto firstParam = getNextParameter(parameterModes);
            const auto secondParam = getNextParameter(parameterModes);
            setAtNextParameter(parameterModes, firstParam + secondParam);
        } else if (opCode == 2) {
            // multiply
            const auto firstParam = getNextParameter(parameterModes);
            const auto secondParam = getNextParameter(parameterModes);
            setAtNextParameter(parameterModes, firstParam * secondParam);
        } else if (opCode == 3) {
            // read input from internal vector
            const auto input = m_input.back();
            m_input.pop_back();
            setAtNextParameter(parameterModes, input);
        } else if (opCode == 4) {
            // write output to internal vector
            const auto param = getNextParameter(parameterModes);
            m_output.push_back(param);
        } else if(opCode == 5) {
            // jump if true
            const auto firstParam = getNextParameter(parameterModes);
            const auto secondParam = getNextParameter(parameterModes);
            if(firstParam != 0) {
                m_ic = secondParam;
            }
        } else if(opCode == 6) {
            // jump if false
            const auto firstParam = getNextParameter(parameterModes);
            const auto secondParam = getNextParameter(parameterModes);
            if(firstParam == 0) {
                m_ic = secondParam;
            }
        } else if(opCode == 7) {
            // less then
            const auto firstParam = getNextParameter(parameterModes);
            const auto secondParam = getNextParameter(parameterModes);
            setAtNextParameter(parameterModes, firstParam < secondParam);
        } else if(opCode == 8) {
            // less then
            const auto firstParam = getNextParameter(parameterModes);
            const auto secondParam = getNextParameter(parameterModes);
            setAtNextParameter(parameterModes, firstParam == secondParam);
        } else if (opCode == 99) {
            return false;
        } else {
            throw std::exception();
        }

        return true;
    }

    /**
     * Helper function to read the parameter value given by the parameter.
     * 
     * If the next parameterMode (the least-significant digit) is 1, use the parameter directly.
     * If the parameterMode is 0, interpret the parameter as an address in memory.
     */
    int readNumber(bool immediateMode, int parameterOrAddress) const {
        if(immediateMode) {
            return parameterOrAddress;
        } else {
            // We use .at() instead of [] to have boundary checks
            return at(parameterOrAddress);
        }
    }

    /// Write at the given address. This can not be immediate mode!
    int writeNumber(bool immediateMode, int parameterAddress, int value) {
        assert(not immediateMode);
        at(parameterAddress) = value;
    }

    /// Get the parameter specified by the mode and the next instruction
    int getNextParameter(ParameterModes& parameterModes) {
        const bool immediateMode = parameterModes.getNextMode();
        const int parameterOrAddress = getNextInstruction();
        return readNumber(immediateMode, parameterOrAddress);
    }

    /// Use the address of the next parameter to write down the given value
    int setAtNextParameter(ParameterModes& parameterModes, int value) {
        const bool immediateMode = parameterModes.getNextMode();
        const int parameterOrAddress = getNextInstruction();
        return writeNumber(immediateMode, parameterOrAddress, value);
    }

    /// Get the instruction pointed by the instruction pointer
    int getNextInstruction() {
        const int instruction = operator[](m_ic);
        m_ic++;
        return instruction;
    }
    
    /// Check if we are still in the range of the memory
    bool hasNextInstruction() const {
        return m_ic < size();
    }
};

/// Split the input by comma
Memory readIn() {
    Memory result;

    std::ifstream inputFile("input.txt");
    std::string intCodeAsString;

    while(std::getline(inputFile, intCodeAsString, ',')) {
        const auto intCode = std::atoi(intCodeAsString.c_str());
        result.push_back(intCode);
    }

    return result;
}

/// Just a helper for printing
void printOutput(const std::vector<int> output) {
    for(const auto& o : output) {
        std::cout << o << std::endl;
    }
}

int main()
{
    // Read in the content
    auto memory = readIn();

    // First star
    auto firstStarMemory = memory;
    printOutput(firstStarMemory.run({1}));

    // Second star
    auto secondStarMemory = memory;
    printOutput(secondStarMemory.run({5}));

    
}
