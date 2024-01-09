public class NoDefault {
    int a;
    NoDefault(int a){
        this.a = a;
    }
    public static void main(String[] args) {
        //Error: The constructor NoDefault() is undefined
        NoDefault nd = new NoDefault();
    }
}
