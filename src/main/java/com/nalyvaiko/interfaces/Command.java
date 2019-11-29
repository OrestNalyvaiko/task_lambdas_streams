package com.nalyvaiko.interfaces;

@FunctionalInterface
public interface Command {

  void execute(String message);
}
