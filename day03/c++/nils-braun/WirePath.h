#include <Point.h>
#include <set>
#include <vector>
#include <algorithm>
#include <sstream>

/**
 * Class for storing the visited points.
 * We use a set with the custom xySorter to make sure that
 * points with the same xy end up only once.
 * As the wire is walked from beginning to back, the first one will stay
 * and this is the one with the smaller travelled distance.
 */
class WirePath : public std::set<Point, Point::xySorter> {
public:
    /// Create a wirepath out of a description of walking commands, separated by comma
    WirePath(const std::string& line) {
        auto stream = std::stringstream(line);

        std::string entity;
        while(std::getline(stream, entity, ',')) {
            walk(entity);
        }
    }

private:
    /// The current position. Is changed by walk()
    Point m_position{0, 0};

    /// "Walk" the specified direction and length by adding each visited point to the line
    void walk(const std::string& entity) {
        std::stringstream entityStream(entity);

        char dirChar;
        entityStream >> dirChar;

        unsigned int length;
        entityStream >> length;

        walk(dirChar, length);
    }

    /// "Walk" the specified direction and length by adding each visited point to the line
    void walk(char dir, unsigned int length) {
        // We cheat here a bit. We get a reference to the x or y value of the point ...
        int& value = getDirectedValue(dir);
        const auto offset = getOffset(dir);

        for(unsigned int i = 0; i < length; i++) {
            // ... and add +1 or -1 here. By this, we always use the same point and just manipulate its coordinates
            value += offset;
            // we also need to advance the distance by 1
            m_position.advance();
            // now we store it in our list
            insert(m_position);
        }
    }

    /// Should we advance the x or y coordinate?
    int& getDirectedValue(char dir) {
        if(dir == 'D' or dir == 'U') {
            return m_position.getY();
        } else {
            return m_position.getX();
        }
    }

    /// Should we advance foreward or backward?
    short getOffset(char dir) {
        if(dir == 'D' or dir == 'L') {
            return -1;
        } else {
            return 1;
        }
    }
};

/// Help with debugging: make wire lines printable
std::ostream& operator<<(std::ostream& input, const WirePath& path) {
    for(const auto& point : path) {
        input << point << std::endl;
    }
    return input;
}

/// Help with debugging: make intersections printable
std::ostream& operator<<(std::ostream& input, const std::vector<Point>& path) {
    for(const auto& point : path) {
        input << point << std::endl;
    }
    return input;
}
