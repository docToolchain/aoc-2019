// Solution for day05: 1202 Program Alarm
#include <fstream>
#include <iostream>
#include <assert.h>
#include <vector>
#include <tuple>
#include <sstream>

class Memory {
private:
    using MemoryCell = long long int;

    enum class ParameterMode {
        positionalMode = 0,
        immediateMode = 1,
        relativeMode = 2
    };

    class ParameterModes {
    public:
        ParameterModes(unsigned int parameterModes) : m_intValue(parameterModes) {}

        ParameterMode getNextMode() {
            // Grab the next parameter mode and delete it from the list of parameter modes
            const auto parameterMode = m_intValue % 10;
            m_intValue /= 10;

            return static_cast<ParameterMode>(parameterMode);
        }

    private:
        unsigned int m_intValue = 0;
    };

public:
    /// Split the input by comma
    static Memory readIn() {
        Memory result;

        std::ifstream inputFile("input.txt");
        std::string intCodeAsString;

        while(std::getline(inputFile, intCodeAsString, ',')) {
            const auto intCode = std::atoll(intCodeAsString.c_str());
            result.m_memory.push_back(intCode);
        }

        return result;
    }

    std::vector<MemoryCell> run(std::vector<MemoryCell>&& input, bool stopOnOutput = false) {
        m_input.swap(input);
        m_output.clear();

        while(hasNextInstruction()) {
            const auto instruction = getNextInstruction();
            const unsigned int opCode = instruction % 100;
            ParameterModes parameterModes(instruction / 100);

            if(not apply(opCode, parameterModes, stopOnOutput)) {
                break;
            }
        }

        return m_output;
    }

private:
    /// The memory content
    std::vector<MemoryCell> m_memory;

    /// The address of the current instruction
    MemoryCell m_ic = 0;

    /// The relative base address
    MemoryCell m_relativeBase = 0;

    /// The input vector
    std::vector<MemoryCell> m_input;

    /// The output vector
    std::vector<MemoryCell> m_output;

    /// Apply the operation given by the opcode and the parameter modes
    bool apply(unsigned int opCode, ParameterModes parameterModes, bool stopOnOutput) {
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
            // TODO: reversed!!!
            if(m_input.empty()) {
                // Nothing to digest!
                // more one step back, so the next time we can continue here
                m_ic--;
                return false;
            }
            const auto input = m_input.back();
            m_input.pop_back();
            setAtNextParameter(parameterModes, input);
        } else if (opCode == 4) {
            // write output to internal vector
            const auto param = getNextParameter(parameterModes);
            m_output.push_back(param);
            if(stopOnOutput) {
                return false;
            }
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
        } else if(opCode == 9) {
            // adjust relative base
            const auto param = getNextParameter(parameterModes);
            m_relativeBase += param;
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
    MemoryCell readNumber(ParameterMode parameterMode, MemoryCell parameterOrAddress) {
        if(parameterMode == ParameterMode::immediateMode) {
            return parameterOrAddress;
        } else if(parameterMode == ParameterMode::positionalMode) {
            // We use .at() instead of [] to have boundary checks
            return get(parameterOrAddress);
        } else if(parameterMode == ParameterMode::relativeMode) {
            return get(parameterOrAddress + m_relativeBase);
        }
    }

    /// Write at the given address. This can not be immediate mode!
    void writeNumber(ParameterMode parameterMode, MemoryCell parameterAddress, MemoryCell value) {
        assert(parameterMode != ParameterMode::immediateMode);
        if(parameterMode == ParameterMode::relativeMode) {
            parameterAddress += m_relativeBase;
        }
        
        get(parameterAddress) = value;
    }

    /// Get the parameter specified by the mode and the next instruction
    MemoryCell getNextParameter(ParameterModes& parameterModes) {
        const auto parameterMode = parameterModes.getNextMode();
        const auto parameterOrAddress = getNextInstruction();
        return readNumber(parameterMode, parameterOrAddress);
    }

    /// Use the address of the next parameter to write down the given value
    void setAtNextParameter(ParameterModes& parameterModes, MemoryCell value) {
        const auto parameterMode = parameterModes.getNextMode();
        const auto parameterOrAddress = getNextInstruction();
        writeNumber(parameterMode, parameterOrAddress, value);
    }

    /// Get the instruction pointed by the instruction pointer
    MemoryCell getNextInstruction() {
        const auto instruction = get(m_ic);
        m_ic++;
        return instruction;
    }
    
    /// Check if we are still in the range of the memory
    bool hasNextInstruction() const {
        return m_ic < m_memory.size();
    }

    MemoryCell& get(MemoryCell address) {
        if(address >= m_memory.size()) {
            m_memory.resize(address + 1, 0);
        }
        return m_memory[address];
    }
};