package com.codio.feature_usage_mod.controller.features.constructs;

import com.codio.feature_usage_mod.controller.features.IConstructs;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.Type;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class DataTypes implements IConstructs {

  // to do: limit on the number of variables of a specific data type

  public DataTypes() {
  }

  public String process(CompilationUnit cu) {

    List<VariableDeclarator> dataTypes = getAllPrimitiveDataTypes(cu);
    Hashtable<PrimitiveType.Primitive, List<String>> dataTypesTable = generateDataTypesTable(dataTypes);
    List<String> allDataTypes = getAllDataTypes(dataTypesTable);
    int count = allDataTypes.size();

    return generateMessage(count, allDataTypes);
  }

  private List<String> getAllDataTypes(Hashtable<PrimitiveType.Primitive,
          List<String>> dataTypesTable) {
    List<String> allDataTypes = new ArrayList<>();
    for (PrimitiveType.Primitive key: dataTypesTable.keySet()) {
      allDataTypes.addAll(dataTypesTable.get(key));
    }

    return allDataTypes;
  }

  public String processSpecificCase(CompilationUnit cu, String dataType, String variableName) {
//    Hashtable<String, List<String>> dataTypesTable = generateDataTypesTable(cu);
//    //System.out.println(dataTypesTable);
//    List<String> specifiedDatatypeList = dataTypesTable.get(dataType);
//    boolean flag = false;
//    for (String variable : specifiedDatatypeList) {
//      if (variable.contains(variableName)) {
//        flag = true;
//        break;
//      }
//    }
//
//    if (flag) {
//      return "DataType and Variable Present !";
//    }
    return "DataType and Variable NOT FOUND";
  }

  private Hashtable<PrimitiveType.Primitive, List<String>> generateDataTypesTable(List<VariableDeclarator> dataTypes) {

    Hashtable<PrimitiveType.Primitive, List<String>> dataTypesTable = new Hashtable<>();
    for (VariableDeclarator dataType: dataTypes) {
      Type type = dataType.getType();
      if (type.isPrimitiveType()) {
        addToHashTable(dataTypesTable, type, dataType);
      }
    }

    return dataTypesTable;
  }

  private void addToHashTable(Hashtable<PrimitiveType.Primitive, List<String>> dataTypesTable,
                              Type type, VariableDeclarator dataType) {

    PrimitiveType.Primitive key = PrimitiveType.Primitive.valueOf(type.asString().toUpperCase());
    String value = dataType.getTypeAsString() + " " + dataType.toString();
    if (dataTypesTable.containsKey(key)) {
      List<String> updatedList = dataTypesTable.get(key);
      updatedList.add(value);
      dataTypesTable.replace(key, updatedList);
    }
    else {
      List<String> newList = new ArrayList<>();
      newList.add(value);
      dataTypesTable.put(key, newList);
    }

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

  private List<VariableDeclarator> getAllPrimitiveDataTypes(CompilationUnit cu) {

    List<VariableDeclarator> dataTypes = new ArrayList<>();
    List<VariableDeclarator> allVariables = cu.findAll(VariableDeclarator.class);

    for (VariableDeclarator var: allVariables) {
      Type type = var.getType();
      if (type.isPrimitiveType())  {
        dataTypes.add(var);
      }
    }

    return dataTypes;
  }
}

