package cn.liaozh.common.validator;

import cn.liaozh.common.validator.annotation.IDMust;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 验证主键ID参数
 */
public class IDMustValidator implements ConstraintValidator<IDMust, Integer> {

    @Override
    public void initialize(IDMust constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null && value > 0;
    }

}
