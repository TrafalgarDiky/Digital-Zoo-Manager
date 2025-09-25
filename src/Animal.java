public class Animal {
    private String name;
    private int age;

    // Construktor
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //Getter & Setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }

    // Method
    public String makeSound() {
        return "The animal makes a sound.";
    }

    public String getInfo() {
        return "Name: " + name + ", Age: " + age;
    }

}