import collections

class NyotaUhura:

    def valid_no_decrease(self, start, end):
        validcodes = []
        for code in range(start, end):
            code = str(code)
            decrease = False
            for pos in range(0, len(code)-1):
                value1 = int(code[pos])
                value2 = int(code[pos+1])
                calc = value2 - value1
                if calc < 0:
                    decrease = True
            if not decrease:
                validcodes.append(code)
        return validcodes
        
    def valid_same(self, codes):
        validcodes = []
        for code in codes:
            same = False
            for pos in range(0, len(code)-1):
                value1 = int(code[pos])
                value2 = int(code[pos+1])
                calc = value2 - value1
                if calc == 0:
                    same = True
            if same:
                validcodes.append(code)
        return validcodes

    def valid_larger_group(self, codes):
        validcodes = []
        for code in codes:
            c=collections.Counter(code)
            large = False
            for count in c.values():
                count = int(count)
                if count == 2:
                    large = True
            if large:
                validcodes.append(code)
        return validcodes