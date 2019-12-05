// Solution for day03: Crossed Wires 
#include <WirePath.h>
#include <fstream>
#include <tuple>

/// Read in the two wire paths by reading in the first two lines
std::tuple<WirePath, WirePath> readIn() {
    std::ifstream inputFile("input.txt");
    std::string line;

    std::getline(inputFile, line);
    WirePath a(line);

    std::getline(inputFile, line);
    WirePath b(line);

    return {a, b};
}

std::vector<std::pair<Point, Point>> intersect(const WirePath& lhs, const WirePath& rhs) {
    std::vector<std::pair<Point, Point>> result;

    // This algorithm to build the intersection of two sets is basically stolen from std::set_intersection
    // with the difference that I wanted to have pairs of left and right, not just one of them
    auto lhsIt = lhs.begin();
    auto rhsIt = rhs.begin();

    while (lhsIt != lhs.end() and rhsIt != rhs.end()) {
        const auto& lhsElement = *lhsIt;
        const auto& rhsElement = *rhsIt;
        if (lhsElement < rhsElement) {
            lhsIt++;
        } else if (rhsElement < lhsElement) {
	        rhsIt++;
        } else {
            result.emplace_back(lhsElement, rhsElement);
            lhsIt++;
            rhsIt++;
        }
    }
      
    return result;
}

int main()
{
    const auto& [a, b] = readIn();
    const auto& intersections = intersect(a, b);

    // First star: sort by manhatten distance
    std::vector<unsigned int> manhattenDistances;
    manhattenDistances.reserve(intersections.size());
    std::transform(intersections.begin(), intersections.end(), std::back_inserter(manhattenDistances), [](const auto& point){
        // The point from the first and the second wire have the same coordinates, so just use the first one
        return point.first.manhattenNorm();
    });
    
    const auto minElement1 = std::min_element(manhattenDistances.begin(), manhattenDistances.end());
    std::cout << *minElement1 << std::endl;

    // Second star: sort by travelled distance
    std::vector<unsigned int> travelledDistances;
    travelledDistances.reserve(intersections.size());
    std::transform(intersections.begin(), intersections.end(), std::back_inserter(travelledDistances), [](const auto& point){
        return point.first.getDistance() + point.second.getDistance();
    });
    
    const auto minElement2 = std::min_element(travelledDistances.begin(), travelledDistances.end());
    std::cout << *minElement2 << std::endl;
}
