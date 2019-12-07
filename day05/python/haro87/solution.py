import random
import os
from enterprise import MontgomeryScott as Scotty

cwd = os.getcwd()
ifile = cwd + "/input.txt"

with open(ifile, "r") as commands:
    #Part one
    scotty = Scotty(input=1)
    trans = scotty.translate(commands.read())
    res = scotty.execute(trans.copy())
    print("Scotty says the solution for part one is: {0}".format(res[-1]))

    #Part two
    scotty = Scotty(input=5)
    res = scotty.execute(trans.copy())
    print("Scotty says the solution for part two is: {0}".format(res[-1]))
