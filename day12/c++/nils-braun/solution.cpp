// Solution for day11
#include <iostream>
#include <assert.h>
#include <vector>
#include <tuple>

int sign(int value) {
    return (0 < value) - (value < 0);
}

unsigned long long ggT(unsigned long long a, unsigned long long b){
    if(b == 0)
        return a;
    else return ggT(b, a % b);
}

unsigned long long kgV(unsigned long long a, unsigned long long b){
    return (a * b) / ggT(a, b);
}

class Moon {
public:
    Moon(int x, int y, int z) : m_x(x), m_y(y), m_z(z) {}

    void pullGravityFrom(const std::vector<Moon>& moons) {
        for(const auto& from : moons) {
            pullGravityFrom(from);
        }
    }

    void pullGravityFrom(const Moon& from) {
        m_vx += sign(from.getX() - getX());
        m_vy += sign(from.getY() - getY());
        m_vz += sign(from.getZ() - getZ());
    }

    void advance() {
        m_x += m_vx;
        m_y += m_vy;
        m_z += m_vz;
    }

    void print() const {
        std::cout << "<";
        std::cout << "x=" << getX() << ", ";
        std::cout << "y=" << getY() << ", ";
        std::cout << "z=" << getZ() << ", ";
        std::cout << "vx=" << getVX() << ", ";
        std::cout << "vy=" << getVY() << ", ";
        std::cout << "vz=" << getVZ();
        std::cout << ">" << std::endl;
    }

    unsigned int getTotalEnergy() const {
        const unsigned int potentialEnergy = std::abs(getX()) + std::abs(getY()) + std::abs(getZ());
        const unsigned int kinematicEnergy = std::abs(getVX()) + std::abs(getVY()) + std::abs(getVZ());

        return potentialEnergy * kinematicEnergy;
    }

    int getX() const {
        return m_x;
    }

    int getY() const {
        return m_y;
    }

    int getZ() const {
        return m_z;
    }

    int getVX() const {
        return m_vx;
    }

    int getVY() const {
        return m_vy;
    }

    int getVZ() const {
        return m_vz;
    }

    bool equalX(const Moon& rhs) const {
        return getX() == rhs.getX() and
               getVX() == rhs.getVX();
    }

    bool equalY(const Moon& rhs) const {
        return getY() == rhs.getY() and
               getVY() == rhs.getVY();
    }

    bool equalZ(const Moon& rhs) const {
        return getZ() == rhs.getZ() and
               getVZ() == rhs.getVZ();
    }


private:
    int m_x = 0;
    int m_y = 0;
    int m_z = 0;
    int m_vx = 0;
    int m_vy = 0;
    int m_vz = 0;
};

class MoonList : public std::vector<Moon> {
public:
    using std::vector<Moon>::vector;

    void pullGravity() {
        for(auto& moon : *this) {
            moon.pullGravityFrom(*this);
        }
    }

    void advance() {
        for(auto& moon : *this) {
            moon.advance();
        }
    }

    std::tuple<bool, bool, bool> checkEquality(const MoonList& rhs) const {
        const auto& lhs = *this;

        bool equalX = true;
        bool equalY = true;
        bool equalZ = true;

        for(unsigned int i = 0; i < size(); i++) {
            equalX &= lhs[i].equalX(rhs[i]);
            equalY &= lhs[i].equalY(rhs[i]);
            equalZ &= lhs[i].equalZ(rhs[i]);
        }

        return {equalX, equalY, equalZ};
    }
};


int main()
{
    MoonList startMoons{
        {4, 12, 13},
        {-9, 14, -3},
        {-7, -1, 2},
        {-11, 17, -1},
    };

    auto moons = startMoons;

    unsigned long long repeatingX = 0;
    unsigned long long repeatingY = 0;
    unsigned long long repeatingZ = 0;
    unsigned int totalEnergyAfter1000 = 0;

    for(unsigned int simulation = 1; ; simulation++) {
        moons.pullGravity();
        moons.advance();

        const auto& [equalX, equalY, equalZ] = moons.checkEquality(startMoons);

        if(equalX and repeatingX == 0) {
            repeatingX = simulation;
        }
        if(equalY and repeatingY == 0) {
            repeatingY = simulation;
        }
        if(equalZ and repeatingZ == 0) {
            repeatingZ = simulation;
        }

        if(simulation == 1000) {
            for(auto& moon : moons) {
                totalEnergyAfter1000 += moon.getTotalEnergy();
            }
        }

        if(repeatingX > 0 and repeatingY > 0 and repeatingZ > 0) {
            break;
        }
    }


    std::cout << "First star: " << totalEnergyAfter1000 << std::endl;
    std::cout << "Second star: " << repeatingX << " " << repeatingY << " " << repeatingZ << std::endl;
    std::cout << "Second star: " << kgV(kgV(repeatingX, repeatingY), repeatingZ) << std::endl;
}
