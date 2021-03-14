public class Employee {

    public long id;                                                 // идентификатор сотрудника
    public String firstName;                                        // имя сотрудника
    public String lastName;                                         // фамилия сотрудника
    public String country;                                          // страна проживания сотрудника
    public int age;                                                 // кол-во лет сотруднику
    /**
     * Необходим для организации парсинга из CSV
     * в объект класса Employee.
     */
    public Employee() {

    }
    /**
     * Перегруженный констрктор класса, для инициализации
     * полей класса.
     *
     * @param id идентификатор сотрудника
     * @param firstName имя сотрудника
     * @param lastName фамилия сотрудника
     * @param country страна проживания сотрудника
     * @param age кол-во лет сотруднику
     */
    public Employee(long id, String firstName, String lastName, String country, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", age=" + age +
                '}';
    }
}