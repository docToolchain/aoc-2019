import pytest
import solution

orbit_map = {
    'B': 'COM',
    'C': 'B',
    'D': 'C',
    'E': 'D',
    'F': 'E',
    'G': 'B',
    'H': 'G',
    'I': 'D',
    'J': 'E',
    'K': 'J',
    'L': 'K',
}

@pytest.mark.parametrize('orbitee, orbiters', [
    ('B',{'C', 'G'}),
    ('D', {'I', 'E'}),
    ('COM', {'B'}),
    ('E', {'J', 'F'}),
])

def test_orbit(orbitee, orbiters):
    assert solution.orbiters(orbit_map, orbitee) == orbiters
