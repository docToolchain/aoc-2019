import java.util.Arrays;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

class AsteriodMapConverter implements ArgumentConverter {
  @Override
  public String[] convert(Object o, ParameterContext parameterContext) throws ArgumentConversionException {
    return Arrays.stream(((String) o).split("\n"))
        .map(String::trim)
        .toArray(String[]::new);
  }
}
