public class PayRate {
    public static void main(String[] args) {
        if (args.length == 2) {
            try {

                float hours = Float.valueOf(args[0]);
                float rate = Float.valueOf(args[1]);
                float pay = hours * rate;
                if (rate < 12.5) {
                    System.out.println("Less than minimum rate");
                }
                if (hours > 7.24) {
                    System.out.println("Over worked");
                }
                System.out.println("Pay = " + pay);
            } catch (Exception e) {
                System.out.println("Error parsing arguments");
            }
        } else {
            System.out.println("Provide 2 arguments");
        }

    }
}
