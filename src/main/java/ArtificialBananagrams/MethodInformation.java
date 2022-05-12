package ArtificialBananagrams;

import java.util.HashMap;
import java.util.Map;

/**
 * This class encapsulates information needed to call a method via reflection.
 * The two inputs to this class are the name of the method and the parameters to the method.
 * The class types are created dynamically and support both Object and primitive types.
 * The three public methods can be used to get the information needed for reflection.
 *
 * @author Matthew Knox
 */
public class MethodInformation {
  private final String name;
  private final Class<?>[] argTypes;
  private final Object[] args;

  private static final Map<Class<?>, Class<?>> classMap;
  static {
    classMap = new HashMap<>();
    classMap.put(Integer.class, int.class);
    classMap.put(Byte.class, byte.class);
    classMap.put(Short.class, short.class);
    classMap.put(Long.class, long.class);
    classMap.put(Float.class, float.class);
    classMap.put(Double.class, double.class);
    classMap.put(Boolean.class, boolean.class);
    classMap.put(Character.class, char.class);
    classMap.put(Void.class, void.class);
  }

  public MethodInformation(String methodName, Object[] arguments) {
    name = methodName;
    if(arguments == null) {
      args = new Object[0];
      argTypes = new Class<?>[0];
      return;
    }
    args = arguments;
    argTypes = new Class<?>[args.length];
    for(int i = 0; i < args.length; i++) {
      argTypes[i] = getPossiblePrimitiveType(args[i]);
    }
  }

  private Class<?> getPossiblePrimitiveType(Object o) {
    Class<?> initialClass = o.getClass();
    if(classMap.containsKey(initialClass))
      return classMap.get(initialClass);
    return initialClass;
  }

  public String methodName() { return name; }
  public Class<?>[] argTypes() { return argTypes; }
  public Object[] args() { return args; }
}