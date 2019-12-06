<?xml version="1.0" encoding="UTF-8"?>
<model ref="r:c59fa5b7-130e-45f2-a476-3ba334523617(Day03.structure)">
  <persistence version="9" />
  <languages>
    <use id="c72da2b9-7cce-4447-8389-f407dc1158b7" name="jetbrains.mps.lang.structure" version="7" />
    <devkit ref="78434eb8-b0e5-444b-850d-e7c4ad2da9ab(jetbrains.mps.devkit.aspect.structure)" />
  </languages>
  <imports>
    <import index="tpck" ref="r:00000000-0000-4000-0000-011c89590288(jetbrains.mps.lang.core.structure)" implicit="true" />
  </imports>
  <registry>
    <language id="c72da2b9-7cce-4447-8389-f407dc1158b7" name="jetbrains.mps.lang.structure">
      <concept id="1169125787135" name="jetbrains.mps.lang.structure.structure.AbstractConceptDeclaration" flags="ig" index="PkWjJ">
        <property id="6714410169261853888" name="conceptId" index="EcuMT" />
        <property id="4628067390765907488" name="conceptShortDescription" index="R4oN_" />
        <property id="5092175715804935370" name="conceptAlias" index="34LRSv" />
        <child id="1071489727083" name="linkDeclaration" index="1TKVEi" />
        <child id="1071489727084" name="propertyDeclaration" index="1TKVEl" />
      </concept>
      <concept id="1169125989551" name="jetbrains.mps.lang.structure.structure.InterfaceConceptDeclaration" flags="ig" index="PlHQZ" />
      <concept id="1169127622168" name="jetbrains.mps.lang.structure.structure.InterfaceConceptReference" flags="ig" index="PrWs8">
        <reference id="1169127628841" name="intfc" index="PrY4T" />
      </concept>
      <concept id="1071489090640" name="jetbrains.mps.lang.structure.structure.ConceptDeclaration" flags="ig" index="1TIwiD">
        <property id="1096454100552" name="rootable" index="19KtqR" />
        <reference id="1071489389519" name="extends" index="1TJDcQ" />
        <child id="1169129564478" name="implements" index="PzmwI" />
      </concept>
      <concept id="1071489288299" name="jetbrains.mps.lang.structure.structure.PropertyDeclaration" flags="ig" index="1TJgyi">
        <property id="241647608299431129" name="propertyId" index="IQ2nx" />
        <reference id="1082985295845" name="dataType" index="AX2Wp" />
      </concept>
      <concept id="1071489288298" name="jetbrains.mps.lang.structure.structure.LinkDeclaration" flags="ig" index="1TJgyj">
        <property id="1071599776563" name="role" index="20kJfa" />
        <property id="1071599893252" name="sourceCardinality" index="20lbJX" />
        <property id="1071599937831" name="metaClass" index="20lmBu" />
        <property id="241647608299431140" name="linkId" index="IQ2ns" />
        <reference id="1071599976176" name="target" index="20lvS9" />
      </concept>
    </language>
    <language id="ceab5195-25ea-4f22-9b92-103b95ca8c0c" name="jetbrains.mps.lang.core">
      <concept id="1169194658468" name="jetbrains.mps.lang.core.structure.INamedConcept" flags="ng" index="TrEIO">
        <property id="1169194664001" name="name" index="TrG5h" />
      </concept>
    </language>
  </registry>
  <node concept="1TIwiD" id="fQbx7c7mKt">
    <property role="EcuMT" value="285466280358407197" />
    <property role="TrG5h" value="Grid" />
    <property role="19KtqR" value="true" />
    <property role="34LRSv" value="grid" />
    <property role="R4oN_" value="Grid" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="1TJgyi" id="fQbx7c87jb" role="1TKVEl">
      <property role="IQ2nx" value="285466280358606027" />
      <property role="TrG5h" value="path1String" />
      <ref role="AX2Wp" to="tpck:fKAOsGN" resolve="string" />
    </node>
    <node concept="1TJgyi" id="fQbx7c87jf" role="1TKVEl">
      <property role="IQ2nx" value="285466280358606031" />
      <property role="TrG5h" value="path2String" />
      <ref role="AX2Wp" to="tpck:fKAOsGN" resolve="string" />
    </node>
    <node concept="1TJgyi" id="fQbx7cbjNY" role="1TKVEl">
      <property role="IQ2nx" value="285466280359443710" />
      <property role="TrG5h" value="closestIntersectionPointDistance" />
      <ref role="AX2Wp" to="tpck:fKAQMTA" resolve="integer" />
    </node>
    <node concept="1TJgyj" id="fQbx7c7qg8" role="1TKVEi">
      <property role="IQ2ns" value="285466280358421512" />
      <property role="20lmBu" value="aggregation" />
      <property role="20kJfa" value="path1" />
      <property role="20lbJX" value="0..n" />
      <ref role="20lvS9" node="fQbx7c7oP8" resolve="IDirection" />
    </node>
    <node concept="1TJgyj" id="fQbx7c7qga" role="1TKVEi">
      <property role="IQ2ns" value="285466280358421514" />
      <property role="20lmBu" value="aggregation" />
      <property role="20kJfa" value="path2" />
      <property role="20lbJX" value="0..n" />
      <ref role="20lvS9" node="fQbx7c7oP8" resolve="IDirection" />
    </node>
    <node concept="1TJgyj" id="fQbx7c9BzW" role="1TKVEi">
      <property role="IQ2ns" value="285466280359000316" />
      <property role="20lmBu" value="aggregation" />
      <property role="20kJfa" value="path1Coordinates" />
      <property role="20lbJX" value="0..n" />
      <ref role="20lvS9" node="fQbx7c9y6Z" resolve="Coordinate" />
    </node>
    <node concept="1TJgyj" id="fQbx7c9B$6" role="1TKVEi">
      <property role="IQ2ns" value="285466280359000326" />
      <property role="20lmBu" value="aggregation" />
      <property role="20kJfa" value="path2Coordinates" />
      <property role="20lbJX" value="0..n" />
      <ref role="20lvS9" node="fQbx7c9y6Z" resolve="Coordinate" />
    </node>
    <node concept="1TJgyj" id="fQbx7caSls" role="1TKVEi">
      <property role="IQ2ns" value="285466280359331164" />
      <property role="20lmBu" value="aggregation" />
      <property role="20kJfa" value="intersectionPoints" />
      <property role="20lbJX" value="0..n" />
      <ref role="20lvS9" node="fQbx7c9y6Z" resolve="Coordinate" />
    </node>
    <node concept="PrWs8" id="fQbx7c7mKu" role="PzmwI">
      <ref role="PrY4T" to="tpck:h0TrEE$" resolve="INamedConcept" />
    </node>
  </node>
  <node concept="PlHQZ" id="fQbx7c7oP8">
    <property role="EcuMT" value="285466280358415688" />
    <property role="TrG5h" value="IDirection" />
    <node concept="1TJgyi" id="fQbx7c7oP9" role="1TKVEl">
      <property role="IQ2nx" value="285466280358415689" />
      <property role="TrG5h" value="steps" />
      <ref role="AX2Wp" to="tpck:fKAQMTA" resolve="integer" />
    </node>
  </node>
  <node concept="1TIwiD" id="fQbx7c7qfW">
    <property role="EcuMT" value="285466280358421500" />
    <property role="TrG5h" value="Up" />
    <property role="34LRSv" value="U" />
    <property role="R4oN_" value="up" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="PrWs8" id="fQbx7c7qfX" role="PzmwI">
      <ref role="PrY4T" node="fQbx7c7oP8" resolve="IDirection" />
    </node>
  </node>
  <node concept="1TIwiD" id="fQbx7c7qfZ">
    <property role="EcuMT" value="285466280358421503" />
    <property role="TrG5h" value="Down" />
    <property role="34LRSv" value="D" />
    <property role="R4oN_" value="down" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="PrWs8" id="fQbx7c7qg0" role="PzmwI">
      <ref role="PrY4T" node="fQbx7c7oP8" resolve="IDirection" />
    </node>
  </node>
  <node concept="1TIwiD" id="fQbx7c7qg2">
    <property role="EcuMT" value="285466280358421506" />
    <property role="TrG5h" value="Left" />
    <property role="34LRSv" value="L" />
    <property role="R4oN_" value="left" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="PrWs8" id="fQbx7c7qg3" role="PzmwI">
      <ref role="PrY4T" node="fQbx7c7oP8" resolve="IDirection" />
    </node>
  </node>
  <node concept="1TIwiD" id="fQbx7c7qg5">
    <property role="EcuMT" value="285466280358421509" />
    <property role="TrG5h" value="Right" />
    <property role="34LRSv" value="R" />
    <property role="R4oN_" value="right" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="PrWs8" id="fQbx7c7qg6" role="PzmwI">
      <ref role="PrY4T" node="fQbx7c7oP8" resolve="IDirection" />
    </node>
  </node>
  <node concept="1TIwiD" id="fQbx7c80lB">
    <property role="EcuMT" value="285466280358577511" />
    <property role="TrG5h" value="EmptyDirection" />
    <property role="34LRSv" value="empty" />
    <property role="R4oN_" value="Empty Direction" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="PrWs8" id="fQbx7c80lC" role="PzmwI">
      <ref role="PrY4T" node="fQbx7c7oP8" resolve="IDirection" />
    </node>
  </node>
  <node concept="1TIwiD" id="fQbx7c9y6Z">
    <property role="EcuMT" value="285466280358977983" />
    <property role="TrG5h" value="Coordinate" />
    <property role="34LRSv" value="(x,y)" />
    <property role="R4oN_" value="coordinate" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="1TJgyi" id="fQbx7c9y70" role="1TKVEl">
      <property role="IQ2nx" value="285466280358977984" />
      <property role="TrG5h" value="x" />
      <ref role="AX2Wp" to="tpck:fKAQMTA" resolve="integer" />
    </node>
    <node concept="1TJgyi" id="fQbx7c9y74" role="1TKVEl">
      <property role="IQ2nx" value="285466280358977988" />
      <property role="TrG5h" value="y" />
      <ref role="AX2Wp" to="tpck:fKAQMTA" resolve="integer" />
    </node>
  </node>
</model>

