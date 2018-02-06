package Models;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import Interfaces.ICommand;
import Server.StringProcessor;

public class GenericCommand implements ICommand {
    private String _className;
    private String _methodName;
    private Class<?>[] _paramTypes;
    private String[] _strParamTypes;
    private Request[] _paramValues;
//    private Object[] _paramValues;


    public GenericCommand(String className, String methodName,
                          String[] paramTypes, Request[] paramValues) {
        _className = className;
        _methodName = methodName;
        _strParamTypes = paramTypes;
        _paramValues = paramValues;
    }

    @Override
    public Results execute(){
        _paramTypes = new Class<?>[_strParamTypes.length];
        for(int i = 0; i < _strParamTypes.length; i++)
        {
            try{
                _paramTypes[i] = (Class.forName(_strParamTypes[i]));
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        Results result = new Results();
        try {
            Class<?> receiver = Class.forName(_className);
            Method method = receiver.getMethod(_methodName, _paramTypes);
            result = (Results) method.invoke(StringProcessor.getInstance(), _paramValues);
        }
        catch (Exception e) {
            if(e.getCause().getClass() == NumberFormatException.class)
            {
//                System.out.println("Caught in Server.GenericCommand.execute()");
//                System.out.println(e.getCause().getClass());
//                System.out.println(e.getClass());
                throw new NumberFormatException();
            }
            else
            {
//                System.out.println(e.getCause());
//                System.out.println();
                e.printStackTrace();
            }
        }
        return result;
    }
}
