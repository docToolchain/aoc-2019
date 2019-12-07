import re

def check_rules(x, check_star2):
    s = str(x)
    def monotonous(s):
        for i in range(0, len(s)-1):
            if s[i] > s[i+1]:
                return False
        return True

    def has_twin(s):
        return 2 in count_series(s)
    
    def noncanonical_series(s):
        return count_series(s) - {1} 

    def count_series(x):
        return set([ len(x.groups()[0]) for x in re.finditer(r'((.)\2*)',x) ])
        
    return monotonous(s) and noncanonical_series(s) and (not check_star2 or has_twin(s)) 
        
def check_range(begin, end, star2):
    return [ x for x in range(begin, end) if check_rules(x, star2) ]

star1 = check_range(193651, 649729, False)
print("Result first star:  {}".format(len(star1)))

star2 = check_range(193651, 649729, True)
print("Result second star: {}".format(len(star2)))