import 'dart:io';

class LayerImage {
  List<List<int>> image;

  LayerImage();

  void applyLayer(List<List<int>> layer) {
    if (image == null) {
      image = layer;
      return;
    }

    for (int row = 0; row < layer.length; row++) {
      for (int val = 0; val < layer[row].length; val++) {
        if (image[row][val] == 2) {
          image[row][val] = layer[row][val];
        }
      }
    }
  }

  @override
  String toString() {
    String out = '';
    for (List<int> row in image) {
      out += '$row\n';
    }
    return out;
  }
}

class Layer {
  List<List<int>> rows;
  Map<int, int> numOccurences = {};

  Layer(this.rows) {
    numOccurences.putIfAbsent(0, () => getNumOf(0));
    numOccurences.putIfAbsent(1, () => getNumOf(1));
    numOccurences.putIfAbsent(2, () => getNumOf(2));
  }

  int getNumOf(int searchNum) {
    return rows.fold(0, (accu, row) {
      return accu +
          row.fold(0, (accu, val) => val == searchNum ? ++accu : accu);
    });
  }

  @override
  String toString() {
    return '$rows';
  }
}

List<Layer> parseInputToLayers(String input, {int width, int height}) {
  List<Layer> layers = [];
  print('$width, $height');

  List<List<int>> currLayerRows = [];
  List<int> currRow = [];
  for (int i = 0; i < input.length + 0; i++) {
    currRow.add(int.parse(input[i]));

    // create a new row
    if (currRow.length % width == 0) {
      currLayerRows.add(currRow);
      currRow = [];
    }

    // create a new layer when we reached m rows with n elememnts
    if ((i + 1) % (width * height) == 0) {
      layers.add(Layer(currLayerRows));
      currLayerRows = [];
    }
  }

  return layers;
}

int main(List<String> args) {
  String input = new File('./input.txt').readAsStringSync();
  int width = 25;
  int height = 6;

  List<Layer> layers = parseInputToLayers(input, width: width, height: height);

  // part 1
  Layer fewestZeroLayer = layers[0];
  int fewestZeroes = layers[0].numOccurences[0];

  for (Layer layer in layers) {
    if (layer.numOccurences[0] < fewestZeroes) {
      fewestZeroes = layer.numOccurences[0];
      fewestZeroLayer = layer;
    }
  }

  print(fewestZeroLayer.numOccurences[1] * fewestZeroLayer.numOccurences[2]);

  // Part2
  LayerImage layerImage = LayerImage();
  for (Layer layer in layers) {
    layerImage.applyLayer(layer.rows);
  }

  print(layerImage);

  return 1;
}
