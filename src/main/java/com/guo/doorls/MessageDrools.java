package com.guo.doorls;

import java.util.HashMap;
//@org.kie.api.definition.type.Label("消息")  
public class MessageDrools {

	private String msg;  
    private HashMap<String, Integer> map;  
  
    public MessageDrools() {  
    	MessageDrools();  
    }  
  
    private void MessageDrools() {
	}

	public String getMsg() {  
        return this.msg;  
    }  
  
    public void setMsg(String message) {  
        this.msg = message;  
    }

	public HashMap<String, Integer> getMap() {
		return map;
	}

	public void setMap(HashMap<String, Integer> map) {
		this.map = map;
	}
}
