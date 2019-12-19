// Solution for day10
#include <iostream>
#include <fstream>
#include <sstream>
#include <assert.h>
#include <map>
#include <vector>
#include <utility>
#include <set>
#include <algorithm>
#include <math.h>

unsigned int ggT(unsigned int a, unsigned int b) {
    if(b == 0) {
        return a;
    }

    return ggT(b, a % b);
}

/// Small struct for the Astroids just keeping x and y position
struct Astroid {
    unsigned int x = 0;
    unsigned int y = 0;

    bool operator<(const Astroid& rhs) const {
        return std::make_pair(x, y) < std::make_pair(rhs.x, rhs.y);
    }
};

using SightLineType = double;

    class SightedAstroid {
    public:
        SightedAstroid(const Astroid& lhs, const Astroid& rhs) : m_astroid(rhs) {
            const auto xDistance = static_cast<double>(rhs.x) - static_cast<double>(lhs.x);
            const auto yDistance = static_cast<double>(rhs.y) - static_cast<double>(lhs.y);

            m_sightLineType = std::atan2(yDistance, xDistance) - M_PI/2;
            while(m_sightLineType >= M_PI) {
                m_sightLineType -= 2*M_PI;
            }
            while(m_sightLineType < -M_PI) {
                m_sightLineType += 2*M_PI;
            }
            m_manhattenDistance = std::abs(xDistance) + std::abs(yDistance);
        }

        const auto& getAstroid() const {
            return m_astroid;
        }

        const auto& getSightLineType() const {
            return m_sightLineType;
        }

        const auto& getDistance() const {
            return m_manhattenDistance;
        }

    private:
        SightLineType m_sightLineType;
        int m_manhattenDistance = 0;
        Astroid m_astroid;
    };


using SightLines = std::map<SightLineType, std::vector<SightedAstroid>>;

class AstroidSystem {
public:
    static AstroidSystem readIn() {
        AstroidSystem result;

        std::ifstream inputFile("input.txt");
        std::string line;
        char nextChar;

        unsigned int y = 0;

        while(std::getline(inputFile, line)) {
            unsigned int x = 0;
            auto sline = std::stringstream(line);
            while(sline >> nextChar) {
                if(nextChar == '#') {
                    result.addAstroid(x, y);
                }

                x += 1;
            }

            y += 1;
        }

        return result;
    }

    /// Order by the number of distance types and return the largest number
    auto getLargestSight() const {
        const auto maxSightIt = std::max_element(m_distances.begin(), m_distances.end(), [](const auto& lhs, const auto& rhs) {
            return lhs.second.size() < rhs.second.size();
        });
        assert(maxSightIt != m_distances.end());
        return maxSightIt;
    }


private:
    /// A list of all astroids
    std::vector<Astroid> m_astroids;

    /// A mapping from astroid to each distance type seen by the astroid
    std::map<Astroid, SightLines> m_distances;

    /// Add a new astroid to the list and fill in the calculated distances with each other astroid
    void addAstroid(unsigned int x, unsigned int y) {
        Astroid newAstroid{x, y};

        for(const auto& oldAstroid : m_astroids) {
            // Calculate the distance from this to all others and from all others to this
            SightedAstroid newToOld(newAstroid, oldAstroid);
            m_distances[newAstroid][newToOld.getSightLineType()].push_back(std::move(newToOld));

            // TODO: we only need to calculate the distance once!
            SightedAstroid oldToNew(oldAstroid, newAstroid);
            m_distances[oldAstroid][oldToNew.getSightLineType()].push_back(std::move(oldToNew));
        }

        m_astroids.push_back(std::move(newAstroid));
    }
};


int main()
{
    // Read in the content
    auto astroids = AstroidSystem::readIn();
    auto chosenAstroidIt = astroids.getLargestSight();

    // Very evil! We do a copy. But this is on purpose, as we want to edit things
    // Ok, in this example we actually do not care, but it is a good exercise :-)
    SightLines sightLines = chosenAstroidIt->second;
    for(auto& [lineType, astroids] : sightLines) {
        std::sort(astroids.begin(), astroids.end(), [](const auto& lhs, const auto& rhs){
            return lhs.getDistance() > rhs.getDistance();
        });
    }

    // First Star
    std::cout << "First star: " << sightLines.size() << std::endl;

    // Second Star
    std::cout << chosenAstroidIt->first.x << "/" << chosenAstroidIt->first.y << std::endl;

    std::cout << "Second star" << std::endl;
    unsigned int shootCounter = 0;
    auto sightLineIterator = sightLines.begin();
    Astroid astroidInShoot;
    // ATTENTION we assume that we actually have 200 elements to shoot to!
    while(shootCounter < 200) {
        auto& seenAstroids = sightLineIterator->second;
        if(not seenAstroids.empty()) {
            astroidInShoot = seenAstroids.back().getAstroid();
            seenAstroids.pop_back();
            shootCounter += 1;

            std::cout << "shooting " << astroidInShoot.x << "/" << astroidInShoot.y << std::endl;
        }
        sightLineIterator = std::next(sightLineIterator);
        if(sightLineIterator == sightLines.end()) {
            sightLineIterator = sightLines.begin();
        }
    }
    std::cout << astroidInShoot.x << "/" << astroidInShoot.y << std::endl;
}
