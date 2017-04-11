package util;
import java.lang.reflect.Method;

import model.UserModel;

/**
 * Created by X on 2016/10/2.
 */

public class ModelUtil {


    final static public <T> void reSet(T t) {

        try {

            // getDeclaredMethods得到本类中所有方法
            Method[] studentMethods = t.getClass().getMethods();

            for (Method method : studentMethods) {

                if(!method.getReturnType().equals(void.class) || !method.getName().contains("set"))
                {
                    continue;
                }




                Class[] paramsType =  method.getParameterTypes();

                if (paramsType.length != 1){continue;}


                XNetUtil.APPPrintln(method.getName());
                XNetUtil.APPPrintln(paramsType[0].getName());

                if(paramsType[0].getName().equals("java.lang.Integer") || paramsType[0].getName().equals("int"))
                {
                    method.invoke(t,0);
                }
                else if(paramsType[0].getName().equals("java.lang.Double") || paramsType[0].getName().equals("double"))
                {
                    method.invoke(t,0.0);
                }
                else if(paramsType[0].getName().equals("java.lang.Float") || paramsType[0].getName().equals("float"))
                {
                    method.invoke(t,0.0f);
                }
                else if(paramsType[0].getName().equals("boolean"))
                {
                    method.invoke(t,false);
                }
                else
                {
                    method.invoke(t,paramsType[0].newInstance());
                }

            }

        }
        catch (Exception e)
        {
            XNetUtil.APPPrintln("Model重设失败: "+e);
        }

    }

    public static void main(String args[]) {

        System.out.println("Hello World!");

        UserModel model = new UserModel();

        ModelUtil.reSet(model);


    }

}
