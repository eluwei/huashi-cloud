package com.huashi.cloud.common.validation;

import com.huashi.cloud.common.exception.ValidationException;
import com.huashi.cloud.common.exception.result.ValidationExceptionResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Aspect
@Component
public class ValidationAspect {
    ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final ExecutableValidator executableValidator = factory.getValidator().forExecutables();
    private final Validator validator = factory.getValidator();

    @Pointcut("@target(org.springframework.web.bind.annotation.RestController) && (@annotation(org.springframework.web.bind.annotation.RequestMapping) || @annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping))")
    public void controllerBefore() {
    }

    @Before("controllerBefore()")
    public void before(JoinPoint point) throws NoSuchMethodException, SecurityException, ValidationException {
        // 获得切入目标对象
        Object target = point.getThis();
        // 获得切入方法参数
        Object[] args = point.getArgs();
        // 获得切入的方法
        Method method = ((MethodSignature) point.getSignature()).getMethod();

        // 校验实体模型
        Set<ConstraintViolation<Object>> validResult4Model = validModelField(args);

        // 校验QueryString参数
        Set<ConstraintViolation<Object>> validResult4Param = validMethodParams(target, method, args);

        Set<ConstraintViolation<Object>> validResult = new HashSet<>();
        validResult.addAll(validResult4Model);
        validResult.addAll(validResult4Param);

        if (!validResult.isEmpty()) {
            String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
            ValidationExceptionResult validationResult = new ValidationExceptionResult();

            List<ValidationExceptionResult.InvalidField> errors = new ArrayList<>();
            for (ConstraintViolation<Object> constraintViolationTmp : validResult) {
                String name = constraintViolationTmp.getPropertyPath().toString();
                //方法参数无法获取名字，获取到arg0,arg1,这样的，需要特殊处理
                if (name.contains("arg")) {
                    int index = Integer.valueOf(name.replaceAll(".*[^\\d](?=(\\d+))", ""));
                    name = parameterNames[index];
                }
                ValidationExceptionResult.InvalidField error = validationResult.new InvalidField();
                error.setName(name);
                error.setMessage(constraintViolationTmp.getMessage());
                error.setRejectedValue(constraintViolationTmp.getInvalidValue());
                errors.add(error);
            }

            String path = method.getDeclaringClass().getName() + "." + method.getName();

            validationResult.setPath(path);
            validationResult.setErrors(errors);

            throw new ValidationException(validationResult);
        }
    }

    private <T> Set<ConstraintViolation<T>> validMethodParams(T obj, Method method, Object[] params) {
        return executableValidator.validateParameters(obj, method, params);
    }

    private <T> Set<ConstraintViolation<T>> validModelField(T[] models) {
        Set<ConstraintViolation<T>> validResultList = new HashSet<>();

        for (T model : models) {
            if (model == null) {
                continue;
            }
            Set<ConstraintViolation<T>> modelValidResult = validator.validate(model);
            validResultList.addAll(modelValidResult);
        }
        return validResultList;
    }
}
