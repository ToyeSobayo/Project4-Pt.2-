package com.example.project4;

public enum Size {
    SMALL{
        @Override
        public String toString(){
            return "Small";
        }
    },
    MEDIUM{
        @Override
        public String toString(){
            return "Medium";
        }
    },
    LARGE{
        @Override
        public String toString(){
            return "Large";
        }
    },
}
