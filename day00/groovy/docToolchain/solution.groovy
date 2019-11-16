#!/usr/bin/env groovy

// tag::helper[]
String readInput(fileName) {
    new File(fileName).text
}
// end::helper[]

// tag::starOne[]
String helloWorld() {
    def whoShallBeGreeted = readInput("input.txt") // <1>
    "Hello ${whoShallBeGreeted}"
}

println "Solution: " + helloWorld()
// end::starOne[]


