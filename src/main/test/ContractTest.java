import bean.ContrastObject;

public class ContractTest {


    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        SimpleObj s1 = new SimpleObj();
        s1.setM((float) 2.2);
        s1.setY("55555");
        ContrastObject<SimpleObj> sim = ContrastUtil.contrast(new SimpleObj(), s1);


        System.out.print(sim.getTargetChange().m + " " + sim.getTargetChange().x + " " + sim.getTargetChange().y);
    }
}
