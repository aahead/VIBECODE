// Base class
// TODO: Define a class called Animal with:
class Animal{

    // - A protected String field called name
    protected String name;
    // - A constructor that takes a name and assigns it
    Animal(String name){
        this.name = name;
    }
    // - A public void method called speak() that prints a generic sound
    public void speak(){
        System.out.println("Burp");
    }
    // - Override toString() to return "Animal: " + name
    @Override
    public String toString(){
        return "Animal: " + name;
    }
    // - Override equals(Object o) to compare names if o is an Animal
    @Override
    public boolean equals(Object o) {
        if(o instanceof Animal){
            return ((Animal)o).name == this.name;
        } else {
            return false;
        }
    }
   
}
// Subclass
// TODO: Define a class called Mammal that extends Animal with:
class Mammal extends Animal{

    // - A protected boolean field called warmBlooded (set to true)
    protected boolean warmBlooded = true;
    // - A protected String field called dietType (e.g., "herbivore")
    protected String dietType;
    // - A protected int field called numLegs (e.g., 4)
    protected int numLegs;
    // - A protected String field called sound (e.g., "growl")
    protected String sound;
    // - A constructor that uses super to set name and sets these fields
    Mammal(String name, boolean warmBlooded, String dietType, int numLegs, String sound){
        super(name);
        this.warmBlooded = warmBlooded;
        this.dietType = dietType;
        this.numLegs = numLegs;
        this.sound = sound;
    }
    // - Override speak() to print the value of sound prefixed by name
    @Override
    public void speak(){
        System.out.println(name + " says " + sound);
    }
    public void performTrick(){
        System.out.println("*Does a Backflip*");
    }
    }
    // Subclasses of Mammal
    // TODO: Define a class Dog that extends Mammal
class Dog extends Mammal{

    // - Constructor takes name and passes name, true, "omnivore", 4, "Woof!" to super
    Dog(String name){
        super(name, true, "omnivore", 4, "Woof!");
    }
    // - Override speak() to print "<name> says: Woof!"
    @Override
    public void speak() {
        System.out.println(name + " says: " + sound);
    }
    
    @Override
    public boolean equals(Object o) {
        if((o instanceof Dog) && o == ((Dog)o)){
            return ((Animal)o).name == this.name;
        } else {
            return false;
        }
    }

   
}
// TODO: Define a class Cat that extends Mammal
class Cat extends Mammal{
    // - Constructor takes name and passes name, true, "carnivore", 4, "Meow!" to super
    Cat(String name){
        super(name, true, "carnivore", 4, "Meow!");
    }
    // - Override speak() to print "<name> says: Meow!"
    @Override
    public void speak() {
        System.out.println(name + " says: " + sound);
    }  
    
    @Override
    public boolean equals(Object o) {
        if((o instanceof Cat) && o == ((Cat)o)){
            return ((Animal)o).name == this.name;
        } else {
            return false;
        }
    }
}


// TODO: Define a final class Human that extends Mammal
final class Human extends Mammal{

// - Constructor takes name and passes name, true, "omnivore", 2, "Hello!" to super
    Human(String name){
        super(name, true, "omnivore", 2, "Hello!");
    }
}
// TODO: Define a class Reptile that extends Animal
class Reptile extends Animal{
    // - Add fields: boolean warmBlooded (set to false), int numLegs, String dietType, String sound
    boolean warmBlooded = false;
    int numLegs;
    String dietType;
    String sound;
   
    // - Constructor should take all values and use super for name
    Reptile(String name, boolean warmBlooded, int numLegs, String dietType, String sound){
        super(name);
        this.warmBlooded = warmBlooded;
        this.numLegs = numLegs;
        this.dietType = dietType;
        this.sound = sound;
    }
    // - Override speak() to print the value of sound prefixed by name
    @Override
    public void speak() {
        System.out.println(name + " says: " + sound);
    }
}
// Main class to test
// TODO: Define class AnimalKingdomTest with a main method
class AnimalKingdomTest{
    public static void main(String[] args) {
        // - Create Animal a1 = new Dog("Buddy")
        Animal a1 = new Dog("Buddy");
        a1.speak();
        // - Create Animal a2 = new Cat("Whiskers")
        Animal a2 = new Cat("Whiskers");
        a2.speak();
        // - Call speak() on both (demonstrating polymorphism)
        // - Print both using System.out.println
        // - Use equals() to compare a1 to a new Dog("Buddy")
        System.out.println(a1.equals(new Dog("Buddy")));
        Mammal myMammal = (Mammal) a1;
        myMammal.performTrick();
    }
}



// Additional TODOs:
// 1. Create a new subclass of Animal (e.g., Bird) and override speak()
class Bird extends Animal{
    Bird(String name){
        super(name);
    }

    @Override
    public void speak() {
        System.out.println(name + " says: " + "Tweet Tweet");
    }
}
// 2. Try to extend Human – what happens and why?

// Answer: I got an error saying you cannot inherit from human, this is because Human is final.

// 3. Override equals() in Dog or Cat to also check the type
// DONE

// 4. Add a method to Mammal called performTrick() and call it via downcasting
// DONE
