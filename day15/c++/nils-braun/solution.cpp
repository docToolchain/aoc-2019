// Solution for day15
#include <iostream>
#include <algorithm>
#include <assert.h>
#include "IntMachine.h"
#include <vector>
#include <map>
#include <utility>
#include <thread>
#include <chrono>
#include <iomanip>
#include <set>


class Point : public std::pair<int, int> {
public:
    Point(int x, int y) : std::pair<int, int>(x, y) {}

    const int& x() const { return first; }
    const int& y() const { return second; }

    int& x() { return first; }
    int& y() { return second; }

    Point operator+(const Point& rhs) const {
        return Point(x() + rhs.x(), y() + rhs.y());
    }

    Point operator-(const Point& rhs) const {
        return Point(x() - rhs.x(), y() - rhs.y());
    }
};

std::ostream& operator<<(std::ostream& o, const Point& p) {
    o << "Point(" << p.x() << "/" << p.y() << ")";
    return o;
}

class Map {
public:
    void reset() {
        m_currentPosition = {0, 0};
        m_nextDirection = 1;
        m_movements = 0;
    }

    int explore() {
        auto memory = Memory::readIn();
        while(not move(memory, m_nextDirection, true));
        reset();

        memory = Memory::readIn();
        while(not move(memory, m_nextDirection, false));

        return m_movements;
    }

    bool move(Memory& memory, int direction, bool reverse) {
        const auto& output = memory.run({direction}, 1);
        assert(output.size() == 1);

        const auto& content = output[0];

        auto nextPosition = advanced(m_currentPosition, direction);

        if(content == 0) {
            m_map[nextPosition] = '#';
            if(reverse) {
                turnRight(direction);
            } else {
                turnLeft(direction);
            }
            return false;
        } else if(content == 1) {
            m_map[nextPosition] = '.';
            m_currentPosition = nextPosition;
            m_movements += 1;
            if(m_movementMap[nextPosition] != 0 and m_movementMap[nextPosition] < m_movements) {
                m_movements = m_movementMap[nextPosition];
            } else {
                m_movementMap[nextPosition] = m_movements;
            }
            if(reverse) {
                turnLeft(direction);
            } else {
                turnRight(direction);
            }
            return false;
        } else if(content == 2) {
            m_map[nextPosition] = 'X';
            m_currentPosition = nextPosition;
            m_movements += 1;
            if(m_movementMap[nextPosition] != 0 and m_movementMap[nextPosition] < m_movements) {
                m_movements = m_movementMap[nextPosition];
            } else {
                m_movementMap[nextPosition] = m_movements;
            }

            m_targetPosition = m_currentPosition;

            return true;
        }
    }

    Point advanced(const Point& currentPosition, const int direction) const {
        auto nextPosition = currentPosition;
        if(direction == 1) {
            nextPosition.y() -= 1;
        } else if(direction == 2) {
            nextPosition.y() += 1;
        } else if(direction == 3) {
            nextPosition.x() -= 1;
        } else if(direction == 4) {
            nextPosition.x() += 1;
        }

        return nextPosition;
    }

    void turnLeft(const int direction) {
        if(direction == 1) {
            m_nextDirection = 3;
        } else if(direction == 2) {
            m_nextDirection = 4;
        } else if(direction == 3) {
            m_nextDirection = 2;
        } else if(direction == 4) {
            m_nextDirection = 1;
        }
    }

    void turnRight(const int direction) {
        if(direction == 1) {
            m_nextDirection = 4;
        } else if(direction == 2) {
            m_nextDirection = 3;
        } else if(direction == 3) {
            m_nextDirection = 1;
        } else if(direction == 4) {
            m_nextDirection = 2;
        }
    }

    void draw() const {
        printf("\033c");
        const auto& [minXIt, maxXIt] = std::minmax_element(m_map.begin(), m_map.end(), [](const auto& lhs, const auto& rhs) {
            return lhs.first.x() < rhs.first.x();
        });

        const auto& [minYIt, maxYIt] = std::minmax_element(m_map.begin(), m_map.end(), [](const auto& lhs, const auto& rhs) {
            return lhs.first.y() < rhs.first.y();
        });

        const auto& minX = minXIt->first.x();
        const auto& maxX = maxXIt->first.x();
        const auto& minY = minYIt->first.y();
        const auto& maxY = maxYIt->first.y();

        for(int y = minY; y <= maxY; y++) {
            for(int x = minX; x <= maxX; x++) {
                if(x == m_currentPosition.x() and y == m_currentPosition.y()) {
                    std::cout << "  D";
                    continue;
                }
                const auto contentIt = m_movementMap.find({x, y});
                if(contentIt == m_movementMap.end()) {
                    std::cout << "   ";
                    continue;
                }

                const auto content = contentIt->second;
                std::cout << std::setw(3) << content;
            }
            std::cout << std::endl;
        }
    }

    unsigned int fillWithOxygen(Point start, std::set<Point>& alreadyFilled, unsigned int pathLength) {
        if(alreadyFilled.find(start) != alreadyFilled.end()) {
            // we are here already - we do not need to go further
            return pathLength;
        }

        if(m_map.find(start) == m_map.end()) {
            // end of map
            return pathLength;
        }

        if(m_map.at(start) == '#') {
            // We hit a wall, we can not go further
            return pathLength;
        }

        // we can actually do the step!
        pathLength += 1;
        alreadyFilled.insert(start);

        // Now explore further
        return std::max({
            fillWithOxygen(advanced(start, 1), alreadyFilled, pathLength),
            fillWithOxygen(advanced(start, 2), alreadyFilled, pathLength),
            fillWithOxygen(advanced(start, 3), alreadyFilled, pathLength),
            fillWithOxygen(advanced(start, 4), alreadyFilled, pathLength)
        });
    }

    const Point& getTargetPosition() const {
        return m_targetPosition;
    }

private:
    std::map<Point, char> m_map;
    std::map<Point, int> m_movementMap;
    unsigned int m_movements = 0;
    Point m_currentPosition{0, 0};
    Point m_targetPosition{0, 0};
    int m_nextDirection = 1;
};

int main()
{
    // Read in the content
    Map map;

    std::cout << "First star: ";
    std::cout << map.explore() << std::endl;

    const auto& targetPosition = map.getTargetPosition();
    std::set<Point> alreadySeen = {};
    const auto numberOfMoves = map.fillWithOxygen(targetPosition, alreadySeen, 0);
    std::cout << "Second star: " << numberOfMoves - 1 << std::endl;
}
