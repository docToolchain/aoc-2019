#include <utility>
#include <iostream>

/**
 * Class for string a point on the grid visited
 * by a wire.
 * 
 * It stores the x and y coordinates (first star)
 * and the travelled distance (second star).
 */
class Point : public std::pair<int, int> {
public:
    /// Helper function to sort two points according to their manhatten distance
    static bool manhattenSorter(const Point& lhs, const Point& rhs) { 
        return lhs.manhattenNorm() < rhs.manhattenNorm(); 
    }

    /** Helper function sorting only according to the xy position. 
     * By this we make sure that points with the same xy are only used 
     * once in a set.
     */
    class xySorter {
    public:
        bool operator()(const Point& lhs, const Point& rhs) {
            using BaseClass = std::pair<int, int>;
            return static_cast<BaseClass>(lhs) < static_cast<BaseClass>(rhs);
        }
    };

    /// Construct a point out of the x and y coordinate
    Point(int x, int y) : std::pair<int, int>(x, y){}

    /// Get x (as const reference)
    const int& getX() const { return first; }
    /// Get y (as const reference)
    const int& getY() const { return second; }
    /// Get the travelled distance (as const reference)
    const unsigned int& getDistance() const { return m_distance; }

    /// Get x (as reference)
    int& getX() { return first; }
    /// Get y (as reference)
    int& getY() { return second; }
    /// advance by 1 distance
    void advance() { m_distance++; }

    /// Calculate the manhatten norm
    unsigned int manhattenNorm() const { return std::abs(getX()) + std::abs(getY()); }
private:
    /// The travelled distance
    unsigned int m_distance = 0;
};

/// Small helper to make debugging easier: make points printable
std::ostream& operator<<(std::ostream& input, const Point& p) {
    return input << p.getX() << "/" << p.getY() << " (" << p.getDistance() << ")";
}