package com.nalyvaiko.factory;

import com.nalyvaiko.interfaces.impl.CommandClass;
import com.nalyvaiko.interfaces.Command;

public class CommandFactory {

  public Command getCommand(String command) {
    switch (command) {
      case "add":
        return (message -> System.out.println("Add " + message + " to DB\n"));
      case "delete":
        return this::deleteString;
      case "get":
        return new Command() {
          public void execute(String message) {
            System.out.println("Get " + message + " from DB\n");
          }
        };
      case "update":
        return new CommandClass();
      default:
        return null;
    }
  }

  private void deleteString(String string) {
    System.out.println("Delete " + string + " from DB\n");
  }
}
