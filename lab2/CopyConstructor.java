class Room{
    float area;
    boolean copied;
    Room(float area){
        this.area = area;
        copied = false;
        area = 0;
    }
    Room(Room R){
        this.area = R.area;
        R.copied = true;
    }
    public String toString() {
        return "Area = " + area + " copied = " + copied;
    }
}


public class CopyConstructor {
    public static void main(String[] args) {
        float roomArea =  50.56f;
        //roomArea is passed as value
        Room room1 = new Room(roomArea);
        //No change in the roomArea variable
        System.out.println(roomArea);
        //room1 is passed as reference
        Room room2 = new Room(room1);
        System.out.println("Room 1 ");
        System.out.println(room1);
        System.out.println("Room 2");
        System.out.println(room2);
    }
}
