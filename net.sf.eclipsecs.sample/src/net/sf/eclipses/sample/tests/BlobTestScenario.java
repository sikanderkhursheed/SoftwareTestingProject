package net.sf.eclipses.sample.tests;
//antipattern Blob (gob class)
public class BlobTestScenario {
	  String name;
	  int size;
	  String type;
	  double area;
	  float perimeter;
	  float locationX;
	  float locationY;
	  // set and get name
	  public void setName(String name) {
		    this.name = name;
	  }
	  public String getName() {
		    return name;
	  }
	  // set and get size
	  public void setSize(int size) {
		    this.size = size;
	  }
	  public int getSize() {
		    return size;
	  }
	  // set and get type
	  public void setType(String type) {
		    this.type = type;
	  }
	  public String getType() {
		    return type;
	  }
	  // set and get area
	  public void setArea(double area) {
		    this.area = area;
	  }
	  public double getArea() {
		    return area;
	  }
	  // set and get perimeter
	  public void setPerimeter(float perimeter) {
		    this.perimeter = perimeter;
	  }
	  public float getPerimeter() {
		    return perimeter;
	  }
	  // set and get locationX
	  public void setLocationX(float locationX) {
		    this.locationX = locationX;
	  }
	  public float getLocationX() {
		    return locationX;
	  }
	  // set and get locationY
	  public void setLocationY(float locationY) {
		    this.locationY = locationY;
	  }
	  public float getLocationY() {
		    return locationY;
	  }
	  
	  // output default values of name, size, type, area, perimeter, locationX and locationY
	  // output default value of name
	  public String outputDefaultName() {
		    return "Shape";
	  }
	  // output default value of size
	  public int outputDefaultSize() {
		    return 0;
	  }
	  // output default value of type
	  public String outputDefaultType() {
		    return "shape";
	  }
	  // output default value of area
	  public double outputDefaultArea() {
		    return 0.00;
	  }
	  // output default value of perimeter 
	  public float outputDefaultPerimeter() {
		    return (float) 0.0;
	  }
	  // output default value of locationX 	
	  public float outputDefaultLocationX() {
		    return (float) 0.0;
	  }
	  // output default value of locationY 	
	  public float outputDefaultLocationY() {
		    return (float) 0.0;
	  }	    
	  
	  // print values of name, size, type, area, perimeter, locationX and locationY
	  // print the value of name
	  public void printDefaultName() {
		    System.out.println("Shape name is " + name);
	  }
	  // print the value of size
	  public void printDefaultSize() {
		    System.out.println("Shape size is " + size);
	  }
	  // print the value of type
	  public void printDefaultType() {
		  	System.out.println("Shape type is " + type);
	  }
	  // print the value of area
	  public void printDefaultArea() {
		  	System.out.println("Shape area is " + area);
	  }
	  // print the value of perimeter 
	  public void printDefaultPerimeter() {
		  	System.out.println("Shape perimeter is " + perimeter);
	  }
	  // print the value of locationX 	
	  public void printDefaultLocationX() {
		  	System.out.println("Shape locationX is" + locationX);
	  }
	  // print the value of locationY 	
	  public void printDefaultLocationY() {
		  	System.out.println("Shape locationY is " + locationY);
	  }	    		  
}
