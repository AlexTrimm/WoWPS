package com.world.domination.beans;

import java.util.Comparator;

public class VertexBeanComparator implements Comparator<VertexBean>{
	public int compare(VertexBean v1, VertexBean v2){
		return v1.compareTo(v2);
	}
}
