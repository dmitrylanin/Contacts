package contacts;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Organisation extends Contact{
    private String address;

    public Organisation(Scanner scanner){
        super();
        buildingOrganisation(scanner, this);
    }

    @Override
    public String edit(Scanner scanner){
        System.out.println("Select a field (name, address, number):");
        String field = scanner.nextLine();
        switch (field){
            case("name"):
                System.out.println("Enter name: ");
                this.name = scanner.nextLine();
                break;
            case("address"):
                System.out.println("Enter address: ");
                this.address = scanner.nextLine();
                break;
            case("number"):
                System.out.println("Enter number: ");
                this.number = scanner.nextLine();
                break;
        }
        this.setTimeEdited(LocalDateTime.now().withNano(0));
        return "Saved";
    }

    public void buildingOrganisation(Scanner scanner, Organisation organization){
        System.out.println("Enter the organization name:");
        organization.setName(scanner.nextLine());
        System.out.println("Enter the address:");
        organization.setAddress(scanner.nextLine());
        System.out.println("Enter the number:");
        String number = scanner.nextLine();
        if(Contact.isCorrectNumber(number)){
            organization.setNumber(number);
        }else{
            System.out.println("Wrong number format!");
            organization.setNumber(null);
        }
    }

    @Override
    public String printContact(){
        String str = "";
        if(this.name != null){
            str += "Organization name: " + this.name + "\n";
        }else{
            str += "Organization name: [no data]\n";
        }

        if(this.address != null){
            str += "Address: " + this.address + "\n";
        }else{
            str += "Address: [no data]\n";
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

    public void setAddress(String address) {
        this.address = address;
    }
}