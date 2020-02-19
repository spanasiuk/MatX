package com.ia61.matx.model.signal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractSignal implements Signal {

  protected List<Float> data;
  protected Long frequency;

  public AbstractSignal(List<Float> data, Long frequency) {
    this.data = data;
    this.frequency = frequency;
  }

  @Override
  public Map<Float, Float> getData(Long pullRate, Long pullTime) {
    final Map<Float, Float> result = new HashMap<>();
    for (long i = 0; i < pullRate; i += pullRate) {
      result.put(i / 1000f, getValueAtTimestamp(i));
    }
    return result;
  }

  @Override
  public Float getValueAtTimestamp(Long timestamp) {
    final float floatPosition = timestamp / (float) frequency;
    final int position = (int) Math.floor(floatPosition);
    final float positionBetweenDataPoints = floatPosition - position;
    final int listSize = data.size();

    if (listSize <= 0 || position >= listSize) {
      return 0f;
    } else if (position == listSize - 1) {
      return data.get(listSize - 1);
    } else if (position == 0) {
      return data.get(0);
    }

    return getValue(position, positionBetweenDataPoints);
  }

  @Override
  public Long getFrequency() {
    return frequency;
  }

  @Override
  public Long getLength() {
    return data.size() * frequency;
  }
}