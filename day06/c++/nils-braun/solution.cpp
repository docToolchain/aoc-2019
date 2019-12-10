// Solution for day06: Universal Orbit Map
#include <fstream>
#include <iostream>
#include <assert.h>
#include <map>
#include <vector>
#include <sstream>
#include <set>

using Node = std::string;

class DirectedGraph {
public:
    void addRelation(const Node& from, const Node& to) {
        m_graph[from].push_back(to);
        assert(m_parent.find(to) == m_parent.end());
        m_parent[to] = from;
    }

    unsigned int summedNumberOfRelations() {
        unsigned int numberOfRelations = 0;
        for(const auto& [cell, number] : m_cells) {
            numberOfRelations += number;
        }
        return numberOfRelations;
    }

    const Node& getParent(const Node& child) {
        assert(m_parent.find(child) != m_parent.end());
        return m_parent[child];
    }

    unsigned int stepsBetween(const Node& first, const Node& second) {
        std::set<Node> pathFirst;
        buildPath(first, pathFirst);

        Node commonNode = second;
        while(pathFirst.find(commonNode) == pathFirst.end()) {
            commonNode = getParent(commonNode);
        }
        
        return (m_cells[first] + m_cells[second] - 2 * m_cells[commonNode]);
    }

    // Count automaton cell number
    void count(const Node& from = "COM", unsigned int parentCount = 0) {
        // safe for myself
        m_cells[from] = parentCount;

        // not increase by 1 for myself
        parentCount += 1;

        const auto& children = m_graph[from];
        // TODO: Here we cheat a bit and create a new vector if there are actually no children,
        // just to "loop" over it (which will give nothing)
        // Could be optimizes easily
        for(auto& children : children) {
            count(children, parentCount);
        }
    }

private:
    // If the node objects would be larger, we would probably store pointers here.
    // But a 3-char object is actually more or less as large as a pointer...
    std::map<Node, std::vector<Node>> m_graph;

    /// The same as graph but in the other direction
    std::map<Node, Node> m_parent;

    // Automaton Cells including the counts of each element
    std::map<Node, unsigned int> m_cells;

    void buildPath(const Node& from, std::set<Node>& path) {
        path.insert(from);
        const auto& parentIt = m_parent.find(from);
        if(parentIt != m_parent.end()) {
            buildPath(parentIt->second, path);
        }
    }
};


/// Split the input by comma
DirectedGraph readIn() {
    DirectedGraph result;

    std::ifstream inputFile("input.txt");
    std::string graphRelation;

    while(std::getline(inputFile, graphRelation)) {
        auto stream = std::stringstream(graphRelation);

        std::string from;
        std::getline(stream, from, ')');

        std::string to;
        std::getline(stream, to);

        result.addRelation(from, to);
    }

    result.count();
    return result;
}

int main()
{
    // Read in the content
    auto graph = readIn();

    // First star
    std::cout << graph.summedNumberOfRelations() << std::endl;

    // Second star
    std::cout << graph.stepsBetween(graph.getParent("YOU"), graph.getParent("SAN")) << std::endl;
    
}
