package cn.liaozh.common.validator;

import cn.liaozh.common.validator.annotation.IntArrayEmpty;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


/**
 * 验证整数数组是否为空
 */
public class IntArrayEmptyValidator implements ConstraintValidator<IntArrayEmpty, int[]> {

    @Override
    public void initialize(IntArrayEmpty constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(int[] value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        return value.length > 0;
    }

}
