import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class NanofactoryTest {
  private final Map<String, Long> chemicals = new HashMap<>();
  private Map<String, Reaction> reactions = new HashMap<>();

  private static Stream<Arguments> part2Source() {
    return Stream.of(Arguments.of("""
                         3 LMPDB, 11 CBTKP => 7 PZDPS
                         5 CBKW, 4 CXBH => 9 KXNDF
                         1 LVDN, 4 HGDHV => 1 PCXS
                         11 PCXS => 2 XTBRS
                         5 RVSF => 7 TDCH
                         1 CXBH, 6 PXVN => 8 GQXV
                         3 DBCB, 3 QLNK => 4 CTFCD
                         7 PZDPS, 18 HGDHV, 9 TBKM => 4 JHVL
                         10 QGSV, 1 DBCB, 7 LTHFX => 3 BLRSQ
                         12 CBTKP, 7 SPBF => 5 KSQL
                         1 QXHDQ, 5 MQKH, 10 XRCB, 30 SQWHX, 2 PQZVD, 30 TFST, 39 JPFC, 1 FDGS, 17 LVDN => 1 FUEL
                         2 TBKM => 8 PFHKT
                         13 CBTKP => 5 QLNK
                         12 TVRDM, 6 QGSV, 16 LMPDB => 4 PQZVD
                         7 TDCH, 17 PXVN, 4 ZLKZ => 6 XRCB
                         1 QBJQ, 26 CBKW => 4 RVSF
                         24 KXNDF, 3 BLRSQ => 9 GSHKQ
                         12 BLRSQ, 3 HGDHV => 9 RQNGQ
                         2 RFBK, 2 WHWS => 8 CBKW
                         1 WHWS => 7 LTHFX
                         13 CKQLD, 10 ZLKZ, 2 GQXV => 8 TVHC
                         1 DBCB => 2 JZXKW
                         8 SPBF => 7 CXBH
                         11 LTHFX, 1 PTGLG, 10 NCQTM => 6 SQWHX
                         16 PFHKT => 3 HGDHV
                         3 LVDN, 5 PZDPS, 1 SPBF => 9 CQBCL
                         19 BLRSQ, 1 BLQRD, 5 GSHKQ, 2 LVDN, 3 LMPDB, 5 KTJR => 1 QXHDQ
                         1 RFBK, 1 JPFC => 7 PXVN
                         110 ORE => 3 MQKH
                         1 FPBRB, 7 MQKH => 7 SDJBT
                         128 ORE => 7 FPBRB
                         3 WRWGP => 2 RFBK
                         1 PFHKT, 4 SPBF => 7 JPFC
                         14 LTHFX, 2 JZXKW, 2 BLRSQ, 2 MHVJP, 6 RQNGQ, 1 CQBCL, 8 TDCH, 2 NJTR => 2 FDGS
                         4 SDJBT, 2 LMPDB => 8 PLGS
                         1 RFBK, 1 TBKM => 6 CBTKP
                         17 LVDN, 2 CBTKP => 4 QGSV
                         7 WRWGP => 9 LMPDB
                         3 CKQLD => 6 WHWS
                         14 CBTKP, 9 XTBRS, 9 GSHKQ, 12 GQXV, 20 LTHFX, 1 RQNGQ, 1 KTJR, 3 BLRSQ => 7 TFST
                         1 QPCQ => 5 BLQRD
                         6 QGSV, 1 HGDHV, 1 JPFC => 1 NJTR
                         1 HGDHV, 7 JHVL, 5 PZDPS => 9 MGRT
                         1 KSQL => 5 QBJQ
                         2 QLNK => 2 CKQLD
                         13 JZXKW, 14 XTBRS => 3 PTGLG
                         1 BNPXT, 2 PLGS => 7 DBCB
                         1 RFBK, 9 CTFCD => 1 MHVJP
                         1 NJTR, 1 TVHC, 2 PCXS => 1 KTJR
                         2 WRWGP => 6 TBKM
                         12 QLNK, 1 NJTR => 3 NCQTM
                         13 ZHCKP, 2 DBCB, 5 PXVN => 9 QPCQ
                         125 ORE => 3 WRWGP
                         6 CBTKP, 9 TBKM => 9 SPBF
                         1 GQXV => 6 ZHCKP
                         1 MGRT => 8 BNPXT
                         2 SPBF => 4 ZLKZ
                         9 TVHC, 5 KXNDF, 3 QPCQ => 3 TVRDM
                         1 PLGS, 7 TBKM => 8 LVDN
                         """,97422)                     );
  }
  private static Stream<Arguments> part1Source() {
    return Stream.of(
        Arguments.of("""
        10 ORE => 10 A
        1 ORE => 1 B
        7 A, 1 B => 1 C
        7 A, 1 C => 1 D
        7 A, 1 D => 1 E
        7 A, 1 E => 1 FUEL
        """,31),
        Arguments.of("""
                    9 ORE => 2 A
                    8 ORE => 3 B
                    7 ORE => 5 C
                    3 A, 4 B => 1 AB
                    5 B, 7 C => 1 BC
                    4 C, 1 A => 1 CA
                    2 AB, 3 BC, 4 CA => 1 FUEL
                         """,165),
        Arguments.of("""
                         157 ORE => 5 NZVS
                         165 ORE => 6 DCFZ
                         44 XJWVT, 5 KHKGT, 1 QDVJ, 29 NZVS, 9 GPVTF, 48 HKGWZ => 1 FUEL
                         12 HKGWZ, 1 GPVTF, 8 PSHF => 9 QDVJ
                         179 ORE => 7 PSHF
                         177 ORE => 5 HKGWZ
                         7 DCFZ, 7 PSHF => 2 XJWVT
                         165 ORE => 2 GPVTF
                         3 DCFZ, 7 NZVS, 5 HKGWZ, 10 PSHF => 8 KHKGT
                         """,13312),
        Arguments.of("""
                         2 VPVL, 7 FWMGM, 2 CXFTF, 11 MNCFX => 1 STKFG
                         17 NVRVD, 3 JNWZP => 8 VPVL
                         53 STKFG, 6 MNCFX, 46 VJHF, 81 HVMC, 68 CXFTF, 25 GNMV => 1 FUEL
                         22 VJHF, 37 MNCFX => 5 FWMGM
                         139 ORE => 4 NVRVD
                         144 ORE => 7 JNWZP
                         5 MNCFX, 7 RFSQX, 2 FWMGM, 2 VPVL, 19 CXFTF => 3 HVMC
                         5 VJHF, 7 MNCFX, 9 VPVL, 37 CXFTF => 6 GNMV
                         145 ORE => 6 MNCFX
                         1 NVRVD => 8 CXFTF
                         1 VJHF, 6 MNCFX => 4 RFSQX
                         176 ORE => 6 VJHF
                         """,180697 ),
        Arguments.of("""
                         171 ORE => 8 CNZTR
                         7 ZLQW, 3 BMBT, 9 XCVML, 26 XMNCP, 1 WPTQ, 2 MZWV, 1 RJRHP => 4 PLWSL
                         114 ORE => 4 BHXH
                         14 VRPVC => 6 BMBT
                         6 BHXH, 18 KTJDG, 12 WPTQ, 7 PLWSL, 31 FHTLT, 37 ZDVW => 1 FUEL
                         6 WPTQ, 2 BMBT, 8 ZLQW, 18 KTJDG, 1 XMNCP, 6 MZWV, 1 RJRHP => 6 FHTLT
                         15 XDBXC, 2 LTCX, 1 VRPVC => 6 ZLQW
                         13 WPTQ, 10 LTCX, 3 RJRHP, 14 XMNCP, 2 MZWV, 1 ZLQW => 1 ZDVW
                         5 BMBT => 4 WPTQ
                         189 ORE => 9 KTJDG
                         1 MZWV, 17 XDBXC, 3 XCVML => 2 XMNCP
                         12 VRPVC, 27 CNZTR => 2 XDBXC
                         15 KTJDG, 12 BHXH => 5 XCVML
                         3 BHXH, 2 VRPVC => 7 MZWV
                         121 ORE => 7 VRPVC
                         7 XCVML => 6 RJRHP
                         5 BHXH, 4 VRPVC => 5 LTCX
                         """,2210736 ),
        Arguments.of("""
                         3 LMPDB, 11 CBTKP => 7 PZDPS
                         5 CBKW, 4 CXBH => 9 KXNDF
                         1 LVDN, 4 HGDHV => 1 PCXS
                         11 PCXS => 2 XTBRS
                         5 RVSF => 7 TDCH
                         1 CXBH, 6 PXVN => 8 GQXV
                         3 DBCB, 3 QLNK => 4 CTFCD
                         7 PZDPS, 18 HGDHV, 9 TBKM => 4 JHVL
                         10 QGSV, 1 DBCB, 7 LTHFX => 3 BLRSQ
                         12 CBTKP, 7 SPBF => 5 KSQL
                         1 QXHDQ, 5 MQKH, 10 XRCB, 30 SQWHX, 2 PQZVD, 30 TFST, 39 JPFC, 1 FDGS, 17 LVDN => 1 FUEL
                         2 TBKM => 8 PFHKT
                         13 CBTKP => 5 QLNK
                         12 TVRDM, 6 QGSV, 16 LMPDB => 4 PQZVD
                         7 TDCH, 17 PXVN, 4 ZLKZ => 6 XRCB
                         1 QBJQ, 26 CBKW => 4 RVSF
                         24 KXNDF, 3 BLRSQ => 9 GSHKQ
                         12 BLRSQ, 3 HGDHV => 9 RQNGQ
                         2 RFBK, 2 WHWS => 8 CBKW
                         1 WHWS => 7 LTHFX
                         13 CKQLD, 10 ZLKZ, 2 GQXV => 8 TVHC
                         1 DBCB => 2 JZXKW
                         8 SPBF => 7 CXBH
                         11 LTHFX, 1 PTGLG, 10 NCQTM => 6 SQWHX
                         16 PFHKT => 3 HGDHV
                         3 LVDN, 5 PZDPS, 1 SPBF => 9 CQBCL
                         19 BLRSQ, 1 BLQRD, 5 GSHKQ, 2 LVDN, 3 LMPDB, 5 KTJR => 1 QXHDQ
                         1 RFBK, 1 JPFC => 7 PXVN
                         110 ORE => 3 MQKH
                         1 FPBRB, 7 MQKH => 7 SDJBT
                         128 ORE => 7 FPBRB
                         3 WRWGP => 2 RFBK
                         1 PFHKT, 4 SPBF => 7 JPFC
                         14 LTHFX, 2 JZXKW, 2 BLRSQ, 2 MHVJP, 6 RQNGQ, 1 CQBCL, 8 TDCH, 2 NJTR => 2 FDGS
                         4 SDJBT, 2 LMPDB => 8 PLGS
                         1 RFBK, 1 TBKM => 6 CBTKP
                         17 LVDN, 2 CBTKP => 4 QGSV
                         7 WRWGP => 9 LMPDB
                         3 CKQLD => 6 WHWS
                         14 CBTKP, 9 XTBRS, 9 GSHKQ, 12 GQXV, 20 LTHFX, 1 RQNGQ, 1 KTJR, 3 BLRSQ => 7 TFST
                         1 QPCQ => 5 BLQRD
                         6 QGSV, 1 HGDHV, 1 JPFC => 1 NJTR
                         1 HGDHV, 7 JHVL, 5 PZDPS => 9 MGRT
                         1 KSQL => 5 QBJQ
                         2 QLNK => 2 CKQLD
                         13 JZXKW, 14 XTBRS => 3 PTGLG
                         1 BNPXT, 2 PLGS => 7 DBCB
                         1 RFBK, 9 CTFCD => 1 MHVJP
                         1 NJTR, 1 TVHC, 2 PCXS => 1 KTJR
                         2 WRWGP => 6 TBKM
                         12 QLNK, 1 NJTR => 3 NCQTM
                         13 ZHCKP, 2 DBCB, 5 PXVN => 9 QPCQ
                         125 ORE => 3 WRWGP
                         6 CBTKP, 9 TBKM => 9 SPBF
                         1 GQXV => 6 ZHCKP
                         1 MGRT => 8 BNPXT
                         2 SPBF => 4 ZLKZ
                         9 TVHC, 5 KXNDF, 3 QPCQ => 3 TVRDM
                         1 PLGS, 7 TBKM => 8 LVDN
                         """,97422)
    );
  }

  @BeforeEach
  void cleanpu() {
    chemicals.clear();
    reactions.clear();
  }

  @DisplayName("What is the minimum amount of ORE required to produce exactly 1 FUEL?")
  @ParameterizedTest(name = "{index}. example")
  @MethodSource("part1Source")
  void day14Part1(String reactionList, int neededOre) {
    for (String line : reactionList.split("\n")) {
      final Reaction reaction = new Reaction(line);
      reactions.put(reaction.output.name, reaction);
    }

    final var initialOre = 1_000_000_000_000L;
    chemicals.put("ORE", initialOre);

    final Reaction reaction = reactions.get("FUEL");
    prepareEnoughMaterialFor(reaction);
    reaction.execute(chemicals);

    var ore = chemicals.get("ORE");
    assertEquals(neededOre, initialOre - ore);
  }

  @DisplayName("Given 1 trillion ORE, what is the maximum amount of FUEL you can produce?")
  @ParameterizedTest(name = "{index}. example")
  @MethodSource("part2Source")
  void day14Part2(String reactionList, int expectedFuel) {
    for (String line : reactionList.split("\n")) {
      final Reaction reaction = new Reaction(line);
      reactions.put(reaction.output.name, reaction);
    }

    final var initialOre = 1_000_000_000_000L;
    chemicals.put("ORE", initialOre);

    var producedFuel = 0L;
    while (true) {
      try {
        final Reaction reaction = reactions.get("FUEL");
        prepareEnoughMaterialFor(reaction);
        reaction.execute(chemicals);
        producedFuel++;
        if(producedFuel % 10_000 == 0) {
          System.out.println(producedFuel);
          var ore = chemicals.get("ORE");
          final var usedOre = initialOre - ore;
          final var orePerFuel = (double) usedOre / producedFuel;
          var estimatedProducableFuel = initialOre/orePerFuel;
          System.out.println(format("ORE left %d, used %d, est. fuel %d", ore, usedOre, (long)estimatedProducableFuel));
        }

      } catch (Exception e) {
        break;
      }
    }

    assertEquals(expectedFuel, producedFuel);
  }

  private void prepareEnoughMaterialFor(Reaction reaction) {
    final Map<String, Long> reservedChemicals = new HashMap<>();

    for (Chemical chemical : reaction.inputs) {
      var availableAmount = chemicals.get(chemical.name);

      // produce enough of the needed material (or use ORE)
      while(availableAmount == null || availableAmount < chemical.amount) {
        var reactionForChemical = reactions.get(chemical.name);
        prepareEnoughMaterialFor(reactionForChemical);
        reactionForChemical.execute(chemicals);
        availableAmount = chemicals.get(chemical.name);
      }

      // reserve neede material
      reservedChemicals.put(chemical.name, chemical.amount);
      chemicals.put(chemical.name, availableAmount - chemical.amount);
    }

    // put back reserved material
    for (var reservedChemical : reservedChemicals.entrySet()) {
      final var availableAmount = chemicals.get(reservedChemical.getKey());
      chemicals.put(reservedChemical.getKey(), availableAmount + reservedChemical.getValue());
    }

  }

  private static class Chemical {
    private final String name;
    private final long amount;

    public Chemical(String description) {
      final String[] parts = description.trim().split(" ");
      name = parts[1];
      amount = Integer.valueOf(parts[0].trim());
    }

    @Override
    public String toString() {
      return format("%d %s", amount, name);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      final Chemical chemical = (Chemical) o;
      return amount == chemical.amount && Objects.equals(name, chemical.name);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name, amount);
    }
  }

  private static class Reaction {
    private final Chemical       output;
    private final List<Chemical> inputs = new ArrayList<>();

    private Reaction(String description) {
      final String[] io = description.trim().split("=>");
      this.output = new Chemical(io[1]);
      final String[] splitted = io[0].split(",");
      for (String input : splitted) {
        inputs.add(new Chemical(input));
      }
    }

    public void execute(Map<String, Long> chemicals) {
      // reduce chemicals needed by input
      for (Chemical input : inputs) {
        var availableAmount = chemicals.get(input.name);
        if(availableAmount == null) {
          throw new IllegalStateException("No " + input.name + " available.");
        }
        if(availableAmount < input.amount) {
          throw new IllegalStateException(
              format("Not enough %s available. Needed %d, available %d", input.name, input.amount, availableAmount));
        }
        chemicals.put(input.name, availableAmount - input.amount);
      }


      // add output
      chemicals.merge(output.name, output.amount, Long::sum);
    }

    @Override
    public String toString() {
      return inputs + " => " + output;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      final Reaction reaction = (Reaction) o;
      return Objects.equals(output, reaction.output) && Objects.equals(inputs, reaction.inputs);
    }

    @Override
    public int hashCode() {
      return Objects.hash(output, inputs);
    }
  }
}
