import bean.ContrastObject;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContractTest {


    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
//        testSimple();
//        testObject();
//        testSimpleMap();
//        testSimpleList();
//        testListList();
//        testListObject();
//        testListObjectRewriteEqual();
    }

    private static void testObject() throws InstantiationException, IllegalAccessException {
        SimpleObjectObj simpleObjectObj = new SimpleObjectObj();
        simpleObjectObj.setX(1);
        simpleObjectObj.setY("123");
        simpleObject s = new simpleObject();
        s.setXx(1111);
        s.setYy("1111");
        simpleObjectObj.setSimpleObject(s);

        SimpleObjectObj simpleObjectObj1 = new SimpleObjectObj();
        simpleObjectObj1.setX(1);
        simpleObjectObj1.setY("123");
        simpleObject s2 = new simpleObject();
        s2.setXx(1111);
        s2.setYy("1111");
        simpleObjectObj1.setSimpleObject(s2);


        ContrastObject<SimpleObjectObj> sim1 = ContrastUtil.contrast(simpleObjectObj, simpleObjectObj1);
        System.out.println("不重写equal的对象的值一样，其他没差别 ");
        if (null != sim1) {
            System.out.println(sim1.getOriginChange().x + " " + sim1.getOriginChange().y + " " + sim1.getOriginChange().getSimpleObject() + " " + sim1.getOriginChange().getSimpleObjectRewrite());
            System.out.println(sim1.getTargetChange().x + " " + sim1.getTargetChange().y + " " + sim1.getTargetChange().getSimpleObject() + " " + sim1.getTargetChange().getSimpleObjectRewrite());
        }


        SimpleObjectObj simpleObjectObj2 = new SimpleObjectObj();
        simpleObjectObj.setX(1);
        simpleObjectObj.setY("123");
        simpleObjectRewrite s3 = new simpleObjectRewrite();
        simpleObject sx = new simpleObject();
        sx.setXx(1111);
        sx.setYy("1111");
        simpleObjectObj.setSimpleObject(sx);
        simpleObjectObj.setSimpleObjectRewrite(s3);

        SimpleObjectObj simpleObjectObj3 = new SimpleObjectObj();
        simpleObjectObj1.setX(1);
        simpleObjectObj1.setY("123");
        simpleObjectRewrite s4 = new simpleObjectRewrite();
        s4.setXx(1111);
        s4.setYy("1111");
        simpleObject sx1 = new simpleObject();
        sx1.setXx(1111);
        sx1.setYy("1111");
        simpleObjectObj.setSimpleObject(sx1);
        simpleObjectObj1.setSimpleObjectRewrite(s4);

        ContrastObject<SimpleObjectObj> sim2 = ContrastUtil.contrast(simpleObjectObj2, simpleObjectObj3);
        System.out.println("重写equal的对象的值一样，其他没差别 ");
        if (null != sim2) {
            System.out.println(sim2.getOriginChange().x + " " + sim2.getOriginChange().y + " " + sim2.getOriginChange().getSimpleObject() + " " + sim2.getOriginChange().getSimpleObjectRewrite());
            System.out.println(sim2.getTargetChange().x + " " + sim2.getTargetChange().y + " " + sim2.getTargetChange().getSimpleObject() + " " + sim2.getTargetChange().getSimpleObjectRewrite());
        }

        SimpleObjectObj simpleObjectObj4 = new SimpleObjectObj();
        simpleObjectObj4.setX(1);
        simpleObjectObj4.setY("123");
        simpleObjectRewrite s5 = new simpleObjectRewrite();
        s5.setXx(1111);
        s5.setYy("1111");
        simpleObjectObj4.setSimpleObjectRewrite(s5);
        simpleObject sx11 = new simpleObject();
        sx11.setXx(1111);
        sx11.setYy("1111");
        simpleObjectObj4.setSimpleObject(sx11);

        SimpleObjectObj simpleObjectObj5 = new SimpleObjectObj();
        simpleObjectObj5.setX(1);
        simpleObjectObj5.setY("123");
        simpleObjectRewrite s6 = new simpleObjectRewrite();
        s6.setXx(1111);
        s6.setYy("1111");
        simpleObjectObj5.setSimpleObjectRewrite(s6);
        simpleObject sx22 = new simpleObject();
        sx22.setXx(1111);
        sx22.setYy("1111");
        simpleObjectObj5.setSimpleObject(sx22);

        ContrastObject<SimpleObjectObj> sim3 = ContrastUtil.contrast(simpleObjectObj4, simpleObjectObj5);
        System.out.println("重写equals的对象的值一样以及不重写equals的对象值一样，其他没差别 ");
        if (null != sim3) {
            System.out.println(sim3.getOriginChange().x + " " + sim3.getOriginChange().y + " " + sim3.getOriginChange().getSimpleObject() + " " + sim3.getOriginChange().getSimpleObjectRewrite());
            System.out.println(sim3.getTargetChange().x + " " + sim3.getTargetChange().y + " " + sim3.getTargetChange().getSimpleObject() + " " + sim3.getTargetChange().getSimpleObjectRewrite());
        }


        SimpleObjectObj simpleObjectObj7 = new SimpleObjectObj();
        simpleObjectObj7.setX(1);
        simpleObjectObj7.setY("123");
        simpleObjectRewrite s10 = new simpleObjectRewrite();
        s10.setXx(1111);
        s10.setYy("11113");
        simpleObjectObj7.setSimpleObjectRewrite(s10);
        simpleObject sx12 = new simpleObject();
        sx12.setXx(1111);
        sx12.setYy("1111");
        simpleObjectObj7.setSimpleObject(sx12);

        SimpleObjectObj simpleObjectObj6 = new SimpleObjectObj();
        simpleObjectObj6.setX(1);
        simpleObjectObj6.setY("123");
        simpleObjectRewrite s11 = new simpleObjectRewrite();
        s11.setXx(1111);
        s11.setYy("1111");
        simpleObjectObj6.setSimpleObjectRewrite(s11);
        simpleObject sx13 = new simpleObject();
        sx13.setXx(1111);
        sx13.setYy("11112");
        simpleObjectObj6.setSimpleObject(sx13);

        ContrastObject<SimpleObjectObj> sim4 = ContrastUtil.contrast(simpleObjectObj7, simpleObjectObj6);
        System.out.println("对象完全不一样 ");
        if (null != sim4) {
            System.out.println(sim4.getOriginChange().x + " " + sim4.getOriginChange().y + " " + sim4.getOriginChange().getSimpleObject() + " " + sim4.getOriginChange().getSimpleObjectRewrite());
            System.out.println(sim4.getTargetChange().x + " " + sim4.getTargetChange().y + " " + sim4.getTargetChange().getSimpleObject() + " " + sim4.getTargetChange().getSimpleObjectRewrite());
        }


        SimpleObjectObj simpleObjectObjx1 = new SimpleObjectObj();
        simpleObjectObjx1.setX(1);
        simpleObjectObjx1.setY("123");

        SimpleObjectObj simpleObjectObjx2 = new SimpleObjectObj();
        simpleObjectObjx2.setX(1);
        simpleObjectObjx2.setY("123");
        simpleObjectObjx2.setSimpleObjectRewrite(s11);
        simpleObjectObjx2.setSimpleObject(sx13);

        ContrastObject<SimpleObjectObj> sim5 = ContrastUtil.contrast(simpleObjectObjx1, simpleObjectObjx2);
        System.out.println("对象的自定义对象为空的情况 ");
        if (null != sim5) {
            System.out.println(sim5.getOriginChange().x + " " + sim5.getOriginChange().y + " " + sim5.getOriginChange().getSimpleObject() + " " + sim5.getOriginChange().getSimpleObjectRewrite());
            System.out.println(sim5.getTargetChange().x + " " + sim5.getTargetChange().y + " " + sim5.getTargetChange().getSimpleObject() + " " + sim5.getTargetChange().getSimpleObjectRewrite());
        }

    }

    private static void testListObjectRewriteEqual() throws InstantiationException, IllegalAccessException {
        ListObjectObjRewriteEquals s1 = new ListObjectObjRewriteEquals();
        s1.setM((float) 2.2);
        s1.setY("55555");
        List<MyObjectRewrite> list = new ArrayList<>();
        MyObjectRewrite myObject = new MyObjectRewrite();
        myObject.setX("123");
        myObject.setY(1);
        myObject.setZ(true);
        list.add(myObject);
        s1.setList(list);

        ListObjectObjRewriteEquals s2 = new ListObjectObjRewriteEquals();
        s2.setM((float) 2.2);
        s2.setY("55555");
        List<MyObjectRewrite> list1 = new ArrayList<>();
        MyObjectRewrite myObject1 = new MyObjectRewrite();
        myObject1.setX("123");
        myObject1.setY(1);
        myObject1.setZ(true);
        list1.add(myObject1);
        s2.setList(list1);

        ListObjectObjRewriteEquals s3 = new ListObjectObjRewriteEquals();
        s3.setM((float) 2.2);
        s3.setY("55555");
        List<MyObjectRewrite> list2 = new ArrayList<>();
        MyObjectRewrite myObject2 = new MyObjectRewrite();
        myObject2.setX("234");
        myObject2.setY(2);
        myObject2.setZ(false);
        list2.add(myObject2);
        s3.setList(list2);


        ListObjectObjRewriteEquals s4 = new ListObjectObjRewriteEquals();
        s4.setM((float) 2.2);
        s4.setY("55555");
        List<MyObjectRewrite> list3 = new ArrayList<>();
        MyObjectRewrite myObject3 = new MyObjectRewrite();
        myObject3.setX("234");
        myObject3.setY(2);
        myObject3.setZ(false);
        MyObjectRewrite myObject4 = new MyObjectRewrite();
        myObject4.setX("999");
        myObject4.setY(23);
        myObject4.setZ(true);
        MyObjectRewrite myObject5 = new MyObjectRewrite();
        myObject5.setX("998");
        myObject5.setY(23);
        myObject5.setZ(true);

        list3.add(myObject3);
        list3.add(myObject4);
        list3.add(myObject5);
        s4.setList(list3);


        ListObjectObjRewriteEquals s5 = new ListObjectObjRewriteEquals();
        s5.setM((float) 2.2);
        s5.setY("55555");


        ContrastObject<ListObjectObjRewriteEquals> sim = ContrastUtil.contrast(s1, s2);
        System.out.println("重写之后使用equal方法判断");
        if (null != sim) {
            System.out.println(sim.getOriginChange().m + " " + sim.getOriginChange().x + " " + sim.getOriginChange().y + " " + sim.getOriginChange().getList());
            System.out.println(sim.getTargetChange().m + " " + sim.getTargetChange().x + " " + sim.getTargetChange().y + " " + sim.getTargetChange().getList());
        }


        ContrastObject<ListObjectObjRewriteEquals> sim2 = ContrastUtil.contrast(s1, s3);
        System.out.println("两个值不一样的对象 ");
        if (null != sim2) {
            System.out.println(sim2.getOriginChange().m + " " + sim2.getOriginChange().x + " " + sim2.getOriginChange().y + " " + sim2.getOriginChange().getList());
            System.out.println(sim2.getTargetChange().m + " " + sim2.getTargetChange().x + " " + sim2.getTargetChange().y + " " + sim2.getTargetChange().getList());
        }


        ContrastObject<ListObjectObjRewriteEquals> sim3 = ContrastUtil.contrast(s1, s4);
        System.out.println("一个对象对多个对象的List ");
        if (null != sim3) {
            System.out.println(sim3.getOriginChange().m + " " + sim3.getOriginChange().x + " " + sim3.getOriginChange().y + " " + sim3.getOriginChange().getList());
            System.out.println(sim3.getTargetChange().m + " " + sim3.getTargetChange().x + " " + sim3.getTargetChange().y + " " + sim3.getTargetChange().getList());
        }


        ContrastObject<ListObjectObjRewriteEquals> sim4 = ContrastUtil.contrast(s1, s5);
        System.out.println("空的List和非空List ");
        if (null != sim4) {
            System.out.println(sim4.getOriginChange().m + " " + sim4.getOriginChange().x + " " + sim4.getOriginChange().y + " " + sim4.getOriginChange().getList());
            System.out.println(sim4.getTargetChange().m + " " + sim4.getTargetChange().x + " " + sim4.getTargetChange().y + " " + sim4.getTargetChange().getList());
        }

    }

    private static void testListObject() throws InstantiationException, IllegalAccessException {
        ListObjectObj s1 = new ListObjectObj();
        s1.setM((float) 2.2);
        s1.setY("55555");
        List<MyObject> list = new ArrayList<>();
        MyObject myObject = new MyObject();
        myObject.setX("123");
        myObject.setY(1);
        myObject.setZ(true);
        myObject.setObj(myObject);
        list.add(myObject);
        s1.setList(list);

        ListObjectObj s2 = new ListObjectObj();
        s2.setM((float) 2.2);
        s2.setY("55555");
        List<MyObject> list1 = new ArrayList<>();
        MyObject myObject1 = new MyObject();
        myObject1.setX("123");
        myObject1.setY(1);
        myObject1.setZ(true);
        myObject1.setObj(myObject1);
        list1.add(myObject1);
        s2.setList(list1);

        ListObjectObj s3 = new ListObjectObj();
        s3.setM((float) 2.2);
        s3.setY("55555");
        List<MyObject> list2 = new ArrayList<>();
        MyObject myObject2 = new MyObject();
        myObject2.setX("234");
        myObject2.setY(2);
        myObject2.setZ(false);
        myObject2.setObj(myObject2);
        list2.add(myObject2);
        s3.setList(list2);


        ListObjectObj s4 = new ListObjectObj();
        s4.setM((float) 2.2);
        s4.setY("55555");
        List<MyObject> list3 = new ArrayList<>();
        MyObject myObject3 = new MyObject();
        myObject3.setX("234");
        myObject3.setY(2);
        myObject3.setZ(false);
        myObject3.setObj(myObject3);
        MyObject myObject4 = new MyObject();
        myObject4.setX("999");
        myObject4.setY(23);
        myObject4.setZ(true);
        myObject4.setObj(myObject4);
        MyObject myObject5 = new MyObject();
        myObject5.setX("998");
        myObject5.setY(23);
        myObject5.setZ(true);

        myObject5.setObj(myObject5);
        list3.add(myObject3);
        list3.add(myObject4);
        s4.setList(list3);


        ListObjectObj s5 = new ListObjectObj();
        s5.setM((float) 2.2);
        s5.setY("55555");


        ContrastObject<ListObjectObj> sim = ContrastUtil.contrast(s1, s2);
        System.out.println("虽然对象值一样对象不同。因为没有重写equal,所以区分不出来 ");
        if (null != sim) {
            System.out.println(sim.getOriginChange().m + " " + sim.getOriginChange().x + " " + sim.getOriginChange().y + " " + sim.getOriginChange().getList());
            System.out.println(sim.getTargetChange().m + " " + sim.getTargetChange().x + " " + sim.getTargetChange().y + " " + sim.getTargetChange().getList());
        }


        ContrastObject<ListObjectObj> sim2 = ContrastUtil.contrast(s1, s3);
        System.out.println("两个值不一样的对象 ");
        if (null != sim2) {
            System.out.println(sim2.getOriginChange().m + " " + sim2.getOriginChange().x + " " + sim2.getOriginChange().y + " " + sim2.getOriginChange().getList());
            System.out.println(sim2.getTargetChange().m + " " + sim2.getTargetChange().x + " " + sim2.getTargetChange().y + " " + sim2.getTargetChange().getList());
        }


        ContrastObject<ListObjectObj> sim3 = ContrastUtil.contrast(s1, s4);
        System.out.println("一个对象对多个对象的List ");
        if (null != sim3) {
            System.out.println(sim3.getOriginChange().m + " " + sim3.getOriginChange().x + " " + sim3.getOriginChange().y + " " + sim3.getOriginChange().getList());
            System.out.println(sim3.getTargetChange().m + " " + sim3.getTargetChange().x + " " + sim3.getTargetChange().y + " " + sim3.getTargetChange().getList());
        }


        ContrastObject<ListObjectObj> sim4 = ContrastUtil.contrast(s1, s5);
        System.out.println("空的List和非空List ");
        if (null != sim4) {
            System.out.println(sim4.getOriginChange().m + " " + sim4.getOriginChange().x + " " + sim4.getOriginChange().y + " " + sim4.getOriginChange().getList());
            System.out.println(sim4.getTargetChange().m + " " + sim4.getTargetChange().x + " " + sim4.getTargetChange().y + " " + sim4.getTargetChange().getList());
        }


    }

    private static void testListList() throws InstantiationException, IllegalAccessException {

        ListListObj s1 = new ListListObj();
        s1.setM((float) 2.2);
        s1.setY("55555");
        List<List<String>> list = new ArrayList<>();
        list.add(Lists.newArrayList("1", "2", "3"));
        s1.setList(list);


        ListListObj s2 = new ListListObj();
        s2.setM((float) 2.2);
        s2.setY("55555");

        ListListObj s3 = new ListListObj();
        s3.setM((float) 2.2);
        s3.setY("55555");
        List<List<String>> list1 = new ArrayList<>();
        list1.add(Lists.newArrayList("3", "2", "5", "99"));
        s3.setList(list1);


        ListListObj s4 = new ListListObj();
        s4.setM((float) 2.2);
        s4.setY("55555");
        List<List<String>> list2 = new ArrayList<>();
        list2.add(Lists.newArrayList("3", "2", "5", "99"));
        s4.setList(list2);

        ListListObj s5 = new ListListObj();
        s5.setM((float) 2.2);
        s5.setY("55555");
        List<List<String>> list3 = new ArrayList<>();
        list3.add(Lists.newArrayList("3", "2", "5", "99"));
        list3.add(Lists.newArrayList("5", "2", "5", "99"));
        list3.add(Lists.newArrayList("5", "2", "5", "99"));
        s5.setList(list3);


        ContrastObject<ListListObj> sim = ContrastUtil.contrast(s1, s2);
        System.out.println("空的List和非空List ");
        if (null != sim) {
            System.out.println(sim.getOriginChange().m + " " + sim.getOriginChange().x + " " + sim.getOriginChange().y + " " + sim.getOriginChange().getList());
            System.out.println(sim.getTargetChange().m + " " + sim.getTargetChange().x + " " + sim.getTargetChange().y + " " + sim.getTargetChange().getList());
        }


        ContrastObject<ListListObj> sim1 = ContrastUtil.contrast(s1, s3);
        System.out.println("List只有一个值，都是非空 ");
        if (null != sim1) {
            System.out.println(sim1.getTargetChange().m + " " + sim1.getTargetChange().x + " " + sim1.getTargetChange().y + " " + sim1.getTargetChange().getList());
            System.out.println(sim1.getOriginChange().m + " " + sim1.getOriginChange().x + " " + sim1.getOriginChange().y + " " + sim1.getOriginChange().getList());
        }


        ContrastObject<ListListObj> sim2 = ContrastUtil.contrast(s2, s3);
        System.out.println("空的List和非空List ");
        if (null != sim2) {
            System.out.println(sim2.getTargetChange().m + " " + sim2.getTargetChange().x + " " + sim2.getTargetChange().y + " " + sim2.getTargetChange().getList());
            System.out.println(sim2.getOriginChange().m + " " + sim2.getOriginChange().x + " " + sim2.getOriginChange().y + " " + sim2.getOriginChange().getList());
        }


        ContrastObject<ListListObj> sim3 = ContrastUtil.contrast(s5, s3);
        System.out.println("List为一个值和多个值，一样的值会进行剔除 ");
        if (null != sim3) {
            System.out.println(sim3.getTargetChange().m + " " + sim3.getTargetChange().x + " " + sim3.getTargetChange().y + " " + sim3.getTargetChange().getList());
            System.out.println(sim3.getOriginChange().m + " " + sim3.getOriginChange().x + " " + sim3.getOriginChange().y + " " + sim3.getOriginChange().getList());
        }


        ContrastObject<ListListObj> sim4 = ContrastUtil.contrast(s5, s1);
        System.out.println("List为一个值和多个值,值完全不一样 ");
        if (null != sim4) {
            System.out.println(sim4.getOriginChange().m + " " + sim4.getOriginChange().x + " " + sim4.getOriginChange().y + " " + sim4.getOriginChange().getList());
            System.out.println(sim4.getTargetChange().m + " " + sim4.getTargetChange().x + " " + sim4.getTargetChange().y + " " + sim4.getTargetChange().getList());
        }
    }


    private static void testSimpleList() throws InstantiationException, IllegalAccessException {
        SimpleListObj s1 = new SimpleListObj();
        s1.setM((float) 2.2);
        s1.setY("55555");
        List<String> list = new ArrayList<>();
        list.add("abc");
        s1.setList(list);

        SimpleListObj s2 = new SimpleListObj();
        s2.setM((float) 2.2);
        s2.setY("555556");
        List<String> list1 = new ArrayList<>();
        list1.add("abc");
        s2.setList(list1);


        SimpleListObj s3 = new SimpleListObj();
        s3.setM((float) 2.2);
        s3.setY("55555");
        List<String> list2 = new ArrayList<>();
        list2.add("abc");
        s3.setList(list2);


        SimpleListObj s4 = new SimpleListObj();
        s4.setM((float) 2.2);
        s4.setY("55555");
        List<String> list3 = new ArrayList<>();
        list3.add("abc");
        list3.add("bcd");
        list3.add("cde");
        s4.setList(list3);

        ContrastObject<SimpleListObj> sim = ContrastUtil.contrast(s1, s2);
        System.out.println("List的值一样 ，其他字段不一样");
        if (null != sim) {
            System.out.println(sim.getTargetChange().m + " " + sim.getTargetChange().x + " " + sim.getTargetChange().y + " " + sim.getTargetChange().getList());
            System.out.println(sim.getOriginChange().m + " " + sim.getOriginChange().x + " " + sim.getOriginChange().y + " " + sim.getOriginChange().getList());
        }


        ContrastObject<SimpleListObj> sim1 = ContrastUtil.contrast(s1, s3);
        System.out.println("List的值一样，其他字段也一样 ");
        if (null != sim1) {
            System.out.println(sim1.getTargetChange().m + " " + sim1.getTargetChange().x + " " + sim1.getTargetChange().y + " " + sim1.getTargetChange().getList().toString());
            System.out.println(sim1.getOriginChange().m + " " + sim1.getOriginChange().x + " " + sim1.getOriginChange().y + " " + sim1.getOriginChange().getList().toString());
        }


        ContrastObject<SimpleListObj> sim2 = ContrastUtil.contrast(s1, s4);
        System.out.println("List为一个值和多个值 ");
        if (null != sim2) {
            System.out.println(sim2.getTargetChange().m + " " + sim2.getTargetChange().x + " " + sim2.getTargetChange().y + " " + sim2.getTargetChange().getList().toString());
            System.out.println(sim2.getOriginChange().m + " " + sim2.getOriginChange().x + " " + sim2.getOriginChange().y + " " + sim2.getOriginChange().getList().toString());
        }
    }


    private static void testSimpleMap() throws InstantiationException, IllegalAccessException {
        SimpleMapObj m1 = new SimpleMapObj();
        m1.setM((float) 2.2);
        m1.setY("55555");
        HashMap<String, String> mm = new HashMap<>();
        mm.put("123", "345");
        m1.setMap(mm);

        SimpleMapObj m2 = new SimpleMapObj();
        m2.setM((float) 2.21);
        m2.setY("555551");
        HashMap<String, String> mm1 = new HashMap<>();
        mm1.put("123", "123");
        m2.setMap(mm1);

        SimpleMapObj m3 = new SimpleMapObj();
        m3.setM((float) 2.21);
        m3.setY("555551");
        HashMap<String, String> mm2 = new HashMap<>();
        mm2.put("123", "123");
        m3.setMap(mm2);

        SimpleMapObj m4 = new SimpleMapObj();
        m4.setM((float) 2.21);
        m4.setY("555551");
        HashMap<String, String> mm3 = new HashMap<>();
        mm3.put("123", "123");
        mm3.put("222", "123");
        m4.setMap(mm3);

        SimpleMapObj m5 = new SimpleMapObj();
        m5.setM((float) 2.21);
        m5.setY("555551");


        ContrastObject<SimpleMapObj> sim = ContrastUtil.contrast(m1, m2);
        System.out.println("Map只有一个值，并且不一样");
        if (null != sim) {
            System.out.println(sim.getTargetChange().m + " " + sim.getTargetChange().x + " " + sim.getTargetChange().y + " " + sim.getTargetChange().getMap().toString());
            System.out.println(sim.getOriginChange().m + " " + sim.getOriginChange().x + " " + sim.getOriginChange().y + " " + sim.getOriginChange().getMap().toString());
        }


        ContrastObject<SimpleMapObj> sim1 = ContrastUtil.contrast(m3, m2);
        System.out.println("Map的值相同并且其他字段也相同");
        if (null != sim1) {
            System.out.println(sim1.getTargetChange().m + " " + sim1.getTargetChange().x + " " + sim1.getTargetChange().y + " " + sim1.getTargetChange().getMap().toString());
            System.out.println(sim1.getOriginChange().m + " " + sim1.getOriginChange().x + " " + sim1.getOriginChange().y + " " + sim1.getOriginChange().getMap().toString());
        }


        ContrastObject<SimpleMapObj> sim2 = ContrastUtil.contrast(m3, m4);
        System.out.println("Map只有一个值和多个值");
        if (null != sim2) {
            System.out.println(sim2.getTargetChange().m + " " + sim2.getTargetChange().x + " " + sim2.getTargetChange().y + " " + sim2.getTargetChange().getMap().toString());
            System.out.println(sim2.getOriginChange().m + " " + sim2.getOriginChange().x + " " + sim2.getOriginChange().y + " " + sim2.getOriginChange().getMap().toString());
        }


        ContrastObject<SimpleMapObj> sim3 = ContrastUtil.contrast(m3, m5);
        System.out.println("测试有一个Map为空的情况");
        if (null != sim3) {
            System.out.println(sim3.getTargetChange().m + " " + sim3.getTargetChange().x + " " + sim3.getTargetChange().y + " " + sim3.getTargetChange().getMap());
            System.out.println(sim3.getOriginChange().m + " " + sim3.getOriginChange().x + " " + sim3.getOriginChange().y + " " + sim3.getOriginChange().getMap());
        }
    }

    private static void testSimple() throws InstantiationException, IllegalAccessException {
        SimpleObj s1 = new SimpleObj();
        s1.setM((float) 2.2);
        s1.setY("55555");
        ContrastObject<SimpleObj> sim = ContrastUtil.contrast(new SimpleObj(), s1);

        System.out.println("测试一下对象中的基本类型");
        if (sim != null) {
            System.out.println(sim.getTargetChange().m + " " + sim.getTargetChange().x + " " + sim.getTargetChange().y);
            System.out.println(sim.getOriginChange().m + " " + sim.getOriginChange().x + " " + sim.getOriginChange().y);
        }


        System.out.println("测试一下基本类型");
        ContrastObject<Integer> sim1 = ContrastUtil.contrast(1, 1);
        ContrastObject<Integer> sim2 = ContrastUtil.contrast(1, 2);
        ContrastObject<String> sim3 = ContrastUtil.contrast("1", "1");
        System.out.println("两个相同的Integer");
        if (null != sim1) {
            System.out.println(sim1.getTargetChange() + " " + sim1.getTargetChange() + " " + sim1.getTargetChange() + " " + sim1.getTargetChange());
            System.out.println(sim1.getOriginChange() + " " + sim1.getOriginChange() + " " + sim1.getOriginChange() + " " + sim1.getOriginChange());
        }


        System.out.println("两个不同的Integer");
        if (null != sim2) {
            System.out.println(sim2.getTargetChange() + " " + sim2.getTargetChange() + " " + sim2.getTargetChange() + " " + sim2.getTargetChange());
            System.out.println(sim2.getOriginChange() + " " + sim2.getOriginChange() + " " + sim2.getOriginChange() + " " + sim2.getOriginChange());
        }


        System.out.println("两个相同的String");
        if (null != sim3) {
            System.out.println(sim3.getTargetChange() + " " + sim3.getTargetChange() + " " + sim3.getTargetChange() + " " + sim3.getTargetChange());
            System.out.println(sim3.getOriginChange() + " " + sim3.getOriginChange() + " " + sim3.getOriginChange() + " " + sim3.getOriginChange());
        }


        System.out.println("两个不同的String");
        ContrastObject<String> sim4 = ContrastUtil.contrast("1", "2");
        if (null != sim4) {
            System.out.println(sim4.getTargetChange() + " " + sim4.getTargetChange() + " " + sim4.getTargetChange() + " " + sim4.getTargetChange());
            System.out.println(sim4.getOriginChange() + " " + sim4.getOriginChange() + " " + sim4.getOriginChange() + " " + sim4.getOriginChange());
        }


        ContrastObject<ListListObj> sim5 = ContrastUtil.contrast(null, new ListListObj());
        System.out.println("测试有null的情况");
        if (null != sim5) {
            System.out.println(sim5.getTargetChange() + " " + sim5.getTargetChange() + " " + sim5.getTargetChange() + " " + sim5.getTargetChange());
            System.out.println(sim5.getOriginChange() + " " + sim5.getOriginChange() + " " + sim5.getOriginChange() + " " + sim5.getOriginChange());
        }
    }
}
