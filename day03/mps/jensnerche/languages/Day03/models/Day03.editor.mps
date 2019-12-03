<?xml version="1.0" encoding="UTF-8"?>
<model ref="r:ffa68794-3307-4dd3-8af0-81b3f19ec8bc(Day03.editor)">
  <persistence version="9" />
  <languages>
    <use id="18bc6592-03a6-4e29-a83a-7ff23bde13ba" name="jetbrains.mps.lang.editor" version="12" />
    <devkit ref="fbc25dd2-5da4-483a-8b19-70928e1b62d7(jetbrains.mps.devkit.general-purpose)" />
  </languages>
  <imports>
    <import index="b4kx" ref="r:c59fa5b7-130e-45f2-a476-3ba334523617(Day03.structure)" implicit="true" />
    <import index="tpck" ref="r:00000000-0000-4000-0000-011c89590288(jetbrains.mps.lang.core.structure)" implicit="true" />
    <import index="tpco" ref="r:00000000-0000-4000-0000-011c89590284(jetbrains.mps.lang.core.editor)" implicit="true" />
    <import index="c17a" ref="8865b7a8-5271-43d3-884c-6fd1d9cfdd34/java:org.jetbrains.mps.openapi.language(MPS.OpenAPI/)" implicit="true" />
  </imports>
  <registry>
    <language id="18bc6592-03a6-4e29-a83a-7ff23bde13ba" name="jetbrains.mps.lang.editor">
      <concept id="2000375450116454183" name="jetbrains.mps.lang.editor.structure.ISubstituteMenu" flags="ng" index="22mbnS">
        <child id="414384289274416996" name="parts" index="3ft7WO" />
      </concept>
      <concept id="1071666914219" name="jetbrains.mps.lang.editor.structure.ConceptEditorDeclaration" flags="ig" index="24kQdi">
        <child id="1078153129734" name="inspectedCellModel" index="6VMZX" />
      </concept>
      <concept id="1176897764478" name="jetbrains.mps.lang.editor.structure.QueryFunction_NodeFactory" flags="in" index="4$FPG" />
      <concept id="1140524381322" name="jetbrains.mps.lang.editor.structure.CellModel_ListWithRole" flags="ng" index="2czfm3">
        <child id="1176897874615" name="nodeFactory" index="4_6I_" />
        <child id="1140524464360" name="cellLayout" index="2czzBx" />
        <child id="928328222691832421" name="separatorTextQuery" index="2gpyvW" />
      </concept>
      <concept id="1106270549637" name="jetbrains.mps.lang.editor.structure.CellLayout_Horizontal" flags="nn" index="2iRfu4" />
      <concept id="1106270571710" name="jetbrains.mps.lang.editor.structure.CellLayout_Vertical" flags="nn" index="2iRkQZ" />
      <concept id="709996738298806197" name="jetbrains.mps.lang.editor.structure.QueryFunction_SeparatorText" flags="in" index="2o9xnK" />
      <concept id="1177327570013" name="jetbrains.mps.lang.editor.structure.QueryFunction_SubstituteMenu_Substitute" flags="in" index="ucgPf" />
      <concept id="8478191136883534237" name="jetbrains.mps.lang.editor.structure.IExtensibleSubstituteMenuPart" flags="ng" index="upBLQ">
        <child id="8478191136883534238" name="features" index="upBLP" />
      </concept>
      <concept id="1177335944525" name="jetbrains.mps.lang.editor.structure.QueryFunction_SubstituteMenu_SubstituteString" flags="in" index="uGdhv" />
      <concept id="1080736578640" name="jetbrains.mps.lang.editor.structure.BaseEditorComponent" flags="ig" index="2wURMF">
        <child id="1080736633877" name="cellModel" index="2wV5jI" />
      </concept>
      <concept id="8371900013785948369" name="jetbrains.mps.lang.editor.structure.QueryFunction_SubstituteMenu_Parameter" flags="ig" index="2$S_p_" />
      <concept id="308059530142752797" name="jetbrains.mps.lang.editor.structure.SubstituteMenuPart_Parameterized" flags="ng" index="2F$Pav">
        <child id="8371900013785948359" name="part" index="2$S_pN" />
        <child id="8371900013785948365" name="parameterQuery" index="2$S_pT" />
      </concept>
      <concept id="1078939183254" name="jetbrains.mps.lang.editor.structure.CellModel_Component" flags="sg" stub="3162947552742194261" index="PMmxH">
        <reference id="1078939183255" name="editorComponent" index="PMmxG" />
      </concept>
      <concept id="1186414536763" name="jetbrains.mps.lang.editor.structure.BooleanStyleSheetItem" flags="ln" index="VOi$J">
        <property id="1186414551515" name="flag" index="VOm3f" />
      </concept>
      <concept id="1630016958697344083" name="jetbrains.mps.lang.editor.structure.IMenu_Concept" flags="ng" index="2ZABuq">
        <reference id="6591946374543067572" name="conceptDeclaration" index="aqKnT" />
      </concept>
      <concept id="1630016958697286851" name="jetbrains.mps.lang.editor.structure.QueryFunctionParameter_parameterObject" flags="ng" index="2ZBlsa" />
      <concept id="1630016958697057551" name="jetbrains.mps.lang.editor.structure.IMenuPartParameterized" flags="ng" index="2ZBHr6">
        <child id="1630016958697057552" name="parameterType" index="2ZBHrp" />
      </concept>
      <concept id="1233758997495" name="jetbrains.mps.lang.editor.structure.PunctuationLeftStyleClassItem" flags="ln" index="11L4FC" />
      <concept id="8998492695583125082" name="jetbrains.mps.lang.editor.structure.SubstituteFeature_MatchingText" flags="ng" index="16NfWO">
        <child id="8998492695583129244" name="query" index="16NeZM" />
      </concept>
      <concept id="8998492695583129971" name="jetbrains.mps.lang.editor.structure.SubstituteFeature_DescriptionText" flags="ng" index="16NL0t">
        <child id="8998492695583129972" name="query" index="16NL0q" />
      </concept>
      <concept id="7342352913006985483" name="jetbrains.mps.lang.editor.structure.SubstituteMenuPart_Action" flags="ng" index="3eGOop">
        <child id="8612453216082699922" name="substituteHandler" index="3aKz83" />
      </concept>
      <concept id="3308396621974580100" name="jetbrains.mps.lang.editor.structure.SubstituteMenu_Default" flags="ng" index="3p36aQ" />
      <concept id="7580468736840446506" name="jetbrains.mps.lang.editor.structure.ConceptFunctionParameter_model" flags="nn" index="1rpKSd" />
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
      <concept id="1219418625346" name="jetbrains.mps.lang.editor.structure.IStyleContainer" flags="ng" index="3F0Thp">
        <child id="1219418656006" name="styleItem" index="3F10Kt" />
      </concept>
      <concept id="1073390211982" name="jetbrains.mps.lang.editor.structure.CellModel_RefNodeList" flags="sg" stub="2794558372793454595" index="3F2HdR" />
      <concept id="2722384699544370949" name="jetbrains.mps.lang.editor.structure.SubstituteMenuPart_Placeholder" flags="ng" index="3VyMlK" />
      <concept id="1166049232041" name="jetbrains.mps.lang.editor.structure.AbstractComponent" flags="ng" index="1XWOmA">
        <reference id="1166049300910" name="conceptDeclaration" index="1XX52x" />
      </concept>
    </language>
    <language id="f3061a53-9226-4cc5-a443-f952ceaf5816" name="jetbrains.mps.baseLanguage">
      <concept id="4836112446988635817" name="jetbrains.mps.baseLanguage.structure.UndefinedType" flags="in" index="2jxLKc" />
      <concept id="1202948039474" name="jetbrains.mps.baseLanguage.structure.InstanceMethodCallOperation" flags="nn" index="liA8E" />
      <concept id="1197027756228" name="jetbrains.mps.baseLanguage.structure.DotExpression" flags="nn" index="2OqwBi">
        <child id="1197027771414" name="operand" index="2Oq$k0" />
        <child id="1197027833540" name="operation" index="2OqNvi" />
      </concept>
      <concept id="1145552977093" name="jetbrains.mps.baseLanguage.structure.GenericNewExpression" flags="nn" index="2ShNRf">
        <child id="1145553007750" name="creator" index="2ShVmc" />
      </concept>
      <concept id="1137021947720" name="jetbrains.mps.baseLanguage.structure.ConceptFunction" flags="in" index="2VMwT0">
        <child id="1137022507850" name="body" index="2VODD2" />
      </concept>
      <concept id="1070475926800" name="jetbrains.mps.baseLanguage.structure.StringLiteral" flags="nn" index="Xl_RD">
        <property id="1070475926801" name="value" index="Xl_RC" />
      </concept>
      <concept id="1068431474542" name="jetbrains.mps.baseLanguage.structure.VariableDeclaration" flags="ng" index="33uBYm">
        <child id="1068431790190" name="initializer" index="33vP2m" />
      </concept>
      <concept id="1068498886296" name="jetbrains.mps.baseLanguage.structure.VariableReference" flags="nn" index="37vLTw">
        <reference id="1068581517664" name="variableDeclaration" index="3cqZAo" />
      </concept>
      <concept id="4972933694980447171" name="jetbrains.mps.baseLanguage.structure.BaseVariableDeclaration" flags="ng" index="19Szcq">
        <child id="5680397130376446158" name="type" index="1tU5fm" />
      </concept>
      <concept id="1068580123155" name="jetbrains.mps.baseLanguage.structure.ExpressionStatement" flags="nn" index="3clFbF">
        <child id="1068580123156" name="expression" index="3clFbG" />
      </concept>
      <concept id="1068580123136" name="jetbrains.mps.baseLanguage.structure.StatementList" flags="sn" stub="5293379017992965193" index="3clFbS">
        <child id="1068581517665" name="statement" index="3cqZAp" />
      </concept>
      <concept id="1068581242878" name="jetbrains.mps.baseLanguage.structure.ReturnStatement" flags="nn" index="3cpWs6">
        <child id="1068581517676" name="expression" index="3cqZAk" />
      </concept>
      <concept id="1068581242864" name="jetbrains.mps.baseLanguage.structure.LocalVariableDeclarationStatement" flags="nn" index="3cpWs8">
        <child id="1068581242865" name="localVariableDeclaration" index="3cpWs9" />
      </concept>
      <concept id="1068581242863" name="jetbrains.mps.baseLanguage.structure.LocalVariableDeclaration" flags="nr" index="3cpWsn" />
      <concept id="1081516740877" name="jetbrains.mps.baseLanguage.structure.NotExpression" flags="nn" index="3fqX7Q">
        <child id="1081516765348" name="expression" index="3fr31v" />
      </concept>
      <concept id="1204053956946" name="jetbrains.mps.baseLanguage.structure.IMethodCall" flags="ng" index="1ndlxa">
        <reference id="1068499141037" name="baseMethodDeclaration" index="37wK5l" />
      </concept>
    </language>
    <language id="fd392034-7849-419d-9071-12563d152375" name="jetbrains.mps.baseLanguage.closures">
      <concept id="1199569711397" name="jetbrains.mps.baseLanguage.closures.structure.ClosureLiteral" flags="nn" index="1bVj0M">
        <child id="1199569906740" name="parameter" index="1bW2Oz" />
        <child id="1199569916463" name="body" index="1bW5cS" />
      </concept>
    </language>
    <language id="7866978e-a0f0-4cc7-81bc-4d213d9375e1" name="jetbrains.mps.lang.smodel">
      <concept id="1181949435690" name="jetbrains.mps.lang.smodel.structure.Concept_NewInstance" flags="nn" index="LFhST" />
      <concept id="1181952871644" name="jetbrains.mps.lang.smodel.structure.Concept_GetAllSubConcepts" flags="nn" index="LSoRf">
        <child id="1182506816063" name="smodel" index="1iTxcG" />
      </concept>
      <concept id="2644386474300074836" name="jetbrains.mps.lang.smodel.structure.ConceptIdRefExpression" flags="nn" index="35c_gC">
        <reference id="2644386474300074837" name="conceptDeclaration" index="35c_gD" />
      </concept>
      <concept id="6677504323281689838" name="jetbrains.mps.lang.smodel.structure.SConceptType" flags="in" index="3bZ5Sz">
        <reference id="6677504323281689839" name="conceptDeclaraton" index="3bZ5Sy" />
      </concept>
      <concept id="6870613620390542976" name="jetbrains.mps.lang.smodel.structure.ConceptAliasOperation" flags="ng" index="3n3YKJ" />
      <concept id="1180636770613" name="jetbrains.mps.lang.smodel.structure.SNodeCreator" flags="nn" index="3zrR0B">
        <child id="1180636770616" name="createdType" index="3zrR0E" />
      </concept>
      <concept id="1138055754698" name="jetbrains.mps.lang.smodel.structure.SNodeType" flags="in" index="3Tqbb2">
        <reference id="1138405853777" name="concept" index="ehGHo" />
      </concept>
    </language>
    <language id="ceab5195-25ea-4f22-9b92-103b95ca8c0c" name="jetbrains.mps.lang.core">
      <concept id="1169194658468" name="jetbrains.mps.lang.core.structure.INamedConcept" flags="ng" index="TrEIO">
        <property id="1169194664001" name="name" index="TrG5h" />
      </concept>
    </language>
    <language id="83888646-71ce-4f1c-9c53-c54016f6ad4f" name="jetbrains.mps.baseLanguage.collections">
      <concept id="1204796164442" name="jetbrains.mps.baseLanguage.collections.structure.InternalSequenceOperation" flags="nn" index="23sCx2">
        <child id="1204796294226" name="closure" index="23t8la" />
      </concept>
      <concept id="1203518072036" name="jetbrains.mps.baseLanguage.collections.structure.SmartClosureParameterDeclaration" flags="ig" index="Rh6nW" />
      <concept id="1202120902084" name="jetbrains.mps.baseLanguage.collections.structure.WhereOperation" flags="nn" index="3zZkjj" />
    </language>
  </registry>
  <node concept="24kQdi" id="fQbx7c7mKT">
    <ref role="1XX52x" to="b4kx:fQbx7c7mKt" resolve="Grid" />
    <node concept="3EZMnI" id="fQbx7c7mKV" role="2wV5jI">
      <node concept="2iRkQZ" id="fQbx7c7mKW" role="2iSdaV" />
      <node concept="3EZMnI" id="fQbx7c7mL1" role="3EZMnx">
        <node concept="2iRfu4" id="fQbx7c7mL2" role="2iSdaV" />
        <node concept="3F0ifn" id="fQbx7c7mL7" role="3EZMnx">
          <property role="3F0ifm" value="Grid" />
        </node>
        <node concept="3F0A7n" id="fQbx7c7mLd" role="3EZMnx">
          <ref role="1NtTu8" to="tpck:h0TrG11" resolve="name" />
        </node>
      </node>
      <node concept="3F0ifn" id="fQbx7c7qgd" role="3EZMnx" />
      <node concept="3EZMnI" id="fQbx7c7qgq" role="3EZMnx">
        <node concept="2iRfu4" id="fQbx7c7qgr" role="2iSdaV" />
        <node concept="3F0ifn" id="fQbx7c7qgj" role="3EZMnx">
          <property role="3F0ifm" value="First Wires Path :" />
        </node>
        <node concept="3F2HdR" id="fQbx7c7qgC" role="3EZMnx">
          <ref role="1NtTu8" to="b4kx:fQbx7c7qg8" resolve="path1" />
          <node concept="2iRfu4" id="fQbx7c7qgE" role="2czzBx" />
          <node concept="2o9xnK" id="fQbx7c7ts6" role="2gpyvW">
            <node concept="3clFbS" id="fQbx7c7ts7" role="2VODD2">
              <node concept="3clFbF" id="fQbx7c7t$I" role="3cqZAp">
                <node concept="Xl_RD" id="fQbx7c7t$H" role="3clFbG">
                  <property role="Xl_RC" value="," />
                </node>
              </node>
            </node>
          </node>
          <node concept="4$FPG" id="fQbx7c811P" role="4_6I_">
            <node concept="3clFbS" id="fQbx7c811Q" role="2VODD2">
              <node concept="3clFbF" id="fQbx7c815k" role="3cqZAp">
                <node concept="2ShNRf" id="fQbx7c815i" role="3clFbG">
                  <node concept="3zrR0B" id="fQbx7c81la" role="2ShVmc">
                    <node concept="3Tqbb2" id="fQbx7c81lc" role="3zrR0E">
                      <ref role="ehGHo" to="b4kx:fQbx7c80lB" resolve="EmptyDirection" />
                    </node>
                  </node>
                </node>
              </node>
            </node>
          </node>
        </node>
      </node>
      <node concept="3EZMnI" id="fQbx7c7qgU" role="3EZMnx">
        <node concept="2iRfu4" id="fQbx7c7qgV" role="2iSdaV" />
        <node concept="3F0ifn" id="fQbx7c7qgI" role="3EZMnx">
          <property role="3F0ifm" value="Second Wires Path:" />
        </node>
        <node concept="3F2HdR" id="fQbx7c7qhd" role="3EZMnx">
          <ref role="1NtTu8" to="b4kx:fQbx7c7qga" resolve="path2" />
          <node concept="2iRfu4" id="fQbx7c7qhf" role="2czzBx" />
          <node concept="2o9xnK" id="fQbx7c7tQf" role="2gpyvW">
            <node concept="3clFbS" id="fQbx7c7tQg" role="2VODD2">
              <node concept="3clFbF" id="fQbx7c7tQn" role="3cqZAp">
                <node concept="Xl_RD" id="fQbx7c7tQm" role="3clFbG">
                  <property role="Xl_RC" value="," />
                </node>
              </node>
            </node>
          </node>
        </node>
      </node>
      <node concept="3F0ifn" id="fQbx7c9BLl" role="3EZMnx" />
      <node concept="3F0ifn" id="fQbx7c9BMK" role="3EZMnx">
        <property role="3F0ifm" value="Coordinates that were touched by wires" />
      </node>
      <node concept="3EZMnI" id="fQbx7c9BPJ" role="3EZMnx">
        <node concept="2iRfu4" id="fQbx7c9BPK" role="2iSdaV" />
        <node concept="3F0ifn" id="fQbx7c9BOe" role="3EZMnx">
          <property role="3F0ifm" value="Wire 1:" />
        </node>
        <node concept="3F2HdR" id="fQbx7c9BRx" role="3EZMnx">
          <ref role="1NtTu8" to="b4kx:fQbx7c9BzW" resolve="path1Coordinates" />
          <node concept="2iRfu4" id="fQbx7c9BRz" role="2czzBx" />
        </node>
      </node>
      <node concept="3EZMnI" id="fQbx7c9BTt" role="3EZMnx">
        <node concept="2iRfu4" id="fQbx7c9BTu" role="2iSdaV" />
        <node concept="3F0ifn" id="fQbx7c9BRH" role="3EZMnx">
          <property role="3F0ifm" value="Wire 2:" />
        </node>
        <node concept="3F2HdR" id="fQbx7c9BVu" role="3EZMnx">
          <ref role="1NtTu8" to="b4kx:fQbx7c9B$6" resolve="path2Coordinates" />
          <node concept="2iRfu4" id="fQbx7c9BVw" role="2czzBx" />
        </node>
      </node>
      <node concept="3F0ifn" id="fQbx7caSyI" role="3EZMnx" />
      <node concept="3F0ifn" id="fQbx7caS$H" role="3EZMnx">
        <property role="3F0ifm" value="Intersection Points" />
      </node>
      <node concept="3F2HdR" id="fQbx7caSCO" role="3EZMnx">
        <ref role="1NtTu8" to="b4kx:fQbx7caSls" resolve="intersectionPoints" />
        <node concept="2iRfu4" id="fQbx7caSEY" role="2czzBx" />
      </node>
      <node concept="3F0ifn" id="fQbx7cbk1a" role="3EZMnx" />
      <node concept="3F0ifn" id="fQbx7cbk3l" role="3EZMnx">
        <property role="3F0ifm" value="Manhattan Distance of the Closes Intersection" />
      </node>
      <node concept="3F0A7n" id="fQbx7cbk7O" role="3EZMnx">
        <ref role="1NtTu8" to="b4kx:fQbx7cbjNY" resolve="closestIntersectionPointDistance" />
      </node>
    </node>
    <node concept="3EZMnI" id="fQbx7c87ws" role="6VMZX">
      <node concept="2iRkQZ" id="fQbx7c87wt" role="2iSdaV" />
      <node concept="3EZMnI" id="fQbx7c87wE" role="3EZMnx">
        <node concept="2iRfu4" id="fQbx7c87wF" role="2iSdaV" />
        <node concept="3F0ifn" id="fQbx7c87wS" role="3EZMnx">
          <property role="3F0ifm" value="Path 1 as string:" />
        </node>
        <node concept="3F0A7n" id="fQbx7c87x4" role="3EZMnx">
          <ref role="1NtTu8" to="b4kx:fQbx7c87jb" resolve="path1String" />
        </node>
      </node>
      <node concept="3EZMnI" id="fQbx7c87xs" role="3EZMnx">
        <node concept="2iRfu4" id="fQbx7c87xt" role="2iSdaV" />
        <node concept="3F0ifn" id="fQbx7c87xc" role="3EZMnx">
          <property role="3F0ifm" value="Path 2 as string:" />
        </node>
        <node concept="3F0A7n" id="fQbx7c87xX" role="3EZMnx">
          <ref role="1NtTu8" to="b4kx:fQbx7c87jf" resolve="path2String" />
        </node>
      </node>
    </node>
  </node>
  <node concept="24kQdi" id="fQbx7c7oP$">
    <ref role="1XX52x" to="b4kx:fQbx7c7oP8" resolve="IDirection" />
    <node concept="3EZMnI" id="fQbx7c7oPA" role="2wV5jI">
      <node concept="2iRfu4" id="fQbx7c7oPB" role="2iSdaV" />
      <node concept="PMmxH" id="fQbx7c7oPG" role="3EZMnx">
        <ref role="PMmxG" to="tpco:2wZex4PafBj" resolve="alias" />
      </node>
      <node concept="3F0A7n" id="fQbx7c7oPL" role="3EZMnx">
        <ref role="1NtTu8" to="b4kx:fQbx7c7oP9" resolve="steps" />
        <node concept="11L4FC" id="fQbx7c7rX2" role="3F10Kt">
          <property role="VOm3f" value="true" />
        </node>
      </node>
    </node>
  </node>
  <node concept="3p36aQ" id="fQbx7c7yrU">
    <ref role="aqKnT" to="b4kx:fQbx7c7oP8" resolve="IDirection" />
    <node concept="2F$Pav" id="fQbx7c7yrV" role="3ft7WO">
      <node concept="3eGOop" id="fQbx7c7FJx" role="2$S_pN">
        <node concept="ucgPf" id="fQbx7c7FJz" role="3aKz83">
          <node concept="3clFbS" id="fQbx7c7FJ_" role="2VODD2">
            <node concept="3cpWs8" id="fQbx7c7G1T" role="3cqZAp">
              <node concept="3cpWsn" id="fQbx7c7G1W" role="3cpWs9">
                <property role="TrG5h" value="newInitialized" />
                <node concept="3Tqbb2" id="fQbx7c7G1S" role="1tU5fm">
                  <ref role="ehGHo" to="b4kx:fQbx7c7oP8" resolve="IDirection" />
                </node>
                <node concept="2OqwBi" id="fQbx7c7GFO" role="33vP2m">
                  <node concept="2ZBlsa" id="fQbx7c7Gqa" role="2Oq$k0" />
                  <node concept="LFhST" id="fQbx7c7KRC" role="2OqNvi" />
                </node>
              </node>
            </node>
            <node concept="3cpWs6" id="fQbx7c7L59" role="3cqZAp">
              <node concept="37vLTw" id="fQbx7c7MDc" role="3cqZAk">
                <ref role="3cqZAo" node="fQbx7c7G1W" resolve="newInitialized" />
              </node>
            </node>
          </node>
        </node>
        <node concept="16NfWO" id="fQbx7c7MJZ" role="upBLP">
          <node concept="uGdhv" id="fQbx7c7MQP" role="16NeZM">
            <node concept="3clFbS" id="fQbx7c7MQR" role="2VODD2">
              <node concept="3clFbF" id="fQbx7c7MZz" role="3cqZAp">
                <node concept="2OqwBi" id="fQbx7c7Nmj" role="3clFbG">
                  <node concept="2ZBlsa" id="fQbx7c7MZy" role="2Oq$k0" />
                  <node concept="3n3YKJ" id="fQbx7c7NVn" role="2OqNvi" />
                </node>
              </node>
            </node>
          </node>
        </node>
        <node concept="16NL0t" id="fQbx7c7Otv" role="upBLP">
          <node concept="uGdhv" id="fQbx7c7OH_" role="16NL0q">
            <node concept="3clFbS" id="fQbx7c7OHB" role="2VODD2">
              <node concept="3clFbF" id="fQbx7c7OQj" role="3cqZAp">
                <node concept="2OqwBi" id="fQbx7c7Pd3" role="3clFbG">
                  <node concept="2ZBlsa" id="fQbx7c7OQi" role="2Oq$k0" />
                  <node concept="liA8E" id="fQbx7c7PM7" role="2OqNvi">
                    <ref role="37wK5l" to="c17a:~SAbstractConcept.getName()" resolve="getName" />
                  </node>
                </node>
              </node>
            </node>
          </node>
        </node>
      </node>
      <node concept="3bZ5Sz" id="fQbx7c7A9K" role="2ZBHrp">
        <ref role="3bZ5Sy" to="b4kx:fQbx7c7oP8" resolve="IDirection" />
      </node>
      <node concept="2$S_p_" id="fQbx7c7ys4" role="2$S_pT">
        <node concept="3clFbS" id="fQbx7c7ys5" role="2VODD2">
          <node concept="3clFbF" id="fQbx7c7AeD" role="3cqZAp">
            <node concept="2OqwBi" id="fQbx7c7Cu1" role="3clFbG">
              <node concept="2OqwBi" id="fQbx7c7ALz" role="2Oq$k0">
                <node concept="35c_gC" id="fQbx7c7AeC" role="2Oq$k0">
                  <ref role="35c_gD" to="b4kx:fQbx7c7oP8" resolve="IDirection" />
                </node>
                <node concept="LSoRf" id="fQbx7c7Br1" role="2OqNvi">
                  <node concept="1rpKSd" id="fQbx7c7BEn" role="1iTxcG" />
                </node>
              </node>
              <node concept="3zZkjj" id="fQbx7c7E2b" role="2OqNvi">
                <node concept="1bVj0M" id="fQbx7c7E2d" role="23t8la">
                  <node concept="3clFbS" id="fQbx7c7E2e" role="1bW5cS">
                    <node concept="3clFbF" id="fQbx7c7EhX" role="3cqZAp">
                      <node concept="3fqX7Q" id="fQbx7c7EhV" role="3clFbG">
                        <node concept="2OqwBi" id="fQbx7c7EW9" role="3fr31v">
                          <node concept="37vLTw" id="fQbx7c7EBs" role="2Oq$k0">
                            <ref role="3cqZAo" node="fQbx7c7E2f" resolve="it" />
                          </node>
                          <node concept="liA8E" id="fQbx7c7Fv7" role="2OqNvi">
                            <ref role="37wK5l" to="c17a:~SAbstractConcept.isAbstract()" resolve="isAbstract" />
                          </node>
                        </node>
                      </node>
                    </node>
                  </node>
                  <node concept="Rh6nW" id="fQbx7c7E2f" role="1bW2Oz">
                    <property role="TrG5h" value="it" />
                    <node concept="2jxLKc" id="fQbx7c7E2g" role="1tU5fm" />
                  </node>
                </node>
              </node>
            </node>
          </node>
        </node>
      </node>
    </node>
    <node concept="3VyMlK" id="fQbx7c7O4m" role="3ft7WO" />
  </node>
  <node concept="24kQdi" id="fQbx7c9y7$">
    <ref role="1XX52x" to="b4kx:fQbx7c9y6Z" resolve="Coordinate" />
    <node concept="3EZMnI" id="fQbx7c9y7A" role="2wV5jI">
      <node concept="2iRfu4" id="fQbx7c9y7B" role="2iSdaV" />
      <node concept="3F0ifn" id="fQbx7c9y7O" role="3EZMnx">
        <property role="3F0ifm" value="(" />
      </node>
      <node concept="3F0A7n" id="fQbx7c9y80" role="3EZMnx">
        <ref role="1NtTu8" to="b4kx:fQbx7c9y70" resolve="x" />
      </node>
      <node concept="3F0ifn" id="fQbx7c9y8i" role="3EZMnx">
        <property role="3F0ifm" value="," />
      </node>
      <node concept="3F0A7n" id="fQbx7c9y8E" role="3EZMnx">
        <ref role="1NtTu8" to="b4kx:fQbx7c9y74" resolve="y" />
      </node>
      <node concept="3F0ifn" id="fQbx7c9y98" role="3EZMnx">
        <property role="3F0ifm" value=")" />
      </node>
    </node>
  </node>
</model>

