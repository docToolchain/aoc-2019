with open("image") as file:
    imageStr = file.read()

image = []
for char in imageStr:
    image.append(int(char))

size = len(image)
width = 25
height = 6
depth = int(size / width / height)
layerSize = int(size / depth)

layers = []
zeroes = []
for i in range(depth):
    layers.append(image[layerSize*i:layerSize*(i+1)])
    zeroes.append(len([p for p in layers[i] if p == 0]))
i = zeroes.index(min(zeroes))
ones = len([p for p in layers[i] if p == 1])
twos = len([p for p in layers[i] if p == 2])
print("solution for star 1: {}".format(ones*twos))

pixels = [2 for i in range(layerSize)]
for layer in layers:
    current = 0
    for p in layer:
        if p != 2 and pixels[current] == 2:
            pixels[current] = p
        current += 1
string = ""
for i in pixels:
    string += str(i)
print("solution for star 2: "+string)
