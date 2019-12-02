<?xml version="1.0" encoding="UTF-8"?>
<model ref="r:aea39d3f-6e83-42ec-a080-6541cd315d5d(Fuel.editor)">
  <persistence version="9" />
  <languages>
    <use id="18bc6592-03a6-4e29-a83a-7ff23bde13ba" name="jetbrains.mps.lang.editor" version="12" />
    <devkit ref="fbc25dd2-5da4-483a-8b19-70928e1b62d7(jetbrains.mps.devkit.general-purpose)" />
  </languages>
  <imports>
    <import index="v7ld" ref="r:5ada82f8-0d9f-4074-9439-407e19c221c3(Fuel.structure)" implicit="true" />
    <import index="tpck" ref="r:00000000-0000-4000-0000-011c89590288(jetbrains.mps.lang.core.structure)" implicit="true" />
  </imports>
  <registry>
    <language id="18bc6592-03a6-4e29-a83a-7ff23bde13ba" name="jetbrains.mps.lang.editor">
      <concept id="1071666914219" name="jetbrains.mps.lang.editor.structure.ConceptEditorDeclaration" flags="ig" index="24kQdi" />
      <concept id="1140524381322" name="jetbrains.mps.lang.editor.structure.CellModel_ListWithRole" flags="ng" index="2czfm3">
        <child id="1140524464360" name="cellLayout" index="2czzBx" />
      </concept>
      <concept id="1106270549637" name="jetbrains.mps.lang.editor.structure.CellLayout_Horizontal" flags="nn" index="2iRfu4" />
      <concept id="1106270571710" name="jetbrains.mps.lang.editor.structure.CellLayout_Vertical" flags="nn" index="2iRkQZ" />
      <concept id="1080736578640" name="jetbrains.mps.lang.editor.structure.BaseEditorComponent" flags="ig" index="2wURMF">
        <child id="1080736633877" name="cellModel" index="2wV5jI" />
      </concept>
      <concept id="1139848536355" name="jetbrains.mps.lang.editor.structure.CellModel_WithRole" flags="ng" index="1$h60E">
        <reference id="1140103550593" name="relationDeclaration" index="1NtTu8" />
      </concept>
      <concept id="1073389446423" name="jetbrains.mps.lang.editor.structure.CellModel_Collection" flags="sn" stub="3013115976261988961" index="3EZMnI">
        <child id="1106270802874" name="cellLayout" index="2iSdaV" />
        <child id="1073389446424" name="childCellModel" index="3EZMnx" />
      </concept>
      <concept id="1073389577006" name="jetbrains.mps.lang.editor.structure.CellModel_Constant" flags="sn" stub="3610246225209162225" index="3F0ifn">
        <property id="1073389577007" name="text" index="3F0ifm" />
      </concept>
      <concept id="1073389658414" name="jetbrains.mps.lang.editor.structure.CellModel_Property" flags="sg" stub="730538219796134133" index="3F0A7n" />
      <concept id="1073390211982" name="jetbrains.mps.lang.editor.structure.CellModel_RefNodeList" flags="sg" stub="2794558372793454595" index="3F2HdR" />
      <concept id="1166049232041" name="jetbrains.mps.lang.editor.structure.AbstractComponent" flags="ng" index="1XWOmA">
        <reference id="1166049300910" name="conceptDeclaration" index="1XX52x" />
      </concept>
    </language>
  </registry>
  <node concept="24kQdi" id="6nxSbbthbKV">
    <ref role="1XX52x" to="v7ld:6nxSbbthbKv" resolve="FuelCounterUpper" />
    <node concept="3EZMnI" id="6nxSbbthbKX" role="2wV5jI">
      <node concept="2iRkQZ" id="6nxSbbthbKY" role="2iSdaV" />
      <node concept="3EZMnI" id="6nxSbbthbL3" role="3EZMnx">
        <node concept="2iRfu4" id="6nxSbbthbL4" role="2iSdaV" />
        <node concept="3F0ifn" id="6nxSbbthbL9" role="3EZMnx">
          <property role="3F0ifm" value="Fuel Counter-Upper" />
        </node>
        <node concept="3F0A7n" id="6nxSbbthbLf" role="3EZMnx">
          <ref role="1NtTu8" to="tpck:h0TrG11" resolve="name" />
        </node>
      </node>
      <node concept="3F0ifn" id="6nxSbbthk70" role="3EZMnx" />
      <node concept="3EZMnI" id="6nxSbbthIpC" role="3EZMnx">
        <node concept="2iRfu4" id="6nxSbbthIpD" role="2iSdaV" />
        <node concept="3F0ifn" id="6nxSbbthIpj" role="3EZMnx">
          <property role="3F0ifm" value="Input file path:" />
        </node>
        <node concept="3F0A7n" id="6nxSbbthIpU" role="3EZMnx">
          <ref role="1NtTu8" to="v7ld:6nxSbbthIph" resolve="inputFilePath" />
        </node>
      </node>
      <node concept="3EZMnI" id="6nxSbbthYe9" role="3EZMnx">
        <node concept="2iRfu4" id="6nxSbbthYea" role="2iSdaV" />
        <node concept="3F0ifn" id="6nxSbbthYdU" role="3EZMnx">
          <property role="3F0ifm" value="Sum of fuel requirements:" />
        </node>
        <node concept="3F0A7n" id="6nxSbbthYev" role="3EZMnx">
          <ref role="1NtTu8" to="v7ld:6nxSbbthYdR" resolve="sumFuelRequirements" />
        </node>
      </node>
      <node concept="3F0ifn" id="6nxSbbthIpt" role="3EZMnx" />
      <node concept="3F0ifn" id="6nxSbbthk76" role="3EZMnx">
        <property role="3F0ifm" value="Modules" />
      </node>
      <node concept="3F2HdR" id="6nxSbbthk7l" role="3EZMnx">
        <ref role="1NtTu8" to="v7ld:6nxSbbthk6Y" resolve="modules" />
        <node concept="2iRkQZ" id="6nxSbbthk7n" role="2czzBx" />
      </node>
    </node>
  </node>
  <node concept="24kQdi" id="6nxSbbthcVx">
    <ref role="1XX52x" to="v7ld:6nxSbbthcV5" resolve="Module" />
    <node concept="3EZMnI" id="6nxSbbthhyb" role="2wV5jI">
      <node concept="2iRfu4" id="6nxSbbthhyc" role="2iSdaV" />
      <node concept="3F0A7n" id="6nxSbbthhyh" role="3EZMnx">
        <ref role="1NtTu8" to="v7ld:6nxSbbthcV6" resolve="mass" />
      </node>
      <node concept="3F0ifn" id="6nxSbbthhyn" role="3EZMnx">
        <property role="3F0ifm" value="-&gt;" />
      </node>
      <node concept="3F0A7n" id="6nxSbbthhyv" role="3EZMnx">
        <ref role="1NtTu8" to="v7ld:6nxSbbthcVz" resolve="requiredFuel" />
      </node>
      <node concept="3F0ifn" id="6nxSbbticzt" role="3EZMnx">
        <property role="3F0ifm" value="+" />
      </node>
      <node concept="3F0A7n" id="6nxSbbticzD" role="3EZMnx">
        <ref role="1NtTu8" to="v7ld:6nxSbbticzh" resolve="additionalFuel" />
      </node>
      <node concept="3F0ifn" id="6nxSbbtiuxR" role="3EZMnx">
        <property role="3F0ifm" value="=" />
      </node>
      <node concept="3F0A7n" id="6nxSbbtiuy7" role="3EZMnx">
        <ref role="1NtTu8" to="v7ld:6nxSbbtiuxC" resolve="totalFuel" />
      </node>
    </node>
  </node>
</model>

