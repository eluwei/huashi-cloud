package com.huashi.cloud.common.exception.result;

import java.util.List;

public class ValidationExceptionResult {
    String path;
    List<InvalidField> errors;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<InvalidField> getErrors() {
        return errors;
    }

    public void setErrors(List<InvalidField> errors) {
        this.errors = errors;
    }

    public class InvalidField {
        private String name;
        private String message;
        private Object rejectedValue;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getRejectedValue() {
            return rejectedValue;
        }

        public void setRejectedValue(Object rejectedValue) {
            this.rejectedValue = rejectedValue;
        }
    }

    public String getMessage(){
        if (null != errors && errors.size() > 0) {
            return errors.get(0).getMessage();
        }
        return null;
    }
}