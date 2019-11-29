package com.nalyvaiko.menu;

import com.nalyvaiko.factory.CommandFactory;
import com.nalyvaiko.interfaces.Command;
import com.nalyvaiko.interfaces.MyFunctionalInterface;
import com.nalyvaiko.generator.ListGenerator;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Menu {

  private Map<String, String> menu = new LinkedHashMap<>();
  private CommandFactory commandFactory = new CommandFactory();
  private Map<String, Runnable> menuMethods = new HashMap<>();

  public Menu() {
    menu.put("1", "1 - Work with lambdas");
    menu.put("2", "2 - Work with commands");
    menu.put("3", "3 - Work with streams");
    menu.put("4", "4 - Work wih text lines");
    menu.put("Q", "Q - Exit");

    menuMethods.put("1", this::workWithLambdas);
    menuMethods.put("2", this::workWithCommand);
    menuMethods.put("3", this::workWithStreams);
    menuMethods.put("4", this::workWithTextLines);
  }

  public void start() {
    String action;
    do {
      showMenu();
      System.out.print("Select action: ");
      Scanner scanner1 = new Scanner(System.in);
      action = scanner1.nextLine().toUpperCase();
      System.out.println();
      try {
        menuMethods.get(action).run();
      } catch (Exception e) {
        if (!action.equals("Q")) {
          System.out.println("Incorrect action\n");
        }
      }
    } while (!action.equals("Q"));
  }

  private void showMenu() {
    System.out.println("Menu:");
    for (String str : menu.values()) {
      System.out.println(str);
    }
  }

  private void workWithCommand() {
    System.out.println("Commands: add , delete , get , update");
    System.out.print("Enter command: ");
    Scanner scanner = new Scanner(System.in);
    String commandName = scanner.nextLine().toLowerCase();
    Command command = commandFactory.getCommand(commandName);
    if (Objects.isNull(command)) {
      System.out.println("Incorrect command try again\n");
    } else {
      System.out.print("Enter argument: ");
      String argument = scanner.nextLine();
      command.execute(argument);
    }
  }

  private void workWithLambdas() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter first value: ");
    int firstInt = scanner.nextInt();
    System.out.print("Enter second value: ");
    int secondInt = scanner.nextInt();
    System.out.print("Enter third value: ");
    int thirdInt = scanner.nextInt();
    MyFunctionalInterface functionalInterface = (firstValue, secondValue, thirdValue)
        -> maxValue(firstValue, secondValue, thirdValue);
    System.out.print("Max value is ");
    System.out.println(functionalInterface
        .functionalMethod(firstInt, secondInt, thirdInt));
    functionalInterface = (firstValue, secondValue, thirdValue)
        -> (firstValue + secondValue + thirdValue) / 3;
    System.out.print("Average value is ");
    System.out.println(functionalInterface
        .functionalMethod(firstInt, secondInt, thirdInt));
    System.out.println();
  }

  private int maxValue(int firstValue, int secondValue, int thirdValue) {
    if (firstValue > secondValue) {
      if (firstValue > thirdValue) {
        return firstValue;
      } else {
        return thirdValue;
      }
    } else {
      if (secondValue > thirdValue) {
        return secondValue;
      } else {
        return thirdValue;
      }
    }
  }

  private void workWithStreams() {
    ListGenerator listGenerator = new ListGenerator();
    System.out.println("1 - List created by builder");
    System.out.println("2 - List created by generate");
    System.out.println("3 - List created by iterate");
    Scanner scanner = new Scanner(System.in);
    List<Integer> list;
    switch (scanner.nextLine()) {
      case "1":
        list = listGenerator.getBuilderList();
        break;
      case "2":
        list = listGenerator.getGenerateList();
        break;
      case "3":
        list = listGenerator.getIterateList();
        break;
      default:
        System.out.println("Incorrect action");
        return;
    }
    operationsWithList(list);
  }

  private void operationsWithList(List<Integer> list) {
    System.out.println("List elements");
    list.forEach(i -> System.out.print(i + " "));

    System.out.println("\nMax value " + list
        .stream()
        .max(Comparator.comparingInt(Integer::intValue))
        .get());
    System.out.println("Min value " + list
        .stream()
        .min(Comparator.comparingInt(Integer::intValue))
        .get());
    System.out.println("Average " + list
        .stream()
        .mapToInt(i -> i)
        .average()
        .getAsDouble());
    System.out.println("Sum by sum method " + list
        .stream()
        .mapToInt(i -> i)
        .sum());
    System.out.println("Sum by reduce method " + list
        .stream()
        .reduce((i, j) -> i + j)
        .get());
    System.out.println();
  }

  private void workWithTextLines() {
    System.out.println("Enter string lines");
    Scanner scanner = new Scanner(System.in);
    List<String> stringList = new ArrayList<>();
    String text = scanner.nextLine();
    while (!text.isEmpty()) {
      stringList.add(text);
      text = scanner.nextLine();
    }
    applicationOperations(stringList);
  }

  private void applicationOperations(List<String> stringList) {
    System.out.println("Number of unique words " + stringList
        .stream()
        .distinct()
        .count());
    List<String> sortedListWithUniqueWords = stringList
        .stream()
        .distinct()
        .collect(Collectors.toList());
    System.out.println("Sorted list with unique elements");
    sortedListWithUniqueWords
        .stream()
        .sorted()
        .forEach(i -> System.out.print(i + " "));
    System.out.println();
    Map<String, Long> map = stringList
        .stream()
        .collect(
            Collectors.groupingBy(Function.identity(), Collectors.counting()));
    System.out.println("Occurrence number of each word in the text");
    map.forEach(
        (key, value) -> System.out.println("[" + key + " ; " + value + "] "));
    System.out.println();
    System.out.println(
        "Occurrence number of each symbol except upper case characters");
    Map<Character, Long> occurrenceNumber = stringList
        .stream()
        .flatMap(i -> i.chars().boxed())
        .map(i -> (char) i.intValue())
        .filter(Character::isLowerCase)
        .collect(
            Collectors.groupingBy(Function.identity(), Collectors.counting()));
    occurrenceNumber.forEach(
        (key, value) -> System.out.println("[" + key + " ; " + value + "] "));
    System.out.println();
  }
}
