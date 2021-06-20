package moe.kouyou.stablejs;

import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.ScriptEngine;
import java.util.Arrays;
import java.util.List;

public class ProxyScriptEngineFactory implements javax.script.ScriptEngineFactory{
  
  private static final NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
  
  @Override
  public String getEngineName(){
    return "nashorn";
  }
  
  @Override
  public String getEngineVersion(){
    return factory.getEngineVersion();
  }
  
  @Override
  public List<String> getExtensions(){
    return factory.getExtensions();
  }
  
  @Override
  public String getLanguageName(){
    return factory.getLanguageName();
  }
  
  @Override
  public String getLanguageVersion(){
    return factory.getLanguageVersion();
  }
  
  @Override
  public String getMethodCallSyntax(final String obj, final String method, final String... args){
    return factory.getMethodCallSyntax(obj, method, args);
  }
  
  @Override
  public List<String> getMimeTypes(){
    return factory.getMimeTypes();
  }
  
  @Override
  public List<String> getNames(){
    return Arrays.asList(
        "stablejs",
        "nashorn", "Nashorn",
        "js", "JS",
        "JavaScript", "javascript",
        "ECMAScript", "ecmascript"
    );
  }
  
  @Override
  public String getOutputStatement(final String toDisplay){
    return factory.getOutputStatement(toDisplay);
  }
  
  @Override
  public Object getParameter(final String key){
    return factory.getParameter(key);
  }
  
  @Override
  public String getProgram(final String... statements){
    return factory.getProgram(statements);
  }
  
  @Override
  public ScriptEngine getScriptEngine(){
    return factory.getScriptEngine();
  }
}
