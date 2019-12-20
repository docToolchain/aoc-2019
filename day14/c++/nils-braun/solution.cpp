// Solution for day14
#include <iostream>
#include <assert.h>
#include <vector>
#include <map>
#include <fstream>
#include <sstream>
#include <regex>
#include <math.h>

using Element = std::string;

class Input : public std::map<Element, long long int> {
public:
    static Input oneOf(Element element) {
        Input result;
        result.insert({element, 1});
        return result;
    }

    static Input readIn(const std::string& string) {
        Input result;

        std::stringstream stream(string);
        std::string input;
        while(std::getline(stream, input, ',')) {
            std::stringstream inputStream(input);
            long long int quantity;
            inputStream >> quantity;
            Element element;
            inputStream >> element;

            result[element] = quantity;
        }

        return result;
    }

    Input operator*(long long int factor) const {
        Input result;
        for(const auto& [source, quantity] : *this) {
            result.insert({source, factor * quantity});
        }
        return result;
    }

    friend Input operator*(long long int factor, const Input& rhs)
    {
        return rhs * factor;
    }

    Input& operator+=(const Input& rhs) {
        auto& lhs = *this;
        for(const auto& [source, quantity] : rhs) {
            lhs[source] += quantity;
        }
        return lhs;
    }

    Input& operator-=(const Input& rhs) {
        *this += rhs * (-1);
        return *this;
    }
};


class Cookbook {
public:
    static Cookbook readIn() {
        Cookbook result;

        const std::regex reactionRegex("(.*) => (.*)");

        std::ifstream inputFile("input.txt");
        std::string reactionLine;

        while(std::getline(inputFile, reactionLine)) {
            std::smatch reactionMatch;

            if (not std::regex_match(reactionLine, reactionMatch, reactionRegex)) {
                std::cerr << "Do not understand " << reactionLine << std::endl;
            }

            Input inputs = Input::readIn(reactionMatch[1].str());
            Input outputs = Input::readIn(reactionMatch[2].str());

            assert(outputs.size() == 1);
            result.m_recipies[outputs.begin()->first] = {outputs.begin()->second, inputs};
        }

        return result;
    }

    Input cook(const Element& output, int long long factor = 1) const {
        Input requirements = factor * Input::oneOf(output);

        // We go through all requirements in our list and try to produce them.
        // We do this by calling one of our recipies exactly as much as needed (but not more)
        // this might produce more output than we actually need, so we will have "negative" requirements
        while(true) {
            bool didAReplacement = false;
            for(const auto& [requiredElement, requiredQuantity] : requirements) {
                // We have too much of it in the moment, so just keep it as it is
                if(requiredQuantity <= 0) {
                    continue;
                }

                // We can not produce OREs, so just skip them
                if(requiredElement == "ORE") {
                    continue;
                }

                // We need this `requiredElement` exactly `requiredQuantity` times.
                // But we might not have a recipe for this number, so calculate the factor
                // how often we need to call our recipe. Use ceil() as we can only call it in
                // integer numbers (of course).
                const auto& [creatableQuantity, requiredInputs] = m_recipies.at(requiredElement);
                const auto factor = std::ceil(static_cast<double>(requiredQuantity) / creatableQuantity);
                requirements += factor * requiredInputs;

                // We have now produced it, so we can remove the output of the recipe from our input
                requirements -= factor * creatableQuantity * Input::oneOf(requiredElement);

                didAReplacement = true;

                // Lets better break out of the loop here, as there might be changes to
                // the overall quantity
                break;
            }

            if(not didAReplacement) {
                // There is nothing we can still do -> break out of the loop
                // This could mean we are finished or not!
                break;
            }
        }

        for(const auto& [input, factor] : requirements) {
            // There should not be any requirements left except OREs
            assert(factor <= 0 or input == "ORE");
        }
        return requirements;
    }

private:
    std::map<Element, std::pair<long long int, Input>> m_recipies;
};

auto neededOre(const Cookbook& cookbook, long long int factor) {
    const auto& sources = cookbook.cook("FUEL", factor);
    return sources.at("ORE");
}

int main() {
    // First Star
    const auto& cookbook = Cookbook::readIn();
    const auto& orePerFuel = neededOre(cookbook, 1);
    std::cout << "First star: " << orePerFuel  << std::endl;

    const auto trillion = 1000000000000ll;
    auto minimumFuel = trillion / orePerFuel;
    auto maximumFuel = trillion;
    auto resultingFuel = (maximumFuel + minimumFuel) / 2;

    while(true) {
        const auto ore = neededOre(cookbook, resultingFuel);

        if(ore > trillion) {
            maximumFuel = resultingFuel;
        } else if(ore < trillion) {
            minimumFuel = resultingFuel;
        }

        const auto newResultingFuel = (maximumFuel + minimumFuel) / 2;

        if(newResultingFuel == resultingFuel) {
            break;
        }

        resultingFuel = newResultingFuel;
    }
    std::cout << resultingFuel << std::endl;
    std::cout << neededOre(cookbook, resultingFuel) << std::endl;
}