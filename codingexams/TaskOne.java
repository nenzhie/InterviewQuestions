public class TaskOne {

    /* task 1 sample codes */
    public interface IncDec {
        void increment();
        void decrement();
    }

    public static class MyIncDec implements IncDec {
        private int x;

        public MyIncDec(int x) {
            this.x = x;
        }

        public void increment() { this.x++; }
        public void decrement() { this.x--; }
    }
    /* task 1 sample codes */

    /*
     * Part 1: The task is to be able to have an additional service on
     * the existing class that will measure and log the execution time
     * of each method invocation without changing anything in the
     * existing sample class
     */

    /*
     * SOLUTION:  To do that, my approach would be to utilize the
     * Decorator design pattern. This pattern essentially involves
     * creating the same class but extending its methods with an
     * added feature (such as the measure and log service).
     *
     * What does it look like? The 'decorator' class implements the
     * same interface, so the reference type remains abstract. Then,
     * the decorator class will encapsulate an instance of the
     * original and reimplement each method, adding the desired feature.
     *
     * With this, we were able to put an additional feature on the base
     * class without actually modifying the original one.
     *
     * It is safe, flexible, and the original class still exists
     * and is optional
     */

    public static class MyIncDecDecorator implements IncDec {
        private MyIncDec myIncDec;
        private long timestamp;

        public MyIncDecDecorator(MyIncDec myIncDec) {
            this.myIncDec = myIncDec;
        }

        public void increment() {
            timestamp = System.currentTimeMillis();
            this.myIncDec.increment();

            System.out.println("[increment] done execution after "
                            + (System.currentTimeMillis() - timestamp)
                            + " milliseconds");
        }

        public void decrement() {
            timestamp = System.currentTimeMillis();
            this.myIncDec.decrement();

            System.out.println("[decrement] done execution after "
                    + (System.currentTimeMillis() - timestamp)
                    + " milliseconds");
        }
    }

    /*
     * Part 2: Same task but done across multiple classes
     */

    /*
     * SOLUTION: To do that, my approach would be to make a master factory
     * of the decorator objects. Each class that we want to have a
     * measure-and-log service will have an existing decorator version of
     * it. Then the factory (statically shared across the application)
     * can easily provide the decorator instances when needed.
     *
     * The idea is that this factory class will act as a utility to the
     * application.
     *
     * This approach is not just flexible; it also gives a sense of
     * optionality to apply the feature only to the specific area of code
     * that needs it.
     * */

    public static class MeasureLogFactory {
        // since we want it to be shared across application
        // as a static utility, we do not allow creation of instance
        private MeasureLogFactory() {}

        public static IncDec getIncDec(IncDec c) {
            if(c instanceof MyIncDec) {
                return new MyIncDecDecorator((MyIncDec) c);
            } else {
               // other classes with decorator
               return null;
            }
        }
    }


    // result
    public static void main(String[] args) {
        // Part 1: when using the decorator class,
        // developers just have to change the object
        // being passed on the interface reference.
        // See that it's more flexible and scalable
        // without having to modify the existing class code

        IncDec myIncDec = new MyIncDecDecorator(new MyIncDec(0));
        myIncDec.increment();
        myIncDec.decrement();
        myIncDec.increment();
        myIncDec.increment();
        myIncDec.increment();
        myIncDec.decrement();
        myIncDec.decrement();
        myIncDec.increment();


        // Part 2: Static factory can be shared across application
        // and, it can produce decorator version of the class which means
        // devs have the liberty to choose whether a certain area
        // of code needs measure-and-logging service or not

        System.out.println("FROM FACTORY");
        IncDec myIncDecFromFactory =
                MeasureLogFactory.getIncDec(new MyIncDec(0));

        if(myIncDecFromFactory != null) {
            myIncDecFromFactory.increment();
            myIncDecFromFactory.decrement();
            myIncDecFromFactory.increment();
            myIncDecFromFactory.increment();
            myIncDecFromFactory.increment();
            myIncDecFromFactory.decrement();
            myIncDecFromFactory.decrement();
            myIncDecFromFactory.increment();
        }
    }
}
