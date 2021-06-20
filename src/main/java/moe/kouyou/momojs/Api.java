package moe.kouyou.momojs;

import javax.script.Compilable;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;

public final class Api{
  private static final ScriptEngineFactory factory = new ProxyScriptEngineFactory();
  
  public ScriptEngineFactory getNashornFactory(){
    return factory;
  }
  
  public ScriptEngine getNashornEngine(){
    return factory.getScriptEngine();
  }
  
  public Invocable getAsInvocable(){
    return (Invocable) factory.getScriptEngine();
  }
  
  public Compilable getAsCompilable(){
    return (Compilable) factory.getScriptEngine();
  }
}
