package com.xie.java.log.aop;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

@Component
public class SpelParser {
	
	private static ExpressionParser parser = new SpelExpressionParser();
	
	public static String getKey(String key, String[] paramsName, Object[] args) {
        Expression exp = parser.parseExpression(key);
        EvaluationContext context = new StandardEvaluationContext();
        if(args.length<=0){
        	return null;
        }
        for (int i =0;i<args.length;i++) {
        	context.setVariable(paramsName[i], args[i]);
		}
        return exp.getValue(context, String.class);
	}
	
}
