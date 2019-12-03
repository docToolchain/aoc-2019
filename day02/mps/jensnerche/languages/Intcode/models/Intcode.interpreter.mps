<?xml version="1.0" encoding="UTF-8"?>
<model ref="r:e463d01e-58f3-409b-9e10-6b372437586e(Intcode.interpreter)">
  <persistence version="9" />
  <languages>
    <use id="f3061a53-9226-4cc5-a443-f952ceaf5816" name="jetbrains.mps.baseLanguage" version="8" />
    <devkit ref="fbc25dd2-5da4-483a-8b19-70928e1b62d7(jetbrains.mps.devkit.general-purpose)" />
  </languages>
  <imports>
    <import index="x9r3" ref="r:84dee49f-0f88-4a63-af75-9bc34ebfe2a0(Intcode.structure)" />
    <import index="wyt6" ref="6354ebe7-c22a-4a0f-ac54-50b52ab9b065/java:java.lang(JDK/)" implicit="true" />
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
      <concept id="1215695189714" name="jetbrains.mps.baseLanguage.structure.PlusAssignmentExpression" flags="nn" index="d57v9" />
      <concept id="1202948039474" name="jetbrains.mps.baseLanguage.structure.InstanceMethodCallOperation" flags="nn" index="liA8E" />
      <concept id="1465982738277781862" name="jetbrains.mps.baseLanguage.structure.PlaceholderMember" flags="ng" index="2tJIrI" />
      <concept id="1076505808687" name="jetbrains.mps.baseLanguage.structure.WhileStatement" flags="nn" index="2$JKZl">
        <child id="1076505808688" name="condition" index="2$JKZa" />
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
      <concept id="1081236700937" name="jetbrains.mps.baseLanguage.structure.StaticMethodCall" flags="nn" index="2YIFZM">
        <reference id="1144433194310" name="classConcept" index="1Pybhc" />
      </concept>
      <concept id="1070534370425" name="jetbrains.mps.baseLanguage.structure.IntegerType" flags="in" index="10Oyi0" />
      <concept id="1070534760951" name="jetbrains.mps.baseLanguage.structure.ArrayType" flags="in" index="10Q1$e">
        <child id="1070534760952" name="componentType" index="10Q1$1" />
      </concept>
      <concept id="1068390468198" name="jetbrains.mps.baseLanguage.structure.ClassConcept" flags="ig" index="312cEu" />
      <concept id="1068431474542" name="jetbrains.mps.baseLanguage.structure.VariableDeclaration" flags="ng" index="33uBYm">
        <child id="1068431790190" name="initializer" index="33vP2m" />
      </concept>
      <concept id="1092119917967" name="jetbrains.mps.baseLanguage.structure.MulExpression" flags="nn" index="17qRlL" />
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
      <concept id="1068580123136" name="jetbrains.mps.baseLanguage.structure.StatementList" flags="sn" stub="5293379017992965193" index="3clFbS">
        <child id="1068581517665" name="statement" index="3cqZAp" />
      </concept>
      <concept id="1068580123137" name="jetbrains.mps.baseLanguage.structure.BooleanConstant" flags="nn" index="3clFbT">
        <property id="1068580123138" name="value" index="3clFbU" />
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
      <concept id="1068581517677" name="jetbrains.mps.baseLanguage.structure.VoidType" flags="in" index="3cqZAl" />
      <concept id="1204053956946" name="jetbrains.mps.baseLanguage.structure.IMethodCall" flags="ng" index="1ndlxa">
        <reference id="1068499141037" name="baseMethodDeclaration" index="37wK5l" />
        <child id="1068499141038" name="actualArgument" index="37wK5m" />
      </concept>
      <concept id="1107461130800" name="jetbrains.mps.baseLanguage.structure.Classifier" flags="ng" index="3pOWGL">
        <child id="5375687026011219971" name="member" index="jymVt" unordered="true" />
      </concept>
      <concept id="1107535904670" name="jetbrains.mps.baseLanguage.structure.ClassifierType" flags="in" index="3uibUv">
        <reference id="1107535924139" name="classifier" index="3uigEE" />
      </concept>
      <concept id="1081773326031" name="jetbrains.mps.baseLanguage.structure.BinaryOperation" flags="nn" index="3uHJSO">
        <child id="1081773367579" name="rightExpression" index="3uHU7w" />
        <child id="1081773367580" name="leftExpression" index="3uHU7B" />
      </concept>
      <concept id="1081855346303" name="jetbrains.mps.baseLanguage.structure.BreakStatement" flags="nn" index="3zACq4" />
      <concept id="1178549954367" name="jetbrains.mps.baseLanguage.structure.IVisible" flags="ng" index="1B3ioH">
        <child id="1178549979242" name="visibility" index="1B3o_S" />
      </concept>
      <concept id="1163670490218" name="jetbrains.mps.baseLanguage.structure.SwitchStatement" flags="nn" index="3KaCP$">
        <child id="1163670592366" name="defaultBlock" index="3Kb1Dw" />
        <child id="1163670766145" name="expression" index="3KbGdf" />
        <child id="1163670772911" name="case" index="3KbHQx" />
      </concept>
      <concept id="1163670641947" name="jetbrains.mps.baseLanguage.structure.SwitchCase" flags="ng" index="3KbdKl">
        <child id="1163670677455" name="expression" index="3Kbmr1" />
        <child id="1163670683720" name="body" index="3Kbo56" />
      </concept>
      <concept id="6329021646629104957" name="jetbrains.mps.baseLanguage.structure.TextCommentPart" flags="nn" index="3SKdUq">
        <property id="6329021646629104958" name="text" index="3SKdUp" />
      </concept>
      <concept id="6329021646629104954" name="jetbrains.mps.baseLanguage.structure.SingleLineComment" flags="nn" index="3SKdUt">
        <child id="6329021646629175155" name="commentPart" index="3SKWNk" />
      </concept>
      <concept id="1146644602865" name="jetbrains.mps.baseLanguage.structure.PublicVisibility" flags="nn" index="3Tm1VV" />
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
      <concept id="1133920641626" name="jetbrains.mps.lang.core.structure.BaseConcept" flags="ng" index="2VYdi">
        <child id="5169995583184591170" name="smodelAttribute" index="lGtFl" />
      </concept>
      <concept id="1169194658468" name="jetbrains.mps.lang.core.structure.INamedConcept" flags="ng" index="TrEIO">
        <property id="1169194664001" name="name" index="TrG5h" />
      </concept>
      <concept id="709746936026466394" name="jetbrains.mps.lang.core.structure.ChildAttribute" flags="ng" index="3VBwX9">
        <property id="709746936026609031" name="linkId" index="3V$3ak" />
        <property id="709746936026609029" name="role_DebugInfo" index="3V$3am" />
      </concept>
      <concept id="4452961908202556907" name="jetbrains.mps.lang.core.structure.BaseCommentAttribute" flags="ng" index="1X3_iC">
        <child id="3078666699043039389" name="commentedNode" index="8Wnug" />
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
      <concept id="1162934736510" name="jetbrains.mps.baseLanguage.collections.structure.GetElementOperation" flags="nn" index="34jXtK" />
    </language>
  </registry>
  <node concept="312cEu" id="32nSEEspNRE">
    <property role="TrG5h" value="IntcodeInterpreter" />
    <node concept="2tJIrI" id="32nSEEspNSC" role="jymVt" />
    <node concept="2YIFZL" id="32nSEEspNT_" role="jymVt">
      <property role="TrG5h" value="interpret" />
      <node concept="3clFbS" id="32nSEEspNTC" role="3clF47">
        <node concept="3SKdUt" id="32nSEEsq1nK" role="3cqZAp">
          <node concept="3SKdUq" id="32nSEEsq1nM" role="3SKWNk">
            <property role="3SKdUp" value="before running the program, replace position 1 with the value 12 and replace position 2 with the value 2" />
          </node>
        </node>
        <node concept="1X3_iC" id="32nSEEsq8Cq" role="lGtFl">
          <property role="3V$3am" value="statement" />
          <property role="3V$3ak" value="f3061a53-9226-4cc5-a443-f952ceaf5816/1068580123136/1068581517665" />
          <node concept="3clFbF" id="32nSEEsq1qh" role="8Wnug">
            <node concept="37vLTI" id="32nSEEsq7kG" role="3clFbG">
              <node concept="3cmrfG" id="32nSEEsq7qs" role="37vLTx">
                <property role="3cmrfH" value="12" />
              </node>
              <node concept="2OqwBi" id="32nSEEsq63x" role="37vLTJ">
                <node concept="2OqwBi" id="32nSEEsq3iy" role="2Oq$k0">
                  <node concept="2OqwBi" id="32nSEEsq1zd" role="2Oq$k0">
                    <node concept="37vLTw" id="32nSEEsq1qf" role="2Oq$k0">
                      <ref role="3cqZAo" node="32nSEEspNU1" resolve="intcodeProgram" />
                    </node>
                    <node concept="3Tsc0h" id="32nSEEsq1Oo" role="2OqNvi">
                      <ref role="3TtcxE" to="x9r3:32nSEEspL_h" resolve="memory" />
                    </node>
                  </node>
                  <node concept="34jXtK" id="32nSEEsq5Q$" role="2OqNvi">
                    <node concept="3cmrfG" id="32nSEEsq5Tt" role="25WWJ7">
                      <property role="3cmrfH" value="1" />
                    </node>
                  </node>
                </node>
                <node concept="3TrcHB" id="32nSEEsq6ne" role="2OqNvi">
                  <ref role="3TsBF5" to="x9r3:32nSEEspL$L" resolve="content" />
                </node>
              </node>
            </node>
          </node>
        </node>
        <node concept="1X3_iC" id="32nSEEsq8Ed" role="lGtFl">
          <property role="3V$3am" value="statement" />
          <property role="3V$3ak" value="f3061a53-9226-4cc5-a443-f952ceaf5816/1068580123136/1068581517665" />
          <node concept="3clFbF" id="32nSEEsq7ug" role="8Wnug">
            <node concept="37vLTI" id="32nSEEsq7uh" role="3clFbG">
              <node concept="3cmrfG" id="32nSEEsq7ui" role="37vLTx">
                <property role="3cmrfH" value="2" />
              </node>
              <node concept="2OqwBi" id="32nSEEsq7uj" role="37vLTJ">
                <node concept="2OqwBi" id="32nSEEsq7uk" role="2Oq$k0">
                  <node concept="2OqwBi" id="32nSEEsq7ul" role="2Oq$k0">
                    <node concept="37vLTw" id="32nSEEsq7um" role="2Oq$k0">
                      <ref role="3cqZAo" node="32nSEEspNU1" resolve="intcodeProgram" />
                    </node>
                    <node concept="3Tsc0h" id="32nSEEsq7un" role="2OqNvi">
                      <ref role="3TtcxE" to="x9r3:32nSEEspL_h" resolve="memory" />
                    </node>
                  </node>
                  <node concept="34jXtK" id="32nSEEsq7uo" role="2OqNvi">
                    <node concept="3cmrfG" id="32nSEEsq7HA" role="25WWJ7">
                      <property role="3cmrfH" value="2" />
                    </node>
                  </node>
                </node>
                <node concept="3TrcHB" id="32nSEEsq7uq" role="2OqNvi">
                  <ref role="3TsBF5" to="x9r3:32nSEEspL$L" resolve="content" />
                </node>
              </node>
            </node>
          </node>
        </node>
        <node concept="3clFbH" id="32nSEEsq7t1" role="3cqZAp" />
        <node concept="3cpWs8" id="32nSEEspWes" role="3cqZAp">
          <node concept="3cpWsn" id="32nSEEspWev" role="3cpWs9">
            <property role="TrG5h" value="instructionPointer" />
            <node concept="10Oyi0" id="32nSEEspWer" role="1tU5fm" />
            <node concept="3cmrfG" id="32nSEEspWg4" role="33vP2m">
              <property role="3cmrfH" value="0" />
            </node>
          </node>
        </node>
        <node concept="3clFbH" id="32nSEEsqOKD" role="3cqZAp" />
        <node concept="2$JKZl" id="32nSEEsqOSL" role="3cqZAp">
          <node concept="3clFbS" id="32nSEEsqOSN" role="2LFqv$">
            <node concept="3cpWs8" id="32nSEEspZzt" role="3cqZAp">
              <node concept="3cpWsn" id="32nSEEspZzu" role="3cpWs9">
                <property role="TrG5h" value="opcode" />
                <node concept="10Oyi0" id="32nSEEsq0fL" role="1tU5fm" />
                <node concept="2OqwBi" id="32nSEEspZIw" role="33vP2m">
                  <node concept="2OqwBi" id="32nSEEspZzv" role="2Oq$k0">
                    <node concept="2OqwBi" id="32nSEEspZzw" role="2Oq$k0">
                      <node concept="37vLTw" id="32nSEEspZzx" role="2Oq$k0">
                        <ref role="3cqZAo" node="32nSEEspNU1" resolve="intcodeProgram" />
                      </node>
                      <node concept="3Tsc0h" id="32nSEEspZzy" role="2OqNvi">
                        <ref role="3TtcxE" to="x9r3:32nSEEspL_h" resolve="memory" />
                      </node>
                    </node>
                    <node concept="34jXtK" id="32nSEEspZzz" role="2OqNvi">
                      <node concept="37vLTw" id="32nSEEsq1cH" role="25WWJ7">
                        <ref role="3cqZAo" node="32nSEEspWev" resolve="instructionPointer" />
                      </node>
                    </node>
                  </node>
                  <node concept="3TrcHB" id="32nSEEspZUm" role="2OqNvi">
                    <ref role="3TsBF5" to="x9r3:32nSEEspL$L" resolve="content" />
                  </node>
                </node>
              </node>
            </node>
            <node concept="3KaCP$" id="32nSEEsq0s7" role="3cqZAp">
              <node concept="3KbdKl" id="32nSEEsq0ti" role="3KbHQx">
                <node concept="3cmrfG" id="32nSEEsq0tV" role="3Kbmr1">
                  <property role="3cmrfH" value="1" />
                </node>
                <node concept="3clFbS" id="32nSEEsq0tk" role="3Kbo56">
                  <node concept="9aQIb" id="32nSEEsq_Zg" role="3cqZAp">
                    <node concept="3clFbS" id="32nSEEsq_Zi" role="9aQI4">
                      <node concept="3SKdUt" id="32nSEEsq0up" role="3cqZAp">
                        <node concept="3SKdUq" id="32nSEEsq0uq" role="3SKWNk">
                          <property role="3SKdUp" value="add" />
                        </node>
                      </node>
                      <node concept="3cpWs8" id="32nSEEsqkOy" role="3cqZAp">
                        <node concept="3cpWsn" id="32nSEEsqkOz" role="3cpWs9">
                          <property role="TrG5h" value="readAddress1" />
                          <node concept="10Oyi0" id="32nSEEsqkOo" role="1tU5fm" />
                          <node concept="2OqwBi" id="32nSEEsqkO$" role="33vP2m">
                            <node concept="2OqwBi" id="32nSEEsqkO_" role="2Oq$k0">
                              <node concept="2OqwBi" id="32nSEEsqkOA" role="2Oq$k0">
                                <node concept="37vLTw" id="32nSEEsqkOB" role="2Oq$k0">
                                  <ref role="3cqZAo" node="32nSEEspNU1" resolve="intcodeProgram" />
                                </node>
                                <node concept="3Tsc0h" id="32nSEEsqkOC" role="2OqNvi">
                                  <ref role="3TtcxE" to="x9r3:32nSEEspL_h" resolve="memory" />
                                </node>
                              </node>
                              <node concept="34jXtK" id="32nSEEsqkOD" role="2OqNvi">
                                <node concept="3cpWs3" id="32nSEEsqkOE" role="25WWJ7">
                                  <node concept="3cmrfG" id="32nSEEsqkOF" role="3uHU7w">
                                    <property role="3cmrfH" value="1" />
                                  </node>
                                  <node concept="37vLTw" id="32nSEEsqkOG" role="3uHU7B">
                                    <ref role="3cqZAo" node="32nSEEspWev" resolve="instructionPointer" />
                                  </node>
                                </node>
                              </node>
                            </node>
                            <node concept="3TrcHB" id="32nSEEsqkOH" role="2OqNvi">
                              <ref role="3TsBF5" to="x9r3:32nSEEspL$L" resolve="content" />
                            </node>
                          </node>
                        </node>
                      </node>
                      <node concept="3cpWs8" id="32nSEEsql9s" role="3cqZAp">
                        <node concept="3cpWsn" id="32nSEEsql9t" role="3cpWs9">
                          <property role="TrG5h" value="readAddress2" />
                          <node concept="10Oyi0" id="32nSEEsql9u" role="1tU5fm" />
                          <node concept="2OqwBi" id="32nSEEsql9v" role="33vP2m">
                            <node concept="2OqwBi" id="32nSEEsql9w" role="2Oq$k0">
                              <node concept="2OqwBi" id="32nSEEsql9x" role="2Oq$k0">
                                <node concept="37vLTw" id="32nSEEsql9y" role="2Oq$k0">
                                  <ref role="3cqZAo" node="32nSEEspNU1" resolve="intcodeProgram" />
                                </node>
                                <node concept="3Tsc0h" id="32nSEEsql9z" role="2OqNvi">
                                  <ref role="3TtcxE" to="x9r3:32nSEEspL_h" resolve="memory" />
                                </node>
                              </node>
                              <node concept="34jXtK" id="32nSEEsql9$" role="2OqNvi">
                                <node concept="3cpWs3" id="32nSEEsql9_" role="25WWJ7">
                                  <node concept="37vLTw" id="32nSEEsql9B" role="3uHU7B">
                                    <ref role="3cqZAo" node="32nSEEspWev" resolve="instructionPointer" />
                                  </node>
                                  <node concept="3cmrfG" id="32nSEEsqltM" role="3uHU7w">
                                    <property role="3cmrfH" value="2" />
                                  </node>
                                </node>
                              </node>
                            </node>
                            <node concept="3TrcHB" id="32nSEEsql9C" role="2OqNvi">
                              <ref role="3TsBF5" to="x9r3:32nSEEspL$L" resolve="content" />
                            </node>
                          </node>
                        </node>
                      </node>
                      <node concept="3cpWs8" id="32nSEEsqlKw" role="3cqZAp">
                        <node concept="3cpWsn" id="32nSEEsqlKx" role="3cpWs9">
                          <property role="TrG5h" value="writeAddress" />
                          <node concept="10Oyi0" id="32nSEEsqlKy" role="1tU5fm" />
                          <node concept="2OqwBi" id="32nSEEsqlKz" role="33vP2m">
                            <node concept="2OqwBi" id="32nSEEsqlK$" role="2Oq$k0">
                              <node concept="2OqwBi" id="32nSEEsqlK_" role="2Oq$k0">
                                <node concept="37vLTw" id="32nSEEsqlKA" role="2Oq$k0">
                                  <ref role="3cqZAo" node="32nSEEspNU1" resolve="intcodeProgram" />
                                </node>
                                <node concept="3Tsc0h" id="32nSEEsqlKB" role="2OqNvi">
                                  <ref role="3TtcxE" to="x9r3:32nSEEspL_h" resolve="memory" />
                                </node>
                              </node>
                              <node concept="34jXtK" id="32nSEEsqlKC" role="2OqNvi">
                                <node concept="3cpWs3" id="32nSEEsqlKD" role="25WWJ7">
                                  <node concept="37vLTw" id="32nSEEsqlKE" role="3uHU7B">
                                    <ref role="3cqZAo" node="32nSEEspWev" resolve="instructionPointer" />
                                  </node>
                                  <node concept="3cmrfG" id="32nSEEsqm5E" role="3uHU7w">
                                    <property role="3cmrfH" value="3" />
                                  </node>
                                </node>
                              </node>
                            </node>
                            <node concept="3TrcHB" id="32nSEEsqlKG" role="2OqNvi">
                              <ref role="3TsBF5" to="x9r3:32nSEEspL$L" resolve="content" />
                            </node>
                          </node>
                        </node>
                      </node>
                      <node concept="3cpWs8" id="32nSEEsq8Gf" role="3cqZAp">
                        <node concept="3cpWsn" id="32nSEEsq8Gi" role="3cpWs9">
                          <property role="TrG5h" value="akku" />
                          <node concept="10Oyi0" id="32nSEEsq8Gd" role="1tU5fm" />
                          <node concept="3cpWs3" id="32nSEEsqm$K" role="33vP2m">
                            <node concept="2OqwBi" id="32nSEEsqsby" role="3uHU7w">
                              <node concept="2OqwBi" id="32nSEEsqpgn" role="2Oq$k0">
                                <node concept="2OqwBi" id="32nSEEsqmT3" role="2Oq$k0">
                                  <node concept="37vLTw" id="32nSEEsqmCI" role="2Oq$k0">
                                    <ref role="3cqZAo" node="32nSEEspNU1" resolve="intcodeProgram" />
                                  </node>
                                  <node concept="3Tsc0h" id="32nSEEsqndj" role="2OqNvi">
                                    <ref role="3TtcxE" to="x9r3:32nSEEspL_h" resolve="memory" />
                                  </node>
                                </node>
                                <node concept="34jXtK" id="32nSEEsqrPu" role="2OqNvi">
                                  <node concept="37vLTw" id="32nSEEsqrX7" role="25WWJ7">
                                    <ref role="3cqZAo" node="32nSEEsql9t" resolve="readAddress2" />
                                  </node>
                                </node>
                              </node>
                              <node concept="3TrcHB" id="32nSEEsqswg" role="2OqNvi">
                                <ref role="3TsBF5" to="x9r3:32nSEEspL$L" resolve="content" />
                              </node>
                            </node>
                            <node concept="2OqwBi" id="32nSEEsqsS6" role="3uHU7B">
                              <node concept="2OqwBi" id="32nSEEsqaRI" role="2Oq$k0">
                                <node concept="2OqwBi" id="32nSEEsq8RU" role="2Oq$k0">
                                  <node concept="37vLTw" id="32nSEEsq8HS" role="2Oq$k0">
                                    <ref role="3cqZAo" node="32nSEEspNU1" resolve="intcodeProgram" />
                                  </node>
                                  <node concept="3Tsc0h" id="32nSEEsq99a" role="2OqNvi">
                                    <ref role="3TtcxE" to="x9r3:32nSEEspL_h" resolve="memory" />
                                  </node>
                                </node>
                                <node concept="34jXtK" id="32nSEEsqdrN" role="2OqNvi">
                                  <node concept="37vLTw" id="32nSEEsqmoI" role="25WWJ7">
                                    <ref role="3cqZAo" node="32nSEEsqkOz" resolve="readAddress1" />
                                  </node>
                                </node>
                              </node>
                              <node concept="3TrcHB" id="32nSEEsqtc0" role="2OqNvi">
                                <ref role="3TsBF5" to="x9r3:32nSEEspL$L" resolve="content" />
                              </node>
                            </node>
                          </node>
                        </node>
                      </node>
                      <node concept="3clFbF" id="32nSEEsqtwn" role="3cqZAp">
                        <node concept="2OqwBi" id="32nSEEsqJY3" role="3clFbG">
                          <node concept="2OqwBi" id="32nSEEsqyaI" role="2Oq$k0">
                            <node concept="2OqwBi" id="32nSEEsqvnN" role="2Oq$k0">
                              <node concept="2OqwBi" id="32nSEEsqtDF" role="2Oq$k0">
                                <node concept="37vLTw" id="32nSEEsqtwl" role="2Oq$k0">
                                  <ref role="3cqZAo" node="32nSEEspNU1" resolve="intcodeProgram" />
                                </node>
                                <node concept="3Tsc0h" id="32nSEEsqtMI" role="2OqNvi">
                                  <ref role="3TtcxE" to="x9r3:32nSEEspL_h" resolve="memory" />
                                </node>
                              </node>
                              <node concept="34jXtK" id="32nSEEsqxW1" role="2OqNvi">
                                <node concept="37vLTw" id="32nSEEsqxYU" role="25WWJ7">
                                  <ref role="3cqZAo" node="32nSEEsqlKx" resolve="writeAddress" />
                                </node>
                              </node>
                            </node>
                            <node concept="3TrcHB" id="32nSEEsqyuA" role="2OqNvi">
                              <ref role="3TsBF5" to="x9r3:32nSEEspL$L" resolve="content" />
                            </node>
                          </node>
                          <node concept="tyxLq" id="32nSEEsqKQD" role="2OqNvi">
                            <node concept="37vLTw" id="32nSEEsqL5B" role="tz02z">
                              <ref role="3cqZAo" node="32nSEEsq8Gi" resolve="akku" />
                            </node>
                          </node>
                        </node>
                      </node>
                      <node concept="3zACq4" id="32nSEEsqX33" role="3cqZAp" />
                    </node>
                  </node>
                </node>
              </node>
              <node concept="3KbdKl" id="32nSEEsq0u_" role="3KbHQx">
                <node concept="3cmrfG" id="32nSEEsq0vs" role="3Kbmr1">
                  <property role="3cmrfH" value="2" />
                </node>
                <node concept="3clFbS" id="32nSEEsq0uB" role="3Kbo56">
                  <node concept="9aQIb" id="32nSEEsqAvH" role="3cqZAp">
                    <node concept="3clFbS" id="32nSEEsqAvJ" role="9aQI4">
                      <node concept="3SKdUt" id="32nSEEsq0vY" role="3cqZAp">
                        <node concept="3SKdUq" id="32nSEEsq0vZ" role="3SKWNk">
                          <property role="3SKdUp" value="mult" />
                        </node>
                      </node>
                      <node concept="3cpWs8" id="32nSEEsqzY5" role="3cqZAp">
                        <node concept="3cpWsn" id="32nSEEsqzY6" role="3cpWs9">
                          <property role="TrG5h" value="readAddress1" />
                          <node concept="10Oyi0" id="32nSEEsqzY7" role="1tU5fm" />
                          <node concept="2OqwBi" id="32nSEEsqzY8" role="33vP2m">
                            <node concept="2OqwBi" id="32nSEEsqzY9" role="2Oq$k0">
                              <node concept="2OqwBi" id="32nSEEsqzYa" role="2Oq$k0">
                                <node concept="37vLTw" id="32nSEEsqzYb" role="2Oq$k0">
                                  <ref role="3cqZAo" node="32nSEEspNU1" resolve="intcodeProgram" />
                                </node>
                                <node concept="3Tsc0h" id="32nSEEsqzYc" role="2OqNvi">
                                  <ref role="3TtcxE" to="x9r3:32nSEEspL_h" resolve="memory" />
                                </node>
                              </node>
                              <node concept="34jXtK" id="32nSEEsqzYd" role="2OqNvi">
                                <node concept="3cpWs3" id="32nSEEsqzYe" role="25WWJ7">
                                  <node concept="3cmrfG" id="32nSEEsqzYf" role="3uHU7w">
                                    <property role="3cmrfH" value="1" />
                                  </node>
                                  <node concept="37vLTw" id="32nSEEsqzYg" role="3uHU7B">
                                    <ref role="3cqZAo" node="32nSEEspWev" resolve="instructionPointer" />
                                  </node>
                                </node>
                              </node>
                            </node>
                            <node concept="3TrcHB" id="32nSEEsqzYh" role="2OqNvi">
                              <ref role="3TsBF5" to="x9r3:32nSEEspL$L" resolve="content" />
                            </node>
                          </node>
                        </node>
                      </node>
                      <node concept="3cpWs8" id="32nSEEsqzYi" role="3cqZAp">
                        <node concept="3cpWsn" id="32nSEEsqzYj" role="3cpWs9">
                          <property role="TrG5h" value="readAddress2" />
                          <node concept="10Oyi0" id="32nSEEsqzYk" role="1tU5fm" />
                          <node concept="2OqwBi" id="32nSEEsqzYl" role="33vP2m">
                            <node concept="2OqwBi" id="32nSEEsqzYm" role="2Oq$k0">
                              <node concept="2OqwBi" id="32nSEEsqzYn" role="2Oq$k0">
                                <node concept="37vLTw" id="32nSEEsqzYo" role="2Oq$k0">
                                  <ref role="3cqZAo" node="32nSEEspNU1" resolve="intcodeProgram" />
                                </node>
                                <node concept="3Tsc0h" id="32nSEEsqzYp" role="2OqNvi">
                                  <ref role="3TtcxE" to="x9r3:32nSEEspL_h" resolve="memory" />
                                </node>
                              </node>
                              <node concept="34jXtK" id="32nSEEsqzYq" role="2OqNvi">
                                <node concept="3cpWs3" id="32nSEEsqzYr" role="25WWJ7">
                                  <node concept="37vLTw" id="32nSEEsqzYs" role="3uHU7B">
                                    <ref role="3cqZAo" node="32nSEEspWev" resolve="instructionPointer" />
                                  </node>
                                  <node concept="3cmrfG" id="32nSEEsqzYt" role="3uHU7w">
                                    <property role="3cmrfH" value="2" />
                                  </node>
                                </node>
                              </node>
                            </node>
                            <node concept="3TrcHB" id="32nSEEsqzYu" role="2OqNvi">
                              <ref role="3TsBF5" to="x9r3:32nSEEspL$L" resolve="content" />
                            </node>
                          </node>
                        </node>
                      </node>
                      <node concept="3cpWs8" id="32nSEEsqzYv" role="3cqZAp">
                        <node concept="3cpWsn" id="32nSEEsqzYw" role="3cpWs9">
                          <property role="TrG5h" value="writeAddress" />
                          <node concept="10Oyi0" id="32nSEEsqzYx" role="1tU5fm" />
                          <node concept="2OqwBi" id="32nSEEsqzYy" role="33vP2m">
                            <node concept="2OqwBi" id="32nSEEsqzYz" role="2Oq$k0">
                              <node concept="2OqwBi" id="32nSEEsqzY$" role="2Oq$k0">
                                <node concept="37vLTw" id="32nSEEsqzY_" role="2Oq$k0">
                                  <ref role="3cqZAo" node="32nSEEspNU1" resolve="intcodeProgram" />
                                </node>
                                <node concept="3Tsc0h" id="32nSEEsqzYA" role="2OqNvi">
                                  <ref role="3TtcxE" to="x9r3:32nSEEspL_h" resolve="memory" />
                                </node>
                              </node>
                              <node concept="34jXtK" id="32nSEEsqzYB" role="2OqNvi">
                                <node concept="3cpWs3" id="32nSEEsqzYC" role="25WWJ7">
                                  <node concept="37vLTw" id="32nSEEsqzYD" role="3uHU7B">
                                    <ref role="3cqZAo" node="32nSEEspWev" resolve="instructionPointer" />
                                  </node>
                                  <node concept="3cmrfG" id="32nSEEsqzYE" role="3uHU7w">
                                    <property role="3cmrfH" value="3" />
                                  </node>
                                </node>
                              </node>
                            </node>
                            <node concept="3TrcHB" id="32nSEEsqzYF" role="2OqNvi">
                              <ref role="3TsBF5" to="x9r3:32nSEEspL$L" resolve="content" />
                            </node>
                          </node>
                        </node>
                      </node>
                      <node concept="3cpWs8" id="32nSEEsqzYG" role="3cqZAp">
                        <node concept="3cpWsn" id="32nSEEsqzYH" role="3cpWs9">
                          <property role="TrG5h" value="akku" />
                          <node concept="10Oyi0" id="32nSEEsqzYI" role="1tU5fm" />
                          <node concept="17qRlL" id="32nSEEsqAYb" role="33vP2m">
                            <node concept="2OqwBi" id="32nSEEsqzYS" role="3uHU7B">
                              <node concept="2OqwBi" id="32nSEEsqzYT" role="2Oq$k0">
                                <node concept="2OqwBi" id="32nSEEsqzYU" role="2Oq$k0">
                                  <node concept="37vLTw" id="32nSEEsqzYV" role="2Oq$k0">
                                    <ref role="3cqZAo" node="32nSEEspNU1" resolve="intcodeProgram" />
                                  </node>
                                  <node concept="3Tsc0h" id="32nSEEsqzYW" role="2OqNvi">
                                    <ref role="3TtcxE" to="x9r3:32nSEEspL_h" resolve="memory" />
                                  </node>
                                </node>
                                <node concept="34jXtK" id="32nSEEsqzYX" role="2OqNvi">
                                  <node concept="37vLTw" id="32nSEEsqzYY" role="25WWJ7">
                                    <ref role="3cqZAo" node="32nSEEsqzY6" resolve="readAddress1" />
                                  </node>
                                </node>
                              </node>
                              <node concept="3TrcHB" id="32nSEEsqzYZ" role="2OqNvi">
                                <ref role="3TsBF5" to="x9r3:32nSEEspL$L" resolve="content" />
                              </node>
                            </node>
                            <node concept="2OqwBi" id="32nSEEsqzYK" role="3uHU7w">
                              <node concept="2OqwBi" id="32nSEEsqzYL" role="2Oq$k0">
                                <node concept="2OqwBi" id="32nSEEsqzYM" role="2Oq$k0">
                                  <node concept="37vLTw" id="32nSEEsqzYN" role="2Oq$k0">
                                    <ref role="3cqZAo" node="32nSEEspNU1" resolve="intcodeProgram" />
                                  </node>
                                  <node concept="3Tsc0h" id="32nSEEsqzYO" role="2OqNvi">
                                    <ref role="3TtcxE" to="x9r3:32nSEEspL_h" resolve="memory" />
                                  </node>
                                </node>
                                <node concept="34jXtK" id="32nSEEsqzYP" role="2OqNvi">
                                  <node concept="37vLTw" id="32nSEEsqzYQ" role="25WWJ7">
                                    <ref role="3cqZAo" node="32nSEEsqzYj" resolve="readAddress2" />
                                  </node>
                                </node>
                              </node>
                              <node concept="3TrcHB" id="32nSEEsqzYR" role="2OqNvi">
                                <ref role="3TsBF5" to="x9r3:32nSEEspL$L" resolve="content" />
                              </node>
                            </node>
                          </node>
                        </node>
                      </node>
                      <node concept="3clFbF" id="32nSEEsqzZ0" role="3cqZAp">
                        <node concept="2OqwBi" id="32nSEEsqM9W" role="3clFbG">
                          <node concept="2OqwBi" id="32nSEEsqzZ3" role="2Oq$k0">
                            <node concept="2OqwBi" id="32nSEEsqzZ4" role="2Oq$k0">
                              <node concept="2OqwBi" id="32nSEEsqzZ5" role="2Oq$k0">
                                <node concept="37vLTw" id="32nSEEsqzZ6" role="2Oq$k0">
                                  <ref role="3cqZAo" node="32nSEEspNU1" resolve="intcodeProgram" />
                                </node>
                                <node concept="3Tsc0h" id="32nSEEsqzZ7" role="2OqNvi">
                                  <ref role="3TtcxE" to="x9r3:32nSEEspL_h" resolve="memory" />
                                </node>
                              </node>
                              <node concept="34jXtK" id="32nSEEsqzZ8" role="2OqNvi">
                                <node concept="37vLTw" id="32nSEEsqzZ9" role="25WWJ7">
                                  <ref role="3cqZAo" node="32nSEEsqzYw" resolve="writeAddress" />
                                </node>
                              </node>
                            </node>
                            <node concept="3TrcHB" id="32nSEEsqzZa" role="2OqNvi">
                              <ref role="3TsBF5" to="x9r3:32nSEEspL$L" resolve="content" />
                            </node>
                          </node>
                          <node concept="tyxLq" id="32nSEEsqMFx" role="2OqNvi">
                            <node concept="37vLTw" id="32nSEEsqMRW" role="tz02z">
                              <ref role="3cqZAo" node="32nSEEsqzYH" resolve="akku" />
                            </node>
                          </node>
                        </node>
                      </node>
                      <node concept="3zACq4" id="32nSEEsqX6h" role="3cqZAp" />
                    </node>
                  </node>
                  <node concept="3clFbH" id="32nSEEsqzXT" role="3cqZAp" />
                </node>
              </node>
              <node concept="3KbdKl" id="32nSEEsq0wa" role="3KbHQx">
                <node concept="3cmrfG" id="32nSEEsq0xd" role="3Kbmr1">
                  <property role="3cmrfH" value="99" />
                </node>
                <node concept="3clFbS" id="32nSEEsq0wc" role="3Kbo56">
                  <node concept="3SKdUt" id="32nSEEsq0y8" role="3cqZAp">
                    <node concept="3SKdUq" id="32nSEEsq0ya" role="3SKWNk">
                      <property role="3SKdUp" value="stop" />
                    </node>
                  </node>
                  <node concept="2xdQw9" id="32nSEEsqJ05" role="3cqZAp">
                    <property role="2xdLsb" value="info" />
                    <node concept="Xl_RD" id="32nSEEsqJ07" role="9lYJi">
                      <property role="Xl_RC" value="Stop" />
                    </node>
                  </node>
                  <node concept="3cpWs6" id="32nSEEsq0_S" role="3cqZAp" />
                </node>
              </node>
              <node concept="37vLTw" id="32nSEEsq0sW" role="3KbGdf">
                <ref role="3cqZAo" node="32nSEEspZzu" resolve="opcode" />
              </node>
              <node concept="3clFbS" id="32nSEEsq0Ck" role="3Kb1Dw">
                <node concept="2xdQw9" id="32nSEEsq0Do" role="3cqZAp">
                  <property role="2xdLsb" value="error" />
                  <node concept="3cpWs3" id="32nSEEsq0Wu" role="9lYJi">
                    <node concept="37vLTw" id="32nSEEsq0X7" role="3uHU7w">
                      <ref role="3cqZAo" node="32nSEEspZzu" resolve="opcode" />
                    </node>
                    <node concept="Xl_RD" id="32nSEEsq0Dq" role="3uHU7B">
                      <property role="Xl_RC" value="Execution error: " />
                    </node>
                  </node>
                </node>
                <node concept="3zACq4" id="32nSEEsqX8c" role="3cqZAp" />
              </node>
            </node>
            <node concept="3clFbH" id="32nSEEsqNaK" role="3cqZAp" />
            <node concept="3clFbF" id="32nSEEsqNjy" role="3cqZAp">
              <node concept="d57v9" id="32nSEEsqOb$" role="3clFbG">
                <node concept="3cmrfG" id="32nSEEsqOcw" role="37vLTx">
                  <property role="3cmrfH" value="4" />
                </node>
                <node concept="37vLTw" id="32nSEEsqNjw" role="37vLTJ">
                  <ref role="3cqZAo" node="32nSEEspWev" resolve="instructionPointer" />
                </node>
              </node>
            </node>
          </node>
          <node concept="3clFbT" id="32nSEEsqOXv" role="2$JKZa">
            <property role="3clFbU" value="true" />
          </node>
        </node>
      </node>
      <node concept="3Tm1VV" id="32nSEEspNT1" role="1B3o_S" />
      <node concept="3cqZAl" id="32nSEEspNTq" role="3clF45" />
      <node concept="37vLTG" id="32nSEEspNU1" role="3clF46">
        <property role="TrG5h" value="intcodeProgram" />
        <node concept="3Tqbb2" id="32nSEEspNU0" role="1tU5fm">
          <ref role="ehGHo" to="x9r3:32nSEEspKJr" resolve="IntcodeProgram" />
        </node>
      </node>
    </node>
    <node concept="2tJIrI" id="32nSEEss1Cm" role="jymVt" />
    <node concept="2YIFZL" id="32nSEEss1QC" role="jymVt">
      <property role="TrG5h" value="reinitializeMemory" />
      <node concept="3clFbS" id="32nSEEss1QF" role="3clF47">
        <node concept="3clFbF" id="32nSEEss54u" role="3cqZAp">
          <node concept="2OqwBi" id="32nSEEss766" role="3clFbG">
            <node concept="2OqwBi" id="32nSEEss5m2" role="2Oq$k0">
              <node concept="37vLTw" id="32nSEEss54s" role="2Oq$k0">
                <ref role="3cqZAo" node="32nSEEss1Vs" resolve="intcodeProgram" />
              </node>
              <node concept="3Tsc0h" id="32nSEEss5BW" role="2OqNvi">
                <ref role="3TtcxE" to="x9r3:32nSEEspL_h" resolve="memory" />
              </node>
            </node>
            <node concept="2Kehj3" id="32nSEEss9ER" role="2OqNvi" />
          </node>
        </node>
        <node concept="3clFbH" id="32nSEEss4WP" role="3cqZAp" />
        <node concept="3clFbH" id="32nSEEsrJ3I" role="3cqZAp" />
        <node concept="3cpWs8" id="32nSEEsrbgT" role="3cqZAp">
          <node concept="3cpWsn" id="32nSEEsrbgU" role="3cpWs9">
            <property role="TrG5h" value="parts" />
            <node concept="10Q1$e" id="32nSEEsrbgO" role="1tU5fm">
              <node concept="3uibUv" id="32nSEEsrbgR" role="10Q1$1">
                <ref role="3uigEE" to="wyt6:~String" resolve="String" />
              </node>
            </node>
            <node concept="2OqwBi" id="32nSEEsrbgV" role="33vP2m">
              <node concept="liA8E" id="32nSEEsrbgX" role="2OqNvi">
                <ref role="37wK5l" to="wyt6:~String.split(java.lang.String)" resolve="split" />
                <node concept="Xl_RD" id="32nSEEsrbgY" role="37wK5m">
                  <property role="Xl_RC" value="," />
                </node>
              </node>
              <node concept="2OqwBi" id="32nSEEssce9" role="2Oq$k0">
                <node concept="37vLTw" id="32nSEEssbYe" role="2Oq$k0">
                  <ref role="3cqZAo" node="32nSEEss1Vs" resolve="intcodeProgram" />
                </node>
                <node concept="3TrcHB" id="32nSEEsscta" role="2OqNvi">
                  <ref role="3TsBF5" to="x9r3:32nSEEsrvWP" resolve="inputString" />
                </node>
              </node>
            </node>
          </node>
        </node>
        <node concept="2Gpval" id="32nSEEsrbsY" role="3cqZAp">
          <node concept="2GrKxI" id="32nSEEsrbt0" role="2Gsz3X">
            <property role="TrG5h" value="part" />
          </node>
          <node concept="37vLTw" id="32nSEEsrbuH" role="2GsD0m">
            <ref role="3cqZAo" node="32nSEEsrbgU" resolve="parts" />
          </node>
          <node concept="3clFbS" id="32nSEEsrbt4" role="2LFqv$">
            <node concept="3cpWs8" id="32nSEEsrbvk" role="3cqZAp">
              <node concept="3cpWsn" id="32nSEEsrbvn" role="3cpWs9">
                <property role="TrG5h" value="memPos" />
                <node concept="3Tqbb2" id="32nSEEsrbvj" role="1tU5fm">
                  <ref role="ehGHo" to="x9r3:32nSEEspL$K" resolve="MemoryPosition" />
                </node>
                <node concept="2ShNRf" id="32nSEEsrbwF" role="33vP2m">
                  <node concept="3zrR0B" id="32nSEEsrbwD" role="2ShVmc">
                    <node concept="3Tqbb2" id="32nSEEsrbwE" role="3zrR0E">
                      <ref role="ehGHo" to="x9r3:32nSEEspL$K" resolve="MemoryPosition" />
                    </node>
                  </node>
                </node>
              </node>
            </node>
            <node concept="3clFbF" id="32nSEEsrbxw" role="3cqZAp">
              <node concept="2OqwBi" id="32nSEEsrdFc" role="3clFbG">
                <node concept="TSZUe" id="32nSEEsrggx" role="2OqNvi">
                  <node concept="37vLTw" id="32nSEEsrgw6" role="25WWJ7">
                    <ref role="3cqZAo" node="32nSEEsrbvn" resolve="memPos" />
                  </node>
                </node>
                <node concept="2OqwBi" id="32nSEEssd8y" role="2Oq$k0">
                  <node concept="37vLTw" id="32nSEEsscP4" role="2Oq$k0">
                    <ref role="3cqZAo" node="32nSEEss1Vs" resolve="intcodeProgram" />
                  </node>
                  <node concept="3Tsc0h" id="32nSEEssdk4" role="2OqNvi">
                    <ref role="3TtcxE" to="x9r3:32nSEEspL_h" resolve="memory" />
                  </node>
                </node>
              </node>
            </node>
            <node concept="3clFbF" id="32nSEEsrgSs" role="3cqZAp">
              <node concept="37vLTI" id="32nSEEsri6e" role="3clFbG">
                <node concept="2YIFZM" id="32nSEEsrisa" role="37vLTx">
                  <ref role="37wK5l" to="wyt6:~Integer.valueOf(java.lang.String)" resolve="valueOf" />
                  <ref role="1Pybhc" to="wyt6:~Integer" resolve="Integer" />
                  <node concept="2GrUjf" id="32nSEEsriwy" role="37wK5m">
                    <ref role="2Gs0qQ" node="32nSEEsrbt0" resolve="part" />
                  </node>
                </node>
                <node concept="2OqwBi" id="32nSEEsrhb3" role="37vLTJ">
                  <node concept="37vLTw" id="32nSEEsrgSq" role="2Oq$k0">
                    <ref role="3cqZAo" node="32nSEEsrbvn" resolve="memPos" />
                  </node>
                  <node concept="3TrcHB" id="32nSEEsrhim" role="2OqNvi">
                    <ref role="3TsBF5" to="x9r3:32nSEEspL$L" resolve="content" />
                  </node>
                </node>
              </node>
            </node>
          </node>
        </node>
      </node>
      <node concept="3Tm1VV" id="32nSEEss1KI" role="1B3o_S" />
      <node concept="3cqZAl" id="32nSEEss1PY" role="3clF45" />
      <node concept="37vLTG" id="32nSEEss1Vs" role="3clF46">
        <property role="TrG5h" value="intcodeProgram" />
        <node concept="3Tqbb2" id="32nSEEss1Vr" role="1tU5fm">
          <ref role="ehGHo" to="x9r3:32nSEEspKJr" resolve="IntcodeProgram" />
        </node>
      </node>
    </node>
    <node concept="3Tm1VV" id="32nSEEspNRF" role="1B3o_S" />
  </node>
</model>

