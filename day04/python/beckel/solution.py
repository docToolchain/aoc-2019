def non_decreasing(d):
    for i in range(len(d)-1):
        if (d[i] > d[i+1]):
            return False
    return True
    
def contains_double(d):
    for i in range(len(d)-1):
        if (d[i] == d[i+1]):
            return True
    return False

def contains_double_no_larger_group(d):
    # could be generalized similarly - too lazy atm :)
    if d[0] == d[1] and d[1] != d[2] \
        or d[0] != d[1] and d[1] == d[2] and d[2] != d[3] \
        or d[1] != d[2] and d[2] == d[3] and d[3] != d[4] \
        or d[2] != d[3] and d[3] == d[4] and d[4] != d[5] \
        or d[3] != d[4] and d[4] == d[5]:
        return True
    return False

num_passwords = 0
num_passwords_no_larger_group = 0
for i in range(271973,785962):
    # create integer array from integer
    d = list(map(lambda x:int(x), list(str(i))))
    if (non_decreasing(d)):
        if (contains_double(d)):
            num_passwords += 1
        if (contains_double_no_larger_group(d)):
            num_passwords_no_larger_group += 1
            
print("Num passwords:",num_passwords)
print("Num passwords without larger group:",num_passwords_no_larger_group)

