// Solution for day07
#include <iostream>
#include <algorithm>
#include <assert.h>
#include "IntMachine.h"

int testPhases(const Memory& memory, const std::vector<int> phaseSettings, bool repeat = false) {
    // Init the computers with the phase settings
    std::vector<Memory> memories;
    for(const auto& phaseSetting : phaseSettings) {
        memories.push_back(memory);
        memories.back().run({phaseSetting});
    }

    // Now run the program repeatedly (if requested)
    int currentSignal = 0;
    bool hasEnded = false;

    while(not hasEnded) {
        for(auto& memory : memories) {
            const auto& result = memory.run({currentSignal}, true);
            if(result.empty()) {
                hasEnded = true;
                continue;
            }
            assert(result.size() == 1);
            currentSignal = result.front();
        }
        
        if(not repeat) {
            hasEnded = true;
        }
    }

    return currentSignal;
}

/// Loop through all combinations
int testAllCombinations(const Memory& baseMemory, std::vector<int> inputPhases, bool repeat = false) {
    int highestOutput = 0;

    do {
        const auto currentOutput = testPhases(baseMemory, inputPhases, repeat);
        if(currentOutput > highestOutput) {
            highestOutput = currentOutput;
        }
    } while(std::next_permutation(inputPhases.begin(), inputPhases.end()));
    
    return highestOutput;
}

int main()
{
    // Read in the content
    const auto& baseMemory = Memory::readIn();

    // First Star
    std::cout << "First star: " << testAllCombinations(baseMemory, {0, 1, 2, 3, 4}, false) << std::endl;

    // Second Star
    std::cout << "Second star: " << testAllCombinations(baseMemory, {5, 6, 7, 8, 9}, true) << std::endl;
}
