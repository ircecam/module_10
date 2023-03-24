package module_10;

public class User {
    private String name;
    private int age;

    public User(String name, int age){
        this.name = name;
        this.age = age;
    }
    public String toString(){
        return "[" + name + ": " + Integer.valueOf(age).toString() + "]";
    }

}
