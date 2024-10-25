package com.uol.candidate_evaluation_project.main.error;

public class Errors {
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    public static class InvalidUserException extends RuntimeException {
        public InvalidUserException(String message) {
            super(message);
        }
    }

    public static class ParamNullorEmptyException extends RuntimeException {
        public ParamNullorEmptyException(String message) {
            super(message);
        }
    }
}
