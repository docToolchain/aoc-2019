#!/usr/bin/env python3

# tag::starOne[]
startFrequency = 0
currentFrequency = startFrequency
file = open("input.txt", "r")
for line in file:
    frequencyChange = int(line)
    currentFrequency += frequencyChange
print("Solution Star One: ", currentFrequency)
file.close()
# end::starOne[]

# tag::starTwo[]
startFrequency = 0
currentFrequency = startFrequency
alreadySeen = [currentFrequency]
found = False
for times in range(1000):
    file = open("input.txt", "r")
    for line in file:
        frequencyChange = int(line)
        currentFrequency += frequencyChange
        if currentFrequency in alreadySeen:
            print("Solution Star two: ", currentFrequency)
            found = True
            break
        alreadySeen.append(currentFrequency)
    if found:
        break
file.close()
# end::starTwo[]
