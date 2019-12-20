// Solution for day09
#include <iostream>
#include <algorithm>
#include <assert.h>
#include "IntMachine.h"
#include <map>
#include <vector>

auto paint(const Memory& baseMemory, bool firstColor) {
    auto memory = baseMemory;

    std::map<std::pair<int, int>, bool> panels;
    std::pair<int, int> currentPosition{0, 0};
    short currentDirection = 0; // up, right, down, left

    panels[{0, 0}] = firstColor;
    
    while(true) {
        std::vector<long long> currentInput;

        // Make sure to not touch the panels map if we do not write to it
        // TODO: do this via non-default constructable objects
        const auto& currentPanelIt = panels.find(currentPosition);
        if(currentPanelIt == panels.end()) {
            currentInput.push_back(0);
        } else {
            currentInput.push_back(currentPanelIt->second);
        }
        const auto& nextColorArray = memory.run(std::move(currentInput), true);
        if(nextColorArray.empty()) {
            break;
        }
        assert(nextColorArray.size() == 1);
        const auto& nextColor = nextColorArray.front();
        assert(nextColor == 0 or nextColor == 1);

        panels[currentPosition] = nextColor == 1;

        const auto& nextDirectionArray = memory.run(std::move(currentInput), true);
        assert(nextDirectionArray.size() == 1);
        const auto& nextDirection = nextDirectionArray.front();
        assert(nextDirection == 0 or nextDirection == 1);

        // Turn
        if(nextDirection == 0) {
            currentDirection = (currentDirection - 1 + 4) % 4;
        } else {
            currentDirection = (currentDirection + 1) % 4;
        }

        // Step
        if(currentDirection == 0) {
            // up
            currentPosition.second -= 1;            
        } else if(currentDirection == 1) {
            // right
            currentPosition.first += 1;            
        } else if(currentDirection == 2) {
            // down
            currentPosition.second += 1;            
        } else if(currentDirection == 3) {
            // left
            currentPosition.first -= 1;            
        }

    }

    return panels;
}

int main()
{
    // Read in the content
    auto baseMemory = Memory::readIn();

    // First Star
    std::cout << "First star: " << paint(baseMemory, 0).size() << std::endl;

    // Second star
    std::cout << "Second Star" << std::endl;
    auto panels = paint(baseMemory, 1);
    
    const auto& [minXIt, maxXIt] = std::minmax_element(panels.begin(), panels.end(), [](const auto& lhs, const auto& rhs) {
        return lhs.first.first < rhs.first.first;
    });

    const auto& [minYIt, maxYIt] = std::minmax_element(panels.begin(), panels.end(), [](const auto& lhs, const auto& rhs) {
        return lhs.first.second < rhs.first.second;
    });

    const auto& minX = minXIt->first.first;
    const auto& maxX = maxXIt->first.first;
    const auto& minY = minYIt->first.second;
    const auto& maxY = maxYIt->first.second;

    for(int y = minY; y <= maxY; y++) {
        for(int x = minX; x <= maxX; x++) {
            const auto& currentColor = panels[{x, y}];
            if(currentColor != 0) {
                std::cout << "#";
            } else {
                std::cout << ".";
            }
        }
        std::cout << std::endl;
    }
}
