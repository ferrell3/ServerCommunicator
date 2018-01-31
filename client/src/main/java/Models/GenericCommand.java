package Models;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import Interfaces.ICommand;

public class GenericCommand implements ICommand {
    private String _className;
    private String _methodName;
    private Class<?>[] _paramTypes;
    private Object[] _paramValues;

    public GenericCommand(String className, String methodName,
                          String[] paramTypes, Object[] paramValues) {
        _className = className;
        _methodName = methodName;
        List<Class<?>> types = new ArrayList<>();
        for(int i = 0; i < paramTypes.length; i++)
        {
            try{
                types.add(Class.forName(paramTypes[i]));
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
//        _paramTypes;
        _paramValues = paramValues;
    }

    @Override
    public Results execute(){
        Results result = new Results();
        try {
            Class<?> receiver = Class.forName(_className);
            Method method = receiver.getMethod(_methodName, _paramTypes);
            method.invoke(null, _paramValues);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
