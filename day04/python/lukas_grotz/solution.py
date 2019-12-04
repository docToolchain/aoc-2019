lower_bound = '236491'
upper_bound = '713787'


# first formulate password rules
def test_increase(password: str) -> bool:
    return password == ''.join(sorted(password))

def test_length(password: str) -> bool:
    return len(password) == 6

def test_range(password: str) -> bool:
    return int(password) in range(int(lower_bound), int(upper_bound)+1)

def test_adjacency(password: str) -> bool:
    pw_list = list(password)
    last_digit = password[0]
    matches = {}
    digit_counter = 1
    for digit in pw_list[1:len(pw_list)]:
        if digit == last_digit:
            digit_counter += 1
            matches[digit] = digit_counter
        else:
            digit_counter = 1
        last_digit = digit
        
    if len(matches) == 0:
        return False
    result = any(value == 2 for value in matches.values())
    return result

if __name__ == '__main__':
    possible_combinations = [str(i).zfill(6) for i in range(int(lower_bound), int(upper_bound)+1)]

    solution_sorted = list(filter(test_increase, possible_combinations))
    solution_adjacent = list(filter(test_adjacency, solution_sorted))

    print(len(solution_adjacent))