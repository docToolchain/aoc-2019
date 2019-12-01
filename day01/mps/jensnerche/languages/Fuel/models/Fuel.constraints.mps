<?xml version="1.0" encoding="UTF-8"?>
<model ref="r:31583db9-adc5-468c-a223-4ef25f5a4657(Fuel.constraints)">
  <persistence version="9" />
  <languages>
    <use id="3f4bc5f5-c6c1-4a28-8b10-c83066ffa4a1" name="jetbrains.mps.lang.constraints" version="4" />
    <devkit ref="00000000-0000-4000-0000-5604ebd4f22c(jetbrains.mps.devkit.aspect.constraints)" />
  </languages>
  <imports>
    <import index="v7ld" ref="r:5ada82f8-0d9f-4074-9439-407e19c221c3(Fuel.structure)" implicit="true" />
  </imports>
  <registry>
    <language id="f3061a53-9226-4cc5-a443-f952ceaf5816" name="jetbrains.mps.baseLanguage">
      <concept id="1215693861676" name="jetbrains.mps.baseLanguage.structure.BaseAssignmentExpression" flags="nn" index="d038R">
        <child id="1068498886297" name="rValue" index="37vLTx" />
        <child id="1068498886295" name="lValue" index="37vLTJ" />
      </concept>
      <concept id="1215695189714" name="jetbrains.mps.baseLanguage.structure.PlusAssignmentExpression" flags="nn" index="d57v9" />
      <concept id="1076505808687" name="jetbrains.mps.baseLanguage.structure.WhileStatement" flags="nn" index="2$JKZl">
        <child id="1076505808688" name="condition" index="2$JKZa" />
      </concept>
      <concept id="1095950406618" name="jetbrains.mps.baseLanguage.structure.DivExpression" flags="nn" index="FJ1c_" />
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
      <concept id="1070534370425" name="jetbrains.mps.baseLanguage.structure.IntegerType" flags="in" index="10Oyi0" />
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
      <concept id="1068581242875" name="jetbrains.mps.baseLanguage.structure.PlusExpression" flags="nn" index="3cpWs3" />
      <concept id="1068581242864" name="jetbrains.mps.baseLanguage.structure.LocalVariableDeclarationStatement" flags="nn" index="3cpWs8">
        <child id="1068581242865" name="localVariableDeclaration" index="3cpWs9" />
      </concept>
      <concept id="1068581242869" name="jetbrains.mps.baseLanguage.structure.MinusExpression" flags="nn" index="3cpWsd" />
      <concept id="1068581242863" name="jetbrains.mps.baseLanguage.structure.LocalVariableDeclaration" flags="nr" index="3cpWsn" />
      <concept id="1081506762703" name="jetbrains.mps.baseLanguage.structure.GreaterThanExpression" flags="nn" index="3eOSWO" />
      <concept id="1081773326031" name="jetbrains.mps.baseLanguage.structure.BinaryOperation" flags="nn" index="3uHJSO">
        <child id="1081773367579" name="rightExpression" index="3uHU7w" />
        <child id="1081773367580" name="leftExpression" index="3uHU7B" />
      </concept>
    </language>
    <language id="3f4bc5f5-c6c1-4a28-8b10-c83066ffa4a1" name="jetbrains.mps.lang.constraints">
      <concept id="1147467115080" name="jetbrains.mps.lang.constraints.structure.NodePropertyConstraint" flags="ng" index="EnEH3">
        <reference id="1147467295099" name="applicableProperty" index="EomxK" />
        <child id="1152963095733" name="propertySetter" index="1LXaQT" />
      </concept>
      <concept id="1147468365020" name="jetbrains.mps.lang.constraints.structure.ConstraintsFunctionParameter_node" flags="nn" index="EsrRn" />
      <concept id="1152959968041" name="jetbrains.mps.lang.constraints.structure.ConstraintFunction_PropertySetter" flags="in" index="1LLf8_" />
      <concept id="1213093968558" name="jetbrains.mps.lang.constraints.structure.ConceptConstraints" flags="ng" index="1M2fIO">
        <reference id="1213093996982" name="concept" index="1M2myG" />
        <child id="1213098023997" name="property" index="1MhHOB" />
      </concept>
      <concept id="1153138554286" name="jetbrains.mps.lang.constraints.structure.ConstraintsFunctionParameter_propertyValue" flags="nn" index="1Wqviy" />
    </language>
    <language id="7866978e-a0f0-4cc7-81bc-4d213d9375e1" name="jetbrains.mps.lang.smodel">
      <concept id="1138056022639" name="jetbrains.mps.lang.smodel.structure.SPropertyAccess" flags="nn" index="3TrcHB">
        <reference id="1138056395725" name="property" index="3TsBF5" />
      </concept>
    </language>
    <language id="ceab5195-25ea-4f22-9b92-103b95ca8c0c" name="jetbrains.mps.lang.core">
      <concept id="1169194658468" name="jetbrains.mps.lang.core.structure.INamedConcept" flags="ng" index="TrEIO">
        <property id="1169194664001" name="name" index="TrG5h" />
      </concept>
    </language>
  </registry>
  <node concept="1M2fIO" id="6nxSbbthcVZ">
    <ref role="1M2myG" to="v7ld:6nxSbbthcV5" resolve="Module" />
    <node concept="EnEH3" id="6nxSbbthcW0" role="1MhHOB">
      <ref role="EomxK" to="v7ld:6nxSbbthcV6" resolve="mass" />
      <node concept="1LLf8_" id="6nxSbbthcW2" role="1LXaQT">
        <node concept="3clFbS" id="6nxSbbthcW3" role="2VODD2">
          <node concept="3clFbF" id="6nxSbbthmX7" role="3cqZAp">
            <node concept="37vLTI" id="6nxSbbthoh6" role="3clFbG">
              <node concept="1Wqviy" id="6nxSbbthos6" role="37vLTx" />
              <node concept="2OqwBi" id="6nxSbbthnbh" role="37vLTJ">
                <node concept="EsrRn" id="6nxSbbthmX5" role="2Oq$k0" />
                <node concept="3TrcHB" id="6nxSbbthnkC" role="2OqNvi">
                  <ref role="3TsBF5" to="v7ld:6nxSbbthcV6" resolve="mass" />
                </node>
              </node>
            </node>
          </node>
          <node concept="3clFbF" id="6nxSbbthcWp" role="3cqZAp">
            <node concept="37vLTI" id="6nxSbbthe7r" role="3clFbG">
              <node concept="3cpWsd" id="6nxSbbthgYx" role="37vLTx">
                <node concept="FJ1c_" id="6nxSbbthfTf" role="3uHU7B">
                  <node concept="2OqwBi" id="6nxSbbtherq" role="3uHU7B">
                    <node concept="EsrRn" id="6nxSbbtheiq" role="2Oq$k0" />
                    <node concept="3TrcHB" id="6nxSbbtheNW" role="2OqNvi">
                      <ref role="3TsBF5" to="v7ld:6nxSbbthcV6" resolve="mass" />
                    </node>
                  </node>
                  <node concept="3cmrfG" id="6nxSbbthfTl" role="3uHU7w">
                    <property role="3cmrfH" value="3" />
                  </node>
                </node>
                <node concept="3cmrfG" id="6nxSbbthhgy" role="3uHU7w">
                  <property role="3cmrfH" value="2" />
                </node>
              </node>
              <node concept="2OqwBi" id="6nxSbbthd3z" role="37vLTJ">
                <node concept="EsrRn" id="6nxSbbthcWo" role="2Oq$k0" />
                <node concept="3TrcHB" id="6nxSbbthdaX" role="2OqNvi">
                  <ref role="3TsBF5" to="v7ld:6nxSbbthcVz" resolve="requiredFuel" />
                </node>
              </node>
            </node>
          </node>
          <node concept="3clFbH" id="6nxSbbticPH" role="3cqZAp" />
          <node concept="3cpWs8" id="6nxSbbtid92" role="3cqZAp">
            <node concept="3cpWsn" id="6nxSbbtid95" role="3cpWs9">
              <property role="TrG5h" value="additionalFuel" />
              <node concept="10Oyi0" id="6nxSbbtid90" role="1tU5fm" />
              <node concept="3cmrfG" id="6nxSbbtidkx" role="33vP2m">
                <property role="3cmrfH" value="0" />
              </node>
            </node>
          </node>
          <node concept="3cpWs8" id="6nxSbbtie2e" role="3cqZAp">
            <node concept="3cpWsn" id="6nxSbbtie2h" role="3cpWs9">
              <property role="TrG5h" value="x" />
              <node concept="10Oyi0" id="6nxSbbtie2c" role="1tU5fm" />
              <node concept="2OqwBi" id="6nxSbbtiel_" role="33vP2m">
                <node concept="EsrRn" id="6nxSbbtiecJ" role="2Oq$k0" />
                <node concept="3TrcHB" id="6nxSbbtiezB" role="2OqNvi">
                  <ref role="3TsBF5" to="v7ld:6nxSbbthcVz" resolve="requiredFuel" />
                </node>
              </node>
            </node>
          </node>
          <node concept="2$JKZl" id="6nxSbbtieJQ" role="3cqZAp">
            <node concept="3clFbS" id="6nxSbbtieJS" role="2LFqv$">
              <node concept="3clFbF" id="6nxSbbtifP0" role="3cqZAp">
                <node concept="37vLTI" id="6nxSbbtigwl" role="3clFbG">
                  <node concept="3cpWsd" id="6nxSbbtiinR" role="37vLTx">
                    <node concept="3cmrfG" id="6nxSbbtiinX" role="3uHU7w">
                      <property role="3cmrfH" value="2" />
                    </node>
                    <node concept="FJ1c_" id="6nxSbbtihtd" role="3uHU7B">
                      <node concept="37vLTw" id="6nxSbbtigw$" role="3uHU7B">
                        <ref role="3cqZAo" node="6nxSbbtie2h" resolve="x" />
                      </node>
                      <node concept="3cmrfG" id="6nxSbbtihtj" role="3uHU7w">
                        <property role="3cmrfH" value="3" />
                      </node>
                    </node>
                  </node>
                  <node concept="37vLTw" id="6nxSbbtifOZ" role="37vLTJ">
                    <ref role="3cqZAo" node="6nxSbbtie2h" resolve="x" />
                  </node>
                </node>
              </node>
              <node concept="3clFbJ" id="6nxSbbtiiCa" role="3cqZAp">
                <node concept="3clFbS" id="6nxSbbtiiCc" role="3clFbx">
                  <node concept="3clFbF" id="6nxSbbtijzI" role="3cqZAp">
                    <node concept="d57v9" id="6nxSbbtiknC" role="3clFbG">
                      <node concept="37vLTw" id="6nxSbbtiko2" role="37vLTx">
                        <ref role="3cqZAo" node="6nxSbbtie2h" resolve="x" />
                      </node>
                      <node concept="37vLTw" id="6nxSbbtijzG" role="37vLTJ">
                        <ref role="3cqZAo" node="6nxSbbtid95" resolve="additionalFuel" />
                      </node>
                    </node>
                  </node>
                </node>
                <node concept="3eOSWO" id="6nxSbbtijsC" role="3clFbw">
                  <node concept="3cmrfG" id="6nxSbbtijsI" role="3uHU7w">
                    <property role="3cmrfH" value="0" />
                  </node>
                  <node concept="37vLTw" id="6nxSbbtiiCK" role="3uHU7B">
                    <ref role="3cqZAo" node="6nxSbbtie2h" resolve="x" />
                  </node>
                </node>
              </node>
            </node>
            <node concept="3eOSWO" id="6nxSbbtifHV" role="2$JKZa">
              <node concept="3cmrfG" id="6nxSbbtifI1" role="3uHU7w">
                <property role="3cmrfH" value="0" />
              </node>
              <node concept="37vLTw" id="6nxSbbtieU3" role="3uHU7B">
                <ref role="3cqZAo" node="6nxSbbtie2h" resolve="x" />
              </node>
            </node>
          </node>
          <node concept="3clFbF" id="6nxSbbtipeH" role="3cqZAp">
            <node concept="37vLTI" id="6nxSbbtiqJg" role="3clFbG">
              <node concept="37vLTw" id="6nxSbbtiqUg" role="37vLTx">
                <ref role="3cqZAo" node="6nxSbbtid95" resolve="additionalFuel" />
              </node>
              <node concept="2OqwBi" id="6nxSbbtipEA" role="37vLTJ">
                <node concept="EsrRn" id="6nxSbbtipeF" role="2Oq$k0" />
                <node concept="3TrcHB" id="6nxSbbtipVn" role="2OqNvi">
                  <ref role="3TsBF5" to="v7ld:6nxSbbticzh" resolve="additionalFuel" />
                </node>
              </node>
            </node>
          </node>
          <node concept="3clFbF" id="6nxSbbtivhn" role="3cqZAp">
            <node concept="37vLTI" id="6nxSbbtiwUN" role="3clFbG">
              <node concept="3cpWs3" id="6nxSbbtiygV" role="37vLTx">
                <node concept="2OqwBi" id="6nxSbbtiyET" role="3uHU7w">
                  <node concept="EsrRn" id="6nxSbbtiyh9" role="2Oq$k0" />
                  <node concept="3TrcHB" id="6nxSbbtiz3B" role="2OqNvi">
                    <ref role="3TsBF5" to="v7ld:6nxSbbticzh" resolve="additionalFuel" />
                  </node>
                </node>
                <node concept="2OqwBi" id="6nxSbbtix4g" role="3uHU7B">
                  <node concept="EsrRn" id="6nxSbbtiwVg" role="2Oq$k0" />
                  <node concept="3TrcHB" id="6nxSbbtixsM" role="2OqNvi">
                    <ref role="3TsBF5" to="v7ld:6nxSbbthcVz" resolve="requiredFuel" />
                  </node>
                </node>
              </node>
              <node concept="2OqwBi" id="6nxSbbtivHs" role="37vLTJ">
                <node concept="EsrRn" id="6nxSbbtivhl" role="2Oq$k0" />
                <node concept="3TrcHB" id="6nxSbbtivYl" role="2OqNvi">
                  <ref role="3TsBF5" to="v7ld:6nxSbbtiuxC" resolve="totalFuel" />
                </node>
              </node>
            </node>
          </node>
        </node>
      </node>
    </node>
  </node>
</model>

