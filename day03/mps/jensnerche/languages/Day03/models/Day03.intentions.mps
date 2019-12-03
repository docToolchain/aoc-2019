<?xml version="1.0" encoding="UTF-8"?>
<model ref="r:ee74e37e-85b8-4ee2-8ce1-be821a9c7ed7(Day03.intentions)">
  <persistence version="9" />
  <languages>
    <use id="d7a92d38-f7db-40d0-8431-763b0c3c9f20" name="jetbrains.mps.lang.intentions" version="0" />
    <devkit ref="fbc25dd2-5da4-483a-8b19-70928e1b62d7(jetbrains.mps.devkit.general-purpose)" />
  </languages>
  <imports>
    <import index="wyt6" ref="6354ebe7-c22a-4a0f-ac54-50b52ab9b065/java:java.lang(JDK/)" />
    <import index="b4kx" ref="r:c59fa5b7-130e-45f2-a476-3ba334523617(Day03.structure)" />
    <import index="6hgk" ref="r:e316f4f0-91ba-41ba-9004-e5c0a5e4356e(Day03.util)" />
    <import index="ro4w" ref="r:ca97feae-8c6c-4eed-93e4-9f93f0f44320(Day03.behavior)" implicit="true" />
  </imports>
  <registry>
    <language id="f3061a53-9226-4cc5-a443-f952ceaf5816" name="jetbrains.mps.baseLanguage">
      <concept id="1215693861676" name="jetbrains.mps.baseLanguage.structure.BaseAssignmentExpression" flags="nn" index="d038R">
        <child id="1068498886297" name="rValue" index="37vLTx" />
        <child id="1068498886295" name="lValue" index="37vLTJ" />
      </concept>
      <concept id="1202948039474" name="jetbrains.mps.baseLanguage.structure.InstanceMethodCallOperation" flags="nn" index="liA8E" />
      <concept id="1154032098014" name="jetbrains.mps.baseLanguage.structure.AbstractLoopStatement" flags="nn" index="2LF5Ji">
        <child id="1154032183016" name="body" index="2LFqv$" />
      </concept>
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
      <concept id="1081236700937" name="jetbrains.mps.baseLanguage.structure.StaticMethodCall" flags="nn" index="2YIFZM">
        <reference id="1144433194310" name="classConcept" index="1Pybhc" />
      </concept>
      <concept id="1070534760951" name="jetbrains.mps.baseLanguage.structure.ArrayType" flags="in" index="10Q1$e">
        <child id="1070534760952" name="componentType" index="10Q1$1" />
      </concept>
      <concept id="1068431474542" name="jetbrains.mps.baseLanguage.structure.VariableDeclaration" flags="ng" index="33uBYm">
        <child id="1068431790190" name="initializer" index="33vP2m" />
      </concept>
      <concept id="1068498886296" name="jetbrains.mps.baseLanguage.structure.VariableReference" flags="nn" index="37vLTw">
        <reference id="1068581517664" name="variableDeclaration" index="3cqZAo" />
      </concept>
      <concept id="1068498886294" name="jetbrains.mps.baseLanguage.structure.AssignmentExpression" flags="nn" index="37vLTI" />
      <concept id="4972933694980447171" name="jetbrains.mps.baseLanguage.structure.BaseVariableDeclaration" flags="ng" index="19Szcq">
        <child id="5680397130376446158" name="type" index="1tU5fm" />
      </concept>
      <concept id="1068580123155" name="jetbrains.mps.baseLanguage.structure.ExpressionStatement" flags="nn" index="3clFbF">
        <child id="1068580123156" name="expression" index="3clFbG" />
      </concept>
      <concept id="1068580123157" name="jetbrains.mps.baseLanguage.structure.Statement" flags="nn" index="3clFbH" />
      <concept id="1068580123159" name="jetbrains.mps.baseLanguage.structure.IfStatement" flags="nn" index="3clFbJ">
        <child id="1068580123160" name="condition" index="3clFbw" />
        <child id="1068580123161" name="ifTrue" index="3clFbx" />
      </concept>
      <concept id="1068580123136" name="jetbrains.mps.baseLanguage.structure.StatementList" flags="sn" stub="5293379017992965193" index="3clFbS">
        <child id="1068581517665" name="statement" index="3cqZAp" />
      </concept>
      <concept id="1068580320020" name="jetbrains.mps.baseLanguage.structure.IntegerConstant" flags="nn" index="3cmrfG">
        <property id="1068580320021" name="value" index="3cmrfH" />
      </concept>
      <concept id="1068581242864" name="jetbrains.mps.baseLanguage.structure.LocalVariableDeclarationStatement" flags="nn" index="3cpWs8">
        <child id="1068581242865" name="localVariableDeclaration" index="3cpWs9" />
      </concept>
      <concept id="1068581242863" name="jetbrains.mps.baseLanguage.structure.LocalVariableDeclaration" flags="nr" index="3cpWsn" />
      <concept id="1081506773034" name="jetbrains.mps.baseLanguage.structure.LessThanExpression" flags="nn" index="3eOVzh" />
      <concept id="1204053956946" name="jetbrains.mps.baseLanguage.structure.IMethodCall" flags="ng" index="1ndlxa">
        <reference id="1068499141037" name="baseMethodDeclaration" index="37wK5l" />
        <child id="1068499141038" name="actualArgument" index="37wK5m" />
      </concept>
      <concept id="1107535904670" name="jetbrains.mps.baseLanguage.structure.ClassifierType" flags="in" index="3uibUv">
        <reference id="1107535924139" name="classifier" index="3uigEE" />
      </concept>
      <concept id="1081773326031" name="jetbrains.mps.baseLanguage.structure.BinaryOperation" flags="nn" index="3uHJSO">
        <child id="1081773367579" name="rightExpression" index="3uHU7w" />
        <child id="1081773367580" name="leftExpression" index="3uHU7B" />
      </concept>
    </language>
    <language id="d7a92d38-f7db-40d0-8431-763b0c3c9f20" name="jetbrains.mps.lang.intentions">
      <concept id="1192794744107" name="jetbrains.mps.lang.intentions.structure.IntentionDeclaration" flags="ig" index="2S6QgY" />
      <concept id="1192794782375" name="jetbrains.mps.lang.intentions.structure.DescriptionBlock" flags="in" index="2S6ZIM" />
      <concept id="1192795911897" name="jetbrains.mps.lang.intentions.structure.ExecuteBlock" flags="in" index="2Sbjvc" />
      <concept id="1192796902958" name="jetbrains.mps.lang.intentions.structure.ConceptFunctionParameter_node" flags="nn" index="2Sf5sV" />
      <concept id="2522969319638091381" name="jetbrains.mps.lang.intentions.structure.BaseIntentionDeclaration" flags="ig" index="2ZfUlf">
        <reference id="2522969319638198290" name="forConcept" index="2ZfgGC" />
        <child id="2522969319638198291" name="executeFunction" index="2ZfgGD" />
        <child id="2522969319638093993" name="descriptionFunction" index="2ZfVej" />
      </concept>
    </language>
    <language id="7866978e-a0f0-4cc7-81bc-4d213d9375e1" name="jetbrains.mps.lang.smodel">
      <concept id="1179409122411" name="jetbrains.mps.lang.smodel.structure.Node_ConceptMethodCall" flags="nn" index="2qgKlT" />
      <concept id="1138661924179" name="jetbrains.mps.lang.smodel.structure.Property_SetOperation" flags="nn" index="tyxLq">
        <child id="1138662048170" name="value" index="tz02z" />
      </concept>
      <concept id="1180636770613" name="jetbrains.mps.lang.smodel.structure.SNodeCreator" flags="nn" index="3zrR0B">
        <child id="1180636770616" name="createdType" index="3zrR0E" />
      </concept>
      <concept id="1144146199828" name="jetbrains.mps.lang.smodel.structure.Node_CopyOperation" flags="nn" index="1$rogu" />
      <concept id="1138055754698" name="jetbrains.mps.lang.smodel.structure.SNodeType" flags="in" index="3Tqbb2">
        <reference id="1138405853777" name="concept" index="ehGHo" />
      </concept>
      <concept id="1138056022639" name="jetbrains.mps.lang.smodel.structure.SPropertyAccess" flags="nn" index="3TrcHB">
        <reference id="1138056395725" name="property" index="3TsBF5" />
      </concept>
      <concept id="1138056282393" name="jetbrains.mps.lang.smodel.structure.SLinkListAccess" flags="nn" index="3Tsc0h">
        <reference id="1138056546658" name="link" index="3TtcxE" />
      </concept>
    </language>
    <language id="ceab5195-25ea-4f22-9b92-103b95ca8c0c" name="jetbrains.mps.lang.core">
      <concept id="1169194658468" name="jetbrains.mps.lang.core.structure.INamedConcept" flags="ng" index="TrEIO">
        <property id="1169194664001" name="name" index="TrG5h" />
      </concept>
    </language>
    <language id="83888646-71ce-4f1c-9c53-c54016f6ad4f" name="jetbrains.mps.baseLanguage.collections">
      <concept id="540871147943773365" name="jetbrains.mps.baseLanguage.collections.structure.SingleArgumentSequenceOperation" flags="nn" index="25WWJ4">
        <child id="540871147943773366" name="argument" index="25WWJ7" />
      </concept>
      <concept id="1153943597977" name="jetbrains.mps.baseLanguage.collections.structure.ForEachStatement" flags="nn" index="2Gpval">
        <child id="1153944400369" name="variable" index="2Gsz3X" />
        <child id="1153944424730" name="inputSequence" index="2GsD0m" />
      </concept>
      <concept id="1153944193378" name="jetbrains.mps.baseLanguage.collections.structure.ForEachVariable" flags="nr" index="2GrKxI" />
      <concept id="1153944233411" name="jetbrains.mps.baseLanguage.collections.structure.ForEachVariableReference" flags="nn" index="2GrUjf">
        <reference id="1153944258490" name="variable" index="2Gs0qQ" />
      </concept>
      <concept id="1227022210526" name="jetbrains.mps.baseLanguage.collections.structure.ClearAllElementsOperation" flags="nn" index="2Kehj3" />
      <concept id="1160612413312" name="jetbrains.mps.baseLanguage.collections.structure.AddElementOperation" flags="nn" index="TSZUe" />
    </language>
  </registry>
  <node concept="2S6QgY" id="fQbx7c8dpC">
    <property role="TrG5h" value="ConvertPathStringsToPrograms" />
    <ref role="2ZfgGC" to="b4kx:fQbx7c7mKt" resolve="Grid" />
    <node concept="2S6ZIM" id="fQbx7c8dpD" role="2ZfVej">
      <node concept="3clFbS" id="fQbx7c8dpE" role="2VODD2">
        <node concept="3clFbF" id="fQbx7c8dyT" role="3cqZAp">
          <node concept="Xl_RD" id="fQbx7c8dyS" role="3clFbG">
            <property role="Xl_RC" value="Convert Path Strings To Program" />
          </node>
        </node>
      </node>
    </node>
    <node concept="2Sbjvc" id="fQbx7c8dpF" role="2ZfgGD">
      <node concept="3clFbS" id="fQbx7c8dpG" role="2VODD2">
        <node concept="3clFbF" id="fQbx7c8hy1" role="3cqZAp">
          <node concept="2OqwBi" id="fQbx7c8jkk" role="3clFbG">
            <node concept="2OqwBi" id="fQbx7c8hF3" role="2Oq$k0">
              <node concept="2Sf5sV" id="fQbx7c8hxZ" role="2Oq$k0" />
              <node concept="3Tsc0h" id="fQbx7c8hQX" role="2OqNvi">
                <ref role="3TtcxE" to="b4kx:fQbx7c7qg8" resolve="path1" />
              </node>
            </node>
            <node concept="2Kehj3" id="fQbx7c8lt$" role="2OqNvi" />
          </node>
        </node>
        <node concept="3clFbF" id="fQbx7c8p1X" role="3cqZAp">
          <node concept="2OqwBi" id="fQbx7c8su5" role="3clFbG">
            <node concept="2OqwBi" id="fQbx7c8qSi" role="2Oq$k0">
              <node concept="2Sf5sV" id="fQbx7c8p1V" role="2Oq$k0" />
              <node concept="3Tsc0h" id="fQbx7c8rec" role="2OqNvi">
                <ref role="3TtcxE" to="b4kx:fQbx7c7qga" resolve="path2" />
              </node>
            </node>
            <node concept="2Kehj3" id="fQbx7c8uBl" role="2OqNvi" />
          </node>
        </node>
        <node concept="3clFbH" id="fQbx7c8wvI" role="3cqZAp" />
        <node concept="3cpWs8" id="fQbx7c8gvm" role="3cqZAp">
          <node concept="3cpWsn" id="fQbx7c8gvn" role="3cpWs9">
            <property role="TrG5h" value="path1Parts" />
            <node concept="10Q1$e" id="fQbx7c8gvg" role="1tU5fm">
              <node concept="3uibUv" id="fQbx7c8gvj" role="10Q1$1">
                <ref role="3uigEE" to="wyt6:~String" resolve="String" />
              </node>
            </node>
            <node concept="2OqwBi" id="fQbx7c8gvo" role="33vP2m">
              <node concept="2OqwBi" id="fQbx7c8gvp" role="2Oq$k0">
                <node concept="2Sf5sV" id="fQbx7c8gvq" role="2Oq$k0" />
                <node concept="3TrcHB" id="fQbx7c8gvr" role="2OqNvi">
                  <ref role="3TsBF5" to="b4kx:fQbx7c87jb" resolve="path1String" />
                </node>
              </node>
              <node concept="liA8E" id="fQbx7c8gvs" role="2OqNvi">
                <ref role="37wK5l" to="wyt6:~String.split(java.lang.String)" resolve="split" />
                <node concept="Xl_RD" id="fQbx7c8gvt" role="37wK5m">
                  <property role="Xl_RC" value="," />
                </node>
              </node>
            </node>
          </node>
        </node>
        <node concept="2Gpval" id="fQbx7c8hoQ" role="3cqZAp">
          <node concept="2GrKxI" id="fQbx7c8hoS" role="2Gsz3X">
            <property role="TrG5h" value="part" />
          </node>
          <node concept="37vLTw" id="fQbx7c8htb" role="2GsD0m">
            <ref role="3cqZAo" node="fQbx7c8gvn" resolve="path1Parts" />
          </node>
          <node concept="3clFbS" id="fQbx7c8hoW" role="2LFqv$">
            <node concept="3clFbJ" id="fQbx7c8$30" role="3cqZAp">
              <node concept="2OqwBi" id="fQbx7c8$pi" role="3clFbw">
                <node concept="2GrUjf" id="fQbx7c8$3x" role="2Oq$k0">
                  <ref role="2Gs0qQ" node="fQbx7c8hoS" resolve="part" />
                </node>
                <node concept="liA8E" id="fQbx7c8$XI" role="2OqNvi">
                  <ref role="37wK5l" to="wyt6:~String.startsWith(java.lang.String)" resolve="startsWith" />
                  <node concept="Xl_RD" id="fQbx7c8$YF" role="37wK5m">
                    <property role="Xl_RC" value="R" />
                  </node>
                </node>
              </node>
              <node concept="3clFbS" id="fQbx7c8$32" role="3clFbx">
                <node concept="3cpWs8" id="fQbx7c8_0h" role="3cqZAp">
                  <node concept="3cpWsn" id="fQbx7c8_0k" role="3cpWs9">
                    <property role="TrG5h" value="right" />
                    <node concept="3Tqbb2" id="fQbx7c8_0g" role="1tU5fm">
                      <ref role="ehGHo" to="b4kx:fQbx7c7oP8" resolve="IDirection" />
                    </node>
                    <node concept="2ShNRf" id="fQbx7c8_1H" role="33vP2m">
                      <node concept="3zrR0B" id="fQbx7c8_8k" role="2ShVmc">
                        <node concept="3Tqbb2" id="fQbx7c8_8m" role="3zrR0E">
                          <ref role="ehGHo" to="b4kx:fQbx7c7qg5" resolve="Right" />
                        </node>
                      </node>
                    </node>
                  </node>
                </node>
                <node concept="3clFbF" id="fQbx7c8Cmd" role="3cqZAp">
                  <node concept="2OqwBi" id="fQbx7c8E6s" role="3clFbG">
                    <node concept="2OqwBi" id="fQbx7c8Cy4" role="2Oq$k0">
                      <node concept="2Sf5sV" id="fQbx7c8Cmb" role="2Oq$k0" />
                      <node concept="3Tsc0h" id="fQbx7c8CQz" role="2OqNvi">
                        <ref role="3TtcxE" to="b4kx:fQbx7c7qg8" resolve="path1" />
                      </node>
                    </node>
                    <node concept="TSZUe" id="fQbx7c8GfG" role="2OqNvi">
                      <node concept="37vLTw" id="fQbx7c8Gsv" role="25WWJ7">
                        <ref role="3cqZAo" node="fQbx7c8_0k" resolve="right" />
                      </node>
                    </node>
                  </node>
                </node>
                <node concept="3clFbF" id="fQbx7c8_a1" role="3cqZAp">
                  <node concept="2OqwBi" id="fQbx7c8AkF" role="3clFbG">
                    <node concept="2OqwBi" id="fQbx7c8_hs" role="2Oq$k0">
                      <node concept="37vLTw" id="fQbx7c8_9Z" role="2Oq$k0">
                        <ref role="3cqZAo" node="fQbx7c8_0k" resolve="right" />
                      </node>
                      <node concept="3TrcHB" id="fQbx7c8_oA" role="2OqNvi">
                        <ref role="3TsBF5" to="b4kx:fQbx7c7oP9" resolve="steps" />
                      </node>
                    </node>
                    <node concept="tyxLq" id="fQbx7c8AMH" role="2OqNvi">
                      <node concept="2YIFZM" id="fQbx7c8C1e" role="tz02z">
                        <ref role="37wK5l" to="wyt6:~Integer.valueOf(java.lang.String)" resolve="valueOf" />
                        <ref role="1Pybhc" to="wyt6:~Integer" resolve="Integer" />
                        <node concept="2OqwBi" id="fQbx7c8Bah" role="37wK5m">
                          <node concept="2GrUjf" id="fQbx7c8AOB" role="2Oq$k0">
                            <ref role="2Gs0qQ" node="fQbx7c8hoS" resolve="part" />
                          </node>
                          <node concept="liA8E" id="fQbx7c8BKx" role="2OqNvi">
                            <ref role="37wK5l" to="wyt6:~String.substring(int)" resolve="substring" />
                            <node concept="3cmrfG" id="fQbx7c8BQo" role="37wK5m">
                              <property role="3cmrfH" value="1" />
                            </node>
                          </node>
                        </node>
                      </node>
                    </node>
                  </node>
                </node>
              </node>
            </node>
            <node concept="3clFbJ" id="fQbx7c8GE3" role="3cqZAp">
              <node concept="2OqwBi" id="fQbx7c8GE4" role="3clFbw">
                <node concept="2GrUjf" id="fQbx7c8GE5" role="2Oq$k0">
                  <ref role="2Gs0qQ" node="fQbx7c8hoS" resolve="part" />
                </node>
                <node concept="liA8E" id="fQbx7c8GE6" role="2OqNvi">
                  <ref role="37wK5l" to="wyt6:~String.startsWith(java.lang.String)" resolve="startsWith" />
                  <node concept="Xl_RD" id="fQbx7c8GE7" role="37wK5m">
                    <property role="Xl_RC" value="L" />
                  </node>
                </node>
              </node>
              <node concept="3clFbS" id="fQbx7c8GE8" role="3clFbx">
                <node concept="3cpWs8" id="fQbx7c8GE9" role="3cqZAp">
                  <node concept="3cpWsn" id="fQbx7c8GEa" role="3cpWs9">
                    <property role="TrG5h" value="left" />
                    <node concept="3Tqbb2" id="fQbx7c8GEb" role="1tU5fm">
                      <ref role="ehGHo" to="b4kx:fQbx7c7oP8" resolve="IDirection" />
                    </node>
                    <node concept="2ShNRf" id="fQbx7c8GEc" role="33vP2m">
                      <node concept="3zrR0B" id="fQbx7c8GEd" role="2ShVmc">
                        <node concept="3Tqbb2" id="fQbx7c8GEe" role="3zrR0E">
                          <ref role="ehGHo" to="b4kx:fQbx7c7qg2" resolve="Left" />
                        </node>
                      </node>
                    </node>
                  </node>
                </node>
                <node concept="3clFbF" id="fQbx7c8GEf" role="3cqZAp">
                  <node concept="2OqwBi" id="fQbx7c8GEg" role="3clFbG">
                    <node concept="2OqwBi" id="fQbx7c8GEh" role="2Oq$k0">
                      <node concept="2Sf5sV" id="fQbx7c8GEi" role="2Oq$k0" />
                      <node concept="3Tsc0h" id="fQbx7c8GEj" role="2OqNvi">
                        <ref role="3TtcxE" to="b4kx:fQbx7c7qg8" resolve="path1" />
                      </node>
                    </node>
                    <node concept="TSZUe" id="fQbx7c8GEk" role="2OqNvi">
                      <node concept="37vLTw" id="fQbx7c8GEl" role="25WWJ7">
                        <ref role="3cqZAo" node="fQbx7c8GEa" resolve="left" />
                      </node>
                    </node>
                  </node>
                </node>
                <node concept="3clFbF" id="fQbx7c8GEm" role="3cqZAp">
                  <node concept="2OqwBi" id="fQbx7c8GEn" role="3clFbG">
                    <node concept="2OqwBi" id="fQbx7c8GEo" role="2Oq$k0">
                      <node concept="37vLTw" id="fQbx7c8GEp" role="2Oq$k0">
                        <ref role="3cqZAo" node="fQbx7c8GEa" resolve="left" />
                      </node>
                      <node concept="3TrcHB" id="fQbx7c8GEq" role="2OqNvi">
                        <ref role="3TsBF5" to="b4kx:fQbx7c7oP9" resolve="steps" />
                      </node>
                    </node>
                    <node concept="tyxLq" id="fQbx7c8GEr" role="2OqNvi">
                      <node concept="2YIFZM" id="fQbx7c8GEs" role="tz02z">
                        <ref role="37wK5l" to="wyt6:~Integer.valueOf(java.lang.String)" resolve="valueOf" />
                        <ref role="1Pybhc" to="wyt6:~Integer" resolve="Integer" />
                        <node concept="2OqwBi" id="fQbx7c8GEt" role="37wK5m">
                          <node concept="2GrUjf" id="fQbx7c8GEu" role="2Oq$k0">
                            <ref role="2Gs0qQ" node="fQbx7c8hoS" resolve="part" />
                          </node>
                          <node concept="liA8E" id="fQbx7c8GEv" role="2OqNvi">
                            <ref role="37wK5l" to="wyt6:~String.substring(int)" resolve="substring" />
                            <node concept="3cmrfG" id="fQbx7c8GEw" role="37wK5m">
                              <property role="3cmrfH" value="1" />
                            </node>
                          </node>
                        </node>
                      </node>
                    </node>
                  </node>
                </node>
              </node>
            </node>
            <node concept="3clFbJ" id="fQbx7c8IB8" role="3cqZAp">
              <node concept="2OqwBi" id="fQbx7c8IB9" role="3clFbw">
                <node concept="2GrUjf" id="fQbx7c8IBa" role="2Oq$k0">
                  <ref role="2Gs0qQ" node="fQbx7c8hoS" resolve="part" />
                </node>
                <node concept="liA8E" id="fQbx7c8IBb" role="2OqNvi">
                  <ref role="37wK5l" to="wyt6:~String.startsWith(java.lang.String)" resolve="startsWith" />
                  <node concept="Xl_RD" id="fQbx7c8IBc" role="37wK5m">
                    <property role="Xl_RC" value="U" />
                  </node>
                </node>
              </node>
              <node concept="3clFbS" id="fQbx7c8IBd" role="3clFbx">
                <node concept="3cpWs8" id="fQbx7c8IBe" role="3cqZAp">
                  <node concept="3cpWsn" id="fQbx7c8IBf" role="3cpWs9">
                    <property role="TrG5h" value="up" />
                    <node concept="3Tqbb2" id="fQbx7c8IBg" role="1tU5fm">
                      <ref role="ehGHo" to="b4kx:fQbx7c7oP8" resolve="IDirection" />
                    </node>
                    <node concept="2ShNRf" id="fQbx7c8IBh" role="33vP2m">
                      <node concept="3zrR0B" id="fQbx7c8IBi" role="2ShVmc">
                        <node concept="3Tqbb2" id="fQbx7c8IBj" role="3zrR0E">
                          <ref role="ehGHo" to="b4kx:fQbx7c7qfW" resolve="Up" />
                        </node>
                      </node>
                    </node>
                  </node>
                </node>
                <node concept="3clFbF" id="fQbx7c8IBk" role="3cqZAp">
                  <node concept="2OqwBi" id="fQbx7c8IBl" role="3clFbG">
                    <node concept="2OqwBi" id="fQbx7c8IBm" role="2Oq$k0">
                      <node concept="2Sf5sV" id="fQbx7c8IBn" role="2Oq$k0" />
                      <node concept="3Tsc0h" id="fQbx7c8IBo" role="2OqNvi">
                        <ref role="3TtcxE" to="b4kx:fQbx7c7qg8" resolve="path1" />
                      </node>
                    </node>
                    <node concept="TSZUe" id="fQbx7c8IBp" role="2OqNvi">
                      <node concept="37vLTw" id="fQbx7c8IBq" role="25WWJ7">
                        <ref role="3cqZAo" node="fQbx7c8IBf" resolve="up" />
                      </node>
                    </node>
                  </node>
                </node>
                <node concept="3clFbF" id="fQbx7c8IBr" role="3cqZAp">
                  <node concept="2OqwBi" id="fQbx7c8IBs" role="3clFbG">
                    <node concept="2OqwBi" id="fQbx7c8IBt" role="2Oq$k0">
                      <node concept="37vLTw" id="fQbx7c8IBu" role="2Oq$k0">
                        <ref role="3cqZAo" node="fQbx7c8IBf" resolve="up" />
                      </node>
                      <node concept="3TrcHB" id="fQbx7c8IBv" role="2OqNvi">
                        <ref role="3TsBF5" to="b4kx:fQbx7c7oP9" resolve="steps" />
                      </node>
                    </node>
                    <node concept="tyxLq" id="fQbx7c8IBw" role="2OqNvi">
                      <node concept="2YIFZM" id="fQbx7c8IBx" role="tz02z">
                        <ref role="37wK5l" to="wyt6:~Integer.valueOf(java.lang.String)" resolve="valueOf" />
                        <ref role="1Pybhc" to="wyt6:~Integer" resolve="Integer" />
                        <node concept="2OqwBi" id="fQbx7c8IBy" role="37wK5m">
                          <node concept="2GrUjf" id="fQbx7c8IBz" role="2Oq$k0">
                            <ref role="2Gs0qQ" node="fQbx7c8hoS" resolve="part" />
                          </node>
                          <node concept="liA8E" id="fQbx7c8IB$" role="2OqNvi">
                            <ref role="37wK5l" to="wyt6:~String.substring(int)" resolve="substring" />
                            <node concept="3cmrfG" id="fQbx7c8IB_" role="37wK5m">
                              <property role="3cmrfH" value="1" />
                            </node>
                          </node>
                        </node>
                      </node>
                    </node>
                  </node>
                </node>
              </node>
            </node>
            <node concept="3clFbJ" id="fQbx7c8K8E" role="3cqZAp">
              <node concept="2OqwBi" id="fQbx7c8K8F" role="3clFbw">
                <node concept="2GrUjf" id="fQbx7c8K8G" role="2Oq$k0">
                  <ref role="2Gs0qQ" node="fQbx7c8hoS" resolve="part" />
                </node>
                <node concept="liA8E" id="fQbx7c8K8H" role="2OqNvi">
                  <ref role="37wK5l" to="wyt6:~String.startsWith(java.lang.String)" resolve="startsWith" />
                  <node concept="Xl_RD" id="fQbx7c8K8I" role="37wK5m">
                    <property role="Xl_RC" value="D" />
                  </node>
                </node>
              </node>
              <node concept="3clFbS" id="fQbx7c8K8J" role="3clFbx">
                <node concept="3cpWs8" id="fQbx7c8K8K" role="3cqZAp">
                  <node concept="3cpWsn" id="fQbx7c8K8L" role="3cpWs9">
                    <property role="TrG5h" value="down" />
                    <node concept="3Tqbb2" id="fQbx7c8K8M" role="1tU5fm">
                      <ref role="ehGHo" to="b4kx:fQbx7c7oP8" resolve="IDirection" />
                    </node>
                    <node concept="2ShNRf" id="fQbx7c8K8N" role="33vP2m">
                      <node concept="3zrR0B" id="fQbx7c8K8O" role="2ShVmc">
                        <node concept="3Tqbb2" id="fQbx7c8K8P" role="3zrR0E">
                          <ref role="ehGHo" to="b4kx:fQbx7c7qfZ" resolve="Down" />
                        </node>
                      </node>
                    </node>
                  </node>
                </node>
                <node concept="3clFbF" id="fQbx7c8K8Q" role="3cqZAp">
                  <node concept="2OqwBi" id="fQbx7c8K8R" role="3clFbG">
                    <node concept="2OqwBi" id="fQbx7c8K8S" role="2Oq$k0">
                      <node concept="2Sf5sV" id="fQbx7c8K8T" role="2Oq$k0" />
                      <node concept="3Tsc0h" id="fQbx7c8K8U" role="2OqNvi">
                        <ref role="3TtcxE" to="b4kx:fQbx7c7qg8" resolve="path1" />
                      </node>
                    </node>
                    <node concept="TSZUe" id="fQbx7c8K8V" role="2OqNvi">
                      <node concept="37vLTw" id="fQbx7c8K8W" role="25WWJ7">
                        <ref role="3cqZAo" node="fQbx7c8K8L" resolve="down" />
                      </node>
                    </node>
                  </node>
                </node>
                <node concept="3clFbF" id="fQbx7c8K8X" role="3cqZAp">
                  <node concept="2OqwBi" id="fQbx7c8K8Y" role="3clFbG">
                    <node concept="2OqwBi" id="fQbx7c8K8Z" role="2Oq$k0">
                      <node concept="37vLTw" id="fQbx7c8K90" role="2Oq$k0">
                        <ref role="3cqZAo" node="fQbx7c8K8L" resolve="down" />
                      </node>
                      <node concept="3TrcHB" id="fQbx7c8K91" role="2OqNvi">
                        <ref role="3TsBF5" to="b4kx:fQbx7c7oP9" resolve="steps" />
                      </node>
                    </node>
                    <node concept="tyxLq" id="fQbx7c8K92" role="2OqNvi">
                      <node concept="2YIFZM" id="fQbx7c8K93" role="tz02z">
                        <ref role="37wK5l" to="wyt6:~Integer.valueOf(java.lang.String)" resolve="valueOf" />
                        <ref role="1Pybhc" to="wyt6:~Integer" resolve="Integer" />
                        <node concept="2OqwBi" id="fQbx7c8K94" role="37wK5m">
                          <node concept="2GrUjf" id="fQbx7c8K95" role="2Oq$k0">
                            <ref role="2Gs0qQ" node="fQbx7c8hoS" resolve="part" />
                          </node>
                          <node concept="liA8E" id="fQbx7c8K96" role="2OqNvi">
                            <ref role="37wK5l" to="wyt6:~String.substring(int)" resolve="substring" />
                            <node concept="3cmrfG" id="fQbx7c8K97" role="37wK5m">
                              <property role="3cmrfH" value="1" />
                            </node>
                          </node>
                        </node>
                      </node>
                    </node>
                  </node>
                </node>
              </node>
            </node>
          </node>
        </node>
        <node concept="3clFbH" id="fQbx7c8WYj" role="3cqZAp" />
        <node concept="3cpWs8" id="fQbx7c95y0" role="3cqZAp">
          <node concept="3cpWsn" id="fQbx7c95y1" role="3cpWs9">
            <property role="TrG5h" value="path2Parts" />
            <node concept="10Q1$e" id="fQbx7c95y2" role="1tU5fm">
              <node concept="3uibUv" id="fQbx7c95y3" role="10Q1$1">
                <ref role="3uigEE" to="wyt6:~String" resolve="String" />
              </node>
            </node>
            <node concept="2OqwBi" id="fQbx7c95y4" role="33vP2m">
              <node concept="2OqwBi" id="fQbx7c95y5" role="2Oq$k0">
                <node concept="2Sf5sV" id="fQbx7c95y6" role="2Oq$k0" />
                <node concept="3TrcHB" id="fQbx7c9a2h" role="2OqNvi">
                  <ref role="3TsBF5" to="b4kx:fQbx7c87jf" resolve="path2String" />
                </node>
              </node>
              <node concept="liA8E" id="fQbx7c95y8" role="2OqNvi">
                <ref role="37wK5l" to="wyt6:~String.split(java.lang.String)" resolve="split" />
                <node concept="Xl_RD" id="fQbx7c95y9" role="37wK5m">
                  <property role="Xl_RC" value="," />
                </node>
              </node>
            </node>
          </node>
        </node>
        <node concept="2Gpval" id="fQbx7c9a5x" role="3cqZAp">
          <node concept="2GrKxI" id="fQbx7c9a5y" role="2Gsz3X">
            <property role="TrG5h" value="part" />
          </node>
          <node concept="37vLTw" id="fQbx7c9fcu" role="2GsD0m">
            <ref role="3cqZAo" node="fQbx7c95y1" resolve="path2Parts" />
          </node>
          <node concept="3clFbS" id="fQbx7c9a5$" role="2LFqv$">
            <node concept="3clFbJ" id="fQbx7c9a5_" role="3cqZAp">
              <node concept="2OqwBi" id="fQbx7c9a5A" role="3clFbw">
                <node concept="2GrUjf" id="fQbx7c9a5B" role="2Oq$k0">
                  <ref role="2Gs0qQ" node="fQbx7c9a5y" resolve="part" />
                </node>
                <node concept="liA8E" id="fQbx7c9a5C" role="2OqNvi">
                  <ref role="37wK5l" to="wyt6:~String.startsWith(java.lang.String)" resolve="startsWith" />
                  <node concept="Xl_RD" id="fQbx7c9a5D" role="37wK5m">
                    <property role="Xl_RC" value="R" />
                  </node>
                </node>
              </node>
              <node concept="3clFbS" id="fQbx7c9a5E" role="3clFbx">
                <node concept="3cpWs8" id="fQbx7c9a5F" role="3cqZAp">
                  <node concept="3cpWsn" id="fQbx7c9a5G" role="3cpWs9">
                    <property role="TrG5h" value="right" />
                    <node concept="3Tqbb2" id="fQbx7c9a5H" role="1tU5fm">
                      <ref role="ehGHo" to="b4kx:fQbx7c7oP8" resolve="IDirection" />
                    </node>
                    <node concept="2ShNRf" id="fQbx7c9a5I" role="33vP2m">
                      <node concept="3zrR0B" id="fQbx7c9a5J" role="2ShVmc">
                        <node concept="3Tqbb2" id="fQbx7c9a5K" role="3zrR0E">
                          <ref role="ehGHo" to="b4kx:fQbx7c7qg5" resolve="Right" />
                        </node>
                      </node>
                    </node>
                  </node>
                </node>
                <node concept="3clFbF" id="fQbx7c9a5L" role="3cqZAp">
                  <node concept="2OqwBi" id="fQbx7c9a5M" role="3clFbG">
                    <node concept="2OqwBi" id="fQbx7c9a5N" role="2Oq$k0">
                      <node concept="2Sf5sV" id="fQbx7c9a5O" role="2Oq$k0" />
                      <node concept="3Tsc0h" id="fQbx7c9gKf" role="2OqNvi">
                        <ref role="3TtcxE" to="b4kx:fQbx7c7qga" resolve="path2" />
                      </node>
                    </node>
                    <node concept="TSZUe" id="fQbx7c9a5Q" role="2OqNvi">
                      <node concept="37vLTw" id="fQbx7c9a5R" role="25WWJ7">
                        <ref role="3cqZAo" node="fQbx7c9a5G" resolve="right" />
                      </node>
                    </node>
                  </node>
                </node>
                <node concept="3clFbF" id="fQbx7c9a5S" role="3cqZAp">
                  <node concept="2OqwBi" id="fQbx7c9a5T" role="3clFbG">
                    <node concept="2OqwBi" id="fQbx7c9a5U" role="2Oq$k0">
                      <node concept="37vLTw" id="fQbx7c9a5V" role="2Oq$k0">
                        <ref role="3cqZAo" node="fQbx7c9a5G" resolve="right" />
                      </node>
                      <node concept="3TrcHB" id="fQbx7c9a5W" role="2OqNvi">
                        <ref role="3TsBF5" to="b4kx:fQbx7c7oP9" resolve="steps" />
                      </node>
                    </node>
                    <node concept="tyxLq" id="fQbx7c9a5X" role="2OqNvi">
                      <node concept="2YIFZM" id="fQbx7c9a5Y" role="tz02z">
                        <ref role="1Pybhc" to="wyt6:~Integer" resolve="Integer" />
                        <ref role="37wK5l" to="wyt6:~Integer.valueOf(java.lang.String)" resolve="valueOf" />
                        <node concept="2OqwBi" id="fQbx7c9a5Z" role="37wK5m">
                          <node concept="2GrUjf" id="fQbx7c9a60" role="2Oq$k0">
                            <ref role="2Gs0qQ" node="fQbx7c9a5y" resolve="part" />
                          </node>
                          <node concept="liA8E" id="fQbx7c9a61" role="2OqNvi">
                            <ref role="37wK5l" to="wyt6:~String.substring(int)" resolve="substring" />
                            <node concept="3cmrfG" id="fQbx7c9a62" role="37wK5m">
                              <property role="3cmrfH" value="1" />
                            </node>
                          </node>
                        </node>
                      </node>
                    </node>
                  </node>
                </node>
              </node>
            </node>
            <node concept="3clFbJ" id="fQbx7c9a63" role="3cqZAp">
              <node concept="2OqwBi" id="fQbx7c9a64" role="3clFbw">
                <node concept="2GrUjf" id="fQbx7c9a65" role="2Oq$k0">
                  <ref role="2Gs0qQ" node="fQbx7c9a5y" resolve="part" />
                </node>
                <node concept="liA8E" id="fQbx7c9a66" role="2OqNvi">
                  <ref role="37wK5l" to="wyt6:~String.startsWith(java.lang.String)" resolve="startsWith" />
                  <node concept="Xl_RD" id="fQbx7c9a67" role="37wK5m">
                    <property role="Xl_RC" value="L" />
                  </node>
                </node>
              </node>
              <node concept="3clFbS" id="fQbx7c9a68" role="3clFbx">
                <node concept="3cpWs8" id="fQbx7c9a69" role="3cqZAp">
                  <node concept="3cpWsn" id="fQbx7c9a6a" role="3cpWs9">
                    <property role="TrG5h" value="left" />
                    <node concept="3Tqbb2" id="fQbx7c9a6b" role="1tU5fm">
                      <ref role="ehGHo" to="b4kx:fQbx7c7oP8" resolve="IDirection" />
                    </node>
                    <node concept="2ShNRf" id="fQbx7c9a6c" role="33vP2m">
                      <node concept="3zrR0B" id="fQbx7c9a6d" role="2ShVmc">
                        <node concept="3Tqbb2" id="fQbx7c9a6e" role="3zrR0E">
                          <ref role="ehGHo" to="b4kx:fQbx7c7qg2" resolve="Left" />
                        </node>
                      </node>
                    </node>
                  </node>
                </node>
                <node concept="3clFbF" id="fQbx7c9a6f" role="3cqZAp">
                  <node concept="2OqwBi" id="fQbx7c9a6g" role="3clFbG">
                    <node concept="2OqwBi" id="fQbx7c9a6h" role="2Oq$k0">
                      <node concept="2Sf5sV" id="fQbx7c9a6i" role="2Oq$k0" />
                      <node concept="3Tsc0h" id="fQbx7c9heu" role="2OqNvi">
                        <ref role="3TtcxE" to="b4kx:fQbx7c7qga" resolve="path2" />
                      </node>
                    </node>
                    <node concept="TSZUe" id="fQbx7c9a6k" role="2OqNvi">
                      <node concept="37vLTw" id="fQbx7c9a6l" role="25WWJ7">
                        <ref role="3cqZAo" node="fQbx7c9a6a" resolve="left" />
                      </node>
                    </node>
                  </node>
                </node>
                <node concept="3clFbF" id="fQbx7c9a6m" role="3cqZAp">
                  <node concept="2OqwBi" id="fQbx7c9a6n" role="3clFbG">
                    <node concept="2OqwBi" id="fQbx7c9a6o" role="2Oq$k0">
                      <node concept="37vLTw" id="fQbx7c9a6p" role="2Oq$k0">
                        <ref role="3cqZAo" node="fQbx7c9a6a" resolve="left" />
                      </node>
                      <node concept="3TrcHB" id="fQbx7c9a6q" role="2OqNvi">
                        <ref role="3TsBF5" to="b4kx:fQbx7c7oP9" resolve="steps" />
                      </node>
                    </node>
                    <node concept="tyxLq" id="fQbx7c9a6r" role="2OqNvi">
                      <node concept="2YIFZM" id="fQbx7c9a6s" role="tz02z">
                        <ref role="37wK5l" to="wyt6:~Integer.valueOf(java.lang.String)" resolve="valueOf" />
                        <ref role="1Pybhc" to="wyt6:~Integer" resolve="Integer" />
                        <node concept="2OqwBi" id="fQbx7c9a6t" role="37wK5m">
                          <node concept="2GrUjf" id="fQbx7c9a6u" role="2Oq$k0">
                            <ref role="2Gs0qQ" node="fQbx7c9a5y" resolve="part" />
                          </node>
                          <node concept="liA8E" id="fQbx7c9a6v" role="2OqNvi">
                            <ref role="37wK5l" to="wyt6:~String.substring(int)" resolve="substring" />
                            <node concept="3cmrfG" id="fQbx7c9a6w" role="37wK5m">
                              <property role="3cmrfH" value="1" />
                            </node>
                          </node>
                        </node>
                      </node>
                    </node>
                  </node>
                </node>
              </node>
            </node>
            <node concept="3clFbJ" id="fQbx7c9a6x" role="3cqZAp">
              <node concept="2OqwBi" id="fQbx7c9a6y" role="3clFbw">
                <node concept="2GrUjf" id="fQbx7c9a6z" role="2Oq$k0">
                  <ref role="2Gs0qQ" node="fQbx7c9a5y" resolve="part" />
                </node>
                <node concept="liA8E" id="fQbx7c9a6$" role="2OqNvi">
                  <ref role="37wK5l" to="wyt6:~String.startsWith(java.lang.String)" resolve="startsWith" />
                  <node concept="Xl_RD" id="fQbx7c9a6_" role="37wK5m">
                    <property role="Xl_RC" value="U" />
                  </node>
                </node>
              </node>
              <node concept="3clFbS" id="fQbx7c9a6A" role="3clFbx">
                <node concept="3cpWs8" id="fQbx7c9a6B" role="3cqZAp">
                  <node concept="3cpWsn" id="fQbx7c9a6C" role="3cpWs9">
                    <property role="TrG5h" value="up" />
                    <node concept="3Tqbb2" id="fQbx7c9a6D" role="1tU5fm">
                      <ref role="ehGHo" to="b4kx:fQbx7c7oP8" resolve="IDirection" />
                    </node>
                    <node concept="2ShNRf" id="fQbx7c9a6E" role="33vP2m">
                      <node concept="3zrR0B" id="fQbx7c9a6F" role="2ShVmc">
                        <node concept="3Tqbb2" id="fQbx7c9a6G" role="3zrR0E">
                          <ref role="ehGHo" to="b4kx:fQbx7c7qfW" resolve="Up" />
                        </node>
                      </node>
                    </node>
                  </node>
                </node>
                <node concept="3clFbF" id="fQbx7c9a6H" role="3cqZAp">
                  <node concept="2OqwBi" id="fQbx7c9a6I" role="3clFbG">
                    <node concept="2OqwBi" id="fQbx7c9a6J" role="2Oq$k0">
                      <node concept="2Sf5sV" id="fQbx7c9a6K" role="2Oq$k0" />
                      <node concept="3Tsc0h" id="fQbx7c9hFT" role="2OqNvi">
                        <ref role="3TtcxE" to="b4kx:fQbx7c7qga" resolve="path2" />
                      </node>
                    </node>
                    <node concept="TSZUe" id="fQbx7c9a6M" role="2OqNvi">
                      <node concept="37vLTw" id="fQbx7c9a6N" role="25WWJ7">
                        <ref role="3cqZAo" node="fQbx7c9a6C" resolve="up" />
                      </node>
                    </node>
                  </node>
                </node>
                <node concept="3clFbF" id="fQbx7c9a6O" role="3cqZAp">
                  <node concept="2OqwBi" id="fQbx7c9a6P" role="3clFbG">
                    <node concept="2OqwBi" id="fQbx7c9a6Q" role="2Oq$k0">
                      <node concept="37vLTw" id="fQbx7c9a6R" role="2Oq$k0">
                        <ref role="3cqZAo" node="fQbx7c9a6C" resolve="up" />
                      </node>
                      <node concept="3TrcHB" id="fQbx7c9a6S" role="2OqNvi">
                        <ref role="3TsBF5" to="b4kx:fQbx7c7oP9" resolve="steps" />
                      </node>
                    </node>
                    <node concept="tyxLq" id="fQbx7c9a6T" role="2OqNvi">
                      <node concept="2YIFZM" id="fQbx7c9a6U" role="tz02z">
                        <ref role="37wK5l" to="wyt6:~Integer.valueOf(java.lang.String)" resolve="valueOf" />
                        <ref role="1Pybhc" to="wyt6:~Integer" resolve="Integer" />
                        <node concept="2OqwBi" id="fQbx7c9a6V" role="37wK5m">
                          <node concept="2GrUjf" id="fQbx7c9a6W" role="2Oq$k0">
                            <ref role="2Gs0qQ" node="fQbx7c9a5y" resolve="part" />
                          </node>
                          <node concept="liA8E" id="fQbx7c9a6X" role="2OqNvi">
                            <ref role="37wK5l" to="wyt6:~String.substring(int)" resolve="substring" />
                            <node concept="3cmrfG" id="fQbx7c9a6Y" role="37wK5m">
                              <property role="3cmrfH" value="1" />
                            </node>
                          </node>
                        </node>
                      </node>
                    </node>
                  </node>
                </node>
              </node>
            </node>
            <node concept="3clFbJ" id="fQbx7c9a6Z" role="3cqZAp">
              <node concept="2OqwBi" id="fQbx7c9a70" role="3clFbw">
                <node concept="2GrUjf" id="fQbx7c9a71" role="2Oq$k0">
                  <ref role="2Gs0qQ" node="fQbx7c9a5y" resolve="part" />
                </node>
                <node concept="liA8E" id="fQbx7c9a72" role="2OqNvi">
                  <ref role="37wK5l" to="wyt6:~String.startsWith(java.lang.String)" resolve="startsWith" />
                  <node concept="Xl_RD" id="fQbx7c9a73" role="37wK5m">
                    <property role="Xl_RC" value="D" />
                  </node>
                </node>
              </node>
              <node concept="3clFbS" id="fQbx7c9a74" role="3clFbx">
                <node concept="3cpWs8" id="fQbx7c9a75" role="3cqZAp">
                  <node concept="3cpWsn" id="fQbx7c9a76" role="3cpWs9">
                    <property role="TrG5h" value="down" />
                    <node concept="3Tqbb2" id="fQbx7c9a77" role="1tU5fm">
                      <ref role="ehGHo" to="b4kx:fQbx7c7oP8" resolve="IDirection" />
                    </node>
                    <node concept="2ShNRf" id="fQbx7c9a78" role="33vP2m">
                      <node concept="3zrR0B" id="fQbx7c9a79" role="2ShVmc">
                        <node concept="3Tqbb2" id="fQbx7c9a7a" role="3zrR0E">
                          <ref role="ehGHo" to="b4kx:fQbx7c7qfZ" resolve="Down" />
                        </node>
                      </node>
                    </node>
                  </node>
                </node>
                <node concept="3clFbF" id="fQbx7c9a7b" role="3cqZAp">
                  <node concept="2OqwBi" id="fQbx7c9a7c" role="3clFbG">
                    <node concept="2OqwBi" id="fQbx7c9a7d" role="2Oq$k0">
                      <node concept="2Sf5sV" id="fQbx7c9a7e" role="2Oq$k0" />
                      <node concept="3Tsc0h" id="fQbx7c9i9k" role="2OqNvi">
                        <ref role="3TtcxE" to="b4kx:fQbx7c7qga" resolve="path2" />
                      </node>
                    </node>
                    <node concept="TSZUe" id="fQbx7c9a7g" role="2OqNvi">
                      <node concept="37vLTw" id="fQbx7c9a7h" role="25WWJ7">
                        <ref role="3cqZAo" node="fQbx7c9a76" resolve="down" />
                      </node>
                    </node>
                  </node>
                </node>
                <node concept="3clFbF" id="fQbx7c9a7i" role="3cqZAp">
                  <node concept="2OqwBi" id="fQbx7c9a7j" role="3clFbG">
                    <node concept="2OqwBi" id="fQbx7c9a7k" role="2Oq$k0">
                      <node concept="37vLTw" id="fQbx7c9a7l" role="2Oq$k0">
                        <ref role="3cqZAo" node="fQbx7c9a76" resolve="down" />
                      </node>
                      <node concept="3TrcHB" id="fQbx7c9a7m" role="2OqNvi">
                        <ref role="3TsBF5" to="b4kx:fQbx7c7oP9" resolve="steps" />
                      </node>
                    </node>
                    <node concept="tyxLq" id="fQbx7c9a7n" role="2OqNvi">
                      <node concept="2YIFZM" id="fQbx7c9a7o" role="tz02z">
                        <ref role="37wK5l" to="wyt6:~Integer.valueOf(java.lang.String)" resolve="valueOf" />
                        <ref role="1Pybhc" to="wyt6:~Integer" resolve="Integer" />
                        <node concept="2OqwBi" id="fQbx7c9a7p" role="37wK5m">
                          <node concept="2GrUjf" id="fQbx7c9a7q" role="2Oq$k0">
                            <ref role="2Gs0qQ" node="fQbx7c9a5y" resolve="part" />
                          </node>
                          <node concept="liA8E" id="fQbx7c9a7r" role="2OqNvi">
                            <ref role="37wK5l" to="wyt6:~String.substring(int)" resolve="substring" />
                            <node concept="3cmrfG" id="fQbx7c9a7s" role="37wK5m">
                              <property role="3cmrfH" value="1" />
                            </node>
                          </node>
                        </node>
                      </node>
                    </node>
                  </node>
                </node>
              </node>
            </node>
          </node>
        </node>
        <node concept="3clFbH" id="fQbx7c91gi" role="3cqZAp" />
      </node>
    </node>
  </node>
  <node concept="2S6QgY" id="fQbx7c9HKA">
    <property role="TrG5h" value="CalculateCoordinates" />
    <ref role="2ZfgGC" to="b4kx:fQbx7c7mKt" resolve="Grid" />
    <node concept="2S6ZIM" id="fQbx7c9HKB" role="2ZfVej">
      <node concept="3clFbS" id="fQbx7c9HKC" role="2VODD2">
        <node concept="3clFbF" id="fQbx7c9HTN" role="3cqZAp">
          <node concept="Xl_RD" id="fQbx7c9HTM" role="3clFbG">
            <property role="Xl_RC" value="Calculate Coordinates" />
          </node>
        </node>
      </node>
    </node>
    <node concept="2Sbjvc" id="fQbx7c9HKD" role="2ZfgGD">
      <node concept="3clFbS" id="fQbx7c9HKE" role="2VODD2">
        <node concept="3clFbF" id="fQbx7c9ICq" role="3cqZAp">
          <node concept="2YIFZM" id="fQbx7c9IDq" role="3clFbG">
            <ref role="37wK5l" to="6hgk:fQbx7c9IB0" resolve="calculateCoordinates" />
            <ref role="1Pybhc" to="6hgk:fQbx7c9I_v" resolve="CoordinateCalculator" />
            <node concept="2Sf5sV" id="fQbx7c9IDU" role="37wK5m" />
          </node>
        </node>
      </node>
    </node>
  </node>
  <node concept="2S6QgY" id="fQbx7caPDU">
    <property role="TrG5h" value="CalculateIntersectionPoints" />
    <ref role="2ZfgGC" to="b4kx:fQbx7c7mKt" resolve="Grid" />
    <node concept="2S6ZIM" id="fQbx7caPDV" role="2ZfVej">
      <node concept="3clFbS" id="fQbx7caPDW" role="2VODD2">
        <node concept="3clFbF" id="fQbx7caPN7" role="3cqZAp">
          <node concept="Xl_RD" id="fQbx7caPN6" role="3clFbG">
            <property role="Xl_RC" value="Calculate Intersection Points" />
          </node>
        </node>
      </node>
    </node>
    <node concept="2Sbjvc" id="fQbx7caPDX" role="2ZfgGD">
      <node concept="3clFbS" id="fQbx7caPDY" role="2VODD2">
        <node concept="3clFbF" id="fQbx7cblKW" role="3cqZAp">
          <node concept="2OqwBi" id="fQbx7cbol6" role="3clFbG">
            <node concept="2OqwBi" id="fQbx7cbm2F" role="2Oq$k0">
              <node concept="2Sf5sV" id="fQbx7cblKU" role="2Oq$k0" />
              <node concept="3Tsc0h" id="fQbx7cbmAq" role="2OqNvi">
                <ref role="3TtcxE" to="b4kx:fQbx7caSls" resolve="intersectionPoints" />
              </node>
            </node>
            <node concept="2Kehj3" id="fQbx7cbr6V" role="2OqNvi" />
          </node>
        </node>
        <node concept="3clFbH" id="fQbx7cblvC" role="3cqZAp" />
        <node concept="2Gpval" id="fQbx7caQSU" role="3cqZAp">
          <node concept="2GrKxI" id="fQbx7caQSV" role="2Gsz3X">
            <property role="TrG5h" value="c1" />
          </node>
          <node concept="2OqwBi" id="fQbx7caR4G" role="2GsD0m">
            <node concept="2Sf5sV" id="fQbx7caQUT" role="2Oq$k0" />
            <node concept="3Tsc0h" id="fQbx7caRdK" role="2OqNvi">
              <ref role="3TtcxE" to="b4kx:fQbx7c9BzW" resolve="path1Coordinates" />
            </node>
          </node>
          <node concept="3clFbS" id="fQbx7caQSX" role="2LFqv$">
            <node concept="2Gpval" id="fQbx7caRgy" role="3cqZAp">
              <node concept="2GrKxI" id="fQbx7caRgz" role="2Gsz3X">
                <property role="TrG5h" value="c2" />
              </node>
              <node concept="2OqwBi" id="fQbx7caRz0" role="2GsD0m">
                <node concept="2Sf5sV" id="fQbx7caRhp" role="2Oq$k0" />
                <node concept="3Tsc0h" id="fQbx7caROo" role="2OqNvi">
                  <ref role="3TtcxE" to="b4kx:fQbx7c9B$6" resolve="path2Coordinates" />
                </node>
              </node>
              <node concept="3clFbS" id="fQbx7caRg_" role="2LFqv$">
                <node concept="3clFbJ" id="fQbx7caRRa" role="3cqZAp">
                  <node concept="2OqwBi" id="fQbx7caS0I" role="3clFbw">
                    <node concept="2GrUjf" id="fQbx7caRRF" role="2Oq$k0">
                      <ref role="2Gs0qQ" node="fQbx7caQSV" resolve="c1" />
                    </node>
                    <node concept="2qgKlT" id="fQbx7caSbG" role="2OqNvi">
                      <ref role="37wK5l" to="ro4w:fQbx7caDz9" resolve="isAt" />
                      <node concept="2GrUjf" id="fQbx7caSga" role="37wK5m">
                        <ref role="2Gs0qQ" node="fQbx7caRgz" resolve="c2" />
                      </node>
                    </node>
                  </node>
                  <node concept="3clFbS" id="fQbx7caRRc" role="3clFbx">
                    <node concept="3clFbF" id="fQbx7caYTb" role="3cqZAp">
                      <node concept="2OqwBi" id="fQbx7cb0L1" role="3clFbG">
                        <node concept="2OqwBi" id="fQbx7caZ0Z" role="2Oq$k0">
                          <node concept="2Sf5sV" id="fQbx7caYTa" role="2Oq$k0" />
                          <node concept="3Tsc0h" id="fQbx7caZi_" role="2OqNvi">
                            <ref role="3TtcxE" to="b4kx:fQbx7caSls" resolve="intersectionPoints" />
                          </node>
                        </node>
                        <node concept="TSZUe" id="fQbx7cb3lk" role="2OqNvi">
                          <node concept="2OqwBi" id="fQbx7cb3Qh" role="25WWJ7">
                            <node concept="2GrUjf" id="fQbx7cb3yj" role="2Oq$k0">
                              <ref role="2Gs0qQ" node="fQbx7caQSV" resolve="c1" />
                            </node>
                            <node concept="1$rogu" id="fQbx7cb5Pc" role="2OqNvi" />
                          </node>
                        </node>
                      </node>
                    </node>
                  </node>
                </node>
              </node>
            </node>
          </node>
        </node>
        <node concept="3clFbH" id="fQbx7cbthM" role="3cqZAp" />
        <node concept="3clFbF" id="fQbx7cbC8h" role="3cqZAp">
          <node concept="37vLTI" id="fQbx7cbFXr" role="3clFbG">
            <node concept="3cmrfG" id="fQbx7cbGmu" role="37vLTx">
              <property role="3cmrfH" value="1000000000" />
            </node>
            <node concept="2OqwBi" id="fQbx7cbEuu" role="37vLTJ">
              <node concept="2Sf5sV" id="fQbx7cbC8f" role="2Oq$k0" />
              <node concept="3TrcHB" id="fQbx7cbEZ5" role="2OqNvi">
                <ref role="3TsBF5" to="b4kx:fQbx7cbjNY" resolve="closestIntersectionPointDistance" />
              </node>
            </node>
          </node>
        </node>
        <node concept="2Gpval" id="fQbx7cbxWv" role="3cqZAp">
          <node concept="2GrKxI" id="fQbx7cbxWx" role="2Gsz3X">
            <property role="TrG5h" value="i" />
          </node>
          <node concept="2OqwBi" id="fQbx7cb$SE" role="2GsD0m">
            <node concept="2Sf5sV" id="fQbx7cb$$W" role="2Oq$k0" />
            <node concept="3Tsc0h" id="fQbx7cb_qM" role="2OqNvi">
              <ref role="3TtcxE" to="b4kx:fQbx7caSls" resolve="intersectionPoints" />
            </node>
          </node>
          <node concept="3clFbS" id="fQbx7cbxW_" role="2LFqv$">
            <node concept="3clFbJ" id="fQbx7cbGCZ" role="3cqZAp">
              <node concept="3eOVzh" id="fQbx7cbIIb" role="3clFbw">
                <node concept="2OqwBi" id="fQbx7cbJs0" role="3uHU7w">
                  <node concept="2Sf5sV" id="fQbx7cbIZH" role="2Oq$k0" />
                  <node concept="3TrcHB" id="fQbx7cbJKX" role="2OqNvi">
                    <ref role="3TsBF5" to="b4kx:fQbx7cbjNY" resolve="closestIntersectionPointDistance" />
                  </node>
                </node>
                <node concept="2OqwBi" id="fQbx7cbGMk" role="3uHU7B">
                  <node concept="2GrUjf" id="fQbx7cbGDw" role="2Oq$k0">
                    <ref role="2Gs0qQ" node="fQbx7cbxWx" resolve="i" />
                  </node>
                  <node concept="2qgKlT" id="fQbx7cbHay" role="2OqNvi">
                    <ref role="37wK5l" to="ro4w:fQbx7caLSV" resolve="calculateManhattanDistance" />
                  </node>
                </node>
              </node>
              <node concept="3clFbS" id="fQbx7cbGD1" role="3clFbx">
                <node concept="3clFbF" id="fQbx7cbJXx" role="3cqZAp">
                  <node concept="37vLTI" id="fQbx7cbLcD" role="3clFbG">
                    <node concept="2OqwBi" id="fQbx7cbLo_" role="37vLTx">
                      <node concept="2GrUjf" id="fQbx7cbLcY" role="2Oq$k0">
                        <ref role="2Gs0qQ" node="fQbx7cbxWx" resolve="i" />
                      </node>
                      <node concept="2qgKlT" id="fQbx7cbM7C" role="2OqNvi">
                        <ref role="37wK5l" to="ro4w:fQbx7caLSV" resolve="calculateManhattanDistance" />
                      </node>
                    </node>
                    <node concept="2OqwBi" id="fQbx7cbK5l" role="37vLTJ">
                      <node concept="2Sf5sV" id="fQbx7cbJXw" role="2Oq$k0" />
                      <node concept="3TrcHB" id="fQbx7cbKmS" role="2OqNvi">
                        <ref role="3TsBF5" to="b4kx:fQbx7cbjNY" resolve="closestIntersectionPointDistance" />
                      </node>
                    </node>
                  </node>
                </node>
              </node>
            </node>
          </node>
        </node>
      </node>
    </node>
  </node>
</model>

