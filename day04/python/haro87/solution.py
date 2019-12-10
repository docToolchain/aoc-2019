import random
from enterprise import NyotaUhura as Uhura

uhura = Uhura()

START = 240920
END = 789857

codes = uhura.valid_no_decrease(START, END)
codes = uhura.valid_same(codes)

print('Part one, Uhura says: {0}'.format(len(codes)))

codes = uhura.valid_larger_group(codes)

print('Part two, Uhura says: {0}'.format(len(codes)))