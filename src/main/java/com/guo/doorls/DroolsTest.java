package com.guo.doorls;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.drools.KnowledgeBase;  
import org.drools.KnowledgeBaseFactory;  
import org.drools.builder.KnowledgeBuilder;  
import org.drools.builder.KnowledgeBuilderError;  
import org.drools.builder.KnowledgeBuilderErrors;  
import org.drools.builder.KnowledgeBuilderFactory;  
import org.drools.builder.ResourceType;  
import org.drools.io.ResourceFactory;  
import org.drools.runtime.StatefulKnowledgeSession;  

public class DroolsTest {

	public static void main(String[] args) {
		StringBuilder sb1 = new StringBuilder();
		sb1.append("  package com.guo.drools ");
		sb1.append("  import com.guo.doorls.MessageDrools  ");
		sb1.append("  rule ");
		sb1.append(" \"rule1\" ");
		sb1.append(" when ");
		sb1.append("MessageDrools( map[\"key1\"] == 1, myMessageDrools : msg )");
		sb1.append(" then ");
		sb1.append(" System.out.println( 1+\":\"+myMessageDrools ); ");
		sb1.append(" end ");

		StringBuilder sb2 = new StringBuilder();
		sb2.append("  package com.guo.drools ");
		sb2.append("  import com.guo.doorls.MessageDrools  ");
		sb2.append("  rule ");
		sb2.append(" \"rule2\" ");
		sb2.append(" when ");
		sb2.append("MessageDrools( map[\"key2\"] == 2, myMessageDrools : msg )");
		sb2.append(" then ");
		sb2.append(" System.out.println( 2+\":\"+myMessageDrools ); ");
		sb2.append(" end ");
        System.out.println("规则一: "+sb1.toString());
        System.out.println("规则二: "+sb2.toString());
        StatefulKnowledgeSession kSession = null;  
        try {  
            KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();  
            //装入规则，可以装入多个  
            kb.add(ResourceFactory.newByteArrayResource(sb1.toString().getBytes("utf-8")), ResourceType.DRL);  
            kb.add(ResourceFactory.newByteArrayResource(sb2.toString().getBytes("utf-8")), ResourceType.DRL);  
              
            KnowledgeBuilderErrors errors = kb.getErrors();
            for (KnowledgeBuilderError error : errors) {  
                System.out.println(error);  
            }  
            KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();  
            kBase.addKnowledgePackages(kb.getKnowledgePackages());  
              
            kSession = kBase.newStatefulKnowledgeSession();  
  
              
            MessageDrools MessageDrools1 = new MessageDrools();  
//            MessageDrools1.setStatus(1);  
            HashMap<String, Integer> map = new HashMap<>();
            MessageDrools1.setMsg("hello world!");  
            map.put("key1", 1);
            MessageDrools1.setMap(map);
           
            MessageDrools MessageDrools2 = new MessageDrools();  
            HashMap<String, Integer> map2 = new HashMap<>();
            MessageDrools2.setMsg("hi world!"); 
            map2.put("key2", 2);
            MessageDrools2.setMap(map2);
              
            kSession.insert(MessageDrools1);  
            kSession.insert(MessageDrools2);  
            kSession.fireAllRules();  
            String msg1 = MessageDrools1.getMsg();
            System.out.println("规则一结果为:"+msg1);
  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        } finally {  
            if (kSession != null)  
                kSession.dispose();  
        }  
	}
}
