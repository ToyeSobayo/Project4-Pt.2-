package com.example.project4;
/**
 * Represents the types of sauce available for pizzas.
 */
public enum Sauce {

    // Defines the Alfredo sauce option
    ALFREDO{
        @Override
        public String toString(){
            return "Alfredo";
        }
    },
    // Defines the Tomato sauce option
    TOMATO{
        @Override
        public String toString(){
            return "Tomato";
        }
    },
}
