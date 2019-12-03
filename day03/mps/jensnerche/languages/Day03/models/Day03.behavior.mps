<?xml version="1.0" encoding="UTF-8"?>
<model ref="r:ca97feae-8c6c-4eed-93e4-9f93f0f44320(Day03.behavior)">
  <persistence version="9" />
  <languages>
    <use id="af65afd8-f0dd-4942-87d9-63a55f2a9db1" name="jetbrains.mps.lang.behavior" version="2" />
    <devkit ref="fbc25dd2-5da4-483a-8b19-70928e1b62d7(jetbrains.mps.devkit.general-purpose)" />
  </languages>
  <imports>
    <import index="wyt6" ref="6354ebe7-c22a-4a0f-ac54-50b52ab9b065/java:java.lang(JDK/)" />
    <import index="b4kx" ref="r:c59fa5b7-130e-45f2-a476-3ba334523617(Day03.structure)" implicit="true" />
  </imports>
  <registry>
    <language id="af65afd8-f0dd-4942-87d9-63a55f2a9db1" name="jetbrains.mps.lang.behavior">
      <concept id="1225194240794" name="jetbrains.mps.lang.behavior.structure.ConceptBehavior" flags="ng" index="13h7C7">
        <reference id="1225194240799" name="concept" index="13h7C2" />
        <child id="1225194240805" name="method" index="13h7CS" />
        <child id="1225194240801" name="constructor" index="13h7CW" />
      </concept>
      <concept id="1225194413805" name="jetbrains.mps.lang.behavior.structure.ConceptConstructorDeclaration" flags="in" index="13hLZK" />
      <concept id="1225194472830" name="jetbrains.mps.lang.behavior.structure.ConceptMethodDeclaration" flags="ng" index="13i0hz" />
      <concept id="1225194691553" name="jetbrains.mps.lang.behavior.structure.ThisNodeExpression" flags="nn" index="13iPFW" />
    </language>
    <language id="f3061a53-9226-4cc5-a443-f952ceaf5816" name="jetbrains.mps.baseLanguage">
      <concept id="1197027756228" name="jetbrains.mps.baseLanguage.structure.DotExpression" flags="nn" index="2OqwBi">
        <child id="1197027771414" name="operand" index="2Oq$k0" />
        <child id="1197027833540" name="operation" index="2OqNvi" />
      </concept>
      <concept id="1137021947720" name="jetbrains.mps.baseLanguage.structure.ConceptFunction" flags="in" index="2VMwT0">
        <child id="1137022507850" name="body" index="2VODD2" />
      </concept>
      <concept id="1081236700937" name="jetbrains.mps.baseLanguage.structure.StaticMethodCall" flags="nn" index="2YIFZM">
        <reference id="1144433194310" name="classConcept" index="1Pybhc" />
      </concept>
      <concept id="1070534370425" name="jetbrains.mps.baseLanguage.structure.IntegerType" flags="in" index="10Oyi0" />
      <concept id="1070534644030" name="jetbrains.mps.baseLanguage.structure.BooleanType" flags="in" index="10P_77" />
      <concept id="1068498886296" name="jetbrains.mps.baseLanguage.structure.VariableReference" flags="nn" index="37vLTw">
        <reference id="1068581517664" name="variableDeclaration" index="3cqZAo" />
      </concept>
      <concept id="1068498886292" name="jetbrains.mps.baseLanguage.structure.ParameterDeclaration" flags="ir" index="37vLTG" />
      <concept id="4972933694980447171" name="jetbrains.mps.baseLanguage.structure.BaseVariableDeclaration" flags="ng" index="19Szcq">
        <child id="5680397130376446158" name="type" index="1tU5fm" />
      </concept>
      <concept id="1068580123132" name="jetbrains.mps.baseLanguage.structure.BaseMethodDeclaration" flags="ng" index="3clF44">
        <child id="1068580123133" name="returnType" index="3clF45" />
        <child id="1068580123134" name="parameter" index="3clF46" />
        <child id="1068580123135" name="body" index="3clF47" />
      </concept>
      <concept id="1068580123152" name="jetbrains.mps.baseLanguage.structure.EqualsExpression" flags="nn" index="3clFbC" />
      <concept id="1068580123136" name="jetbrains.mps.baseLanguage.structure.StatementList" flags="sn" stub="5293379017992965193" index="3clFbS">
        <child id="1068581517665" name="statement" index="3cqZAp" />
      </concept>
      <concept id="1068581242875" name="jetbrains.mps.baseLanguage.structure.PlusExpression" flags="nn" index="3cpWs3" />
      <concept id="1068581242878" name="jetbrains.mps.baseLanguage.structure.ReturnStatement" flags="nn" index="3cpWs6">
        <child id="1068581517676" name="expression" index="3cqZAk" />
      </concept>
      <concept id="1204053956946" name="jetbrains.mps.baseLanguage.structure.IMethodCall" flags="ng" index="1ndlxa">
        <reference id="1068499141037" name="baseMethodDeclaration" index="37wK5l" />
        <child id="1068499141038" name="actualArgument" index="37wK5m" />
      </concept>
      <concept id="1081773326031" name="jetbrains.mps.baseLanguage.structure.BinaryOperation" flags="nn" index="3uHJSO">
        <child id="1081773367579" name="rightExpression" index="3uHU7w" />
        <child id="1081773367580" name="leftExpression" index="3uHU7B" />
      </concept>
      <concept id="1178549954367" name="jetbrains.mps.baseLanguage.structure.IVisible" flags="ng" index="1B3ioH">
        <child id="1178549979242" name="visibility" index="1B3o_S" />
      </concept>
      <concept id="1146644602865" name="jetbrains.mps.baseLanguage.structure.PublicVisibility" flags="nn" index="3Tm1VV" />
      <concept id="1080120340718" name="jetbrains.mps.baseLanguage.structure.AndExpression" flags="nn" index="1Wc70l" />
    </language>
    <language id="7866978e-a0f0-4cc7-81bc-4d213d9375e1" name="jetbrains.mps.lang.smodel">
      <concept id="1138055754698" name="jetbrains.mps.lang.smodel.structure.SNodeType" flags="in" index="3Tqbb2">
        <reference id="1138405853777" name="concept" index="ehGHo" />
      </concept>
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
  <node concept="13h7C7" id="fQbx7caDyY">
    <ref role="13h7C2" to="b4kx:fQbx7c9y6Z" resolve="Coordinate" />
    <node concept="13i0hz" id="fQbx7caDz9" role="13h7CS">
      <property role="TrG5h" value="isAt" />
      <node concept="3Tm1VV" id="fQbx7caDza" role="1B3o_S" />
      <node concept="10P_77" id="fQbx7caDzx" role="3clF45" />
      <node concept="3clFbS" id="fQbx7caDzc" role="3clF47">
        <node concept="3cpWs6" id="fQbx7caD$_" role="3cqZAp">
          <node concept="1Wc70l" id="fQbx7caGQw" role="3cqZAk">
            <node concept="3clFbC" id="fQbx7caIWW" role="3uHU7w">
              <node concept="2OqwBi" id="fQbx7caJyZ" role="3uHU7w">
                <node concept="37vLTw" id="fQbx7caJh8" role="2Oq$k0">
                  <ref role="3cqZAo" node="fQbx7caDzZ" resolve="other" />
                </node>
                <node concept="3TrcHB" id="fQbx7caJXQ" role="2OqNvi">
                  <ref role="3TsBF5" to="b4kx:fQbx7c9y74" resolve="y" />
                </node>
              </node>
              <node concept="2OqwBi" id="fQbx7caHkh" role="3uHU7B">
                <node concept="13iPFW" id="fQbx7caHb5" role="2Oq$k0" />
                <node concept="3TrcHB" id="fQbx7caHIY" role="2OqNvi">
                  <ref role="3TsBF5" to="b4kx:fQbx7c9y74" resolve="y" />
                </node>
              </node>
            </node>
            <node concept="3clFbC" id="fQbx7caELF" role="3uHU7B">
              <node concept="2OqwBi" id="fQbx7caDHQ" role="3uHU7B">
                <node concept="13iPFW" id="fQbx7caD$S" role="2Oq$k0" />
                <node concept="3TrcHB" id="fQbx7caDPe" role="2OqNvi">
                  <ref role="3TsBF5" to="b4kx:fQbx7c9y70" resolve="x" />
                </node>
              </node>
              <node concept="2OqwBi" id="fQbx7caFmU" role="3uHU7w">
                <node concept="37vLTw" id="fQbx7caF5q" role="2Oq$k0">
                  <ref role="3cqZAo" node="fQbx7caDzZ" resolve="other" />
                </node>
                <node concept="3TrcHB" id="fQbx7caFLk" role="2OqNvi">
                  <ref role="3TsBF5" to="b4kx:fQbx7c9y70" resolve="x" />
                </node>
              </node>
            </node>
          </node>
        </node>
      </node>
      <node concept="37vLTG" id="fQbx7caDzZ" role="3clF46">
        <property role="TrG5h" value="other" />
        <node concept="3Tqbb2" id="fQbx7caDzY" role="1tU5fm">
          <ref role="ehGHo" to="b4kx:fQbx7c9y6Z" resolve="Coordinate" />
        </node>
      </node>
    </node>
    <node concept="13i0hz" id="fQbx7caLSV" role="13h7CS">
      <property role="TrG5h" value="calculateManhattanDistance" />
      <node concept="3Tm1VV" id="fQbx7caLSW" role="1B3o_S" />
      <node concept="10Oyi0" id="fQbx7caMe9" role="3clF45" />
      <node concept="3clFbS" id="fQbx7caLSY" role="3clF47">
        <node concept="3cpWs6" id="fQbx7caMfe" role="3cqZAp">
          <node concept="3cpWs3" id="fQbx7cc7N1" role="3cqZAk">
            <node concept="2YIFZM" id="fQbx7cc8MI" role="3uHU7w">
              <ref role="1Pybhc" to="wyt6:~Math" resolve="Math" />
              <ref role="37wK5l" to="wyt6:~Math.abs(int)" resolve="abs" />
              <node concept="2OqwBi" id="fQbx7ccabQ" role="37wK5m">
                <node concept="13iPFW" id="fQbx7cc9tz" role="2Oq$k0" />
                <node concept="3TrcHB" id="fQbx7ccaKV" role="2OqNvi">
                  <ref role="3TsBF5" to="b4kx:fQbx7c9y74" resolve="y" />
                </node>
              </node>
            </node>
            <node concept="2YIFZM" id="fQbx7cc5UH" role="3uHU7B">
              <ref role="1Pybhc" to="wyt6:~Math" resolve="Math" />
              <ref role="37wK5l" to="wyt6:~Math.abs(int)" resolve="abs" />
              <node concept="2OqwBi" id="fQbx7cc6by" role="37wK5m">
                <node concept="13iPFW" id="fQbx7cc5V1" role="2Oq$k0" />
                <node concept="3TrcHB" id="fQbx7cc6jh" role="2OqNvi">
                  <ref role="3TsBF5" to="b4kx:fQbx7c9y70" resolve="x" />
                </node>
              </node>
            </node>
          </node>
        </node>
      </node>
    </node>
    <node concept="13hLZK" id="fQbx7caDyZ" role="13h7CW">
      <node concept="3clFbS" id="fQbx7caDz0" role="2VODD2" />
    </node>
  </node>
</model>

