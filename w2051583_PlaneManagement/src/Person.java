public class Person {
    private String name;
    private String surname;
    private String personal_email;

    public Person(String name, String surname, String personal_email) {
        this.name = name;
        this.surname = surname;
        this.personal_email = personal_email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPersonalemail() {
        return personal_email;
    }

    public void setPersonalemail(String personal_email) {
        this.personal_email = personal_email;}
}
