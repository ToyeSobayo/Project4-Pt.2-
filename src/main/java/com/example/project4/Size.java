package com.example.project4;

/**
 * Represents the available sizes for pizzas.
 */
public enum Size {
    // Defines the Large size option
    LARGE {
        @Override
        public String toString() {
            return "Large";
        }
    },

    // Defines the Medium size option
    MEDIUM {
        @Override
        public String toString() {
            return "Medium";
        }
    },

    // Defines the Small size option
    SMALL {
        @Override
        public String toString() {
            return "Small";
        }
    }
}
