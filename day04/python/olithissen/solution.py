#!/usr/bin/env python3

import re

def solutionStar1(start, stop):
    counter = 0
    for number in range(start, stop):
        if (testNumber(number)):
            counter += 1
    return counter

def solutionStar2(start, stop):
    counter = 0
    for number in range(start, stop):
        if (testNumber2(number)):
            counter += 1
    return counter

def testNumber(number):
    numberAsString = str(number)
    ascending = re.match("^0*1*2*3*4*5*6*7*8*9*$", numberAsString)
    repeating = re.match(r'.*(\d)\1{1}.*', numberAsString)
    return bool(ascending and repeating)

def testNumber2(number):
    numberAsString = str(number)
    ascending = re.match("^0*1*2*3*4*5*6*7*8*9*$", numberAsString)
    repeating = re.findall(r'((\d)\2+)', numberAsString)
    return bool(ascending and matchesContainDoubleDigit(repeating))

def matchesContainDoubleDigit(matches):
    for match in matches:
        if(len(match[0]) == 2):
            return True
    
    return False

assert testNumber(12345) == False
assert testNumber(12344) == True
assert testNumber(12335) == True
assert testNumber(22222) == True
assert testNumber(12325) == False
assert testNumber(12255) == True
assert testNumber(54322) == False

assert testNumber2(12345) == False
assert testNumber2(12344) == True
assert testNumber2(12335) == True
assert testNumber2(22255) == True
assert testNumber2(22222) == False
assert testNumber2(12325) == False
assert testNumber2(12255) == True
assert testNumber2(54322) == False

realResultStar1 = solutionStar1(382345, 843167)
realResultStar2 = solutionStar2(382345, 843167)

print("Real result *1: ", realResultStar1)
assert realResultStar1 == 460

print("Real result *2: ", realResultStar2)
assert realResultStar2 == 290
