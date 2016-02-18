package com.tuling.wechat.kaowu.sau.www;

import java.util.ArrayList;

public class RespMessage<T> {
	
	ArrayList<T> MassageConent = new ArrayList<T>();

	public ArrayList<T> getMassageConent() {
		return MassageConent;
	}

	public void setMassageConent(ArrayList<T> massageConent) {
		MassageConent = massageConent;
	}
	
	
}
