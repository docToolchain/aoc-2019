import math
import sys
import numpy as np

def fft(output_list, num_phases):
    pattern = (0, 1, 0, -1)
    for phase in range(num_phases):
        print("Phase",phase)
        input_list = output_list
        output_list = ''
        for i in range(len(input_list)):
            current_pattern = [x for x in pattern for _ in range(i+1)]
            sum_of_phase = 0
            for j in range(len(input_list)):
                val = input_list[j]
                index = (j+1) % len(current_pattern)
                multiplier = current_pattern[index]
                sum_of_phase += int(val) * multiplier
            output_list += str(sum_of_phase)[len(str(sum_of_phase))-1]
    return output_list[0:8]

if __name__ == "__main__":
    input = "59712692690937920492680390886862131901538154314496197364022235676243731306353384700179627460533651346711155314756853419495734284609894966089975988246871687322567664499495407183657735571812115059436153203283165299263503632551949744441033411147947509168375383038493461562836199103303184064429083384309509676574941283043596285161244885454471652448757914444304449337194545948341288172476145567753415508006250059581738670546703862905469451368454757707996318377494042589908611965335468490525108524655606907405249860972187568380476703577532080056382150009356406585677577958020969940093556279280232948278128818920216728406595068868046480073694516140765535007"
    # input = "03036732577212944063491565474664"
    # input = "02935109699940807407585447034323"
    # input = "03081770884921959731165446850517"
    # result = fft(input, 100)
    # print("Star 1:",result)

    # Star 2
    # input: 650 characters long
    
    # COMP RESULT
    def compute_output(list_a, list_b):
        val = 0
        for idx in reversed(range(len(list_a))):
            val += list_a[idx]
            list_b[idx] = val % 10
        list_a[idx:len(list_a)] = list_b[idx:len(list_b)].copy()
        return list_a
    
    input_list = [int(x) for x in list(input)]
    offset = int(input[0:7])
    total_size = len(input_list) * 10001
    assert(offset > (total_size / 2))
    
    # create long input list
    offset_in_input_list = offset % len(input_list)
    input_list_long = input_list[offset_in_input_list:].copy()
    num_lists_at_the_end = math.floor((len(input_list)*10000 - (offset+1)) / len(input_list))
    for i in range(num_lists_at_the_end):
        input_list_long.extend(input_list.copy())

    # Phase 1-100:
    tmp_input_list = np.zeros(len(input_list_long), dtype=np.int16)#list_a.copy()
    for i in range(100):
        print("Phase",i)
        input_list_long = compute_output(input_list_long, tmp_input_list)
        print(input_list_long[0:8])


    