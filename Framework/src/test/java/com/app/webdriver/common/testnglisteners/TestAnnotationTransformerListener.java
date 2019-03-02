package com.app.webdriver.common.testnglisteners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer2;
import org.testng.IAnnotationTransformer3;
import org.testng.annotations.IConfigurationAnnotation;
import org.testng.annotations.IDataProviderAnnotation;
import org.testng.annotations.IFactoryAnnotation;
import org.testng.annotations.IListenersAnnotation;
import org.testng.annotations.ITestAnnotation;

public class TestAnnotationTransformerListener implements IAnnotationTransformer3 {

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		// TODO Auto-generated method stub
	}

	@Override
	public void transform(IConfigurationAnnotation annotation, Class testClass, Constructor testConstructor,
			Method testMethod) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void transform(IDataProviderAnnotation annotation, Method method) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transform(IFactoryAnnotation annotation, Method method) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void transform(IListenersAnnotation annotation, Class testClass) {
		// TODO Auto-generated method stub
		
	}

	
}
