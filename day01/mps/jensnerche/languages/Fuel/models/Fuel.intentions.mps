<?xml version="1.0" encoding="UTF-8"?>
<model ref="r:8835bd90-b630-4e2f-86e6-0ffce7e9bb5e(Fuel.intentions)">
  <persistence version="9" />
  <languages>
    <use id="d7a92d38-f7db-40d0-8431-763b0c3c9f20" name="jetbrains.mps.lang.intentions" version="0" />
    <devkit ref="fbc25dd2-5da4-483a-8b19-70928e1b62d7(jetbrains.mps.devkit.general-purpose)" />
  </languages>
  <imports>
    <import index="eoo2" ref="6354ebe7-c22a-4a0f-ac54-50b52ab9b065/java:java.nio.file(JDK/)" />
    <import index="33ny" ref="6354ebe7-c22a-4a0f-ac54-50b52ab9b065/java:java.util(JDK/)" />
    <import index="wyt6" ref="6354ebe7-c22a-4a0f-ac54-50b52ab9b065/java:java.lang(JDK/)" />
    <import index="v7ld" ref="r:5ada82f8-0d9f-4074-9439-407e19c221c3(Fuel.structure)" implicit="true" />
  </imports>
  <registry>
    <language id="f3061a53-9226-4cc5-a443-f952ceaf5816" name="jetbrains.mps.baseLanguage">
      <concept id="1215693861676" name="jetbrains.mps.baseLanguage.structure.BaseAssignmentExpression" flags="nn" index="d038R">
        <child id="1068498886297" name="rValue" index="37vLTx" />
        <child id="1068498886295" name="lValue" index="37vLTJ" />
      </concept>
      <concept id="1215695189714" name="jetbrains.mps.baseLanguage.structure.PlusAssignmentExpression" flags="nn" index="d57v9" />
      <concept id="1154032098014" name="jetbrains.mps.baseLanguage.structure.AbstractLoopStatement" flags="nn" index="2LF5Ji">
        <child id="1154032183016" name="body" index="2LFqv$" />
      </concept>
      <concept id="1197027756228" name="jetbrains.mps.baseLanguage.structure.DotExpression" flags="nn" index="2OqwBi">
        <child id="1197027771414" name="operand" index="2Oq$k0" />
        <child id="1197027833540" name="operation" index="2OqNvi" />
      </concept>
      <concept id="1164879751025" name="jetbrains.mps.baseLanguage.structure.TryCatchStatement" flags="nn" index="SfApY">
        <child id="1164879758292" name="body" index="SfCbr" />
        <child id="1164903496223" name="catchClause" index="TEbGg" />
      </concept>
      <concept id="1145552977093" name="jetbrains.mps.baseLanguage.structure.GenericNewExpression" flags="nn" index="2ShNRf">
        <child id="1145553007750" name="creator" index="2ShVmc" />
      </concept>
      <concept id="1164903280175" name="jetbrains.mps.baseLanguage.structure.CatchClause" flags="nn" index="TDmWw">
        <child id="1164903359218" name="catchBody" index="TDEfX" />
        <child id="1164903359217" name="throwable" index="TDEfY" />
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
      <concept id="1070534934090" name="jetbrains.mps.baseLanguage.structure.CastExpression" flags="nn" index="10QFUN">
        <child id="1070534934091" name="type" index="10QFUM" />
        <child id="1070534934092" name="expression" index="10QFUP" />
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
      <concept id="1068580123136" name="jetbrains.mps.baseLanguage.structure.StatementList" flags="sn" stub="5293379017992965193" index="3clFbS">
        <child id="1068581517665" name="statement" index="3cqZAp" />
      </concept>
      <concept id="1068580320020" name="jetbrains.mps.baseLanguage.structure.IntegerConstant" flags="nn" index="3cmrfG">
        <property id="1068580320021" name="value" index="3cmrfH" />
      </concept>
      <concept id="1068581242875" name="jetbrains.mps.baseLanguage.structure.PlusExpression" flags="nn" index="3cpWs3" />
      <concept id="1068581242864" name="jetbrains.mps.baseLanguage.structure.LocalVariableDeclarationStatement" flags="nn" index="3cpWs8">
        <child id="1068581242865" name="localVariableDeclaration" index="3cpWs9" />
      </concept>
      <concept id="1068581242867" name="jetbrains.mps.baseLanguage.structure.LongType" flags="in" index="3cpWsb" />
      <concept id="1068581242863" name="jetbrains.mps.baseLanguage.structure.LocalVariableDeclaration" flags="nr" index="3cpWsn" />
      <concept id="1204053956946" name="jetbrains.mps.baseLanguage.structure.IMethodCall" flags="ng" index="1ndlxa">
        <reference id="1068499141037" name="baseMethodDeclaration" index="37wK5l" />
        <child id="1068499141038" name="actualArgument" index="37wK5m" />
      </concept>
      <concept id="1107535904670" name="jetbrains.mps.baseLanguage.structure.ClassifierType" flags="in" index="3uibUv">
        <reference id="1107535924139" name="classifier" index="3uigEE" />
        <child id="1109201940907" name="parameter" index="11_B2D" />
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
    <language id="760a0a8c-eabb-4521-8bfd-65db761a9ba3" name="jetbrains.mps.baseLanguage.logging">
      <concept id="6332851714983831325" name="jetbrains.mps.baseLanguage.logging.structure.MsgStatement" flags="ng" index="2xdQw9">
        <property id="6332851714983843871" name="severity" index="2xdLsb" />
        <child id="5721587534047265374" name="message" index="9lYJi" />
        <child id="5721587534047265375" name="throwable" index="9lYJj" />
      </concept>
    </language>
    <language id="7866978e-a0f0-4cc7-81bc-4d213d9375e1" name="jetbrains.mps.lang.smodel">
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
  <node concept="2S6QgY" id="6nxSbbthp51">
    <property role="TrG5h" value="ReadModuleMasses" />
    <ref role="2ZfgGC" to="v7ld:6nxSbbthbKv" resolve="FuelCounterUpper" />
    <node concept="2S6ZIM" id="6nxSbbthp52" role="2ZfVej">
      <node concept="3clFbS" id="6nxSbbthp53" role="2VODD2">
        <node concept="3clFbF" id="6nxSbbthpee" role="3cqZAp">
          <node concept="Xl_RD" id="6nxSbbthped" role="3clFbG">
            <property role="Xl_RC" value="Read Module Masses" />
          </node>
        </node>
      </node>
    </node>
    <node concept="2Sbjvc" id="6nxSbbthp54" role="2ZfgGD">
      <node concept="3clFbS" id="6nxSbbthp55" role="2VODD2">
        <node concept="3clFbF" id="6nxSbbthrx5" role="3cqZAp">
          <node concept="2OqwBi" id="6nxSbbthtBd" role="3clFbG">
            <node concept="2OqwBi" id="6nxSbbthrIM" role="2Oq$k0">
              <node concept="2Sf5sV" id="6nxSbbthrx3" role="2Oq$k0" />
              <node concept="3Tsc0h" id="6nxSbbths7V" role="2OqNvi">
                <ref role="3TtcxE" to="v7ld:6nxSbbthk6Y" resolve="modules" />
              </node>
            </node>
            <node concept="2Kehj3" id="6nxSbbthwi$" role="2OqNvi" />
          </node>
        </node>
        <node concept="SfApY" id="6nxSbbthpVy" role="3cqZAp">
          <node concept="3clFbS" id="6nxSbbthpV$" role="SfCbr">
            <node concept="3cpWs8" id="6nxSbbthqx5" role="3cqZAp">
              <node concept="3cpWsn" id="6nxSbbthqx6" role="3cpWs9">
                <property role="TrG5h" value="allLines" />
                <node concept="3uibUv" id="6nxSbbthqwZ" role="1tU5fm">
                  <ref role="3uigEE" to="33ny:~List" resolve="List" />
                  <node concept="3uibUv" id="6nxSbbthqx2" role="11_B2D">
                    <ref role="3uigEE" to="wyt6:~String" resolve="String" />
                  </node>
                </node>
                <node concept="2YIFZM" id="6nxSbbthqx7" role="33vP2m">
                  <ref role="37wK5l" to="eoo2:~Files.readAllLines(java.nio.file.Path)" resolve="readAllLines" />
                  <ref role="1Pybhc" to="eoo2:~Files" resolve="Files" />
                  <node concept="2YIFZM" id="6nxSbbthqx8" role="37wK5m">
                    <ref role="37wK5l" to="eoo2:~Paths.get(java.lang.String,java.lang.String...)" resolve="get" />
                    <ref role="1Pybhc" to="eoo2:~Paths" resolve="Paths" />
                    <node concept="2OqwBi" id="6nxSbbthN55" role="37wK5m">
                      <node concept="2Sf5sV" id="6nxSbbthMBM" role="2Oq$k0" />
                      <node concept="3TrcHB" id="6nxSbbthNOU" role="2OqNvi">
                        <ref role="3TsBF5" to="v7ld:6nxSbbthIph" resolve="inputFilePath" />
                      </node>
                    </node>
                  </node>
                </node>
              </node>
            </node>
            <node concept="2Gpval" id="6nxSbbthqR$" role="3cqZAp">
              <node concept="2GrKxI" id="6nxSbbthqRA" role="2Gsz3X">
                <property role="TrG5h" value="line" />
              </node>
              <node concept="37vLTw" id="6nxSbbthqVK" role="2GsD0m">
                <ref role="3cqZAo" node="6nxSbbthqx6" resolve="allLines" />
              </node>
              <node concept="3clFbS" id="6nxSbbthqRE" role="2LFqv$">
                <node concept="3cpWs8" id="6nxSbbthrma" role="3cqZAp">
                  <node concept="3cpWsn" id="6nxSbbthrmd" role="3cpWs9">
                    <property role="TrG5h" value="module" />
                    <node concept="3Tqbb2" id="6nxSbbthrm9" role="1tU5fm">
                      <ref role="ehGHo" to="v7ld:6nxSbbthcV5" resolve="Module" />
                    </node>
                    <node concept="2ShNRf" id="6nxSbbthyxj" role="33vP2m">
                      <node concept="3zrR0B" id="6nxSbbthyxh" role="2ShVmc">
                        <node concept="3Tqbb2" id="6nxSbbthyxi" role="3zrR0E">
                          <ref role="ehGHo" to="v7ld:6nxSbbthcV5" resolve="Module" />
                        </node>
                      </node>
                    </node>
                  </node>
                </node>
                <node concept="3clFbF" id="6nxSbbthy$q" role="3cqZAp">
                  <node concept="2OqwBi" id="6nxSbbth$u3" role="3clFbG">
                    <node concept="2OqwBi" id="6nxSbbthyGe" role="2Oq$k0">
                      <node concept="2Sf5sV" id="6nxSbbthy$o" role="2Oq$k0" />
                      <node concept="3Tsc0h" id="6nxSbbthyYF" role="2OqNvi">
                        <ref role="3TtcxE" to="v7ld:6nxSbbthk6Y" resolve="modules" />
                      </node>
                    </node>
                    <node concept="TSZUe" id="6nxSbbthB32" role="2OqNvi">
                      <node concept="37vLTw" id="6nxSbbthBeB" role="25WWJ7">
                        <ref role="3cqZAo" node="6nxSbbthrmd" resolve="module" />
                      </node>
                    </node>
                  </node>
                </node>
                <node concept="3clFbF" id="6nxSbbthB_m" role="3cqZAp">
                  <node concept="37vLTI" id="6nxSbbthD6R" role="3clFbG">
                    <node concept="2YIFZM" id="6nxSbbthDvr" role="37vLTx">
                      <ref role="37wK5l" to="wyt6:~Integer.valueOf(java.lang.String)" resolve="valueOf" />
                      <ref role="1Pybhc" to="wyt6:~Integer" resolve="Integer" />
                      <node concept="2GrUjf" id="6nxSbbthDD5" role="37wK5m">
                        <ref role="2Gs0qQ" node="6nxSbbthqRA" resolve="line" />
                      </node>
                    </node>
                    <node concept="2OqwBi" id="6nxSbbthBRW" role="37vLTJ">
                      <node concept="37vLTw" id="6nxSbbthB_k" role="2Oq$k0">
                        <ref role="3cqZAo" node="6nxSbbthrmd" resolve="module" />
                      </node>
                      <node concept="3TrcHB" id="6nxSbbthC0$" role="2OqNvi">
                        <ref role="3TsBF5" to="v7ld:6nxSbbthcV6" resolve="mass" />
                      </node>
                    </node>
                  </node>
                </node>
              </node>
            </node>
          </node>
          <node concept="TDmWw" id="6nxSbbthpV_" role="TEbGg">
            <node concept="3cpWsn" id="6nxSbbthpVB" role="TDEfY">
              <property role="TrG5h" value="e" />
              <node concept="3uibUv" id="6nxSbbthpWe" role="1tU5fm">
                <ref role="3uigEE" to="wyt6:~Exception" resolve="Exception" />
              </node>
            </node>
            <node concept="3clFbS" id="6nxSbbthpVF" role="TDEfX">
              <node concept="2xdQw9" id="6nxSbbthpZ$" role="3cqZAp">
                <property role="2xdLsb" value="error" />
                <node concept="Xl_RD" id="6nxSbbthpZA" role="9lYJi">
                  <property role="Xl_RC" value="Error while reading input.txt" />
                </node>
                <node concept="37vLTw" id="6nxSbbthpZC" role="9lYJj">
                  <ref role="3cqZAo" node="6nxSbbthpVB" resolve="e" />
                </node>
              </node>
            </node>
          </node>
        </node>
      </node>
    </node>
  </node>
  <node concept="2S6QgY" id="6nxSbbthYez">
    <property role="TrG5h" value="CalculateSumFuelRequirements" />
    <ref role="2ZfgGC" to="v7ld:6nxSbbthbKv" resolve="FuelCounterUpper" />
    <node concept="2S6ZIM" id="6nxSbbthYe$" role="2ZfVej">
      <node concept="3clFbS" id="6nxSbbthYe_" role="2VODD2">
        <node concept="3clFbF" id="6nxSbbthYnS" role="3cqZAp">
          <node concept="Xl_RD" id="6nxSbbthYnR" role="3clFbG">
            <property role="Xl_RC" value="Calculate Sum of Fuel Requirements" />
          </node>
        </node>
      </node>
    </node>
    <node concept="2Sbjvc" id="6nxSbbthYeA" role="2ZfgGD">
      <node concept="3clFbS" id="6nxSbbthYeB" role="2VODD2">
        <node concept="3cpWs8" id="6nxSbbthZ_b" role="3cqZAp">
          <node concept="3cpWsn" id="6nxSbbthZ_e" role="3cpWs9">
            <property role="TrG5h" value="sum" />
            <node concept="3cpWsb" id="6nxSbbtiFNK" role="1tU5fm" />
            <node concept="3cmrfG" id="6nxSbbthZCn" role="33vP2m">
              <property role="3cmrfH" value="0" />
            </node>
          </node>
        </node>
        <node concept="2Gpval" id="6nxSbbthZ3L" role="3cqZAp">
          <node concept="2GrKxI" id="6nxSbbthZ3N" role="2Gsz3X">
            <property role="TrG5h" value="module" />
          </node>
          <node concept="2OqwBi" id="6nxSbbthZeD" role="2GsD0m">
            <node concept="2Sf5sV" id="6nxSbbthZ4Q" role="2Oq$k0" />
            <node concept="3Tsc0h" id="6nxSbbthZvR" role="2OqNvi">
              <ref role="3TtcxE" to="v7ld:6nxSbbthk6Y" resolve="modules" />
            </node>
          </node>
          <node concept="3clFbS" id="6nxSbbthZ3R" role="2LFqv$">
            <node concept="3clFbF" id="6nxSbbthZCK" role="3cqZAp">
              <node concept="d57v9" id="6nxSbbti0sE" role="3clFbG">
                <node concept="2OqwBi" id="6nxSbbti0Q4" role="37vLTx">
                  <node concept="2GrUjf" id="6nxSbbti0t7" role="2Oq$k0">
                    <ref role="2Gs0qQ" node="6nxSbbthZ3N" resolve="module" />
                  </node>
                  <node concept="3TrcHB" id="6nxSbbtiQmM" role="2OqNvi">
                    <ref role="3TsBF5" to="v7ld:6nxSbbtiuxC" resolve="totalFuel" />
                  </node>
                </node>
                <node concept="37vLTw" id="6nxSbbthZCJ" role="37vLTJ">
                  <ref role="3cqZAo" node="6nxSbbthZ_e" resolve="sum" />
                </node>
              </node>
            </node>
          </node>
        </node>
        <node concept="2xdQw9" id="6nxSbbtiHWh" role="3cqZAp">
          <property role="2xdLsb" value="info" />
          <node concept="3cpWs3" id="6nxSbbtiIEV" role="9lYJi">
            <node concept="37vLTw" id="6nxSbbtiIFy" role="3uHU7w">
              <ref role="3cqZAo" node="6nxSbbthZ_e" resolve="sum" />
            </node>
            <node concept="Xl_RD" id="6nxSbbtiHWj" role="3uHU7B">
              <property role="Xl_RC" value="Calculates sum: " />
            </node>
          </node>
        </node>
        <node concept="3clFbF" id="6nxSbbti1Rj" role="3cqZAp">
          <node concept="37vLTI" id="6nxSbbti3kG" role="3clFbG">
            <node concept="10QFUN" id="6nxSbbtiHdR" role="37vLTx">
              <node concept="37vLTw" id="6nxSbbti3l9" role="10QFUP">
                <ref role="3cqZAo" node="6nxSbbthZ_e" resolve="sum" />
              </node>
              <node concept="10Oyi0" id="6nxSbbtiHdS" role="10QFUM" />
            </node>
            <node concept="2OqwBi" id="6nxSbbti2if" role="37vLTJ">
              <node concept="2Sf5sV" id="6nxSbbti1Rh" role="2Oq$k0" />
              <node concept="3TrcHB" id="6nxSbbti2v5" role="2OqNvi">
                <ref role="3TsBF5" to="v7ld:6nxSbbthYdR" resolve="sumFuelRequirements" />
              </node>
            </node>
          </node>
        </node>
      </node>
    </node>
  </node>
</model>

