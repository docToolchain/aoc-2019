<?xml version="1.0" encoding="UTF-8"?>
<model ref="r:35a6b1a2-fa61-442e-8711-e324c7801264(Intcode.editor)">
  <persistence version="9" />
  <languages>
    <use id="18bc6592-03a6-4e29-a83a-7ff23bde13ba" name="jetbrains.mps.lang.editor" version="12" />
    <devkit ref="fbc25dd2-5da4-483a-8b19-70928e1b62d7(jetbrains.mps.devkit.general-purpose)" />
  </languages>
  <imports>
    <import index="x9r3" ref="r:84dee49f-0f88-4a63-af75-9bc34ebfe2a0(Intcode.structure)" implicit="true" />
    <import index="tpck" ref="r:00000000-0000-4000-0000-011c89590288(jetbrains.mps.lang.core.structure)" implicit="true" />
  </imports>
  <registry>
    <language id="18bc6592-03a6-4e29-a83a-7ff23bde13ba" name="jetbrains.mps.lang.editor">
      <concept id="1071666914219" name="jetbrains.mps.lang.editor.structure.ConceptEditorDeclaration" flags="ig" index="24kQdi" />
      <concept id="1140524381322" name="jetbrains.mps.lang.editor.structure.CellModel_ListWithRole" flags="ng" index="2czfm3">
        <child id="1140524464360" name="cellLayout" index="2czzBx" />
        <child id="928328222691832421" name="separatorTextQuery" index="2gpyvW" />
      </concept>
      <concept id="1106270549637" name="jetbrains.mps.lang.editor.structure.CellLayout_Horizontal" flags="nn" index="2iRfu4" />
      <concept id="1106270571710" name="jetbrains.mps.lang.editor.structure.CellLayout_Vertical" flags="nn" index="2iRkQZ" />
      <concept id="709996738298806197" name="jetbrains.mps.lang.editor.structure.QueryFunction_SeparatorText" flags="in" index="2o9xnK" />
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
    <language id="f3061a53-9226-4cc5-a443-f952ceaf5816" name="jetbrains.mps.baseLanguage">
      <concept id="1137021947720" name="jetbrains.mps.baseLanguage.structure.ConceptFunction" flags="in" index="2VMwT0">
        <child id="1137022507850" name="body" index="2VODD2" />
      </concept>
      <concept id="1070475926800" name="jetbrains.mps.baseLanguage.structure.StringLiteral" flags="nn" index="Xl_RD">
        <property id="1070475926801" name="value" index="Xl_RC" />
      </concept>
      <concept id="1068580123155" name="jetbrains.mps.baseLanguage.structure.ExpressionStatement" flags="nn" index="3clFbF">
        <child id="1068580123156" name="expression" index="3clFbG" />
      </concept>
      <concept id="1068580123136" name="jetbrains.mps.baseLanguage.structure.StatementList" flags="sn" stub="5293379017992965193" index="3clFbS">
        <child id="1068581517665" name="statement" index="3cqZAp" />
      </concept>
    </language>
  </registry>
  <node concept="24kQdi" id="32nSEEspKJR">
    <ref role="1XX52x" to="x9r3:32nSEEspKJr" resolve="IntcodeProgram" />
    <node concept="3EZMnI" id="32nSEEspKJT" role="2wV5jI">
      <node concept="2iRkQZ" id="32nSEEspKJU" role="2iSdaV" />
      <node concept="3EZMnI" id="32nSEEspKJZ" role="3EZMnx">
        <node concept="2iRfu4" id="32nSEEspKK0" role="2iSdaV" />
        <node concept="3F0ifn" id="32nSEEspKK5" role="3EZMnx">
          <property role="3F0ifm" value="Intcode Program" />
        </node>
        <node concept="3F0A7n" id="32nSEEspKKb" role="3EZMnx">
          <ref role="1NtTu8" to="tpck:h0TrG11" resolve="name" />
        </node>
      </node>
      <node concept="3F0ifn" id="32nSEEspL_j" role="3EZMnx" />
      <node concept="3EZMnI" id="32nSEEsrw62" role="3EZMnx">
        <node concept="2iRfu4" id="32nSEEsrw63" role="2iSdaV" />
        <node concept="3F0ifn" id="32nSEEsrw5B" role="3EZMnx">
          <property role="3F0ifm" value="Input string:" />
        </node>
        <node concept="3F0A7n" id="32nSEEsrw6n" role="3EZMnx">
          <ref role="1NtTu8" to="x9r3:32nSEEsrvWP" resolve="inputString" />
        </node>
      </node>
      <node concept="3EZMnI" id="32nSEEssJZ5" role="3EZMnx">
        <node concept="2iRfu4" id="32nSEEssJZ6" role="2iSdaV" />
        <node concept="3F0ifn" id="32nSEEssJYN" role="3EZMnx">
          <property role="3F0ifm" value="Result part 2:" />
        </node>
        <node concept="3F0A7n" id="32nSEEssJZu" role="3EZMnx">
          <ref role="1NtTu8" to="x9r3:32nSEEssJQ3" resolve="resultPart2" />
        </node>
      </node>
      <node concept="3F0ifn" id="32nSEEsrw5O" role="3EZMnx" />
      <node concept="3F0ifn" id="fQbx7c7cGv" role="3EZMnx">
        <property role="3F0ifm" value="Memory contents:" />
      </node>
      <node concept="3F2HdR" id="32nSEEspL_w" role="3EZMnx">
        <ref role="1NtTu8" to="x9r3:32nSEEspL_h" resolve="memory" />
        <node concept="2iRfu4" id="32nSEEspL_E" role="2czzBx" />
        <node concept="2o9xnK" id="32nSEEspL_H" role="2gpyvW">
          <node concept="3clFbS" id="32nSEEspL_I" role="2VODD2">
            <node concept="3clFbF" id="32nSEEspLIl" role="3cqZAp">
              <node concept="Xl_RD" id="32nSEEspLIk" role="3clFbG">
                <property role="Xl_RC" value="," />
              </node>
            </node>
          </node>
        </node>
      </node>
    </node>
  </node>
  <node concept="24kQdi" id="32nSEEspL_c">
    <ref role="1XX52x" to="x9r3:32nSEEspL$K" resolve="MemoryPosition" />
    <node concept="3F0A7n" id="32nSEEspL_e" role="2wV5jI">
      <ref role="1NtTu8" to="x9r3:32nSEEspL$L" resolve="content" />
    </node>
  </node>
</model>

