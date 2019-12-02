// Solution for day02: 1202 Program Alarm
#include <fstream>
#include <iostream>
#include <assert.h>
#include <vector>
#include <sstream>


std::vector<unsigned int> readIn() {
    std::vector<unsigned int> result;

    std::ifstream inputFile("input.txt");
    std::string intCodeAsString;

    while(std::getline(inputFile, intCodeAsString, ',')) {
        const unsigned int intCode = std::atoi(intCodeAsString.c_str());
        result.push_back(intCode);
    }

    return result;
}

unsigned int speak(unsigned int noun, unsigned int verb, const std::vector<unsigned int>& memoryBackup) {
    // First copy
    std::vector<unsigned int> memory = memoryBackup;

    // Do the replacements
    memory[1] = noun;
    memory[2] = verb;

    // No increase the intruction pointer until we have reached the end
    for(unsigned int i = 0; i < memory.size(); i++) {
        // And always do what we are told
        const unsigned int value = memory[i];
        if(value == 99) {
            break;
        } else if (value == 1 or value == 2) {
            assert(i + 3 < memory.size());
            const unsigned int first = memory[i + 1];
            const unsigned int second = memory[i + 2];
            const unsigned int result = memory[i + 3];

            // Make sure to jump :-)
            i += 3;

            if(value == 1) {
                memory[result] = memory[first] + memory[second];
            } else {
                memory[result] = memory[first] * memory[second];
            }
        } else {
            // Just a random error - not really needed in this case
            throw std::bad_alloc();
        }
    }

    return memory[0];

}


int main()
{
    // Read in the content
    const auto& memory = readIn();
    
    // First star:
    std::cout << speak(12, 2, memory) << std::endl;

    // Second star: (brute force)
    for(unsigned int noun = 0; noun <= 99; noun++) {
        for(unsigned int verb = 0; verb <= 99; verb++) {
            if(speak(noun, verb, memory) == 19690720) {
                std::cout << 100 * noun + verb << std::endl;
            }
        }
    }
}
