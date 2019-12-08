#!/usr/bin/env python3

from textwrap import wrap

w = 25
h = 6
d = w * h

with open('input.txt', 'r') as file:
    input = file.read()

    layers = wrap(input, width=d) # <1>
    mostTransparentLayer = sorted(layers, key=lambda s: s.count("0"))[0] # <2>
    starOne = mostTransparentLayer.count("1") * mostTransparentLayer.count("2")
    print(f"Star one {starOne}")

    translation = {"0": " ", "1" : "*", "2": "2"} # <3>
    image = ["2" for _ in range(d)] # <4>
    px = 0
    for layer in layers:
        for px in range(0, d):
            image[px] = translation[layer[px]] if image[px] == "2" else image[px]

    starTwo = "\n".join(wrap("".join(image),width=w)) # <5>
    print("Star two")
    print(starTwo)
