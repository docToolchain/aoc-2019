<?xml version="1.0" encoding="UTF-8"?>
<model ref="r:e316f4f0-91ba-41ba-9004-e5c0a5e4356e(Day03.util)">
  <persistence version="9" />
  <languages>
    <use id="f3061a53-9226-4cc5-a443-f952ceaf5816" name="jetbrains.mps.baseLanguage" version="8" />
    <devkit ref="fbc25dd2-5da4-483a-8b19-70928e1b62d7(jetbrains.mps.devkit.general-purpose)" />
  </languages>
  <imports>
    <import index="b4kx" ref="r:c59fa5b7-130e-45f2-a476-3ba334523617(Day03.structure)" />
    <import index="wyt6" ref="6354ebe7-c22a-4a0f-ac54-50b52ab9b065/java:java.lang(JDK/)" implicit="true" />
  </imports>
  <registry>
    <language id="f3061a53-9226-4cc5-a443-f952ceaf5816" name="jetbrains.mps.baseLanguage">
      <concept id="1215693861676" name="jetbrains.mps.baseLanguage.structure.BaseAssignmentExpression" flags="nn" index="d038R">
        <child id="1068498886297" name="rValue" index="37vLTx" />
        <child id="1068498886295" name="lValue" index="37vLTJ" />
      </concept>
      <concept id="1202948039474" name="jetbrains.mps.baseLanguage.structure.InstanceMethodCallOperation" flags="nn" index="liA8E" />
      <concept id="1465982738277781862" name="jetbrains.mps.baseLanguage.structure.PlaceholderMember" flags="ng" index="2tJIrI" />
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
      <concept id="1145552977093" name="jetbrains.mps.baseLanguage.structure.GenericNewExpression" flags="nn" index="2ShNRf">
        <child id="1145553007750" name="creator" index="2ShVmc" />
      </concept>
      <concept id="1070475926800" name="jetbrains.mps.baseLanguage.structure.StringLiteral" flags="nn" index="Xl_RD">
        <property id="1070475926801" name="value" index="Xl_RC" />
      </concept>
      <concept id="1081236700938" name="jetbrains.mps.baseLanguage.structure.StaticMethodDeclaration" flags="ig" index="2YIFZL" />
      <concept id="1070534370425" name="jetbrains.mps.baseLanguage.structure.IntegerType" flags="in" index="10Oyi0" />
      <concept id="1068390468198" name="jetbrains.mps.baseLanguage.structure.ClassConcept" flags="ig" index="312cEu" />
      <concept id="1068431474542" name="jetbrains.mps.baseLanguage.structure.VariableDeclaration" flags="ng" index="33uBYm">
        <child id="1068431790190" name="initializer" index="33vP2m" />
      </concept>
      <concept id="1068498886296" name="jetbrains.mps.baseLanguage.structure.VariableReference" flags="nn" index="37vLTw">
        <reference id="1068581517664" name="variableDeclaration" index="3cqZAo" />
      </concept>
      <concept id="1068498886292" name="jetbrains.mps.baseLanguage.structure.ParameterDeclaration" flags="ir" index="37vLTG" />
      <concept id="1068498886294" name="jetbrains.mps.baseLanguage.structure.AssignmentExpression" flags="nn" index="37vLTI" />
      <concept id="4972933694980447171" name="jetbrains.mps.baseLanguage.structure.BaseVariableDeclaration" flags="ng" index="19Szcq">
        <child id="5680397130376446158" name="type" index="1tU5fm" />
      </concept>
      <concept id="1068580123132" name="jetbrains.mps.baseLanguage.structure.BaseMethodDeclaration" flags="ng" index="3clF44">
        <child id="1068580123133" name="returnType" index="3clF45" />
        <child id="1068580123134" name="parameter" index="3clF46" />
        <child id="1068580123135" name="body" index="3clF47" />
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
      <concept id="1068581517677" name="jetbrains.mps.baseLanguage.structure.VoidType" flags="in" index="3cqZAl" />
      <concept id="1081506773034" name="jetbrains.mps.baseLanguage.structure.LessThanExpression" flags="nn" index="3eOVzh" />
      <concept id="1204053956946" name="jetbrains.mps.baseLanguage.structure.IMethodCall" flags="ng" index="1ndlxa">
        <reference id="1068499141037" name="baseMethodDeclaration" index="37wK5l" />
        <child id="1068499141038" name="actualArgument" index="37wK5m" />
      </concept>
      <concept id="1107461130800" name="jetbrains.mps.baseLanguage.structure.Classifier" flags="ng" index="3pOWGL">
        <child id="5375687026011219971" name="member" index="jymVt" unordered="true" />
      </concept>
      <concept id="7812454656619025412" name="jetbrains.mps.baseLanguage.structure.LocalMethodCall" flags="nn" index="1rXfSq" />
      <concept id="1081773326031" name="jetbrains.mps.baseLanguage.structure.BinaryOperation" flags="nn" index="3uHJSO">
        <child id="1081773367579" name="rightExpression" index="3uHU7w" />
        <child id="1081773367580" name="leftExpression" index="3uHU7B" />
      </concept>
      <concept id="1214918800624" name="jetbrains.mps.baseLanguage.structure.PostfixIncrementExpression" flags="nn" index="3uNrnE" />
      <concept id="1214918975462" name="jetbrains.mps.baseLanguage.structure.PostfixDecrementExpression" flags="nn" index="3uO5VW" />
      <concept id="1178549954367" name="jetbrains.mps.baseLanguage.structure.IVisible" flags="ng" index="1B3ioH">
        <child id="1178549979242" name="visibility" index="1B3o_S" />
      </concept>
      <concept id="1144230876926" name="jetbrains.mps.baseLanguage.structure.AbstractForStatement" flags="nn" index="1DupvO">
        <child id="1144230900587" name="variable" index="1Duv9x" />
      </concept>
      <concept id="1144231330558" name="jetbrains.mps.baseLanguage.structure.ForStatement" flags="nn" index="1Dw8fO">
        <child id="1144231399730" name="condition" index="1Dwp0S" />
        <child id="1144231408325" name="iteration" index="1Dwrff" />
      </concept>
      <concept id="1146644602865" name="jetbrains.mps.baseLanguage.structure.PublicVisibility" flags="nn" index="3Tm1VV" />
    </language>
    <language id="7866978e-a0f0-4cc7-81bc-4d213d9375e1" name="jetbrains.mps.lang.smodel">
      <concept id="7453996997717780434" name="jetbrains.mps.lang.smodel.structure.Node_GetSConceptOperation" flags="nn" index="2yIwOk" />
      <concept id="1145383075378" name="jetbrains.mps.lang.smodel.structure.SNodeListType" flags="in" index="2I9FWS">
        <reference id="1145383142433" name="elementConcept" index="2I9WkF" />
      </concept>
      <concept id="6870613620390542976" name="jetbrains.mps.lang.smodel.structure.ConceptAliasOperation" flags="ng" index="3n3YKJ" />
      <concept id="1180636770613" name="jetbrains.mps.lang.smodel.structure.SNodeCreator" flags="nn" index="3zrR0B">
        <child id="1180636770616" name="createdType" index="3zrR0E" />
      </concept>
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
  <node concept="312cEu" id="fQbx7c9I_v">
    <property role="TrG5h" value="CoordinateCalculator" />
    <node concept="2YIFZL" id="fQbx7c9IB0" role="jymVt">
      <property role="TrG5h" value="calculateCoordinates" />
      <node concept="3clFbS" id="fQbx7c9IB3" role="3clF47">
        <node concept="3clFbF" id="fQbx7c9QfC" role="3cqZAp">
          <node concept="1rXfSq" id="fQbx7c9QfA" role="3clFbG">
            <ref role="37wK5l" node="fQbx7c9II$" resolve="coordinatesForWire" />
            <node concept="2OqwBi" id="fQbx7c9Qtm" role="37wK5m">
              <node concept="37vLTw" id="fQbx7c9Qio" role="2Oq$k0">
                <ref role="3cqZAo" node="fQbx7c9IBz" resolve="grid" />
              </node>
              <node concept="3Tsc0h" id="fQbx7c9QLg" role="2OqNvi">
                <ref role="3TtcxE" to="b4kx:fQbx7c7qg8" resolve="path1" />
              </node>
            </node>
            <node concept="2OqwBi" id="fQbx7c9R4X" role="37wK5m">
              <node concept="37vLTw" id="fQbx7c9QU1" role="2Oq$k0">
                <ref role="3cqZAo" node="fQbx7c9IBz" resolve="grid" />
              </node>
              <node concept="3Tsc0h" id="fQbx7c9RiO" role="2OqNvi">
                <ref role="3TtcxE" to="b4kx:fQbx7c9BzW" resolve="path1Coordinates" />
              </node>
            </node>
          </node>
        </node>
        <node concept="3clFbF" id="fQbx7cajHC" role="3cqZAp">
          <node concept="1rXfSq" id="fQbx7cajHA" role="3clFbG">
            <ref role="37wK5l" node="fQbx7c9II$" resolve="coordinatesForWire" />
            <node concept="2OqwBi" id="fQbx7cak1f" role="37wK5m">
              <node concept="37vLTw" id="fQbx7cajQh" role="2Oq$k0">
                <ref role="3cqZAo" node="fQbx7c9IBz" resolve="grid" />
              </node>
              <node concept="3Tsc0h" id="fQbx7cakgc" role="2OqNvi">
                <ref role="3TtcxE" to="b4kx:fQbx7c7qga" resolve="path2" />
              </node>
            </node>
            <node concept="2OqwBi" id="fQbx7cakwB" role="37wK5m">
              <node concept="37vLTw" id="fQbx7caklF" role="2Oq$k0">
                <ref role="3cqZAo" node="fQbx7c9IBz" resolve="grid" />
              </node>
              <node concept="3Tsc0h" id="fQbx7cakTC" role="2OqNvi">
                <ref role="3TtcxE" to="b4kx:fQbx7c9B$6" resolve="path2Coordinates" />
              </node>
            </node>
          </node>
        </node>
      </node>
      <node concept="3Tm1VV" id="fQbx7c9IAn" role="1B3o_S" />
      <node concept="3cqZAl" id="fQbx7c9IAN" role="3clF45" />
      <node concept="37vLTG" id="fQbx7c9IBz" role="3clF46">
        <property role="TrG5h" value="grid" />
        <node concept="3Tqbb2" id="fQbx7c9IBy" role="1tU5fm">
          <ref role="ehGHo" to="b4kx:fQbx7c7mKt" resolve="Grid" />
        </node>
      </node>
    </node>
    <node concept="2tJIrI" id="fQbx7c9IG4" role="jymVt" />
    <node concept="2YIFZL" id="fQbx7c9II$" role="jymVt">
      <property role="TrG5h" value="coordinatesForWire" />
      <node concept="3clFbS" id="fQbx7c9IIB" role="3clF47">
        <node concept="3clFbF" id="fQbx7carlH" role="3cqZAp">
          <node concept="2OqwBi" id="fQbx7casUG" role="3clFbG">
            <node concept="37vLTw" id="fQbx7carlF" role="2Oq$k0">
              <ref role="3cqZAo" node="fQbx7c9Kip" resolve="coordinates" />
            </node>
            <node concept="2Kehj3" id="fQbx7cavz3" role="2OqNvi" />
          </node>
        </node>
        <node concept="3cpWs8" id="fQbx7ca160" role="3cqZAp">
          <node concept="3cpWsn" id="fQbx7ca163" role="3cpWs9">
            <property role="TrG5h" value="currentX" />
            <node concept="10Oyi0" id="fQbx7ca15Y" role="1tU5fm" />
            <node concept="3cmrfG" id="fQbx7ca1aP" role="33vP2m">
              <property role="3cmrfH" value="0" />
            </node>
          </node>
        </node>
        <node concept="3cpWs8" id="fQbx7ca1dX" role="3cqZAp">
          <node concept="3cpWsn" id="fQbx7ca1e0" role="3cpWs9">
            <property role="TrG5h" value="currentY" />
            <node concept="10Oyi0" id="fQbx7ca1dV" role="1tU5fm" />
            <node concept="3cmrfG" id="fQbx7ca1hW" role="33vP2m">
              <property role="3cmrfH" value="0" />
            </node>
          </node>
        </node>
        <node concept="3clFbH" id="fQbx7ca1ix" role="3cqZAp" />
        <node concept="2Gpval" id="fQbx7c9Znp" role="3cqZAp">
          <node concept="2GrKxI" id="fQbx7c9Znq" role="2Gsz3X">
            <property role="TrG5h" value="step" />
          </node>
          <node concept="37vLTw" id="fQbx7c9ZoX" role="2GsD0m">
            <ref role="3cqZAo" node="fQbx7c9JqY" resolve="path" />
          </node>
          <node concept="3clFbS" id="fQbx7c9Zns" role="2LFqv$">
            <node concept="3clFbJ" id="fQbx7ca1B7" role="3cqZAp">
              <node concept="3clFbS" id="fQbx7ca1Bp" role="3clFbx">
                <node concept="1Dw8fO" id="fQbx7ca1EZ" role="3cqZAp">
                  <node concept="3cpWsn" id="fQbx7ca1F0" role="1Duv9x">
                    <property role="TrG5h" value="i" />
                    <node concept="10Oyi0" id="fQbx7ca1FC" role="1tU5fm" />
                    <node concept="3cmrfG" id="fQbx7ca1I0" role="33vP2m">
                      <property role="3cmrfH" value="0" />
                    </node>
                  </node>
                  <node concept="3clFbS" id="fQbx7ca1F1" role="2LFqv$">
                    <node concept="3clFbF" id="fQbx7caa6i" role="3cqZAp">
                      <node concept="3uNrnE" id="fQbx7cabNb" role="3clFbG">
                        <node concept="37vLTw" id="fQbx7cabNd" role="2$L3a6">
                          <ref role="3cqZAo" node="fQbx7ca1e0" resolve="currentY" />
                        </node>
                      </node>
                    </node>
                    <node concept="3cpWs8" id="fQbx7ca5Dz" role="3cqZAp">
                      <node concept="3cpWsn" id="fQbx7ca5DA" role="3cpWs9">
                        <property role="TrG5h" value="c" />
                        <node concept="3Tqbb2" id="fQbx7ca5Dy" role="1tU5fm">
                          <ref role="ehGHo" to="b4kx:fQbx7c9y6Z" resolve="Coordinate" />
                        </node>
                        <node concept="2ShNRf" id="fQbx7ca5J8" role="33vP2m">
                          <node concept="3zrR0B" id="fQbx7ca5Ie" role="2ShVmc">
                            <node concept="3Tqbb2" id="fQbx7ca5If" role="3zrR0E">
                              <ref role="ehGHo" to="b4kx:fQbx7c9y6Z" resolve="Coordinate" />
                            </node>
                          </node>
                        </node>
                      </node>
                    </node>
                    <node concept="3clFbF" id="fQbx7ca5Mn" role="3cqZAp">
                      <node concept="2OqwBi" id="fQbx7ca797" role="3clFbG">
                        <node concept="37vLTw" id="fQbx7ca5Ml" role="2Oq$k0">
                          <ref role="3cqZAo" node="fQbx7c9Kip" resolve="coordinates" />
                        </node>
                        <node concept="TSZUe" id="fQbx7ca9GB" role="2OqNvi">
                          <node concept="37vLTw" id="fQbx7ca9RS" role="25WWJ7">
                            <ref role="3cqZAo" node="fQbx7ca5DA" resolve="c" />
                          </node>
                        </node>
                      </node>
                    </node>
                    <node concept="3clFbF" id="fQbx7caa3e" role="3cqZAp">
                      <node concept="37vLTI" id="fQbx7cacZJ" role="3clFbG">
                        <node concept="37vLTw" id="fQbx7cadeO" role="37vLTx">
                          <ref role="3cqZAo" node="fQbx7ca163" resolve="currentX" />
                        </node>
                        <node concept="2OqwBi" id="fQbx7cac4m" role="37vLTJ">
                          <node concept="37vLTw" id="fQbx7caa3c" role="2Oq$k0">
                            <ref role="3cqZAo" node="fQbx7ca5DA" resolve="c" />
                          </node>
                          <node concept="3TrcHB" id="fQbx7cacbX" role="2OqNvi">
                            <ref role="3TsBF5" to="b4kx:fQbx7c9y70" resolve="x" />
                          </node>
                        </node>
                      </node>
                    </node>
                    <node concept="3clFbF" id="fQbx7cadt1" role="3cqZAp">
                      <node concept="37vLTI" id="fQbx7caewr" role="3clFbG">
                        <node concept="37vLTw" id="fQbx7caeJw" role="37vLTx">
                          <ref role="3cqZAo" node="fQbx7ca1e0" resolve="currentY" />
                        </node>
                        <node concept="2OqwBi" id="fQbx7cad$4" role="37vLTJ">
                          <node concept="37vLTw" id="fQbx7cadsZ" role="2Oq$k0">
                            <ref role="3cqZAo" node="fQbx7ca5DA" resolve="c" />
                          </node>
                          <node concept="3TrcHB" id="fQbx7cadGn" role="2OqNvi">
                            <ref role="3TsBF5" to="b4kx:fQbx7c9y74" resolve="y" />
                          </node>
                        </node>
                      </node>
                    </node>
                  </node>
                  <node concept="3eOVzh" id="fQbx7ca2zz" role="1Dwp0S">
                    <node concept="2OqwBi" id="fQbx7ca2ZA" role="3uHU7w">
                      <node concept="2GrUjf" id="fQbx7ca2_e" role="2Oq$k0">
                        <ref role="2Gs0qQ" node="fQbx7c9Znq" resolve="step" />
                      </node>
                      <node concept="3TrcHB" id="fQbx7ca4uG" role="2OqNvi">
                        <ref role="3TsBF5" to="b4kx:fQbx7c7oP9" resolve="steps" />
                      </node>
                    </node>
                    <node concept="37vLTw" id="fQbx7ca1Jt" role="3uHU7B">
                      <ref role="3cqZAo" node="fQbx7ca1F0" resolve="i" />
                    </node>
                  </node>
                  <node concept="3uNrnE" id="fQbx7ca5t0" role="1Dwrff">
                    <node concept="37vLTw" id="fQbx7ca5t2" role="2$L3a6">
                      <ref role="3cqZAo" node="fQbx7ca1F0" resolve="i" />
                    </node>
                  </node>
                </node>
              </node>
              <node concept="2OqwBi" id="fQbx7ca0H0" role="3clFbw">
                <node concept="2OqwBi" id="fQbx7c9ZXE" role="2Oq$k0">
                  <node concept="2OqwBi" id="fQbx7c9Zz4" role="2Oq$k0">
                    <node concept="2GrUjf" id="fQbx7c9Zr0" role="2Oq$k0">
                      <ref role="2Gs0qQ" node="fQbx7c9Znq" resolve="step" />
                    </node>
                    <node concept="2yIwOk" id="fQbx7c9ZF4" role="2OqNvi" />
                  </node>
                  <node concept="3n3YKJ" id="fQbx7ca0ne" role="2OqNvi" />
                </node>
                <node concept="liA8E" id="fQbx7ca0Xv" role="2OqNvi">
                  <ref role="37wK5l" to="wyt6:~String.equalsIgnoreCase(java.lang.String)" resolve="equalsIgnoreCase" />
                  <node concept="Xl_RD" id="fQbx7ca0Z5" role="37wK5m">
                    <property role="Xl_RC" value="U" />
                  </node>
                </node>
              </node>
            </node>
            <node concept="3clFbJ" id="fQbx7caeV7" role="3cqZAp">
              <node concept="3clFbS" id="fQbx7caeV8" role="3clFbx">
                <node concept="1Dw8fO" id="fQbx7caeV9" role="3cqZAp">
                  <node concept="3cpWsn" id="fQbx7caeVa" role="1Duv9x">
                    <property role="TrG5h" value="i" />
                    <node concept="10Oyi0" id="fQbx7caeVb" role="1tU5fm" />
                    <node concept="3cmrfG" id="fQbx7caeVc" role="33vP2m">
                      <property role="3cmrfH" value="0" />
                    </node>
                  </node>
                  <node concept="3clFbS" id="fQbx7caeVd" role="2LFqv$">
                    <node concept="3clFbF" id="fQbx7caeVe" role="3cqZAp">
                      <node concept="3uO5VW" id="fQbx7caglq" role="3clFbG">
                        <node concept="37vLTw" id="fQbx7cagls" role="2$L3a6">
                          <ref role="3cqZAo" node="fQbx7ca1e0" resolve="currentY" />
                        </node>
                      </node>
                    </node>
                    <node concept="3cpWs8" id="fQbx7caeVh" role="3cqZAp">
                      <node concept="3cpWsn" id="fQbx7caeVi" role="3cpWs9">
                        <property role="TrG5h" value="c" />
                        <node concept="3Tqbb2" id="fQbx7caeVj" role="1tU5fm">
                          <ref role="ehGHo" to="b4kx:fQbx7c9y6Z" resolve="Coordinate" />
                        </node>
                        <node concept="2ShNRf" id="fQbx7caeVk" role="33vP2m">
                          <node concept="3zrR0B" id="fQbx7caeVl" role="2ShVmc">
                            <node concept="3Tqbb2" id="fQbx7caeVm" role="3zrR0E">
                              <ref role="ehGHo" to="b4kx:fQbx7c9y6Z" resolve="Coordinate" />
                            </node>
                          </node>
                        </node>
                      </node>
                    </node>
                    <node concept="3clFbF" id="fQbx7caeVn" role="3cqZAp">
                      <node concept="2OqwBi" id="fQbx7caeVo" role="3clFbG">
                        <node concept="37vLTw" id="fQbx7caeVp" role="2Oq$k0">
                          <ref role="3cqZAo" node="fQbx7c9Kip" resolve="coordinates" />
                        </node>
                        <node concept="TSZUe" id="fQbx7caeVq" role="2OqNvi">
                          <node concept="37vLTw" id="fQbx7caeVr" role="25WWJ7">
                            <ref role="3cqZAo" node="fQbx7caeVi" resolve="c" />
                          </node>
                        </node>
                      </node>
                    </node>
                    <node concept="3clFbF" id="fQbx7caeVs" role="3cqZAp">
                      <node concept="37vLTI" id="fQbx7caeVt" role="3clFbG">
                        <node concept="37vLTw" id="fQbx7caeVu" role="37vLTx">
                          <ref role="3cqZAo" node="fQbx7ca163" resolve="currentX" />
                        </node>
                        <node concept="2OqwBi" id="fQbx7caeVv" role="37vLTJ">
                          <node concept="37vLTw" id="fQbx7caeVw" role="2Oq$k0">
                            <ref role="3cqZAo" node="fQbx7caeVi" resolve="c" />
                          </node>
                          <node concept="3TrcHB" id="fQbx7caeVx" role="2OqNvi">
                            <ref role="3TsBF5" to="b4kx:fQbx7c9y70" resolve="x" />
                          </node>
                        </node>
                      </node>
                    </node>
                    <node concept="3clFbF" id="fQbx7caeVy" role="3cqZAp">
                      <node concept="37vLTI" id="fQbx7caeVz" role="3clFbG">
                        <node concept="37vLTw" id="fQbx7caeV$" role="37vLTx">
                          <ref role="3cqZAo" node="fQbx7ca1e0" resolve="currentY" />
                        </node>
                        <node concept="2OqwBi" id="fQbx7caeV_" role="37vLTJ">
                          <node concept="37vLTw" id="fQbx7caeVA" role="2Oq$k0">
                            <ref role="3cqZAo" node="fQbx7caeVi" resolve="c" />
                          </node>
                          <node concept="3TrcHB" id="fQbx7caeVB" role="2OqNvi">
                            <ref role="3TsBF5" to="b4kx:fQbx7c9y74" resolve="y" />
                          </node>
                        </node>
                      </node>
                    </node>
                  </node>
                  <node concept="3eOVzh" id="fQbx7caeVC" role="1Dwp0S">
                    <node concept="2OqwBi" id="fQbx7caeVD" role="3uHU7w">
                      <node concept="2GrUjf" id="fQbx7caeVE" role="2Oq$k0">
                        <ref role="2Gs0qQ" node="fQbx7c9Znq" resolve="step" />
                      </node>
                      <node concept="3TrcHB" id="fQbx7caeVF" role="2OqNvi">
                        <ref role="3TsBF5" to="b4kx:fQbx7c7oP9" resolve="steps" />
                      </node>
                    </node>
                    <node concept="37vLTw" id="fQbx7caeVG" role="3uHU7B">
                      <ref role="3cqZAo" node="fQbx7caeVa" resolve="i" />
                    </node>
                  </node>
                  <node concept="3uNrnE" id="fQbx7caeVH" role="1Dwrff">
                    <node concept="37vLTw" id="fQbx7caeVI" role="2$L3a6">
                      <ref role="3cqZAo" node="fQbx7caeVa" resolve="i" />
                    </node>
                  </node>
                </node>
              </node>
              <node concept="2OqwBi" id="fQbx7caeVJ" role="3clFbw">
                <node concept="2OqwBi" id="fQbx7caeVK" role="2Oq$k0">
                  <node concept="2OqwBi" id="fQbx7caeVL" role="2Oq$k0">
                    <node concept="2GrUjf" id="fQbx7caeVM" role="2Oq$k0">
                      <ref role="2Gs0qQ" node="fQbx7c9Znq" resolve="step" />
                    </node>
                    <node concept="2yIwOk" id="fQbx7caeVN" role="2OqNvi" />
                  </node>
                  <node concept="3n3YKJ" id="fQbx7caeVO" role="2OqNvi" />
                </node>
                <node concept="liA8E" id="fQbx7caeVP" role="2OqNvi">
                  <ref role="37wK5l" to="wyt6:~String.equalsIgnoreCase(java.lang.String)" resolve="equalsIgnoreCase" />
                  <node concept="Xl_RD" id="fQbx7caeVQ" role="37wK5m">
                    <property role="Xl_RC" value="D" />
                  </node>
                </node>
              </node>
            </node>
            <node concept="3clFbJ" id="fQbx7cagAc" role="3cqZAp">
              <node concept="3clFbS" id="fQbx7cagAd" role="3clFbx">
                <node concept="1Dw8fO" id="fQbx7cagAe" role="3cqZAp">
                  <node concept="3cpWsn" id="fQbx7cagAf" role="1Duv9x">
                    <property role="TrG5h" value="i" />
                    <node concept="10Oyi0" id="fQbx7cagAg" role="1tU5fm" />
                    <node concept="3cmrfG" id="fQbx7cagAh" role="33vP2m">
                      <property role="3cmrfH" value="0" />
                    </node>
                  </node>
                  <node concept="3clFbS" id="fQbx7cagAi" role="2LFqv$">
                    <node concept="3clFbF" id="fQbx7cagAj" role="3cqZAp">
                      <node concept="3uO5VW" id="fQbx7cagAk" role="3clFbG">
                        <node concept="37vLTw" id="fQbx7cah$T" role="2$L3a6">
                          <ref role="3cqZAo" node="fQbx7ca163" resolve="currentX" />
                        </node>
                      </node>
                    </node>
                    <node concept="3cpWs8" id="fQbx7cagAm" role="3cqZAp">
                      <node concept="3cpWsn" id="fQbx7cagAn" role="3cpWs9">
                        <property role="TrG5h" value="c" />
                        <node concept="3Tqbb2" id="fQbx7cagAo" role="1tU5fm">
                          <ref role="ehGHo" to="b4kx:fQbx7c9y6Z" resolve="Coordinate" />
                        </node>
                        <node concept="2ShNRf" id="fQbx7cagAp" role="33vP2m">
                          <node concept="3zrR0B" id="fQbx7cagAq" role="2ShVmc">
                            <node concept="3Tqbb2" id="fQbx7cagAr" role="3zrR0E">
                              <ref role="ehGHo" to="b4kx:fQbx7c9y6Z" resolve="Coordinate" />
                            </node>
                          </node>
                        </node>
                      </node>
                    </node>
                    <node concept="3clFbF" id="fQbx7cagAs" role="3cqZAp">
                      <node concept="2OqwBi" id="fQbx7cagAt" role="3clFbG">
                        <node concept="37vLTw" id="fQbx7cagAu" role="2Oq$k0">
                          <ref role="3cqZAo" node="fQbx7c9Kip" resolve="coordinates" />
                        </node>
                        <node concept="TSZUe" id="fQbx7cagAv" role="2OqNvi">
                          <node concept="37vLTw" id="fQbx7cagAw" role="25WWJ7">
                            <ref role="3cqZAo" node="fQbx7cagAn" resolve="c" />
                          </node>
                        </node>
                      </node>
                    </node>
                    <node concept="3clFbF" id="fQbx7cagAx" role="3cqZAp">
                      <node concept="37vLTI" id="fQbx7cagAy" role="3clFbG">
                        <node concept="37vLTw" id="fQbx7cagAz" role="37vLTx">
                          <ref role="3cqZAo" node="fQbx7ca163" resolve="currentX" />
                        </node>
                        <node concept="2OqwBi" id="fQbx7cagA$" role="37vLTJ">
                          <node concept="37vLTw" id="fQbx7cagA_" role="2Oq$k0">
                            <ref role="3cqZAo" node="fQbx7cagAn" resolve="c" />
                          </node>
                          <node concept="3TrcHB" id="fQbx7cagAA" role="2OqNvi">
                            <ref role="3TsBF5" to="b4kx:fQbx7c9y70" resolve="x" />
                          </node>
                        </node>
                      </node>
                    </node>
                    <node concept="3clFbF" id="fQbx7cagAB" role="3cqZAp">
                      <node concept="37vLTI" id="fQbx7cagAC" role="3clFbG">
                        <node concept="37vLTw" id="fQbx7cagAD" role="37vLTx">
                          <ref role="3cqZAo" node="fQbx7ca1e0" resolve="currentY" />
                        </node>
                        <node concept="2OqwBi" id="fQbx7cagAE" role="37vLTJ">
                          <node concept="37vLTw" id="fQbx7cagAF" role="2Oq$k0">
                            <ref role="3cqZAo" node="fQbx7cagAn" resolve="c" />
                          </node>
                          <node concept="3TrcHB" id="fQbx7cagAG" role="2OqNvi">
                            <ref role="3TsBF5" to="b4kx:fQbx7c9y74" resolve="y" />
                          </node>
                        </node>
                      </node>
                    </node>
                  </node>
                  <node concept="3eOVzh" id="fQbx7cagAH" role="1Dwp0S">
                    <node concept="2OqwBi" id="fQbx7cagAI" role="3uHU7w">
                      <node concept="2GrUjf" id="fQbx7cagAJ" role="2Oq$k0">
                        <ref role="2Gs0qQ" node="fQbx7c9Znq" resolve="step" />
                      </node>
                      <node concept="3TrcHB" id="fQbx7cagAK" role="2OqNvi">
                        <ref role="3TsBF5" to="b4kx:fQbx7c7oP9" resolve="steps" />
                      </node>
                    </node>
                    <node concept="37vLTw" id="fQbx7cagAL" role="3uHU7B">
                      <ref role="3cqZAo" node="fQbx7cagAf" resolve="i" />
                    </node>
                  </node>
                  <node concept="3uNrnE" id="fQbx7cagAM" role="1Dwrff">
                    <node concept="37vLTw" id="fQbx7cagAN" role="2$L3a6">
                      <ref role="3cqZAo" node="fQbx7cagAf" resolve="i" />
                    </node>
                  </node>
                </node>
              </node>
              <node concept="2OqwBi" id="fQbx7cagAO" role="3clFbw">
                <node concept="2OqwBi" id="fQbx7cagAP" role="2Oq$k0">
                  <node concept="2OqwBi" id="fQbx7cagAQ" role="2Oq$k0">
                    <node concept="2GrUjf" id="fQbx7cagAR" role="2Oq$k0">
                      <ref role="2Gs0qQ" node="fQbx7c9Znq" resolve="step" />
                    </node>
                    <node concept="2yIwOk" id="fQbx7cagAS" role="2OqNvi" />
                  </node>
                  <node concept="3n3YKJ" id="fQbx7cagAT" role="2OqNvi" />
                </node>
                <node concept="liA8E" id="fQbx7cagAU" role="2OqNvi">
                  <ref role="37wK5l" to="wyt6:~String.equalsIgnoreCase(java.lang.String)" resolve="equalsIgnoreCase" />
                  <node concept="Xl_RD" id="fQbx7cagAV" role="37wK5m">
                    <property role="Xl_RC" value="L" />
                  </node>
                </node>
              </node>
            </node>
            <node concept="3clFbJ" id="fQbx7cahQt" role="3cqZAp">
              <node concept="3clFbS" id="fQbx7cahQu" role="3clFbx">
                <node concept="1Dw8fO" id="fQbx7cahQv" role="3cqZAp">
                  <node concept="3cpWsn" id="fQbx7cahQw" role="1Duv9x">
                    <property role="TrG5h" value="i" />
                    <node concept="10Oyi0" id="fQbx7cahQx" role="1tU5fm" />
                    <node concept="3cmrfG" id="fQbx7cahQy" role="33vP2m">
                      <property role="3cmrfH" value="0" />
                    </node>
                  </node>
                  <node concept="3clFbS" id="fQbx7cahQz" role="2LFqv$">
                    <node concept="3clFbF" id="fQbx7cahQ$" role="3cqZAp">
                      <node concept="3uNrnE" id="fQbx7cajkD" role="3clFbG">
                        <node concept="37vLTw" id="fQbx7cajkF" role="2$L3a6">
                          <ref role="3cqZAo" node="fQbx7ca163" resolve="currentX" />
                        </node>
                      </node>
                    </node>
                    <node concept="3cpWs8" id="fQbx7cahQB" role="3cqZAp">
                      <node concept="3cpWsn" id="fQbx7cahQC" role="3cpWs9">
                        <property role="TrG5h" value="c" />
                        <node concept="3Tqbb2" id="fQbx7cahQD" role="1tU5fm">
                          <ref role="ehGHo" to="b4kx:fQbx7c9y6Z" resolve="Coordinate" />
                        </node>
                        <node concept="2ShNRf" id="fQbx7cahQE" role="33vP2m">
                          <node concept="3zrR0B" id="fQbx7cahQF" role="2ShVmc">
                            <node concept="3Tqbb2" id="fQbx7cahQG" role="3zrR0E">
                              <ref role="ehGHo" to="b4kx:fQbx7c9y6Z" resolve="Coordinate" />
                            </node>
                          </node>
                        </node>
                      </node>
                    </node>
                    <node concept="3clFbF" id="fQbx7cahQH" role="3cqZAp">
                      <node concept="2OqwBi" id="fQbx7cahQI" role="3clFbG">
                        <node concept="37vLTw" id="fQbx7cahQJ" role="2Oq$k0">
                          <ref role="3cqZAo" node="fQbx7c9Kip" resolve="coordinates" />
                        </node>
                        <node concept="TSZUe" id="fQbx7cahQK" role="2OqNvi">
                          <node concept="37vLTw" id="fQbx7cahQL" role="25WWJ7">
                            <ref role="3cqZAo" node="fQbx7cahQC" resolve="c" />
                          </node>
                        </node>
                      </node>
                    </node>
                    <node concept="3clFbF" id="fQbx7cahQM" role="3cqZAp">
                      <node concept="37vLTI" id="fQbx7cahQN" role="3clFbG">
                        <node concept="37vLTw" id="fQbx7cahQO" role="37vLTx">
                          <ref role="3cqZAo" node="fQbx7ca163" resolve="currentX" />
                        </node>
                        <node concept="2OqwBi" id="fQbx7cahQP" role="37vLTJ">
                          <node concept="37vLTw" id="fQbx7cahQQ" role="2Oq$k0">
                            <ref role="3cqZAo" node="fQbx7cahQC" resolve="c" />
                          </node>
                          <node concept="3TrcHB" id="fQbx7cahQR" role="2OqNvi">
                            <ref role="3TsBF5" to="b4kx:fQbx7c9y70" resolve="x" />
                          </node>
                        </node>
                      </node>
                    </node>
                    <node concept="3clFbF" id="fQbx7cahQS" role="3cqZAp">
                      <node concept="37vLTI" id="fQbx7cahQT" role="3clFbG">
                        <node concept="37vLTw" id="fQbx7cahQU" role="37vLTx">
                          <ref role="3cqZAo" node="fQbx7ca1e0" resolve="currentY" />
                        </node>
                        <node concept="2OqwBi" id="fQbx7cahQV" role="37vLTJ">
                          <node concept="37vLTw" id="fQbx7cahQW" role="2Oq$k0">
                            <ref role="3cqZAo" node="fQbx7cahQC" resolve="c" />
                          </node>
                          <node concept="3TrcHB" id="fQbx7cahQX" role="2OqNvi">
                            <ref role="3TsBF5" to="b4kx:fQbx7c9y74" resolve="y" />
                          </node>
                        </node>
                      </node>
                    </node>
                  </node>
                  <node concept="3eOVzh" id="fQbx7cahQY" role="1Dwp0S">
                    <node concept="2OqwBi" id="fQbx7cahQZ" role="3uHU7w">
                      <node concept="2GrUjf" id="fQbx7cahR0" role="2Oq$k0">
                        <ref role="2Gs0qQ" node="fQbx7c9Znq" resolve="step" />
                      </node>
                      <node concept="3TrcHB" id="fQbx7cahR1" role="2OqNvi">
                        <ref role="3TsBF5" to="b4kx:fQbx7c7oP9" resolve="steps" />
                      </node>
                    </node>
                    <node concept="37vLTw" id="fQbx7cahR2" role="3uHU7B">
                      <ref role="3cqZAo" node="fQbx7cahQw" resolve="i" />
                    </node>
                  </node>
                  <node concept="3uNrnE" id="fQbx7cahR3" role="1Dwrff">
                    <node concept="37vLTw" id="fQbx7cahR4" role="2$L3a6">
                      <ref role="3cqZAo" node="fQbx7cahQw" resolve="i" />
                    </node>
                  </node>
                </node>
              </node>
              <node concept="2OqwBi" id="fQbx7cahR5" role="3clFbw">
                <node concept="2OqwBi" id="fQbx7cahR6" role="2Oq$k0">
                  <node concept="2OqwBi" id="fQbx7cahR7" role="2Oq$k0">
                    <node concept="2GrUjf" id="fQbx7cahR8" role="2Oq$k0">
                      <ref role="2Gs0qQ" node="fQbx7c9Znq" resolve="step" />
                    </node>
                    <node concept="2yIwOk" id="fQbx7cahR9" role="2OqNvi" />
                  </node>
                  <node concept="3n3YKJ" id="fQbx7cahRa" role="2OqNvi" />
                </node>
                <node concept="liA8E" id="fQbx7cahRb" role="2OqNvi">
                  <ref role="37wK5l" to="wyt6:~String.equalsIgnoreCase(java.lang.String)" resolve="equalsIgnoreCase" />
                  <node concept="Xl_RD" id="fQbx7cahRc" role="37wK5m">
                    <property role="Xl_RC" value="R" />
                  </node>
                </node>
              </node>
            </node>
            <node concept="3clFbH" id="fQbx7ca1kC" role="3cqZAp" />
          </node>
        </node>
      </node>
      <node concept="3Tm1VV" id="fQbx7c9IHl" role="1B3o_S" />
      <node concept="3cqZAl" id="fQbx7c9II4" role="3clF45" />
      <node concept="37vLTG" id="fQbx7c9JqY" role="3clF46">
        <property role="TrG5h" value="path" />
        <node concept="2I9FWS" id="fQbx7c9JqX" role="1tU5fm">
          <ref role="2I9WkF" to="b4kx:fQbx7c7oP8" resolve="IDirection" />
        </node>
      </node>
      <node concept="37vLTG" id="fQbx7c9Kip" role="3clF46">
        <property role="TrG5h" value="coordinates" />
        <node concept="2I9FWS" id="fQbx7c9Kjm" role="1tU5fm">
          <ref role="2I9WkF" to="b4kx:fQbx7c9y6Z" resolve="Coordinate" />
        </node>
      </node>
    </node>
    <node concept="3Tm1VV" id="fQbx7c9I_w" role="1B3o_S" />
  </node>
</model>

