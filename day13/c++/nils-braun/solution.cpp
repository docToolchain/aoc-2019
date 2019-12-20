// Solution for day13
#include <iostream>
#include <algorithm>
#include <assert.h>
#include "IntMachine.h"
#include <vector>
#include <map>
#include <utility>
#include <thread>
#include <chrono>

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

class Game {
public:
    static Game readIn() {
        return Game(Memory::readIn());
    }

    void play(int input = 0) {
        const auto result = m_memory.run({input});
        assert(result.size() % 3 == 0);

        for(unsigned int counter = 0; counter < result.size(); counter += 3) {
            const auto point = Point(result[counter], result[counter + 1]);
            const auto content = result[counter + 2];

            if(point.x() == -1 and point.y() == 0) {
                m_score = content;
            } else {
                m_board[point] = content;
                if(content == 4) {
                    m_ballDirection = point - m_ballPosition;
                    m_ballPosition = point;
                }
                if(content == 3) {
                    m_pedalPosition = point;
                }
            }
        }

        m_lastInput = input;
    }

    void aiPlay() {
        auto nextDirection = m_ballDirection;
        const auto possibleNextPosition = m_ballPosition + nextDirection;
        const auto projectedPedalPosition = m_pedalPosition + Point(0, -1);
        // If we bounce against the pedal, we revert. We assume we actually hit the ball
        if(possibleNextPosition.y() == m_pedalPosition.y()) {
            nextDirection.x() = m_lastInput;
        }
        // If we bounce agains the wall, we also revert

        const auto nextPosition = m_ballPosition + nextDirection;

        if(m_ballPosition.x() > m_pedalPosition.x()) {
            play(1);
        } else if(m_ballPosition.x() < m_pedalPosition.x()) {
            play(-1);
        } else {
            play(0);
        }
    }

    void draw() const {
        printf("\033c");
        const auto& [minXIt, maxXIt] = std::minmax_element(m_board.begin(), m_board.end(), [](const auto& lhs, const auto& rhs) {
            return lhs.first.x() < rhs.first.x();
        });

        const auto& [minYIt, maxYIt] = std::minmax_element(m_board.begin(), m_board.end(), [](const auto& lhs, const auto& rhs) {
            return lhs.first.y() < rhs.first.y();
        });

        const auto& minX = minXIt->first.x();
        const auto& maxX = maxXIt->first.x();
        const auto& minY = minYIt->first.y();
        const auto& maxY = maxYIt->first.y();

        for(int y = minY; y <= maxY; y++) {
            for(int x = minX; x <= maxX; x++) {
                const auto contentIt = m_board.find({x, y});
                if(contentIt == m_board.end()) {
                    std::cout << " ";
                    continue;
                }

                const auto content = contentIt->second;
                if(content == 0) {
                    std::cout << " ";
                } else if(content == 1) {
                    std::cout << "#";
                } else if(content == 2) {
                    std::cout << ".";
                } else if(content == 3) {
                    std::cout << "=";
                } else if(content == 4) {
                    std::cout << "O";
                }
            }
            std::cout << std::endl;
        }

        std::cout << std::endl << "Score: " << m_score << std::endl;
        std::cout << "Pedal: " << m_pedalPosition.x() << "/" << m_pedalPosition.y() << std::endl;
        std::cout << "Ball: " << m_ballPosition.x() << "/" << m_ballPosition.y() << std::endl;
        std::cout << "Direction: " << m_ballDirection.x() << "/" << m_ballDirection.y() << std::endl;
    }

    unsigned int countBlockTiles() const {
        unsigned int blockTiles = 0;

        for(const auto& [position, content] : m_board) {
            if(content == 2) {
                blockTiles += 1;
            }
        }

        return blockTiles;
    }

    void insertCoins() {
        m_memory.adjustMemory(0, 2);
    }

    void reset() {
        m_memory = Memory::readIn();
        m_score = 0;
        m_board.clear();
    }

    unsigned int getScore() const {
        return m_score;
    }

private:
    Game(Memory&& memory) : m_memory(std::move(memory)) {}

    Memory m_memory;
    unsigned int m_score = 0;
    Point m_ballPosition{0, 0};
    Point m_ballDirection{0, 0};
    Point m_pedalPosition{0,0 };
    int m_lastInput = 0;
    std::map<Point ,unsigned int> m_board;
};

int main()
{
    using namespace std::chrono_literals;

    // Read in the content
    auto game = Game::readIn();

    std::cout << "First star: " << std::endl;
    game.play();
    const unsigned int blockTiles = game.countBlockTiles();
    std::cout << blockTiles << std::endl;

    game.reset();
    std::cout << "Second star: " << std::endl;
    game.insertCoins();
    do {
        game.aiPlay();
        //game.draw();
        //std::this_thread::sleep_for(0.1s);
        std::cout << game.countBlockTiles() << " " << game.getScore() << std::endl;
    } while(game.countBlockTiles() > 0);

}
