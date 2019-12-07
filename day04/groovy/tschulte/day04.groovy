// tag::firstStar[]
def isValid(String password) {
    if (password.size() != 6) {
        return false
    }
    boolean hasDuplicate = false
    Character previousChar = null
    for(int i = 0; i < password.size(); i++) {
        char c = password.charAt(i)
        hasDuplicate = hasDuplicate || c == previousChar
        if (previousChar && previousChar > c) {
            return false
        }
        previousChar = c
    }
    return hasDuplicate
}

assert !isValid("")
assert !isValid("111110")
assert isValid("011111")
assert isValid("111111")
assert isValid("112345")

println((245318..765747).findAll { isValid("$it") }.size())
// end::firstStar[]

// tag::secondStar[]
def isValid2(String password) {
    if (password.size() != 6) {
        return false
    }
    def duplicates = [:].withDefault { 1 }
    Character previousChar = null
    for(int i = 0; i < password.size(); i++) {
        char c = password.charAt(i)
        if (c == previousChar) {
            duplicates[c] = duplicates[c] + 1
        }
        if (previousChar && previousChar > c) {
            return false
        }
        previousChar = c
    }
    def duplicateCounts = duplicates*.value
    return !duplicateCounts.empty && !duplicateCounts.findAll { it == 2 }.empty
}


assert isValid2("112233")
assert !isValid2("123444")
assert isValid2("111122")
assert isValid2("111233")

println((245318..765747).findAll { isValid2("$it") }.size())
// end::secondStar[]