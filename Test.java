public class Test {
    
    public static void main(String[] args) {
        Test test = new Test();
        test.main();
    }

    private void main() {
        Son1 son1 = new Son1();
        Son son = new Son();
        son1.setX(1);
        son.setX(2);
        System.out.println(son1.getX());
        System.out.println(son.getX());
    }
}
