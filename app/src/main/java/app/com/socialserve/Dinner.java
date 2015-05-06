package app.com.socialserve;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("events")
public class Dinner extends ParseObject {

    public Dinner(){

    }

    public String getTitle() {
        return getString("name");
    }

    public void setTitle(String name) {
        put("name", name);
    }

    public String getHost() {
        return getString("host");
    }

    public void setHost(String host){
        put("host", host);
    }

    public String getTime(){
        return getString("time");
    }

    public void setTime(String time){
        put("time", time);
    }

    public String location(){
        return getString("location");
    }

    public void setLocation(String location){
        put("location", location);
    }

    public String getDescription(){
        return getString("description");
    }

    public void setDescription(String description){
        put("description", description);
    }

    public String getIngredients(){
        return getString("ingredients");
    }

    public void setIngredients(String ingredients){
        put("ingredients", ingredients);
    }

    public void setSeats(int seats){ put("seatsAvailable", seats); }

    public int getSeats(){
        return getInt("seatsAvailable");
    }

    public int getSeatsSold(){ return getInt("seatsSold"); }

}
