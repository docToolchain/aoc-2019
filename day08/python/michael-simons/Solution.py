#!/usr/bin/env python3

w = 25
h = 6
d = w * h

def chunks(lst, n):
    """Yield successive n-sized chunks from lst.

    This solution is from Andreas Zwinkau (https://github.com/qznc)
    I used `wrap` from textwrap before, but that doesn't play nice with whitespaces
    (Well, it does, but more for like text processing).
    Many other inputs have been very messed up and hardly readable.
    https://aoc-2019.netlify.com/generated/coder/qznc/generateddays#_day_08_python
    """
    for i in range(0, len(lst), n):
        yield lst[i:i + n]

with open('input.txt', 'r') as file:
    input = file.read().rstrip()

    layers = list(chunks(input, d)) # <1>
    mostTransparentLayer = sorted(layers, key=lambda s: s.count("0"))[0] # <2>
    starOne = mostTransparentLayer.count("1") * mostTransparentLayer.count("2")
    print(f"Star one {starOne}")

    translation = {"0": " ", "1" : "*", "2": "2"} # <3>
    image = ["2" for _ in range(d)] # <4>
    px = 0
    for layer in layers:
        for px in range(0, d):
            image[px] = translation[layer[px]] if image[px] == "2" else image[px]

    starTwo = "\n".join(chunks("".join(image), w)) # <5>
    print("Star two")
    print(starTwo)
