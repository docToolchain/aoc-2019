// Solution for day08
#include "Image.h"
#include <algorithm>
#include <iostream>

constexpr const auto WIDTH = 25;
constexpr const auto HEIGHT = 6;

int main()
{
    // Read in the content
    const auto& image = Image<WIDTH, HEIGHT>::readIn();

    // First Star
    const auto& layerCounts = image.layerCounts();

    const auto& fewestNullIt = std::min_element(layerCounts.begin(), layerCounts.end(), [](const auto& lhs, const auto& rhs) {
        return lhs.at(0) < rhs.at(0);
    });

    assert(fewestNullIt != layerCounts.end());
    const auto fewestNull = *fewestNullIt;

    std::cout << "First star: " << fewestNull.at(1) * fewestNull.at(2) << std::endl;

    // Second Star
    const auto& summedImage = image.sumUp();

    std::cout << "Second star: " << std::endl;
    summedImage.print();
}
