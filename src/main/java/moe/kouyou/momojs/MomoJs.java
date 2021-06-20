package moe.kouyou.momojs;

import org.bukkit.plugin.java.JavaPlugin;

import javax.script.*;

import java.lang.invoke.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Stream;

import sun.misc.Unsafe;

public final class MomoJs extends JavaPlugin{
  static{
    try{
      Module om = MomoJs.class.getModule();
      setClassModule(MomoJs.class, Object.class.getModule());
      Field jlas = Class.forName("jdk.internal.access.SharedSecrets").getDeclaredField("javaLangAccess");
      jlas.setAccessible(true);
      Set<ModuleLayer> mls = new HashSet<>();
      Method gsc = ModuleLayer.class.getDeclaredMethod("layers", ClassLoader.class);
      gsc.setAccessible(true);
      mls.add(ModuleLayer.boot());
      mls.add(ModuleLayer.empty());
      mls.add(ClassLoader.getSystemClassLoader().getUnnamedModule().getLayer());
      mls.add(ClassLoader.getPlatformClassLoader().getUnnamedModule().getLayer());
      mls.add(Thread.currentThread().getContextClassLoader().getUnnamedModule().getLayer());
      ((Stream<ModuleLayer>) gsc.invoke(null, new Object[]{null})).forEach(mls::add);
      ((Stream<ModuleLayer>) gsc.invoke(null, ClassLoader.getSystemClassLoader())).forEach(mls::add);
      ((Stream<ModuleLayer>) gsc.invoke(null, ClassLoader.getPlatformClassLoader())).forEach(mls::add);
      ((Stream<ModuleLayer>) gsc.invoke(null, Thread.currentThread().getContextClassLoader())).forEach(mls::add);
      Class bl = Class.forName("jdk.internal.loader.BootLoader");
      Method blumm = bl.getDeclaredMethod("getUnnamedModule");
      blumm.setAccessible(true);
      mls.add(((Module) blumm.invoke(null)).getLayer());
      Set scs = new HashSet();
      Method mlscm = ModuleLayer.class.getDeclaredMethod("getServicesCatalog");
      mlscm.setAccessible(true);
      for(ModuleLayer ml : mls){
        if(ml != null) scs.add(mlscm.invoke(ml));
      }
      Method blscm = bl.getDeclaredMethod("getServicesCatalog");
      blscm.setAccessible(true);
      scs.add(blscm.invoke(null));
      Class scc = Class.forName("jdk.internal.module.ServicesCatalog");
      Method ap = scc.getDeclaredMethod("addProvider", Module.class, Class.class, Class.class);
      ap.setAccessible(true);
      for(Object sc : scs){
        ap.invoke(sc, om, ScriptEngineFactory.class, ProxyScriptEngineFactory.class);
      }
      setClassModule(MomoJs.class, om);
    }catch(Throwable t){
      t.printStackTrace();
      throw new RuntimeException(t);
    }
  }
  
  private static Unsafe theUnsafe = null;
  private static Unsafe getUnsafe(){
    if(theUnsafe == null){
      try{
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        theUnsafe = (Unsafe) f.get(null);
      }catch(Throwable t){
        throw new RuntimeException(t);
      }
    }
    return theUnsafe;
  }
  
  private static MethodHandles.Lookup theLookup = null;
  private static MethodHandles.Lookup getLookup(){
    if(theLookup == null){
      try{
        Field f = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
        f.setAccessible(true);
        theLookup = (MethodHandles.Lookup) f.get(null);
      }catch(Throwable t){
        throw new RuntimeException(t);
      }
    }
    return theLookup;
  }
  
  private static void setClassModule(Class c, Module m){
    try{
      Unsafe u = getUnsafe();
      Field f = Class.class.getDeclaredField("module");
      u.putObject(c, u.objectFieldOffset(f), m);
    }catch(Throwable t) {
      throw new RuntimeException(t);
    }
  }
  
  @Override
  public void onLoad(){
    getLogger().info("js engine provider is loaded");
  }
  
  @Override
  public void onEnable(){
    try{
      getLogger().info("js engine provider is enabled");
      System.out.println(new ScriptEngineManager().getEngineByName("nashorn"));
    }catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }
  
  @Override
  public void onDisable(){
    getLogger().info("js engine provider is disabled");
  }
}
