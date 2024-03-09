import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class User {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void saveUserData(User user){
        try(FileWriter fileWriter = new FileWriter("userData.txt", false)){
            fileWriter.write(user.getEmail() + "\n" + user.getPassword() );
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public User readUserData(){
        User user = new User();
        try{
            Scanner scanner = new Scanner(new File("userData.txt"));
            user.setEmail(scanner.nextLine());
            user.setPassword(scanner.nextLine());
        }catch(Exception e){
            e.printStackTrace();
        }

        return user;
    }
}
