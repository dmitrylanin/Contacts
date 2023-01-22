package contacts;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person extends Contact{
    private String surname;
    private String birthDate;
    private String gender;

    public Person(Scanner scanner){
        super();
        buildingPerson(scanner, this);
    }

    public void buildingPerson(Scanner scanner, Person person){
        System.out.println("Enter the name:");
        person.setName(scanner.nextLine());

        System.out.println("Enter the surname:");
        person.setSurname(scanner.nextLine());

        System.out.println("Enter the birth date:");
        person.setBirthDate((scanner.nextLine().trim()));

        System.out.println("Enter the gender (M, F):");
        person.setGender((scanner.nextLine().toUpperCase()));

        System.out.println("Enter the number:");
        person.setNumber(scanner.nextLine());
    }

    @Override
    public String printContact(){
        String str = "";
        if(this.name != null){
            str += "Name: " + this.name + "\n";
        }else{
            str += "Name: [no data]\n";
        }

        if(this.surname != null){
            str += "Surname: " + this.surname + "\n";
        }else{
            str += "Surname: [no data]\n";
        }

        if(this.birthDate != null){
            str += "Birth date: " + this.birthDate + "\n";
        }else{
            str += "Birth date: [no data]\n";
        }

        if(this.gender != null){
            str += "Gender: " + this.gender + "\n";
        }else{
            str += "Gender: [no data]\n";
        }

        if(this.number != null){
            str += "Number: " + this.number + "\n";
        }else{
            str += "Number: [no data]\n";
        }

        str+="Time created: " + this.timeCreated + "\n" +
                "Time last edit: " + this.timeEdited;

        return str;
    }

    @Override
    public String edit(Scanner scanner){
        System.out.println("Select a field (name, surname, birth, gender, number):");
        String field = scanner.nextLine();
        switch (field){
            case("name"):
                System.out.println("Enter name: ");
                this.name = scanner.nextLine();
                break;
            case("surname"):
                System.out.println("Enter surname: ");
                this.surname = scanner.nextLine();
                break;
            case("birth"):
                System.out.println("Enter birth: ");
                this.birthDate = scanner.nextLine();
                break;
            case("gender"):
                System.out.println("Enter gender: ");
                this.gender = scanner.nextLine();
                break;
            case("number"):
                System.out.println("Enter number: ");
                this.number = scanner.nextLine();
                break;
        }
        this.setTimeEdited(LocalDateTime.now().withNano(0));
        return "Saved";
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public static boolean isCorrectBirthDate(String date){
        if(date.isEmpty()){
            return false;
        }

        String base = "[0-9]{4}s*-?[0-9]{2}s*-?[0-9]{2}";
        Pattern pattern = Pattern.compile(base, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    public void setGender(String gender) {
        if(!gender.equals("M") && !gender.equals("F")){
            System.out.println("Bad gender!");
            this.gender = null;
        }else{
            this.gender = gender;
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setBirthDate(String birthDate) {
        if(isCorrectBirthDate(birthDate)){
            this.birthDate = birthDate;
        }else{
            System.out.println("Bad birth date!");
            this.birthDate = null;
        }
    }
}
