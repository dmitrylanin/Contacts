package contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ContactList{
    protected ArrayList<Contact> contacts;
    private File dataFile;

    ContactList(String fileName){
        this.contacts = new ArrayList<>();
        if(fileName != null){
            this.dataFile = new File(fileName);

            if (!this.dataFile.exists()){
                try {
                    this.dataFile.createNewFile();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(dataFile.length()>0) {
                readDataFromFile();
            }
        }
    }

    public HashMap<Integer, Contact> searchData(String query){
        HashMap<Integer, Contact> searchResult = new HashMap<>();
        int count = 1;
        for (int i = 0; i<contacts.size(); i++){
            if(contacts.get(i).getClass() == Person.class){
                Person person = (Person) contacts.get(i);
               if((person.getName().toLowerCase() + " " + person.getSurname().toLowerCase() + " " + person.getNumber()).contains(query)){
                   searchResult.put(count, person);
                   count++;
               }
            }else{
                Organisation organisation = (Organisation) contacts.get(i);
                if((organisation.getName().toLowerCase()+ " " + organisation.getNumber()).contains(query)){
                    searchResult.put(count, organisation);
                    count++;
                }
            }
        }
        return searchResult;
    }

    public void removeContact(Contact contact){
        if(contacts.size()>0){
            contacts.remove(contact);
        }else{
            System.out.println("No records to remove");
        }
    }

    private void readDataFromFile(){
        try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)))){
            this.contacts =(ArrayList<Contact>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewContact(Contact newContact){
        contacts.add(newContact);
        if(dataFile != null){
            try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)))) {
                oos.writeObject(contacts);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateContactBase(){
        if(dataFile != null){
            try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)))) {
                oos.writeObject(contacts);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int count(){
        return contacts.size();
    }

    public void printContactList(){
        for (int i = 0; i<contacts.size(); i++){
            if(contacts.get(i).getClass() == Person.class){
                Person person = (Person) contacts.get(i);
                System.out.println(i+1 + ". " + person.getName() + " " + person.getSurname());
            }else{
                Organisation organisation = (Organisation) contacts.get(i);
                System.out.println(i+1 + ". " + organisation.getName());
            }
        }
    }

    public void printConcreteInfo(int index){
        if(contacts.get(index).getClass() == Person.class){
            Person person = (Person) contacts.get(index);
            System.out.println(person.printContact());
        }else{
            Organisation organisation = (Organisation) contacts.get(index);
            System.out.println(organisation.printContact());
        }
    }

}