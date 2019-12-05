#!/usr/bin/env ruby

def checkPass(pass) 
    valid = false
    # It is a six-digit number
    # => yes, all are six-digit

    # The value is within the range given in your puzzle input.
    # yes, I iterate over the range

    # Two adjacent digits are the same (like 22 in 122345).
    pass_s = pass.to_s
    if (  (pass_s[0]==pass_s[1]) ||
          (pass_s[1]==pass_s[2]) ||
          (pass_s[2]==pass_s[3]) ||
          (pass_s[3]==pass_s[4]) ||
          (pass_s[4]==pass_s[5])) then

        # Going from left to right, the digits never decrease
        if (  (pass_s[0]<=pass_s[1]) &&
              (pass_s[1]<=pass_s[2]) &&
              (pass_s[2]<=pass_s[3]) &&
              (pass_s[3]<=pass_s[4]) &&
              (pass_s[4]<=pass_s[5])) then

            # the two adjacent matching digits are not part of a larger group of matching digits
            # let's count how many times each digit is in the pass
            # and it is valid if one digit is two times in the pass
            count = [0,0,0,0,0,0,0,0,0,0]
            (0..9).each { |digit|
                (0..5).each { |pos|
                    if (pass_s[pos]==digit.to_s) then
                        count[digit] += 1
                    end
                }
            }
            # now check that one count is equal to 2
            (0..9).each { |digit|
                if (count[digit]==2) then
                    valid = true
                end
            }
        end
    end
    return valid
end

validPass = 0
(130254..678275).each { |pass|
    if (checkPass(pass)==true) then
        validPass += 1
    end
}

puts checkPass(111111)==true
puts checkPass(223450)==false
puts checkPass(123789)==false


puts validPass