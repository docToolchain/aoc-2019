<?xml version="1.0" encoding="UTF-8"?>
<model ref="r:174ae00a-0c93-4998-993a-7ef0a3587db2(Intcode.sandbox)">
  <persistence version="9" />
  <languages>
    <use id="373e206f-d13b-4429-85dd-fc79dfc34880" name="Intcode" version="0" />
  </languages>
  <imports />
  <registry>
    <language id="373e206f-d13b-4429-85dd-fc79dfc34880" name="Intcode">
      <concept id="3501516457700166619" name="Intcode.structure.IntcodeProgram" flags="ng" index="2GWe_L">
        <property id="3501516457700622133" name="inputString" index="2GYxQv" />
        <child id="3501516457700170065" name="memory" index="2GWfJV" />
      </concept>
      <concept id="3501516457700170032" name="Intcode.structure.MemoryPosition" flags="ng" index="2GWfIq">
        <property id="3501516457700170033" name="content" index="2GWfIr" />
      </concept>
    </language>
    <language id="ceab5195-25ea-4f22-9b92-103b95ca8c0c" name="jetbrains.mps.lang.core">
      <concept id="1169194658468" name="jetbrains.mps.lang.core.structure.INamedConcept" flags="ng" index="TrEIO">
        <property id="1169194664001" name="name" index="TrG5h" />
      </concept>
    </language>
  </registry>
  <node concept="2GWe_L" id="32nSEEspL$J">
    <property role="TrG5h" value="Example" />
    <property role="2GYxQv" value="1,9,10,3,2,3,11,0,99,30,40,50" />
    <node concept="2GWfIq" id="fQbx7c7mH6" role="2GWfJV">
      <property role="2GWfIr" value="1" />
    </node>
    <node concept="2GWfIq" id="fQbx7c7mH7" role="2GWfJV">
      <property role="2GWfIr" value="9" />
    </node>
    <node concept="2GWfIq" id="fQbx7c7mH8" role="2GWfJV">
      <property role="2GWfIr" value="10" />
    </node>
    <node concept="2GWfIq" id="fQbx7c7mH9" role="2GWfJV">
      <property role="2GWfIr" value="3" />
    </node>
    <node concept="2GWfIq" id="fQbx7c7mHa" role="2GWfJV">
      <property role="2GWfIr" value="2" />
    </node>
    <node concept="2GWfIq" id="fQbx7c7mHb" role="2GWfJV">
      <property role="2GWfIr" value="3" />
    </node>
    <node concept="2GWfIq" id="fQbx7c7mHc" role="2GWfJV">
      <property role="2GWfIr" value="11" />
    </node>
    <node concept="2GWfIq" id="fQbx7c7mHd" role="2GWfJV">
      <property role="2GWfIr" value="0" />
    </node>
    <node concept="2GWfIq" id="fQbx7c7mHe" role="2GWfJV">
      <property role="2GWfIr" value="99" />
    </node>
    <node concept="2GWfIq" id="fQbx7c7mHf" role="2GWfJV">
      <property role="2GWfIr" value="30" />
    </node>
    <node concept="2GWfIq" id="fQbx7c7mHg" role="2GWfJV">
      <property role="2GWfIr" value="40" />
    </node>
    <node concept="2GWfIq" id="fQbx7c7mHh" role="2GWfJV">
      <property role="2GWfIr" value="50" />
    </node>
  </node>
  <node concept="2GWe_L" id="32nSEEsr4mE">
    <property role="TrG5h" value="Example 2" />
    <property role="2GYxQv" value="1,0,0,0,99" />
    <node concept="2GWfIq" id="32nSEEsrTYv" role="2GWfJV">
      <property role="2GWfIr" value="2" />
    </node>
    <node concept="2GWfIq" id="32nSEEsrTYw" role="2GWfJV">
      <property role="2GWfIr" value="0" />
    </node>
    <node concept="2GWfIq" id="32nSEEsrTYx" role="2GWfJV">
      <property role="2GWfIr" value="0" />
    </node>
    <node concept="2GWfIq" id="32nSEEsrTYy" role="2GWfJV">
      <property role="2GWfIr" value="0" />
    </node>
    <node concept="2GWfIq" id="32nSEEsrTYz" role="2GWfJV">
      <property role="2GWfIr" value="99" />
    </node>
  </node>
  <node concept="2GWe_L" id="32nSEEsrTYJ">
    <property role="TrG5h" value="Example 3" />
    <property role="2GYxQv" value="2,3,0,3,99" />
    <node concept="2GWfIq" id="32nSEEsrTYQ" role="2GWfJV">
      <property role="2GWfIr" value="2" />
    </node>
    <node concept="2GWfIq" id="32nSEEsrTYR" role="2GWfJV">
      <property role="2GWfIr" value="3" />
    </node>
    <node concept="2GWfIq" id="32nSEEsrTYS" role="2GWfJV">
      <property role="2GWfIr" value="0" />
    </node>
    <node concept="2GWfIq" id="32nSEEsrTYT" role="2GWfJV">
      <property role="2GWfIr" value="6" />
    </node>
    <node concept="2GWfIq" id="32nSEEsrTYU" role="2GWfJV">
      <property role="2GWfIr" value="99" />
    </node>
  </node>
  <node concept="2GWe_L" id="32nSEEsrTZ6">
    <property role="TrG5h" value="Example 4" />
    <property role="2GYxQv" value="2,4,4,5,99,0" />
    <node concept="2GWfIq" id="32nSEEsrTZ7" role="2GWfJV">
      <property role="2GWfIr" value="2" />
    </node>
    <node concept="2GWfIq" id="32nSEEsrTZ8" role="2GWfJV">
      <property role="2GWfIr" value="4" />
    </node>
    <node concept="2GWfIq" id="32nSEEsrTZ9" role="2GWfJV">
      <property role="2GWfIr" value="4" />
    </node>
    <node concept="2GWfIq" id="32nSEEsrTZa" role="2GWfJV">
      <property role="2GWfIr" value="5" />
    </node>
    <node concept="2GWfIq" id="32nSEEsrTZb" role="2GWfJV">
      <property role="2GWfIr" value="99" />
    </node>
    <node concept="2GWfIq" id="32nSEEsrTZc" role="2GWfJV">
      <property role="2GWfIr" value="9801" />
    </node>
  </node>
</model>

