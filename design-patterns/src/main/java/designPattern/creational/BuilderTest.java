package designPattern.creational;

public class BuilderTest {
    //required parameter
    private  String name;
    private int age;
    private int code;
    //optional

    private String firtName;
    private String latName;

    public BuilderTest(NameBuilder builder){
        this.age=builder.age;
        this.code=builder.code;
        this.name=builder.name;
        this.firtName= builder.firtName;
        this.latName= builder.latName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getFirtName() {
        return firtName;
    }

    public void setFirtName(String firtName) {
        this.firtName = firtName;
    }

    public String getLatName() {
        return latName;
    }

    public void setLatName(String latName) {
        this.latName = latName;
    }

   public static class NameBuilder{
        private final String name;
       private final int age;
       private final int code;

       public NameBuilder(String name ,int age,int code) {
           this.age = age;
           this.name=name;
           this.code=code;
       }

       private String firtName;
       private String latName;

       public NameBuilder firtName(String firtName){
           this.firtName=firtName;
           return this;
       }

       public NameBuilder latName(String latname){
           this.latName=latname;
           return this;
       }
        public BuilderTest build(){
           return new BuilderTest(this);
        }
    }
}
