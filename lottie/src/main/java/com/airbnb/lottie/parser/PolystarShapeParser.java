package com.airbnb.lottie.parser;

import android.graphics.PointF;
import android.util.JsonReader;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatablePathValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.content.PolystarShape;

import java.io.IOException;

public class PolystarShapeParser {

  private PolystarShapeParser() {}

  public static PolystarShape parse(
      JsonReader reader, LottieComposition composition) throws IOException {
    String name = null;
    PolystarShape.Type type = null;
    AnimatableFloatValue points = null;
    AnimatableValue<PointF, PointF> position = null;
    AnimatableFloatValue rotation = null;
    AnimatableFloatValue outerRadius = null;
    AnimatableFloatValue outerRoundedness = null;
    AnimatableFloatValue innerRadius = null;
    AnimatableFloatValue innerRoundedness = null;

    while (reader.hasNext()) {
      switch (reader.nextName()) {
        case "nm":
          name = reader.nextString();
          break;
        case "sy":
          type = PolystarShape.Type.forValue(reader.nextInt());
          break;
        case "pt":
          points = AnimatableFloatValue.Factory.newInstance(reader, composition, false);
          break;
        case "p":
          position = AnimatablePathValue
              .createAnimatablePathOrSplitDimensionPath(reader, composition);
          break;
        case "r":
          rotation = AnimatableFloatValue.Factory.newInstance(reader, composition, false);
          break;
        case "or":
          outerRadius = AnimatableFloatValue.Factory.newInstance(reader, composition);
          break;
        case "os":
          outerRoundedness = AnimatableFloatValue.Factory.newInstance(reader, composition, false);
          break;
        case "ir":
          innerRadius = AnimatableFloatValue.Factory.newInstance(reader, composition);
          break;
        case "is":
          innerRoundedness = AnimatableFloatValue.Factory.newInstance(reader, composition, false);
          break;
        default:
          reader.skipValue();
      }
    }

    return new PolystarShape(
        name, type, points, position, rotation, innerRadius, outerRadius, innerRoundedness, outerRoundedness);
  }
}