// Solution for day04: Secure Container
#include <fstream>
#include <iostream>
#include <assert.h>
#include <map>
#include <vector>
#include <numeric>
#include <tuple>

using Number = std::vector<unsigned int>;
constexpr const unsigned int NUMBERSIZE = 6;

bool checkSecondStarRequirement(const Number& number) {
    std::map<unsigned int, unsigned int> counter;

    for(const auto& digit : number) {
        counter[digit] += 1;
    }

    for(const auto& [digit, count] : counter) {
        if(count == 2) {
            return true;
        }
    }
    
    return false;
}

bool checkFirstStarRequirement(const Number& number) {
    std::map<unsigned int, unsigned int> counter;

    for(const auto& digit : number) {
        counter[digit] += 1;
        if(counter[digit] > 1) {
            return true;
        }
    }

    return false;
}

unsigned int toUInt(const Number& number) {
    unsigned int realValue = 0;
    for(const auto& d : number) {
        realValue = 10 * realValue + d;
    }
    return realValue;
}

unsigned int testAllNumbers(Number& currentTest, unsigned int lowerBound, const unsigned int higherBound, bool secondQuiz) {
    // We have reached the end!
    if(currentTest.size() == NUMBERSIZE) {
        // First make sure we fulfill all our requirements
        if(secondQuiz) {
            if(not checkSecondStarRequirement(currentTest)) {
                return 0;
            }
        } else {
            if(not checkFirstStarRequirement(currentTest)) {
                return 0;
            }
        }
        
        // Now check if we are in the ranges
        const unsigned int realValue = toUInt(currentTest);
        return realValue >= lowerBound and realValue <= higherBound;
    }

    // We go on recursively
    unsigned int counter = 0;
    const unsigned int lastHighest = currentTest.empty() ? 0 : currentTest.back();
    for(unsigned int nextDigit = lastHighest; nextDigit < 10; nextDigit++) {
        currentTest.push_back(nextDigit);
        counter += testAllNumbers(currentTest, lowerBound, higherBound, secondQuiz);
        currentTest.pop_back();
    }
    return counter;
}

int main()
{
    // Read in the file and add up the fuel costs
    std::ifstream inputFile("input.txt");

    unsigned int firstNumber, secondNumber;
    inputFile >> firstNumber;
    inputFile >> secondNumber;

    Number test;
    std::cout << "First star: " << testAllNumbers(test, firstNumber, secondNumber, false) << std::endl;
    std::cout << "Second star: " << testAllNumbers(test, firstNumber, secondNumber, true) << std::endl;
}
