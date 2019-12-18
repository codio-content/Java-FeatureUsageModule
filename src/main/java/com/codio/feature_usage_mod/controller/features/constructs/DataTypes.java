package com.codio.feature_usage_mod.controller.features.constructs;

import com.codio.feature_usage_mod.controller.features.IConstructs;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class DataTypes implements IConstructs {

  // TODO: limit on the number of variables of a specific data type

  // TODO: Need to use both, field declarator and variable declarator

  public DataTypes() {
  }

  public String processGeneralCase(CompilationUnit cu) {

    Hashtable<String, List<String>> dataTypesTable = generateDataTypesTable(cu);
    List<String> all = dataTypesTable.get("all");
    int count = all.size();
    return generateMessage(count, all);
  }

  public String processSpecificCase(CompilationUnit cu, String dataType, String variableName) {
    Hashtable<String, List<String>> dataTypesTable = generateDataTypesTable(cu);
    System.out.println(dataTypesTable);
    List<String> specifiedDatatypeList = dataTypesTable.get(dataType);
    boolean flag = false;
    for (String variable : specifiedDatatypeList) {
      if (variable.contains(variableName)) {
        flag = true;
        break;
      }
    }

    if (flag) {
      return "DataType and Variable Present !";
    }
    return "DataType and Variable NOT FOUND";
  }

  private Hashtable<String, List<String>> generateDataTypesTable(CompilationUnit cu) {

    Hashtable<String, List<String>> dataTypesTable = new Hashtable<>();
    List<VariableDeclarationExpr> allVariables = cu.findAll(VariableDeclarationExpr.class);
    List<FieldDeclaration> allFields = cu.findAll(FieldDeclaration.class);

    List<String> ints = new ArrayList<>();
    List<String> doubles = new ArrayList<>();
    List<String> strings = new ArrayList<>();
    List<String> floats = new ArrayList<>();
    List<String> characters = new ArrayList<>();
    List<String> bytes = new ArrayList<>();
    List<String> shorts = new ArrayList<>();
    List<String> longs = new ArrayList<>();
    List<String> booleans = new ArrayList<>();
    List<String> allVars = new ArrayList<>();

    for (VariableDeclarationExpr variable : allVariables) {
      String var = variable.toString();
      if (var.startsWith("int")) {
        ints.add(var);
        allVars.add(var);
      } else if (var.startsWith("double")) {
        doubles.add(var);
        allVars.add(var);
      } else if (var.startsWith("String") && !var.startsWith("String[")) {
        strings.add(var);
        allVars.add(var);
      } else if (var.startsWith("float")) {
        floats.add(var);
        allVars.add(var);
      } else if (var.startsWith("char")) {
        characters.add(var);
        allVars.add(var);
      } else if (var.startsWith("byte")) {
        bytes.add(var);
        allVars.add(var);
      } else if (var.startsWith("short")) {
        shorts.add(var);
        allVars.add(var);
      } else if (var.startsWith("long")) {
        longs.add(var);
        allVars.add(var);
      } else if (var.startsWith("boolean")) {
        booleans.add(var);
        allVars.add(var);
      }
    }

    dataTypesTable.put("int", ints);
    dataTypesTable.put("double", doubles);
    dataTypesTable.put("String", strings);
    dataTypesTable.put("float", floats);
    dataTypesTable.put("char", characters);
    dataTypesTable.put("byte", bytes);
    dataTypesTable.put("short", shorts);
    dataTypesTable.put("long", longs);
    dataTypesTable.put("boolean", booleans);
    dataTypesTable.put("all", allVars);

    return dataTypesTable;
  }

  private String generateMessage(int count, List<String> all) {
    if (count == 0) {
      return "No variables with standard data types in Student Code";
    } else if (count == 1) {
      return "1 variable with a standard data type in Student Code.\nVariable name: "
              + getVariableNames(all);
    } else {
      return count + " variables with standard data types in Student Code.\nVariable names:\n"
              + getVariableNames(all);
    }
  }

  private String getVariableNames(List<String> all) {
    StringBuilder sb = new StringBuilder();
    for (String var : all) {
      sb.append(var).append("\n");
    }
    return sb.toString();
  }

  @Override
  public String process(CompilationUnit cu) {
    return null;
  }
}
