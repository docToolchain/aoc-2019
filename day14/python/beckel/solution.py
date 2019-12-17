import numpy as np
import math

def get_ore(amount_needed, chemical, reactions, spare_parts):

    if chemical == "ORE":
        return amount_needed

    amount_produced = int(reactions[chemical][0])
    amount_reactions_needed = math.ceil(amount_needed / amount_produced)
    ueberschuss = amount_reactions_needed * amount_produced - amount_needed
    if ueberschuss > 0:
        if not chemical in spare_parts:
            spare_parts[chemical] = 0
        spare_parts[chemical] += ueberschuss
    input_required = reactions[chemical][1:]

    ore_needed = 0
    for input_reaction in input_required:
        in_amount = amount_reactions_needed * int(input_reaction.split(" ")[0])
        in_chemical = input_reaction.split(" ")[1]
        if in_chemical in spare_parts:
            if spare_parts[in_chemical] >= in_amount:
                spare_parts[in_chemical] -= in_amount
                continue
            else:
                in_amount -= spare_parts[in_chemical]
                spare_parts[in_chemical] = 0
        
        ore_needed += get_ore(in_amount, in_chemical, reactions, spare_parts)
    
    return ore_needed
    
    
with open("input.txt") as fd:
    reactions = dict()
    for line in fd:
        reaction_output = line.strip().split(" => ")[1]
        reaction_input = line.strip().split(" => ")[0].strip().split(", ")
        tmp = [int(reaction_output.split(" ")[0])]
        tmp.extend(reaction_input)
        reactions[reaction_output.split(" ")[1]] = tmp
    
    spare_parts = dict()
    ore = get_ore(1, "FUEL", reactions, spare_parts)
    
    print("Star 1:",ore)
    print("Star 2: Maximum 11788286 fuel can be produced (manual binary search :-)")
    # ore = get_ore(11788286, "FUEL", reactions, spare_parts)

