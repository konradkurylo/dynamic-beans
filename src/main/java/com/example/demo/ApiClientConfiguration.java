package com.example.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class ApiClientConfiguration implements BeanDefinitionRegistryPostProcessor {
    private static final String API_CLIENT_BEAN_NAME = "apiClient_";

    public ApiClientConfiguration(Environment environment) {

    }    

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        Reflections reflections = new Reflections("com.example.demo");

        Set<Class<?>> types = reflections.getTypesAnnotatedWith(MyAnnotation.class);

        System.out.println(types);

        types.forEach(type -> {
            Object o = Proxy.newProxyInstance(type.getClassLoader(), new Class<?>[]{type}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    return args[0];
                }
            });

            GenericBeanDefinition beanDef = new GenericBeanDefinition();

            beanDef.setBeanClass(type);
            beanDef.setInstanceSupplier(()-> o);
            registry.registerBeanDefinition(API_CLIENT_BEAN_NAME + type.getName(), beanDef);
        });



    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }
}