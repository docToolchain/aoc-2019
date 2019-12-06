def get_depth(obj, orbit_mapping):
    if obj == "COM":
        return 0
    else:
        new_obj = orbit_mapping[obj]
        return 1 + get_depth(new_obj, orbit_mapping)


with open("./input.txt", "r") as fd:
    # store all orbit relations in ditionary
    orbit_mapping = {}
    for line in fd:
        left = line.strip().split(')')[0]
        right = line.strip().split(')')[1]
        orbit_mapping[right] = left

    # traverse and count links from each object to "COM"
    num = 0
    for obj in orbit_mapping:
        num += get_depth(obj, orbit_mapping)
    print("Number of direct and indirect orbits:",num)

    # Star 2: identify common objects which must NOT be travelled through
    you_object = "YOU"
    santa_object = "SAN"
    you_chain = []
    santa_chain = []
    while(you_object != "COM"):
        you_chain.append(orbit_mapping[you_object])
        you_object = orbit_mapping[you_object]
    while(santa_object != "COM"):
        santa_chain.append(orbit_mapping[santa_object])
        santa_object = orbit_mapping[santa_object]

    common_objects = set(you_chain) ^ set(santa_chain)
    print("Travel distance:",len(common_objects))
