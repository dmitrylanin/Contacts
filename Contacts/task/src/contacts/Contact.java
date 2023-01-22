package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Contact implements Serializable {
    protected String name;
    protected String number;
    protected LocalDateTime timeCreated;
    protected LocalDateTime timeEdited;

    public Contact(){
        this.timeCreated = LocalDateTime.now().withNano(0);
        this.timeEdited = this.timeCreated;
    }

    public static boolean isCorrectNumber(String number){
        String base = "\\+?\\s*-?\\d*\\s*(\\(\\s*[a-z0-9]{2,}(?<=\\([a-z0-9]{2,})\\)|\\s*[a-z0-9]{2,}|\\s*)(\\s*-?|\\s*-{1,}\\s*)(\\(\\s*[a-z0-9]{2,}(?<=\\([a-z0-9]{2,})\\)|\\s*[a-z0-9]{2,}|\\s*)([ -]\\w{2,})*";
        Pattern pattern = Pattern.compile(base, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    public abstract String edit(Scanner scanner);

    public abstract String printContact();

    public void setName(String name){
        this.name = name;
    };

    public void setNumber(String number){
        if(!Contact.isCorrectNumber(number)) {
            System.out.println("Wrong number format!");
            this.number = null;
        }else {
            this.number = number;
        }
    }

    public String getNumber() {
        return number;
    }

    public String getName(){
        return this.name;
    };

    public void setTimeEdited(LocalDateTime timeEdited) {
        this.timeEdited = timeEdited;
    }
}
