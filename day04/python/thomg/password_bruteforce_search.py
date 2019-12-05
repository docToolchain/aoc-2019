import re

def findAnyPair(word):
    if re.findall('([0-9])\\1+', word):
        return 1
    return 0

def findExclusivePair(word):
    repeats = re.findall('([0-9])\\1+', word)
    for i in repeats:
        if re.search(i+i+i, word):
            continue
        else:
            return 1
    return 0

matchCounter1 = 0
matchCounter2 = 0
for word in range(367479,893699):
    pairCounter = 0
    Descending = 1
    pw = str(word)
    for i in range(0,len(pw)-1):
        if int(pw[i]) > int(pw[i+1]):
            Descending = 0
    if findAnyPair(pw) and Descending:
        matchCounter1 += 1
        if findExclusivePair(pw):
            matchCounter2 += 1

print("solution for star 1: "+str(matchCounter1))
print("solution for star 2: "+str(matchCounter2))

