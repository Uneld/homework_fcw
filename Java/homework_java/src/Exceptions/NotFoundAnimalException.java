package Exceptions;

import javax.xml.namespace.QName;

public class NotFoundAnimalException extends Exception{
    String name;
    int age;

    static final int ERR_AGE = 345;

    public NotFoundAnimalException(String name) {
        this.name = name;
        this.age = ERR_AGE;
    }
    public NotFoundAnimalException(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String getMessage() {
        if (age == ERR_AGE){
            return "Животное с именем: " + name + " отсутствует.";
        }
        return "Животное с именем: " + name + "и возрастом: " + age + " отсутствует.";
    }
}
