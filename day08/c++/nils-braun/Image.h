// Solution for day05: 1202 Program Alarm
#include <fstream>
#include <iostream>
#include <assert.h>
#include <vector>
#include <map>
#include <sstream>


using Digit = unsigned short;
using LayerCounts = std::map<Digit, unsigned int>;

template <unsigned int X, unsigned int Y>
class Layer {
public:
    Layer() {
        m_digits.reserve(X*Y);
        for(Digit d = 0; d <= 2; d++) {
            m_layerCounts[d] = 0;
        }
    }

    void setTransparent() {
        std::fill_n(m_digits.begin(), X*Y, 2);
    }

    void addDigit(Digit&& digit) {
        m_digits.push_back(std::move(digit));
        m_layerCounts[digit] += 1;
    }

    bool finished() const {
        return m_digits.size() == X*Y;
    }

    const LayerCounts& layerCounts() const {
        return m_layerCounts;
    }

    const Digit& getDigit(unsigned int x, unsigned int y) const {
        return m_digits[y * X + x];
    }

    void setDigit(unsigned int x, unsigned int y, const Digit& digit) {
        m_digits[y*X + x] = digit;
    }

    Layer& operator+=(const Layer& rhs) {
        for(unsigned int x = 0; x < X; x++) {
            for(unsigned int y = 0; y < Y; y++) {
                const auto& lhsDigit = getDigit(x, y);
                const auto& rhsDigit = rhs.getDigit(x, y);

                if(lhsDigit == 2) {
                    setDigit(x, y, rhsDigit);
                } else {
                    setDigit(x, y, lhsDigit);
                }
            }
        }
        return *this;
    }

    void print() const {
        for(unsigned int y = 0; y < Y; y++) {
            for(unsigned int x = 0; x < X; x++) {
                const auto& digit = getDigit(x, y);
                if(digit == 0) {
                    std::cout << " ";
                } else {
                    std::cout << "#";
                }
            }
            std::cout << std::endl;
        }
    }

private:
    std::vector<Digit> m_digits;
    LayerCounts m_layerCounts;
};

template <unsigned int X, unsigned int Y>
class Image {
public:
    static Image readIn() {
        std::ifstream inputFile("input.txt");
        char digit;

        Image image;
        Layer<X, Y> layer;
        while(inputFile >> digit) {
            // ASCII is nice, we can just subtract '0' to convert between ASCII code and real digit
            layer.addDigit(digit - '0');
            if(layer.finished()) {
                image.addLayer(layer);
                layer = Layer<X, Y>();
            }
        }

        return image;
    }

    std::vector<LayerCounts> layerCounts() const {
        std::vector<LayerCounts> results;

        for(const auto& layer : m_layers) {
            results.push_back(layer.layerCounts());
        }

        return results;
    }

    Layer<X, Y> sumUp() const {
        Layer<X, Y> result;
        result.setTransparent();

        // I choose to do this row-wise, as it might be better for the caching
        // I did not do any profiling though, so this is just a guess

        for(const auto& layer : m_layers) {
            result += layer;
        }

        return result;
    }

private:
    std::vector<Layer<X, Y>> m_layers;

    void addLayer(const Layer<X, Y>& layer) {
        m_layers.push_back(layer);
    }
};