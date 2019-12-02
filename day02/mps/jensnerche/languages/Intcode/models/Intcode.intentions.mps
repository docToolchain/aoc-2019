<?xml version="1.0" encoding="UTF-8"?>
<model ref="r:d9820586-4154-4321-9df9-ce756895f0f0(Intcode.intentions)">
  <persistence version="9" />
  <languages>
    <use id="d7a92d38-f7db-40d0-8431-763b0c3c9f20" name="jetbrains.mps.lang.intentions" version="0" />
    <devkit ref="fbc25dd2-5da4-483a-8b19-70928e1b62d7(jetbrains.mps.devkit.general-purpose)" />
  </languages>
  <imports>
    <import index="3il8" ref="r:e463d01e-58f3-409b-9e10-6b372437586e(Intcode.interpreter)" />
    <import index="x9r3" ref="r:84dee49f-0f88-4a63-af75-9bc34ebfe2a0(Intcode.structure)" />
  </imports>
  <registry>
    <language id="f3061a53-9226-4cc5-a443-f952ceaf5816" name="jetbrains.mps.baseLanguage">
      <concept id="1082485599095" name="jetbrains.mps.baseLanguage.structure.BlockStatement" flags="nn" index="9aQIb">
        <child id="1082485599096" name="statements" index="9aQI4" />
      </concept>
      <concept id="1215693861676" name="jetbrains.mps.baseLanguage.structure.BaseAssignmentExpression" flags="nn" index="d038R">
        <child id="1068498886297" name="rValue" index="37vLTx" />
        <child id="1068498886295" name="lValue" index="37vLTJ" />
      </concept>
      <concept id="1239714755177" name="jetbrains.mps.baseLanguage.structure.AbstractUnaryNumberOperation" flags="nn" index="2$Kvd9">
        <child id="1239714902950" name="expression" index="2$L3a6" />
      </concept>
      <concept id="1154032098014" name="jetbrains.mps.baseLanguage.structure.AbstractLoopStatement" flags="nn" index="2LF5Ji">
        <child id="1154032183016" name="body" index="2LFqv$" />
      </concept>
      <concept id="1197027756228" name="jetbrains.mps.baseLanguage.structure.DotExpression" flags="nn" index="2OqwBi">
        <child id="1197027771414" name="operand" index="2Oq$k0" />
        <child id="1197027833540" name="operation" index="2OqNvi" />
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
      <concept id="1070534370425" name="jetbrains.mps.baseLanguage.structure.IntegerType" flags="in" index="10Oyi0" />
      <concept id="1068431474542" name="jetbrains.mps.baseLanguage.structure.VariableDeclaration" flags="ng" index="33uBYm">
        <child id="1068431790190" name="initializer" index="33vP2m" />
      </concept>
      <concept id="1092119917967" name="jetbrains.mps.baseLanguage.structure.MulExpression" flags="nn" index="17qRlL" />
      <concept id="1068498886296" name="jetbrains.mps.baseLanguage.structure.VariableReference" flags="nn" index="37vLTw">
        <reference id="1068581517664" name="variableDeclaration" index="3cqZAo" />
      </concept>
      <concept id="1068498886294" name="jetbrains.mps.baseLanguage.structure.AssignmentExpression" flags="nn" index="37vLTI" />
      <concept id="4972933694980447171" name="jetbrains.mps.baseLanguage.structure.BaseVariableDeclaration" flags="ng" index="19Szcq">
        <child id="5680397130376446158" name="type" index="1tU5fm" />
      </concept>
      <concept id="1068580123152" name="jetbrains.mps.baseLanguage.structure.EqualsExpression" flags="nn" index="3clFbC" />
      <concept id="1068580123155" name="jetbrains.mps.baseLanguage.structure.ExpressionStatement" flags="nn" index="3clFbF">
        <child id="1068580123156" name="expression" index="3clFbG" />
      </concept>
      <concept id="1068580123157" name="jetbrains.mps.baseLanguage.structure.Statement" flags="nn" index="3clFbH" />
      <concept id="1068580123159" name="jetbrains.mps.baseLanguage.structure.IfStatement" flags="nn" index="3clFbJ">
        <child id="1082485599094" name="ifFalseStatement" index="9aQIa" />
        <child id="1068580123160" name="condition" index="3clFbw" />
        <child id="1068580123161" name="ifTrue" index="3clFbx" />
      </concept>
      <concept id="1068580123136" name="jetbrains.mps.baseLanguage.structure.StatementList" flags="sn" stub="5293379017992965193" index="3clFbS">
        <child id="1068581517665" name="statement" index="3cqZAp" />
      </concept>
      <concept id="1068580320020" name="jetbrains.mps.baseLanguage.structure.IntegerConstant" flags="nn" index="3cmrfG">
        <property id="1068580320021" name="value" index="3cmrfH" />
      </concept>
      <concept id="1068581242875" name="jetbrains.mps.baseLanguage.structure.PlusExpression" flags="nn" index="3cpWs3" />
      <concept id="1068581242878" name="jetbrains.mps.baseLanguage.structure.ReturnStatement" flags="nn" index="3cpWs6" />
      <concept id="1068581242864" name="jetbrains.mps.baseLanguage.structure.LocalVariableDeclarationStatement" flags="nn" index="3cpWs8">
        <child id="1068581242865" name="localVariableDeclaration" index="3cpWs9" />
      </concept>
      <concept id="1068581242863" name="jetbrains.mps.baseLanguage.structure.LocalVariableDeclaration" flags="nr" index="3cpWsn" />
      <concept id="1081506773034" name="jetbrains.mps.baseLanguage.structure.LessThanExpression" flags="nn" index="3eOVzh" />
      <concept id="1204053956946" name="jetbrains.mps.baseLanguage.structure.IMethodCall" flags="ng" index="1ndlxa">
        <reference id="1068499141037" name="baseMethodDeclaration" index="37wK5l" />
        <child id="1068499141038" name="actualArgument" index="37wK5m" />
      </concept>
      <concept id="1081773326031" name="jetbrains.mps.baseLanguage.structure.BinaryOperation" flags="nn" index="3uHJSO">
        <child id="1081773367579" name="rightExpression" index="3uHU7w" />
        <child id="1081773367580" name="leftExpression" index="3uHU7B" />
      </concept>
      <concept id="1214918800624" name="jetbrains.mps.baseLanguage.structure.PostfixIncrementExpression" flags="nn" index="3uNrnE" />
      <concept id="1144230876926" name="jetbrains.mps.baseLanguage.structure.AbstractForStatement" flags="nn" index="1DupvO">
        <child id="1144230900587" name="variable" index="1Duv9x" />
      </concept>
      <concept id="1144231330558" name="jetbrains.mps.baseLanguage.structure.ForStatement" flags="nn" index="1Dw8fO">
        <child id="1144231399730" name="condition" index="1Dwp0S" />
        <child id="1144231408325" name="iteration" index="1Dwrff" />
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
    <language id="760a0a8c-eabb-4521-8bfd-65db761a9ba3" name="jetbrains.mps.baseLanguage.logging">
      <concept id="6332851714983831325" name="jetbrains.mps.baseLanguage.logging.structure.MsgStatement" flags="ng" index="2xdQw9">
        <property id="6332851714983843871" name="severity" index="2xdLsb" />
        <child id="5721587534047265374" name="message" index="9lYJi" />
      </concept>
    </language>
    <language id="7866978e-a0f0-4cc7-81bc-4d213d9375e1" name="jetbrains.mps.lang.smodel">
      <concept id="1138661924179" name="jetbrains.mps.lang.smodel.structure.Property_SetOperation" flags="nn" index="tyxLq">
        <child id="1138662048170" name="value" index="tz02z" />
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
      <concept id="1162934736510" name="jetbrains.mps.baseLanguage.collections.structure.GetElementOperation" flags="nn" index="34jXtK" />
    </language>
  </registry>
  <node concept="2S6QgY" id="32nSEEspU4x">
    <property role="TrG5h" value="RunInterpreter" />
    <ref role="2ZfgGC" to="x9r3:32nSEEspKJr" resolve="IntcodeProgram" />
    <node concept="2S6ZIM" id="32nSEEspU4y" role="2ZfVej">
      <node concept="3clFbS" id="32nSEEspU4z" role="2VODD2">
        <node concept="3clFbF" id="32nSEEspUdM" role="3cqZAp">
          <node concept="Xl_RD" id="32nSEEspUdL" role="3clFbG">
            <property role="Xl_RC" value="Run Intcode Interpreter" />
          </node>
        </node>
      </node>
    </node>
    <node concept="2Sbjvc" id="32nSEEspU4$" role="2ZfgGD">
      <node concept="3clFbS" id="32nSEEspU4_" role="2VODD2">
        <node concept="3clFbF" id="32nSEEspV28" role="3cqZAp">
          <node concept="2YIFZM" id="32nSEEspV35" role="3clFbG">
            <ref role="37wK5l" to="3il8:32nSEEspNT_" resolve="interpret" />
            <ref role="1Pybhc" to="3il8:32nSEEspNRE" resolve="IntcodeInterpreter" />
            <node concept="2Sf5sV" id="32nSEEspV3z" role="37wK5m" />
          </node>
        </node>
      </node>
    </node>
  </node>
  <node concept="2S6QgY" id="32nSEEsrxZh">
    <property role="TrG5h" value="ConvertToMemory" />
    <ref role="2ZfgGC" to="x9r3:32nSEEspKJr" resolve="IntcodeProgram" />
    <node concept="2S6ZIM" id="32nSEEsrxZi" role="2ZfVej">
      <node concept="3clFbS" id="32nSEEsrxZj" role="2VODD2">
        <node concept="3clFbF" id="32nSEEsry8w" role="3cqZAp">
          <node concept="Xl_RD" id="32nSEEsry8v" role="3clFbG">
            <property role="Xl_RC" value="Convert To Memory" />
          </node>
        </node>
      </node>
    </node>
    <node concept="2Sbjvc" id="32nSEEsrxZk" role="2ZfgGD">
      <node concept="3clFbS" id="32nSEEsrxZl" role="2VODD2">
        <node concept="3clFbF" id="32nSEEssdx7" role="3cqZAp">
          <node concept="2YIFZM" id="32nSEEssdyP" role="3clFbG">
            <ref role="37wK5l" to="3il8:32nSEEss1QC" resolve="reinitializeMemory" />
            <ref role="1Pybhc" to="3il8:32nSEEspNRE" resolve="IntcodeInterpreter" />
            <node concept="2Sf5sV" id="32nSEEssdzl" role="37wK5m" />
          </node>
        </node>
      </node>
    </node>
  </node>
  <node concept="2S6QgY" id="32nSEEsrYNM">
    <property role="TrG5h" value="RunPart2" />
    <ref role="2ZfgGC" to="x9r3:32nSEEspKJr" resolve="IntcodeProgram" />
    <node concept="2S6ZIM" id="32nSEEsrYNN" role="2ZfVej">
      <node concept="3clFbS" id="32nSEEsrYNO" role="2VODD2">
        <node concept="3clFbF" id="32nSEEssjYB" role="3cqZAp">
          <node concept="Xl_RD" id="32nSEEssjYA" role="3clFbG">
            <property role="Xl_RC" value="Run Part 2" />
          </node>
        </node>
      </node>
    </node>
    <node concept="2Sbjvc" id="32nSEEsrYNP" role="2ZfgGD">
      <node concept="3clFbS" id="32nSEEsrYNQ" role="2VODD2">
        <node concept="1Dw8fO" id="32nSEEsskoS" role="3cqZAp">
          <node concept="3cpWsn" id="32nSEEsskoT" role="1Duv9x">
            <property role="TrG5h" value="noun" />
            <node concept="10Oyi0" id="32nSEEsskpj" role="1tU5fm" />
            <node concept="3cmrfG" id="32nSEEsskqk" role="33vP2m">
              <property role="3cmrfH" value="0" />
            </node>
          </node>
          <node concept="3clFbS" id="32nSEEsskoU" role="2LFqv$">
            <node concept="1Dw8fO" id="32nSEEssmaQ" role="3cqZAp">
              <node concept="3cpWsn" id="32nSEEssmaR" role="1Duv9x">
                <property role="TrG5h" value="verb" />
                <node concept="10Oyi0" id="32nSEEssmbh" role="1tU5fm" />
                <node concept="3cmrfG" id="32nSEEssmcw" role="33vP2m">
                  <property role="3cmrfH" value="0" />
                </node>
              </node>
              <node concept="3clFbS" id="32nSEEssmaS" role="2LFqv$">
                <node concept="2xdQw9" id="2D0OFrLvaln" role="3cqZAp">
                  <property role="2xdLsb" value="info" />
                  <node concept="3cpWs3" id="2D0OFrLvcx7" role="9lYJi">
                    <node concept="37vLTw" id="2D0OFrLvcAf" role="3uHU7w">
                      <ref role="3cqZAo" node="32nSEEssmaR" resolve="verb" />
                    </node>
                    <node concept="3cpWs3" id="2D0OFrLvbMj" role="3uHU7B">
                      <node concept="3cpWs3" id="2D0OFrLvb1T" role="3uHU7B">
                        <node concept="Xl_RD" id="2D0OFrLvalp" role="3uHU7B">
                          <property role="Xl_RC" value="noun=" />
                        </node>
                        <node concept="37vLTw" id="2D0OFrLvb2u" role="3uHU7w">
                          <ref role="3cqZAo" node="32nSEEsskoT" resolve="noun" />
                        </node>
                      </node>
                      <node concept="Xl_RD" id="2D0OFrLvbRd" role="3uHU7w">
                        <property role="Xl_RC" value=", verb=" />
                      </node>
                    </node>
                  </node>
                </node>
                <node concept="3clFbF" id="32nSEEssnXG" role="3cqZAp">
                  <node concept="2YIFZM" id="32nSEEssnYB" role="3clFbG">
                    <ref role="37wK5l" to="3il8:32nSEEss1QC" resolve="reinitializeMemory" />
                    <ref role="1Pybhc" to="3il8:32nSEEspNRE" resolve="IntcodeInterpreter" />
                    <node concept="2Sf5sV" id="32nSEEssnZ8" role="37wK5m" />
                  </node>
                </node>
                <node concept="2xdQw9" id="2D0OFrLvd77" role="3cqZAp">
                  <property role="2xdLsb" value="info" />
                  <node concept="Xl_RD" id="2D0OFrLvd79" role="9lYJi">
                    <property role="Xl_RC" value="Memory resetted." />
                  </node>
                </node>
                <node concept="3clFbH" id="2D0OFrLvd_k" role="3cqZAp" />
                <node concept="3clFbF" id="32nSEEsso0p" role="3cqZAp">
                  <node concept="2OqwBi" id="32nSEEssulg" role="3clFbG">
                    <node concept="2OqwBi" id="32nSEEssths" role="2Oq$k0">
                      <node concept="2OqwBi" id="32nSEEssqu3" role="2Oq$k0">
                        <node concept="2OqwBi" id="32nSEEsso8n" role="2Oq$k0">
                          <node concept="2Sf5sV" id="32nSEEsso0n" role="2Oq$k0" />
                          <node concept="3Tsc0h" id="32nSEEssoZD" role="2OqNvi">
                            <ref role="3TtcxE" to="x9r3:32nSEEspL_h" resolve="memory" />
                          </node>
                        </node>
                        <node concept="34jXtK" id="32nSEEsst24" role="2OqNvi">
                          <node concept="3cmrfG" id="32nSEEsst5n" role="25WWJ7">
                            <property role="3cmrfH" value="1" />
                          </node>
                        </node>
                      </node>
                      <node concept="3TrcHB" id="32nSEEsstth" role="2OqNvi">
                        <ref role="3TsBF5" to="x9r3:32nSEEspL$L" resolve="content" />
                      </node>
                    </node>
                    <node concept="tyxLq" id="32nSEEssuQs" role="2OqNvi">
                      <node concept="37vLTw" id="32nSEEssuX3" role="tz02z">
                        <ref role="3cqZAo" node="32nSEEsskoT" resolve="noun" />
                      </node>
                    </node>
                  </node>
                </node>
                <node concept="3clFbF" id="32nSEEssv7V" role="3cqZAp">
                  <node concept="2OqwBi" id="32nSEEss$De" role="3clFbG">
                    <node concept="2OqwBi" id="32nSEEsszFR" role="2Oq$k0">
                      <node concept="2OqwBi" id="32nSEEsswUf" role="2Oq$k0">
                        <node concept="2OqwBi" id="32nSEEssvgT" role="2Oq$k0">
                          <node concept="2Sf5sV" id="32nSEEssv7T" role="2Oq$k0" />
                          <node concept="3Tsc0h" id="32nSEEssvu1" role="2OqNvi">
                            <ref role="3TtcxE" to="x9r3:32nSEEspL_h" resolve="memory" />
                          </node>
                        </node>
                        <node concept="34jXtK" id="32nSEEsszug" role="2OqNvi">
                          <node concept="3cmrfG" id="32nSEEsszxz" role="25WWJ7">
                            <property role="3cmrfH" value="2" />
                          </node>
                        </node>
                      </node>
                      <node concept="3TrcHB" id="32nSEEsszPj" role="2OqNvi">
                        <ref role="3TsBF5" to="x9r3:32nSEEspL$L" resolve="content" />
                      </node>
                    </node>
                    <node concept="tyxLq" id="32nSEEss_6h" role="2OqNvi">
                      <node concept="37vLTw" id="32nSEEss_cX" role="tz02z">
                        <ref role="3cqZAo" node="32nSEEssmaR" resolve="verb" />
                      </node>
                    </node>
                  </node>
                </node>
                <node concept="2xdQw9" id="2D0OFrLveNq" role="3cqZAp">
                  <property role="2xdLsb" value="info" />
                  <node concept="Xl_RD" id="2D0OFrLveNs" role="9lYJi">
                    <property role="Xl_RC" value="Inputs were provided. Run the interpreter." />
                  </node>
                </node>
                <node concept="3clFbH" id="2D0OFrLvepn" role="3cqZAp" />
                <node concept="3clFbF" id="32nSEEss_rN" role="3cqZAp">
                  <node concept="2YIFZM" id="32nSEEss_zu" role="3clFbG">
                    <ref role="37wK5l" to="3il8:32nSEEspNT_" resolve="interpret" />
                    <ref role="1Pybhc" to="3il8:32nSEEspNRE" resolve="IntcodeInterpreter" />
                    <node concept="2Sf5sV" id="32nSEEss_$3" role="37wK5m" />
                  </node>
                </node>
                <node concept="3clFbH" id="2D0OFrLvffl" role="3cqZAp" />
                <node concept="3cpWs8" id="2D0OFrLvfHG" role="3cqZAp">
                  <node concept="3cpWsn" id="2D0OFrLvfHH" role="3cpWs9">
                    <property role="TrG5h" value="output" />
                    <node concept="10Oyi0" id="2D0OFrLvfH$" role="1tU5fm" />
                    <node concept="2OqwBi" id="2D0OFrLvfHI" role="33vP2m">
                      <node concept="2OqwBi" id="2D0OFrLvfHJ" role="2Oq$k0">
                        <node concept="2OqwBi" id="2D0OFrLvfHK" role="2Oq$k0">
                          <node concept="2Sf5sV" id="2D0OFrLvfHL" role="2Oq$k0" />
                          <node concept="3Tsc0h" id="2D0OFrLvfHM" role="2OqNvi">
                            <ref role="3TtcxE" to="x9r3:32nSEEspL_h" resolve="memory" />
                          </node>
                        </node>
                        <node concept="34jXtK" id="2D0OFrLvfHN" role="2OqNvi">
                          <node concept="3cmrfG" id="2D0OFrLvfHO" role="25WWJ7">
                            <property role="3cmrfH" value="0" />
                          </node>
                        </node>
                      </node>
                      <node concept="3TrcHB" id="2D0OFrLvfHP" role="2OqNvi">
                        <ref role="3TsBF5" to="x9r3:32nSEEspL$L" resolve="content" />
                      </node>
                    </node>
                  </node>
                </node>
                <node concept="2xdQw9" id="2D0OFrLvhfX" role="3cqZAp">
                  <property role="2xdLsb" value="info" />
                  <node concept="3cpWs3" id="2D0OFrLvj1_" role="9lYJi">
                    <node concept="37vLTw" id="2D0OFrLvj2a" role="3uHU7w">
                      <ref role="3cqZAo" node="2D0OFrLvfHH" resolve="output" />
                    </node>
                    <node concept="Xl_RD" id="2D0OFrLvhfZ" role="3uHU7B">
                      <property role="Xl_RC" value="Output=" />
                    </node>
                  </node>
                </node>
                <node concept="3clFbH" id="2D0OFrLvgPG" role="3cqZAp" />
                <node concept="3clFbJ" id="32nSEEss_FU" role="3cqZAp">
                  <node concept="3clFbS" id="32nSEEss_FW" role="3clFbx">
                    <node concept="3cpWs8" id="32nSEEssJsC" role="3cqZAp">
                      <node concept="3cpWsn" id="32nSEEssJsD" role="3cpWs9">
                        <property role="TrG5h" value="result" />
                        <node concept="10Oyi0" id="32nSEEssJsA" role="1tU5fm" />
                        <node concept="3cpWs3" id="32nSEEssJsE" role="33vP2m">
                          <node concept="37vLTw" id="32nSEEssJsF" role="3uHU7w">
                            <ref role="3cqZAo" node="32nSEEssmaR" resolve="verb" />
                          </node>
                          <node concept="17qRlL" id="32nSEEssJsG" role="3uHU7B">
                            <node concept="3cmrfG" id="32nSEEssJsH" role="3uHU7B">
                              <property role="3cmrfH" value="100" />
                            </node>
                            <node concept="37vLTw" id="32nSEEssJsI" role="3uHU7w">
                              <ref role="3cqZAo" node="32nSEEsskoT" resolve="noun" />
                            </node>
                          </node>
                        </node>
                      </node>
                    </node>
                    <node concept="3clFbF" id="32nSEEssKKG" role="3cqZAp">
                      <node concept="37vLTI" id="32nSEEssLRj" role="3clFbG">
                        <node concept="37vLTw" id="32nSEEssM2x" role="37vLTx">
                          <ref role="3cqZAo" node="32nSEEssJsD" resolve="result" />
                        </node>
                        <node concept="2OqwBi" id="32nSEEssKSw" role="37vLTJ">
                          <node concept="2Sf5sV" id="32nSEEssKKE" role="2Oq$k0" />
                          <node concept="3TrcHB" id="32nSEEssL1A" role="2OqNvi">
                            <ref role="3TsBF5" to="x9r3:32nSEEssJQ3" resolve="resultPart2" />
                          </node>
                        </node>
                      </node>
                    </node>
                    <node concept="3cpWs6" id="32nSEEssJP2" role="3cqZAp" />
                  </node>
                  <node concept="3clFbC" id="32nSEEssFWO" role="3clFbw">
                    <node concept="3cmrfG" id="32nSEEssGjC" role="3uHU7w">
                      <property role="3cmrfH" value="19690720" />
                    </node>
                    <node concept="37vLTw" id="2D0OFrLvfHQ" role="3uHU7B">
                      <ref role="3cqZAo" node="2D0OFrLvfHH" resolve="output" />
                    </node>
                  </node>
                  <node concept="9aQIb" id="2D0OFrLvj75" role="9aQIa">
                    <node concept="3clFbS" id="2D0OFrLvj76" role="9aQI4" />
                  </node>
                </node>
              </node>
              <node concept="3eOVzh" id="32nSEEssn0S" role="1Dwp0S">
                <node concept="3cmrfG" id="32nSEEssn1r" role="3uHU7w">
                  <property role="3cmrfH" value="100" />
                </node>
                <node concept="37vLTw" id="32nSEEssmcV" role="3uHU7B">
                  <ref role="3cqZAo" node="32nSEEssmaR" resolve="verb" />
                </node>
              </node>
              <node concept="3uNrnE" id="32nSEEssnRN" role="1Dwrff">
                <node concept="37vLTw" id="32nSEEssnRP" role="2$L3a6">
                  <ref role="3cqZAo" node="32nSEEssmaR" resolve="verb" />
                </node>
              </node>
            </node>
          </node>
          <node concept="3eOVzh" id="32nSEEssleE" role="1Dwp0S">
            <node concept="3cmrfG" id="32nSEEssleK" role="3uHU7w">
              <property role="3cmrfH" value="100" />
            </node>
            <node concept="37vLTw" id="32nSEEsskqJ" role="3uHU7B">
              <ref role="3cqZAo" node="32nSEEsskoT" resolve="noun" />
            </node>
          </node>
          <node concept="3uNrnE" id="32nSEEssm4Y" role="1Dwrff">
            <node concept="37vLTw" id="32nSEEssm50" role="2$L3a6">
              <ref role="3cqZAo" node="32nSEEsskoT" resolve="noun" />
            </node>
          </node>
        </node>
      </node>
    </node>
  </node>
</model>

