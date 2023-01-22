package contacts;

import java.util.*;

public class Controller {
    private Scanner scanner;
    private boolean stopMarker;
    private ContactList contactList;

    Controller(String fileName){
        this.scanner = new Scanner(System.in);
        this.stopMarker = false;
        this.contactList = new ContactList(fileName);
    }

    public void engine(){
        while (!stopMarker){
            System.out.println("[menu] Enter action (add, list, search, count, exit):");
            String command = scanner.nextLine();

            switch (command){
                case ("add"):
                    System.out.println("Enter the type (person, organization):");
                    System.out.println(addContact(scanner.nextLine()));
                    break;
                case ("list"):
                    toInfo();
                    //listInfo();
                    break;
                case ("info"):
                    toInfo();
                    break;
                case ("search"):
                    search();
                    break;
                case ("count"):
                    System.out.println(countContacts());
                    break;
                case ("edit"):
                    toEdit();
                    break;
                case ("remove"):
                    System.out.println("No records to remove");
                    break;
                case ("exit"):
                    stopMarker = true;
                    break;
            }
            if(!command.equals("exit")){
                System.out.println();
            }
        }
        scanner.close();
    }

    public void toEdit(){
        if(contactList.count()==0){
            System.out.println("No records to edit");
        }else{
            int index = Integer.parseInt(scanner.nextLine());
            Contact contact = contactList.contacts.get(index-1);
            contact.edit(scanner);
        }
    }

    public void search(){
        System.out.println("Enter search query: ");
        HashMap<Integer, Contact> searchResult = contactList.searchData((scanner.nextLine()).toLowerCase());
        System.out.println("Found " + searchResult.size() + " results:");
        int count = 1;
        for(Map.Entry<Integer, Contact> entry : searchResult.entrySet()){
            if(entry.getValue().getClass()== Person.class) {
                Person person = (Person) entry.getValue();
                System.out.println(count + ". " + person.getName() + " " + person.getSurname());
            }else{
                Organisation organisation = (Organisation) entry.getValue();
                System.out.println(count + ". " + organisation.getName());
            }
            count++;
        }
        System.out.println();
        System.out.println("[search] Enter action ([number], back, again):");
        String command = scanner.nextLine();
        if(command.equals("back")){

        }else if(command.equals("again")){
            search();
        }else if(searchResult.size()>0) {
            int index = Integer.parseInt(command);
            System.out.println(searchResult.get(index).printContact());
            System.out.println("[record] Enter action (edit, delete, menu):");

            switch (scanner.nextLine()){
                case("edit"):
                    System.out.println(searchResult.get(index).edit(scanner));
                    searchResult.get(index).printContact();
                    contactList.updateContactBase();
                    break;
                case("delete"):
                    contactList.removeContact(searchResult.get(index));
                    break;
                case("menu"):
                    break;
            }
        }
    }

    public void toInfo(){
        contactList.printContactList();
        System.out.println();
        System.out.println("Enter index to show info: ");
        int index = Integer.parseInt(scanner.nextLine());
        contactList.printConcreteInfo(index-1);
        Contact currentContact = contactList.contacts.get(index-1);

        boolean marker = false;
        while (!marker){
            System.out.println("[record] Enter action (edit, delete, menu):");
            switch (scanner.nextLine()){
                case("edit"):
                    currentContact.edit(scanner);
                    contactList.updateContactBase();
                    break;
                case("delete"):
                    contactList.removeContact(currentContact);
                    break;
                case("menu"):
                    marker = true;
                    break;
            }
        }
    }

    public void listInfo(){
        contactList.printContactList();
    }

    public void listContacts(){
        contactList.printContactList();
        int index = Integer.parseInt(scanner.nextLine());
        contactList.printConcreteInfo(index-1);
        System.out.println();
    }

    public String addContact(String type){
        if(type.equals("person")){
            Contact person = new Person(scanner);
            contactList.addNewContact(person);
        }else{
            Contact organisation = new Organisation(scanner);
            contactList.addNewContact(organisation);
        }
        return "The record added.";
    }


    public String countContacts(){
        return "The Phone Book has " + contactList.count() + " records.";
    }
}
