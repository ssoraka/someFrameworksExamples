package ru.example1.starter;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Method;

public class TimedBeanPostProcessor implements BeanPostProcessor {

    public TimedBeanPostProcessor() {}

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();

        boolean hasAnnotation = false;
        for (Method method : aClass.getMethods()) {
            if (method.isAnnotationPresent(PrintMethodTime.class)) {
                hasAnnotation = true;
                break ;
            }
        }

        if (!hasAnnotation) {
            return bean;
        }

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(aClass);
        enhancer.setCallback((MethodInterceptor) (obj, method, objects, methodProxy) -> {
            if (!method.isAnnotationPresent(PrintMethodTime.class)) {
                return methodProxy.invokeSuper(obj, objects);
            }

            long start = System.nanoTime();
            try {
                return methodProxy.invokeSuper(obj, objects);
            } finally {
                long time = System.nanoTime() - start;
                System.out.println(method.getName() + " " + time);
            }
        });

        return enhancer.create();
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
