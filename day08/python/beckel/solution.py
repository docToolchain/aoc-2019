import numpy as np

def render(image_display):
    for i in range(image_display.shape[0]):
        for j in range(image_display.shape[1]):
            if image_display[i,j] == 0:
                print(' ', end = '')
            else:
                print('█', end = '')
        print()
    
x = 25
y = 6
with open("input.txt") as fd:
    line = fd.readline()
    digits = [int(x) for x in line]
    z = len(digits) // x // y
    image = np.zeros([x,y,z],dtype=np.int64)
    
    for i in range(z):
        for j in range(y):
            image[:,j,i] = digits[i*x*y + j*x : i*x*y + (j+1)*x]

    # identify layer with least 0s
    test = image == 0
    zeros_on_layers = np.sum(test, axis=(0,1))
    z_min_zeros = np.argmin(zeros_on_layers)
    
    # Star 1: No. 1s * no. 2s on layer with least 0s
    num_one_digits = np.sum(image[:,:,z_min_zeros] == 1)
    num_two_digits = np.sum(image[:,:,z_min_zeros] == 2)
    print("Result Star 1:",num_one_digits*num_two_digits)

    # print the image (Star 2)
    image_display = np.zeros([x,y],dtype=np.int64)
    for i in range(x):
        for j in range(y):
            color_array = image[i,j,:]
            for color in color_array:
                if color == 0:
                    break
                if color == 1:
                    image_display[i,j] = 1
                    break
                else:
                    continue

    image_display = np.transpose(image_display)
    render(image_display)


