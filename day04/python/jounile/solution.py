#!/usr/bin/python

def get_index_value(password, idx):
    return int(str(password)[idx])

def previous_is_same(password, idx):
    return get_index_value(password, idx) == get_index_value(password, idx-1)

def next_is_same(password, idx):
    return get_index_value(password, idx) == get_index_value(password, idx+1)

def previous_and_next_not_same(password, idx):
    return get_index_value(password, idx-1) != get_index_value(password, idx+1)

def has_double_digit(password):
    for idx in range(0, 5):
        if(next_is_same(password, idx)):
            if (idx+2 > 5 or get_index_value(password, idx+2) != get_index_value(password, idx)):
                if (idx-1 < 0 or get_index_value(password, idx-1) != get_index_value(password, idx)):
                    return True
    return False

def is_next_value_larger(password):
    index_value = get_index_value(password, 0)
    next_index_value1 = get_index_value(password, 1)
    next_index_value2 = get_index_value(password, 2)
    next_index_value3 = get_index_value(password, 3)
    next_index_value4 = get_index_value(password, 4)
    next_index_value5 = get_index_value(password, 5)
    if (next_index_value1 >= index_value
        and next_index_value2 >= next_index_value1 
        and next_index_value3 >= next_index_value2
        and next_index_value4 >= next_index_value3
        and next_index_value5 >= next_index_value4):
        return True
    return False

def passes_criteria(password):
    if is_next_value_larger(password) and has_double_digit(password):
        return True
    return False

def check_passwords(start, end):
    passwords = []
    for password in range(start, end):
        if passes_criteria(password):
            passwords.append(password)
    return passwords

start = 347312
end = 805915

print(len(check_passwords(start, end)))
# 364