package com.nalyvaiko.interfaces.impl;

import com.nalyvaiko.interfaces.Command;

public class CommandClass implements Command {

  @Override
  public void execute(String message) {
    System.out.println("Update " + message + " into DB\n");
  }
}
