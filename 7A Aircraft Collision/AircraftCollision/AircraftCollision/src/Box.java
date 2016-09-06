//   Box.java
//Victor Chan, Christian Yon, Daabin Kim
import java.util.*;
public class  Box{
	double[] bdy;
	static int NumberPlanes = 100;
	public static void main (String[] args){
		double[] v ={0.,10.,0.,10.,0.,10.,0.,10.}; /* new double[8];*/
		//double[] p0 ={5.,5.,11.,5.}; /* new double[8];*/
		Box bx0= new Box(v);
		Point[] Planes = new Point[NumberPlanes];
		//bx0.pr(p0);
		//double[] p1 ={5.,5.,2.,5.}; /* new double[8];*/
		//bx0.pr(p1);
		//bx0.pr(double[] {5.,5.,2.,5.});
		//use find and replace to change nextDouble()*10 to nextDouble()*2 to get much higher probabilities of crashes
		Planes[0] =new Point(5.,5.,2.,0.,0.,2.,"Plane 1");//meant to crash with Plane 2 in 1.25 minute (1 minute and 15 seconds)
		bx0.pr(Planes[0]);
		Planes[1] =new Point(5.,5.,12.,0.,0.,-6.,"Plane 2");//meant to crash with Plane 1 in 1.25 minute (1 minute and 15 seconds)
		bx0.pr(Planes[1]);
		Planes[2] =(new Point(2.,4.,3.,5.,-1.,-4.,"Plane 3"));//meant to crash with Plane 4 in 6 minutes
		bx0.pr(Planes[2]);
		Planes[3] =(new Point(8.,10.,-3.,4.,-2.,-3.,"Plane 4"));//meant to crash with Plane 3 in 6 minutes
		bx0.pr(Planes[3]);
		for (int l=4;l<100;l++){
			Planes[l] =(new Point(Math.round(new Random().nextDouble()*10),Math.round(new Random().nextDouble()*10),Math.round(new Random().nextDouble()*10),Math.round(new Random().nextDouble()*10),Math.round(new Random().nextDouble()*10),Math.round(new Random().nextDouble()*10),"Plane "+(l-1)));
			bx0.pr(Planes[l]);
		}
		Planes[99] =(new Point(Math.round(new Random().nextDouble()*10),Math.round(new Random().nextDouble()*10),Math.round(new Random().nextDouble()*10),Math.round(new Random().nextDouble()*10),Math.round(new Random().nextDouble()*10),Math.round(new Random().nextDouble()*10),"Plane10"));
		bx0.pr(Planes[9]);
		//the positions and velocities of the planes are rounded to make it more likely that they will crash

		//(new Box(new double[]{0.,10.,0.,10.,5.,10.,0.,10.})).pr(new Point(5.,5.,2.,5.,2.,3.));	
		if (NumberPlanes==0||NumberPlanes==1){
			System.out.println("No collisions possible.");
		}
		else if(NumberPlanes>=2){
			for (int i=0;i<NumberPlanes-1;i++){
				for (int j=i+1;j<NumberPlanes;j++){
					collision(Planes[i],Planes[j]);
				}
			}
		}
		else System.out.println("Error.");
	} 
	public Box(double[] bdy0) {
		bdy=bdy0; //8 component array	
	}
	public boolean inBox(double[] p){ //4 component array
		return !(p[0]<bdy[0]||p[0]>bdy[1]||p[1]<bdy[2]||p[1]>bdy[3]||p[2]<bdy[4]||p[2]>bdy[5]||p[3]<bdy[6]||p[3]>bdy[7]);
	}
	public boolean inBox(Point p){ //4 component array
		return !(p.x<bdy[0]||p.x>bdy[1]||p.y<bdy[2]||p.y>bdy[3]||p.z<bdy[4]||p.z>bdy[5]);
	}
	public void pr(double[] p0){ // apply inBox(Array) to box and display
		for(int i=0;i<NumberPlanes;i++) System.out.print(p0[i]+"\t");
		System.out.println(inBox(p0));
	}
	public void pr(Point p){ // apply inBox(Point) to box and display
		System.out.print(p.x+"\t"+p.y+"\t"+p.z+"\t"+p.vx+"\t"+p.vy+"\t"+p.vz+"\t");
		System.out.println(inBox(p));
	}
	public static void collision(Point p, Point p1){
		if (p.vx<=10&&p.vx>=-10&&p.vy<=10&&p.vy>=-10&&p.vz<=10&&p.vz>=-10){
			if (p1.vx<=10&&p1.vx>=-10&&p1.vy<=10&&p1.vy>=-10&&p1.vz<=10&&p1.vz>=-10){
				boolean collisionPossible = false;
				double time = 0;
				for(double t=0;t<=10000;t++){
					double dx = (p.vx)*t/1000 + p.x;
					double dy = (p.vy)*t/1000 + p.y;
					double dz = (p.vz)*t/1000 + p.z;
					double dx1 = (p1.vx)*t/1000 + p1.x;
					double dy1 = (p1.vy)*t/1000 + p1.y;
					double dz1 = (p1.vz)*t/1000 + p1.z;
					if (dx==dx1 && dy==dy1 && dz==dz1){
						collisionPossible = true;
						time = t/1000;
						break;
					}
					else collisionPossible = false;
				}
				if (collisionPossible==true){
					System.out.println(p.ID+" and "+p1.ID+" will crash in "+Math.floor(time)+" minutes and "+(time%1)*60+" seconds.");
				}
			}
			else System.out.println("The speed of the plane is not realistic.");
		}
		else System.out.println("The speed of the plane is not realistic.");
		
	}
	
}
// end of Box.java