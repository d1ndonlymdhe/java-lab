public class ClassCount {
    static int count = 0;
    ClassCount(){
        ClassCount.count++;
    }
    public static void main(String[] args) {
        ClassCount c1 = new ClassCount();
        ClassCount c2 = new ClassCount();
        ClassCount c3 = new ClassCount();
        System.out.println(ClassCount.count);
    }
}

