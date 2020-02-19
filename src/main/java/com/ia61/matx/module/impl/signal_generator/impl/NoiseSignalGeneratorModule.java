package com.ia61.matx.module.impl.signal_generator.impl;

import com.ia61.matx.model.signal.Signal;
import com.ia61.matx.model.signal.impl.AnalogAbstractSignal;
import com.ia61.matx.module.impl.signal_generator.AbstractSignalGeneratorModule;
import java.util.ArrayList;
import java.util.List;

import com.ia61.matx.util.NumberUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoiseSignalGeneratorModule extends AbstractSignalGeneratorModule {

  public Float upperBound = 1f;
  public Float lowerBound = 0f;

  @Override
  public Signal getDataToFirstOutput() {
    final List<Float> data = new ArrayList<>();
    for (long i = 0; i <= getLenght(); i += getDiscretizationFrequency()) {
      data.add(NumberUtil.random(lowerBound, upperBound));
    }
    return new AnalogAbstractSignal(data, getDiscretizationFrequency());
  }

  @Override
  public String getModuleName() {
    return "Noise Signal Generator";
  }
}