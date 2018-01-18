package com.retry;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import org.testng.annotations.Test;

public class AnnotationTransformer implements IAnnotationTransformer {
	


	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor test, Method test2) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);

		
	}
}
