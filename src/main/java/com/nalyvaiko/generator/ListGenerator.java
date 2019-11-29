package com.nalyvaiko.generator;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListGenerator {

  private Random random = new Random();

  public List<Integer> getBuilderList() {
    return Stream
        .<Integer>builder()
        .add(random.nextInt(50))
        .add(random.nextInt(50))
        .add(random.nextInt(50))
        .add(random.nextInt(50))
        .add(random.nextInt(50))
        .add(random.nextInt(50))
        .add(random.nextInt(50))
        .add(random.nextInt(50))
        .add(random.nextInt(50))
        .add(random.nextInt(50))
        .build()
        .collect(Collectors.toList());
  }

  public List<Integer> getGenerateList() {
    return Stream
        .generate(() -> random.nextInt())
        .limit(10)
        .collect(Collectors.toList());
  }

  public List<Integer> getIterateList() {
    return Stream
        .iterate(random.nextInt(), (integer) -> random.nextInt())
        .limit(10)
        .collect(Collectors.toList());
  }
}
