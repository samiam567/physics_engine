package jetpack_joyride;

import java.awt.Graphics;

import Physics_engine.Physics_drawable;
import Physics_engine.object_draw;
import calculator.Settings;

public class Monster extends Physics_drawable implements Comparable {
	
	public int height,weight,age;

	public Monster(object_draw drawer) {
		super(drawer);
		System.out.println("why do i exist");
	}
	
	public Monster(object_draw drawer,int height1, int weight1) {
		super(drawer);
		height = height1;
		weight = weight1;
	}
	
	public Monster(object_draw drawer,int height1, int weight1, int age1) {
		super(drawer);
		height = height1;
		weight = weight1;
		age = age1;
	}
	
	public void setVals(int height1, int weight1, int age1) {
		height = height1;
		weight = weight1;
		age = age1;
	}
	
	public double getMonsterSize() {
		return weight+age+height;
	}
	
	public int compareTo(Object c) {
		try {
			double d = getMonsterSize();
			double p = ((Monster) c).getMonsterSize();
			if (p > d) {
				return 1;
			}else if (p < d) {
				return -1;
			}else {
				return 0;
			}
		}catch(ClassCastException error) {
			return -5;
		}	
	}
	
	public Monster clone() {
		return new Monster(drawer,height,weight,age);
	}
	
	public boolean equals(Object obj) {
		try {
			Monster cMon = (Monster) obj;
			return ( (cMon.age == age) && (cMon.weight == weight) && (cMon.height == height) );
		}catch(ClassCastException c) {
			return false;
		}
	}
	
	public String toString() {
		return "Monster:: age:" + age + " weight:" + weight + " height:" + height + " ::";
	}
	
	public void paint(Graphics page) {
		page.drawRect(age * 4,-height + Settings.height/2,weight,weight);
		
		if ((height%10 == 0) && ( height > 1)) {
			String compare = "nAn";
			
			
			switch(compareTo(drawer.getObjects().get(height-1))) {
			case(-1):
				compare = "<";
			break;
			case(1):
				compare = ">";
			break;
			case(0):
				compare = "=";
			break;
			case(-5):
				compare = "previous object is not a monster";
			break;
			}
			
			System.out.print("#" + height + compare);
			page.drawString(toString() + ", " + compare, age * 4,-height + Settings.height/2 - 10);
		}
	}
	
}
