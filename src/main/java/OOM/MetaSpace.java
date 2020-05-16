package OOM;
/**
 *需要使用到cglib，maven中导入包
 */
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MetaSpace {
	static class ClassA{}
  public static void main(String[] args) {
		int i = 0;
		try {
			while (true){
				i++;
				Enhancer enhancer = new Enhancer();
				enhancer.setSuperclass(ClassA.class);
				enhancer.setUseCache(false);
				enhancer.setCallback(new MethodInterceptor() {
					@Override
					public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
						return methodProxy.invokeSuper(o,args);
					}
				});
				enhancer.create();
			}
		} catch (Exception e) {
      System.out.println("多少次发生异常：" + i);
      e.printStackTrace();
		}
		//
  }
}
