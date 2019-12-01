<?xml version="1.0" encoding="UTF-8"?>
<model ref="r:e571d96b-e617-4301-9e05-030e4fdbc1ca(Fuel.sandbox)">
  <persistence version="9" />
  <languages>
    <use id="5a51c51f-be77-476b-b071-cdecafb4c996" name="Fuel" version="0" />
  </languages>
  <imports />
  <registry>
    <language id="ceab5195-25ea-4f22-9b92-103b95ca8c0c" name="jetbrains.mps.lang.core">
      <concept id="1169194658468" name="jetbrains.mps.lang.core.structure.INamedConcept" flags="ng" index="TrEIO">
        <property id="1169194664001" name="name" index="TrG5h" />
      </concept>
    </language>
    <language id="5a51c51f-be77-476b-b071-cdecafb4c996" name="Fuel">
      <concept id="7341395926411693765" name="Fuel.structure.Module" flags="ng" index="2SDiTR">
        <property id="7341395926411693795" name="requiredFuel" index="2SDiTh" />
        <property id="7341395926411693766" name="mass" index="2SDiTO" />
        <property id="7341395926412028008" name="totalFuel" index="2SE0zq" />
        <property id="7341395926411954385" name="additionalFuel" index="2SEixz" />
      </concept>
      <concept id="7341395926411688991" name="Fuel.structure.FuelCounterUpper" flags="ng" index="2SDlMH">
        <property id="7341395926411895671" name="sumFuelRequirements" index="2SDwf5" />
        <property id="7341395926411830865" name="inputFilePath" index="2SDKrz" />
        <child id="7341395926411723198" name="modules" index="2SDa4c" />
      </concept>
    </language>
  </registry>
  <node concept="2SDlMH" id="6nxSbbticZd">
    <property role="TrG5h" value="Example" />
    <property role="2SDKrz" value="absolute-path-to-downloaded-input-file" />
    <property role="2SDwf5" value="51312" />
    <node concept="2SDiTR" id="6nxSbbticZe" role="2SDa4c">
      <property role="2SDiTO" value="1969" />
      <property role="2SDiTh" value="654" />
      <property role="2SEixz" value="312" />
      <property role="2SE0zq" value="966" />
    </node>
    <node concept="2SDiTR" id="6nxSbbtiC$1" role="2SDa4c">
      <property role="2SDiTO" value="100756" />
      <property role="2SDiTh" value="33583" />
      <property role="2SEixz" value="16763" />
      <property role="2SE0zq" value="50346" />
    </node>
  </node>
  <node concept="2SDlMH" id="57nI3ieYbYA">
    <property role="TrG5h" value="Jens" />
    <property role="2SDKrz" value="C:/Users/jnerc/MPSProjects/aoc_2019/01_fuel/input.txt" />
    <property role="2SDwf5" value="4946546" />
    <node concept="2SDiTR" id="57nI3ieYbYB" role="2SDa4c">
      <property role="2SDiTO" value="71764" />
      <property role="2SDiTh" value="23919" />
      <property role="2SEixz" value="11932" />
      <property role="2SE0zq" value="35851" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYC" role="2SDa4c">
      <property role="2SDiTO" value="58877" />
      <property role="2SDiTh" value="19623" />
      <property role="2SEixz" value="9783" />
      <property role="2SE0zq" value="29406" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYD" role="2SDa4c">
      <property role="2SDiTO" value="107994" />
      <property role="2SDiTh" value="35996" />
      <property role="2SEixz" value="17969" />
      <property role="2SE0zq" value="53965" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYE" role="2SDa4c">
      <property role="2SDiTO" value="72251" />
      <property role="2SDiTh" value="24081" />
      <property role="2SEixz" value="12015" />
      <property role="2SE0zq" value="36096" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYF" role="2SDa4c">
      <property role="2SDiTO" value="74966" />
      <property role="2SDiTh" value="24986" />
      <property role="2SEixz" value="12464" />
      <property role="2SE0zq" value="37450" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYG" role="2SDa4c">
      <property role="2SDiTO" value="87584" />
      <property role="2SDiTh" value="29192" />
      <property role="2SEixz" value="14568" />
      <property role="2SE0zq" value="43760" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYH" role="2SDa4c">
      <property role="2SDiTO" value="118260" />
      <property role="2SDiTh" value="39418" />
      <property role="2SEixz" value="19682" />
      <property role="2SE0zq" value="59100" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYI" role="2SDa4c">
      <property role="2SDiTO" value="144961" />
      <property role="2SDiTh" value="48318" />
      <property role="2SEixz" value="24130" />
      <property role="2SE0zq" value="72448" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYJ" role="2SDa4c">
      <property role="2SDiTO" value="86889" />
      <property role="2SDiTh" value="28961" />
      <property role="2SEixz" value="14452" />
      <property role="2SE0zq" value="43413" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYK" role="2SDa4c">
      <property role="2SDiTO" value="136710" />
      <property role="2SDiTh" value="45568" />
      <property role="2SEixz" value="22753" />
      <property role="2SE0zq" value="68321" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYL" role="2SDa4c">
      <property role="2SDiTO" value="52493" />
      <property role="2SDiTh" value="17495" />
      <property role="2SEixz" value="8723" />
      <property role="2SE0zq" value="26218" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYM" role="2SDa4c">
      <property role="2SDiTO" value="131045" />
      <property role="2SDiTh" value="43679" />
      <property role="2SEixz" value="21808" />
      <property role="2SE0zq" value="65487" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYN" role="2SDa4c">
      <property role="2SDiTO" value="101496" />
      <property role="2SDiTh" value="33830" />
      <property role="2SEixz" value="16887" />
      <property role="2SE0zq" value="50717" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYO" role="2SDa4c">
      <property role="2SDiTO" value="124341" />
      <property role="2SDiTh" value="41445" />
      <property role="2SEixz" value="20693" />
      <property role="2SE0zq" value="62138" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYP" role="2SDa4c">
      <property role="2SDiTO" value="71936" />
      <property role="2SDiTh" value="23976" />
      <property role="2SEixz" value="11960" />
      <property role="2SE0zq" value="35936" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYQ" role="2SDa4c">
      <property role="2SDiTO" value="88967" />
      <property role="2SDiTh" value="29653" />
      <property role="2SEixz" value="14799" />
      <property role="2SE0zq" value="44452" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYR" role="2SDa4c">
      <property role="2SDiTO" value="106520" />
      <property role="2SDiTh" value="35504" />
      <property role="2SEixz" value="17724" />
      <property role="2SE0zq" value="53228" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYS" role="2SDa4c">
      <property role="2SDiTO" value="125454" />
      <property role="2SDiTh" value="41816" />
      <property role="2SEixz" value="20879" />
      <property role="2SE0zq" value="62695" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYT" role="2SDa4c">
      <property role="2SDiTO" value="113463" />
      <property role="2SDiTh" value="37819" />
      <property role="2SEixz" value="18879" />
      <property role="2SE0zq" value="56698" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYU" role="2SDa4c">
      <property role="2SDiTO" value="81854" />
      <property role="2SDiTh" value="27282" />
      <property role="2SEixz" value="13613" />
      <property role="2SE0zq" value="40895" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYV" role="2SDa4c">
      <property role="2SDiTO" value="99918" />
      <property role="2SDiTh" value="33304" />
      <property role="2SEixz" value="16624" />
      <property role="2SE0zq" value="49928" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYW" role="2SDa4c">
      <property role="2SDiTO" value="105217" />
      <property role="2SDiTh" value="35070" />
      <property role="2SEixz" value="17509" />
      <property role="2SE0zq" value="52579" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYX" role="2SDa4c">
      <property role="2SDiTO" value="120383" />
      <property role="2SDiTh" value="40125" />
      <property role="2SEixz" value="20035" />
      <property role="2SE0zq" value="60160" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYY" role="2SDa4c">
      <property role="2SDiTO" value="61105" />
      <property role="2SDiTh" value="20366" />
      <property role="2SEixz" value="10155" />
      <property role="2SE0zq" value="30521" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbYZ" role="2SDa4c">
      <property role="2SDiTO" value="103842" />
      <property role="2SDiTh" value="34612" />
      <property role="2SEixz" value="17278" />
      <property role="2SE0zq" value="51890" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZ0" role="2SDa4c">
      <property role="2SDiTO" value="125151" />
      <property role="2SDiTh" value="41715" />
      <property role="2SEixz" value="20830" />
      <property role="2SE0zq" value="62545" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZ1" role="2SDa4c">
      <property role="2SDiTO" value="139191" />
      <property role="2SDiTh" value="46395" />
      <property role="2SEixz" value="23168" />
      <property role="2SE0zq" value="69563" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZ2" role="2SDa4c">
      <property role="2SDiTO" value="143365" />
      <property role="2SDiTh" value="47786" />
      <property role="2SEixz" value="23861" />
      <property role="2SE0zq" value="71647" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZ3" role="2SDa4c">
      <property role="2SDiTO" value="102168" />
      <property role="2SDiTh" value="34054" />
      <property role="2SEixz" value="16999" />
      <property role="2SE0zq" value="51053" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZ4" role="2SDa4c">
      <property role="2SDiTO" value="69845" />
      <property role="2SDiTh" value="23279" />
      <property role="2SEixz" value="11610" />
      <property role="2SE0zq" value="34889" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZ5" role="2SDa4c">
      <property role="2SDiTO" value="57343" />
      <property role="2SDiTh" value="19112" />
      <property role="2SEixz" value="9527" />
      <property role="2SE0zq" value="28639" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZ6" role="2SDa4c">
      <property role="2SDiTO" value="93401" />
      <property role="2SDiTh" value="31131" />
      <property role="2SEixz" value="15538" />
      <property role="2SE0zq" value="46669" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZ7" role="2SDa4c">
      <property role="2SDiTO" value="140910" />
      <property role="2SDiTh" value="46968" />
      <property role="2SEixz" value="23455" />
      <property role="2SE0zq" value="70423" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZ8" role="2SDa4c">
      <property role="2SDiTO" value="121997" />
      <property role="2SDiTh" value="40663" />
      <property role="2SEixz" value="20303" />
      <property role="2SE0zq" value="60966" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZ9" role="2SDa4c">
      <property role="2SDiTO" value="107964" />
      <property role="2SDiTh" value="35986" />
      <property role="2SEixz" value="17964" />
      <property role="2SE0zq" value="53950" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZa" role="2SDa4c">
      <property role="2SDiTO" value="53358" />
      <property role="2SDiTh" value="17784" />
      <property role="2SEixz" value="8866" />
      <property role="2SE0zq" value="26650" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZb" role="2SDa4c">
      <property role="2SDiTO" value="57397" />
      <property role="2SDiTh" value="19130" />
      <property role="2SEixz" value="9537" />
      <property role="2SE0zq" value="28667" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZc" role="2SDa4c">
      <property role="2SDiTO" value="141456" />
      <property role="2SDiTh" value="47150" />
      <property role="2SEixz" value="23546" />
      <property role="2SE0zq" value="70696" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZd" role="2SDa4c">
      <property role="2SDiTO" value="94052" />
      <property role="2SDiTh" value="31348" />
      <property role="2SEixz" value="15647" />
      <property role="2SE0zq" value="46995" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZe" role="2SDa4c">
      <property role="2SDiTO" value="127395" />
      <property role="2SDiTh" value="42463" />
      <property role="2SEixz" value="21202" />
      <property role="2SE0zq" value="63665" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZf" role="2SDa4c">
      <property role="2SDiTO" value="99180" />
      <property role="2SDiTh" value="33058" />
      <property role="2SEixz" value="16502" />
      <property role="2SE0zq" value="49560" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZg" role="2SDa4c">
      <property role="2SDiTO" value="143838" />
      <property role="2SDiTh" value="47944" />
      <property role="2SEixz" value="23941" />
      <property role="2SE0zq" value="71885" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZh" role="2SDa4c">
      <property role="2SDiTO" value="130749" />
      <property role="2SDiTh" value="43581" />
      <property role="2SEixz" value="21761" />
      <property role="2SE0zq" value="65342" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZi" role="2SDa4c">
      <property role="2SDiTO" value="126809" />
      <property role="2SDiTh" value="42267" />
      <property role="2SEixz" value="21103" />
      <property role="2SE0zq" value="63370" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZj" role="2SDa4c">
      <property role="2SDiTO" value="70165" />
      <property role="2SDiTh" value="23386" />
      <property role="2SEixz" value="11665" />
      <property role="2SE0zq" value="35051" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZk" role="2SDa4c">
      <property role="2SDiTO" value="92007" />
      <property role="2SDiTh" value="30667" />
      <property role="2SEixz" value="15305" />
      <property role="2SE0zq" value="45972" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZl" role="2SDa4c">
      <property role="2SDiTO" value="83343" />
      <property role="2SDiTh" value="27779" />
      <property role="2SEixz" value="13860" />
      <property role="2SE0zq" value="41639" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZm" role="2SDa4c">
      <property role="2SDiTO" value="55163" />
      <property role="2SDiTh" value="18385" />
      <property role="2SEixz" value="9167" />
      <property role="2SE0zq" value="27552" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZn" role="2SDa4c">
      <property role="2SDiTO" value="95270" />
      <property role="2SDiTh" value="31754" />
      <property role="2SEixz" value="15848" />
      <property role="2SE0zq" value="47602" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZo" role="2SDa4c">
      <property role="2SDiTO" value="101323" />
      <property role="2SDiTh" value="33772" />
      <property role="2SEixz" value="16856" />
      <property role="2SE0zq" value="50628" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZp" role="2SDa4c">
      <property role="2SDiTO" value="99877" />
      <property role="2SDiTh" value="33290" />
      <property role="2SEixz" value="16618" />
      <property role="2SE0zq" value="49908" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZq" role="2SDa4c">
      <property role="2SDiTO" value="105721" />
      <property role="2SDiTh" value="35238" />
      <property role="2SEixz" value="17592" />
      <property role="2SE0zq" value="52830" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZr" role="2SDa4c">
      <property role="2SDiTO" value="129657" />
      <property role="2SDiTh" value="43217" />
      <property role="2SEixz" value="21578" />
      <property role="2SE0zq" value="64795" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZs" role="2SDa4c">
      <property role="2SDiTO" value="61213" />
      <property role="2SDiTh" value="20402" />
      <property role="2SEixz" value="10172" />
      <property role="2SE0zq" value="30574" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZt" role="2SDa4c">
      <property role="2SDiTO" value="130120" />
      <property role="2SDiTh" value="43371" />
      <property role="2SEixz" value="21656" />
      <property role="2SE0zq" value="65027" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZu" role="2SDa4c">
      <property role="2SDiTO" value="108549" />
      <property role="2SDiTh" value="36181" />
      <property role="2SEixz" value="18061" />
      <property role="2SE0zq" value="54242" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZv" role="2SDa4c">
      <property role="2SDiTO" value="90539" />
      <property role="2SDiTh" value="30177" />
      <property role="2SEixz" value="15060" />
      <property role="2SE0zq" value="45237" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZw" role="2SDa4c">
      <property role="2SDiTO" value="111382" />
      <property role="2SDiTh" value="37125" />
      <property role="2SEixz" value="18533" />
      <property role="2SE0zq" value="55658" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZx" role="2SDa4c">
      <property role="2SDiTO" value="61665" />
      <property role="2SDiTh" value="20553" />
      <property role="2SEixz" value="10250" />
      <property role="2SE0zq" value="30803" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZy" role="2SDa4c">
      <property role="2SDiTO" value="95121" />
      <property role="2SDiTh" value="31705" />
      <property role="2SEixz" value="15824" />
      <property role="2SE0zq" value="47529" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZz" role="2SDa4c">
      <property role="2SDiTO" value="53216" />
      <property role="2SDiTh" value="17736" />
      <property role="2SEixz" value="8844" />
      <property role="2SE0zq" value="26580" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZ$" role="2SDa4c">
      <property role="2SDiTO" value="103144" />
      <property role="2SDiTh" value="34379" />
      <property role="2SEixz" value="17161" />
      <property role="2SE0zq" value="51540" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZ_" role="2SDa4c">
      <property role="2SDiTO" value="134367" />
      <property role="2SDiTh" value="44787" />
      <property role="2SEixz" value="22363" />
      <property role="2SE0zq" value="67150" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZA" role="2SDa4c">
      <property role="2SDiTO" value="101251" />
      <property role="2SDiTh" value="33748" />
      <property role="2SEixz" value="16846" />
      <property role="2SE0zq" value="50594" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZB" role="2SDa4c">
      <property role="2SDiTO" value="105118" />
      <property role="2SDiTh" value="35037" />
      <property role="2SEixz" value="17491" />
      <property role="2SE0zq" value="52528" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZC" role="2SDa4c">
      <property role="2SDiTO" value="73220" />
      <property role="2SDiTh" value="24404" />
      <property role="2SEixz" value="12173" />
      <property role="2SE0zq" value="36577" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZD" role="2SDa4c">
      <property role="2SDiTO" value="56270" />
      <property role="2SDiTh" value="18754" />
      <property role="2SEixz" value="9350" />
      <property role="2SE0zq" value="28104" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZE" role="2SDa4c">
      <property role="2SDiTO" value="50846" />
      <property role="2SDiTh" value="16946" />
      <property role="2SEixz" value="8446" />
      <property role="2SE0zq" value="25392" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZF" role="2SDa4c">
      <property role="2SDiTO" value="77314" />
      <property role="2SDiTh" value="25769" />
      <property role="2SEixz" value="12856" />
      <property role="2SE0zq" value="38625" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZG" role="2SDa4c">
      <property role="2SDiTO" value="59134" />
      <property role="2SDiTh" value="19709" />
      <property role="2SEixz" value="9829" />
      <property role="2SE0zq" value="29538" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZH" role="2SDa4c">
      <property role="2SDiTO" value="98495" />
      <property role="2SDiTh" value="32829" />
      <property role="2SEixz" value="16389" />
      <property role="2SE0zq" value="49218" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZI" role="2SDa4c">
      <property role="2SDiTO" value="113654" />
      <property role="2SDiTh" value="37882" />
      <property role="2SEixz" value="18911" />
      <property role="2SE0zq" value="56793" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZJ" role="2SDa4c">
      <property role="2SDiTO" value="89711" />
      <property role="2SDiTh" value="29901" />
      <property role="2SEixz" value="14923" />
      <property role="2SE0zq" value="44824" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZK" role="2SDa4c">
      <property role="2SDiTO" value="68676" />
      <property role="2SDiTh" value="22890" />
      <property role="2SEixz" value="11417" />
      <property role="2SE0zq" value="34307" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZL" role="2SDa4c">
      <property role="2SDiTO" value="98991" />
      <property role="2SDiTh" value="32995" />
      <property role="2SEixz" value="16470" />
      <property role="2SE0zq" value="49465" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZM" role="2SDa4c">
      <property role="2SDiTO" value="109068" />
      <property role="2SDiTh" value="36354" />
      <property role="2SEixz" value="18147" />
      <property role="2SE0zq" value="54501" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZN" role="2SDa4c">
      <property role="2SDiTO" value="129630" />
      <property role="2SDiTh" value="43208" />
      <property role="2SEixz" value="21574" />
      <property role="2SE0zq" value="64782" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZO" role="2SDa4c">
      <property role="2SDiTO" value="58999" />
      <property role="2SDiTh" value="19664" />
      <property role="2SEixz" value="9803" />
      <property role="2SE0zq" value="29467" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZP" role="2SDa4c">
      <property role="2SDiTO" value="132095" />
      <property role="2SDiTh" value="44029" />
      <property role="2SEixz" value="21985" />
      <property role="2SE0zq" value="66014" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZQ" role="2SDa4c">
      <property role="2SDiTO" value="98685" />
      <property role="2SDiTh" value="32893" />
      <property role="2SEixz" value="16420" />
      <property role="2SE0zq" value="49313" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZR" role="2SDa4c">
      <property role="2SDiTO" value="91762" />
      <property role="2SDiTh" value="30585" />
      <property role="2SEixz" value="15262" />
      <property role="2SE0zq" value="45847" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZS" role="2SDa4c">
      <property role="2SDiTO" value="88589" />
      <property role="2SDiTh" value="29527" />
      <property role="2SEixz" value="14735" />
      <property role="2SE0zq" value="44262" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZT" role="2SDa4c">
      <property role="2SDiTO" value="73846" />
      <property role="2SDiTh" value="24613" />
      <property role="2SEixz" value="12278" />
      <property role="2SE0zq" value="36891" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZU" role="2SDa4c">
      <property role="2SDiTO" value="124940" />
      <property role="2SDiTh" value="41644" />
      <property role="2SEixz" value="20794" />
      <property role="2SE0zq" value="62438" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZV" role="2SDa4c">
      <property role="2SDiTO" value="106944" />
      <property role="2SDiTh" value="35646" />
      <property role="2SEixz" value="17795" />
      <property role="2SE0zq" value="53441" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZW" role="2SDa4c">
      <property role="2SDiTO" value="133882" />
      <property role="2SDiTh" value="44625" />
      <property role="2SEixz" value="22282" />
      <property role="2SE0zq" value="66907" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZX" role="2SDa4c">
      <property role="2SDiTO" value="104073" />
      <property role="2SDiTh" value="34689" />
      <property role="2SEixz" value="17315" />
      <property role="2SE0zq" value="52004" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZY" role="2SDa4c">
      <property role="2SDiTO" value="78475" />
      <property role="2SDiTh" value="26156" />
      <property role="2SEixz" value="13047" />
      <property role="2SE0zq" value="39203" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYbZZ" role="2SDa4c">
      <property role="2SDiTO" value="76545" />
      <property role="2SDiTh" value="25513" />
      <property role="2SEixz" value="12730" />
      <property role="2SE0zq" value="38243" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYc00" role="2SDa4c">
      <property role="2SDiTO" value="144728" />
      <property role="2SDiTh" value="48240" />
      <property role="2SEixz" value="24091" />
      <property role="2SE0zq" value="72331" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYc01" role="2SDa4c">
      <property role="2SDiTO" value="72449" />
      <property role="2SDiTh" value="24147" />
      <property role="2SEixz" value="12047" />
      <property role="2SE0zq" value="36194" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYc02" role="2SDa4c">
      <property role="2SDiTO" value="118320" />
      <property role="2SDiTh" value="39438" />
      <property role="2SEixz" value="19691" />
      <property role="2SE0zq" value="59129" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYc03" role="2SDa4c">
      <property role="2SDiTO" value="65363" />
      <property role="2SDiTh" value="21785" />
      <property role="2SEixz" value="10862" />
      <property role="2SE0zq" value="32647" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYc04" role="2SDa4c">
      <property role="2SDiTO" value="83523" />
      <property role="2SDiTh" value="27839" />
      <property role="2SEixz" value="13891" />
      <property role="2SE0zq" value="41730" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYc05" role="2SDa4c">
      <property role="2SDiTO" value="124634" />
      <property role="2SDiTh" value="41542" />
      <property role="2SEixz" value="20740" />
      <property role="2SE0zq" value="62282" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYc06" role="2SDa4c">
      <property role="2SDiTO" value="96222" />
      <property role="2SDiTh" value="32072" />
      <property role="2SEixz" value="16004" />
      <property role="2SE0zq" value="48076" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYc07" role="2SDa4c">
      <property role="2SDiTO" value="128252" />
      <property role="2SDiTh" value="42748" />
      <property role="2SEixz" value="21344" />
      <property role="2SE0zq" value="64092" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYc08" role="2SDa4c">
      <property role="2SDiTO" value="112848" />
      <property role="2SDiTh" value="37614" />
      <property role="2SEixz" value="18778" />
      <property role="2SE0zq" value="56392" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYc09" role="2SDa4c">
      <property role="2SDiTO" value="139027" />
      <property role="2SDiTh" value="46340" />
      <property role="2SEixz" value="23141" />
      <property role="2SE0zq" value="69481" />
    </node>
    <node concept="2SDiTR" id="57nI3ieYc0a" role="2SDa4c">
      <property role="2SDiTO" value="108208" />
      <property role="2SDiTh" value="36067" />
      <property role="2SEixz" value="18004" />
      <property role="2SE0zq" value="54071" />
    </node>
  </node>
</model>

