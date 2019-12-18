package com.codio.feature_usage_mod.controller.features;

import com.codio.feature_usage_mod.controller.features.constructs.Classes;
import com.codio.feature_usage_mod.controller.features.constructs.Constructors;
import com.codio.feature_usage_mod.controller.features.constructs.DataTypes;
import com.codio.feature_usage_mod.controller.features.constructs.DoWhile;
import com.codio.feature_usage_mod.controller.features.constructs.For;
import com.codio.feature_usage_mod.controller.features.constructs.ForEach;
import com.codio.feature_usage_mod.controller.features.constructs.IfConditionals;
import com.codio.feature_usage_mod.controller.features.constructs.MethodReturnTypes;
import com.codio.feature_usage_mod.controller.features.constructs.Methods;
import com.codio.feature_usage_mod.controller.features.constructs.NestedLoops;
import com.codio.feature_usage_mod.controller.features.constructs.Objects;
import com.codio.feature_usage_mod.controller.features.constructs.Strings;
import com.codio.feature_usage_mod.controller.features.constructs.Switch;
import com.codio.feature_usage_mod.controller.features.constructs.Throws;
import com.codio.feature_usage_mod.controller.features.constructs.TryCatch;
import com.codio.feature_usage_mod.controller.features.constructs.Variables;
import com.codio.feature_usage_mod.controller.features.constructs.While;

public class GetConstructsFactory {

  private String option;

  public GetConstructsFactory(String option){
    this.option = option;
  }

  public IConstructs getConstructObject(){

    switch (option){
      case "1":
        return new Classes();
      case "2":
        return new Constructors();
      case "3":
        return new DataTypes();
      case "4":
        return new DoWhile();
      case "5":
        return new For();
      case "6":
        return new ForEach();
      case "7":
        return new IfConditionals();
      case "8":
        return new MethodReturnTypes();
      case "9":
        return new Methods();
      case "10":
        return new NestedLoops();
      case "11":
        return new Objects();
      case "12":
        return new Strings();
      case "13":
        return new Switch();
      case "14":
        return new Throws();
      case "15":
        return new TryCatch();
      case "16":
        return new Variables();
      case "17":
        return new While();
        default:
          return null;
    }
  }
}
