def puzzle_input_list ():
    puzzle_input = '''
        114106
        87170
        133060
        70662
        134140
        125874
        50081
        133117
        100409
        95098
        70251
        134043
        87501
        85034
        110678
        80615
        64647
        88555
        106387
        143755
        101246
        142348
        92684
        62051
        94894
        65873
        78473
        64042
        147982
        145898
        85591
        121413
        132163
        94351
        80080
        73554
        106598
        135174
        147951
        132517
        50925
        115752
        114022
        73448
        50451
        56205
        81474
        90028
        124879
        137452
        91036
        87221
        126590
        130592
        91503
        148689
        86526
        105924
        52411
        146708
        149280
        52100
        80024
        115412
        91204
        132726
        59837
        129863
        140980
        109574
        103013
        84105
        138883
        144861
        126708
        140290
        54417
        138154
        125187
        91537
        90338
        61150
        61702
        95888
        100484
        82115
        122141
        63986
        138234
        54150
        57651
        124570
        88460
        112144
        112334
        119114
        58220
        143221
        86568
        148706
        '''
    #print (puzzle_input)
    puzzle_input_list = puzzle_input.split()
    return puzzle_input_list

# examples given in the puzzle
def test_data():
    return [12,14,1969,100756]


#calculate fuel amount per given mass
def fuel (mass):
    required_fuel = (mass//3-2)
    if required_fuel <= 0:
        return 0
    else:
        return required_fuel + fuel(required_fuel)

# iterate through list calculate mass and add to total sum
total = 0
for x in puzzle_input_list():
#for x in test_data():
    #print (int(x))
    total += fuel (int(x))
print (total)


