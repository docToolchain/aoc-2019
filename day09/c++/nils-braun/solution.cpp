// Solution for day09
#include <iostream>
#include <algorithm>
#include <assert.h>
#include "IntMachine.h"

int main()
{
    // Read in the content
    auto baseMemory = Memory::readIn();

    // First Star
    std::cout << "First star: " << std::endl;
    const auto& firstStarOutput = baseMemory.run({1}, false);
    for(const auto& o : firstStarOutput) {
        std::cout << o << std::endl;
    }

    // Second Star
    std::cout << "Second star: " << std::endl;
    const auto& secondStarOutput = baseMemory.run({2}, false);
    for(const auto& o : secondStarOutput) {
        std::cout << o << std::endl;
    }
}
