package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.visitor.GenericVisitorAdapter;

public class Variables extends GenericVisitorAdapter<String, Void> {

  public Variables(){}

    //TODO: Thinking about this for a bit. Basic distinction: Global, Local, Instance ? Is something missing ?

}
